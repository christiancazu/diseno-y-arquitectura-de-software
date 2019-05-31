package dao.impl;

import dao.IAlumnoDAO;
import utils.MySQLDBConexion;
import entidades.Alumno;
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
public class MySQLAlumnoDAO implements IAlumnoDAO {

    private final String ALUMNO = "alumno";

    @Override
    public List<Alumno> getAll() {
        List<Alumno> alumnos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = MySQLDBConexion.getConexion();
            
            String sql = "SELECT * FROM " + ALUMNO;
            
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Alumno alumno = null;
            
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setEdad(rs.getInt("edad"));
                
                alumnos.add(alumno);
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
        
        return alumnos;
    }

    @Override
    public int crear(Alumno alumno) {
        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = MySQLDBConexion.getConexion();
            
            String sql = "INSERT INTO " + ALUMNO + 
                    " VALUES(null,?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, alumno.getNombre());
            pstm.setString(2, alumno.getApellido());
            pstm.setInt(3, alumno.getEdad());
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
        int salida = -1;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {           
            conn = MySQLDBConexion.getConexion();
            
            String sql =
                    "DELETE FROM " + ALUMNO +
                    " WHERE id = ?";            
  
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
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
    public int actualizar(Alumno alumno) {
        int salida = -1;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {           
            conn = MySQLDBConexion.getConexion();
            
            String sql =
                "UPDATE " + ALUMNO +
                " SET nombre = ?, apellido = ?, edad = ? " +
                " WHERE id = ?";            
  
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, alumno.getNombre());
            pstm.setString(2, alumno.getApellido());
            pstm.setInt(3, alumno.getEdad());
            pstm.setInt(4, alumno.getId());
            
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
    public Alumno getById(int id) {
        Alumno alumno = null;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {           
            conn = MySQLDBConexion.getConexion();
            
            String sql =
                    "SELECT * " +
                    "FROM " + ALUMNO + " " +
                    "WHERE id = ?";            
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            rs.next();       
            
            alumno = new Alumno();            
            alumno.setId(rs.getInt("id"));
            alumno.setNombre(rs.getString("nombre"));
            alumno.setApellido(rs.getString("apellido"));
            alumno.setEdad(rs.getInt("edad"));

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

        return alumno;
    }
}

