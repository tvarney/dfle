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

import dfdictionary.io.SyntaxException;

/**
 * @class DFObject
 * @author tvarney
 * 
 * Abstract representation of a Dwarf Fortress "Object".
 * This class provides a basis for comparing two separate dwarf fortress objects
 * based on their 'unique' identifier given after the object definition. For
 * words, this would look something like:
 * [WORD:DAGGER]
 * where dagger is the unique identifier. This means that two different types of
 * objects can compare to equal, simply because they use the same identifier.
 * Since situations where the identifier is the same causes dwarf fortress to
 * produce very interesting results, this mimics the behavior of dwarf fortress.
 */
public abstract class DFObject implements Comparable {
    /**
     * tag
     * The unique tag identifying this DFObject
     */
    public String tag;
    protected DFTag[] data;
    
    /**
     * Creates a new DFObject with the empty string as the unique identifying
     * tag, and null for data.
     */
    public DFObject() {
        /* Opt for the empty string instead of a null tag. The tag member should
         * never be null
         */
        this("", null);
    }
    /**
     * Creates a new DFObject with the given string as the unique identifying
     * tag, and null for data.
     * @param tag The unique identifying string.
     */
    public DFObject(String tag) {
        this(tag, null);
    }
    /**
     * Creates a new DFObject with the given string as the unique identifying
     * tag, and the given array of tags as data.
     * @param tag The unique identifying string.
     * @param data The tag data for this DFObject.
     */
    public DFObject(String tag, DFTag[] data) {
        this.tag = tag;
        this.data = data;
    }
    
    /**
     * 
     * @param data
     * @throws SyntaxException 
     */
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
