/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.snippets;

import org.eclipse.rap.rwt.lifecycle.IEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class ButtonSnippet implements IEntryPoint {

  private Button pushButton;
  private Button toggleButton;
  private Label label;

  public int createUI() {
    Display display = new Display();
    Shell shell = new Shell( display );
    shell.setText( "Button Snippet" );
    shell.setLayout( new GridLayout() );
    createContents( shell );
    shell.setSize( 300, 200 );
    shell.setLocation( 20, 20 );
    shell.open();
    while( !shell.isDisposed() ) {
      if( !display.readAndDispatch() ) {
        display.sleep();
      }
    }
    return 0;
  }

  private void createContents( Composite parent ) {
    pushButton = new Button( parent, SWT.PUSH );
    pushButton.setText( "Push" );
    pushButton.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        label.setText( "Push button pressed" );
      }
    } );
    toggleButton = new Button( parent, SWT.TOGGLE );
    toggleButton.setText( "Toggle" );
    toggleButton.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        String action = toggleButton.getSelection() ? "enabled" : "disabled";
        label.setText( "Toggle button " + action );
      }
    } );
    label = new Label( parent, SWT.NONE );
    label.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, true, false ) );
  }
}
