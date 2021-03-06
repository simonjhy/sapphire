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

package org.eclipse.sapphire.tests.modeling.el.functions.content;

import org.eclipse.sapphire.Element;
import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.Transient;
import org.eclipse.sapphire.TransientProperty;
import org.eclipse.sapphire.Type;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;

/**
 * @author <a href="mailto:konstantin@komissarchik.net">Konstantin Komissarchik</a>
 */

public interface TestElement extends Element
{
    ElementType TYPE = new ElementType( TestElement.class );
    
    // *** IntegerValue ***
    
    @Type( base = Integer.class )

    ValueProperty PROP_INTEGER_VALUE = new ValueProperty( TYPE, "IntegerValue" );
    
    Value<Integer> getIntegerValue();
    void setIntegerValue( String value );
    void setIntegerValue( Integer value );
    
    // *** IntegerValueWithDefault ***
    
    @Type( base = Integer.class )
    @DefaultValue( text = "1" )

    ValueProperty PROP_INTEGER_VALUE_WITH_DEFAULT = new ValueProperty( TYPE, "IntegerValueWithDefault" );
    
    Value<Integer> getIntegerValueWithDefault();
    void setIntegerValueWithDefault( String value );
    void setIntegerValueWithDefault( Integer value );
    
    // *** Transient ***
    
    @Type( base = Object.class )

    TransientProperty PROP_TRANSIENT = new TransientProperty( TYPE, "Transient" );
    
    Transient<Object> getTransient();
    void setTransient( Object value );

}
