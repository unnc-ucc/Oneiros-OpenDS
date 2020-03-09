package indi.shuolei.mapeditor.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
			Scene scene = new Scene(root,1024,640);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("JMEMapEditor");
			
			primaryStage.setResizable(true);	
			primaryStage.setMaximized(true);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws Exception {
		MainController.isRunning = false;
		super.stop();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
