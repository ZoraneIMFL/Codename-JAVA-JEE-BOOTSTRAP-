package Persistable; 

public class Joueur implements Persistable{
	
	private String pseudo; 
	private long id; 
	private String role; 
	
	public Joueur() {
		
	}
	
	public Joueur(String pseudo, long id, String role) {
		this.pseudo = pseudo; 
		this.id = id; 
		this.role = role; 
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}