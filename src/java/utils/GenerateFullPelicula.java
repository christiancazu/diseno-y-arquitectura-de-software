package utils;

import entidades.Encuesta;
import entidades.Pelicula;
import java.util.ArrayList;
import java.util.List;
import modelos.FullPelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class GenerateFullPelicula {
    public static List<FullPelicula> assignFullDataToPeliculas(List<Pelicula> peliculas, List<Encuesta> encuestas) {
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
