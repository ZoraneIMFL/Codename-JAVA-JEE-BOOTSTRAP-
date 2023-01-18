package mesTags;

import java.io.IOException;
import java.util.ArrayList;

import Persistable.Equipe;
import Persistable.Joueur;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class AffichageDeroulement extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8551197847120151777L;
	private String role;
	private String tourActuel;
	private String couleurEquipe;
	private Joueur jCourant;
	private Equipe equipeRouge;
	private Equipe equipeBleue;
	private Object indice;

	public void setRole(String role) {
		this.role = role;
	}

	public void setTourActuel(String tourActuel) {
		this.tourActuel = tourActuel;
	}

	public void setjCourant(Joueur jCourant) {
		this.jCourant = jCourant;
	}

	

	public void setCouleurEquipe(String couleurEquipe) {
		this.couleurEquipe = couleurEquipe;
	}

	public void setEquipeBleue(Equipe equipeBleue) {
		this.equipeBleue = equipeBleue;
	}

	public void setEquipeRouge(Equipe equipeRouge) {
		this.equipeRouge = equipeRouge;
	}
	
	public void setIndice(Object indice) {
		this.indice = indice;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();

		try {
			if (tourActuel.contains(jCourant.getRole())) {
				if ((tourActuel.contains("Rouge") && couleurEquipe.equals("rouge"))
						|| (tourActuel.contains("Bleue") && couleurEquipe.equals("bleu"))) {
					out.println("<span style={color:white}>A vous de jouer ! l'indice est : "+indice+" </span>");
				}

			} else {

				if (tourActuel.contains("Rouge")) {
					for (Joueur j : this.equipeRouge.getListeJoueurs()) {
						if (tourActuel.contains("Decodeur") && j.getRole().equals("Decodeur")) {
							out.println("<span style={color:white}>C'est au decodeur de l'equipe rouge de jouer : "
									+ j.getPseudo() + " </span>");

						}
						if (tourActuel.contains("Espion") && j.getRole().equals("Espion")) {
							out.println("<span style={color:white}>C'est à l'espion de l'equipe rouge de jouer : "
									+ j.getPseudo() + " </span>");
						}
					}
				} else {
					for (Joueur j : this.equipeBleue.getListeJoueurs()) {
						if (tourActuel.contains("Decodeur") && j.getRole().equals("Decodeur")) {
							out.println("<span style={color:white}>C'est au decodeur de l'equipe bleue de jouer : "
									+ j.getPseudo() + " </span>");

						}
						if (tourActuel.contains("Espion") && j.getRole().equals("Espion")) {
							out.println("<span style={color:white}>C'est à l'espion de l'equipe bleue de jouer : "
									+ j.getPseudo() + " </span>");
						}
					}
				}
			}

		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}
