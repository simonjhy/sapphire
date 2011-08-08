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

package org.eclipse.sapphire.samples.contacts.internal;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ImageData;
import org.eclipse.sapphire.modeling.ModelPropertyChangeEvent;
import org.eclipse.sapphire.modeling.ModelPropertyListener;
import org.eclipse.sapphire.samples.contacts.IContact;
import org.eclipse.sapphire.services.ImageService;
import org.eclipse.sapphire.services.Service;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class ContactImageService extends ImageService
{
    private static final ImageData IMG_PERSON = ImageData.readFromClassLoader( IContact.class, "Contact.png" );
    private static final ImageData IMG_PERSON_FADED = ImageData.readFromClassLoader( IContact.class, "ContactFaded.png" );
    
    private ModelPropertyListener listener;
    
    @Override
    protected void init()
    {
        super.init();
        
        this.listener = new ModelPropertyListener()
        {
            @Override
            public void handlePropertyChangedEvent( final ModelPropertyChangeEvent event )
            {
                ContactImageService.this.notify( new Service.Event( ContactImageService.this ) );
            }
        };
        
        context( IModelElement.class ).addListener( this.listener, IContact.PROP_E_MAIL.getName() );
    }

    @Override
    public ImageData provide()
    {
        if( context( IContact.class ).getEMail().getContent() == null )
        {
            return IMG_PERSON_FADED;
        }
        else
        {
            return IMG_PERSON;
        }
    }

    @Override
    public void dispose()
    {
        super.dispose();
        
        context( IModelElement.class ).removeListener( this.listener, IContact.PROP_E_MAIL.getName() );
    }
    
}
