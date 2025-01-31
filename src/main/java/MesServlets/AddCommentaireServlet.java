/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MesServlets;

import DAO.CommentaireDAO;
import DAO.OffreDAO;
import MesBeans.Commentaires;
import MesBeans.Offres;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddCommentaireServlet", urlPatterns = {"/AddCommentaireServlet"})
public class AddCommentaireServlet extends HttpServlet {

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
       String message;

        try {
            // Retrieve form data
            String contenu = request.getParameter("contenu");
            int idOffre = Integer.parseInt(request.getParameter("idOffre"));
                 int idUser = (Integer)request.getSession().getAttribute("id_utilisateur");
          
           

            // Create an Offres object
            Commentaires comment = new Commentaires();
           comment.setContenu(contenu);
           comment.setDateCommentaire(new Date());
           comment.setIdOffre(idOffre);
           comment.setIdUtilisateur(idUser);
           

            // Save the offer using OffreDAO
            CommentaireDAO dao = new CommentaireDAO();
            String result = dao.addCommentaire(comment);

            if ("OK".equals(result)) {
                message = "Offre ajoutée avec succès.";
                   // Pass the message to the JSP page
                    
        
        List<Commentaires> commentaires=dao.getCommentairesByOffreId(idOffre);
        request.getSession().setAttribute("commentaires", commentaires);
 request.setAttribute("idOffre", idOffre);
        request.setAttribute("message", message);
        request.getRequestDispatcher("detailsOffre.jsp").forward(request, response);
            } else {
                message = "Erreur lors de l'ajout !";
                   // Pass the message to the JSP page
        request.setAttribute("message", message);
        request.getRequestDispatcher("addOffer.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            message = "Erreur : " + e.getMessage();
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
