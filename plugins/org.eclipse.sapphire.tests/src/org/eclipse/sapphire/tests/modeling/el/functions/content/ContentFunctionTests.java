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

package org.eclipse.sapphire.tests.modeling.el.functions.content;

import org.eclipse.sapphire.modeling.Status;
import org.eclipse.sapphire.modeling.el.FunctionContext;
import org.eclipse.sapphire.modeling.el.FunctionResult;
import org.eclipse.sapphire.modeling.el.ModelElementFunctionContext;
import org.eclipse.sapphire.modeling.el.parser.ExpressionLanguageParser;
import org.eclipse.sapphire.tests.modeling.el.TestExpr;
import org.junit.Test;

/**
 * Tests Content function.
 * 
 * @author <a href="mailto:konstantin@komissarchik.net">Konstantin Komissarchik</a>
 */

public final class ContentFunctionTests extends TestExpr
{
    @Test
    
    public void testContentFunction()
    {
        final TestElement element = TestElement.TYPE.instantiate();
        final FunctionContext context = new ModelElementFunctionContext( element );
        
        try( FunctionResult fr = ExpressionLanguageParser.parse( "${ IntegerValue.Content }" ).evaluate( context ) )
        {
            assertNull( fr.value() );
            
            element.setIntegerValue( 3 );
            assertEquals( 3, fr.value() );

            element.setIntegerValue( "abc" );
            assertNull( fr.value() );
        }

        try( FunctionResult fr = ExpressionLanguageParser.parse( "${ IntegerValueWithDefault.Content }" ).evaluate( context ) )
        {
            assertEquals( 1, fr.value() );
            
            element.setIntegerValueWithDefault( 3 );
            assertEquals( 3, fr.value() );

            element.setIntegerValueWithDefault( "abc" );
            assertEquals( 1, fr.value() );
        }

        try( FunctionResult fr = ExpressionLanguageParser.parse( "${ Transient.Content }" ).evaluate( context ) )
        {
            assertNull( fr.value() );
            
            element.setTransient( this );
            assertSame( this, fr.value() );
        }
    }
    
    @Test

    public void testContentFunctionNull()
    {
        final TestElement element = TestElement.TYPE.instantiate();
        final FunctionContext context = new ModelElementFunctionContext( element );
        
        try( FunctionResult fr = ExpressionLanguageParser.parse( "${ Content( null ) }" ).evaluate( context ) )
        {
            final Status st = fr.status();
            
            assertEquals( Status.Severity.ERROR, st.severity() );
            assertEquals( "Function Content does not accept nulls in position 0.", st.message() );
        }
    }
    
    @Test

    public void testContentFunctionWrongType()
    {
        final TestElement element = TestElement.TYPE.instantiate();
        final FunctionContext context = new ModelElementFunctionContext( element );
        
        try( FunctionResult fr = ExpressionLanguageParser.parse( "${ Content( 'abc' ) }" ).evaluate( context ) )
        {
            final Status st = fr.status();
            
            assertEquals( Status.Severity.ERROR, st.severity() );
            assertEquals( "Function Content( java.lang.String ) is undefined.", st.message() );
        }
    }

}
