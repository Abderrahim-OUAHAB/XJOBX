<%@page import="MesBeans.Utilisateurs"%>
<%@page import="DAO.UtilisateurDAO"%>
<%@page import="MesBeans.Offres"%>
<%@page import="DAO.OffreDAO"%>
<%@page import="DAO.CommentaireDAO"%>
<%@page import="MesBeans.Commentaires"%>
<%@page import="DAO.ReponseDAO"%>
<%@page import="MesBeans.Reponses"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    // Récupérer l'ID de l'offre depuis l'URL
    String idOffreParam = request.getParameter("idOffre");
   
    OffreDAO offreDAO = new OffreDAO();
    UtilisateurDAO ud = new UtilisateurDAO();
    CommentaireDAO commentaireDAO = new CommentaireDAO();
    ReponseDAO reponseDAO = new ReponseDAO();
    Offres offre = null;

    if (idOffreParam != null) {
        int idOffre = Integer.parseInt(idOffreParam);
        offre = offreDAO.getOffreById(idOffre);
        
        // Récupération des commentaires pour cette offre
        List<Commentaires> commentaires = commentaireDAO.getCommentairesByOffreId(idOffre);
        request.setAttribute("commentaires", commentaires);
         request.setAttribute("offre", offre);
    }

    // Vérifier si l'utilisateur est un recruteur
    String role = (String) session.getAttribute("role");
    boolean isRecruteur = "recruteur".equals(role);
    Integer id_utilisateur = (Integer) session.getAttribute("id_utilisateur");
    Utilisateurs user = ud.getUtilisateurById(offre.getIdRecruteur());
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

        .comment-section {
            margin-top: 40px;
        }

        .comment-box {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .comment-box p {
            font-size: 1rem;
        }

        .comment-box .comment-header {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .comment-box .comment-header img {
            border-radius: 1000px;
            width: 40px;
            height: 40px;
            margin-right: 10px;
            border: 2px solid black;
        }

        .comment-box .comment-header strong {
            font-size: 1.1rem;
        }

        .comment-box .comment-header .date {
            font-size: 0.9rem;
            color: #777;
        }

        .comment-box .question {
            font-weight: bold;
            margin-top: 10px;
            margin-bottom: 5px;
            font-size: 1.1rem;
        }

        .comment-box .response-section {
            margin-left: 20px;
            background-color: #f8f9fa;
            border-left: 3px solid #ddd;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
        }

        .comment-box .response-box {
            padding: 10px;
            margin-bottom: 10px;
            background-color: #f1f1f1;
            border-radius: 5px;
        }

        .comment-box .response-box p {
            margin-bottom: 5px;
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
            <p class="form-control"> <img src="${ud.getUtilisateurById(offre.idRecruteur).photoProfil}" style="width:23px;height: 23px;margin-right: 10px;border-radius: 1000px;border:2px solid black"/><%= user.getNom() %> a publié cette offre le <%= offre.getDatePublication() %></p>
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
        <c:if test="${isOffreAccessible}">
            <a href="modifierOffre.jsp?idOffre=<%= offre.getIdOffre() %>" class="btn btn-warning w-100">Modifier</a>
        </c:if>

        <a href="Offres.jsp" class="btn btn-secondary w-100 mt-3">Retour</a>
    </c:if>

    <c:if test="<%= offre == null %>">
        <div class="alert alert-danger">Offre non trouvée.</div>
    </c:if>

    <!-- Section Q/A -->
    <div class="comment-section">
        <h3>Questions et Réponses</h3>

        <!-- Formulaire d'ajout de commentaire -->
        <form action="AddCommentaireServlet" method="POST" class="mb-3">
            <input type="hidden" name="idOffre" value="<%= offre.getIdOffre() %>">
            <textarea class="form-control" name="contenu" rows="3" placeholder="Posez une question..." required></textarea>
            <button type="submit" class="btn btn-primary mt-2">Publier</button>
        </form>

        <c:forEach var="commentaire" items="${commentaires}">
            <div class="comment-box">
                <form action="AddReponseServlet" method="POST" class="mb-3">
                    <div class="comment-header">
                        <img src="${ud.getUtilisateurById(commentaire.idUtilisateur).photoProfil}" />
                        <div>
                            <strong>${ud.getUtilisateurById(commentaire.idUtilisateur).nom}</strong>
                         <fmt:formatDate value="${commentaire.dateCommentaire}" pattern="dd MMM yyyy HH:mm" var="formattedDate"/>
<span class="date">${formattedDate}</span>

                        </div>
                    </div>
                    
                    <p class="question">${commentaire.contenu}</p>

                    <input type="hidden" name="idOffre" value="<%= offre.getIdOffre() %>">
                    <input type="hidden" name="idCommentaire" value="${commentaire.idCommentaire}">
                    <textarea class="form-control" name="contenu" rows="3" placeholder="Répondez à la question..." required></textarea>
                    <button type="submit" class="btn btn-primary mt-2">Répondre</button>
                </form>

                <!-- Affichage des réponses -->
                <c:set var="reponses" value="${reponseDAO.getReponsesByCommentaireId(commentaire.idCommentaire)}"/>
                <c:forEach var="reponse" items="${reponses}">
                    <div class="response-section">
                        <div class="response-box">
                            <fmt:formatDate value="${reponse.dateReponse}" pattern="dd MMM yyyy HH:mm" var="formattedDate"/>


                            <p><strong>${ud.getUtilisateurById(reponse.idUtilisateur).nom}</strong>  <span class="date" style="font-size: 13px;font-style: italic">${formattedDate}</span></p>
                            <p>${reponse.contenu}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>

    </div>
</div>

<%@ include file="footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
