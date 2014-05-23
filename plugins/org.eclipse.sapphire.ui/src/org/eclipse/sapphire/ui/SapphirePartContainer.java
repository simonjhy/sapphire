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

package org.eclipse.sapphire.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelPath;
import org.eclipse.sapphire.modeling.Status;
import org.eclipse.sapphire.ui.def.IFormDef;
import org.eclipse.sapphire.ui.def.ISapphirePartDef;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class SapphirePartContainer

    extends SapphirePart
    
{
    private List<SapphirePart> childParts;
    private List<SapphirePart> childPartsReadOnly;
    
    @Override
    protected void init()
    {
        super.init();

        this.childParts = new ArrayList<SapphirePart>();
        this.childPartsReadOnly = Collections.unmodifiableList( this.childParts );
        
        final IModelElement modelElementForChildParts = getModelElementForChildParts();
        
        if( modelElementForChildParts != null )
        {
            final SapphirePartListener childPartListener = new SapphirePartListener()
            {
                @Override
                public void handleValidateStateChange( final Status oldValidateState,
                                                       final Status newValidationState )
                {
                    updateValidationState();
                }
            };
    
            final IFormDef def = getFormDefinition();
            
            for( ISapphirePartDef childPartDef : def.getContent() )
            {
                final SapphirePart childPart = create( this, modelElementForChildParts, childPartDef, this.params );
                this.childParts.add( childPart );
                childPart.addListener( childPartListener );
            }
        }
        
        updateValidationState();
    }
    
    public IFormDef getFormDefinition()
    {
        return (IFormDef) this.definition;
    }
    
    protected IModelElement getModelElementForChildParts()
    {
        return getModelElement();
    }
    
    public List<SapphirePart> getChildParts()
    {
        return this.childPartsReadOnly;
    }
    
    public void render( final SapphireRenderingContext context )
    {
        for( SapphirePart child : this.childParts )
        {
            child.render( context );
        }
    }
    
    @Override
    
    protected Status computeValidationState()
    {
        final Status.CompositeStatusFactory factory = Status.factoryForComposite();

        for( SapphirePart child : this.childParts )
        {
            factory.add( child.getValidationState() );
        }
        
        return factory.create();
    }
    
    @Override
    
    public boolean setFocus()
    {
        for( SapphirePart child : getChildParts() )
        {
            if( child.setFocus() == true )
            {
                return true;
            }
        }
        
        return false;
    }

    @Override
    
    public boolean setFocus( final ModelPath path )
    {
        for( SapphirePart child : getChildParts() )
        {
            if( child.setFocus( path ) == true )
            {
                return true;
            }
        }
        
        return false;
    }

    @Override
    
    public void dispose()
    {
        super.dispose();
        
        for( SapphirePart child : this.childParts )
        {
            child.dispose();
        }
    }

}