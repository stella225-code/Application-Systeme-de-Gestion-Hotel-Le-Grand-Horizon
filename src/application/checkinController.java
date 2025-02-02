package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class checkinController implements Initializable{

	
	// -----------------------------------------------------------------------------------
	// ----------------------------CHEKIN--------------------------------------------------

	
	@FXML
	private TextField cEmail;

	@FXML
	private TextField cGender;

	@FXML
	private TextField cName;

	@FXML
	private TextField cNationality;

	@FXML
	private TextField cadresse;

	@FXML
	private TextField cPhone;

	@FXML
	private AnchorPane cheikcinformulaire;

	@FXML
 private Button close;

	@FXML
	private Button envoyer;

	@FXML
	private Button facture;

	@FXML
	private DatePicker inDate;

	@FXML
	private DatePicker outDate;

	@FXML
	private Button reinitialiser;

	@FXML
	private Label id_client;
	@FXML
	private 	 Label totalJourLabel;
	@FXML
	private     Label montantTotalLabel;
	

	 // Optionnel : méthode pour afficher une alerte en cas d'erreur
   private void showAlert(String title, String content) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle(title);
       alert.setContentText(content);
       alert.showAndWait();
   }
   
/*	private boolean fieldsAreValid() {
		return !(idchambre.getText().isEmpty() || typechambre.getSelectionModel().isEmpty()
				|| statutchambre.getSelectionModel().isEmpty() || prixchambre.getText().isEmpty());
	}

	private void clearFields() {
		idchambre.setText("");
		typechambre.setPromptText("");
		statutchambre.setPromptText("");
		prixchambre.setText("");
	}
*/
	private static Connection connection = SingleConnection.getConnection();

	private static PreparedStatement pst = null; // Objet pour exécuter les requêtes SQL préparées
	private static Statement stm = null;
	private static ResultSet res = null; // Objet pour stocker les résultats des requêtes SQL

	//----------------------------------------------------------------------------
   
   
   
	
	
	   public void close() {

		 System.exit(0);
	    }

	 public void numeroclient() {
		 String sql1 = "select id_client from clients";
		 connection = SingleConnection.getConnection();

			try {
				

				PreparedStatement pst = connection.prepareStatement(sql1);// Préparer la requête SQL

				res = pst.executeQuery();

				while (res.next()) {
					donneeclients.id_client = res.getInt("id_client");
					
				}

			} catch (Exception e) {
				// TODO: handle exception
	            showAlert("Erreur", "Impossible de récupérer le id client.");

			}
	
		}
	 
	 
	 public void afichernumeroclient() {
		 numeroclient();
		id_client.setText(String.valueOf(donneeclients.id_client +1 ));
	 }
	 
	    // Méthode pour envoyer les données à la base de données (insertion)

	@FXML
	 public void envoyerAction(ActionEvent event) {
        String sql = "INSERT INTO clients (id_client, nom, email, telephone, pays,adresse, checkin, checkout, genre) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        connection=SingleConnection.getConnection();
        
        try {
            pst = connection.prepareStatement(sql);
              pst.setString(8, id_client.getText());
            pst.setString(1, cName.getText());  // Nom
            //.setString(2, "cprenom");  // Prénom à ajouter (s'il y a un champ)
            pst.setString(2, cEmail.getText());  // Email
            pst.setString(3, cPhone.getText());  // Téléphone
            pst.setString(4, cNationality.getText());  // Adresse ou Nationalité
            pst.setString(5, cadresse.getText());  // Ville (à ajouter dans le formulaire si nécessaire)
      //      pst.setString(7, "");  // Pays (idem)
         //   pst.setString(8, String.valueOf(inDate.getValue()));  // Date d'inscription
            pst.setString(6, String.valueOf(inDate.getValue()));  // Check-in
            pst.setString(7, String.valueOf(outDate.getValue()));  // Check-out
            // Numéro client

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("Succès", "Le client a été ajouté avec succès !");
                renialiserAction();  // Réinitialiser après insertion
            }
        } catch (SQLException e) {
            showAlert("Erreur", "Impossible d'ajouter le client. Veuillez vérifier les informations.");
            e.printStackTrace();
        }
    }


	
	
		 // Méthode pour générer la facture (logique à implémenter)
	   // @FXML
	 //   public void factureAction(ActionEvent event) {
	//        // Logique pour afficher ou générer la facture
	//        showAlert("Information", "La génération de facture n'est pas encore implémentée.");
	 //   }
	

	@FXML
	void handleCheckInPick(ActionEvent event) {

	}

	@FXML
	void handleCheckOutPick(ActionEvent event) {

	}

	@FXML
	void idchambre(ActionEvent event) {

	}

	  // Méthode pour réinitialiser le formulaire
    @FXML
    public void renialiserAction() {
      
    	id_client.setText("");  // Clear the client number

    	cEmail.clear();
        cGender.clear();
        cName.clear();
        cNationality.clear();
        cPhone.clear();
        inDate.setValue(null);
        outDate.setValue(null);
        cadresse.clear();
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		afichernumeroclient();
		remplirTypesChambres();
		 remplirNumerosChambres();
		// afficherMontantTotal();
	}

	public void ajouter() {
		
		
		 String sql = "INSERT INTO clients (id_client, nom, email, telephone, pays, adresse, checkin, checkout, genre,totalJours, montantTotal, typechambre, idchambre) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

      connection=SingleConnection.getConnection();
      
      try {
    	// Calculer les valeurs de total_jours et montant_total
          long totalJours = jourTotal();
          double montantTotal = montantTotal();
          
    	  String sql1 =id_client.getText();
        String nom =cName.getText();  // Nom
          String email= cEmail.getText();  // Email
          String telephone= cPhone.getText();  // Téléphone
          String pays= cNationality.getText();  // Adresse ou Nationalité
          String adresse= cadresse.getText();  // Ville (à ajouter dans le formulaire si nécessaire)
          String checkin= String.valueOf(inDate.getValue());  // Check-in
          String checkout= String.valueOf(outDate.getValue());  // Check-out
          String genre =cGender.getText();  // genre client

          if (sql1==null || nom==null|| email==null||telephone==null||telephone ==null| pays==null||adresse==null||
        		  checkin==null||checkout==null||genre==null) {
              showAlert("Erreur", "Impossibbble d'ajouter le client. Veuillez vérifier les informations.");

          }else {
        	            pst = connection.prepareStatement(sql);
        	            pst.setString(1, sql1);
        	            pst.setString(2, nom);  // Nom
        	            pst.setString(3, email);  // Email
        	            pst.setString(4, telephone);  // Téléphone
        	            pst.setString(5, pays);  // Adresse ou Nationalité
        	            pst.setString(6, adresse);  // Ville (à ajouter dans le formulaire si nécessaire)
        	            pst.setString(7, String.valueOf(inDate.getValue()));  // Check-in
        	            pst.setString(8, String.valueOf(outDate.getValue()));  // Check-out
        	            pst.setString(9, cGender.getText());  // Numéro client
        	            // Insertion des valeurs de total_jours et montant_total
        	            pst.setLong(10, totalJours);  // Total jours
        	            pst.setDouble(11, montantTotal);  // Montant total
        	            
        	          pst.setString(12, typechambre.getValue());  // Type de chambre
        	           pst.setString(13, idchambre.getValue());  // Numéro de chambre

        	            pst.executeUpdate();
        	            
        	            //------------------------pour la facture
        	            
        	            String nomf =cName.getText();  // Nom
        	             
        	              String numerof =id_client.getText();
        	              String date	= String.valueOf(inDate.getValue());  // Check-in

        	              String totalp=totalJourLabel.getText();
        	           // Calculer le nombre de jours de séjour
        	              long totalJoursf =jourTotal();


        	           // Récupérer le type de chambre et le numéro de chambre
        	           String typeChambref = typechambre.getSelectionModel().getSelectedItem();
        	           String idChambref = idchambre.getSelectionModel().getSelectedItem();

        	        // Supposons que vous avez déjà défini un prix pour le type de chambre
        	           double prixTotalf = montantTotal();  // Calcul du montant total
        	         //  double montantServices = calculateServiceCost();  // Calcul des services supplémentaires (spa, etc.)
        	           double taxe = prixTotalf * 0.18;  // Par exemple, une taxe de 18%
        	       //    double totalAPayer = prixTotal + montantServices + taxe;  // Montant total à payer

        	           String sqlf = "INSERT INTO facture1 (id_client, nom_client, date_facture, total_jours, prix_total, type_chambre, numero_chambre, taxe) "
        	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        	           PreparedStatement pst = connection.prepareStatement(sqlf);
        	           // Récupérer les valeurs des champs
        	           pst.setString(1, numerof);  // ID du client
        	           pst.setString(2, nomf);  // Nom du client
        	           pst.setString(3, date);
        	           //  pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));  // Date de facture (aujourd'hui)
        	        
        	           pst.setLong(4, totalJoursf);  // Nombre total de jours
        	           pst.setDouble(5, prixTotalf);  // Prix total pour le séjour
        	           pst.setString(6, typeChambref);  // Type de chambre
        	           pst.setString(7, idchambre.getValue());  // Numéro de chambre

        	           pst.setDouble(8, taxe);  // Montant de la taxe
        	      
        	           pst.execute();  // Exécuter l'insertion
        	           showAlert("Succès", "La facture a été générée avec succès !");
        //---------------------------------------fin facture	
        	           
        	           //-------------------------statutt
        	      //     String sqlstatut = "update chambre set 'statutchambre'='occupé'";
        	     //      Statement statement = connection.createStatement();
        	     //      statement.executeUpdate(sqlstatut);
        	           
              showAlert("Succès", "Le client a été ajouté avec succès !");
             // renialiserAction();  // Réinitialiser après insertion
          }
      } catch (SQLException e) {
          showAlert("Erreur", "Impossible d'ajouter le client. Veuillez vérifier les informations.");
          e.printStackTrace();
      }
	}
	
	
	
	@FXML
	// Méthode pour calculer le nombre de jours entre la date de check-in et check-out
	public long jourTotal() {
		 LocalDate dateArrivee = inDate.getValue();  // Date de check-in
		    LocalDate dateDepart = outDate.getValue();  // Date de check-out
		
	    if (inDate.getValue() == null || outDate.getValue() == null) {
	        showAlert("Erreur", "Veuillez sélectionner des dates.");
	        return 0;
	    }

	    if (inDate.getValue().isAfter(outDate.getValue())) {
	        showAlert("Erreur", "La date de départ doit être après la date d'arrivée.");
	        return 0;
	    } else {
	        // Utilisez ChronoUnit.DAYS.between pour obtenir le nombre total de jours
	       int totalJours =  (int) ChronoUnit.DAYS.between(dateArrivee, dateDepart);
	        donneeclients.totaljours = totalJours;	     
	  	   totalJourLabel.setText("Total Jours: " + totalJours);

	//  	 montantTotal();
	        return totalJours;

	    }
	}

	@FXML
public double montantTotal() {
      //  donneeclients.totaljours = totalJours;	     

		//jourTotal();
    // Calculer le nombre total de jours
  //  long totalJours = jourTotal();
 //   if (totalJours == 0) {
   //     return 0; // Si le calcul des jours a échoué, on arrête la méthode
  //  }

    // Récupérer l'ID de la chambre sélectionnée
    String item2 = (String)idchambre.getSelectionModel().getSelectedItem();

//    String sql = "SELECT 'prixchambre' FROM chambre WHERE 'statutchambre' = 'disponible' AND idchambre = ?";
    String sql ="  SELECT * FROM `chambre` WHERE `idchambre`=? ";
    double prix = 0;
    connection = SingleConnection.getConnection();
    System.out.print("le prix ne peut pasetre recuperrrrrre");

    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, item2); // Utilisation d'une requête paramétrée pour éviter les injections SQL
   //     pst.setInt(1, Integer.parseInt(item2)); // Conversion de 'item2' en Integer pour la requête SQL

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            prix = rs.getDouble("prixchambre"); // Récupérer le prix de la chambre
        System.out.print("le prix ne peut pasetre recupere");
        }
        

        double montant =(float) (prix * donneeclients.totaljours); // Calculer le montant total
       // montantTotalLabel.setText("Total: " + totalp + " FCFA"); // Afficher le montant total
        System.out.print("le prix est ok"+ prix + montant);

        montantTotalLabel.setText("Montant Total: " + montant + " FCFA");
            System.out.print("le prix est ok");

        return montant;

    } catch (SQLException e) {
        showAlert("Erreur", "Impossible de calculer le montant total.");
        e.printStackTrace();
        return 0;
    }
}
	  
	
	// Méthode pour calculer le montant total

	

	@FXML
	public void afficherMontantTotal() {
	   long totalJours = jourTotal();
	  double montant = montantTotal();
		// Exemple pour afficher dans un label
	    totalJourLabel.setText("Total Jours: " + totalJours);
		montantTotalLabel.setText("Montant Total: " + montant + " FCFA");

	   showAlert("Information", "Total jours: " + totalJours + "\nMontant total: " + montant + " FCFA");
	//   jourTotal();montantTotal();
	}

	
	
	//////////////////////////////////////
	@FXML
	private ComboBox<String> typechambre;

	@FXML
	private ComboBox<String> idchambre;

	public void remplirTypesChambres() {
	    String sql = "SELECT *from chambre where `statutchambre`='Disponible' GROUP BY typechambre ORDER BY typechambre ASC";
	
	   // String sql = " SELECT DISTINCT typechambre FROM chambre";

	    connection=SingleConnection.getConnection();
	    try {
	    	ObservableList listdata= FXCollections.observableArrayList();
	        PreparedStatement pst = connection.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            listdata.add(rs.getString("typechambre"));
	            
	        }typechambre.setItems(listdata);
	        remplirNumerosChambres();
	    } catch (SQLException e) {
	        showAlert("Erreur", "Impossible de charger les types de chambre.");
	        e.printStackTrace();
	    }
	}

	
	
	
	@FXML
	public void remplirNumerosChambres() {	

		
	   String item = (String)typechambre.getSelectionModel().getSelectedItem();

	   String sql = "SELECT *from  chambre WHERE  `statutchambre` = 'disponible' AND typechambre ='"+item+"' " ;

	
	    connection=SingleConnection.getConnection();
	    
	    
	    try {
	    	ObservableList listdata= FXCollections.observableArrayList();
	        PreparedStatement pst = connection.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            listdata.add(rs.getString("idchambre"));
	            
	        }idchambre.setItems(listdata);
	      
	    } catch (SQLException e) {
	        showAlert("Erreur", "Impossible de charger les numéros de chambre.");
	        e.printStackTrace();
	    }
	}


	@FXML
	public void factureAction(ActionEvent event) {
	    try {
	        // Charger le fichier FXML de la facture
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/facture.fxml")); // Mettez le bon chemin
	        Parent checkInPage = loader.load();
	        
	        // Obtenir le contrôleur de la facture
	        factureController factureCtrl = loader.getController();

	        // Passer les données du check-in au contrôleur de la facture
	        factureCtrl.setdonneeclients(id_client.getText(),cName.getText(), cEmail.getText(), cPhone.getText(), cadresse.getText(), 
	                                  inDate.getValue(), outDate.getValue(), typechambre.getValue(), idchambre.getValue(), montantTotal());


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
	        showAlert("Erreur", "Impossible d'ouvrir la facture.");
	    }
	}

			
	
	
	
	
	
	
}  


