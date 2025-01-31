<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Offres</title>
        <!-- Liens CSS externes -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            /* General styles */
            body {
                font-family: Arial, sans-serif;
                background-color: #ecf0f1;
                color: #2c3e50;
                margin: 0;
                padding: 0;
            }

            .container {
                margin-top: 50px;
            }

            .card {
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin-bottom: 30px;
                background-color: #fff;
                border: none;
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }

            .card-header {
                background-color: #3498db;
                color: white;
                border-radius: 10px 10px 0 0;
                padding: 15px;
                font-weight: bold;
                font-size: 1.2rem;
            }

            .card-body {
                padding: 20px;
            }

            .card-body h5 {
                font-size: 1.1rem;
                margin-bottom: 15px;
            }

            .card-body p {
                font-size: 1rem;
                color: #7f8c8d;
            }

            .btn {
                border-radius: 5px;
                padding: 10px;
                font-size: 1rem;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .btn-primary {
                background-color: #3498db;
                color: white;
                border: none;
            }

            .btn-primary:hover {
                background-color: #2980b9;
            }

            .btn-danger {
                background-color: #e74c3c;
                color: white;
                border: none;
            }

            .btn-danger:hover {
                background-color: #c0392b;
            }

            .btn-warning {
                background-color: #f39c12;
                color: white;
                border: none;
            }

            .btn-warning:hover {
                background-color: #e67e22;
            }

      /* Section de bienvenue */
    .welcome-section {
        text-align: center;
        background-color: #f0f8ff;
        border-radius: 10px;
        padding: 50px 30px;
        box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.1);
        margin-top: 40px;
        transition: all 0.3s ease;
    }

    .welcome-section:hover {
        box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.2);
        transform: translateY(-5px);
    }

    .welcome-section h2 {
        font-size: 2.5rem;
        color: #2c3e50;
        font-weight: 700;
        margin-bottom: 20px;
        text-transform: uppercase;
        letter-spacing: 1.5px;
        text-shadow: 1px 1px 5px rgba(0, 0, 0, 0.1);
    }

    .welcome-section h2 span {
        color: #3498db;
    }

    .welcome-section img {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        object-fit: cover;
        margin-top: 20px;
        border: 5px solid #3498db;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
    }

    .welcome-section img:hover {
        transform: scale(1.1);
    }

    /* Effet de survol pour le texte */
    .welcome-section h2 {
        transition: color 0.3s ease, text-shadow 0.3s ease;
    }

    .welcome-section h2:hover {
        color: #2980b9;
        text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.2);
    }

    /* Mobile Responsiveness */
    @media (max-width: 768px) {
        .welcome-section {
            padding: 30px 20px;
        }

        .welcome-section h2 {
            font-size: 2rem;
        }

        .welcome-section img {
            width: 120px;
            height: 120px;
        }
    

            /* Layout for offers */
            .row {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
            }

            .col-md-4 {
                display: flex;
                flex-direction: column;
                margin-bottom: 20px;
            }

            /* Button floating */
            .btn-floating {
                position: fixed;
                bottom: 80px;
                right: 20px;
                width: 70px;
                height: 70px;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 24px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                z-index: 1000;
            }

            /* Responsive design */
            @media (max-width: 768px) {
                .col-md-4 {
                    width: 100%;
                    margin-bottom: 20px;
                }

                .container {
                    margin-top: 30px;
                }
            }
        </style>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>

        <!-- Welcome Section -->
        <div class="welcome-section">
            <h2>Bienvenue sur XJOBX !</h2>
            <img src="images/logo1.webp" alt="Logo de la plateforme">
        </div>

        <c:if test="${role != 'recruteur'}">
            <div class="container" style="margin-bottom:50px">
                <div class="row">
                    <c:forEach var="offre" items="${suggestedOffres}">
                        <div class="col-12 col-md-4">
                            <div class="card">
                                <div class="card-header" style="text-align:center">
                                                                  <img src="${ud.getUtilisateurById(offre.idRecruteur).photoProfil}" style="width:50px;height: 50px;margin-right: 10px;border-radius: 1000px;border:2px solid black"/>  ${ud.getUtilisateurById(offre.idRecruteur).nom}
                                   
                                </div>
                                <div class="card-body" style="text-align:center">
                                      <strong>    ${offre.titre} <br> <hr></strong>
                                    <a href="detailsOffre.jsp?idOffre=${offre.idOffre}" class="btn btn-info" style="font-size: 14px">
                                        <i class="fas fa-eye"></i> Voir Détails
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

     

        <%@ include file="footer.jsp" %>

        <!-- JS Scripts -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
