<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Index</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css%22%3E">
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
					<div class="form-items">
						<h3>CodeName !</h3>
						<p>Entrez vos information ci dessous</p>
						<form class="requires-validation" action="Index" method="post"
							novalidate>

							<div class="col-md-12">
								<input class="form-control" type="text" name="pseudo"
									placeholder="Entrez votre peusdo ici" required>
								<div class="valid-feedback">Jolie pseudo !</div>
								<div class="invalid-feedback">Entrez un pseudo s'il vous
									plait !</div>

							</div>









							<div class="col-md-12 mt-3">
								<input type="radio" class="btn-check" name="choix"
									id="CreerUnePartie" value="CreerUnePartie" autocomplete="off"
									required> <label
									class="btn btn-sm btn-outline-secondary" for="CreerUnePartie">Créer
									une partie</label>

								<c:if test="${mutex}">
									<input type="radio" class="btn-check" name="choix"
										id="rejoindrePartie" value="rejoindrePartie"
										autocomplete="off" required>
									<label class="btn btn-sm btn-outline-secondary"
										for="rejoindrePartie">Rejoindre une partie</label>
								</c:if>

								<div class="valid-feedback mv-up">
									</br>Vous avez fait votre choix !
								</div>
								<div class="invalid-feedback mv-up">
									</br>Entrez votre choix s'il vous plait!
								</div>
							</div>


							<div class="form-button mt-3">
								<button id="refreshButton1" type="submit"
									class="btn btn-primary">Terminé</button>
							</div>
						</form>

					</div>
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

						const button1 = document
								.getElementById("refreshButton1");
						button1.addEventListener("click", function() {
							webSocket.send("refresh");

						});
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>