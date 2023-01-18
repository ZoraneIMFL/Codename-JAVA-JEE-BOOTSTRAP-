<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="ma" uri="/WEB-INF/MesTaglib"%>>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Vainqueur</title>
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
	<div class="form-body">
		<div class="row">

			<div class="form-holder">

				<div class="form-content">
					<div class="form-items">

						<h3>
							L'équipe
							<%=request.getServletContext().getAttribute("EquipeVictorieuse" + session.getAttribute("idPartie"))%>
							est victorieuse !
						</h3>
						<form class="requires-validation" action="Partie" method="post"
							novalidate>
							<div class="form-button mt-3">
								<button type="submit" name="RetourIndex"
									class="btn btn-primary">Retourner à l'index</button>
							</div>


						</form>
					</div>
				</div>
			</div>
		</div>
	</div>




</body>
</html>