<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter une Offre</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="navbar.jsp" %>

        <div class="container mt-5">
            <h2 class="text-center">Ajouter une Nouvelle Offre</h2>

       
            <form action="AddOfferServlet" method="POST" class="mt-4">
                <div class="mb-3">
                    <label for="titre" class="form-label">Titre de l'Offre</label>
                    <input type="text" class="form-control" id="titre" name="titre" required maxlength="150">
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="competences" class="form-label">Compétences Requises</label>
                    <textarea class="form-control" id="competences" name="competences" rows="4" required></textarea>
                </div>
              
             
                <button type="submit" class="btn btn-primary">Ajouter l'Offre</button>
            </form>
                 <%-- Display a success or error message --%>
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <div class="alert <%= message.startsWith("Erreur") ? "alert-danger" : "alert-success" %>">
                    <%= message %>
                </div>
            <% } %>

        </div>

        <%@ include file="footer.jsp" %>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
