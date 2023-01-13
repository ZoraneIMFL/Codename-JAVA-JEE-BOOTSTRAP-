<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>RejoindrePartie</title>
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
					<div class="form-items">
						<h3>CodeName !</h3>
						<p>Choisissez la partie que vous voulez rejoindre</p>
						<form class="requires-validation" action="Index" method="post"
							novalidate>

							<select name='choixPartie'>
								<c:forEach var="var" items="${listePartie}">
									<option id="${var}"  value="${var}">${var}</option>
								</c:forEach>
							</select>



							<div class="form-button mt-3">
								<button id="submit" type="submit" class="btn btn-primary">Terminé</button>
							</div>
						</form>

					</div>
					<script src="form.js"></script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>