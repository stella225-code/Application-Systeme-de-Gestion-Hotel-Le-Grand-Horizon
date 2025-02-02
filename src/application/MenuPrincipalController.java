package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;
import java.time.LocalDate;

import java.sql.*;
import java.time.LocalDate;

/*import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/
import java.util.Date;
import java.util.LinkedHashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuPrincipalController implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		

		// TODO Auto-generated method stub
	//	tableaubordcomptelivretoday() ;
		  voirlistdonneeemployee();
		  tableemployeditable();
		  rechercherEmploye();
		  
	
		//voirlistdonneeclient(); // Remplir la table

	    //voirlistdonneeclient();
		 voirlistdonneeclient();
		 tableclienteditable();rechercheClient();		 
		  afficherRevenuTotal(); 
		  afficherRevenuJournalier(); 
		  afficherNombreReservationsJournalier();
		  
		  voirlistdonnee();
		  	chambreAjouter();
			chambresupprimer();
			typechambre();statutchambres();
			selectiolisdonnechambre();
			chambresupp(); rechercheChambre();
	//		afficherStatistiquesChambres() ;
  
	 afficherRevenusParJour();  // Afficher les revenus journaliers automatiquement
	afficherNombreReservationsJournalier();
	afficherRevenuJournalier();
	afficherRevenuTotal();
	
		  afficherNomUtilisateurDepuisBD();
		  
		
		  afficherTauxOccupationChambres();
		    afficherRevenusParmois();
		    afficherReservationsHebdomadaires();
			 afficherStatistiquesChambres();
			 setupAutoRefresh() ;
			 afficherDateHeureActuelle();
			 chargerImageDepuisBD();
   
		
	}

	//------------------------permet d'activer lorquon tour le boutton tableau bord
	@FXML
	public void onTableauDeBordButtonClicked() {
	    // Rafraîchit les statistiques en temps réel lorsque le bouton du tableau de bord est cliqué.
	    afficherTauxOccupationChambres();
	    afficherRevenusParmois();
	    afficherReservationsHebdomadaires();
	    afficherStatistiquesChambres();
	    afficherRevenusParJour();
	    afficherNombreReservationsJournalier();
	    afficherRevenuJournalier();
	    afficherRevenuTotal();
	    setupRealTimeClock();
	}

	// Méthode pour mettre à jour les statistiques automatiquement en temps réel
@FXML
	private void setupAutoRefresh() {
	    // Exemple avec un Timer, actualise toutes les 30 secondes
	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        
	        public void run() {
	            Platform.runLater(() -> {
	                // Rafraîchit les statistiques régulièrement
	                afficherTauxOccupationChambres();
	                afficherRevenusParmois();
	                afficherReservationsHebdomadaires();
	                afficherStatistiquesChambres();
	                afficherRevenusParJour();
	                afficherNombreReservationsJournalier();
	                afficherRevenuJournalier();
	                afficherRevenuTotal();
	            });
	        }
	    }, 0, 30000); // Mise à jour toutes les 30 secondes
	}

@FXML
private Label dateTimeLabel; // Assurez-vous de lier ce Label depuis SceneBuilder ou par code

private void afficherDateHeureActuelle() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    dateTimeLabel.setText(now.format(formatter));
}
	
private void setupRealTimeClock() {
    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> afficherDateHeureActuelle()),
            new KeyFrame(Duration.seconds(1)));
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
}

	
	
	
	
	
	
	
	
	
	
	
	

	// -----------------------------------------------------------------------------------
	// ----------------------------CHAMBRES--------------------------------------------------

	
	@FXML
	private StackPane root1;
	@FXML
	private Button accueil;

	@FXML
	private Button ajouterchambre;

	@FXML
	private Button categorie;

	@FXML
	private Button chambre;
	@FXML
	private Button chambres;

	@FXML
	private Button client;

	@FXML
	private Button close;
	@FXML
	private Button deconnection;
	@FXML
	private Button fermer;
	@FXML
	private Pane inner_pane11;

	@FXML
	private Pane inner_pane111;

	@FXML
	private Pane inner_pane1111;

	@FXML
	private Button map;
	@FXML
	private Button parametre;
	@FXML
	private TextField recherche;

	@FXML
	private ImageView reduire;

	@FXML
	private HBox root11;

	@FXML
	private Button services;

	@FXML
	private AnchorPane side_ankerpane1;

	// -----------------------------------------------CHAMBRE.FXML

	@FXML
	private AnchorPane chambredispo;

	@FXML
	private TableColumn<donneechambre, String> colidchambre;

	@FXML
	private TableColumn<donneechambre, String> colprixchambre;

	@FXML
	private TableColumn<donneechambre, String> colstatutchambre;

	// @FXML
	// private TableColumn<donneechambre, String> colstatutchambre;

	@FXML
	private TableColumn<donneechambre, String> coltypechambre;

	@FXML
	private TextField idchambre;

	@FXML
	private TableView<donneechambre> tabledonneechambre;
	@FXML
	private Button enregistrerchambre;
	@FXML
	private Button modifierchambre;

	@FXML
	private Button actualiserchambre;
	@FXML
	private TextField prixchambre;
	@FXML
	private TextField recherchechambre;

	@FXML
	private ComboBox<?> statutchambre;

	// ----------------------------------EMPLOYES.FXML
//	@FXML
//	private TextField rechercheemploye;
	@FXML
	private Button employe;

	@FXML
	private Button employes;

//	@FXML
//	private TableColumn<?, ?> enregistreremploye;

	//@FXML
//	private TableColumn<?, ?> idemploye;

//	@FXML
//	private TableColumn<?, ?> nomemploye;

//@FXML
//	private TableColumn<?, ?> prenomemploye;

	@FXML
	private Button supprimerchambre;

	@FXML
	private AreaChart<?, ?> tabdonnee;

	@FXML
	private AnchorPane tabform;

	@FXML
	private AnchorPane tableemploye;

	@FXML
	private Label tablivre;

	@FXML
	private Label tabrev;

	@FXML
	private Label tabrevtotal;

	//@FXML
//	private TableColumn<?, ?> telemploye;

	@FXML
	private Pane text_recherche1;

	//@FXML
//	private TableColumn<?, ?> totalpayer;

	@FXML
	private ComboBox<?> typechambre;

	@FXML
	private Label username;

//	@FXML
//	private TableColumn<?, ?> verifieremploye;

	@FXML
	private Button deconnecter;

	
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
    
    
	private boolean fieldsAreValid() {
		return !(idchambre.getText().isEmpty() || typechambre.getSelectionModel().isEmpty()
				|| statutchambre.getSelectionModel().isEmpty() || prixchambre.getText().isEmpty());
	}

	private void clearFields() {
		idchambre.setText("");
		typechambre.setPromptText("");
		statutchambre.setPromptText("");
		prixchambre.setText("");
	}

	private static Connection connection = SingleConnection.getConnection();

	private static PreparedStatement pst = null; // Objet pour exécuter les requêtes SQL préparées
	private static Statement stm = null;
	private static ResultSet res = null; // Objet pour stocker les résultats des requêtes SQL

	// ----------------------------------CLASSE PRINCIPALE
	public ObservableList<donneechambre> donneechambres() {
		ObservableList<donneechambre> listdonneechambre = FXCollections.observableArrayList();
		String sql = "select*from chambre";
		connection = SingleConnection.getConnection();

		try {
			donneechambre Dchambre;

			PreparedStatement pst = connection.prepareStatement(sql);// Préparer la requête SQL

			res = pst.executeQuery();

			while (res.next()) {
				Dchambre = new donneechambre(res.getInt("idchambre"), res.getString("typechambre"),
						res.getString("statutchambre"), res.getDouble("prixchambre"));

				listdonneechambre.add(Dchambre);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return listdonneechambre;

	}

	// ---------------------------------------VOIR LE TABLEAU
	private ObservableList<donneechambre> chambrelistdonnee;

	public void voirlistdonnee() {


		chambrelistdonnee = donneechambres();

		colidchambre.setCellValueFactory(new PropertyValueFactory<>("idchambre"));
		coltypechambre.setCellValueFactory(new PropertyValueFactory<>("typechambre"));
		colstatutchambre.setCellValueFactory(new PropertyValueFactory<>("statutchambre"));
		colprixchambre.setCellValueFactory(new PropertyValueFactory<>("prixchambre"));

		tabledonneechambre.setItems(chambrelistdonnee);
	}

	// ------------------------------ MEHODE POUR LA SELECTION
	public void selectiolisdonnechambre() {

		donneechambre Dchambre = tabledonneechambre.getSelectionModel().getSelectedItem();
		int num = tabledonneechambre.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			return;
		}

		idchambre.setText(String.valueOf(Dchambre.getIdchambre()));
		prixchambre.setText(String.valueOf(Dchambre.getPrixchambre()));
	}

	// -------------------------FAIRE LA LISTE DES CHAMBRES
	private static String typechambres[] = { "Chambre Simple ", "Chambre Double", "Chambre Triple", "Chambre Famillial",
			"Chambre Royal" };

	public void typechambre() {
		List<String> listdonneechambre = new ArrayList<>();
		for (String donnee : typechambres) {
			listdonneechambre.add(donnee);
		}
		ObservableList list = FXCollections.observableArrayList(listdonneechambre);
		typechambre.setItems(list);
	}

	// ------------------------------------------FAIRE LA LISTE DES STATUS DE
	// CHAMBRE
	private static String statutchambres[] = { "Disponible ", "Non Disponible", "Occupé" };

	public void statutchambres() {
		List<String> listdonneechambre = new ArrayList<>();
		for (String donnee : statutchambres) {
			listdonneechambre.add(donnee);
		}
		ObservableList list = FXCollections.observableArrayList(listdonneechambre);
		statutchambre.setItems(list);
	}

	// -----------------------------------------METHODE DECONNECTER

	private double x = 0;
	private double y = 0;

	public void deconnexion() {

		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "vous etes sur de vouloir vous deconnecter?",
					ButtonType.OK);
			Optional<ButtonType> option = alert.showAndWait();
			alert.show();

			if (option.get().equals(ButtonType.OK)) {

				Parent root = FXMLLoader.load(getClass().getResource("HoteloranaisSystemDeGestion.fxml"));

				Stage stage = new Stage();
				Scene scene = new Scene(root);

				root.setOnMousePressed((MouseEvent event) -> {
					x = event.getSceneX();
					y = event.getSceneY();

				});

				root.setOnMouseDragged((MouseEvent event) -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
				});

				stage.initStyle(StageStyle.TRANSPARENT);
				stage.setScene(scene);
				stage.show();

				deconnecter.getScene().getWindow().hide();

			} else {
				return;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	


	// --------------------------------------------------------METHODE FERMER
	public void close() {
		System.exit(0);
	}

	// -----------------------------------------MEHTHODE REDUIRE FENETRE
	public void minimize() {
		Stage stage = (Stage) root1.getScene().getWindow();
		stage.setIconified(true);
	}
	//--------------------------------barre recherche
	public void rechercheChambre() {
	    // Créer un FilteredList en fonction de la liste de clients
	    FilteredList<donneechambre> filteredListc = new FilteredList<>(chambrelistdonnee, b -> true);

	    // Ajouter un listener à la barre de recherche
	    recherchechambre.textProperty().addListener((observable, oldValue, newValue) -> {
	        filteredListc.setPredicate(client -> {
	            // Si la barre de recherche est vide, afficher tous les clients
	            if (newValue == null || newValue.isEmpty()) {
	                return true;
	            }

	            // Mettre le texte de la barre de recherche en minuscule pour la comparaison
	            String lowerCaseFilter = newValue.toLowerCase();

	            // Comparer le nom, le prénom, et d'autres champs si nécessaire
	            if (client.getTypechambre().toLowerCase().contains(lowerCaseFilter)) {
	                return true; // Nom correspond
	            } else if (client.getStatutchambre().toLowerCase().contains(lowerCaseFilter)) {
	                return true; // Prénom correspond
	            } else if (client.getIdchambre().toString().contains(lowerCaseFilter)) {
	                return true; // Téléphone correspond
	            }else
	            // Ajouter d'autres conditions selon vos besoins
	            return false; // Ne correspond pas
	        });
	    });

	    // Créer un SortedList avec le FilteredList
	    SortedList<donneechambre> sortedList = new SortedList<>(filteredListc);

	    // Lier le SortedList à la TableView
	    sortedList.comparatorProperty().bind(tabledonneechambre.comparatorProperty());

	    // Appliquer les données triées et filtrées à la TableView
	    tabledonneechambre.setItems(sortedList);
	}

	

		// -------------------------------------------METHODE EFFACER
	public void chambresupprimer() {

		idchambre.setText("");
		typechambre.getSelectionModel().clearSelection();
		statutchambre.getSelectionModel().clearSelection();
		prixchambre.setText("");
		clearFields();

	}

	// -----------------------------------------------FONCTION AJOUTER

	public void chambreAjouter() {
		String sql = "insert into chambre(idchambre ,typechambre,statutchambre,prixchambre) values (?, ? ,? ,?) ";

		try {
			PreparedStatement pst = connection.prepareStatement(sql);// Préparer la requête SQL
			pst.setString(1, idchambre.getText());
			pst.setString(2, (String) typechambre.getSelectionModel().getSelectedItem());
			pst.setString(3, (String) statutchambre.getSelectionModel().getSelectedItem());
			pst.setString(4, prixchambre.getText());

			System.out.println("Exécution de la requête SQL");
			pst.executeUpdate();
			if (idchambre.getText().isEmpty() || ((String) typechambre.getSelectionModel().getSelectedItem()).isEmpty()
					|| ((String) statutchambre.getSelectionModel().getSelectedItem()).isEmpty()
					|| prixchambre.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "Veillez remplir tout les champs ");
				alert.show();
				

			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Chambre ajouter avec success");
				alert.show();

				voirlistdonnee();
				idchambre.setText("");
				typechambre.setPromptText("");
				statutchambre.setPromptText("");
				prixchambre.setText("");

				clearFields();
				// chambresupp();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("préparation de la requête SQL est ok");

	}




	// -------------------------------------------------METHODE POUR MODIFIER
	public void chambrmodifier1() {
		String sql = "UPDATE chambre SET typechambre=?, statutchambre=?, prixchambre=? WHERE idchambre=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, (String) typechambre.getSelectionModel().getSelectedItem());
			pst.setString(2, (String) statutchambre.getSelectionModel().getSelectedItem());
			pst.setString(3, prixchambre.getText());
			pst.setString(4, idchambre.getText());

			if (fieldsAreValid()) {
				pst.executeUpdate();
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Chambre modifiée avec succès !");
				alert.show();

				voirlistdonnee();
				clearFields();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs !");
				alert.show();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la modification : " + e.getMessage());
			alert.show();
		}
	}

	// --------------------------------------------FONCTION SUPPRIMER

	public void chambresupp() {
		String sql = "DELETE FROM chambre WHERE idchambre=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idchambre.getText());

			if (!idchambre.getText().isEmpty()) {
				pst.executeUpdate();
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Chambre supprimée avec succès !");
				alert.show();

				voirlistdonnee();
				clearFields();
			} else {
				/*//Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner une chambre à supprimer !",
				//		ButtonType.OK);
				//alert.show();*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la suppression : " + e.getMessage());
			alert.show();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	//-----------------------------------PAGE ENGEGISTREMENT------------------------------------------------
	
	//----------------------------------------METHODE POUR OUVRIRE LE CHECKIN
	
	// Méthode pour ouvrir la page Check-in
    @FXML
    public void openCheckInPage(ActionEvent event) {
        try {
            // Charger le fichier FXML du Check-in
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/checkin.fxml"));
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
            // Optionnel : Afficher un message d'erreur en cas d'échec de chargement du fichier FXML
            showAlert("Erreur", "Impossible d'ouvrir la page Check-in.");
        }
    }

   
    
    
    
    
    
    
    
    
    
    
    
    
    
 //----------------------------------PAGE CLIENTS------------------------------------------------------   
    
    //--------------------------------------------------------------------------------------
 

 // ----------------------------------CLASSE PRINCIPALE
 
  //  public class ClientController {

        @FXML
        private TableView<donneeclients> tabledonneeclient;

        @FXML
        private TableColumn<donneeclients, Integer> idclient;
        @FXML
        private TableColumn<donneeclients, String> nomclient;
        @FXML
        private TableColumn<donneeclients, String> prenomclient;
        @FXML
        private TableColumn<donneeclients, String> telclient;
        @FXML
        private TableColumn<donneeclients, Double> totalpayer;
        @FXML
        private TableColumn<donneeclients, String> datearriverclient;
        @FXML
        private TableColumn<donneeclients, String> datesortirclient;
        @FXML
        private TableColumn<donneeclients, String> adresseclient;
        @FXML
        private TableColumn<donneeclients, String> genreclient;
    //    @FXML
    //    private TableColumn<donneeclients, Integer> numeroChambre;

        @FXML
        private TextField rechercheclient;

        private ObservableList<donneeclients> clientList = FXCollections.observableArrayList();
        
        
        //----------------------------------classe client
        
    	public ObservableList<donneeclients> donneeclients() {
     		ObservableList<donneeclients> listdonneeclient = FXCollections.observableArrayList();
     		String sql = "SELECT * FROM `clients`";
     		connection = SingleConnection.getConnection();

     		try {
     			donneeclients Dclient;

     			PreparedStatement pst = connection.prepareStatement(sql);// Préparer la requête SQL

     			res = pst.executeQuery();

     			while (res.next()) {
     				Dclient = new donneeclients(
     						res.getInt("id_client"),
                            res.getString("nom"),
                            res.getString("prenom"),
                            res.getString("telephone"),
                            res.getDouble("montanttotal"),
                            res.getString("checkin"),
                            res.getString("checkout"),
                            res.getString("adresse"),
                            res.getString("genre"), sql);
                       

     				listdonneeclient.add(Dclient);
     			}

     		} catch (Exception e) {
     			// TODO: handle exception
     		}return listdonneeclient;

    	}
    	
    	//----------------------------------------voir la liste
    	
    	private ObservableList<donneeclients> clientlistdonnee;

    	public void voirlistdonneeclient() {
    	    clientlistdonnee = donneeclients();
    	    System.out.println("Début de la méthode afficherTotalChambres");


    	    // Lier les colonnes aux propriétés de la classe Client
    	    idclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
    	    nomclient.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	    prenomclient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	    telclient.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    	    totalpayer.setCellValueFactory(new PropertyValueFactory<>("montanttotal"));
    	    datearriverclient.setCellValueFactory(new PropertyValueFactory<>("checkin"));
    	    datesortirclient.setCellValueFactory(new PropertyValueFactory<>("checkout"));
    	    adresseclient.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    	    genreclient.setCellValueFactory(new PropertyValueFactory<>("genre"));
    	 //   numeroChambre.setCellValueFactory(new PropertyValueFactory<>("numero_client"));

    	    tabledonneeclient.setItems(clientlistdonnee);
    	}

    	//-----------------------------------------------------------rendre le tableau editable
    	

@FXML
public void tableclienteditable() {
    voirlistdonneeclient(); // Remplir la table

    // Rendre les colonnes éditables
    tabledonneeclient.setEditable(true);

    // Nom client éditable
    nomclient.setCellFactory(TextFieldTableCell.forTableColumn());
    nomclient.setOnEditCommit(event -> {
        donneeclients client = event.getRowValue();
        client.setNom(event.getNewValue());
        updateClientInDatabase(client); // Mettre à jour la base de données après l'édition
    });

    // Prénom client éditable
    prenomclient.setCellFactory(TextFieldTableCell.forTableColumn());
    prenomclient.setOnEditCommit(event -> {
        donneeclients client = event.getRowValue();
        client.setPrenom(event.getNewValue());
        updateClientInDatabase(client); // Mettre à jour la base de données après l'édition
        
        
    });
    
    // Prénom client éditable
    telclient.setCellFactory(TextFieldTableCell.forTableColumn());
    telclient.setOnEditCommit(event -> {
        donneeclients client = event.getRowValue();
        client.setTelephone(event.getNewValue());
        updateClientInDatabase(client); // Mettre à jour la base de données après l'édition
        
        
    });
   
    // Prénom client éditable
    datearriverclient.setCellFactory(TextFieldTableCell.forTableColumn());
    datearriverclient.setOnEditCommit(event -> {
        donneeclients client = event.getRowValue();
        client.setCheckin(event.getNewValue());
        updateClientInDatabase(client); // Mettre à jour la base de données après l'édition
        
        
    });
    // Prénom client éditable
    datesortirclient.setCellFactory(TextFieldTableCell.forTableColumn());
    datesortirclient.setOnEditCommit(event -> {
        donneeclients client = event.getRowValue();
        client.setCheckout(event.getNewValue());
        updateClientInDatabase(client); // Mettre à jour la base de données après l'édition
        
        
    });
    // Prénom client éditable
    adresseclient.setCellFactory(TextFieldTableCell.forTableColumn());
    adresseclient.setOnEditCommit(event -> {
        donneeclients client = event.getRowValue();
        client.setAdresse(event.getNewValue());
        updateClientInDatabase(client); // Mettre à jour la base de données après l'édition
        
        
    });
    // Prénom client éditable
    genreclient.setCellFactory(TextFieldTableCell.forTableColumn());
    genreclient.setOnEditCommit(event -> {
        donneeclients client = event.getRowValue();
        client.setGenre(event.getNewValue());
        updateClientInDatabase(client); // Mettre à jour la base de données après l'édition
        
        
    });
   
    
}
private void updateClientInDatabase(donneeclients client) {
    String sql = "UPDATE clients SET nom = ?, prenom = ? WHERE id_client = ?";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, client.getNom());
        pst.setString(2, client.getPrenom());
        pst.setInt(3, client.getId_client());
        pst.executeUpdate();
        showAlert("Succès", "Le client a été modifié avec succès !");
    } catch (SQLException e) {
        showAlert("Erreur", "Impossible de modifier le client.");
        e.printStackTrace();
    }
}
    	//-----------------------------------mehtode recherche
    	

    	// Méthode pour filtrer les clients en fonction de la recherche
    	public void rechercheClient() {
    	    // Créer un FilteredList en fonction de la liste de clients
    	    FilteredList<donneeclients> filteredList = new FilteredList<>(clientlistdonnee, b -> true);

    	    // Ajouter un listener à la barre de recherche
    	    rechercheclient.textProperty().addListener((observable, oldValue, newValue) -> {
    	        filteredList.setPredicate(client -> {
    	            // Si la barre de recherche est vide, afficher tous les clients
    	            if (newValue == null || newValue.isEmpty()) {
    	                return true;
    	            }

    	            // Mettre le texte de la barre de recherche en minuscule pour la comparaison
    	            String lowerCaseFilter = newValue.toLowerCase();

    	            // Comparer le nom, le prénom, et d'autres champs si nécessaire
    	            if (client.getNom().toLowerCase().contains(lowerCaseFilter)) {
    	                return true; // Nom correspond
    	            } else if (client.getGenre().toLowerCase().contains(lowerCaseFilter)) {
    	                return true; // Prénom correspond
    	            } else if (client.getTelephone().toLowerCase().contains(lowerCaseFilter)) {
    	                return true; // Téléphone correspond
    	            }else if (client.getCheckin().toLowerCase().contains(lowerCaseFilter)) {
    	                return true; // Téléphone correspond
    	            }else if (client.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
    	                return true; // Téléphone correspond
    	            }
    	            // Ajouter d'autres conditions selon vos besoins
    	            return false; // Ne correspond pas
    	        });
    	    });

    	    // Créer un SortedList avec le FilteredList
    	    SortedList<donneeclients> sortedList = new SortedList<>(filteredList);

    	    // Lier le SortedList à la TableView
    	    sortedList.comparatorProperty().bind(tabledonneeclient.comparatorProperty());

    	    // Appliquer les données triées et filtrées à la TableView
    	    tabledonneeclient.setItems(sortedList);
    	}



    	// Méthode pour afficher les détails du client sélectionné
    	public void afficherDetailsClient(donneeclients client) {
    	    if (client != null) {
    	        nomclient.setText(client.getNom());
    	        prenomclient.setText(client.getPrenom());
    	        // Afficher d'autres informations comme téléphone, adresse, etc.
    	    } else {
    	        // Si aucun client n'est sélectionné, efface les champs de texte
    	    //    nomclient.clear();
    	    //    prenomclient.clear();
    	        // Efface les autres champs
    	    }
    	}
    	
    	
    	//----------------------------------------------supprimer client
    	
    	@FXML
    	private void supprimerClient() {
    	    donneeclients clientSelectionne = tabledonneeclient.getSelectionModel().getSelectedItem();
    	    if (clientSelectionne != null) {
    	        String sql = "DELETE FROM clients WHERE id_client = ?";
    	        try {
    	            PreparedStatement pst = connection.prepareStatement(sql);
    	            pst.setInt(1, clientSelectionne.getId_client());

    	            pst.executeUpdate();
    	            tabledonneeclient.getItems().remove(clientSelectionne);  // Supprime le client de la TableView
    	            showAlert("Succès", "Le client a été supprimé avec succès !");
    	        } catch (SQLException e) {
    	            showAlert("Erreur", "Impossible de supprimer le client.");
    	            e.printStackTrace();
    	        }
    	    } else {
    	        showAlert("Erreur", "Veuillez sélectionner un client.");
    	    }
    	}

    	
    	//-------------------------------------------------------------enregistrer les modification client
    	
    	

        // Méthode pour modifier un client dans la base de données
        @FXML
        private void modifierClient() {
            donneeclients selectedClient = tabledonneeclient.getSelectionModel().getSelectedItem();

            if (selectedClient != null) {
                String sql = "UPDATE clients SET nom = ?, prenom = ?, telephone = ?, montanttotal = ?, checkin = ?, checkout = ?, adresse = ?, genre = ?, numero_client = ? WHERE id_client = ?";

                try {
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, selectedClient.getNom());
                    pst.setString(2, selectedClient.getPrenom());
                    pst.setString(3, selectedClient.getTelephone());
                    pst.setDouble(4, selectedClient.getMontanttotal());
                    pst.setString(5, selectedClient.getCheckin());
                    pst.setString(6, selectedClient.getCheckout());
                    pst.setString(7, selectedClient.getAdresse());
                    pst.setString(8, selectedClient.getGenre());
                    pst.setInt(9, selectedClient.getNumero_client());
                    pst.setInt(10, selectedClient.getId_client());

                    pst.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
  //----------------------------------BARRE DES MENUES-----------------------------------------
        //---------------------------------------------------------------
        
        
        
        
        //-----------------------------------------switer entre les anchorpane 
        @FXML // fx:id="autrebtn"
        private Button autrebtn; // Value injected by FXMLLoader

        @FXML // fx:id="chambreancho"
        private AnchorPane chambreancho; // Value injected by FXMLLoader

        @FXML // fx:id="chambrebtn"
        private Button chambrebtn; // Value injected by FXMLLoader
        
        @FXML // fx:id="clientancho"
        private AnchorPane clientancho; // Value injected by FXMLLoader

        @FXML // fx:id="clientbtn"
        private Button clientbtn; // Value injected by FXMLLoader
        
        @FXML // fx:id="employebtn"
        private Button employebtn; // Value injected by FXMLLoader
        @FXML
        private AnchorPane employeancho;
        @FXML // fx:id="resevationbtn"
        private Button resevationbtn; // Value injected by FXMLLoader
        
        @FXML // fx:id="tableaubordancho1"
        private Pane tableaubordancho1; // Value injected by FXMLLoader
        
        
        @FXML
        private Button creercomptebtn;

        @FXML
        private Button faqbtn;

        @FXML
        private Button contactbtn;

        @FXML
        private AnchorPane creercompteancho;

        @FXML
        private AnchorPane faqancho;

        @FXML
        private AnchorPane contactancho;


        @FXML // fx:id="tableaubordancho2"
        private AnchorPane tableaubordancho2; // Value injected by FXMLLoader

        @FXML // fx:id="tableaubordbtn"
        private Button tableaubordbtn; // Value injected by FXMLLoader
   
     
                
            
        public void switchform(ActionEvent event) {
            if (event.getSource() == tableaubordbtn) {
                tableaubordancho1.setVisible(true);
                chambreancho.setVisible(false);
                clientancho.setVisible(false);
                creercompteancho.setVisible(false);
                faqancho.setVisible(false);
                contactancho.setVisible(false);
                employeancho.setVisible(false);
                
                 

                tableaubordbtn.setStyle("    -fx-background-color:#f2f2f2");
                chambrebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                clientbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                creercomptebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                faqbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                contactbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                employebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");

                onTableauDeBordButtonClicked();
                afficherTauxOccupationChambres() ;
            } else if (event.getSource() == chambrebtn) {
                tableaubordancho1.setVisible(false);
                chambreancho.setVisible(true);
                clientancho.setVisible(false);
                creercompteancho.setVisible(false);
                faqancho.setVisible(false);
                contactancho.setVisible(false);
                employeancho.setVisible(false);


                chambrebtn.setStyle("    -fx-background-color:#f2f2f2");
                tableaubordbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                clientbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                creercomptebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                faqbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                contactbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                employebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");

                
                rechercheChambre();
                voirlistdonnee();
                
            } else if (event.getSource() == clientbtn) {
                tableaubordancho1.setVisible(false);
                chambreancho.setVisible(false);
                clientancho.setVisible(true);
                creercompteancho.setVisible(false);
                faqancho.setVisible(false);
                contactancho.setVisible(false);
                employeancho.setVisible(false);


                clientbtn.setStyle("-fx-background-color:#f2f2f2");
                tableaubordbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                chambrebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2);");
                creercomptebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                faqbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                contactbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                employebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");


                rechercheClient();
                voirlistdonneeclient();
                
            } else if (event.getSource() == creercomptebtn) {
                tableaubordancho1.setVisible(false);
                chambreancho.setVisible(false);
                clientancho.setVisible(false);
                creercompteancho.setVisible(true);
                faqancho.setVisible(false);
                contactancho.setVisible(false);
                employeancho.setVisible(false);


                creercomptebtn.setStyle("-fx-background-color:#f2f2f2");
                tableaubordbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                chambrebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                clientbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                faqbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                contactbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                employebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");

                
            } else if (event.getSource() == faqbtn) {
                tableaubordancho1.setVisible(false);
                chambreancho.setVisible(false);
                clientancho.setVisible(false);
                creercompteancho.setVisible(false);
                faqancho.setVisible(true);
                contactancho.setVisible(false);
                employeancho.setVisible(false);


                faqbtn.setStyle("-fx-background-color:#f2f2f2");
                tableaubordbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                chambrebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                clientbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                creercomptebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                contactbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                employebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");

                
            } else if (event.getSource() == contactbtn) {
                tableaubordancho1.setVisible(false);
                chambreancho.setVisible(false);
                clientancho.setVisible(false);
                creercompteancho.setVisible(false);
                faqancho.setVisible(false);
                contactancho.setVisible(true);
                employeancho.setVisible(false);


                contactbtn.setStyle("-fx-background-color:#f2f2f2");
                tableaubordbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                chambrebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                clientbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                creercomptebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                faqbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                employebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");

            }else if(event.getSource() == employebtn) {
                // Afficher la section Employé et masquer les autres
            	creercompteancho.setVisible(false);
                faqancho.setVisible(false);
                contactancho.setVisible(true);
                tableaubordancho1.setVisible(false);
                chambreancho.setVisible(false);
                clientancho.setVisible(false);
                employeancho.setVisible(true);

                // Changer le style des boutons
                employebtn.setStyle("-fx-background-color:#f2f2f2");
                tableaubordbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                chambrebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                clientbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                creercomptebtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                faqbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");
                contactbtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#540608,#FAB411 ,#f2f2f2)");


                // Méthode spécifique à la section Employé (ajoutez ici vos méthodes de gestion des employés)
                rechercherEmploye();
                voirlistdonneeemployee();

            } 
        }
        
        
        
      //public void couleurbouttondefaut() {
      //tableaubordbtn.setStyle("	-fx-background-color:#020202");

      //}     
        
        
        
        
        
        
        
        
        
        
        
        
        

        //---------------------------------------------TABLEAUBORD---------------------------------------------------
    //-----------------------------------------------------------------------------------------------

//------------------------------------------statistique revenu /total

@FXML
private Label labelRevenuTotal;  // Le label où afficher la somme des revenus

public void afficherRevenuTotal() {
    String sql = "SELECT SUM(prix_total) AS total_revenu FROM facture1";  // Requête SQL pour la somme
    double totalRevenu = 0;

    try {
        Connection connection = SingleConnection.getConnection();  // Connexion à la base de données
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            totalRevenu = rs.getDouble("total_revenu");  // Récupérer la somme
        }

        // Afficher la somme dans le label
        labelRevenuTotal.setText("Revenu Total: \n " + totalRevenu + " FCFA");

    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Erreur", "Impossible de calculer la somme des revenus.");
    }
}

//-------------------------------statistique revenu/jour

@FXML
private Label revenuJournalierLabel;  // Label dans lequel le revenu journalier sera affiché

// Méthode pour récupérer et afficher le revenu journalier
public void afficherRevenuJournalier() {
    String sql = "SELECT SUM(prix_total) AS revenu_journalier FROM facture1 WHERE DATE(date_facture) = CURDATE()";

    try {
        Connection connection = SingleConnection.getConnection();  // Connexion à la base de données
        PreparedStatement pst = connection.prepareStatement(sql);  // Préparer la requête SQL
        ResultSet rs = pst.executeQuery();  // Exécuter la requête

        if (rs.next()) {
            double revenuJournalier = rs.getDouble("revenu_journalier");  // Récupérer le résultat
            revenuJournalierLabel.setText(String.format("Revenu Journalier: \n %.2f FCFA", revenuJournalier));  // Afficher dans le label
        } else {
            revenuJournalierLabel.setText("Aucun revenu \n pour aujourd'hui.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Erreur", "Impossible de récupérer le revenu journalier.");
    }
}

//----------------------------------------statistique---dereservation /jour

@FXML
private Label nombreReservationsLabel;  // Label pour afficher le nombre de réservations

// Méthode pour récupérer et afficher le nombre de réservations journalières
public void afficherNombreReservationsJournalier() {
    String sql = "SELECT COUNT(*) FROM facture1 WHERE DATE(date_facture) = CURDATE()";
    int reservationsCount = 0;
    try {
        Connection connection = SingleConnection.getConnection();  // Connexion à la base de données
        PreparedStatement pst = connection.prepareStatement(sql);  // Préparer la requête SQL
        ResultSet rs = pst.executeQuery();  // Exécuter la requête

        if (rs.next()) {
             reservationsCount = rs.getInt(1);  // Récupérer le résultat
            nombreReservationsLabel.setText("Réservations Aujourd'hui: \n " + reservationsCount);  // Afficher dans le label
        } else {
            nombreReservationsLabel.setText("Aucune réservation \n aujourd'hui.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Erreur", "Impossible de récupérer  le nombre de réservations.");
    }
}


//-------------------------------STATISQUE NOMBRES DES STATUT CHAMBRE /JOUR

@FXML
private Label totalChambresLabel;
@FXML
private Label chambresDisponiblesLabel;
@FXML
private Label chambresOccupeesLabel;

public void afficherStatistiquesChambres() {
  String totalChambresSQL = "SELECT COUNT(*) AS total FROM chambre";
  String chambresDisponiblesSQL = "SELECT COUNT(*) AS disponibles FROM chambre WHERE statutchambre = 'disponible'";
  String chambresOccupeesSQL = "SELECT COUNT(*) AS occupees FROM chambre WHERE statutchambre = 'occupée'";

  try {
  	Connection connection = SingleConnection.getConnection(); 
      // Nombre total de chambres
      PreparedStatement totalChambresStmt = connection.prepareStatement(totalChambresSQL);
      ResultSet totalChambresRs = totalChambresStmt.executeQuery();
      
      if (totalChambresRs.next()) {
          totalChambresLabel.setText(String.valueOf(totalChambresRs.getInt("total")));
      }

      // Nombre de chambres disponibles
      PreparedStatement chambresDisponiblesStmt = connection.prepareStatement(chambresDisponiblesSQL);
      ResultSet chambresDisponiblesRs = chambresDisponiblesStmt.executeQuery();
      if (chambresDisponiblesRs.next()) {
          chambresDisponiblesLabel.setText(String.valueOf(chambresDisponiblesRs.getInt("disponibles")));
      }

      // Nombre de chambres occupées
      PreparedStatement chambresOccupeesStmt = connection.prepareStatement(chambresOccupeesSQL);
      ResultSet chambresOccupeesRs = chambresOccupeesStmt.executeQuery();
      if (chambresOccupeesRs.next()) {
          chambresOccupeesLabel.setText(String.valueOf(chambresOccupeesRs.getInt("occupees")));
      }
  } catch (SQLException e) {
      showAlert("Erreur", "Erreur lors de la récupération des statistiques des chambres.");
      e.printStackTrace();
  }
}
//---------------------------------------------- GRAPHIQUE OCCUPATION DES CHAMBRE


@FXML
private PieChart tauxOccupationChambresChart;
public void afficherTauxOccupationChambres() {

    // Exemple de données ; à adapter selon votre base de données
    try {Connection connection = SingleConnection.getConnection();
         PreparedStatement pst = connection.prepareStatement("SELECT statutchambre, COUNT(*) AS count FROM chambre GROUP BY statutchambre");
         ResultSet rs = pst.executeQuery(); 
    	
      ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        while (rs.next()) {
            String statut = rs.getString("statutchambre");
            int count = rs.getInt("count");
            chartData.add(new PieChart.Data(statut, count));
        }

        tauxOccupationChambresChart.setData(chartData);

    } catch (SQLException e) {
    	e.printStackTrace();
        showAlert("Erreur", "Impossible d'afficher le taux d'occupation des chambres.");
        
    }
    
    
}



//-----------------------------------------------GRAPHIQUE-REVENU PAR mois
@FXML
private BarChart<String, Number> barChartm;  // BarChart pour afficher les revenus par jour
@FXML
private CategoryAxis xAxi1;  // Axe des catégories (pour les dates)
@FXML
private NumberAxis yAxi2;    // Axe des nombres (pour les montants)

// Méthode pour afficher les revenus journaliers dans le BarChart
public void afficherRevenusParmois() {
//   String sql = "SELECT MONTH(date_facture) AS mois, SUM(prix_total) AS total_revenus FROM facture1 GROUP BY MOIS(date_facture) ORDER BY TIMESTAMP(date_facture) ASC LIMIT 8";
// String  sql="  SELECT MONTH(date_facture) AS mois, SUM(prix_total) AS revenus FROM facture1 GROUP BY MONTH(date_facture)";

	String sql=" SELECT MONTHNAME(date_facture) AS mois, SUM(prix_total) AS total_revenus\r\n"
			+ "FROM facture1\r\n"
			+ "WHERE YEAR(date_facture) = YEAR(CURDATE())\r\n"
			+ "GROUP BY MONTH(date_facture)\r\n"
			+ "ORDER BY MONTH(date_facture)";
	
   //  String sql= " SELECT checkin, montanttotal from clients group by checkin order by timestamp(checkin) asc limit 8";
    //SELECT DATE(checkin) AS date_jour, SUM(montanttotal) AS total_revenus FROM clients GROUP BY DATE(checkin) ORDER BY TIMESTAMP(checkin) ASC LIMIT 8;
    try {
        Connection connection = SingleConnection.getConnection();  // Connexion à la base de données
        PreparedStatement pst = connection.prepareStatement(sql);  // Préparer la requête SQL
        ResultSet rs = pst.executeQuery();  // Exécuter la requête

        // Créer une série de données pour le BarChart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Revenus par mois");

        // Parcourir les résultats de la requête et ajouter les données dans le BarChart
        while (rs.next()) {
            String mois = rs.getString("mois");  // Récupérer la date
            double Revenus = rs.getDouble("total_revenus");  // Récupérer les revenus
            series.getData().add(new XYChart.Data<>(mois, Revenus));  // Ajouter au BarChart
        }

        // Ajouter la série au BarChart
        barChartm.getData().clear(); // Assurez-vous de vider le BarChart avant d'ajouter de nouvelles données

        barChartm.getData().add(series);
        System.out.print("allo");

    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Erreur", "Impossible de récupérer les revenus.");
    }
}


//------------------------------------------------GRAPHIQUE RESEVATION PAR semaine


@FXML
private LineChart<String, Number> reservationsHebdoChart;
@FXML
private CategoryAxis xAxisReservations;  // Jours de la semaine
@FXML
private NumberAxis yAxisReservations;  // Nombre de réservations


public void afficherReservationsHebdomadaires() {
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Réservations Hebdomadaires");

    // Requête pour récupérer les réservations par jour de la semaine
  //  String sql = "SELECT DAYNAME(date_facture) AS jour, COUNT(*) AS total FROM facture WHERE WEEK(date_facture) = WEEK(CURRENT_DATE) GROUP BY DAYNAME(date_facture)";
  String sql=" SELECT DATE(date_facture) AS date, COUNT(*) AS nombre_reservations\r\n"
  		+ "FROM facture1\r\n"
  		+ "WHERE date_facture >= DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) + 7 DAY)\r\n"
  		+ "GROUP BY DATE(date_facture)\r\n"
  		+ "ORDER BY DATE(date_facture)";
  
    try {Connection connection = SingleConnection.getConnection();
         PreparedStatement pst = connection.prepareStatement(sql);
         ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String jour = rs.getString("date");
            int total = rs.getInt("nombre_reservations");
            series.getData().add(new XYChart.Data<>(jour, total));
        }

        reservationsHebdoChart.getData().clear();
        reservationsHebdoChart.getData().add(series);

    } catch (SQLException e) {
        showAlert("Erreur", "Impossible d'afficher les réservations hebdomadaires.");
        e.printStackTrace();
    }
}


//------------------------------------GRAPHIQUE REVENUE PAR semaine

@FXML
private BarChart<String, Number> barChartRevenusjour;  // BarChart pour afficher les revenus par jour
@FXML
private CategoryAxis xAxis;  // Axe des catégories (pour les dates)
@FXML
private NumberAxis yAxis;    // Axe des nombres (pour les montants)

// Méthode pour afficher les revenus journaliers dans le BarChart
public void afficherRevenusParJour() {
  // String sql = "SELECT DATE(date_facture) AS date_jour, SUM(prix_total) AS total_revenus FROM facture1 GROUP BY DATE(date_facture) ORDER BY TIMESTAMP(date_facture) ASC LIMIT 8";
  //  String sql= " SELECT checkin, montanttotal from clients group by checkin order by timestamp(checkin) asc limit 8";
    //SELECT DATE(checkin) AS date_jour, SUM(montanttotal) AS total_revenus FROM clients GROUP BY DATE(checkin) ORDER BY TIMESTAMP(checkin) ASC LIMIT 8;
String sql="SELECT DATE(date_facture) AS date, SUM(prix_total) AS total_revenus\r\n"
		+ "FROM facture1\r\n"
		+ "WHERE date_facture >= DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) + 7 DAY)\r\n"
		+ "GROUP BY DATE(date_facture)\r\n"
		+ "ORDER BY DATE(date_facture)";
	
	try {
        Connection connection = SingleConnection.getConnection();  // Connexion à la base de données
        PreparedStatement pst = connection.prepareStatement(sql);  // Préparer la requête SQL
        ResultSet rs = pst.executeQuery();  // Exécuter la requête

        // Créer une série de données pour le BarChart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Revenus par Jour");

        // Parcourir les résultats de la requête et ajouter les données dans le BarChart
        while (rs.next()) {
            String dateJour = rs.getString("date");  // Récupérer la date
            double totalRevenus = rs.getDouble("total_revenus");  // Récupérer les revenus
            series.getData().add(new XYChart.Data<>(dateJour, totalRevenus));  // Ajouter au BarChart
        }

        // Ajouter la série au BarChart
        barChartRevenusjour.getData().clear();
        barChartRevenusjour.getData().add(series);

    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Erreur", "Impossible de récupérer les revenus.");
    }
}

//-----------------------------------------rafraichir













//------------------------------------PROFIL--------------------------

//---------------------------------- PHOTO ET NOM -----------------------


//------------------------------------------------------aficher nom user
@FXML
private Label labelNomUtilisateur;  // Le label pour afficher le nom de l'utilisateur

public void afficherNomUtilisateurDepuisBD() {
    String sql = "SELECT email FROM user WHERE id = ?";  // Remplacez 'id' par l'ID de l'utilisateur connecté

    try {
        Connection connection = SingleConnection.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, donneeclients.getId_client());  // Récupérer l'ID utilisateur de la session
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String nomUtilisateur = rs.getString("email");
            labelNomUtilisateur.setText("Utilisateur Connecté : \n"  + nomUtilisateur);
        } else {
            labelNomUtilisateur.setText("Utilisateur Connecté \n: Inconnu");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        labelNomUtilisateur.setText("Erreur lors de la récupération du nom");
    }
}



//-------------------------------------TEELCHARGER IMAGE--------------------------------------------------------


@FXML
private ImageView imageProfil; // ImageView pour afficher l'image de profil

@FXML
private Button boutonChargerImage; // Bouton pour télécharger une image

private File selectedFile; // Fichier sélectionné pour l'image de profil

// Méthode pour charger l'image de profil via un FileChooser
@FXML
public void chargerImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
    );

    // Ouvrir la boîte de dialogue pour sélectionner un fichier image
    selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        Image image = new Image(selectedFile.toURI().toString());
        imageProfil.setImage(image); // Afficher l'image dans l'ImageView

        // Sauvegarder l'image dans la base de données
        try {
            sauvegarderImageDansBD(selectedFile);
        } catch (IOException | SQLException e) {
            showAlert("Erreur", "Impossible de sauvegarder l'image de profil.");
            e.printStackTrace();
        }
    }
}

/**
 * Sauvegarde l'image de profil dans la base de données pour l'utilisateur connecté.
 *
 * @param file Le fichier image sélectionné.
 * @throws IOException  Si une erreur d'entrée/sortie survient.
 * @throws SQLException Si une erreur de base de données survient.
 */
private void sauvegarderImageDansBD(File file) throws IOException, SQLException {
    // Convertir l'image en tableau de bytes
    byte[] imageBytes = Files.readAllBytes(file.toPath());

    // Connexion à la base de données
    Connection connection = SingleConnection.getConnection();
    String updateImageSQL = "UPDATE user SET photo_Profil = ? WHERE id = ?"; // Adaptez 'id' selon votre structure de table

    // Remplacez 'userId' par l'identifiant de l'utilisateur connecté
    try (PreparedStatement pst = connection.prepareStatement(updateImageSQL)) {
        pst.setBytes(1, imageBytes);
        pst.setInt(2, getUserId()); // Remplacez getUserId() par la méthode pour obtenir l'ID de l'utilisateur actuel
        pst.executeUpdate();
    }
}

/**
 * Charge l'image de profil depuis la base de données pour l'utilisateur connecté.
 */
public void chargerImageDepuisBD() {
    try {
        Connection connection = SingleConnection.getConnection();
        String query = "SELECT photo_Profil FROM user WHERE id = ?"; // Adaptez 'id' selon votre structure de table
        
        // Remplacez 'userId' par l'identifiant de l'utilisateur connecté
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, getUserId()); // Remplacez getUserId() par la méthode pour obtenir l'ID de l'utilisateur actuel
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("photo_Profil");
                if (imageBytes != null) {
                    // Convertir les bytes en Image
                    Image image = new Image(new ByteArrayInputStream(imageBytes));
                    imageProfil.setImage(image); // Afficher l'image dans l'ImageView
                }
            }
        }
    } catch (SQLException e) {
        showAlert("Erreur", "Impossible de charger l'image de profil.");
        e.printStackTrace();
    }
}



/**
 * Méthode simulée pour obtenir l'ID de l'utilisateur actuellement connecté.
 * Remplacez ceci par votre logique d'obtention de l'utilisateur.
 */
private int getUserId() {
    // Remplacer cette ligne par la récupération de l'ID utilisateur connecté
    return 1; // Exemple, à ajuster selon votre logique de connexion
}


































//-------------------------------CREATION DE-COMPTE -UTILISATEUR-------------------------------------------------------
//----------------------------------------------------------------------------------------------


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

//private Connection connection;


  

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


	   
	   /**
	    * Vérifie et applique les conditions d'accès en fonction du rôle de l'utilisateur connecté.
	    * Cette méthode doit être appelée après la connexion réussie de l'utilisateur.
	    *
	    * @param email L'email de l'utilisateur pour identifier le compte dans la base de données.
	    */
/**
 * Limite l'accès aux fonctionnalités en fonction du rôle de l'utilisateur connecté.
 * Cette méthode doit être appelée après la connexion réussie de l'utilisateur.
 * @param role2 
 */
public void limiterAcces(HoteloranaisSystemDeGestionController controller) {
    // Récupérer l'email saisi par l'utilisateur dans le champ usertfd du contrôleur de connexion
    String email2 = email.getText();

    // Vérifier que l'email n'est pas vide avant de continuer
    if (email2.isEmpty()) {
        System.out.println("Erreur : l'email n'a pas été fourni.");
        return; // Arrêter la méthode si l'email est vide
    }

    // Requête SQL pour récupérer le rôle de l'utilisateur en fonction de son email
    String sql = "SELECT role FROM user WHERE id = ?";

    try {
        // Préparer la requête SQL
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, email2); // Associer l'email à la requête
        ResultSet res = pst.executeQuery();

        // Vérifier si un résultat est trouvé
        if (res.next()) {
            // Récupérer le rôle de l'utilisateur connecté
            String role = res.getString("role");

            // Appliquer les conditions d'accès en fonction du rôle
            switch (role.toLowerCase()) {
                case "client":
                    appliquerRestrictionsClient();
                    break;

                case "administrateur":
                    appliquerPermissionsAdministrateur();
                    break;

                case "personnel":
                    appliquerRestrictionsPersonnel();
                    break;

                default:
                    // Cas où le rôle n'est pas reconnu
                    System.out.println("Rôle inconnu : " + role);
                    break;
            }
        } else {
            // Cas où aucun utilisateur n'est trouvé avec cet email
            System.out.println("Erreur : utilisateur non trouvé.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur lors de la récupération du rôle.");
    }
}

/**
 * Applique les restrictions spécifiques pour un utilisateur de type Client.
 */
private void appliquerRestrictionsClient() {
    creercomptebtn.setDisable(true);
    clientbtn.setDisable(true);
    tableaubordbtn.setDisable(true);
    chambrebtn.setDisable(true);
    employebtn.setDisable(true);

    faqbtn.setDisable(false);
    contactbtn.setDisable(false);
    resevationbtn.setDisable(false);

    System.out.println("Accès limité : fonctionnalités d'administration désactivées pour le Client.");
}

/**
 * Applique les permissions complètes pour un utilisateur de type Administrateur.
 */
private void appliquerPermissionsAdministrateur() {
    creercomptebtn.setDisable(false);
    clientbtn.setDisable(false);
    tableaubordbtn.setDisable(false);
    chambrebtn.setDisable(false);
    faqbtn.setDisable(false);
    contactbtn.setDisable(false);
    employebtn.setDisable(false);
    resevationbtn.setDisable(false);

    System.out.println("Accès complet pour l'Administrateur.");
}

/**
 * Applique les restrictions spécifiques pour un utilisateur de type Personnel.
 */
private void appliquerRestrictionsPersonnel() {
    creercomptebtn.setDisable(true);
    clientbtn.setDisable(false);
    tableaubordbtn.setDisable(false);
    chambrebtn.setDisable(false);
    employebtn.setDisable(false);

    faqbtn.setDisable(false);
    contactbtn.setDisable(false);
    resevationbtn.setDisable(false);

    System.out.println("Accès restreint pour le Personnel : accès limité aux fonctions administratives.");
}



// Exemple d'utilisation de la méthode dans votre flux de connexion ou de création de compte
public void handleLogin() {
    // Supposons que le rôle soit déterminé après une connexion ou une validation de compte
    String role = getRole(); // Récupère le rôle de l'utilisateur connecté
  //  limiterAcces(role); // Applique les restrictions en fonction du rôle
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
















//----------------------------------CONTACTE-------------------------------------------
//----------------------------------------------------------------------------


@FXML
private TextField nomField;
@FXML
private TextField emailField;
@FXML
private TextField sujetField;
@FXML
private TextArea messageField;
@FXML
private Button envoyerButton;
@FXML
private Button reinitialiserButton;




  /*  public static void envoyerEmail(String recipient, String subject, String messageBody) {
        // Configuration des propriétés pour la connexion à Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Informations d'authentification de l'expéditeur (ton email et mot de passe Gmail)
        String monEmail = "ton_email@gmail.com"; // Remplace avec ton email
        String motDePasse = "ton_mot_de_passe";  // Remplace avec ton mot de passe (ou utilise un mot de passe d'application)

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(monEmail, motDePasse);
            }
        });

        try {
            // Création de l'email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(monEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(messageBody);

            // Envoi de l'email
            Transport.send(message);
            System.out.println("Message envoyé avec succès !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
*/


//----------- Méthode pour envoyer les informations du formulaire de contact
@FXML
private void envoyerContact(ActionEvent event) {
    String nom = nomField.getText();
    String email = emailField.getText();
    String sujet = sujetField.getText();
    String message = messageField.getText();

  /*  if (nom.isEmpty() || email.isEmpty() || sujet.isEmpty() || message.isEmpty()) {
        showAlert("Erreur", "Veuillez remplir tous les champs du formulaire.");
    } */
   // else {
        String destinataire = "ton_email@gmail.com";
        String contenuMessage = "Nom: " + nom + "\nEmail: " + email + "\nSujet: " + sujet + "\nMessage: " + message;

        // Envoyer l'email
   //     envoyerEmail(destinataire, sujet, contenuMessage);

        // Enregistrer dans la base de données
        String sql = "INSERT INTO contact (nom, email, sujet, message) VALUES (?, ?, ?, ?)";
        
        try {Connection connection = SingleConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
            		 
            pst.setString(1, nom);
            pst.setString(2, email);
            pst.setString(3, sujet);
            pst.setString(4, message);
            pst.executeUpdate();
            showAlert("Succès", "Votre message a été envoyé et enregistré avec succès.");
        } catch (SQLException e) {
            showAlert("Erreur", "Impossible d'enregistrer le message.");
            e.printStackTrace();
        }

        reinitialiserFormulaire();
    }



// Méthode pour réinitialiser les champs du formulaire
@FXML
private void reinitialiserFormulaire() {
    nomField.clear();
    emailField.clear();
    sujetField.clear();
    messageField.clear();
}



















//--------------------------------------------EMPLOYE--------------------------------------------------------

//-------------------------------------------------------------------------------------------------


    @FXML
    private TextField searchField, nomFieldd, prenomField, posteField, salaireField;

    @FXML
    private DatePicker dateEmbauchePicker;

   // @FXML
 //   private TableView<Employe> employesTable;
    @FXML
    private TableView<Employe> tabledonneeemploye;

    @FXML
    private TableColumn<Employe, Integer> idColumn;
    @FXML
    private TableColumn<Employe, String> nomColumn;
    @FXML
    private TableColumn<Employe, String> prenomColumn;
    @FXML
    private TableColumn<Employe, String> posteColumn;
    @FXML
    private TableColumn<Employe, String> salaireColumn;
    @FXML
    private TableColumn<Employe, String> dateEmbaucheColumn;

    @FXML
    private Button ajouterButton, reinitialiserButtton;

 //   private ObservableList<Employe> employeList = FXCollections.observableArrayList();
    
    
    

    private ObservableList<Employe> employeList = FXCollections.observableArrayList();
    
    
    //----------------------------------classe client
    
	public ObservableList<Employe> donneeemploye() {
 		ObservableList<Employe> listdonneeemploye = FXCollections.observableArrayList();
 		String sql = "SELECT * FROM `employes`";
 		connection = SingleConnection.getConnection();

 		try {
 			Employe Demploye;

 			PreparedStatement pst = connection.prepareStatement(sql);// Préparer la requête SQL

 			res = pst.executeQuery();

 			while (res.next()) {
 				Demploye = new Employe(
 						res.getInt("id"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getString("poste"),
                        res.getString("salaire"),
                        res.getString("dateEmbauche"));
                        
                   

 				listdonneeemploye.add(Demploye);
 				System.out.print("okk");	    System.out.println("Début de la méthode employe");

 			}

 		} catch (Exception e) {
 			// TODO: handle exception
 		}return listdonneeemploye;


	}
	
	//------------------------------charger la classe principal employe---------

	private ObservableList<Employe> employelistdonneee;

	public void voirlistdonneeemployee() {
	    employelistdonneee = donneeemploye();
	    System.out.println("Début de la méthode affichertotalemploye");


	    // Lier les colonnes aux propriétés de la classe Client
	    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	    nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	    posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
	    salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
	    dateEmbaucheColumn.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
	   
	 //   numeroChambre.setCellValueFactory(new PropertyValueFactory<>("numero_client"));

	    tabledonneeemploye.setItems(employelistdonneee);
	    donneeemploye() ;
	    System.out.println("Début de la méthode affichert");

	}

	//-----------------------------------------------rendre le tableau editable
	

@FXML
public void tableemployeditable() {
voirlistdonneeemployee(); // Remplir la table

// Rendre les colonnes éditables
tabledonneeemploye.setEditable(true);

// Nom client éditable
nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
nomColumn.setOnEditCommit(event -> {
    Employe employes = event.getRowValue();
    employes.setNom(event.getNewValue());
    updateemployeInDatabase(employes); // Mettre à jour la base de données après l'édition
});

// Prénom client éditable
prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
prenomColumn.setOnEditCommit(event -> {
    Employe employes = event.getRowValue();
    employes.setPrenom(event.getNewValue());
    updateemployeInDatabase(employes); // Mettre à jour la base de données après l'édition
    
    
});

// Prénom client éditable
posteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
posteColumn.setOnEditCommit(event -> {
    Employe employes = event.getRowValue();
    employes.setPoste(event.getNewValue());
    updateemployeInDatabase(employes); // Mettre à jour la base de données après l'édition
    
    
});

// Prénom client éditable
salaireColumn.setCellFactory(TextFieldTableCell.forTableColumn());
salaireColumn.setOnEditCommit(event -> {
    Employe employes = event.getRowValue();
    employes.setSalaire(event.getNewValue());
    updateemployeInDatabase(employes); // Mettre à jour la base de données après l'édition
    
    
});
// Prénom client éditable
dateEmbaucheColumn.setCellFactory(TextFieldTableCell.forTableColumn());
dateEmbaucheColumn.setOnEditCommit(event -> {
    Employe employes = event.getRowValue();
    employes.setDateEmbauche(event.getNewValue());
    updateemployeInDatabase(employes); // Mettre à jour la base de données après l'édition
    
    
});

System.out.println("Début de la méthode afficher table employer");


}
//---------------------------------enregistrer modification dans bbd

private void updateemployeInDatabase(Employe employes) {
    String sql = "UPDATE employes SET nom = ?, prenom = ? WHERE id = ?";
    try {
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, employes.getNom());
        pst.setString(2, employes.getPrenom());
        pst.setInt(3, employes.getId());
        pst.executeUpdate();
        showAlert("Succès", "L'employer a été modifié avec succès !");
    } catch (SQLException e) {
        showAlert("Erreur", "Impossible de modifier l'employe.");
        e.printStackTrace();
    }
}
    

 	

    //-------------------------------- Ajouter un nouvel employé
    @FXML
    private void ajouterEmploye() {
       String nom = nomField.getText();
        String prenom = prenomField.getText();
        String poste = posteField.getText();
        Double salaire = Double.parseDouble(salaireField.getText());
       // String dateEmbauche = dateEmbauchePicker.getValue();
       String dateEmbauche= String.valueOf(dateEmbauchePicker.getValue());  // Check-out


        String query = "INSERT INTO employes (nom, prenom, poste, salaire, dateEmbauche) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, poste);
            pst.setDouble(4, salaire);
          //  pst.setDate(5, Date.valueOf(dateEmbauche));
            pst.setString(5, dateEmbauche);  // Check-out


            pst.executeUpdate();
            voirlistdonneeemployee();  // Recharger la table après l'ajout
            reinitialiserFormulaire();

            showAlert("Succès", "L'employé a été ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ajouter l'employé.");
        }
    }
 	

    // Réinitialiser le formulaire d'ajout
    @FXML
    private void reinitialiserFormulairee() {
        nomField.clear();
        prenomField.clear();
        posteField.clear();
        salaireField.clear();
        dateEmbauchePicker.setValue(null);
    }

    // Rechercher un employé
    @FXML
    private void rechercherEmploye() {
        String searchQuery = searchField.getText().toLowerCase();
        ObservableList<Employe> resultatsRecherche = FXCollections.observableArrayList();

        for (Employe employe : employeList) {
            if (employe.getNom().toLowerCase().contains(searchQuery) || employe.getPoste().toLowerCase().contains(searchQuery)) {
                resultatsRecherche.add(employe);
            }   tabledonneeemploye.setItems(resultatsRecherche);

        }

    }
    

  
 
// ----------------------------------------------actualiser
	/*
	 * void actualisertable() { connection = SingleConnection.getConnection();
	 * 
	 * ObservableList <donneechambre> chambres= FXCollections.observableArrayList();
	 * String sql="select*from chambre"; PreparedStatement pst; try { pst =
	 * connection.prepareStatement(sql); pst.executeQuery();
	 * 
	 * res=pst.executeQuery();
	 * 
	 * chambres.clear();
	 * 
	 * while(res.next()) { chambres.add( new donneechambre (
	 * res.getInt("idchambre"), res.getString("typechambre"),
	 * res.getString("statutchambre"), res.getDouble("prixchambre")));
	 * 
	 * tabledonneechambre.setItems(chambres); }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }// Préparer la requête SQL
	 * 
	 * 
	 * 
	 * }
	 */
	// ----------------------------------------supprimer
}