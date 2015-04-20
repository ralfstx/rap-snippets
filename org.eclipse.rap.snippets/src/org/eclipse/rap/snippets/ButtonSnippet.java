/*******************************************************************************
 * Copyright (c) 2012, 2015 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.snippets;

import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;


public class ButtonSnippet extends AbstractEntryPoint {

  private Button pushButton;
  private Button toggleButton;
  private Label label;

  @Override
  protected void createContents( Composite parent ) {
    pushButton = new Button( parent, SWT.PUSH );
    pushButton.setText( "Push" );
    pushButton.addListener( SWT.Selection, new Listener() {
      @Override
      public void handleEvent( Event event ) {
        label.setText( "Push button pressed" );
        System.out.println( "push button pressed" );
      }
    } );
    toggleButton = new Button( parent, SWT.TOGGLE );
    toggleButton.setText( "Toggle" );
    toggleButton.addListener( SWT.Selection, new Listener() {
      @Override
      public void handleEvent( Event event ) {
        String action = toggleButton.getSelection() ? "enabled" : "disabled";
        label.setText( "Toggle button " + action );
      }
    } );
    label = new Label( parent, SWT.NONE );
    label.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, true, false ) );
  }

}
