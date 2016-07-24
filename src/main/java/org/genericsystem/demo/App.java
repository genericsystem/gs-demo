package org.genericsystem.demo;

import org.genericsystem.carcolor.CarColorApp;
import org.genericsystem.kernel.Engine;
import org.genericsystem.reactor.appserver.ApplicationServer;
import org.genericsystem.reactor.appserver.WebAppsConfig;
import org.genericsystem.reactor.model.EngineModel;
import org.genericsystem.todomvc.TodoApp;
import org.genericsystem.todomvc.TodoList;

public class App {
	public static void main(String[] mainArgs) {
		WebAppsConfig appsConfig = new WebAppsConfig(mainArgs);
		appsConfig.addApplication("/todomvc", TodoApp.class, TodoList.class, Engine.class, System.getenv("HOME") + "/genericsystem/todo/");
		appsConfig.addApplication("/", CarColorApp.class, EngineModel.class, Engine.class, System.getenv("HOME") + "/genericsystem/cars/");
		new ApplicationServer(appsConfig).start();

	}
}
