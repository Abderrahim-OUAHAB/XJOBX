/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MesServlets;

import DAO.ProfilDAO;
import DAO.UtilisateurDAO;
import MesBeans.Profils;
import MesBeans.Utilisateurs;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author minfo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        
        String motDePasse = request.getParameter("motDePasse");
        
        Utilisateurs utilisateur = new Utilisateurs();
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(motDePasse);
        
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        String result = utilisateurDAO.authentification(utilisateur);
        ProfilDAO profilDao = new ProfilDAO();
        if ("OK".equals(result)) {
           
             Utilisateurs u = utilisateurDAO.getUtilisateurByEmail(email);
             Profils p=profilDao.getProfilByIdUser(u.getIdUtilisateur());
              if(p!=null){
                  
                   request.getSession().setAttribute("utilisateur", u);
                   request.getSession().setAttribute("profil", p);
            request.getSession().setAttribute("userConnected", u.getNom());
             request.getSession().setAttribute("id_utilisateur", u.getIdUtilisateur());
              request.getSession().setAttribute("role", u.getRole());
            response.sendRedirect("Home.jsp");
              }else{
                   request.getSession().setAttribute("profil", p);
                    request.getSession().setAttribute("utilisateur", u);
                   request.getSession().setAttribute("userConnected", u.getNom());
                    request.getSession().setAttribute("id_utilisateur", u.getIdUtilisateur());
              request.getSession().setAttribute("role", u.getRole());
            response.sendRedirect("profile.jsp");
            
              }
        } else {
            request.setAttribute("error", "Email ou mot de passe incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
