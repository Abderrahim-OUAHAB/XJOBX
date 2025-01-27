/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MesServlets;

import DAO.CandidatureDAO;
import DAO.LikeDAO;
import DAO.OffreDAO;
import DAO.UtilisateurDAO;
import MesBeans.Candidatures;
import MesBeans.Likes;
import MesBeans.Offres;
import MesBeans.Utilisateurs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "PostulerServlet", urlPatterns = {"/PostulerServlet"})
public class PostulerServlet extends HttpServlet {

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
 // Retrieve user and offer IDs
      CandidatureDAO candDAO = new CandidatureDAO();
       UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
       Utilisateurs u = (Utilisateurs)request.getSession().getAttribute("utilisateur");
        OffreDAO od=new OffreDAO();
   
      
        Integer userId = (Integer) request.getSession().getAttribute("id_utilisateur"); // Assuming userId is stored in the session
        int offreId = Integer.parseInt(request.getParameter("offreId"));
        
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect if user is not logged in
            return;
        }

        // Check if the user already liked the offer
        Candidatures existingCand = candDAO.getCandidatureByUserAndOffer(userId, offreId);

        String message;
        if (existingCand != null) {
            // Unlike the offer
            String result = candDAO.deleteCandidature(existingCand.getIdCandidature());
            if ("OK".equals(result)) {
                message = "Like removed.";
            } else {
                message = "Failed to remove like.";
            }
        } else {
            // Add like
            Candidatures newCand = new Candidatures();
            newCand.setIdUtilisateur(userId);
            newCand.setIdOffre(offreId);
            newCand.setDateCreation(new Date());
            newCand.setDatePostulation(new Date());
            newCand.setStatut("en_attente");
            String result = candDAO.addCandidature(newCand);
            if ("OK".equals(result)) {
                message = "Like added.";
            } else {
                message = "Failed to add like.";
            }
        }
   
      
        request.setAttribute("message", message);
        request.getRequestDispatcher("Offres.jsp").forward(request, response);
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
