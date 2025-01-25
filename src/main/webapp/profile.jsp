<%-- 
    Document   : profile
    Created on : Jan 23, 2025, 10:55:56 AM
    Author     : minfo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil utilisateur</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/bootstrap.min.css" rel="stylesheet">
          <link rel="stylesheet" href="css/style.css">
           <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
   <body>
         <%@ include file="navbar.jsp" %>
    <div class="container mt-5" style="margin-bottom: 100px">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center">Compléter votre profil</h2>
                <form action="UpdateProfileServlet" method="post" enctype="multipart/form-data">
                    <!-- Informations personnelles -->
                    <div class="form-group">
                        <label for="nom">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom" value="${utilisateur.nom}" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${utilisateur.email}" required>
                    </div>
                    <!-- Ancien mot de passe -->
                    <div class="form-group">
                        <label for="ancienMotDePasse">Ancien mot de passe</label>
                        <input type="text" class="form-control" id="ancienMotDePasse" name="ancienMotDePasse" value="${utilisateur.motDePasse}" required>
                    </div>
                    <!-- Nouveau mot de passe -->
                    <div class="form-group">
                        <label for="motDePasse">Nouveau mot de passe</label>
                        <input type="text" class="form-control" id="motDePasse" name="motDePasse"  required>
                    </div>
                    <!-- Confirmation du nouveau mot de passe -->
                    <div class="form-group">
                        <label for="confirmationMotDePasse">Confirmer le nouveau mot de passe</label>
                        <input type="password" class="form-control" id="confirmationMotDePasse" name="confirmationMotDePasse" required>
                    </div>

                    <!-- Photo de profil -->
                    <div class="form-group">
                        <label for="photoProfil">Photo de profil</label>
                        <input type="file" class="form-control" id="photoProfil" name="photoProfil">
                        <small>Facultatif. Téléchargez une photo pour votre profil.</small>
                    </div>
                    <c:if test="${role != 'recruteur'}">
                     <!-- cv -->
                    <div class="form-group">
                        <label for="cv">Votre CV</label>
                        <input type="file" class="form-control" id="cv" name="cv">
                        <small>Facultatif. Téléchargez votre cv.</small>
                    </div>
                     </c:if>
                    <!-- Profil utilisateur -->
                    <div class="form-group">
                        <label for="competences">Compétences</label>
                        <textarea class="form-control" id="competences" name="competences" rows="3" required>${profile.competences}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="experience">Années d'expérience</label>
                        <input type="number" class="form-control" id="experience" name="experience" value="${profile.experience}" required>
                    </div>

                    <button type="submit" class="btn btn-primary btn-block">Mettre à jour le profil</button>

                
                </form>
                        <!-- Affichage des erreurs -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
            </div>
        </div>
    </div>
                          <%@ include file="footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>
