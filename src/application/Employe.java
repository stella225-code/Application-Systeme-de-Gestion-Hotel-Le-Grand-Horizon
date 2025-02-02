package application;
import javafx.beans.property.*;

import java.time.LocalDate;


	
	public class Employe {
	    private Integer id;
	    private String nom;
	    private String prenom;
	    private String poste;
	    private String salaire;
	    private String dateEmbauche;
		/**
		 * @param id
		 * @param nom
		 * @param prenom
		 * @param poste
		 * @param salaire
		 * @param dateEmbauche
		 */
		public Employe(Integer id, String nom, String prenom, String poste, String salaire, String dateEmbauche) {
			super();
			this.id = id;
			this.nom = nom;
			this.prenom = prenom;
			this.poste = poste;
			this.salaire = salaire;
			this.dateEmbauche = dateEmbauche;
		}
		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}
		/**
		 * @return the nom
		 */
		public String getNom() {
			return nom;
		}
		/**
		 * @param nom the nom to set
		 */
		public void setNom(String nom) {
			this.nom = nom;
		}
		/**
		 * @return the prenom
		 */
		public String getPrenom() {
			return prenom;
		}
		/**
		 * @param prenom the prenom to set
		 */
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		/**
		 * @return the poste
		 */
		public String getPoste() {
			return poste;
		}
		/**
		 * @param poste the poste to set
		 */
		public void setPoste(String poste) {
			this.poste = poste;
		}
		/**
		 * @return the salaire
		 */
		public String getSalaire() {
			return salaire;
		}
		/**
		 * @param salaire the salaire to set
		 */
		public void setSalaire(String salaire) {
			this.salaire = salaire;
		}
		/**
		 * @return the dateEmbauche
		 */
		public String getDateEmbauche() {
			return dateEmbauche;
		}
		/**
		 * @param dateEmbauche the dateEmbauche to set
		 */
		public void setDateEmbauche(String dateEmbauche) {
			this.dateEmbauche = dateEmbauche;
		}

	   
		
}
