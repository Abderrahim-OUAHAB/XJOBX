/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MesServlets;

import DAO.CandidatureDAO;
import DAO.LikeDAO;
import DAO.OffreDAO;
import MesBeans.Offres;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author minfo
 */
@WebServlet(name = "EditOffreServlet", urlPatterns = {"/EditOffreServlet"})
public class EditOffreServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // Récupérer les paramètres de l'offre depuis le formulaire
        String idOffreParam = request.getParameter("idOffre");
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        String competences = request.getParameter("competences");

        // Vérifier que l'ID de l'offre est valide
        if (idOffreParam != null && !idOffreParam.isEmpty()) {
            int idOffre = Integer.parseInt(idOffreParam);
            
            // Créer un objet OffreDAO pour manipuler la base de données
            OffreDAO offreDAO = new OffreDAO();
            
            // Récupérer l'offre existante pour modification
            Offres offre = offreDAO.getOffreById(idOffre);
            
            if (offre != null) {
                // Modifier les propriétés de l'offre
                offre.setTitre(titre);
                offre.setDescription(description);
                offre.setCompetences(competences);

                // Mettre à jour l'offre dans la base de données
                String updated = offreDAO.updateOffre(offre);
                
                // Vérifier si la mise à jour a réussi
                if (updated.equals("OK")) {
              String message = "Offre ajoutée avec succès.";
OffreDAO od=new OffreDAO();
     
        List<Offres> offres=od.getAllOffres();
     
        request.getSession().setAttribute("offres", offres);
        request.setAttribute("message", message);
        request.getRequestDispatcher("Offres.jsp").forward(request, response);
                } else {
                    // Afficher un message d'erreur
                    response.sendRedirect("modifierOffre.jsp?idOffre=" + idOffre + "&error=true");
                }
            } else {
                // Si l'offre n'existe pas, rediriger avec un message d'erreur
                response.sendRedirect("modifierOffre.jsp?idOffre=" + idOffre + "&error=true");
            }
        } else {
            // Si l'ID de l'offre est invalide, rediriger avec un message d'erreur
            response.sendRedirect("offres.jsp?error=true");
        }
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
