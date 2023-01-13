<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>PreparationPartie</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css%22%3E">
<link rel="stylesheet" href="style.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="form.css">
</head>
<body>


	<div class="form-body">
		<div class="row">
			<div class="form-holder">
				<div class="form-content">
					<form class="requires-validation" action="PreparationPartie"
						method="post" novalidate>
						<div class="form-items">
							<h3>Equipe 1 !</h3>
							<p>Rejoingnez l'équipe 1 !</p>

							<div class="form-button mt-3">
								<button id="refreshButton1" type="submit" class="btn btn-primary"
									name="choixRoleEquipe1" value="DecodeurEquipe1">Rejoindre
									les décodeurs</button>
								<select name='uuid'>
									<c:forEach var="var"
										items="${partieCourante.getEquipe1().getListeJoueurs()}">
										<option id="${var}"><c:if
												test="${var.getRole()=='Decodeur'}">
												<c:out value="${var.getPseudo()}" />
											</c:if></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-button mt-3">
								<button id="refreshButton2" type="submit" class="btn btn-primary"
									name="choixRoleEquipe1" value="EspionEquipe1">Rejoindre
									les espions</button>
								<select name='uuid'>
									<c:forEach var="var"
										items="${partieCourante.getEquipe1().getListeJoueurs()}">
										<option id="${var}"><c:if
												test="${var.getRole()=='Espion'}">
												<c:out value="${var.getPseudo()}" />
											</c:if></option>
									</c:forEach>
								</select>
							</div>
							<div class="valid-feedback mv-up">
								</br>Vous avez fait votre choix !
							</div>
							<div class="invalid-feedback mv-up">
								</br>Entrez votre choix s'il vous plait!
							</div>
						</div>


						<div class="form-items">
							<h3>Equipe 2 !</h3>
							<p>Rejoingnez l'équipé 2 !</p>

							<div class="form-button mt-3">
								<button id="refreshButton3" type="submit" class="btn btn-primary"
									name="choixRoleEquipe2" value="DecodeurEquipe2">Rejoindre
									les décodeurs</button>
								<select name='uuid'>
									<c:forEach var="var"
										items="${partieCourante.getEquipe2().getListeJoueurs()}">
										<option id="${var}"><c:if
												test="${var.getRole()=='Decodeur'}">
												<c:out value="${var.getPseudo()}" />
											</c:if></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-button mt-3">
								<button id="refreshButton4" type="submit" class="btn btn-primary"
									name="choixRoleEquipe2" value="EspionEquipe2">Rejoindre
									les espions</button>
								<select name='uuid'>
									<c:forEach var="var"
										items="${partieCourante.getEquipe2().getListeJoueurs()}">
										<option id="${var}"><c:if
												test="${var.getRole()=='Espion'}">
												<c:out value="${var.getPseudo()}" />
											</c:if></option>
									</c:forEach>
								</select>
							</div>


							<div class="valid-feedback mv-up">
								</br>Vous avez fait votre choix !
							</div>
							<div class="invalid-feedback mv-up">
								</br>Entrez votre choix s'il vous plait!
							</div>


						</div>


						<%-- <c:if test="${pageContext.servletContext.getAttribute('createur_' + session.getAttribute('idPartie')).equals(true)}"> --%>
						<%-- <%=request.getServletContext().getAttribute("createur_" + session.getAttribute("idpartie"))%> --%>

						<c:if test="${createur}">
							<div class="form-button mt-3">
								<button id="lancementPartie" type="submit" value="lancementPartie"
									name="lancementPartie" class="btn btn-primary ${LancementPossibleOuNon}">Lancer
									la partie</button>
							</div>
						</c:if>


					</form>





					<script src="form.js"></script>
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
					    
					    const button1 = document.getElementById("refreshButton1");
					    button1.addEventListener("click", function() {
					      webSocket.send("refresh");
					      
					    });
					    const button2 = document.getElementById("refreshButton2");
					    button2.addEventListener("click", function() {
					      webSocket.send("refresh");
					      
					    });
					    const button3 = document.getElementById("refreshButton3");
					    button3.addEventListener("click", function() {
					      webSocket.send("refresh");
					      
					    });
					    const button4 = document.getElementById("refreshButton4");
					    button4.addEventListener("click", function() {
					      webSocket.send("refresh");
					      
					    });
					    const button5 = document.getElementById("lancementPartie");
					    button5.addEventListener("click", function() {
					      webSocket.send("refresh");
					      
					    });
					    
					</script>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
