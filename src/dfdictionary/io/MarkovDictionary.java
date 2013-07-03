/* -*- mode: Java; tab-width: 4 -*- */

/*
 * Copyright (C) 2011 Troy Varney
 * 
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package dfdictionary.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @class MarkovDictionary
 * @author tvarney
 * 
 * A Markov Chain generator used for generating random words. Each word, as
 * separated by whitespace, is treated as a new set of characters for input.
 * This departs from traditional Markov Chains in which the entire text is
 * consumed as one large part.
 * 
 * Not finished.
 */
public class MarkovDictionary {
    public static String CHAIN_ENTRY = "";  //< Empty String is our start
    public static String CHAIN_EXIT  = null; //< null indicates a stop.
    public static int DEFAULT_ORDER = 1;
    
    private Map<String, Node> nodes; //< Used for lookup
    private int order;
    private Node head, tail;
    private Random rand;
    
    /* Word length stuff, allows us to limit word lengths in a semi-intelligent
     * manner. Previous testing of a similar scheme showed that input consisting
     * of several hundred short words could, and would quite frequently, produce
     * words of greater than 13 characters.
     */
    private int input_count;
    // avg = (avg * input_count + input.length()) / (input_count + 1)
    private double avg_len;
    private int max_len;
    private int min_len;
    
    public MarkovDictionary() {
        this(DEFAULT_ORDER);
    }
    public MarkovDictionary(int order) {
        if(order > 0) {
            this.order = order;
        }else {
            this.order = DEFAULT_ORDER;
        }
        
        /* Initialize primitives all to 0 */
        avg_len = 0.0;
        input_count = max_len = 0;
        min_len = Integer.MAX_VALUE;
        
        rand = new Random();
        nodes = new HashMap<String, Node>();
        head = new Node(CHAIN_ENTRY);
        tail = new Node(CHAIN_EXIT);
        
        /* Don't actually need to put them in the map, but better safe than
         * sorry.
         */
        nodes.put(CHAIN_ENTRY, head);
        nodes.put(CHAIN_EXIT, tail);
    }
    
    public int consume(String fname)
        throws FileNotFoundException, IOException {
        return consume(new FileInputStream(new File(fname)));
    }
    public int consume(InputStream is)
        throws IOException {
        String line;
        List<String> lines;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        lines = new LinkedList<String>();
        
        
        return 0;
    }
    public int add(String word) {
        StringTokenizer tokenizer;
        String sub, token;
        int num, mod, i, len, agg_len;
        Node curr, next;
        if(word == null) {
            return 0;
        }
        
        num = 0;
        agg_len = 0;
        tokenizer = new StringTokenizer(word);
        while(tokenizer.hasMoreTokens()) {
            num++; //< Keep track of the number of tokens
            token = tokenizer.nextToken();
            
            len = token.length();
            /* Update min/max length and the aggregate length */
            agg_len += len;
            if(len < min_len) {
                min_len = len;
            }else if(len > max_len) {
                max_len = len;
            }
            
            mod = len % order;
            curr = head;
            for(i = 0; i < (len - mod); i += order) {
                /* get the current substring */
                sub = token.substring(i, i + order);
                /* Get existing node, or create a new one */
                if(nodes.containsKey(sub)) {
                    next = nodes.get(sub);
                }else {
                    next = new Node(sub);
                    nodes.put(sub, next);
                }
                curr.add(next);
                curr = next;
            }
            if(mod > 0) {
                /* Add the section of the word that is not of the order
                 * specified. These will always be terminal strings.
                 */
                sub = token.substring(len - mod, len);
            }
            
            /* Finish this word chain with the chain exit, reset to head */
            curr.add(tail);
            curr = head;
        }
        
        /* Update average length */
        double ratio = ((double)input_count) / ((double)input_count + num);
        avg_len = avg_len / ratio + agg_len / (input_count + num);
        
        /* Update count */
        input_count += num;
        
        return num; //< Return how many tokens were in the string
    }
    public int add(String[] words) {
        int num = 0;
        for(String word : words) {
            num += add(word);
        }
        
        return num;
    }
    public int add(List<String> words) {
        int num = 0;
        for(String word : words) {
            num += add(word);
        }
        
        return num;
    }
    
    /**
     * Returns a randomly created word. The word is of a random length between
     * the minimum word size entered into this dictionary and the maximum word
     * size entered into this dictionary, weighted towards the average word
     * size.
     * @see nextWord(int min, int max, double avg)
     * @return A random string of a random length.
     */
    public String nextWord() {
        return nextWord(min_len, max_len, avg_len);
    }
    
    /**
     * Generates a random word. The word length is between min and max,
     * weighted towards avg. min is considered a hard limit, that is no word
     * will be shorter than min characters long. max is a soft limit, in that
     * words can exceed that many characters in length if the nodes can not
     * terminate.
     * @param min The minimum length of the string to return.
     * @param max The maximum length of the string to return.
     * @param avg The average length of the string to return.
     * @return A random string of a random length between min and max.
     */
    public String nextWord(int min, int max, double avg) {
        StringBuilder builder;
        Node curr, next;
        int count, iavg, bound;
        double term_chance, term_step;
        boolean term;
        
        builder = new StringBuilder();
        curr = head;
        count = 0;
        iavg = (int)avg;
        /* Observe the hard limit of min */
        while(count < min) {
            next = curr.next(false);
            count += next.value().length();
            builder.append(next.value());
            curr = next;
        }
        // I may want to change this formula to use iavg instead
        term_step = 0.50 / (avg - ((double)min));
        term_chance = term_step;
        term = false;
        bound = iavg;
        while(count < max) {
            while(count < bound) {
                if(term || rand.nextDouble() <= term_chance) {
                    if(curr.canTerminate()) {
                        return builder.toString();
                    }
                    term = true;
                }
                
                next = curr.next();
                if(next == tail) {
                    return builder.toString();
                }
                
                count += next.value().length();
                builder.append(next.value());
                curr = next;
                term_chance += term_step * next.value().length();
            }
            term_step = 0.50 / (((double)max) - avg);
        }
        /* Force a termination ASAP */
        while(!curr.canTerminate()) {
            curr = curr.next();
            builder.append(curr.value());
        }
        return builder.toString();
    }
    
    private class Node implements Comparable{
        private String str;
        private int edge_total;
        private int end_total;
        private TreeSet<Edge> edges;
        
        public Node(String s) {
            edges = new TreeSet<Edge>();
            edge_total = 0;
            end_total = 0;
            str = s;
        }
        
        public boolean canTerminate() {
            return (end_total > 0);
        }
        public String value() {
            return str;
        }
        public int getEdgeCount() {
            return edge_total;
        }
        
        public Node add(Node n) {
            Edge e, cmp;
            cmp = new Edge(n);
            e = edges.ceiling(cmp);
            if(cmp.equals(e)) {
                e.count++; //< Simply increment the count
                if(e.toNode.equals(tail)) {
                    end_total++;
                }
            }else {
                edges.add(cmp);
                if(cmp.toNode.equals(tail)) {
                    end_total++;
                }
            }
            edge_total++;
            
            return n;
        }
        
        public Node next() {
            return next(true);
        }
        public Node next(boolean can_null) {
            if(can_null) {
                return nextNull();
            }
            return nextNonNull();
        }
        
        public Node nextNonNull() {
            int r;
            int num = edge_total - end_total;
            if(num <= 0) {
                return head;
            }
            
            r = rand.nextInt(num) + 1;
            for(Edge e : edges) {
                if(e.toNode.equals(tail)) {
                    continue;
                }
                r -= e.count;
                if(r <= 0) {
                    return e.toNode;
                }
            }
            
            return head;
        }
        public Node nextNull() {
            int r = rand.nextInt(edge_total) + 1;
            for(Edge e : edges) {
                r -= e.count;
                if(r <= 0) {
                    return e.toNode;
                }
            }
            
            System.err.printf("DFDictionary::MarkovDictionary::Invalid edge count.\n");
            return new Node(null); //< Shouldn't happen
        }
        
        @Override
        public int compareTo(Object o) {
            Node n;
            if(o == null) {
                return 1; //< !null > null
            }
            n = (Node)o;
            if(str == null) {
                if(n.str == null) {
                    return 0; //< null == null
                }
                return -1; //< null < !null
            }
            if(n.str == null) {
                return 1; //< !null > null
            }
            return str.compareTo(n.str);
        }
        
        @Override
        public boolean equals(Object o) {
            try {
                if(compareTo(o) == 0) {
                    return true;
                }
                return false;
            }catch(ClassCastException cce) {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + (this.str != null ? this.str.hashCode() : 0);
            return hash;
        }
        
        @Override
        public String toString() {
            return value();
        }
        
        public String graphRepresentation(String prfx) {
            StringBuilder builder = new StringBuilder();
            
            builder.append(prfx);
            builder.append(str);
            builder.append("\n");
            
            /* Get a sortable array of the edges and sort */
            Edge[] edgs = new Edge[edges.size()];
            edgs = edges.toArray(edgs);
            java.util.Arrays.sort(edgs);
            
            for(Edge e : edgs) {
                builder.append(prfx);
                builder.append("  -> ");
                builder.append(e.toNode.str);
                builder.append("\n");
            }
            
            return builder.toString();
        }
        
        public class Edge implements Comparable {
            public Node toNode;
            public int count;
            
            public Edge(Node n) {
                count = 1; //< If we are making a new edge, it has a count of 1
                toNode = n;
            }
            
            @Override
            public boolean equals(Object o) {
                try {
                    if(compareTo(o) == 0) {
                        return true;
                    }
                    return false;
                }catch(ClassCastException cce) {
                    return false;
                }
            }

            @Override
            public int hashCode() {
                int hash = 7;
                hash = 41 * hash + (this.toNode != null ? this.toNode.hashCode() : 0);
                return hash;
            }
            
            @Override
            public int compareTo(Object o) {
                Edge e;
                if(o == null) {
                    return 1; //< An edge is greater than null
                }
                e = (Edge)o; //< ClassCastException thrown here
                if(toNode == null) {
                    if(e.toNode == null) {
                        return 0; //< null == null
                    }
                    return -1; //< null < !null
                }
                if(e.toNode == null) {
                    return 1; //< !null > null
                }
                return toNode.compareTo(e.toNode);
            }
        }
    }
}
