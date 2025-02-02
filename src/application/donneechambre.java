package application;

public class donneechambre {

	private Integer idchambre;
	private String typechambre;
	private String statutchambre;
	private Double prixchambre;
	
	public Integer getIdchambre() {
		return idchambre;
	}

	public void setIdchambre(Integer idchambre) {
		this.idchambre = idchambre;
	}

	public String getTypechambre() {
		return typechambre;
	}

	public void setTypechambre(String typechambre) {
		this.typechambre = typechambre;
	}

	public String getStatutchambre() {
		return statutchambre;
	}

	public void setStatutchambre(String statutchambre) {
		this.statutchambre = statutchambre;
	}

	public Double getPrixchambre() {
		return prixchambre;
	}

	public void setPrixchambre(Double prixchambre) {
		this.prixchambre = prixchambre;
	}

	public donneechambre(Integer idchambre, String typechambre, String statutchambre, Double prixchambre) {
		super();
		this.idchambre = idchambre;
		this.typechambre = typechambre;
		this.statutchambre = statutchambre;
		this.prixchambre = prixchambre;
	}
	
	
	
	
}
