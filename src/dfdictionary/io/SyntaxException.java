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

import java.io.IOException;

/**
 * @class SyntaxException
 * @author tvarney
 * 
 * Exception representing a Syntax error on input.
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
