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

import dfdictionary.object.DFObject;
import dfdictionary.object.DFSymbol;
import dfdictionary.object.DFTag;
import dfdictionary.object.DFWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.util.LinkedList;
import java.util.List;

/**
 * @class DFObjectParser
 * @author tvarney
 * 
 * Creates DFObjects from input obtained by from a file or a reader given to it.
 * Utilizes the DFTag class to obtain arrays of tags from each line of input,
 * then groups them according to each object in the text.
 */
public class DFObjectParser {
    protected BufferedReader reader;
    protected String obj_type;
    protected DFTag[] tags;
    protected int offset;
    protected long line_num;
    
    /**
     * Create a new DFObjectParser with the given file name.
     * @param fname The file name to open to parse.
     * @throws FileNotFoundException
     */
    public DFObjectParser(String fname)
        throws FileNotFoundException {
        this(new File(fname));
    }
    /**
     * Create a new DFObjectParser with the give File.
     * @param file The file to parse.
     * @throws FileNotFoundException 
     */
    public DFObjectParser(File file) 
        throws FileNotFoundException {
        this(new FileReader(file));
    }
    /**
     * Create a new DFObjectParser with the given Reader. The given reader will
     * be wrapped in a BufferedReader.
     * @param reader The reader to use.
     */
    public DFObjectParser(Reader reader) {
        this(new BufferedReader(reader));
    }
    /**
     * Create a new DFObjectParser with the given BufferedReader.
     * @param reader the BufferedReader to use.
     */
    public DFObjectParser(BufferedReader reader) {
        this.reader = reader;
        offset = 0;
        line_num = 0;
        obj_type = null;
        tags = null;
    }
    
    protected void getObjType() throws IOException {
        String line;
        while(tags == null && (line = reader.readLine()) != null) {
            tags = DFTag.getTags(line);
            line_num++;
        }
        offset = 0;
        if(!tags[offset].tag.equals(DFTag.OBJ)) {
            /* Syntax error */
        }
        
        if(tags[offset].data == null) {
            throw new SyntaxException("No Object data", line_num);
        }
        /*TODO: Log extra data error on object tag */
        obj_type = tags[offset].data[0];
        //System.out.printf("Object Type: %s\n", obj_type);
    }
    
    /**
     * Parses and returns the next DFObject in the file.
     * @return The next DFObject in the file, or null if none exist.
     * @throws IOException 
     */
    public DFObject next()
        throws IOException {
        DFObject obj;
        List<DFTag> data;
        DFTag nt;
        
        if(obj_type == null) {
            getObjType();
            if(!obj_type.equals(DFTag.LANG_OBJ)) {
                //System.err.printf("Error parsing object type\n");
                return null;
            }
        }
        
        obj = null;
        
        data = new LinkedList<DFTag>();
        while(obj == null && (nt = nextTag()) != null) {
            if(nt.tag.equals(DFTag.WORD)) {
                obj = new DFWord(nt.data[0]);
            }else if(nt.tag.equals(DFTag.SYMBOL)) {
                obj = new DFSymbol(nt.data[0]);
            }else {
                /* Syntax Error */
            }
        }
        if(obj == null) return null;
        
        while((nt = nextTag()) != null) {
            if(nt.tag.equals(DFTag.WORD) || nt.tag.equals(DFTag.SYMBOL)) {
                offset--; //< Go back so we get the same tag next time
                break;
            }
            data.add(nt);
        }
        
        DFTag[] blah = new DFTag[data.size()];
        if(data == null) {
            System.err.printf("data is null\n");
            System.err.flush();
        }else if(blah == null) {
            System.err.printf("blah is null\n");
            System.err.flush();
        }
        if(obj != null) {
            obj.set(data.toArray(blah));
        }else {
            System.err.printf("Object is null?!?\n");
        }
        
        return obj;
    }
    
    protected DFTag nextTag()
        throws IOException {
        String line;
        if(tags == null || offset >= tags.length) {
            tags = null;
            offset = 0;
            do {
                line = reader.readLine();
                if(line == null) {
                    return null;
                }
                tags = DFTag.getTags(line);
            } while(tags == null);
        }
        
        offset++;
        return tags[offset - 1];
    }
    
    /**
     * Closes the underlying reader object.
     */
    public void close() {
        try {
            reader.close();
            obj_type = null;
            tags = null;
            offset = 0;
        }catch(Exception e) {
            
        }
    }
}
