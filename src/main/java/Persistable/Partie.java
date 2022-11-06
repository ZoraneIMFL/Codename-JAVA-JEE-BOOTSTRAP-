package Persistable; 

public class Partie implements Persistable {
	
	private long idEquipe1; 
	private long idEquipe2; 
	private long id; 
	private String equipeGagnante; 
	
	public Partie () {
		
	}
	public Partie (long idEquipe1, long idEquipe2) {
		this.idEquipe1 = idEquipe1; 
		this.idEquipe2 = idEquipe2; 
		
	}
	

	public long getId() {
		return this.id; 
	}
	
	public void setId(long id) {
		this.id = id; 
		
	}


	public long getIdEquipe2() {
		return idEquipe2;
	}


	public void setIdEquipe2(long idEquipe2) {
		this.idEquipe2 = idEquipe2;
	}


	public long getIdEquipe1() {
		return idEquipe1;
	}


	public void setIdEquipe1(long idEquipe1) {
		this.idEquipe1 = idEquipe1;
	}
	public String getEquipeGagnante() {
		return equipeGagnante;
	}
	public void setEquipeGagnante(String equipeGagnante) {
		this.equipeGagnante = equipeGagnante;
	}

	
}