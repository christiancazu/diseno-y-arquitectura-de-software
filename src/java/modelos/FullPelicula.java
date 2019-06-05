package modelos;

import entidades.Pelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class FullPelicula {
    private Pelicula pelicula;
    private int likes;
    private int dislikes;

    public FullPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
        this.likes = 0;
        this.dislikes = 0;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    
    public void addLike() {
        this.likes++;
    }
    
    public void addDislike() {
        this.dislikes++;
    }
}
