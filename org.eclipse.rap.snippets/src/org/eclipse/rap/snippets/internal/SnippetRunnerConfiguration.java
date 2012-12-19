package org.eclipse.rap.snippets.internal;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;


public class SnippetRunnerConfiguration implements ApplicationConfiguration {

  public void configure( Application application ) {
    application.addEntryPoint( "/snippet", SnippetRunner.class, null );
  }

}
