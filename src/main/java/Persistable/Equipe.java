package Persistable;

import java.util.ArrayList;

public class Equipe implements Persistable{
	
	private long Id;
	private int score; 
	private String nomEquipe; 
	private ArrayList < Joueur > ListeJoueurs = new ArrayList < Joueur > ();
	
	public Equipe() {
		
	}
	public Equipe(String nomEquipe, int score, long id) {
		this.Id = id; 
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


	public ArrayList < Joueur > getListeJoueurs() {
		return ListeJoueurs;
	}


	public void setListeJoueurs(Joueur Joueur) {
		this.ListeJoueurs.add(Joueur);
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