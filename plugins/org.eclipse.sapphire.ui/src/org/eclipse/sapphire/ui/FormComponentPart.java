/******************************************************************************
 * Copyright (c) 2013 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui;

import org.eclipse.sapphire.Color;
import org.eclipse.sapphire.ui.def.FormComponentDef;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public abstract class FormComponentPart extends SapphirePart
{
    @Override
    public FormComponentDef definition()
    {
        return (FormComponentDef) super.definition();
    }
    
    public Color getBackgroundColor()
    {
        return definition().getBackgroundColor().getContent();
    }
    
    public final boolean getScaleVertically()
    {
        return definition().getScaleVertically().getContent();
    }
    
}
