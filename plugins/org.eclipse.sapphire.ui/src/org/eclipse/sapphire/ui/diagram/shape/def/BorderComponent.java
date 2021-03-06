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

package org.eclipse.sapphire.ui.diagram.shape.def;

import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;
import org.eclipse.sapphire.modeling.annotations.Label;

/**
 * @author <a href="mailto:konstantin@komissarchik.net">Konstantin Komissarchik</a>
 */

@Label( standard = "border component" )

public interface BorderComponent extends LinePresentation 
{
	ElementType TYPE = new ElementType( BorderComponent.class );
	
	// *** Color ***
    
    @DefaultValue( text = "${ Parent().Border.Color }" )
    
    ValueProperty PROP_COLOR = new ValueProperty( TYPE, LinePresentation.PROP_COLOR );

	// *** Weight ***
    
    @DefaultValue( text = "${ Parent().Border.Weight }" )
    
    ValueProperty PROP_WEIGHT = new ValueProperty( TYPE, LinePresentation.PROP_WEIGHT );

	// *** Style ***
    
    @DefaultValue( text = "${ Parent().Border.Style }" )
    
    ValueProperty PROP_STYLE = new ValueProperty( TYPE, LinePresentation.PROP_STYLE );
	
}
