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

package org.eclipse.sapphire.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.sapphire.modeling.ModelPath;

/**
 * Produces the set of model paths that point to parts of the model that the property depends on. Most frequently
 * specified via @DependsOn annotation.
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public abstract class DependenciesService extends Service
{
    private Set<ModelPath> dependencies;
    
    @Override
    protected final void init()
    {
        initDependenciesService();
        
        final Set<ModelPath> dependencies = new HashSet<ModelPath>();
        compute( dependencies );
        this.dependencies = Collections.unmodifiableSet( dependencies );
    }

    protected void initDependenciesService()
    {
    }
    
    public final Set<ModelPath> dependencies()
    {
        return this.dependencies;
    }
    
    protected abstract void compute( Set<ModelPath> dependencies );
    
}
