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

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @class DFSymbol
 * @author tvarney
 * 
 * This class represents a Dwarf Fortress Symbol definition. The links to the
 * words given to this class are stored as strings and are not checked for
 * consistency with the list of words given by language_words.txt
 * Thus, it is up to the classes that use DFSymbol to check the validity of each
 * word before adding it to an instance.
 * The internal representation of the data for this class is done using a
 * sorted set of strings, ensuring that symbols can not have redundant entries,
 * as well as ensuring decent insertion and removal time.
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
