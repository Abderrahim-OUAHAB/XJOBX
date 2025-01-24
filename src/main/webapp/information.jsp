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
    <div class="container mt-5">
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
                        <h5><strong>Compétences</strong></h5>
                        <p>${profil.competences}</p>

                        <h5><strong>Années d'expérience</strong></h5>
                        <p>${profil.experience} ans</p>
                    </div>
                </div>

                <!-- Activités récentes (peut être étendu pour inclure des informations supplémentaires) -->
                <div class="card">
                    <div class="card-header bg-info text-white">
                        <h3>Activités récentes</h3>
                    </div>
                    <div class="card-body">
                        <p>Aucune activité récente.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
  <%@ include file="footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
