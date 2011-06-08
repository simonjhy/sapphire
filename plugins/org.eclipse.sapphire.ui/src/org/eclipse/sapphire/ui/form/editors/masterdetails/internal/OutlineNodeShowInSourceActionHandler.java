/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.form.editors.masterdetails.internal;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelElementListener;
import org.eclipse.sapphire.modeling.ModelPropertyChangeEvent;
import org.eclipse.sapphire.modeling.Resource;
import org.eclipse.sapphire.modeling.xml.XmlResource;
import org.eclipse.sapphire.ui.SapphireAction;
import org.eclipse.sapphire.ui.SapphireActionHandler;
import org.eclipse.sapphire.ui.SapphireRenderingContext;
import org.eclipse.sapphire.ui.SourceEditorService;
import org.eclipse.sapphire.ui.def.ISapphireActionHandlerDef;
import org.eclipse.sapphire.ui.form.editors.masterdetails.MasterDetailsContentNode;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class OutlineNodeShowInSourceActionHandler

    extends SapphireActionHandler
    
{
    private ModelElementListener listener;
    
    @Override
    public void init( final SapphireAction action, 
                      final ISapphireActionHandlerDef def ) 
    {
        super.init( action, def );
        
        this.listener = new ModelElementListener()
        {
            @Override
            public void propertyChanged( final ModelPropertyChangeEvent event )
            {
                refreshEnablementState();
            }
        };
        
        ( (MasterDetailsContentNode) getPart() ).getLocalModelElement().addListener( this.listener );

        refreshEnablementState();
    }

    private void refreshEnablementState()
    {
        final MasterDetailsContentNode node = (MasterDetailsContentNode) getPart();
        final IModelElement element = node.getLocalModelElement();
        final Resource resource = element.resource();
        
        setEnabled( resource instanceof XmlResource && ( (XmlResource) resource ).getXmlElement() != null );
    }

    @Override
    protected Object run( final SapphireRenderingContext context )
    {
        final MasterDetailsContentNode node = (MasterDetailsContentNode) getPart();
        final IModelElement element = node.getLocalModelElement();
        
        element.adapt( SourceEditorService.class ).show( element, null );
        
        return null;
    }
    
    @Override
    public void dispose() 
    {
        super.dispose();
        
        ( (MasterDetailsContentNode) getPart() ).getLocalModelElement().removeListener( this.listener );
    }
    
}
