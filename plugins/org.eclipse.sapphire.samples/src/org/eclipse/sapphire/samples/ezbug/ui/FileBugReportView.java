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

package org.eclipse.sapphire.samples.ezbug.ui;

import static org.eclipse.sapphire.ui.swt.renderer.GridLayoutUtil.gdfill;
import static org.eclipse.sapphire.ui.swt.renderer.GridLayoutUtil.glayout;

import org.eclipse.sapphire.samples.ezbug.IBugReport;
import org.eclipse.sapphire.samples.ezbug.IFileBugReportOp;
import org.eclipse.sapphire.ui.def.DefinitionLoader;
import org.eclipse.sapphire.ui.swt.SapphireForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class FileBugReportView extends ViewPart
{
    @Override
    public void createPartControl( final Composite parent )
    {
        parent.setBackground( parent.getDisplay().getSystemColor( SWT.COLOR_WHITE ) );
        parent.setLayout( glayout( 1, 0, 0 ) );
        
        final IFileBugReportOp op = IFileBugReportOp.TYPE.instantiate();
        final IBugReport report = op.getBugReport();
        
        final SapphireForm control = new SapphireForm
        (
            parent, report,
            DefinitionLoader.context( IFileBugReportOp.class ).sdef( "EzBug" ).form( "bug.report.form.style.scrolled" )
        );

        control.setLayoutData( gdfill() );
    }

    @Override
    public void setFocus()
    {
    }
    
}
