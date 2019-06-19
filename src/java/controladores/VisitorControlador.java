package controladores;

import dataContext.DataContext;
import entidades.Auto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import PatronVisitor.AccelerationInCurve;
import PatronVisitor.AccelerationInPlane;
import PatronVisitor.Visitor;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="VisitorControlador", urlPatterns={"/visitor/*"})
public class VisitorControlador extends HttpServlet {
   
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        if (request.getPathInfo().equals("/presentacion")) {
            presentacion(request, response);
        } else {
            demo(request, response);            
        }   
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        double FRICTION_FACTOR = 20.15;        
        double motorHorsePower = 0;   
        
        int idAuto = Integer.parseInt(request.getParameter("id"));

        Auto autoSelected = DataContext.getDataState().get(idAuto);
       
        switch(autoSelected.getTipo()) {
            case "base":
                motorHorsePower = 420;
                break;
            case "medio":
                motorHorsePower = 640;
                break;
            case "full":
                motorHorsePower = 780;
                break;
        }           

        Visitor visitor;
    
        if (request.getParameter("prueba").equals("plano")) {
            visitor = new AccelerationInPlane(motorHorsePower);
        } else {
            visitor = new AccelerationInCurve(motorHorsePower, FRICTION_FACTOR);
            
        }               

        request.setAttribute("resultado", autoSelected.acceptVisitor(visitor));  
        
        request.setAttribute("autoSelected", autoSelected);
        request.setAttribute("prueba", request.getParameter("prueba"));
        request.setAttribute("success", true);

        demo(request, response);        
    }
    
    protected void presentacion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/pages/visitorPresentacion.jsp").forward(request, response);
    }

    protected void demo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("autos", DataContext.getDataState());

        request.getRequestDispatcher("/WEB-INF/pages/visitorDemo.jsp").forward(request, response);
    }
    
}
