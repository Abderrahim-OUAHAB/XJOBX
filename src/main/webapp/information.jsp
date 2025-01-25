<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil Utilisateur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
     <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <%@ include file="navbar.jsp" %>
    <div class="container mt-5" style="margin-bottom: 100px">
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

                <!-- Activités récentes (peut être étendu pour inclure des informations supplémentaires) -->
                <div class="card">
                    <div class="card-header bg-info text-white">
                        <h3>Offres Aimés</h3>
                    </div>
                    
                       <c:forEach var="offre" items="${offreLiked}">
                <div class="col-12 col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${offre.titre}</h5>
                            <p class="card-text">${offre.description}</p>
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
                            <button class="btn btn-warning save-btn">
                                <i class="fas fa-bookmark"></i> Save
                            </button>
                            <c:if test="${role == 'recruteur'}">
                                <hr>
                               
                            <form action="deleteOffre" method="post"> <input type="text" value="${offre.idOffre}" hidden name="idOffre"> <button type="submit" class="btn btn-danger save-btn" >
                                <i class="fas fa-trash"></i> Supprimer
                                    </button></form>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
