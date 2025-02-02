package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.control.Alert;

public class donneeclients {

	

	//



		

		public static int id_client;
	    private String nom;
	    private String prenom;
	    private String email;
	    private String telephone;
	    private String adresse;
	    private String ville;
	    private String pays;
	    private String date_inscription;
	    private String checkin;
	
	    private String checkout;
	    public static int numero_client;
	    public static String genre;
	    public static int totaljours;     // Nouveau attribut pour stocker la durée du séjour
	    private double montanttotal;  // Nouveau attribut pour stocker le montant total


	    
	    public String getGenre() {
			return genre;
		}

	    public void Client2(int id, String nom, String prenom, String telephone, double totalPayer, String dateArrivee, String dateSortie, String adresse, String genre, int numeroChambre) {
	        this.id_client = id;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.telephone = telephone;
	        this.montanttotal = totalPayer;
	        this.checkin = dateArrivee;
	        this.checkout = dateSortie;
	        this.adresse = adresse;
	        this.genre = genre;
	        this.numero_client = numeroChambre;
	    }

	    
	    public void Client(int id_client, String nom, String email, String telephone, String pays, 
                String adresse, String genre, String checkin,String checkout, int totaljours, double montanttotal) 
	    {
      this.id_client = id_client;
      this.nom = nom;
      this.email = email;
      this.telephone = telephone;
      this.pays = pays;
      this.adresse = adresse;
      this.genre = genre;
      this.checkin = checkin;
      this.checkout = checkout;
      this.totaljours = totaljours;
      this.montanttotal = montanttotal;}
	    
	    
	    
	 // Constructeur
	    public donneeclients(Integer id_client, String nom, String prenom, String telephone, Double montantTotal, String checkin, String checkout, String adresse, String genre, String numeroChambre) {
	        this.id_client = id_client;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.telephone = telephone;
	        this.montanttotal = montantTotal;
	        this.checkin = checkin;
	        this.checkout = checkout;
	        this.adresse = adresse;
	        this.genre = genre;
	        this.numero_client = numero_client;
	    }

		public void setGenre(String genre) {
			this.genre = genre;
		}

		
	    // Constructeur sans paramètres
	    public void donneechambres() {
	    }

	    // Constructeur avec tous les paramètres
	    
	    public void donneeclients1(int id_client, String nom, String prenom, String email, String telephone, String adresse,
	                          String ville, String pays, String date_inscription, String checkin, String checkout) {
	       
	    	this.id_client = id_client;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.email = email;
	        this.telephone = telephone;
	        this.adresse = adresse;
	        this.ville = ville;
	        this.pays = pays;
	        this.date_inscription = date_inscription;
	        this.checkin = checkin;
	        this.checkout = checkout;
	        
	        this.email = genre;
	    }

	    // Getters et Setters

	    public static int getId_client() {
	        return id_client;
	    }

	    public void setId_client(int id_client) {
	        this.id_client = id_client;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public String getPrenom() {
	        return prenom;
	    }

	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getTelephone() {
	        return telephone;
	    }

	    public void setTelephone(String telephone) {
	        this.telephone = telephone;
	    }

	    public String getAdresse() {
	        return adresse;
	    }

	    public void setAdresse(String adresse) {
	        this.adresse = adresse;
	    }

	    public String getVille() {
	        return ville;
	    }

	    public void setVille(String ville) {
	        this.ville = ville;
	    }

	    public String getPays() {
	        return pays;
	    }

	    public void setPays(String pays) {
	        this.pays = pays;
	    }

	    public String getDate_inscription() {
	        return date_inscription;
	    }

	    public void setDate_inscription(String date_inscription) {
	        this.date_inscription = date_inscription;
	    }

	    public String getCheckin() {
	        return checkin;
	    }

	    public void setCheckin(String checkin) {
	        this.checkin = checkin;
	    }

	    public String getCheckout() {
	        return checkout;
	    }

	    public void setCheckout(String checkout) {
	        this.checkout = checkout;
	    }

	    public int getNumero_client() {
	        return numero_client;
	    }

	    public void setNumero_client(int numero_client) {
	        this.numero_client = numero_client;
	    }
	


	    // Getter et Setter pour total_jours
	    public int getTotaljours() {
	        return totaljours;
	    }

	    public void setTotaljours(int totaljours) {
	        this.totaljours = totaljours;
	    }

	    // Getter et Setter pour montant_total
	    public double getMontanttotal() {
	        return montanttotal;
	    }

	    public void setMontanttotal(double montant_total) {
	        this.montanttotal = montanttotal;
	    }

}
