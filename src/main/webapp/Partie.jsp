<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="ma" uri="/WEB-INF/MesTaglib"%>
<!DOCTYPE html>
<html lang="fr">
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Partie</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>

<!-- jQuery -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"
	integrity="sha384-kEHhjyVmq3C2zWV7wGKjhO/7bry8kN4F7v+Q2yNjKsM8/ZT6T/T6gZU6eZiEuKM8"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="form.css">

</head>

<body>
	<div class="container-fluid" style="width: 100%">
		<div class="navbar navbar-expand-lg navbar-dark"
			style="background-color: black">

			<h3 style="color: white">CodeName du Z</h3>





			<div class="ml-auto pseudo">${objetJoueur.getPseudo()}</div>
		</div>
		<div class="row mx-auto">

			<div class="text-center"
				style="background-color: rgba(249, 249, 249, 0.2); color: white; width: 100%; left: 50%">


				<ma:AffichageDeroulement role="${role}" tourActuel="${tourActuel}"
					couleurEquipe="${couleurEquipe}" jCourant="${objetJoueur}"
					equipeRouge="${partieCourante.getEquipe1()}"
					equipeBleue="${partieCourante.getEquipe2()}"
					indice="<%=request.getServletContext().getAttribute(\"indice\"+session.getAttribute(\"idPartie\"))%>" />


			</div>


		</div>


		<div class="row pt-3 mt-3">
			<div class="col sideRouge">
				<div
					class="navbar navbar-expand-lg navbar-dark text-center titreEquipe">
					Equipe Rouge - Score :
					<%=request.getServletContext().getAttribute("scoreRouge" + session.getAttribute("idPartie"))%></div>
				<div class="equipe-rouge">

					<c:forEach var="joueur"
						items="${partieCourante.getEquipe1().getListeJoueurs()}">
						<div class="joueur">
							<p>
								<strong>${joueur.getPseudo()}</strong> : <em>${joueur.getRole()}</em>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>







			<div class="col col-9">

				<div class="grille-milieu">
					<form class="requires-validation" action="Partie" method="post"
						novalidate>
						<div class="container  text-center">


							<ma:GrilleCarteTagLib listeCartes="${listeCartes}" role="${role}"
								tourActuel="${tourActuel}" couleurEquipe="${couleurEquipe}" />

						</div>


					</form>
				</div>






















			</div>
			<div class="col sideBleu">
				<div
					class="navbar navbar-expand-lg navbar-dark text-center titreEquipe">
					Equipe Bleue - Score :
					<%=request.getServletContext().getAttribute("scoreBleu" + session.getAttribute("idPartie"))%></div>
				<div class="equipe-bleue">

					<c:forEach var="joueur"
						items="${partieCourante.getEquipe2().getListeJoueurs()}">
						<div class="joueur">
							<p>
								<strong>${joueur.getPseudo()}</strong> : <em>${joueur.getRole()}</em>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="text-center p-3"
		style="background-color: rgba(0, 0, 0, 0.5); position: fixed; bottom: 0; width: 100%">
		© Lien git: <a class="text-dark"
			href="https://gitlab.univ-artois.fr/zeineddine_dbilij/codename/"><strong>Gitlab
				Codename</strong></a>
	</div>



	<script type="text/javascript">
		const webSocketUrl = "ws://localhost:8080/codename2223/refresh";
		const webSocket = new WebSocket(webSocketUrl);
		webSocket.onopen = function() {
			console.log("Connection opened");
		};

		webSocket.onmessage = function(evt) {
			console.log("Received message: " + evt.data);
			// Refresh the page when a message is received
			window.location.reload();
		};

		webSocket.onclose = function() {
			console.log("Connection closed");
		};

		webSocket.onerror = function(error) {
			console.log("Error: " + error);
		};

		for (let i = 1; i <= 25; i++) {
			  const button = document.getElementById("refreshButton"+i.toString());
			  button.addEventListener('click', () => {
			    webSocket.send('refresh');
			  });
			}
	</script>

</body>
</html>