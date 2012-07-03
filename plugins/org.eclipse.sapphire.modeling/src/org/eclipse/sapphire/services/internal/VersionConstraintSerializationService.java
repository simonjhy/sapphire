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

package org.eclipse.sapphire.services.internal;

import org.eclipse.sapphire.VersionConstraint;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.services.Service;
import org.eclipse.sapphire.services.ServiceContext;
import org.eclipse.sapphire.services.ServiceFactory;
import org.eclipse.sapphire.services.ValueSerializationService;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class VersionConstraintSerializationService extends ValueSerializationService
{
    @Override
    protected VersionConstraint decodeFromString( final String value )
    {
        VersionConstraint result = null;
        
        try
        {
            result = new VersionConstraint( value );
        }
        catch( IllegalArgumentException e )
        {
            // Intentionally ignored. It is not the job of serializer to report these
            // problems. That's handled by validators.
        }
        
        return result;
    }
    
    public static final class Factory extends ServiceFactory
    {
        @Override
        public boolean applicable( final ServiceContext context,
                                   final Class<? extends Service> service )
        {
            final ValueProperty property = context.find( ValueProperty.class ); 
            return ( property != null && property.isOfType( VersionConstraint.class ) );
        }
    
        @Override
        public Service create( final ServiceContext context,
                               final Class<? extends Service> service )
        {
            return new VersionConstraintSerializationService();
        }
    }

}
