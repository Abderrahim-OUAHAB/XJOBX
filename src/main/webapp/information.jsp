<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil Utilisateur</title>
    <!-- Liens CSS externes non modifiés -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <style>
    

        .container {
            margin-top: 50px;
            padding-bottom: 100px;
        }

        .card {
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }

        .card-header {
            background-color: #3498db;
            color: white;
            border-radius: 8px 8px 0 0;
            padding: 15px;
        }

        .card-body {
            padding: 20px;
            background-color: #ffffff;
        }

        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
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

        .col-md-4 img {
            width: 100%;
            height: auto;
            border-radius: 8px;
        }

        .card-header.bg-info {
            background-color: #1abc9c;
        }

        .card-header.bg-primary {
            background-color: #3498db;
        }

        .card-header.bg-info h3 {
            color: white;
        }

        .save-btn i {
            margin-right: 5px;
        }

        .save-btn {
            display: inline-flex;
            align-items: center;
        }

        h3 {
            font-size: 1.5rem;
        }

        .row {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }

        /* Mobile responsiveness */
        @media (max-width: 768px) {
            .col-md-4, .col-md-8 {
                width: 100%;
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
            <div class="col-md-4">
                <!-- Photo de profil -->
                <div class="card">
                    <img src="${utilisateur.photoProfil != null ? utilisateur.photoProfil : 'default-avatar.jpg'}"
                         class="card-img-top" alt="Photo de profil">
                    <div class="card-body text-center">
                        <h5 class="card-title">${utilisateur.nom}</h5>
                        <p class="card-text">${utilisateur.email}</p>
                        <a href="profile.jsp" class="btn btn-primary">Modifier le profil</a>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <!-- Informations du profil -->
                <div class="card mb-4">
                    <div class="card-header bg-primary text-white">
                        <h3>Informations du profil</h3>
                    </div>
                    <div class="card-body">
                        <h5>
                            <c:if test="${profil.cv != null}">
                                <strong>
                                    <a href="${profil.cv != null ? profil.cv : 'default-avatar.jpg'}" 
                                       target="_blank" 
                                       class="card-img-top">
                                       Voir le CV
                                    </a>
                                </strong>
                            </c:if>
                        </h5>

                        <h5><strong>Compétences</strong></h5>
                        <p>${profil.competences}</p>

                        <h5><strong>Années d'expérience</strong></h5>
                        <p>${profil.experience} ans</p>
                    </div>
                </div>

                <!-- Activités récentes -->
                <div class="card">
                    <div class="card-header bg-info text-white">
                        <h3>Offres Aimées</h3>
                    </div>
                    <c:forEach var="offre" items="${offreLiked}">
                        <div class="col-4 col-md-12" style="text-align: center">
                            <div class="card" >
                                <div class="card-body" >
                                    <h5 class="card-title">${offre.titre}</h5>
                                   
                                    <form action="LikeServlet" method="post" style="display: inline;">
                                        <input type="hidden" name="offreId" value="${offre.idOffre}">
                                        <c:choose>
                                            <c:when test="${ld.getLikeByUserAndOffer(id_utilisateur,offre.idOffre)!=null}">
                                                <button type="submit" class="btn btn-danger">
                                                    <i class="fas fa-heart"></i> Unlike
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary">
                                                    <i class="fas fa-heart"></i> Like
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                    <c:if test="${role != 'recruteur'}">
                                        <form action="PostulerServlet" method="post" style="display: inline;">
                                            <input type="hidden" name="offreId" value="${offre.idOffre}">
                                            <c:choose>
                                                <c:when test="${cd.getCandidatureByUserAndOffer(id_utilisateur,offre.idOffre)!=null}">
                                                    <button class="btn btn-warning save-btn">
                                                        <i class="fas fa-bookmark"></i> Annuler
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-warning save-btn">
                                                        <i class="fas fa-bookmark"></i> Postuler
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </c:if>
                                    <c:if test="${role == 'recruteur' && od.getOffreByUserIdAndOffre(id_utilisateur,offre.idOffre)!=null}">
                                        <hr>
                                        <form action="deleteOffre" method="post">
                                            <input type="text" value="${offre.idOffre}" hidden name="idOffre">
                                            <button type="submit" class="btn btn-danger save-btn">
                                                <i class="fas fa-trash"></i> Supprimer
                                            </button>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
    <!-- Liens JS externes non modifiés -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
