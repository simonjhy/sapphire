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

package org.eclipse.sapphire.samples.gallery;

import org.eclipse.sapphire.Element;
import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.Type;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;
import org.eclipse.sapphire.modeling.annotations.Label;

/**
 * @author <a href="mailto:konstantin@komissarchik.net">Konstantin Komissarchik</a>
 */

public interface ActuatorsGallery extends Element
{
    ElementType TYPE = new ElementType( ActuatorsGallery.class );
    
    // *** Number ***
    
    @Type( base = Integer.class )
    @Label( standard = "number" )
    @DefaultValue( text = "0" )
    
    ValueProperty PROP_NUMBER = new ValueProperty( TYPE, "Number" );
    
    Value<Integer> getNumber();
    void setNumber( String value );
    void setNumber( Integer value );
 
}
