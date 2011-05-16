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

package org.eclipse.sapphire.samples.jee.web;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ImpliedElementProperty;
import org.eclipse.sapphire.modeling.ListProperty;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.Documentation;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlRootBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlValueBinding;
import org.eclipse.sapphire.samples.jee.IDescribable;
import org.eclipse.sapphire.samples.jee.IParam;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@XmlRootBinding( elementName = "web-app", namespace = "http://java.sun.com/xml/ns/j2ee", schemaLocation = "http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd" )
@GenerateImpl

public interface IWebAppConfig extends IModelElement, IDescribable
{
    ModelElementType TYPE = new ModelElementType( IWebAppConfig.class );
    
    // *** Distributable ***
    
    @Type( base = Boolean.class )
    @Label( standard = "distributable" )
    @XmlValueBinding( path = "distributable", mapExistanceToValue = "true" )
    
    // TODO: Documentation
    
    ValueProperty PROP_DISTRIBUTABLE = new ValueProperty( TYPE, "Distributable" );
    
    Value<Boolean> getDistributable();
    void setDistributable( String value );
    void setDistributable( Boolean value );
    
    // *** ContextParams ***
    
    @Type( base = IParam.class )
    @Label( standard = "context parameters" )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "context-param", type = IParam.class ) )
    
    @Documentation
    (
        content = "The web application's servlet context initialization parameters."
    )
    
    ListProperty PROP_CONTEXT_PARAMS = new ListProperty( TYPE, "ContextParams" );
    
    ModelElementList<IParam> getContextParams();
    
    // *** Filters ***
    
    @Type( base = IFilter.class )
    @Label( standard = "filters" )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "filter", type = IFilter.class ) )
    
    @Documentation
    (
        content = "Filters intercept and modify requests and response from the server. Once defined, " +
                  "filters are mapped to either a servlet or a URL pattern."
    )
    
    ListProperty PROP_FILTERS = new ListProperty( TYPE, "Filters" );
    
    ModelElementList<IFilter> getFilters();
    
    // *** FilterMappings ***
    
    @Type( base = IFilterMapping.class )
    @Label( standard = "filter mappings" )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "filter-mapping", type = IFilterMapping.class ) )
    
    @Documentation
    (
        content = "The container uses the filter mappings to decide which filters to apply to a request and " +
                  "in what order. The container matches request URL to a servlet in the normal manner. To " +
                  "determine which filters to apply, it matches filter mapping declarations either on " +
                  "servlet name or on URL pattern for each filter mapping. The order in which " +
                  "filters are invoked is the order of filter mappings in the web application configuration."
    )
    
    ListProperty PROP_FILTER_MAPPINGS = new ListProperty( TYPE, "FilterMappings" );
    
    ModelElementList<IFilterMapping> getFilterMappings();
    
    // *** Listeners ***
    
    @Type( base = IListener.class )
    @Label( standard = "listeners" )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "listener", type = IListener.class ) )
    
    @Documentation
    (
        content = "Listeners are notified of web application and HTTP session lifecycle events. To listen " +
                  "to web application events, implement javax.servlet.ServletContextListener. To " +
                  "listen to HTTP session events, implement javax.servlet.http.HttpSessionListener."
    )
    
    ListProperty PROP_LISTENERS = new ListProperty( TYPE, "Listeners" );
    
    ModelElementList<IListener> getListeners();

    // *** Servlets ***
    
    @Type( base = IServlet.class )
    @Label( standard = "servlet" )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "servlet", type = IServlet.class ) )
    
    // TODO: documentation
    
    ListProperty PROP_SERVLETS = new ListProperty( TYPE, "Servlets" );
    
    ModelElementList<IServlet> getServlets();
    
    // *** ServletMappings ***
    
    @Type( base = IServletMapping.class )
    @Label( standard = "servlet mappings" )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "servlet-mapping", type = IServletMapping.class ) )
    
    ListProperty PROP_SERVLET_MAPPINGS = new ListProperty( TYPE, "ServletMappings" );
    
    ModelElementList<IServletMapping> getServletMappings();
    
    // *** SessionConfig ***
    
    @Type( base = ISessionConfig.class )
    @Label( standard = "session configuration" )
    @XmlBinding( path = "session-config" )
    
    ImpliedElementProperty PROP_SESSION_CONFIG = new ImpliedElementProperty( TYPE, "SessionConfig" );
    
    ISessionConfig getSessionConfig();
    
    // *** MimeTypeMappings ***
    
    @Type( base = IMimeTypeMapping.class )
    @Label( standard = "MIME type mappings" )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "mime-mapping", type = IMimeTypeMapping.class ) )
    
    @Documentation
    (
        content = "MIME type mappings tell the server how to set the content type when serving files with unrecognized " +
                  "extensions."
    )

    ListProperty PROP_MIME_TYPE_MAPPINGS = new ListProperty( TYPE, "MimeTypeMappings" );
    
    ModelElementList<IMimeTypeMapping> getMimeTypeMappings();
    
    // *** WelcomeFiles ***
    
    @Type( base = IWelcomeFile.class )
    @Label( standard = "welcome files" )
    @XmlListBinding( path = "welcome-file-list", mappings = @XmlListBinding.Mapping( element = "welcome-file", type = IWelcomeFile.class ) )
    
    @Documentation
    (
        content = "When a request for a folder path is received, the server uses the list of welcome file names " +
                  "(such as index.html) to determine the content of the response. Files are matched in order of specification. " +
                  "[pbr/]" +
                  "If not specified by the web application, the server is responsible for providing the default list. Depending " +
                  "on server implimentation, this list may be configurable."
    )

    ListProperty PROP_WELCOME_FILES = new ListProperty( TYPE, "WelcomeFiles" );
    
    ModelElementList<IWelcomeFile> getWelcomeFiles();
    

    
    

    
    

    

    
    


    
    




    
    
}
