/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfdictionary.object;

import dfdictionary.io.SyntaxException;

/**
 *
 * @author tvarney
 */
public abstract class DFObject implements Comparable {
    public String tag;
    protected DFTag[] data;
    
    public DFObject() {
        /* Opt for the empty string instead of a null tag. The tag member should
         * never be null
         */
        this("", null);
    }
    public DFObject(String tag) {
        this(tag, null);
    }
    public DFObject(String tag, DFTag[] data) {
        this.tag = tag;
        this.data = data;
    }
    
    public abstract void set(DFTag[] data) throws SyntaxException;
    public abstract String objectTag();
    public abstract String buildTagString();
    
    @Override
    public int compareTo(Object o) {
        return tag.compareTo(((DFObject)o).tag);
    }
    
    @Override
    public String toString() {
        return this.tag;
    }
}
