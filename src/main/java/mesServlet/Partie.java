package mesServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

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
	private GenerateurMot generateur = new GenerateurMot();
	private int tourPourEviterAccesConcurrentiel;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Partie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (tourPourEviterAccesConcurrentiel==0) {
			try {
				Thread.sleep(0100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tourPourEviterAccesConcurrentiel++;
			
		}
		if (tourPourEviterAccesConcurrentiel==1) {
			try {
				Thread.sleep(0300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tourPourEviterAccesConcurrentiel++;
			
		}
		if (tourPourEviterAccesConcurrentiel==2) {
			try {
				Thread.sleep(0500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tourPourEviterAccesConcurrentiel++;
			
		}
		if (tourPourEviterAccesConcurrentiel==3) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tourPourEviterAccesConcurrentiel=0;
		}
		
		
		if(this.getServletContext().getAttribute("Partie terminée" + session.getAttribute("idPartie"))!=null){
			response.sendRedirect("http://localhost:8080/codename2223/Index");
		}
		else {
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
				System.out.println("JE METS A JOUR ///// JE METS A JOUR ");
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
				if (reponse.equals("mauvaiseReponse")) {
					MajCodename(cd, session);
					session.setAttribute("listeCartes",
							this.getServletContext().getAttribute("listeCartes" + session.getAttribute("idPartie")));

				}
				if (reponse.equals("Motinterdit")) {
					this.getServletContext().setAttribute("Partie terminée" + session.getAttribute("idPartie"), true);
					session.removeAttribute("idPartie");
					
				}

			}
			if (nameAttribute.contains("indice")) {
				System.out.println(request.getParameter(nameAttribute));
				this.getServletContext().setAttribute("indice", request.getParameter(nameAttribute));
				MajCodename(cd, session);

			}

		}

		this.doGet(request, response);

	}

}
