/******************************************************************************
 * Copyright (c) 2016 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.services.internal;

import java.util.SortedSet;

import org.eclipse.sapphire.InitialValueService;
import org.eclipse.sapphire.LocalizableText;
import org.eclipse.sapphire.Property;
import org.eclipse.sapphire.Text;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.internal.ValueSnapshot;
import org.eclipse.sapphire.services.FactsService;
import org.eclipse.sapphire.services.ServiceCondition;
import org.eclipse.sapphire.services.ServiceContext;

/**
 * Creates fact statements about property's initial value by using semantical information specified 
 * by InitialValueService and @InitialValue annotation.
 * 
 * @author <a href="mailto:konstantin@komissarchik.net">Konstantin Komissarchik</a>
 */

public final class InitialValueFactsService extends FactsService
{
    @Text( "Initial value is {0}" )
    private static LocalizableText statement;
    
    static
    {
        LocalizableText.init( InitialValueFactsService.class );
    }

    @Override
    protected void facts( final SortedSet<String> facts )
    {
        final Value<?> value = context( Value.class );
        final InitialValueService initialValueService = value.service( InitialValueService.class );
        final String text = initialValueService.value();
        
        if( text != null && text.trim().length() > 0 )
        {
            facts.add( statement.format( new ValueSnapshot( value.definition(), text ) ) );
        }
    }
    
    public static final class Condition extends ServiceCondition
    {
        @Override
        public boolean applicable( final ServiceContext context )
        {
            final Property property = context.find( Property.class );
            return ( property != null && property.service( InitialValueService.class ) != null );
        }
    }
    
}
