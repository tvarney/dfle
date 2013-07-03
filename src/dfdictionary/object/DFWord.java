package dfdictionary.object;

import dfdictionary.io.SyntaxException;

import java.util.SortedSet;
import java.util.TreeSet;


/**
 * @class DFWord
 * @author tvarney
 * @brief Representation of a word in the DF format.
 * 
 * This class represents a word as defined in the raws. Each word may be any
 * or all of a noun, adjective, verb or prefix.
 * This class also defines subclasses Noun, Adjective, Verb, and Prefix for
 * convenience of representation, as well as delegating the input and output
 * of tags for each word type.
 * In addition to the word data, this class adds the ability to associate
 * symbol data with the object, unlike the dwarf fortress definition. This is
 * to ease editing of the symbols on a per-word basis. The list of symbols for
 * each word is represented as the words are in the DFSymbol class: with a
 * sorted set of strings.
 */
public class DFWord extends DFObject {
    /**
     * is_noun
     * Tells the user if the word is considered a noun.
     * Since DFWord always creates at least an empty Noun class for each
     * instance, setting this member has no side-effects on other members of
     * any instance. Thus, instead of using a getter/setter method, it is
     * simply made public.
     */
    /**
     * is_verb
     * Tells the user if the word is considered a verb.
     * @see is_noun
     */
    /**
     * is_adj
     * Tells the user if the word is considered an adjective.
     * @see is_noun
     */
    /**
     * is_prefix
     * Tells the user if the word is considered a prefix.
     * @see is_noun
     */
    public boolean is_noun, is_verb, is_adj, is_prefix;
    
    /**
     * noun
     * The internal representation of a DFWord Noun structure.
     */
    //Should these remain public?
    public Noun noun;
    /**
     * adj
     * The internal representation of a DFWord Adjective structure.
     */
    public Adjective adj;
    /**
     * verb
     * The internal representation of a DFWord Verb structure.
     */
    public Verb verb;
    /**
     * prefix
     * The internal representation of a DFWord Prefix structure.
     */
    public Prefix prefix;
    
    /**
     * syms
     * The set of all symbols associated this word is associated with.
     */
    protected SortedSet<String> syms;
    
    /**
     * Creates a new DFWord with the empty string as its unique tag and
     * no data.
     */
    public DFWord() {
        this("", null);
    }
    /**
     * Creates a new DFWord with the given string as its unique tag and no data.
     * @param tag The unique tag that references this word.
     */
    public DFWord(String tag) {
        this(tag, null);
    }
    /**
     * Creates a new DFWord with the given string as its unique tag and the
     * given data as its data. The tags in data are parsed in the constructor.
     * The preferred method to create a new DFTag from data is to NOT use this
     * constructor, but to create a new word from the tag, then call the
     * overridden set method with the data. The data member variable is not
     * updated as variables in this class are modified.
     * 
     * @param tag The unique tag that references this word.
     * @param data The tag data associated with this word.
     */
    public DFWord(String tag, DFTag[] data) {
        super(tag, data);
        
        this.is_noun = this.is_verb = this.is_adj = this.is_prefix = false;
        
        this.noun   = new Noun();
        this.adj    = new Adjective();
        this.verb   = new Verb();
        this.prefix = new Prefix();
        
        this.syms = new TreeSet<String>();
        
        /**
         * This is a bad way to do things, but oh well.
         */
        try {
            set(data);
        }catch(SyntaxException se) {
            System.err.println("Exception in DFWord constructor: Syntax Error");
            System.err.println("Could not parse tag data successfully.");
        }
    }
    
    /**
     * Returns the unique tag associated with this DFWord.
     * @return the unique tag associated with this DFWord.
     */
    @Override
    public String objectTag() {
        return DFTag.WORD;
    }
    
    /**
     * Parses the given data and sets the member variables to represent the
     * word as defined in the raws.
     * This method delegates the responsibility for parsing each word type to
     * the nested classes Noun, Verb, Adjective, and Prefix. The only parsing
     * this method does is to determine which subclass should be used for each
     * grouping of tags.
     * @param data The tag data for this word.
     * @throws SyntaxException 
     */
    @Override
    public void set(DFTag[] data) throws SyntaxException {
        int i, len;
        DFTag tp;
        
        // Set this.data to our new data, then check if we can do something
        // with it. If data is null, we don't want to throw a null pointer
        // exception, so we return without doing anything.
        this.data = data;
        if(data == null) {
            return;
        }
        
        // Loop variable initialization.
        i = 0;
        len = data.length;
        
        /* Loop over the data until we reach the end. i is incremented by the
         * value returned by each of the parsing sub-methods.
         */
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
    
    /**
     * Add a new symbol string to the set of symbols this word is associated
     * with.
     * @param sym The symbols unique tag.
     */
    public void add(String sym) {
        syms.add(sym);
    }
    /**
     * Remove a symbol from the set of symbols this word is associated with.
     * @param sym The symbols unique tag.
     */
    public void remove(String sym) {
        syms.remove(sym);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Word Subclasses">
    /**
     * @class Noun
     * This class represents the data contained in a noun. This is basically
     * a glorified struct, with a few utility methods for parsing and output.
     * All member variables are public, allowing easy access.
     */
    public class Noun {
        /**
         * singular
         * The string for the singular representation of the word, in English.
         */
        /**
         * plural
         * The string for the plural representation of the word, in English
         */
        public String singular, plural;
        /**
         * the_singular
         * Boolean indicating if this word can be used by the name generator
         * as part of the "The" construction in a name.
         */
        /**
         * the_plural
         * Boolean indicating if this word can be used by the name generator as
         * part of the "The" construction in a name.
         */
        public boolean the_singular, the_plural;
        /**
         * the_compound_singular
         * Boolean indicating if this word can be used by the name generator as
         * part of a compound "The" construction.
         */
        /**
         * the_compound_plural
         * Boolean indicating if this word can be used by the name generator as
         * part of a compound "The" construction.
         */
        public boolean the_compound_singular, the_compound_plural;
        /**
         * of_singular
         * Boolean indicating if this word can be used by the name generator as
         * part of the "Of" construction.
         */
        /**
         * of_plural
         * Boolean indicating if this word can be used by the name generator as
         * part of the "Of" construction.
         */
        public boolean of_singular, of_plural;
        /**
         * front_compound_singular
         * Boolean indicating if this word can be used by the name generator as
         * the front part of a compound name.
         */
        /**
         * front_compound_plural
         * Boolean indicating if this word can be used by the name generator as
         * the front part of a compound name.
         */
        public boolean front_compound_singular, front_compound_plural;
        /**
         * rear_compound_singular
         * Boolean indicating if this word can be used by the name generator as
         * the rear part of a compound name.
         */
        /**
         * rear_compound_plural
         * Boolean indicating if this word can be used by the name generator as
         * the rear part of a compound name.
         */
        public boolean rear_compound_singular, rear_compound_plural;
        
        /**
         * Creates a new Noun class with the empty string as both its singular
         * and plural English forms.
         */
        private Noun() {
            this("", "");
        }
        /**
         * Creates a new Noun class with the given strings as its singular and
         * plural English forms.
         * @param singular The singular English form for this word.
         * @param plural The plural English form for this word.
         */
        private Noun(String singular, String plural) {
            this.singular = singular;
            this.plural = plural;
            
            the_singular            = the_plural            = false;
            the_compound_singular   = the_compound_plural   = false;
            of_singular             = of_plural             = false;
            front_compound_singular = front_compound_plural = false;
            rear_compound_singular  = rear_compound_plural  = false;
            
        }
        
        /**
         * Parses the given tags starting at the given offset, setting member
         * variables as dictated by the raw representation for this Noun.
         * @param data The tags to parse.
         * @param offset The offset at which to start parsing the tags.
         * @return The number of tags parsed by this method.
         */
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
        
        /**
         * Returns the string representation of the tags defining this Noun.
         * @return the string representation of the tags defining this Noun.
         */
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
    /**
     * @class Adjective
     * This class represents an Adjective part of a DFWord. This class is
     * basically a glorified struct with a few utility methods for input and
     * output of this class as tag data.
     */
    public class Adjective {
        /**
         * adj
         * The English word this adjective represents.
         */
        public String adj;
        /**
         * adjective_distance
         * The priority the adjective is given in placement when multiple
         * adjectives are placed one after the other. Lower values come first.
         */
        public int adjective_distance;
        /**
         * the_adj
         * Boolean indicating if this adjective can be used by the name
         * generator as part of a "The" construction in a name.
         */
        /**
         * front_adj
         * Boolean indicating if this adjective can be used by the name
         * generator as the front part of a compound name.
         */
        /**
         * rear_adj
         * Boolean indicating if this adjective can be used by the name
         * generator as the rear part of a compound name.
         */
        public boolean the_adj, front_adj, rear_adj;
        
        /**
         * Create a new Adjective with the empty string as its English
         * representation.
         */
        private Adjective() {
            this("");
        }
        /**
         * Creates a new Adjective with the given string as its English
         * representation.
         * The adjective distance is initially set to -1, and all booleans are
         * set to 
         * @param adj The English representation of this adjective.
         */
        private Adjective(String adj) {
            this.adj = adj;
            
            this.adjective_distance = -1;
            
            this.the_adj   = false;
            this.front_adj = false;
            this.rear_adj  = false;
        }
        
        /**
         * Parses the given data to set member variables of this class to their
         * appropriate given value.
         * @param data The tags to parse.
         * @param offset The offset to start parsing at.
         * @return The number of tags parsed by this method.
         */
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
        
        /**
         * Returns the string representation of the tags for this Adjective
         * @return the string representation of the tags for this Adjective
         */
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
