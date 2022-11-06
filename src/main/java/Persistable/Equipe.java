package Persistable;

import java.util.ArrayList;

public class Equipe implements Persistable{
	
	private long Id;
	private int score; 
	private String nomEquipe; 
	private ArrayList < Long > ListeJoueurs = new ArrayList < Long > ();
	
	public Equipe() {
		
	}
	public Equipe(String nomEquipe, int score) {
		this.nomEquipe = nomEquipe; 
		this.score =score; 
	}
	

	@Override
	public long getId() {
		return this.Id; 
	}


	public void setId(long id) {
		Id = id;
	}


	public ArrayList < Long > getListeJoeurs() {
		return ListeJoueurs;
	}


	public void setListeJoeurs(Long idJoueur) {
		this.ListeJoueurs.add(idJoueur);
	}


	public String getNomEquipe() {
		return nomEquipe;
	}


	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	
}