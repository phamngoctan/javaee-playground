package com.tanpham.javaee.playground.helloworld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "AuthenticateServlet", urlPatterns = {"/authenticateServlet"})
public class AuthenticateServlet {//extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
//	@Inject
//    private SampleEJB sampleEJB;

//	@Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username != null && password != null) {
                request.login(username, password);
            }

            CustomPrincipal principal = (CustomPrincipal) request.getUserPrincipal();
            response.getWriter().println("principal=" + request.getUserPrincipal().getClass().getSimpleName());
//            response.getWriter().println("username=" + sampleEJB.getPrincipalName());
            response.getWriter().println("description=" + principal.getDescription());

//            response.setContentType("text/html;charset=UTF-8");
//
//            try (PrintWriter out = response.getWriter()) {
//                request.authenticate(response);
//                out.println("Authenticate Successful");
//            }
        } catch (ServletException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}