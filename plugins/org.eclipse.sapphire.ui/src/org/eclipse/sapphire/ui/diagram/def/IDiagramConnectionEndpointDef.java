/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Shenxue Zhou - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.diagram.def;

import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.ui.def.ISapphirePartDef;

/**
 * @author <a href="mailto:shenxue.zhou@oracle.com">Shenxue Zhou</a>
 */

@GenerateImpl

public interface IDiagramConnectionEndpointDef 

	extends ISapphirePartDef 
	
{
	ModelElementType TYPE = new ModelElementType( IDiagramConnectionEndpointDef.class );
	
	// *** Type ***
	
	@Type( base = ConnectionEndpointType.class )
	@XmlBinding( path = "type" )
	
	ValueProperty PROP_TYPE = new ValueProperty( TYPE, "Type" );
	
	Value<ConnectionEndpointType> getType();
	void setType( String value );
	void setType( ConnectionEndpointType value );
    
	
}
