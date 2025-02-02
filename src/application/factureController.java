package application;
import java.net.URL;
import application.checkinController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.print.PrinterJob;
import javafx.scene.Node;
//import com.itextpdf.text.Document;

public class factureController implements Initializable{
	
	
	@FXML
    private Label taxeLabel;
	 @FXML
	    private Label numerochambrelabel;
	 @FXML
	    private Label nomClientLabel;

	    @FXML
	    private Label emailClientLabel;

	    @FXML
	    private Label telephoneClientLabel;

	    @FXML
	    private Label paysClientLabel;

	    @FXML
	    private Label adresseClientLabel;

	    @FXML
	    private Label checkinLabel;

	    @FXML
	    private Label checkoutLabel;

	    @FXML
	    private Label typeChambreLabel;

	    @FXML
	    private Label numeroChambreLabel;

	    @FXML
	    private Label montantTotalLabel;

	 
	@FXML
    private AnchorPane factureAnchorPane;
 
    
    // Optionnel : méthode pour afficher une alerte en cas d'erreur
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	private static Connection connection = SingleConnection.getConnection();

	private static PreparedStatement pst = null; // Objet pour exécuter les requêtes SQL préparées
	private static Statement stm = null;
	private static ResultSet res = null; // Objet pour stocker les résultats des requêtes SQL

//-----------------------------------------------------
	
	
	
	
	@FXML
	public void imprimerFacture() {
	    PrinterJob printerJob = PrinterJob.createPrinterJob();
	    
	    if (printerJob != null) {
	        // Trouver la fenêtre ou le nœud que tu veux imprimer, par exemple la scène de la facture
	        Node node = factureAnchorPane; // "factureAnchorPane" correspond au conteneur principal de la facture
	        
	        boolean printDialogShown = printerJob.showPrintDialog(null);
	        
	        if (printDialogShown) {
	            boolean success = printerJob.printPage(node);
	            if (success) {
	                printerJob.endJob();
	                showAlert("Succès", "La facture a été imprimée avec succès !");
	            } else {
	                showAlert("Erreur", "Échec de l'impression.");
	            }
	        }
	    }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		calculerTaxe(0);
		
	}

	   // Méthode pour recevoir les données du contrôleur checkin
    public void setdonneeclients(String id_client,String nom, String email, String telephone, String adresse, LocalDate checkin, LocalDate checkout, 
                              String typeChambre, String numeroChambre, double montantTotal) {
        // Afficher les données dans les labels
    	numerochambrelabel.setText("Numero de la chambre : " + id_client);
        nomClientLabel.setText("Nom : " + nom);
        emailClientLabel.setText("Email : " + email);
        telephoneClientLabel.setText("Telephone : " + telephone);
     //   paysClientLabel.setText(pays);
        adresseClientLabel.setText("Adresse : " + adresse);
        checkinLabel.setText( checkin != null ? " Date d'Entrer : "+ checkin.toString() : ""); 
        checkoutLabel.setText( checkout != null ?  "Date de Sortir : "+ checkout.toString() : "");
   
        //  numeroChambreLabel.setText(numeroChambre);
        montantTotalLabel.setText(String.valueOf( + montantTotal + " FCFA"));
        
        // Calcul de la taxe et affichage
    
    }
     @FXML
   public void calculerTaxe(double montantTotal) {   
    	// double taxe = calculerTaxe(montantTotal);

        double tauxTaxe = 0.18;  // Exemple de taxe de 18%   

       double taxe= montantTotal * tauxTaxe;
        taxeLabel.setText("Montant de la taxe:  " + taxe + " FCFA");

    }

}


