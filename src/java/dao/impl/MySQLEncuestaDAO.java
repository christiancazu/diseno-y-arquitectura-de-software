package dao.impl;

import dao.IEncuestaDAO;
import entidades.Encuesta;
import entidades.Genero;
import entidades.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.MySQLDBConexion;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLEncuestaDAO extends MySQLDBConexion implements IEncuestaDAO {

    private static final String ENCUESTA = "encuesta";
    
    @Override
    public Encuesta getById(int id) throws Exception {
        Encuesta encuesta = null;   
                
        try {           
            this.getConexion();
            
            String sql =
                    "SELECT *" +
                    " FROM " + ENCUESTA +
                    " WHERE id = ?";            
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            rs.next();
            
            encuesta = new Encuesta();            
            encuesta.setId(rs.getInt("id"));
            encuesta.setPelicula(getPeliculaById(rs.getInt("pelicula")));
            encuesta.setVoto(rs.getString("voto").charAt(0));
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return encuesta;
    }

    @Override
    public List<Encuesta> getAll() throws Exception {
        List<Encuesta> encuestas = null;   
                
        try {           
            this.getConexion();
            
            String sql = "SELECT * FROM " + ENCUESTA;            
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            encuestas = new ArrayList<>();
            
            while (rs.next()) {
                Encuesta encuesta = new Encuesta();           
                encuesta.setId(rs.getInt("id"));
                encuesta.setPelicula(getPeliculaById(rs.getInt("pelicula")));
                encuesta.setVoto(rs.getString("voto").charAt(0));
                encuestas.add(encuesta);
            }    

        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return encuestas;
    }

    @Override
    public boolean crear(Encuesta encuesta) throws Exception {
        int resultado = 0;   
                
        try {           
            this.getConexion();
            
            String sql =
                    "INSERT INTO " + ENCUESTA + 
                    " VALUES(null, ?, ?)";           
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            
            pstm.setInt(1, encuesta.getPelicula().getId());
            pstm.setString(2, String.valueOf(encuesta.getVoto()));
            
            resultado = pstm.executeUpdate();            
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return resultado == 1;
    }

    @Override
    public boolean actualizar(Encuesta encuesta) throws Exception {
        int resultado = 0;
                
        try {           
            this.getConexion();
            
            String sql =
                    "UPDATE " + ENCUESTA +
                    " SET pelicula = ?, voto = ?" +
                    " WHERE id = ?";           
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            
            pstm.setInt(1, encuesta.getPelicula().getId());
            pstm.setString(2, String.valueOf(encuesta.getVoto()));
            pstm.setInt(3, encuesta.getId());
            
            resultado = pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return resultado == 1;
    }

    @Override
    public boolean eliminar(int id) throws Exception { 
        int resultado = 0;
        
        try {           
            this.getConexion();
            
            String sql =
                    "DELETE FROM " + ENCUESTA +
                    " WHERE id = ?";            
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
                        
            pstm.setInt(1, id);
            
            resultado = pstm.executeUpdate();           
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return resultado == 1;
    } 
    
    private Pelicula getPeliculaById(int id) throws Exception {     
        MySQLPeliculaDAO mySQLPeliculaDAO = new MySQLPeliculaDAO(); 
        
        return mySQLPeliculaDAO.getById(id);
    }
}
