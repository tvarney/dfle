package dfdictionary.object;

import dfdictionary.io.SyntaxException;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author tvarney
 */
public class DFSymbol extends DFObject {
    protected SortedSet<String> words;
    
    public DFSymbol() {
        super();
        this.words = new TreeSet<String>();
    }
    public DFSymbol(String tag) {
        super(tag);
        this.words = new TreeSet<String>();
    }
    public DFSymbol(String tag, DFTag[] data) {
        super(tag, data);
    }
    
    @Override
    public String objectTag() {
        return DFTag.SYMBOL;
    }
    
    @Override
    public void set(DFTag[] data) throws SyntaxException {
        int errors = 0;
        for(int i = 0; i < data.length; ++i) {
            if(!data[i].tag.equals(DFTag.SYM_WORD)) {
                /*TODO: add logging for errors here */
                errors++; //< Increment error count
                continue; //< Not a S_WORD entry, skip
            }
            if(data[i].data.length < 1) {
                /*TODO: log that S_WORD tag has no arguments */
                errors++;
                continue;
            }
            
            this.words.add(data[i].data[0]);
            
            if(data[i].data.length > 1) {
                /*TODO: log that S_WORD tag has multiple arguments */
                errors++;
            }
        }
        if(errors > 0) {
            String msg = String.format("%d Invalid tags in Symbol data",
                                       errors);
            throw new SyntaxException(msg);
        }
    }
    
    public void add(String word) {
        this.words.add(word);
    }
    
    public void remove(String word) {
        this.words.remove(word);
    }
    
    public String[] wordArray() {
        String[] warr = {};
        if(words != null && words.size() > 0) {
            warr = words.toArray(warr);
        }
        
        return warr;
    }
    
    @Override
    public String buildTagString() {
        StringBuilder builder = new StringBuilder(1024);
        
        builder.append(DFTag.format(DFTag.SYMBOL, tag));
        builder.append('\n');
        
        for(String word : words) {
            builder.append('\t');
            builder.append(DFTag.format(DFTag.SYM_WORD, word));
            builder.append('\n');
        }
        
        return builder.toString();
    }
}
