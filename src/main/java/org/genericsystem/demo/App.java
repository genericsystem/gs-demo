package org.genericsystem.demo;

import org.genericsystem.carcolor.CarColorApp;
import org.genericsystem.kernel.Engine;
import org.genericsystem.reactor.appserver.ApplicationServer;
import org.genericsystem.reactor.appserver.WebAppsConfig;
import org.genericsystem.reactor.gscomponents.GSApp;
import org.genericsystem.reactor.model.RootModel;
import org.genericsystem.todomvc.TodoApp;

public class App extends GSApp {
	public static void main(String[] mainArgs) {
		WebAppsConfig appsConfig = new WebAppsConfig(mainArgs);
		appsConfig.addApplication("/todomvc", TodoApp.class, RootModel.class, Engine.class, System.getenv("HOME") + "/genericsystem/todo/");
		appsConfig.addApplication("/", CarColorApp.class, RootModel.class, Engine.class, System.getenv("HOME") + "/genericsystem/cars/");
		new ApplicationServer(appsConfig).start();

	}
}
