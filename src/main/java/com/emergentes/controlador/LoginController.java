package com.emergentes.controlador;

import com.emergentes.utiles.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            //verificar datos
            
            Validate v = new Validate();
            if(v.ckeckUser(email, password)){
                System.out.println("todo bien");
                HttpSession ses = request.getSession();
                ses.setAttribute("login","OK");
                response.sendRedirect("ClienteController");
            }else{
                System.out.println("incorrecto");
                response.sendRedirect("login.jsp");
            }
    }

}
