package mesServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import Persistable.Equipe;
import Persistable.Joueur;
import Persistable.Partie;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@jakarta.servlet.annotation.WebServlet("/PreparationPartie")
public class PreparationPartie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PreparationPartie() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute("LancementPossibleOuNon", "disabled");
		if ((boolean) session.getAttribute("createur_" + session.getAttribute("idPartie")) == true) {

			this.getServletContext().setAttribute((String) session.getAttribute("idPartie"),
					session.getAttribute("partie"));
			session.setAttribute("partieCourante", session.getAttribute("partie"));
			session.setAttribute("createur", true);
		} else {
			session.setAttribute("partieCourante",
					this.getServletContext().getAttribute((String) session.getAttribute("idPartie")));
		}

		if (checkNbrParticipantsMin(
				((Partie) this.getServletContext().getAttribute((String) session.getAttribute("idPartie")))
						.getEquipe1())
				&& checkNbrParticipantsMin(
						((Partie) this.getServletContext().getAttribute((String) session.getAttribute("idPartie")))
								.getEquipe2())) {
			session.setAttribute("LancementPossibleOuNon", "");
		}

		if (((Partie) this.getServletContext().getAttribute((String) session.getAttribute("idPartie"))).isStarted()) {
			if ((session.getAttribute("createur_" + session.getAttribute("idPartie")).equals(false))) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			response.sendRedirect("http://localhost:8080/codename2223/Partie");
		} else {

			this.getServletContext().getRequestDispatcher("/PreparationPartie.jsp").forward(request, response);
		}

	}

	private void checkEquipe(Partie partie, long id) {

		for (int i = 0; i < partie.getEquipe1().getListeJoueurs().size(); i++) {
			if (partie.getEquipe1().getListeJoueurs().get(i).getId() == id) {
				partie.getEquipe1().getListeJoueurs().remove(i);
			}
		}
		for (int i = 0; i < partie.getEquipe2().getListeJoueurs().size(); i++) {
			if (partie.getEquipe2().getListeJoueurs().get(i).getId() == id) {
				partie.getEquipe2().getListeJoueurs().remove(i);
			}
		}
	}

	private boolean checkNbrParticipantsMin(Equipe equipe) {
		int compteurDeco = 0;
		int compteurEspion = 0;
		for (Joueur joueur : equipe.getListeJoueurs()) {
			if (joueur.getRole() == "Decodeur") {
				compteurDeco++;
			} else {
				compteurEspion++;
			}
		}
		if (compteurDeco >= 1 && compteurEspion >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Enumeration<String> parametres = request.getParameterNames();
		Partie pCourant = (Partie) this.getServletContext().getAttribute((String) session.getAttribute("idPartie"));
		Joueur jCourant = (Joueur) session.getAttribute("objetJoueur");
		while (parametres.hasMoreElements()) {
			String nameAttribute = parametres.nextElement();
			if (nameAttribute.contains("choixRoleEquipe1")) {
				if (request.getParameter(nameAttribute).toString().contains("DecodeurEquipe1")) {
					jCourant.setRole("Decodeur");
				}
				if (request.getParameter(nameAttribute).toString().contains("EspionEquipe1")) {
					jCourant.setRole("Espion");
				}
				checkEquipe(pCourant, jCourant.getId());
				pCourant.getEquipe1().setListeJoueurs(jCourant);
				session.setAttribute("couleurEquipe", "rouge"); 
				session.setAttribute("role", jCourant.getRole());
				this.doGet(request, response);

			}
			if (nameAttribute.contains("choixRoleEquipe2")) {
				if (request.getParameter(nameAttribute).toString().contains("DecodeurEquipe2")) {
					jCourant.setRole("Decodeur");
				}
				if (request.getParameter(nameAttribute).toString().contains("EspionEquipe2")) {
					jCourant.setRole("Espion");
				}
				checkEquipe(pCourant, jCourant.getId());
				pCourant.getEquipe2().setListeJoueurs(jCourant);
				session.setAttribute("couleurEquipe", "bleu"); 
				session.setAttribute("role", jCourant.getRole());
				this.doGet(request, response);
			}
			if (checkNbrParticipantsMin(pCourant.getEquipe1()) && checkNbrParticipantsMin(pCourant.getEquipe2())) {
				session.setAttribute("LancementPossibleOuNon", "");
			}

			if (nameAttribute.contains("lancementPartie")) {
				pCourant.setStarted(true);
				response.sendRedirect("http://localhost:8080/codename2223/Partie");
			}

		}

	}

}
