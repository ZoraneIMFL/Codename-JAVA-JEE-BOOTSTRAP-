package FonctionsJeu; 




public class Carte {
	private String couleur; 
	private String mot;
	private String idCarte;
	private boolean isFind; 
	
	public Carte (String couleur, String mot, String idCarte, boolean isFind) {
		this.couleur = couleur; 
		this.mot = mot; 
		this.idCarte = idCarte;
		this.isFind = isFind;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getMot() {
		return mot;
	}
	public void setMot(String mot) {
		this.mot = mot;
	}
	public String getIdCarte() {
		return idCarte;
	}
	public void setIdCarte(String idCarte) {
		this.idCarte = idCarte;
	}
	public boolean isFind() {
		return isFind;
	}
	public void setFind(boolean isFind) {
		this.isFind = isFind;
	} 
	
	
}