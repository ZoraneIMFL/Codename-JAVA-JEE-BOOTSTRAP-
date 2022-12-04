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

		this.getServletContext().getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> listeIdPartie = new ArrayList<String>(); 
		HttpSession session = request.getSession(true);
		long compteur_id_partie = 1;
		Joueur j = new Joueur();
	

		Enumeration<String> parametres = request.getParameterNames();
		while (parametres.hasMoreElements()) {
			String nameAttribute = parametres.nextElement();
			if (nameAttribute.contains("pseudo")) {

				j.setPseudo(request.getParameter(nameAttribute));
				session.setAttribute("objetJoueur", j); 
				
//				System.out.println(request.getParameter(nameAttribute));
			}
			if (nameAttribute.contains("choixPartie")){
				session.setAttribute("idPartie",request.getParameter(nameAttribute).toString());
//				this.getServletContext().getRequestDispatcher("/PreparationPartie.jsp").forward(request, response);
				session.setAttribute("créateur", false);
				response.sendRedirect("http://localhost:8080/codename2223/PreparationPartie");//à changer ! 
				
			}
			if (nameAttribute.contains("choix")) {

				if (request.getParameter(nameAttribute).toString().contains("CreerUnePartie")) {
					Partie pCourante = new Partie();
					Equipe e1 = new Equipe(); 
					Equipe e2 = new Equipe(); 
					pCourante.setEquipe1(e1);
					pCourante.setEquipe2(e2);
					String idCourant = String.valueOf(compteur_id_partie); 
					pCourante.setId(compteur_id_partie);
					listeIdPartie.add(idCourant); 
					session.setAttribute(String.valueOf(compteur_id_partie) + "joueurHote", j);
					session.setAttribute("idPartie", idCourant);
					session.setAttribute("partie", pCourante);

					session.setAttribute("créateur", true);
					
					request.getServletContext().setAttribute("listePartie",listeIdPartie );
					request.getServletContext().setAttribute("mutex", true); 
//					System.out.println("Redirection à la page creationPartie.jsp");
					compteur_id_partie++; 
//					this.getServletContext().getRequestDispatcher("/PreparationPartie.jsp").forward(request, response);
					response.sendRedirect("http://localhost:8080/codename2223/PreparationPartie");//à changer !
				}
				if (request.getParameter(nameAttribute).toString().contains("rejoindrePartie")) {

					Enumeration<String> parametresServlet = this.getServletContext().getAttributeNames();
//					System.out.println(Collections.list(parametresServlet).size());
					if (Collections.list(parametresServlet).size() >= 14) {
						System.out.println(parametresServlet);
//						System.out.println("Redirection à la page rejoindrePartie.jsp");

					}
					if (!parametresServlet.hasMoreElements()) {
//						System.out.println("Pas de serveurs disponibles ! ");
					}
//					response.sendRedirect("http://localhost:8080/CodeName/");
					this.getServletContext().getRequestDispatcher("/RejoindrePartie.jsp").forward(request, response);
				}
			}

		}

	}

}
