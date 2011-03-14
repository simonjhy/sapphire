/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ling Hao - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.modeling.util.internal;

import org.eclipse.sapphire.modeling.docsys.BoldPart;
import org.eclipse.sapphire.modeling.docsys.CodePart;
import org.eclipse.sapphire.modeling.docsys.DocumentationContent;
import org.eclipse.sapphire.modeling.docsys.DocumentationPart;
import org.eclipse.sapphire.modeling.docsys.LineBreakPart;
import org.eclipse.sapphire.modeling.docsys.ListItem;
import org.eclipse.sapphire.modeling.docsys.ListPart;
import org.eclipse.sapphire.modeling.docsys.OrderedListPart;
import org.eclipse.sapphire.modeling.docsys.ParagraphBreakPart;
import org.eclipse.sapphire.modeling.docsys.TextPart;
import org.eclipse.sapphire.modeling.docsys.UnorderedListPart;

/**
 * @author <a href="mailto:ling.hao@oracle.com">Ling Hao</a>
 */

public class DocumentationUtil {

    public final static String NEW_LINE = "\r\n"; //$NON-NLS-1$
    public final static String NEW_LINE_2 = "\r\n\r\n"; //$NON-NLS-1$
    
    public final static String collapseString(final String str) {
        String text = str.trim();
        
        final StringBuilder buf = new StringBuilder();
        boolean skipNextWhitespace = true;
        
        for( int i = 0, n = text.length(); i < n; i++ )
        {
            final char ch = text.charAt( i );
            
            if( Character.isWhitespace( ch ) )
            {
                if( ! skipNextWhitespace )
                {
                    buf.append( ' ' );
                    skipNextWhitespace = true;
                }
            }
            else
            {
                buf.append( ch );
                skipNextWhitespace = false;
            }
        }
        
        final int length = buf.length();
        
        if( length > 0 && buf.charAt( length - 1 ) == ' ' )
        {
            buf.deleteCharAt( length - 1 );
        }
        
        return buf.toString();
    }
    
    public final static String decodeDocumentationTags(final String str) {
        if (str == null)
            return str;
        
        DocumentationContent content = DocumentationContent.parse(str);

        final StringBuilder buf = new StringBuilder();
        format( buf, content, -1, true );
        return buf.toString();

    }
    
    private static boolean format(final StringBuilder buf, final DocumentationPart part, int index, boolean collapseSpaces) {
        if (part instanceof TextPart) {
        	final String str = ((TextPart) part).getText();
            buf.append(collapseSpaces ? collapseString(str) : str);
        } else if (part instanceof LineBreakPart) {
            buf.append(NEW_LINE);
        } else if (part instanceof ParagraphBreakPart) {
            buf.append(NEW_LINE_2);
        } else if (part instanceof OrderedListPart) {
            buf.append(NEW_LINE_2);

            int childIndex = 0;
            for (ListItem item : ((ListPart) part).getItems()) {
                childIndex++;
                collapseSpaces = format(buf, item, childIndex, collapseSpaces);
            }
        } else if (part instanceof UnorderedListPart) {
            buf.append(NEW_LINE_2);

            for (ListItem item : ((ListPart) part).getItems()) {
            	collapseSpaces = format(buf, item, -1, collapseSpaces);
            }
        } else if (part instanceof ListItem) {
            buf.append(index == -1 ? "*" : index);
            buf.append("  ");

            for (DocumentationPart child : ((ListItem) part).getChildren()) {
            	collapseSpaces = format(buf, child, -1, collapseSpaces);
            }

            buf.append(NEW_LINE);
        } else if (part instanceof BoldPart) {
            BoldPart boldPart = (BoldPart)part;
            if (boldPart.isOpen()) {
                buf.append("<@#$b>");
            } else {
                buf.append("</@#$b>");
            }
        } else if (part instanceof CodePart) {
        	CodePart codePart = (CodePart)part;
            if (codePart.isOpen()) {
            	collapseSpaces = false;
            } else {
            	collapseSpaces = true;
            }
        } else if (part instanceof DocumentationContent) {
            for (DocumentationPart child : ((DocumentationContent) part).getChildren()) {
            	collapseSpaces = format(buf, child, -1, collapseSpaces);
            }
        }
        return collapseSpaces;
    }
    
}
