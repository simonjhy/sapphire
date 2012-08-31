/******************************************************************************
 * Copyright (c) 2012 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation
 ******************************************************************************/

package org.eclipse.sapphire.sdk;

import org.eclipse.sapphire.PreferDefaultValue;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Service;
import org.eclipse.sapphire.modeling.annotations.Services;
import org.eclipse.sapphire.sdk.extensibility.SapphireExtensionDef;
import org.eclipse.sapphire.sdk.internal.CreateExtensionManifestOpServices.FolderInitialValueService;
import org.eclipse.sapphire.sdk.internal.CreateExtensionManifestOpServices.FolderValidationService;
import org.eclipse.sapphire.workspace.CreateWorkspaceFileOp;
import org.eclipse.sapphire.workspace.WorkspaceFileType;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@WorkspaceFileType( SapphireExtensionDef.class )
@GenerateImpl

public interface CreateExtensionManifestOp extends CreateWorkspaceFileOp
{
    ModelElementType TYPE = new ModelElementType( CreateExtensionManifestOp.class );
    
    // *** Folder ***
    
    @Services
    (
        {
            @Service( impl = FolderValidationService.class ),
            @Service( impl = FolderInitialValueService.class )
        }
    )
    
    ValueProperty PROP_FOLDER = new ValueProperty( TYPE, CreateWorkspaceFileOp.PROP_FOLDER );
    
    // *** FileName ***
    
    @DefaultValue( text = SapphireExtensionDef.FILE_NAME )
    @PreferDefaultValue

    ValueProperty PROP_FILE_NAME = new ValueProperty( TYPE, CreateWorkspaceFileOp.PROP_FILE_NAME );
    
}
