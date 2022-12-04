package mesServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

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
		if ((boolean) session.getAttribute("créateur")) {
			this.getServletContext().setAttribute((String) session.getAttribute("idPartie"), session.getAttribute("partie"));
			session.setAttribute("partieCourante", session.getAttribute("partie") ); 
			session.setAttribute("créateur", false); 
		}
		else {
			session.setAttribute("partieCourante", this.getServletContext().getAttribute((String) session.getAttribute("idPartie")));
		}
		
		this.getServletContext().getRequestDispatcher("/PreparationPartie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Enumeration<String> parametres = request.getParameterNames();
//		System.out.println("msqdfljkqsdmfljsqdmlfkqsjdfmlsqdkfjqsmfljk");
		while (parametres.hasMoreElements()) {
			String nameAttribute = parametres.nextElement();
			if (nameAttribute.contains("choixRoleEquipe1")) {
				Partie pCourant = (Partie) this.getServletContext()
						.getAttribute((String) session.getAttribute("idPartie"));
				Joueur jCourant = (Joueur) session.getAttribute("objetJoueur");
				if (request.getParameter(nameAttribute).toString().contains("DecodeurEquipe1")) {

					jCourant.setRole("Decodeur");
					pCourant.getEquipe1().setListeJoueurs(jCourant);
					this.getServletContext().setAttribute((String) session.getAttribute("idPartie"), pCourant); 
					
					this.doGet(request, response); 
				}
				if (request.getParameter(nameAttribute).toString().contains("EspionEquipe1")) {

					jCourant.setRole("Espion");
					pCourant.getEquipe1().setListeJoueurs(jCourant);
					this.doGet(request, response);
				}
			}
			if (nameAttribute.contains("choixRoleEquipe2")) {
				Joueur jCourant = (Joueur) session.getAttribute("objetJoueur");
				Partie pCourant = (Partie) this.getServletContext()
						.getAttribute((String) session.getAttribute("idPartie"));
				if (request.getParameter(nameAttribute).toString().contains("DecodeurEquipe2")) {
					jCourant.setRole("Decodeur");
					pCourant.getEquipe2().setListeJoueurs(jCourant);
					this.doGet(request, response);
				}
				if (request.getParameter(nameAttribute).toString().contains("EspionEquipe2")) {
					jCourant.setRole("Espion");
					pCourant.getEquipe2().setListeJoueurs(jCourant);
					this.doGet(request, response);
				}
			}
		}

	}

}
