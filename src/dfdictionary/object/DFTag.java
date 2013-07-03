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

package dfdictionary.object;

import java.util.LinkedList;
import java.util.List;

/**
 * @class DFTag
 * @author tvarney
 * 
 * This class is used in the DFObjectParser to parse out individual tags from
 * lines of text. Instead of the normal approach others tend to use to parse
 * tags (regexs), I chose to implement a more traditional parser that loops over
 * the input searching for specific start and end tags.
 * This class does not support tags nested within each other, nor does it
 * represent groupings of tags. A tag is thus defined as the grouping of strings
 * between DFTag.START and DFTag.END. If DFTag.START while already parsing the
 * contents of a tag, it is considered a Syntax error.
 * This class also contains all of the constants used for various tags, such as
 * the definitions for object definitions, various word flags, u.s.w.
 */
public class DFTag {
    ////
    // Global tag characters
    public static char START = '[';
    public static char END   = ']';
    public static char SEP   = ':';
    
    ////
    // Object definition tags
    public static String OBJ = "OBJECT";
    public static String LANG_OBJ = "LANGUAGE";
    
    ////
    // Word tags
    public static String WORD = "WORD";
    // Noun tags
    public static String NOUN = "NOUN";
    public static String NOUN_THE_SINGULAR = "THE_NOUN_SING";
    public static String NOUN_THE_PLURAL   = "THE_NOUN_PLUR";
    public static String NOUN_COMPOUND_SINGULAR = "THE_COMPOUND_NOUN_SING";
    public static String NOUN_COMPOUND_PLURAL   = "THE_COMPOUND_NOUN_PLUR";
    public static String NOUN_OF_SINGULAR = "OF_NOUN_SING";
    public static String NOUN_OF_PLURAL   = "OF_NOUN_PLUR";
    public static String NOUN_FRONT_SINGULAR = "FRONT_COMPOUND_NOUN_SING";
    public static String NOUN_FRONT_PLURAL   = "FRONT_COMPOUND_NOUN_PLUR";
    public static String NOUN_REAR_SINGULAR = "REAR_COMPOUND_NOUN_SING";
    public static String NOUN_REAR_PLURAL   = "REAR_COMPOUND_NOUN_PLUR";
    // Verb tags
    public static String VERB = "VERB";
    public static String VERB_STANDARD = "STANDARD_VERB";
    // Adjective tags
    public static String ADJECTIVE = "ADJ";
    public static String ADJECTIVE_DISTANCE = "ADJ_DIST";
    public static String ADJECTIVE_FRONT = "FRONT_COMPOUND_ADJ";
    public static String ADJECTIVE_REAR = "REAR_COMPOUND_ADJ";
    public static String ADJECTIVE_THE = "THE_COMPOUND_ADJ";
    // Prefix tags
    public static String PREFIX = "PREFIX";
    public static String PREFIX_FRONT = "FRONT_COMPOUND_PREFIX";
    public static String PREFIX_THE   = "THE_COMPOUND_PREFIX";
    
    ////
    // Symbol tags
    public static String SYMBOL   = "SYMBOL";
    public static String SYM_WORD = "S_WORD";
    
    ////
    // Translation tags
    public static String TRANSLATION = "TRANSLATION";
    public static String TRANS_WORD  = "T_WORD";
    
    // Write a tag to a given PrintWriter object
    // Convience method to make printing tags simple
    public static String format(String tag, Object... args) {
        StringBuilder builder = new StringBuilder(64);
        builder.append(DFTag.START);
        
        builder.append(tag);
        for(Object arg : args) {
            builder.append(DFTag.SEP);
            builder.append(arg.toString());
        }
        
        builder.append(DFTag.END);
        return builder.toString();
    }
    
    public String tag;
    public String[] data;
    public DFTag() {
        this("", null);
    }
    public DFTag(String tag, String[] data) {
        this.tag = tag;
        // Not quite sure if this is required =\
        if(data != null) {
            this.data = new String[data.length];
            for(int i = 0; i < data.length; ++i) {
                this.data[i] = data[i];
            }
        }
    }
    
    
    public static DFTag[] getTags(String line) {
        /* The list of tags that we have parsed from this line of text */
        List<DFTag> tags = new LinkedList<DFTag>();
        /* The list of parts in an individual DFTag */
        List<String> data = new LinkedList<String>();
        /* A StringBuilder to be used with the parser */
        StringBuilder builder = new StringBuilder();
        /* *pointer* to the tag data */
        String tag;
        
        int i = 0;
        int len = line.length();
        int blen;
        /* Array look up should be faster than line.charAt(i) calls. */
        char[] str = line.toCharArray();
        while(i < len) {
            data.clear(); //< Clear anything from the last pass
            /* Find the start of a tag. This uses a trick with a while loop to
             * check and increment in the same line. Yay the shit you learn
             * coding C.
             */
            while(i < len && str[i++] != DFTag.START); //< Ignore IDE warnings
            if(i >= len) break; //< We didn't find any start tags
            /* Get the tag data */
            blen = builder.length();
            if(blen > 0) {
                builder.delete(0, blen);
            }
            while(i < len && str[i] != DFTag.END) {
                if(str[i] == DFTag.SEP) {
                    data.add(builder.toString());
                    /* Clear the string builder */
                    blen = builder.length();
                    if(blen > 0) {
                        builder.delete(0, blen);
                    }
                }else {
                    builder.append(str[i]);
                }
                i++;
            }
            /* Add the last set of data obtained to data
             * The nice thing about having one single list is that there is no
             * distinction between the tag and the data, allowing the parsing
             * to be much simpler.
             */
            data.add(builder.toString());
            
            tag = data.remove(0);
            String[] ar = new String[data.size()];
            tags.add(new DFTag(tag, data.toArray(ar)));
        }
        
        /* Return the list of accumulated tags /if/ we have any */
        if(tags.size() > 0) {
            DFTag[] rarray = new DFTag[tags.size()];
            return tags.toArray(rarray);
        }
        return null;
    }
}
