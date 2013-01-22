/******************************************************************************
 * Copyright (c) 2013 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.samples.map.internal;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sapphire.ui.ISapphirePart;
import org.eclipse.sapphire.ui.SapphireActionHandler;
import org.eclipse.sapphire.ui.SapphireRenderingContext;
import org.eclipse.sapphire.ui.diagram.editor.DiagramConnectionPart;
import org.eclipse.sapphire.ui.diagram.editor.DiagramNodePart;
import org.eclipse.sapphire.ui.diagram.editor.SapphireDiagramEditorPagePart;

/**
 * Action handler for Sample.Map.ShowStatistics action, which illustrates how
 * to create actions that operate in the context where multiple diagram parts are
 * selected.
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class ShowStatisticsActionHandler extends SapphireActionHandler
{
    @Override
    protected Object run( final SapphireRenderingContext context ) 
    {
        final SapphireDiagramEditorPagePart page = (SapphireDiagramEditorPagePart) getPart();
        
        int nodes = 0;
        int connections = 0;
        int bendpoints = 0;
        
        for( ISapphirePart selectedPart : page.getSelections() )
        {
            if( selectedPart instanceof DiagramNodePart )
            {
                nodes++;
            }
            else if( selectedPart instanceof DiagramConnectionPart )
            {
                connections++;
                bendpoints += ( (DiagramConnectionPart) selectedPart ).getConnectionBendpoints().size();
            }
            else if( selectedPart instanceof SapphireDiagramEditorPagePart )
            {
                nodes = page.getNodes().size();
                
                final List<DiagramConnectionPart> allConnections = page.getConnections();
                
                connections = allConnections.size();
                
                for( DiagramConnectionPart connection : allConnections )
                {
                    bendpoints += connection.getConnectionBendpoints().size();
                }
            }
        }
        
        final StringBuilder msg = new StringBuilder();
        
        msg.append( "Nodes: " ).append( nodes ).append( '\n' );
        msg.append( "Connections: " ).append( connections ).append( '\n' );
        msg.append( "Bend Points: " ).append( bendpoints );
        
        MessageDialog.openInformation( context.getShell(), "Statistics", msg.toString() );
        
        return null;
    }

}
