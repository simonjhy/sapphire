/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ling Hao - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.tests.modeling.xml.xsd.t0003;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ImpliedElementProperty;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlRootBinding;

/**
 * @author <a href="mailto:ling.hao@oracle.com">Ling Hao</a>
 */

@GenerateImpl

@XmlRootBinding( namespace = "http://www.eclipse.org/sapphire/tests/xml/xsd/0003",
                 elementName = "element" )

public interface ITestXmlXsd0003Element

    extends IModelElement
    
{
    ModelElementType TYPE = new ModelElementType( ITestXmlXsd0003Element.class );
    
    // *** circle ***
    
    @Type( base = ITestXmlXsd0003Circle.class )
    @XmlBinding( path = "circle" )
    
    ImpliedElementProperty PROP_CIRCLE = new ImpliedElementProperty( TYPE, "Circle" );
    
    ITestXmlXsd0003Circle getCircle();
    
    // *** square ***
    
    @Type( base = ITestXmlXsd0003Square.class )
    @XmlBinding( path = "square" )
    
    ImpliedElementProperty PROP_SQUARE = new ImpliedElementProperty( TYPE, "Square" );
    
    ITestXmlXsd0003Square getSquare();
    
    // *** Aaa ***
    
    @XmlBinding( path = "aaa" )
    
    ValueProperty PROP_AAA = new ValueProperty( TYPE, "Aaa" );
    
    Value<String> getAaa();
    void setAaa( String value );
    
    // *** Bbb ***
    
    @XmlBinding( path = "bbb" )
    
    ValueProperty PROP_BBB = new ValueProperty( TYPE, "Bbb" );
    
    Value<String> getBbb();
    void setBbb( String value );
    
    // *** CCC ***
    
    @XmlBinding( path = "ccc" )
    
    ValueProperty PROP_CCC = new ValueProperty( TYPE, "Ccc" );
    
    Value<String> getCcc();
    void setCcc( String value );
    
}