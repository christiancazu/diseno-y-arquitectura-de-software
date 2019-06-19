package controladores;

import entidades.Auto;
import PatronBuilder.BuilderAuto;
import PatronBuilder.ConstructorAutoBase;
import PatronBuilder.ConstructorAutoFull;
import PatronBuilder.ConstructorAutoMedio;
import PatronBuilder.Director;
import dataContext.DataContext;
import java.io.IOException;
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
        Director director = new Director();
        BuilderAuto builderAuto = null;

        switch (request.getParameter("id")) {
            case "base":
                // Construir un coche con equipamiento base
                builderAuto = new ConstructorAutoBase();
                director.construir(builderAuto);
                break;
            case "medio":
                // Construir un coche con equipamiento medio
                builderAuto = new ConstructorAutoMedio();
                director.construir(builderAuto);
                break;
            case "full":
                // Construir un coche con equipamiento full
                builderAuto = new ConstructorAutoFull();
                director.construir(builderAuto);
                break;
        }
        DataContext.addAutoToList(builderAuto.getAuto());

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
