package mesServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import FonctionsJeu.NomProject;
import FonctionsJeu.Carte;
import FonctionsJeu.CodenameGame;
import FonctionsJeu.GenerateurMot;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Partie
 */
@jakarta.servlet.annotation.WebServlet("/Partie")
public class Partie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nomProjet;
	private GenerateurMot generateur = new GenerateurMot();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Partie() {
		super();
		NomProject np = new NomProject();
		nomProjet = np.getNom();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		if (this.getServletContext().getAttribute("Partie terminee Index" + session.getAttribute("idPartie"))!=null) {
			session.removeAttribute("idPartie");
			
			if (session.getAttribute("choixFait")!=null) {
				session.removeAttribute("choixFait"); 
			}
			response.sendRedirect("http://localhost:8080/"+nomProjet+"/Index");
		}

		else if (this.getServletContext().getAttribute("Partie terminee" + session.getAttribute("idPartie")) != null) {
			
			this.getServletContext().getRequestDispatcher("/Vainqueur.jsp").forward(request, response);
			
//			response.sendRedirect("/codename2223/Index");
		} else {
			if ((this.getServletContext().getAttribute("listeCartes" + session.getAttribute("idPartie")) == null)
					&& (session.getAttribute("createur_" + session.getAttribute("idPartie")).equals(true))) {

				ArrayList<Carte> listeCartes = generateur.genere();
				CodenameGame cd = new CodenameGame(listeCartes);
				this.getServletContext().setAttribute("listeCartes" + session.getAttribute("idPartie"), listeCartes);
				this.getServletContext().setAttribute("CodeName" + session.getAttribute("idPartie"), cd);
				this.getServletContext().setAttribute("tourActuel" + session.getAttribute("idPartie"),
						cd.getJoueurActuel());

				session.setAttribute("tourActuel",
						this.getServletContext().getAttribute("tourActuel" + session.getAttribute("idPartie")));

				session.setAttribute("listeCartes", listeCartes);

			} else

			{
				session.setAttribute("listeCartes",
						this.getServletContext().getAttribute("listeCartes" + session.getAttribute("idPartie")));
				session.setAttribute("tourActuel",
						this.getServletContext().getAttribute("tourActuel" + session.getAttribute("idPartie")));
			}
			this.getServletContext().getRequestDispatcher("/Partie.jsp").forward(request, response);
		}

	}

	private void MajCodename(CodenameGame cd, HttpSession session) {
		cd.passerAuTourSuivant();
		this.getServletContext().setAttribute("tourActuel" + session.getAttribute("idPartie"), cd.getJoueurActuel());
		this.getServletContext().setAttribute("CodeName" + session.getAttribute("idPartie"), cd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		Enumeration<String> parametres = request.getParameterNames();
		CodenameGame cd = (CodenameGame) this.getServletContext()
				.getAttribute("CodeName" + session.getAttribute("idPartie"));

		while (parametres.hasMoreElements()) {
			String nameAttribute = parametres.nextElement();
			if (nameAttribute.contains("carte")) {
				String reponse = cd.verifierCarte(request.getParameter(nameAttribute),
						(String) session.getAttribute("couleurEquipe"));
				
				if (reponse.equals("bonneReponse")) {
					if(session.getAttribute("couleurEquipe").equals("rouge")) {
						this.getServletContext().setAttribute("scoreRouge"+ session.getAttribute("idPartie"), cd.getScoreEquipe1());
						if(cd.getScoreEquipe1()>=9) {
							this.getServletContext().setAttribute("EquipeVictorieuse" + session.getAttribute("idPartie"), "rouge");
							this.getServletContext().setAttribute("Partie terminee" + session.getAttribute("idPartie"), true);
						}
					}
					if(session.getAttribute("couleurEquipe").equals("bleu")) {
						this.getServletContext().setAttribute("scoreBleu"+ session.getAttribute("idPartie"), cd.getScoreEquipe2());
						if(cd.getScoreEquipe2()>=8) {
							this.getServletContext().setAttribute("EquipeVictorieuse" + session.getAttribute("idPartie"), "bleue");
							this.getServletContext().setAttribute("Partie terminee" + session.getAttribute("idPartie"), true);
						}
					}
					 
				}
				if (reponse.equals("mauvaiseReponse")) {
					MajCodename(cd, session);
					session.setAttribute("listeCartes",
							this.getServletContext().getAttribute("listeCartes" + session.getAttribute("idPartie")));

				}
				if (reponse.equals("Motinterdit")) {
					if(session.getAttribute("couleurEquipe").equals("rouge")) {
						this.getServletContext().setAttribute("EquipeVictorieuse" + session.getAttribute("idPartie"), "bleue");
					}
					else {
						this.getServletContext().setAttribute("EquipeVictorieuse" + session.getAttribute("idPartie"), "rouge");
					}
					this.getServletContext().setAttribute("Partie terminee" + session.getAttribute("idPartie"), true);
					

				}

			}
			if (nameAttribute.contains("indice")) {

				this.getServletContext().setAttribute("indice"+session.getAttribute("idPartie"), request.getParameter(nameAttribute));
				MajCodename(cd, session);

			}
			if(nameAttribute.contains("RetourIndex")) {
				this.getServletContext().setAttribute("Partie terminee Index" + session.getAttribute("idPartie"), true);
				
			}

		}

		this.doGet(request, response);

	}

}
