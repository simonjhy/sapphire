/******************************************************************************
 * Copyright (c) 2012 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.services.internal;

import org.eclipse.sapphire.Event;
import org.eclipse.sapphire.Listener;
import org.eclipse.sapphire.Version;
import org.eclipse.sapphire.VersionConstraint;
import org.eclipse.sapphire.annotations.Since;
import org.eclipse.sapphire.annotations.VersionCompatibility;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.LoggingService;
import org.eclipse.sapphire.modeling.ModelProperty;
import org.eclipse.sapphire.modeling.el.ConcatFunction;
import org.eclipse.sapphire.modeling.el.FailSafeFunction;
import org.eclipse.sapphire.modeling.el.Function;
import org.eclipse.sapphire.modeling.el.FunctionResult;
import org.eclipse.sapphire.modeling.el.Literal;
import org.eclipse.sapphire.modeling.el.ModelElementFunctionContext;
import org.eclipse.sapphire.modeling.el.parser.ExpressionLanguageParser;
import org.eclipse.sapphire.modeling.util.MiscUtil;
import org.eclipse.sapphire.services.Service;
import org.eclipse.sapphire.services.ServiceContext;
import org.eclipse.sapphire.services.ServiceFactory;
import org.eclipse.sapphire.services.VersionCompatibilityService;
import org.eclipse.sapphire.services.VersionCompatibilityTargetService;

/**
 * Implementation of VersionCompatibilityService that derives its behavior from @VersionCompatibility and @Since annotations.
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class DeclarativeVersionCompatibilityService extends VersionCompatibilityService
{
    private VersionCompatibilityTargetService versionCompatibilityTargetService;
    private Listener versionCompatibilityTargetServiceListener;
    private FunctionResult functionResult;
    
    @Override
    protected void initVersionCompatibilityService()
    {
        final IModelElement element = context( IModelElement.class );
        final ModelProperty property = context( ModelProperty.class );
        
        this.versionCompatibilityTargetService = VersionCompatibilityTargetService.find( element, property );
        
        this.versionCompatibilityTargetServiceListener = new Listener()
        {
            @Override
            public void handle( final Event event )
            {
                refresh();
            }
        };
        
        this.versionCompatibilityTargetService.attach( this.versionCompatibilityTargetServiceListener );
        
        Function function = null;
        
        final VersionCompatibility versionCompatibilityAnnotation = property.getAnnotation( VersionCompatibility.class );

        if( versionCompatibilityAnnotation != null )
        {
            try
            {
                function = ExpressionLanguageParser.parse( versionCompatibilityAnnotation.value() );
            }
            catch( Exception e )
            {
                LoggingService.log( e );
            }
        }
        else
        {
            final Since sinceAnnotation = property.getAnnotation( Since.class );
            
            try
            {
                function = ExpressionLanguageParser.parse( sinceAnnotation.value() );
            }
            catch( Exception e )
            {
                LoggingService.log( e );
            }
            
            if( function != null )
            {
                function = ConcatFunction.create( "[", function );
            }
        }
        
        if( function == null )
        {
            function = Literal.NULL;
        }
        else
        {
            function = FailSafeFunction.create( function, Literal.create( VersionConstraint.class ), null );
            
            this.functionResult = function.evaluate( new ModelElementFunctionContext( element ) );
            
            this.functionResult.attach
            (
                new Listener()
                {
                    @Override
                    public void handle( final Event event )
                    {
                        refresh();
                    }
                }
            );
        }
    }
    
    @Override
    protected Data compute()
    {
        final Version version = this.versionCompatibilityTargetService.version();
        final String versioned = this.versionCompatibilityTargetService.versioned();
        
        boolean compatible = false;
        
        final VersionConstraint constraint = (VersionConstraint) this.functionResult.value();
        
        if( constraint != null && version != null )
        {
            compatible = constraint.check( version );
        }
        
        return new Data( versioned, version, constraint, compatible );
    }
    
    public VersionConstraint constraint()
    {
        final Data data = (Data) data();
        return ( data == null ? null : data.constraint() );
    }

    @Override
    public void dispose()
    {
        super.dispose();
        
        if( this.versionCompatibilityTargetService != null )
        {
            this.versionCompatibilityTargetService.detach( this.versionCompatibilityTargetServiceListener );
        }
        
        if( this.functionResult != null )
        {
            try
            {
                this.functionResult.dispose();
            }
            catch( Exception e )
            {
                LoggingService.log( e );
            }
        }
    }
    
    public static final class Data extends VersionCompatibilityService.Data
    {
        private final VersionConstraint constraint;
        
        public Data( final String versioned,
                     final Version version,
                     final VersionConstraint constraint,
                     final boolean compatible )
        {
           super( compatible, version, versioned );
           
           this.constraint = constraint;
        }
        
        public VersionConstraint constraint()
        {
            return this.constraint;
        }
        
        @Override
        public boolean equals( final Object obj )
        {
            if( obj instanceof Data )
            {
                final Data data = (Data) obj;
                return super.equals( obj ) && MiscUtil.equal( this.constraint, data.constraint );
            }
            
            return false;
        }
        
        @Override
        public int hashCode()
        {
            return super.hashCode() + ( this.constraint == null ? 0 : this.constraint.hashCode() );
        }
    }
    
    public static final class Factory extends ServiceFactory
    {
        @Override
        public boolean applicable( final ServiceContext context,
                                   final Class<? extends Service> service )
        {
            final IModelElement element = context.find( IModelElement.class );
            final ModelProperty property = context.find( ModelProperty.class );
            
            return ( property.hasAnnotation( VersionCompatibility.class ) || property.hasAnnotation( Since.class ) ) &&
                   ( VersionCompatibilityTargetService.find( element, property ) != null );
        }

        @Override
        public Service create( final ServiceContext context,
                               final Class<? extends Service> service )
        {
            return new DeclarativeVersionCompatibilityService();
        }
    }
    
}
