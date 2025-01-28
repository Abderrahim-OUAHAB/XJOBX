<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Disposition des cartes */
            .card {
                border: 1px solid #ddd;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s ease, box-shadow 0.3s ease;
                margin-bottom: 20px;
            }

            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }

            .card-body {
                padding: 20px;
            }

            .card-title {
                font-size: 1.25rem;
                font-weight: bold;
                margin-bottom: 15px;
                color: #333;
            }

            .card-text {
                font-size: 1rem;
                color: #555;
                line-height: 1.5;
            }

            .row {
                margin-top: 20px;
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
                padding: 10px 20px;
                text-transform: uppercase;
                font-weight: bold;
                border-radius: 5px;
            }

            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #004085;
            }

            /* Style pour la phrase de bienvenue et le logo */
            .welcome-section {
                text-align: center;
                margin-bottom: 40px;
                padding: 20px;
                background-color: #f8f9fa;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }

            .welcome-section h2 {
                font-size: 2rem;
                font-weight: bold;
                color: #333;
            }

            .welcome-section img {
                width: 250px; /* Ajustez la taille de votre logo */
                margin-top: 15px;
                border-radius: 1000px;
            }
        </style>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>

        <!-- Section de bienvenue avec le logo -->
        <div class="welcome-section">
            <h2>Bienvenue sur XJOBX !</h2>
            <!-- Remplacez "logo.png" par le chemin de votre logo -->
            <img src="images/logo1.webp" alt="Logo de la plateforme">
        </div>

        <c:if test="${role != 'recruteur'}">
            <div class="container mt-4" style="margin-bottom: 100px">
                <h1>Suggestions d'Offres Basées sur vos Compétences</h1>
                <c:if test="${not empty suggestedOffres}">
                    <div class="row">
                        <c:forEach var="offre" items="${suggestedOffres}">
                            <c:if test="${not empty offre}">
                                <div class="col-md-4">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <h5 class="card-title">${offre.titre}</h5>
                                            <p class="card-text">${offre.description}</p>
                                            <p class="card-text"><strong>Compétences:</strong> ${offre.competences}</p>
                                            <a href="#" class="btn btn-primary">Voir Détails</a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>

                <c:if test="${empty suggestedOffres}">
                    <p>Aucune suggestion trouvée.</p>
                </c:if>
            </div>
        </c:if>

        <%@ include file="footer.jsp" %>
    </body>
</html>
