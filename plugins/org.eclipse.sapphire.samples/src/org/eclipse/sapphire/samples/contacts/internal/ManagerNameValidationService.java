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

package org.eclipse.sapphire.samples.contacts.internal;

import org.eclipse.sapphire.LocalizableText;
import org.eclipse.sapphire.Text;
import org.eclipse.sapphire.modeling.Status;

/**
 * @author <a href="mailto:konstantin@komissarchik.net">Konstantin Komissarchik</a>
 */

public final class ManagerNameValidationService extends ConnectionNameValidationService
{
    @Text( "Cannot be your own manager" )
    private static LocalizableText cannotBeYourOwnManager;
    
    static
    {
        LocalizableText.init( ManagerNameValidationService.class );
    }

    protected Status createErrorStatus()
    {
        return Status.createErrorStatus( cannotBeYourOwnManager.text() );
    }
    
}
