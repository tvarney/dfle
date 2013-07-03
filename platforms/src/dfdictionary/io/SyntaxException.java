package dfdictionary.io;

import java.io.IOException;

/**
 *
 * @author tvarney
 */
public class SyntaxException extends IOException {
    public long line;
    
    public SyntaxException() {
        super();
        this.line = -1;
    }
    public SyntaxException(String msg) {
        this(msg, -1);
    }
    public SyntaxException(String msg, long line) {
        super(msg);
        this.line = line;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("SyntaxException");
        if(line >= 0) {
            builder.append(" on line ");
            builder.append(((Long)line).toString());
        }
        builder.append('\n');
        
        builder.append(getMessage());
        
        return builder.toString();
    }
}
