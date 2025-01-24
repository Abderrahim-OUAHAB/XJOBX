<%-- 
    Document   : Offres
    Created on : Jan 23, 2025, 11:14:02 PM
    Author     : minfo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Offres</title>
       <link href="css/bootstrap.min.css" rel="stylesheet">
       
          <link rel="stylesheet" href="css/style.css">
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

           <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <style>
         /* Style pour le bouton flottant */
            .btn-floating {
                position: fixed;
                bottom: 80px;
                right: 20px;
                width: 70px;
                height: 70px;
                border-radius: 100%;
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
    </style>
    <body>
          <%@ include file="navbar.jsp" %>
    
          <div class="container">
        <div class="row">
            <c:forEach var="offre" items="${offres}">
                <div class="col-12 col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${offre.titre}</h5>
                            <p class="card-text">${offre.description}</p>
                            <button class="btn btn-danger like-btn" data-offre-id="${offre.idOffre}">
                                <i class="fas fa-heart"></i> Like
                            </button>
                            <button class="btn btn-warning save-btn" data-offre-id="${offre.idOffre}">
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
         <c:if test="${role == 'recruteur'}">                  
        <a href="addOffer.jsp" class="btn btn-primary btn-floating">
    <i class="fas fa-plus"></i>
</a>
         </c:if>
 <%@ include file="footer.jsp" %>
 
 
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
