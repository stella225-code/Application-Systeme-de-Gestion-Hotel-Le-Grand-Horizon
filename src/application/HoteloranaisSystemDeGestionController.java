package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.sql.Connection;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HoteloranaisSystemDeGestionController implements Initializable {
	
	@FXML
    private Button loginbtn;
	

    @FXML
    private PasswordField password;

    @FXML
    private StackPane root;
  
    
    @FXML
    private AnchorPane root1;

    @FXML
    private  TextField usertfd;
    
    
    //outil database 
    
 //   private static SingleConnection conx = new SingleConnection(); // Objet de connexion à la base de données
//	private static Connection connection=null;    // laconnexion doit etre partarger""static"" avec user , client et autre
	private static Connection connection=SingleConnection.getConnection();

    private static PreparedStatement pst=null; // Objet pour exécuter les requêtes SQL préparées
    private static ResultSet res=null; // Objet pour stocker les résultats des requêtes SQL
    
    

	 // Optionnel : méthode pour afficher une alerte en cas d'erreur
   private void showAlert(String title, String content) {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle(title);
       alert.setContentText(content);
       alert.show();
       
       // Create a timeline to close the alert after 5 seconds
       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
           // Close the alert if it's still open
           if (alert.isShowing()) {
               alert.close();
           }
       }));
       
       // Start the timeline
       timeline.play();
   }
   
   /* public  void login() {
    //	String user = usertfd.getText();
   // 	String pass = password.getText();
    //	String sql = "SELECT * FROM login WHERE nomlogin ='"+user+"'  and passlogin= '"+pass+"'  ";
    	
    //	conx= SingleConnection.connectdb();
    	
    		pst = conx.prepareStatement(sql);// Préparer la requête SQL
			pst.setString(1, user);
			pst.setString(2, pass);
             System.out.println("Exécution de la requête SQL login");
             
            res=pst.executeQuery();
            
            Alert alert;
            
            if(res.next()) {
            	
             alert = new Alert(AlertType.INFORMATION);
           	 alert.setTitle("information message ");
           	 alert.setHeaderText(null);
           	 alert.setContentText("connection success ");
           	 alert.showAndWait();
            }else {
             alert = new Alert(AlertType.ERROR);
           	 alert.setTitle("information message ");
           	 alert.setHeaderText(null);
           	 alert.setContentText("code ou nom incorrect");
           	 alert.showAndWait();
            }
            
           if( usertfd.getText().isEmpty() || password.getText().isEmpty()) {
            
            	Alert alert = new Alert(AlertType.INFORMATION);
            	 alert.setTitle("information message ");
            	 alert.setHeaderText(null);
            	 alert.setContentText("champs invalide");
            	 alert.showAndWait();
            } /*else
            {
            	conx=(Connection) SingleConnection.connectdb();
    			PreparedStatement pst = conx.prepareStatement(sql);// Préparer la requête SQL
    			pst.setString(1, user);
    			pst.setString(2, pass);
                 System.out.println("Exécution de la requête SQL login");
                res=pst.executeQuery();
                   if (!res.isBeforeFirst()){ //si donnée n'existe pas
                	   
                	   Alert alert = new Alert(AlertType.ERROR);
  		           	 alert.setTitle("ERREUR message ");
  		           	 alert.setContentText("Compte invalide");
  		           	 alert.showAndWait();
             	
                   }else {
		            	while(res.next()){
		        		
		        			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));

                            Stage stage =new Stage();
                            Scene scene=new Scene(root);
                            stage.setScene(scene);
                            stage.show();
		            	                 }
                   
                   }     }*/ 
    
   /* public static int loginx(String user , String pass) {
    	Connection conx= SingleConnection.connectdb();

    	if(conx==null) return -1;
    	String sql = "SELECT * FROM login WHERE nomlogin =?  and passlogin= ? ";

    	
    	 try {
			pst=conx.prepareStatement(sql);
			pst.setString(1 , user);
			pst.setString(2 , pass);
			
			res = pst.executeQuery();
			
			while(res.next()) {
				return 0;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}return 1 ;
    }*/
   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//loginbtn.setOnAction(actionEvent -> loginx());
		
	}
	
	 @FXML
  public  void exit() {
System.exit(0);
    }

	 private double x=0;
	 private double y=0;
	 
	
	
	 @FXML
	 public void loginx() {
	     try {
	         // Préparation de la requête SQL pour vérifier l'utilisateur
	         pst = connection.prepareStatement("SELECT * FROM user WHERE email =? AND password= ?");
	         pst.setString(1, usertfd.getText());
	         pst.setString(2, password.getText());
	         res = pst.executeQuery();

	         if (res.next()) {
	             String role = res.getString("role"); // Récupérer le rôle de l'utilisateur depuis la BD
	             

	        //     Alert alert = new Alert(AlertType.CONFIRMATION, "Connexion réussie", ButtonType.OK);
	         //    alert.show();

	             // Charger le tableau de bord
	             FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
	             Parent root = loader.load();
	             Scene scene = new Scene(root);
	             Stage stage = new Stage();
	             stage.setTitle("Tableau de bord");
	             stage.setScene(scene);

	             // Fermer la fenêtre de connexion après l'ouverture du tableau de bord
	             Stage currentStage = (Stage) loginbtn.getScene().getWindow();
	             currentStage.close();

	             // Gérer le déplacement de la fenêtre
	             root.setOnMousePressed(event -> {
	                 x = event.getSceneX();
	                 y = event.getSceneY();
	             });

	             root.setOnMouseDragged(event -> {
	                 stage.setX(event.getScreenX() - x);
	                 stage.setY(event.getScreenY() - y);
	             });

	             stage.initStyle(StageStyle.TRANSPARENT);
	             stage.show();

	          //   MenuPrincipalController menuPrincipalController = new MenuPrincipalController();
			// Limiter l'accès en fonction du rôle
	         //  menuPrincipalController.limiterAcces();

	         } else {
	             Alert alert = new Alert(AlertType.WARNING, " Connexion OK");
	             alert.show();
	         }
	     } catch (SQLException | IOException e) {
	         e.printStackTrace();
	         showAlert("Erreur", "Erreur lors de la connexion.");
	     }
	 }


	 /**
	  * Limite l'accès aux fonctionnalités en fonction du rôle de l'utilisateur.
	  *
	  * @param role Le rôle de l'utilisateur connecté
	  */
/*	 private void limiterAcces(String role) {
	     if (role.equalsIgnoreCase("Client")) {
	    	 g.faqbtn.setDisable(false);
	         g.contactbtn.setDisable(false);
	        g.resevationbtn.setDisable(false);
	         // Exemple : désactiver certaines fonctionnalités pour les clients
	         System.out.println("Accès limité aux fonctionnalités réservées aux administrateurs.");
	         // Vous pouvez également désactiver ou cacher certains boutons
	     } else if (role.equalsIgnoreCase("Administrateur")) {
	         // Accès complet pour les administrateurs
	    	 
	         System.out.println("Accès complet.");
	     } else if (role.equalsIgnoreCase("Personnel")) {
	         // Limiter l'accès pour le personnel si nécessaire
	         System.out.println("Accès restreint pour le personnel.");
	     }
	 }
*/
	 /**
	  * Limite l'accès aux fonctionnalités en fonction du rôle de l'utilisateur.
	  *
	  * @param role Le rôle de l'utilisateur connecté
	  */
	
	    

	 public void crercompte() {
		 try {
		        // Charger le fichier FXML de la facture
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/creercompte.fxml")); // Mettez le bon chemin
		        Parent checkInPage = loader.load();
		        
		       

	            // Créer une nouvelle scène avec le fichier FXML chargé
	            Scene checkInScene = new Scene(checkInPage);

	            // Obtenir la stage actuelle à partir de l'événement
	            Stage stage =new Stage ();

	            stage.initStyle(StageStyle.DECORATED);
	            // Appliquer la nouvelle scène sur la stage
	            stage.setScene(checkInScene);
	            stage.show();
	            
		     
		
		    } catch (IOException e) {
		        e.printStackTrace();
		        showAlert("Erreur", "Impossible d'ouvrir la creation de compte.");
		    }
	 }
		 
		 //---------------------------------------MTHODE CRER COMPTE
		 @FXML
		 private TextField nomUtilisateur;
		 @FXML
		 private TextField nom;

		 @FXML
		 private TextField prenom;

		 @FXML
		 private TextField email;

		 @FXML
		 private TextField telephone;

		 @FXML
		 private TextField motDePasse;

		 @FXML
		 private TextField confirmerMotDePasse;

		 @FXML
		 private CheckBox clientCheckBox;

		 @FXML
		 private CheckBox adminCheckBox;

		 @FXML
		 private CheckBox personnelCheckBox;

		 @FXML
		 private Button confirmerCreationButton;

		 @FXML
		 private Button annulerButton;
		// Méthode pour valider et insérer les données dans la base de données
		 @FXML
		 public void confirmerCreationCompte() {
		     String username = nomUtilisateur.getText();
		     String name = nom.getText();
		     String surname = prenom.getText();
		     String emailAddress = email.getText();
		     String phone = telephone.getText();
		     String password = motDePasse.getText();
		     String confirmPassword = confirmerMotDePasse.getText();
		     String role = getRole(); // Méthode qui récupère le rôle choisi
		     
		   
		     
		     if (password.equals(confirmPassword)) {
		         // Insérer dans la base de données
		         String sql = "INSERT INTO user (username, name, surname, email, phone, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

		         try {
		             PreparedStatement pst = connection.prepareStatement(sql);
		             pst.setString(1, username);
		             pst.setString(2, name);
		             pst.setString(3, surname);
		             pst.setString(4, emailAddress);
		             pst.setString(5, phone);
		             pst.setString(6, password);
		             pst.setString(7, role);
		             pst.executeUpdate();
		             
		             showAlert("Succès", "Le compte utilisateur a été créé avec succès !");
		         } catch (SQLException e) {
		             e.printStackTrace();
		             showAlert("Erreur", "Impossible de créer le compte utilisateur.");
		         }
		     } else {
		         showAlert("Erreur", "Les mots de passe ne correspondent pas.");
		     }
		 }

		 // Méthode pour obtenir le rôle sélectionné
		 private String getRole() {
		     if (clientCheckBox.isSelected()) {
		         return "Client";
		     } else if (adminCheckBox.isSelected()) {
		         return "Administrateur";
		     } else if (personnelCheckBox.isSelected()) {
		         return "Personnel";
		     }
		     return "Autres";
		 }

		 // Méthode pour réinitialiser les champs
		 @FXML
		 public void annulerAction() {
		     nomUtilisateur.clear();
		     nom.clear();
		     prenom.clear();
		     email.clear();
		     telephone.clear();
		     motDePasse.clear();
		     confirmerMotDePasse.clear();
		     clientCheckBox.setSelected(false);
		     adminCheckBox.setSelected(false);
		     personnelCheckBox.setSelected(false);
		 
	 }
}