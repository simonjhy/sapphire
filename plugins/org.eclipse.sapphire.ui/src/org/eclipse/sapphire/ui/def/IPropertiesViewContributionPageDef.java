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

package org.eclipse.sapphire.ui.def;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.ReferenceValue;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Image;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Reference;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.el.Function;
import org.eclipse.sapphire.modeling.localization.Localizable;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@Label( standard = "properties view contribution page" )
@Image( small = "org.eclipse.sapphire.ui/images/objects/part.gif" )
@GenerateImpl

public interface IPropertiesViewContributionPageDef

    extends IFormDef
    
{
    ModelElementType TYPE = new ModelElementType( IPropertiesViewContributionPageDef.class );

    // *** Label ***
    
    @Type( base = Function.class )
    @Label( standard = "label" )
    @Localizable
    @XmlBinding( path = "label" )
    
    ValueProperty PROP_LABEL = new ValueProperty( TYPE, "Label" );
    
    Value<Function> getLabel();
    void setLabel( String value );
    void setLabel( Function value );
    
    // *** ImagePath ***
    
    @Reference( target = ImageDescriptor.class )
    @Label( standard = "image path" )
    @XmlBinding( path = "image" )
    
    ValueProperty PROP_IMAGE_PATH = new ValueProperty( TYPE, "ImagePath" );
    
    ReferenceValue<String,ImageDescriptor> getImagePath();
    void setImagePath( String imagePath );
    
}
