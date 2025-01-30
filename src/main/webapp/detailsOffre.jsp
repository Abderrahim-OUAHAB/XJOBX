<%@page import="MesBeans.Utilisateurs"%>
<%@page import="DAO.UtilisateurDAO"%>
<%@page import="MesBeans.Offres"%>
<%@page import="DAO.OffreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Récupérer l'ID de l'offre depuis l'URL
    String idOffreParam = request.getParameter("idOffre");
    OffreDAO offreDAO = new OffreDAO();
    UtilisateurDAO ud=new UtilisateurDAO();
    Offres offre = null;

    if (idOffreParam != null) {
        int idOffre = Integer.parseInt(idOffreParam);
        offre = offreDAO.getOffreById(idOffre);
    }

    // Vérifier si l'utilisateur est un recruteur
    String role = (String) session.getAttribute("role");
    boolean isRecruteur = "recruteur".equals(role);
       Integer id_utilisateur = (Integer) session.getAttribute("id_utilisateur");
        Utilisateurs user= ud.getUtilisateurById(offre.getIdRecruteur());
    boolean isOffreAccessible = (isRecruteur && offreDAO.getOffreByUserIdAndOffre(id_utilisateur, offre.getIdOffre()) != null);
    request.setAttribute("isOffreAccessible", isOffreAccessible);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Détails de l'Offre</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 30px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            margin-bottom: 30px;
        }

        .form-container .form-label {
            font-weight: bold;
        }

        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container mt-5 form-container" style="margin-bottom: 100px">
    <h2 class="text-center">Détails de l'Offre</h2>

    <c:if test="<%= offre != null %>">
         <div class="mb-3">
            <label class="form-label">Recruteur</label>
            <p class="form-control"><%= user.getNom() %> a publié cette offre le <%= offre.getDatePublication() %></p>
        </div>
        <div class="mb-3">
            <label class="form-label">Titre de l'Offre</label>
            <p class="form-control"><%= offre.getTitre() %></p>
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea class="form-control"><%= offre.getDescription() %></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Compétences Requises</label>
            <textarea class="form-control"><%= offre.getCompetences() %></textarea>
        </div>

        <!-- Bouton Modifier pour les recruteurs -->
<c:if test="${ isOffreAccessible}">            
    <a href="modifierOffre.jsp?idOffre=<%= offre.getIdOffre() %>" class="btn btn-warning w-100">Modifier</a>
        </c:if>

        <a href="Offres.jsp" class="btn btn-secondary w-100 mt-3">Retour</a>
    </c:if>

    <c:if test="<%= offre == null %>">
        <div class="alert alert-danger">Offre non trouvée.</div>
    </c:if>
</div>

<%@ include file="footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
