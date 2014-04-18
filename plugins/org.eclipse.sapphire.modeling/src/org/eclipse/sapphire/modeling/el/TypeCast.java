/******************************************************************************
 * Copyright (c) 2012 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.modeling.el;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public abstract class TypeCast
{
    public boolean applicable( final FunctionContext context,
                               final Function requestor,
                               final Object value,
                               final Class<?> target )
    {
        return true;
    }
    
    public abstract Object evaluate( FunctionContext context,
                                     Function requestor,
                                     Object value,
                                     Class<?> target ) throws FunctionException;
    
}