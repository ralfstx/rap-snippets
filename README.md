RAP Snippet Runner
==================

The snippet runner makes it easy to run RAP snippets without registering entrypoints
and restarting the server.

Usage
-----

* Put your snippets into the package org.eclipse.rap.snippets.
  To keep them separate from this bundle you can also create a fragment project
  e.g. org.eclipse.rap.snippets.custom.
* Snippet classes must either implement IApplication or IEntrypoint or they must
  have a standard Java main method.
* Snippets that reside in the package org.eclipse.rap.snippets can be accessed
  using an URL like this:
    http://localhost:9090/snippets?class=MySnippet
* Snippets in other packages must use the fully qualified class name:
    http://localhost:9090/snippets?class=org.eclipse.swt.snippets.Snippet91

License
-------

All classes are published under the terms of the Eclipse Public License v1.0
