package mesTags;

import java.io.IOException;
import java.util.ArrayList;

import FonctionsJeu.Carte;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class GrilleCarteTagLib extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8551197847120151777L;
	private ArrayList<Carte> listeCartes;
	private String role;
	private String tourActuel;
	private String couleurEquipe;

	public void setListeCartes(ArrayList<Carte> listeCartes) {
		this.listeCartes = listeCartes;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public void setTourActuel(String tourActuel) {
		this.tourActuel = tourActuel;
	}
	public void setCouleurEquipe(String couleurEquipe) {
		this.couleurEquipe = couleurEquipe;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();

		try {
			out.println("<div class='row row-cols-5'>");

			if (role.equals("Espion")) {
				for (Carte carte : listeCartes) {
					out.println("<div class='col d-grid carte'>");
					out.println("<label class='btn btn-outline-secondary " + carte.getCouleur() + "'>" + carte.getMot()
							+ "</label>");
					out.println("</div>");
				}

				if ((this.tourActuel.equals("EquipeRougeEspion") && this.couleurEquipe.equals("rouge"))
						|| (this.tourActuel.equals("EquipeBleueEspion") && this.couleurEquipe.equals("bleu"))) {
					out.println("<input  type=\"text\" name=\"indice\" class=\"indice text-left\" value='indice ici' />");
					out.println("<button id='refreshButton1' type='submit'>Envoyer</button>");
				}

			} else {
				int index = 0;
				if ((this.tourActuel.equals("EquipeRougeDecodeur") && this.couleurEquipe.equals("rouge"))
						|| (this.tourActuel.equals("EquipeBleueDecodeur") && this.couleurEquipe.equals("bleu"))) {
					for (Carte carte : listeCartes) {
						if (carte.isFind()) {
							out.println("<div class='col d-grid carte'>");
							out.println("<label class='btn btn-outline-secondary " + carte.getCouleur() + "'>"
									+ carte.getMot() + "</label>");
							out.println("</div>");
						} else {
							out.println("<div class='col d-grid carte'>");
							out.println(
									"<button type=\"submit\" class='btn btn-outline-secondary gris' name='carte' id='refreshButton"
											+ carte.getIdCarte() + "' value='" + index + "'>");

							out.println(carte.getMot());

							out.println("</button>");
							out.println("</div>");
						}
						index++;
					}

				}

				else {
					for (Carte carte : listeCartes) {
						if (carte.isFind()) {
							out.println("<div class='col d-grid carte'>");
							out.println("<label class='btn btn-outline-secondary " + carte.getCouleur() + "'>"
									+ carte.getMot() + "</label>");
							out.println("</div>");
						} else {
							out.println("<div class='col d-grid carte'>");
							out.println("<label class='btn btn-outline-secondary gris'>" + carte.getMot() + "</label>");
							out.println("</div>");
						}
						index++;
					}

				}

			}
		} catch (IOException e) {
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
