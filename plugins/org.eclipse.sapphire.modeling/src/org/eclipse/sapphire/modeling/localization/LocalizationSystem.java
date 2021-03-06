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

package org.eclipse.sapphire.modeling.localization;

import java.util.Locale;

/**
 * @author <a href="mailto:konstantin@komissarchik.net">Konstantin Komissarchik</a>
 */

public final class LocalizationSystem
{
    public static LocalizationService service( final Class<?> cl )
    {
        final Locale locale = Locale.getDefault();
        
        if( locale.getLanguage().equals( Locale.ENGLISH.getLanguage() ) )
        {
            return SourceLanguageLocalizationService.INSTANCE;
        }
        else
        {
            return new ClassLocalizationService( cl, locale );
        }
    }

}
