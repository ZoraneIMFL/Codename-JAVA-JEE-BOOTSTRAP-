package FonctionsJeu;

import java.util.ArrayList;

public class CodenameGame {

	private int compteurTourEquipe;
	private int compteurTourJoueurParEquipe;
	private ArrayList<Carte> listeCartes = new ArrayList<Carte>();
	private int scoreEquipeRouge;
	private int scoreEquipeBleue;

	// Constructeur de la classe
	public CodenameGame(ArrayList<Carte> listeCartes) {
		this.setListeCartes(listeCartes);
		this.compteurTourEquipe = 1; // Le premier tour est pour l'�quipe 1
		this.compteurTourJoueurParEquipe = 1; // Le premier joueur de l'�quipe 1 commence
	}

	// M�thode pour obtenir le joueur actuel
	public String getJoueurActuel() {
		String equipe = "";
		String role = "";

		// D�terminer l'�quipe du joueur
		if (compteurTourEquipe % 2 == 1) {
			equipe = "EquipeRouge";
		} else {
			equipe = "EquipeBleue";
		}

		// D�terminer le r�le du joueur
		if (compteurTourJoueurParEquipe == 1) {
			role = "Espion";
		} else {
			role = "D�codeur";
		}

		// Retourner l'�quipe et le r�le du joueur
		return equipe + role;
	}

	// M�thode pour obtenir le score de l'�quipe 1
	public int getScoreEquipe1() {
		return scoreEquipeRouge;
	}

	// M�thode pour obtenir le score de l'�quipe 2
	public int getScoreEquipe2() {
		return scoreEquipeBleue;
	}

	// M�thode pour passer au tour suivant
	public void passerAuTourSuivant() {
		// Si c'est le dernier joueur de l'�quipe, on passe � l'�quipe suivante
		if (compteurTourJoueurParEquipe == 2) {
			compteurTourEquipe++;
			compteurTourJoueurParEquipe = 1;
		}
		// Sinon, on passe au joueur suivant de l'�quipe
		else {
			compteurTourJoueurParEquipe++;
		}

		// Si on a atteint la fin des tours d'�quipe, on recommence � la premi�re �quipe
		if (compteurTourEquipe > 4) {
			compteurTourEquipe = 1;
		}
	}

	// M�thode pour v�rifier si une carte choisie par le joueur est de la bonne
	// couleur
	public String verifierCarte(String indexCarte, String equipeJoueur) {
		Carte carte = getListeCartes().get(Integer.parseInt(indexCarte));
		String couleurCarte = carte.getCouleur();
		System.out.println(carte.getCouleur());
		System.out.println(carte.getMot());

		// Si la carte est de la couleur de l'�quipe du joueur, on ajoute un point au
		// score de l'�quipe et on renvoie "bonneReponse"
		if (couleurCarte.equals(equipeJoueur)) {
			if (equipeJoueur.equals("rouge")) {
				scoreEquipeRouge++;
			} else if (equipeJoueur.equals("bleu")) {
				scoreEquipeBleue++;
			}
			carte.setFind(true); 
			return "bonneReponse";
		}
		// Si la carte est de couleur noire, on renvoie "Motinterdit"
		else if (couleurCarte.equals("noir")) {
			carte.setFind(true); 
			return "Motinterdit";
		}
		// Si la carte n'est ni de la bonne couleur ni noire, on renvoie
		// "mauvaiseReponse"
		else {
			return "mauvaiseReponse";
		}
	}

	// M�thode pour ajouter des points � l'�quipe 1
	public void ajouterPointsEquipe1(int points) {
		scoreEquipeRouge += points;
	}

	// M�thode pour ajouter des points � l'�quipe 2
	public void ajouterPointsEquipe2(int points) {
		scoreEquipeBleue += points;
	}

	public ArrayList<Carte> getListeCartes() {
		return listeCartes;
	}

	public void setListeCartes(ArrayList<Carte> listeCartes) {
		this.listeCartes = listeCartes;
	}
}