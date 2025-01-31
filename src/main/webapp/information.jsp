<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil Utilisateur</title>
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
            text-align: center;
        }
        .profile-image {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            border: 3px solid #3498db;
            margin-bottom: 10px;
        }
        .btn {
            border-radius: 5px;
            padding: 10px;
            font-size: 1rem;
            transition: background-color 0.3s;
        }
        .btn-primary:hover { background-color: #2980b9; }
        .btn-danger:hover { background-color: #c0392b; }
        .btn-warning:hover { background-color: #e67e22; }
        .offre-card {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
    </style>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-4 text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="${utilisateur.photoProfil != null ? utilisateur.photoProfil : 'default-avatar.jpg'}" class="profile-image" alt="Photo de profil">
                        <h5 class="card-title">${utilisateur.nom}</h5>
                        <p class="card-text">${utilisateur.email}</p>
                        <a href="profile.jsp" class="btn btn-primary">Modifier le profil</a>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-4">
                    <div class="card-header">Informations du Profil</div>
                    <div class="card-body">
                        <h5><strong>CV :</strong> <a href="${profil.cv}" target="_blank">Voir le CV</a></h5>
                        <h5><strong>Compétences :</strong></h5>
                        <p>${profil.competences}</p>
                        <h5><strong>Expérience :</strong></h5>
                        <p>${profil.experience} ans</p>
                    </div>
                </div>
                <div class="card">
                <c:if test="${not empty offreLiked}">
                    <div class="card-header bg-info">Offres Aimées</div>
                    <div class="card-body">
                        <c:forEach var="offre" items="${offreLiked}">
                            <div class="offre-card">
                                <h5 class="card-title">${offre.titre}</h5>
                                <p><fmt:formatDate value="${offre.datePublication}" pattern="dd MMM yyyy" /></p>
                                <form action="LikeServlet" method="post" style="display: inline;">
                                    <input type="hidden" name="offreId" value="${offre.idOffre}">
                                    <c:choose>
                                        <c:when test="${ld.getLikeByUserAndOffer(id_utilisateur,offre.idOffre)!=null}">
                                            <button type="submit" class="btn btn-danger">Unlike</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit" class="btn btn-primary">Like</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <a href="detailsOffre.jsp?idOffre=${offre.idOffre}" class="btn btn-success">Voir Détails</a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
