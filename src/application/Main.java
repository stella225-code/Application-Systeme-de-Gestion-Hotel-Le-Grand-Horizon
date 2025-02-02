package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("HoteloranaisSystemDeGestion.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Page de Connexion");
			primaryStage.getIcons().add(new Image ("file:icon.jpg"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SingleConnection conx = new SingleConnection();
		//	conx.connectdb();
			
			///MenuPrincipalController log = new MenuPrincipalController();
			HoteloranaisSystemDeGestionController loginxx =new HoteloranaisSystemDeGestionController();
			
			launch(args);
		}

		
	}

