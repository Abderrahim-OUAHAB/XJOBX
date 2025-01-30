<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Candidatures</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <style>
    /* Style global de la page */
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f9f9f9;
        color: #333;
    }

    /* En-tête de la page */
    h1 {
        color: #2c3e50;
        font-size: 2rem;
        font-weight: 700;
        margin-bottom: 30px;
    }

    /* Table des candidatures */
    .table {
        background-color: #fff;
        border-radius: 8px;
        border: 1px solid #ddd;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    .table th, .table td {
        padding: 12px 15px;
        vertical-align: middle;
        text-align: center;
    }

    /* En-tête de la table */
    .table-dark {
        background-color: #34495e;
        color: white;
        font-weight: bold;
    }

    .table-striped tbody tr:nth-of-type(odd) {
        background-color: #f4f6f8;
    }

    .table-bordered {
        border: 1px solid #ddd;
    }

    .table-striped tbody tr:hover {
        background-color: #e9ecef;
    }

    /* Boutons d'action */
    .btn-sm {
        padding: 5px 10px;
        font-size: 0.875rem;
    }

    .btn-success {
        background-color: #27ae60;
        border-color: #27ae60;
    }

    .btn-success:hover {
        background-color: #2ecc71;
        border-color: #2ecc71;
    }

    .btn-danger {
        background-color: #e74c3c;
        border-color: #e74c3c;
    }

    .btn-danger:hover {
        background-color: #c0392b;
        border-color: #c0392b;
    }

    .btn-warning {
        background-color: #f39c12;
        border-color: #f39c12;
    }

    .btn-warning:hover {
        background-color: #e67e22;
        border-color: #e67e22;
    }

    /* Section contenant le tableau */
    .container {
        margin-top: 50px;
    }

    /* Formulaire en ligne */
    form {
        display: inline-block;
        margin-right: 5px;
    }

    /* Responsive : ajustement sur mobile */
    @media (max-width: 768px) {
        .table th, .table td {
            padding: 10px 8px;
        }

        h1 {
            font-size: 1.5rem;
        }
    }
</style>
<!-- Liens CSS externes -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <%@ include file="navbar.jsp" %>

    <div class="container mt-5">
        

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
                                <form action="AccepterPostulation" method="post" style="display:inline;">
                                    <input type="hidden" name="idCandidature" value="${candidature[0]}">
                                    <button type="submit" class="btn btn-success btn-sm">Accepter</button>
                                </form>
                                
                                <form action="RejterPostulation" method="post" style="display:inline;">
                                    <input type="hidden" name="idCandidature" value="${candidature[0]}">
                                    <button type="submit" class="btn btn-danger btn-sm">Rejeter</button>
                                </form>
                            </c:if>

                            <c:if test="${role != 'recruteur'}">
                                <form action="AnnulerPostulation" method="post" style="display:inline;">
                                    <input type="hidden" name="idCandidature" value="${candidature.idCandidature}">
                                    <button type="submit" class="btn btn-warning btn-sm">Annuler</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="footer.jsp" %>
<!-- JS Scripts -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
