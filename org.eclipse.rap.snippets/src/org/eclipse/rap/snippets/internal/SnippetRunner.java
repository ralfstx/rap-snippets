/*******************************************************************************
 * Copyright (c) 2010, 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.snippets.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.EntryPoint;


public class SnippetRunner implements EntryPoint {

  private static final String PLUGIN_ID = "org.eclipse.rap.snippets";
  private static final String PACKAGE_PREFIX = "org.eclipse.rap.snippets";

  public int createUI() {
    String value = RWT.getRequest().getParameter( "class" );
    try {
      if( value == null || value.length() <= 0 ) {
        String message = "No class given in URL parameter \"class\"";
        throw new IllegalArgumentException( message );
      }
      Class<?> snippetClass = findClass( value );
      runSnippet( snippetClass );
    } catch( Exception exception ) {
      if( exception instanceof InvocationTargetException ) {
        Throwable cause = exception.getCause();
        if( cause instanceof Error ) {
          throw ( Error )cause;
        }
      }
      showErrorDialog( exception );
    }
    return 0;
  }

  private Class<?> findClass( String value ) {
    if( value.indexOf( '.' ) != -1 ) {
      return loadClass( value );
    }
    return loadClass( PACKAGE_PREFIX + "." + value );
  }

  private Class<?> loadClass( String className ) {
    ClassLoader classLoader = SnippetRunner.class.getClassLoader();
    try {
      return classLoader.loadClass( className );
    } catch( ClassNotFoundException e ) {
      String message = "The class could not be found: " + className;
      throw new RuntimeException( message, e );
    }
  }

  private void runSnippet( Class<?> snippetClass ) throws Exception {
    if( IApplication.class.isAssignableFrom( snippetClass ) ) {
      IApplication application = ( IApplication )createInstance( snippetClass );
      application.start( null );
    } else if( EntryPoint.class.isAssignableFrom( snippetClass ) ) {
      EntryPoint entrypoint = ( EntryPoint )createInstance( snippetClass );
      entrypoint.createUI();
    } else {
      Method method = getMainMethod( snippetClass );
      if( method == null ) {
        String message = "The class "
                         + snippetClass.getName()
                         + " does not implement IApplication or EntryPoint"
                         + " and does not have a main method.";
        throw new IllegalArgumentException( message );
      }
      method.invoke( null, new Object[] { new String[ 0 ] } );
    }
  }

  private Method getMainMethod( Class<?> snippetClass ) {
    Method method = null;
    try {
      Class<?>[] mainParameterTypes = new Class[]{ String[].class };
      method = snippetClass.getMethod( "main", mainParameterTypes );
    } catch( NoSuchMethodException e ) {
      // no such method, return null
    }
    return method;
  }

  private Object createInstance( Class<?> classToRun ) {
    try {
      return classToRun.newInstance();
    } catch( InstantiationException e ) {
      String message = "The class could not be instantiated: "
                       + classToRun.getName();
      throw new RuntimeException( message, e );
    } catch( IllegalAccessException e ) {
      String message = "The class or constructor is not accessible: "
                       + classToRun.getName();
      throw new RuntimeException( message, e );
    }
  }

  private void showErrorDialog( Exception e ) {
    String message = "Could not load requested snippet";
    e.printStackTrace();
    String errorMessage = e.getClass().getName() + ": " + e.getMessage();
    Status status = new Status( IStatus.ERROR, PLUGIN_ID, errorMessage, e );
    ErrorDialog.openError( null, "Snippet Runner Error", message, status );
  }
}
