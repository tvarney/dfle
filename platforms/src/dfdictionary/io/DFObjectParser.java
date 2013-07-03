/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author tvarney
 */
public class DFObjectParser {
    protected BufferedReader reader;
    protected String obj_type;
    protected DFTag[] tags;
    protected int offset;
    protected long line_num;
    
    public DFObjectParser(String fname)
        throws FileNotFoundException {
        this(new File(fname));
    }
    public DFObjectParser(File f) 
        throws FileNotFoundException {
        this(new FileReader(f));
    }
    public DFObjectParser(Reader reader) {
        this(new BufferedReader(reader));
    }
    
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
