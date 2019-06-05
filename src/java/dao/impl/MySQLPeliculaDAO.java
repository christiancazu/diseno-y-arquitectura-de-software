package dao.impl;

import dao.IPeliculaDAO;
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
public class MySQLPeliculaDAO extends MySQLDBConexion implements IPeliculaDAO {

    private static final String PELICULA = "pelicula";
    
    @Override
    public Pelicula getById(int id) throws Exception {
        Pelicula pelicula = null;   
                
        try {           
            this.getConexion();
            
            String sql =
                    "SELECT *" +
                    " FROM " + PELICULA +
                    " WHERE id = ?";            
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            rs.next();          
            
            pelicula = new Pelicula();            
            pelicula.setId(rs.getInt("id"));
            pelicula.setNombre(rs.getString("nombre"));
            pelicula.setDescripcion(rs.getString("descripcion"));
            pelicula.setImagen(rs.getString("imagen"));
            pelicula.setGenero(getGeneroById(rs.getInt("genero")));

        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return pelicula;
    }

    @Override
    public List<Pelicula> getAll() throws Exception {
        List<Pelicula> peliculas = null;   
                
        try {           
            this.getConexion();
            
            String sql = "SELECT * FROM " + PELICULA;            
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            peliculas = new ArrayList<>();
            
            while (rs.next()) {                
                Pelicula pelicula = new Pelicula();            
                pelicula.setId(rs.getInt("id"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                pelicula.setImagen(rs.getString("imagen"));
                pelicula.setGenero(getGeneroById(rs.getInt("genero")));
                peliculas.add(pelicula);
            }    

        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return peliculas;
    }

    @Override
    public boolean crear(Pelicula pelicula) throws Exception {
        int resultado = 0;   
                
        try {           
            this.getConexion();
            
            String sql =
                    "INSERT INTO " + PELICULA + 
                    " VALUES(null, ?, ?, ?, ?)";           
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            
            pstm.setString(1, pelicula.getNombre());
            pstm.setString(2, pelicula.getDescripcion());
            pstm.setString(3, pelicula.getImagen());            
            pstm.setInt(4, pelicula.getGenero().getId());
            
            resultado = pstm.executeUpdate();            
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return resultado == 1;
    }

    @Override
    public boolean actualizar(Pelicula pelicula) throws Exception {
        int resultado = 0;
                
        try {           
            this.getConexion();
            
            String sql =
                    "UPDATE " + PELICULA +
                    " SET nombre = ?, descripcion = ?, genero = ?" +                    
                    " WHERE id = ?";  
            
            if(pelicula.getImagen() != null) {
                sql =
                    "UPDATE " + PELICULA +
                    " SET nombre = ?, descripcion = ?, genero = ?, imagen = ?" +                    
                    " WHERE id = ?";
            }                        
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            
            pstm.setString(1, pelicula.getNombre());
            pstm.setString(2, pelicula.getDescripcion());
            pstm.setInt(3, pelicula.getGenero().getId());
                    
            if(pelicula.getImagen() != null) {
                pstm.setString(4, pelicula.getImagen());
                pstm.setInt(5, pelicula.getId());
            } else {
                pstm.setInt(4, pelicula.getId());
            }            
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
                    "DELETE FROM " + PELICULA +
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

    private Genero getGeneroById(int id) throws Exception {     
        MySQLGeneroDAO mySQLGeneroDAO = new MySQLGeneroDAO(); 
        
        return mySQLGeneroDAO.getById(id);
    }
}
