package org.genericsystem.demo;

import org.genericsystem.kernel.Engine;
import org.genericsystem.quiz.app.QuizApp;
import org.genericsystem.reactor.appserver.ApplicationServer;
import org.genericsystem.reactor.appserver.WebAppsConfig;
import org.genericsystem.reactor.context.RootContext;
import org.genericsystem.reactor.gscomponents.RootTagImpl;
import org.genericsystem.todomvc.TodoApp;

public class App extends RootTagImpl {
	public static void main(String[] mainArgs) {
		WebAppsConfig appsConfig = new WebAppsConfig(mainArgs);
		appsConfig.addApplication("/todomvc", TodoApp.class, RootContext.class, Engine.class, System.getenv("HOME") + "/genericsystem/todo/");
		appsConfig.addApplication("/", org.genericsystem.carcolor.app.App.class, RootContext.class, Engine.class, System.getenv("HOME") + "/genericsystem/cars/");
		appsConfig.addApplication("/quiz", QuizApp.class, RootContext.class, Engine.class, System.getenv("HOME") + "/genericsystem/quiz/");
		new ApplicationServer(appsConfig).start();
	}
}
