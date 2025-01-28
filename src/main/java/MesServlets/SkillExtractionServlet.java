package MesServlets;

import DAO.OffreDAO;
import MesBeans.Offres;
import MesBeans.Profils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SkillExtractionServlet", urlPatterns = {"/SkillExtractionServlet"})
public class SkillExtractionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession().getAttribute("role");
        if("chercheur_emploi".equals(role)){
            
        
  Profils profil = (Profils) request.getSession().getAttribute("profil");
        String cvPath = profil.getCv(); // Chemin du CV

        // Récupérer et formater les compétences
        List<String> jobDescriptions = getJobDescriptionsFromDB();
        String formattedJobDescriptions = String.join("||", jobDescriptions);

        // Construire la commande pour appeler le script Python
        ProcessBuilder pb = new ProcessBuilder(
            "/Users/minfo/anaconda3/bin/python", // Chemin complet vers Python
            "/Users/minfo/Documents/XJOBX/src/main/java/MesServlets/skill_extraction.py", 
            "/Users/minfo/Documents/XJOBX/src/main/webapp/" + cvPath, 
            formattedJobDescriptions
        );
        pb.redirectErrorStream(true); // Combine la sortie standard et les erreurs
        Process process = pb.start();

        // Lire les résultats du script Python (les IDs des offres)
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<String> offerIds = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            offerIds.add(line.trim()); // Ajouter chaque ID d'offre extrait
        }

        try {
            process.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(SkillExtractionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Utiliser les IDs pour récupérer les offres correspondantes depuis la base de données
        OffreDAO offreDAO = new OffreDAO();
        List<Offres> suggestedOffres = new ArrayList<>();
        for (String offerId : offerIds) {
            Offres offre = offreDAO.getOffreById(Integer.parseInt(offerId)); // Récupérer chaque offre par son ID
            if (offre != null) {
                suggestedOffres.add(offre);
            }
        }

        // Passer les suggestions d'offres à la JSP
        request.getSession().setAttribute("suggestedOffres", suggestedOffres);
        }
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }

    private List<String> getJobDescriptionsFromDB() {
        OffreDAO offreDAO = new OffreDAO();
        List<String> competences = new ArrayList<>();
        for (Offres offre : offreDAO.getAllOffres()) {
            // Ajouter l'ID de l'offre et sa description de compétences
              String comp = String.join(" ", offre.getCompetences())
                                                .replace("\n", " ") // Supprime les sauts de ligne
                                                .replace("\r", " ") // Supprime les retours chariot
                                                .replace("|", " "); // Évite les conflits avec le délimiteur

            competences.add(offre.getIdOffre()+ ":" +comp );
        }
        return competences;
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
}

