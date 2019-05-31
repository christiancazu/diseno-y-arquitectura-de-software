package dao.impl;

import dao.ICursoDAO;
import utils.MySQLDBConexion;
import entidades.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLCursoDAO implements ICursoDAO { 
    
    private final String CURSO = "curso";

    @Override
    public List<Curso> getAll() {
        List<Curso> cursos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = MySQLDBConexion.getConexion();
            
            String sql = "SELECT * FROM " + CURSO;
            
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Curso curso = null;
            
            while (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNombre(rs.getString("nombre"));
                curso.setDocente(rs.getString("docente"));
                curso.setLugar(rs.getString("lugar"));
                
                cursos.add(curso);
            }
        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        
        return cursos;
    }

    @Override
    public int crear(Curso curso) {
        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = MySQLDBConexion.getConexion();
            
            String sql = "INSERT INTO " + CURSO + 
                    " VALUES(null,?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, curso.getNombre());
            pstm.setString(2, curso.getDocente());
            pstm.setString(3, curso.getLugar());
            salida = pstm.executeUpdate();

        } catch (SQLException e) {
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e2) {
            }
        }
        
        return salida;
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Curso curso) {
        int salida = -1;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {           
            conn = MySQLDBConexion.getConexion();
            
            String sql =
                "UPDATE " + CURSO +
                " SET nombre = ?, docente = ?, lugar = ? " +
                " WHERE id = ?";            
  
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, curso.getNombre());
            pstm.setString(2, curso.getDocente());
            pstm.setString(3, curso.getLugar());
            pstm.setInt(4, curso.getId());
            
            salida = pstm.executeUpdate();
            
        } catch (SQLException e) {
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return salida;
    }
    
    @Override
    public Curso getById(int id) {
        Curso curso = null;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {           
            conn = MySQLDBConexion.getConexion();
            
            String sql =
                    "SELECT * " +
                    "FROM " + CURSO + " " +
                    "WHERE id = ?";            
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            rs.next();       
            
            curso = new Curso();            
            curso.setId(rs.getInt("id"));
            curso.setNombre(rs.getString("nombre"));
            curso.setDocente(rs.getString("docente"));
            curso.setLugar(rs.getString("lugar"));

        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return curso;
    }
 
}
