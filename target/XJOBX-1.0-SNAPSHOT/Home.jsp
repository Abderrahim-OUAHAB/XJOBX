<%-- 
    Document   : Home
    Created on : Jan 21, 2025, 1:02:14 PM
    Author     : minfo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
         <link href="css/bootstrap.min.css" rel="stylesheet">
          <link rel="stylesheet" href="css/style.css">
           <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <form method="get" action="SkillExtractionServlet">
         <div class="container mt-4">
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

            </form>
          <%@ include file="footer.jsp" %>
    </body>
</html>
