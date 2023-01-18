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
@jakarta.servlet.annotation.WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long compteur_id_partie;
	private long compteur_id_joueur;
	private long compteur_id_equipe;
	ArrayList<String> listeIdPartie;

	/**
	 * Default constructor.
	 */
	public Index() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("idPartie")!=null) {
			
			response.sendRedirect("/PreparationPartie");
			
		}
		else if (session.getAttribute("choixFait")!=null) {
			this.getServletContext().getRequestDispatcher("/RejoindrePartie.jsp").forward(request, response);
		}
		else {

			this.getServletContext().getRequestDispatcher("/Index.jsp").forward(request, response);
		}

		
	}

	private Partie creationPartie() {

		Equipe e1 = new Equipe("rouge", 0, compteur_id_equipe++);
		Equipe e2 = new Equipe("bleue", 0, compteur_id_equipe++);
		Partie pCourante = new Partie(e1, e2, false, compteur_id_partie);
		return pCourante;

	}

	private int chercherIndexPartie(ArrayList<String> liste, String id) {
		int i = 0;
		for (String p : liste) {
			if (p.equals(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		Joueur j = new Joueur();

		Enumeration<String> parametres = request.getParameterNames();
		while (parametres.hasMoreElements()) {
			String nameAttribute = parametres.nextElement();
			if (nameAttribute.contains("pseudo")) {
				j.setPseudo(request.getParameter(nameAttribute));
				j.setId(compteur_id_joueur++);
				session.setAttribute("objetJoueur", j);
				session.setAttribute("pseudo",request.getParameter(nameAttribute)); 
			}
			if (nameAttribute.contains("choixPartie")) {
				String id = request.getParameter(nameAttribute).toString();
				session.setAttribute("idPartie", id);
				session.setAttribute("createur_" + session.getAttribute("idPartie"), false);
				session.setAttribute("createur", false);
				Partie pCourant = (Partie) this.getServletContext()
						.getAttribute((String) session.getAttribute("idPartie"));
				if (pCourant.isStarted()) {
					ArrayList<String> liste = (ArrayList<String>) request.getServletContext()
							.getAttribute("listePartie");
					int index = chercherIndexPartie(liste, id);

					if (index != -1) {

						liste.remove(index);
						response.sendRedirect("/Index");
					}

				} else {
					response.sendRedirect("/PreparationPartie");
				}

			}
			if (nameAttribute.contains("choix")) {

				if (request.getParameter(nameAttribute).toString().contains("CreerUnePartie")) {
					Partie pCourante = creationPartie();
					session.setAttribute(String.valueOf(compteur_id_partie) + "joueurHote", j);

					session.setAttribute("idPartie", String.valueOf(compteur_id_partie));
					session.setAttribute("partie", pCourante);
					session.setAttribute("createur_" + session.getAttribute("idPartie"), true);
					if ((listeIdPartie = (ArrayList<String>) request.getServletContext()
							.getAttribute("listePartie")) != null) {
						listeIdPartie.add(String.valueOf(compteur_id_partie));
					} else {
						listeIdPartie = new ArrayList<String>();
						listeIdPartie.add(String.valueOf(compteur_id_partie));
						request.getServletContext().setAttribute("listePartie", listeIdPartie);
						request.getServletContext().setAttribute("mutex", true);
					}
					compteur_id_partie++;
					response.sendRedirect("/PreparationPartie");// à changer !
				}
				if (request.getParameter(nameAttribute).toString().contains("rejoindrePartie")) {

					Enumeration<String> parametresServlet = this.getServletContext().getAttributeNames();
					if (Collections.list(parametresServlet).size() >= 14) {

					}
					session.setAttribute("choixFait", true);
					this.getServletContext().getRequestDispatcher("/RejoindrePartie.jsp").forward(request, response);
				}
			}

		}

	}

}
