/******************************************************************************
 * Copyright (c) 2016 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ling Hao - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.swt.gef.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.sapphire.ui.diagram.DiagramConnectionPart;

/**
 * @author <a href="mailto:ling.hao@oracle.com">Ling Hao</a>
 */

public class LabelConnectionCommand extends Command {
	
	private DiagramConnectionPart part;
	private String labelText;

	public LabelConnectionCommand(DiagramConnectionPart part, String labelText) {
		this.part = part;
		this.labelText = labelText;
	}

	@Override
	public void execute() {
		part.setLabel(labelText);
	}
	
}
