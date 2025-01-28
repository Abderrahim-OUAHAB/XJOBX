package MesServlets;

import DAO.ProfilDAO;
import DAO.UtilisateurDAO;
import MesBeans.Profils;
import MesBeans.Utilisateurs;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les informations soumises
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String confirmationMotDePasse = request.getParameter("confirmationMotDePasse");
        String ancienMotDePasse = request.getParameter("ancienMotDePasse");
        String competences = request.getParameter("competences");
        String experienceParam = request.getParameter("experience");
         Integer utilisateurId = (Integer) request.getSession().getAttribute("id_utilisateur");
        int experience = 0; // Default value if experience is not provided
        if (experienceParam != null && !experienceParam.isEmpty()) {
            try {
                experience = Integer.parseInt(experienceParam);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid experience value.");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }
        }
        
        // Vérification de la confirmation du mot de passe
        if (!motDePasse.equals(confirmationMotDePasse)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas.");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        // Vérification de l'ancien mot de passe
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateurs utilisateur = utilisateurDAO.getUtilisateurById(utilisateurId);
        
        if (utilisateur == null || !utilisateur.getMotDePasse().equals(ancienMotDePasse)) {
            request.setAttribute("error", "L'ancien mot de passe est incorrect.");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        // Traitement de la photo de profil
        Part photoProfilPart = request.getPart("photoProfil");
        String photoProfil = null;
        if (photoProfilPart != null && photoProfilPart.getSize() > 0) {
            // Sauvegarder la photo si elle a été téléchargée
            String photoFileName = Paths.get(photoProfilPart.getSubmittedFileName()).getFileName().toString();
            photoProfil = savePhoto(photoProfilPart, photoFileName);
        }
        
         // Traitement de cv
        Part cvPart = request.getPart("cv");
        String cv = null;
        if (cvPart != null && cvPart.getSize() > 0) {
            // Sauvegarder la photo si elle a été téléchargée
            String cvFileName = Paths.get(cvPart.getSubmittedFileName()).getFileName().toString();
            cv = savePhoto(cvPart, cvFileName);
        }

        // Mettre à jour le profil de l'utilisateur dans la base de données
        ProfilDAO profilDao = new ProfilDAO();
        if (utilisateur != null) {
            // Mise à jour des informations de l'utilisateur
            utilisateur.setNom(nom);
            utilisateur.setEmail(email);
            utilisateur.setMotDePasse(motDePasse); // Mise à jour du mot de passe
          
            if (photoProfil != null) {
                utilisateur.setPhotoProfil(photoProfil); // Mise à jour de la photo de profil
            }

            
            // Sauvegarder les changements dans la base
            utilisateurDAO.updateUtilisateur(utilisateur);
            Profils pr=profilDao.getProfilByIdUser(utilisateurId);
            if(pr==null){
                // Mettre à jour le profil
            Profils profil = new Profils();
            profil.setCompetences(competences);
            profil.setExperience(experience);
           
            profil.setIdUtilisateur(utilisateurId);
             if (cv != null) {
                profil.setCv(cv); // Mise à jour de la photo de profil
            }
            profil.setDateCreation(new Date());

                 profilDao.addProfil(profil);
                  request.getSession().setAttribute("profil", profil);
            }else{
                 pr.setCompetences(competences);
            pr.setExperience(experience);
           
            pr.setIdUtilisateur(utilisateurId);
             if (cv != null) {
                pr.setCv(cv); // Mise à jour de la photo de profil
            }
            pr.setDateCreation(new Date());
            profilDao.updateProfil(pr);
             request.getSession().setAttribute("profil", pr);
            }
            
            request.getSession().setAttribute("utilisateur", utilisateur);
            
            response.sendRedirect("information.jsp");
        } else {
            request.setAttribute("error", "Erreur lors de la mise à jour du profil.");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }

private String savePhoto(Part photoPart, String fileName) {
    // Chemin absolu vers le répertoire "upload" dans webapp
    String uploadDirectory = "/Users/minfo/Documents/XJOBX/src/main/webapp/upload";

    // Nom unique pour éviter les conflits
    String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
    String photoPath = uploadDirectory + "/" + uniqueFileName;

    try {
        // Créer le répertoire si nécessaire
        Files.createDirectories(Paths.get(uploadDirectory));

        // Sauvegarder le fichier
        try (InputStream input = photoPart.getInputStream()) {
            Files.copy(input, Paths.get(photoPath), StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Photo sauvegardée à : " + photoPath);
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Retourner le chemin relatif pour être utilisé dans les JSP
    return "upload/"+uniqueFileName;
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
