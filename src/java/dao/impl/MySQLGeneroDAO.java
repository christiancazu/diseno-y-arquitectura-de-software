package dao.impl;

import dao.IGeneroDAO;
import entidades.Genero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.MySQLDBConexion;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLGeneroDAO extends MySQLDBConexion implements IGeneroDAO {

    private static final String GENERO = "genero";
    
    @Override
    public Genero getById(int id) throws Exception {
        Genero genero = null;   
                
        try {           
            this.getConexion();
            
            String sql =
                    "SELECT *" +
                    " FROM " + GENERO +
                    " WHERE id = ?";            
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            rs.next();       
            
            genero = new Genero();            
            genero.setId(rs.getInt("id"));
            genero.setNombre(rs.getString("nombre"));
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return genero;
    }

    @Override
    public List<Genero> getAll() throws Exception {
        List<Genero> generos = null;   
                
        try {           
            this.getConexion();
            
            String sql = "SELECT * FROM " + GENERO;            
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            generos = new ArrayList<>();
            
            while (rs.next()) {
                Genero genero = new Genero();            
                genero.setId(rs.getInt("id"));
                genero.setNombre(rs.getString("nombre"));
                generos.add(genero);
            }    

        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return generos;
    }

    @Override
    public boolean crear(Genero genero) throws Exception {
        int resultado = 0;   
                
        try {           
            this.getConexion();
            
            String sql =
                    "INSERT INTO " + GENERO + 
                    " VALUES(null, ?)";           
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            
            pstm.setString(1, genero.getNombre());
            
            resultado = pstm.executeUpdate();            
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConexion();
        }

        return resultado == 1;
    }

    @Override
    public boolean actualizar(Genero genero) throws Exception {
        int resultado = 0;
                
        try {           
            this.getConexion();
            
            String sql =
                    "UPDATE " + GENERO +
                    " SET nombre = ?" +
                    " WHERE id = ?";           
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            
            pstm.setString(1, genero.getNombre());
            pstm.setInt(2, genero.getId());
            
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
                    "DELETE FROM " + GENERO +
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
}
