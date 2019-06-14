package controladores;

import entidades.Auto;
import PatronBuilder.BuilderAuto;
import PatronBuilder.ConstructorAutoBase;
import PatronBuilder.ConstructorAutoFull;
import PatronBuilder.ConstructorAutoMedio;
import PatronBuilder.Director;
import dataContext.DataContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "BuilderControlador", urlPatterns = {"/builder/*"})
public class BuilderControlador extends HttpServlet {

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

        if (request.getPathInfo().equals("/presentacion")) {
            presentacion(request, response);
        } else {
            demo(request, response);
        }
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

        // Crear el objeto Director
        Director objFabrica = new Director();
        // Crear los objetos ConcreteBuilder
        BuilderAuto base = new ConstructorAutoBase();
        BuilderAuto medio = new ConstructorAutoMedio();
        BuilderAuto full = new ConstructorAutoFull();
      

        switch (request.getParameter("id")) {
            case "base":
                // Construir un coche con equipamiento base
                objFabrica.construir(base);
                Auto autoBase = base.getAuto();
                DataContext.addAutoToList(autoBase);
            break;
            case "medio":
                // Construir un coche con equipamiento medio
                objFabrica.construir(medio);
                Auto autoMedio = medio.getAuto();
                DataContext.addAutoToList(autoMedio);
            break;
            case "full":
                // Construir un coche con equipamiento full
                objFabrica.construir(full);
                Auto autoFull = full.getAuto();
                DataContext.addAutoToList(autoFull);
            break;
        }
        
        request.setAttribute("success", true);

        demo(request, response);
    }

    protected void presentacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/builderPresentacion.jsp").forward(request, response);
    }

    protected void demo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("autos", DataContext.getDataState());

        request.getRequestDispatcher("/WEB-INF/pages/builderDemo.jsp").forward(request, response);
    }

}
