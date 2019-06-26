/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import acciones.AlumnoAccion;
import antlr.collections.Enumerator;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "AlumnosControlador", urlPatterns = {"/alumnos/*"})
public class AlumnosControlador extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {                
        
        // getting accion depending request infoPath
        AlumnoAccion peliculaAccion = AlumnoAccion.getAccion(request);   
        
        // executing accion assigned from infoPath
        RequestDispatcher despachador = peliculaAccion.ejecutar();
        
        // dispatching request
        despachador.forward(request, response);        
    }

}
