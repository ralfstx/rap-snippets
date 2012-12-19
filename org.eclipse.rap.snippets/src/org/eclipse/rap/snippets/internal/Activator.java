package org.eclipse.rap.snippets.internal;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {

  private ServiceRegistration<ApplicationConfiguration> serviceRegistration;

  public void start( BundleContext context ) throws Exception {
    SnippetRunnerConfiguration config = new SnippetRunnerConfiguration();
    serviceRegistration = context.registerService( ApplicationConfiguration.class, config, null );
  }

  public void stop( BundleContext context ) throws Exception {
    serviceRegistration.unregister();
  }

}
