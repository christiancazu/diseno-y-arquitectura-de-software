package controladores;

import dao.IEncuestaDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.FullPelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "ListarPeliculasControlador", urlPatterns = {"/peliculas"})
public class ListarPeliculasControlador extends HttpServlet {

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

        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();
        IEncuestaDAO iEncuestaDAO = subFabrica.getEncuestaDAO();

        try {
            List<Pelicula> peliculas = iPeliculaDAO.getAll();
            List<Encuesta> encuestas = iEncuestaDAO.getAll();

            // assign data: Pelicula, likes, dislikes for each Pelicula
            request.setAttribute("fullPeliculas", assignFullDataToPeliculas(peliculas, encuestas));
        } catch (Exception ex) {
            Logger.getLogger(ListarPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("WEB-INF/pages/peliculas.jsp").forward(request, response);
    }

    private List<FullPelicula> assignFullDataToPeliculas(List<Pelicula> peliculas, List<Encuesta> encuestas) {
        List<FullPelicula> fullPeliculas = new ArrayList<>();

        // fill peliculas to List fullPeliculas
        peliculas.forEach((pelicula) -> {
            fullPeliculas.add(new FullPelicula(pelicula));
        });

        // fetching Pelicula depending of id on encuesta.pelicula
        encuestas.forEach((encuesta) -> {
            FullPelicula peliculaById = fullPeliculas.stream()
                    .filter(peliculaFull -> encuesta.getPelicula().getId().equals(peliculaFull.getPelicula().getId()))
                    .findAny()
                    .orElse(null);

            // setting vote to each fullPeliculas item
            if (encuesta.getVoto() == 'S') {
                fullPeliculas.get(fullPeliculas.indexOf(peliculaById)).addLike();
            } else {
                fullPeliculas.get(fullPeliculas.indexOf(peliculaById)).addDislike();
            }
        });

        return fullPeliculas;
    }
}
