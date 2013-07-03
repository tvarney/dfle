package dfdictionary.object;

import dfdictionary.io.SyntaxException;

import java.util.SortedSet;
import java.util.TreeSet;


/**
 *
 * @author tvarney
 */
public class DFWord extends DFObject {
    public boolean is_noun, is_verb, is_adj, is_prefix;
    public Noun noun;
    public Adjective adj;
    public Verb verb;
    public Prefix prefix;
    
    protected SortedSet<String> syms;
    
    public DFWord() {
        this("", null);
    }
    public DFWord(String tag) {
        this(tag, null);
    }
    public DFWord(String tag, DFTag[] data) {
        super(tag, data);
        
        this.is_noun = this.is_verb = this.is_adj = this.is_prefix = false;
        
        this.noun   = new Noun();
        this.adj    = new Adjective();
        this.verb   = new Verb();
        this.prefix = new Prefix();
        
        this.syms = new TreeSet<String>();
    }
    
    @Override
    public String objectTag() {
        return DFTag.WORD;
    }
    
    @Override
    public void set(DFTag[] data) throws SyntaxException {
        int i = 0;
        int len = data.length;
        DFTag tp;
        
        while(i < len) {
            tp = data[i];
            if(tp.tag.equals(DFTag.ADJECTIVE)) {
                if(tp.data == null || tp.data.length < 1) {
                    //TODO: Log that there are no args for this adjective
                }else {
                    this.is_adj = true;
                    adj = new Adjective(tp.data[0]);
                    i += adj.set(data, i + 1);
                    if(tp.data.length > 1) {
                        //TODO: Warn that we are discarding args
                    }
                }
            }else if(tp.tag.equals(DFTag.NOUN)) {
                if(tp.data == null || tp.data.length < 2) {
                    //TODO: Log that there are too few args for this Noun
                }else {
                    this.is_noun = true;
                    noun = new Noun(tp.data[0], tp.data[1]);
                    i += noun.set(data, i + 1);
                    if(tp.data.length > 2) {
                        //TODO: Warn that we are discarding args
                    }
                }
            }else if(tp.tag.equals(DFTag.VERB)) {
                if(tp.data == null || tp.data.length < 5) {
                    //TODO: Log that there are too few args for this Verb
                }else {
                    this.is_verb = true;
                    verb = new Verb(tp.data[0], tp.data[1], tp.data[2],
                                    tp.data[3], tp.data[4]);
                    i += verb.set(data, i + 1);
                    
                    if(tp.data.length > 5) {
                        //TODO: Warn that we are discarding extra args
                    }
                }
            }else if(tp.tag.equals(DFTag.PREFIX)){
                if(tp.data == null || tp.data.length < 1) {
                    //TODO: Log that there are too few ares for this Prefix
                }else {
                    this.is_prefix = true;
                    prefix = new Prefix(tp.data[0]);
                    prefix.set(data, i + 1);
                    if(tp.data.length > 1) {
                        //TODO: Warn that we are discarding args
                    }
                }
            }else {
                //TODO: Throw a syntax error or log that we have a stray tag
            }
            i++;
        }
    }
    
    public void add(String sym) {
        syms.add(sym);
    }
    public void remove(String sym) {
        syms.remove(sym);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Word Subclasses">
    public class Noun {
        public String singular, plural;
        public boolean the_singular, the_plural;
        public boolean the_compound_singular, the_compound_plural;
        public boolean of_singular, of_plural;
        public boolean front_compound_singular, front_compound_plural;
        public boolean rear_compound_singular, rear_compound_plural;
        
        private Noun() {
            this("", "");
        }
        private Noun(String singular, String plural) {
            this.singular = singular;
            this.plural = plural;
            
            the_singular            = the_plural            = false;
            the_compound_singular   = the_compound_plural   = false;
            of_singular             = of_plural             = false;
            front_compound_singular = front_compound_plural = false;
            rear_compound_singular  = rear_compound_plural  = false;
            
        }
        
        public int set(DFTag[] data, int offset) {
            int num = 0;
            DFTag tp;
            
            while(num + offset < data.length) {
                tp = data[num + offset];
                /*TODO: Warn about discarding args to noun tags */
                if(tp.tag.equals(DFTag.NOUN_THE_SINGULAR)) {
                    this.the_singular = true;
                }else if(tp.tag.equals(DFTag.NOUN_THE_PLURAL)) {
                    this.the_plural = true;
                }else if(tp.tag.equals(DFTag.NOUN_COMPOUND_SINGULAR)) {
                    this.the_compound_singular = true;
                }else if(tp.tag.equals(DFTag.NOUN_COMPOUND_PLURAL)) {
                    this.the_compound_plural = true;
                }else if(tp.tag.equals(DFTag.NOUN_OF_SINGULAR)) {
                    this.of_singular = true;
                }else if(tp.tag.equals(DFTag.NOUN_OF_PLURAL)) {
                    this.of_plural = true;
                }else if(tp.tag.equals(DFTag.NOUN_FRONT_SINGULAR)) {
                    this.front_compound_singular = true;
                }else if(tp.tag.equals(DFTag.NOUN_FRONT_PLURAL)) {
                    this.front_compound_plural = true;
                }else if(tp.tag.equals(DFTag.NOUN_REAR_SINGULAR)) {
                    this.rear_compound_singular = true;
                }else if(tp.tag.equals(DFTag.NOUN_REAR_PLURAL)) {
                    this.rear_compound_plural = true;
                }else {
                    break;
                }
                num++; //< I could probably move this up to tp = ...;
            }
            
            return num;
        }
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(1024);
            builder.append('\t');
            builder.append(DFTag.format(DFTag.NOUN, singular, plural));
            builder.append('\n');
            if(the_singular) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_THE_SINGULAR));
                builder.append('\n');
            }
            if(the_plural) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_THE_PLURAL));
                builder.append('\n');
            }
            if(the_compound_singular) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_COMPOUND_SINGULAR));
                builder.append('\n');
            }
            if(the_compound_plural) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_COMPOUND_PLURAL));
                builder.append('\n');
            }
            if(of_singular) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_OF_SINGULAR));
                builder.append('\n');
            }
            if(of_plural) {
                builder.append("\t\t");
                DFTag.format(DFTag.NOUN_OF_PLURAL);
                builder.append('\n');
            }
            if(front_compound_singular) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_FRONT_SINGULAR));
                builder.append('\n');
            }
            if(front_compound_plural) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_FRONT_PLURAL));
                builder.append('\n');
            }
            if(rear_compound_singular) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_REAR_SINGULAR));
                builder.append('\n');
            }
            if(rear_compound_plural) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.NOUN_REAR_PLURAL));
                builder.append('\n');
            }
            
            return builder.toString();
        }
    }
    public class Adjective {
        public String adj;
        public int adjective_distance;
        public boolean the_adj, front_adj, rear_adj;
        
        private Adjective() {
            this("");
        }
        private Adjective(String adj) {
            this.adj = adj;
            
            this.adjective_distance = -1;
            
            this.the_adj   = false;
            this.front_adj = false;
            this.rear_adj  = false;
        }
        
        public int set(DFTag[] data, int offset) {
            int num = 0;
            DFTag tp;
            
            while(num + offset < data.length) {
                tp = data[num + offset];
                /*TODO: Log about discarding args from adjective tags */
                if(tp.tag.equals(DFTag.ADJECTIVE_DISTANCE)) {
                    if(tp.data != null && tp.data.length > 0) {
                        this.adjective_distance = Integer.parseInt(tp.data[0]);
                        if(tp.data.length > 1) {
                            //TODO: Warn about discarding extra ADJ_DIST args
                        }
                    }else {
                        /*TODO: Log that there are too few args for adj_dist */
                    }
                }else if(tp.tag.equals(DFTag.ADJECTIVE_FRONT)) {
                    this.front_adj = true;
                }else if(tp.tag.equals(DFTag.ADJECTIVE_REAR)) {
                    this.rear_adj = true;
                }else if(tp.tag.equals(DFTag.ADJECTIVE_THE)) {
                    this.the_adj = true;
                }else {
                    break;
                }
                num++;
            }
            
            return num;
        }
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(1024);
            builder.append('\t');
            builder.append(DFTag.format(DFTag.ADJECTIVE, adj));
            builder.append('\n');
            if(adjective_distance >= 0) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.ADJECTIVE_DISTANCE,
                               adjective_distance));
                builder.append('\n');
            }
            if(the_adj) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.ADJECTIVE_THE));
                builder.append('\n');
            }
            if(front_adj) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.ADJECTIVE_FRONT));
                builder.append('\n');
            }
            if(rear_adj) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.ADJECTIVE_REAR));
                builder.append('\n');
            }
            
            return builder.toString();
        }
    }
    public class Verb {
        public String infinitive, present, past, pastpart, presentpart;
        public boolean standard_verb; //< Always true?
        
        private Verb() {
            this("", "", "", "", "");
        }
        private Verb(String inf, String pres, String past, String pastp,
                     String presp) {
            this.infinitive  = inf;
            this.present     = pres;
            this.past        = past;
            this.pastpart    = pastp;
            this.presentpart = presp;
            
            this.standard_verb = false;
        }
        
        public int set(DFTag[] data, int offset) {
            int num = 0;
            DFTag tp;
            
            while(num + offset < data.length) {
                tp = data[num + offset];
                if(tp.tag.equals(DFTag.VERB_STANDARD)) {
                    this.standard_verb = true;
                }else {
                    break;
                }
                num++;
            }
            
            return num;
        }
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(512);
            builder.append('\t');
            builder.append(DFTag.format(DFTag.VERB, infinitive, present, past,
                                        pastpart, presentpart));
            builder.append('\n');
            
            if(standard_verb) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.VERB_STANDARD));
                builder.append('\n');
            }
            
            return builder.toString();
        }
    }
    public class Prefix {
        public String pfx;
        public boolean front_prefix, the_prefix;
        
        public Prefix() {
            this("");
        }
        public Prefix(String pfx) {
            this.pfx = pfx;
            this.front_prefix = false;
            this.the_prefix   = false;
        }
        
        public int set(DFTag[] data, int offset) {
            int num = 0;
            DFTag tp;
            
            while(num + offset < data.length) {
                tp = data[num + offset];
                if(tp.tag.equals(DFTag.PREFIX_FRONT)) {
                    this.front_prefix = true;
                }else if(tp.tag.equals(DFTag.PREFIX_THE)) {
                    this.the_prefix = true;
                }else {
                    break;
                }
                num++;
            }
            
            return num;
        }
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(256);
            builder.append('\t');
            builder.append(DFTag.format(DFTag.PREFIX, pfx));
            builder.append('\n');
            
            if(front_prefix) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.PREFIX_FRONT));
                builder.append('\n');
            }
            if(the_prefix) {
                builder.append("\t\t");
                builder.append(DFTag.format(DFTag.PREFIX_THE));
                builder.append('\n');
            }
            
            return builder.toString();
        }
    }
    // </editor-fold>
    
    @Override
    public String buildTagString() {
        StringBuilder builder = new StringBuilder(1024);
        builder.append(DFTag.format(DFTag.WORD, tag));
        builder.append('\n');
        
        if(is_noun) {
            builder.append(noun.toString());
        }
        if(is_verb) {
            builder.append(verb.toString());
        }
        if(is_adj) {
            builder.append(adj.toString());
        }
        if(is_prefix) {
            builder.append(prefix.toString());
        }
        
        return builder.toString();
    }
    
    public String[] getSymbolList() {
        String[] symbols = new String[syms.size()];
        
        symbols = syms.toArray(symbols);
        
        return symbols;
    }
}   
