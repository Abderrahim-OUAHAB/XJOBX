<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="Home.jsp">
            <img src="images/logo2.webp" alt="XJOBX Logo" width="50" height="50"> HI, ${userConnected}
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="Home.jsp">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ListeOffre">Offres d'emploi</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="information.jsp">Mon Profil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">À propos</a>
                </li>
               <li class="nav-item">
    <% 
        if (session.getAttribute("userConnected") != null) { // Si l'utilisateur est connecté
    %>
        <a class="btn btn-danger" href="LogoutServlet">Se déconnecter</a>
    <% 
        } else { // Si l'utilisateur n'est pas connecté
    %>
        <a class="btn btn-primary" href="login.jsp">Se connecter</a>
    <% 
        } 
    %>
</li>

            </ul>
        </div>
    </div>
</nav>
