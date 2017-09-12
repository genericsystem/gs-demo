package org.genericsystem.demo;

import org.genericsystem.common.Root;
import org.genericsystem.ir.Dispatcher;
import org.genericsystem.ir.DistributedVerticle;
import org.genericsystem.ir.OcrEngineHolderVerticle;
import org.genericsystem.ir.app.WatchApp;
import org.genericsystem.kernel.Engine;
import org.genericsystem.quiz.app.QuizApp;
import org.genericsystem.reactor.appserver.ApplicationServer;
import org.genericsystem.reactor.appserver.WebAppsConfig;
import org.genericsystem.reactor.context.RootContext;
import org.genericsystem.reactor.gscomponents.RootTagImpl;
import org.genericsystem.todomvc.TodoApp;

public class App extends RootTagImpl {

	private static final String basePath = System.getenv("HOME") + "/genericsystem/";

	public static void main(String[] mainArgs) {
		ApplicationServer server = deployApplications(mainArgs);
		Root root = server.getRoots().get(basePath + "info-retriever/");
		deployIrVerticles(root);
	}

	/**
	 * Deploy the web applications.
	 * 
	 * @param mainArgs - if provided, the port where the application will be accessed
	 * @return the ApplicationServer object
	 */
	private static ApplicationServer deployApplications(String[] mainArgs) {
		WebAppsConfig appsConfig = new WebAppsConfig(mainArgs);
		appsConfig.addApplication("/todomvc", TodoApp.class, RootContext.class, Engine.class, basePath + "genericsystem/todo/");
		appsConfig.addApplication("/", org.genericsystem.carcolor.app.App.class, RootContext.class, Engine.class, basePath + "genericsystem/cars/");
		appsConfig.addApplication("/quiz", QuizApp.class, RootContext.class, Engine.class, basePath + "quiz/");
		appsConfig.addApplication("/information-retriever", WatchApp.class, RootContext.class, Engine.class, basePath + "info-retriever/");
		ApplicationServer server = new ApplicationServer(appsConfig);
		server.start();
		return server;
	}

	/**
	 * Deploy the verticles needed for gs-ir (information retriever) to work.
	 */
	private static void deployIrVerticles(Root root) {
		deployOcrEngineHolderVerticle(root);
		deployDispatcher();
		deployDistributedVerticle();
	}

	private static void deployOcrEngineHolderVerticle(Root root) {
		OcrEngineHolderVerticle ocrEngineHolderVerticle = new OcrEngineHolderVerticle(root);
		ocrEngineHolderVerticle.doDeploy();
	}

	private static void deployDispatcher() {
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.doDeploy();
	}

	private static void deployDistributedVerticle() {
		DistributedVerticle distributedVerticle = new DistributedVerticle();
		distributedVerticle.doDeploy();
	}
}
