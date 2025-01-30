<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Offres</title>
        <!-- Liens CSS externes -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            /* Style général */
            body {
                font-family: Arial, sans-serif;
                background-color: #ecf0f1;
                color: #2c3e50;
                margin: 0;
                padding: 0;
            }

            .container {
                margin-top: 50px;
            }

            .card {
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin-bottom: 30px;
                background-color: #fff;
                border: none;
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }

            .card-header {
                background-color: #3498db;
                color: white;
                border-radius: 10px 10px 0 0;
                padding: 15px;
                font-weight: bold;
                font-size: 1.2rem;
            }

            .card-body {
                padding: 20px;
            }

            .card-body h5 {
                font-size: 1.1rem;
                margin-bottom: 15px;
            }

            .card-body p {
                font-size: 1rem;
                color: #7f8c8d;
            }

            .btn {
                border-radius: 5px;
                padding: 10px;
                font-size: 1rem;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .btn-primary {
                background-color: #3498db;
                color: white;
                border: none;
            }

            .btn-primary:hover {
                background-color: #2980b9;
            }

            .btn-danger {
                background-color: #e74c3c;
                color: white;
                border: none;
            }

            .btn-danger:hover {
                background-color: #c0392b;
            }

            .btn-warning {
                background-color: #f39c12;
                color: white;
                border: none;
            }

            .btn-warning:hover {
                background-color: #e67e22;
            }

            .text-center {
                text-align: center;
            }

            .row {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
            }

            .col-md-4 {
                display: flex;
                flex-direction: column;
                margin-bottom: 20px;
            }

            /* Alignement des cartes dans la grille */
            .col-md-4 {
                padding: 0 15px;
            }

            /* Bouton flottant */
            .btn-floating {
                position: fixed;
                bottom: 80px;
                right: 20px;
                width: 70px;
                height: 70px;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 24px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                z-index: 1000;
            }

            .btn-floating i {
                margin: 0;
            }

            /* Responsivité */
            @media (max-width: 768px) {
                .col-md-4 {
                    width: 100%;
                    margin-bottom: 20px;
                }

                .container {
                    margin-top: 30px;
                }
            }
        </style>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>

        <div class="container">
            <div class="row">
                <c:forEach var="offre" items="${offres}">
                    <div class="col-12 col-md-4">
                        <div class="card">
                            <div class="card-header" style="text-align:center">
                                ${offre.titre}
                            </div>
                            <div class="card-body" style="text-align:center">
                              
                               
                                <form action="LikeServlet" method="post" style="display: inline-block;">
                                    <input type="hidden" name="offreId" value="${offre.idOffre}">
                                    <c:choose>
                                        <c:when test="${ld.getLikeByUserAndOffer(id_utilisateur,offre.idOffre)!=null}">
                                            <button type="submit" class="btn btn-danger" style="font-size: 14px">
                                                <i class="fas fa-heart"></i> Unlike
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit" class="btn btn-primary" style="font-size: 14px">
                                                <i class="fas fa-heart"></i> Like
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>

                                <c:if test="${role != 'recruteur'}">
                                    <form action="PostulerServlet" method="post" style="display: inline-block;">
                                        <input type="hidden" name="offreId" value="${offre.idOffre}">
                                        <c:choose>
                                            <c:when test="${cd.getCandidatureByUserAndOffer(id_utilisateur,offre.idOffre)!=null}">
                                                <button class="btn btn-warning save-btn" style="font-size: 14px">
                                                    <i class="fas fa-bookmark"></i> Annuler
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-warning save-btn" style="font-size: 15px">
                                                    <i class="fas fa-bookmark"></i> Postuler
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </c:if>

                                <c:if test="${role == 'recruteur' && od.getOffreByUserIdAndOffre(id_utilisateur,offre.idOffre)!=null}">
                                 
                                    <form action="deleteOffre" method="post" style="display: inline-block;">
                                        <input type="text" value="${offre.idOffre}" hidden name="idOffre" style="display: none">
                                        <button type="submit" class="btn btn-danger save-btn" style="font-size: 14px">
                                            <i class="fas fa-trash"></i> Supprimer
                                        </button>
                                    </form>
                                        
     

                                </c:if>
                                    
                                                                       <a href="detailsOffre.jsp?idOffre=${offre.idOffre}" class="btn btn-info" style="font-size: 14px">
    <i class="fas fa-eye"></i> Voir Détails
</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <c:if test="${role == 'recruteur'}">
            <a href="addOffer.jsp" class="btn btn-primary btn-floating">
                <i class="fas fa-plus"></i>
            </a>
        </c:if>

        <%@ include file="footer.jsp" %>

        <!-- Liens JS externes -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
