<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Candidatures</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
          <link rel="stylesheet" href="css/style.css">
           <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
      <%@ include file="navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">Liste des Candidatures</h1>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
               
                       <th>Offre</th>
                     
                     <c:if test="${role == 'recruteur'}">
                       <th>Candidat</th>
                        </c:if>
                       
                    <th>Date Postulation</th>
                    <th>Statut</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="candidature" items="${candidatures}">
                    <tr>
                        
                        <c:if test="${role != 'recruteur'}">
                        <td>${od.getOffreById(candidature.idOffre).titre}</td>
                                <td>${candidature.datePostulation}</td>
                        <td>${candidature.statut}</td>
                        </c:if>
                        
                        
                         <c:if test="${role == 'recruteur'}">
                              <td>${candidature[5]}</td>
                        <td>${candidature[3]}</td>
                          <td>${candidature[1]}</td>
                            <td>${candidature[2]}</td>
                        </c:if>
                            
              
                            
                        <td>
                              <c:if test="${role == 'recruteur'}">
                                  
                                      <form action="AccepterPostulation" method="post">
                 <input type="text" name="idCandidature" value="${candidature[0]}" hidden>

                            <button type="submit" class="btn btn-primary">
                                      Accepter
                                    </button>
                                  </form>
                 
                  <form action="RejterPostulation" method="post">
                                      <input type="text" name="idCandidature" value="${candidature[0]}" hidden>
                             <button type="submit" class="btn btn-danger">
                                        <i class="fas fa-heart"></i> Rejter
                                    </button>
                      
                           </form>
                              </c:if>
                              <c:if test="${role != 'recruteur'}">
                                  
                                  <form action="AnnulerPostulation" method="post">
                            
                                      <input type="text" name="idCandidature" value="${candidature.idCandidature}" hidden>
                                
                                      
                             <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-heart"></i> Annuler
                                    </button>
                                  </form>
                              </c:if>
                        </td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
 <%@ include file="footer.jsp" %>
    <!-- Bootstrap JS (Optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
