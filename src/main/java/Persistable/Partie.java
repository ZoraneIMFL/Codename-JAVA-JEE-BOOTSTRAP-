package Persistable; 

public class Partie implements Persistable {
	
	private Equipe Equipe1; 
	private Equipe Equipe2; 
	private long id; 
	private String equipeGagnante; 
	
	public Partie () {
		
	}
	public Partie (Equipe Equipe1, Equipe Equipe2) {
		this.Equipe1 = Equipe1; 
		this.Equipe2 = Equipe2; 
		
	}
	

	public long getId() {
		return this.id; 
	}
	
	public void setId(long id) {
		this.id = id; 
		
	}
	
	public Equipe getEquipe2() {
		return Equipe2;
	}


	public void setEquipe2(Equipe Equipe2) {
		this.Equipe2 = Equipe2;
	}


	public Equipe getEquipe1() {
		return Equipe1;
	}


	public void setEquipe1(Equipe Equipe1) {
		this.Equipe1 = Equipe1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getEquipeGagnante() {
		return equipeGagnante;
	}
	public void setEquipeGagnante(String equipeGagnante) {
		this.equipeGagnante = equipeGagnante;
	}

	
}