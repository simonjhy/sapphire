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

package org.eclipse.sapphire.services.internal;

import java.lang.annotation.Annotation;

import org.eclipse.sapphire.modeling.ModelElementType;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public abstract class ElementServiceContext extends AnnotationsAwareServiceContext
{
    private final ModelElementType elementMetaModel;
    
    public ElementServiceContext( final String contextType,
                                  final ModelElementType elementMetaModel )
    {
        super( contextType );
        
        this.elementMetaModel = elementMetaModel;
    }
    
    @Override
    @SuppressWarnings( "unchecked" )
    
    public <T> T find( final Class<T> type )
    {
        T obj = super.find( type );
        
        if( obj == null )
        {
            if( type == ModelElementType.class )
            {
                obj = (T) this.elementMetaModel;
            }
        }
        
        return obj;
    }
    
    @Override
    
    protected <A extends Annotation> A annotation( final Class<A> type )
    {
        return this.elementMetaModel.getAnnotation( type );
    }
    
}
