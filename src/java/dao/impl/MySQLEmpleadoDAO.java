package dao.impl;

import dao.IEmpleadoDAO;
import utils.MySQLDBConexion;
import entidades.Empleado;
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
public class MySQLEmpleadoDAO implements IEmpleadoDAO { 
    
    private final String EMPLEADO = "empleado";

    @Override
    public List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = MySQLDBConexion.getConexion();
            
            String sql = "SELECT * FROM " + EMPLEADO;
            
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Empleado empleado = null;
            
            while (rs.next()) {
                empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setLogin(rs.getString("login"));
                empleado.setClave(rs.getString("clave"));
                
                empleados.add(empleado);
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
        
        return empleados;
    }

    @Override
    public int crear(Empleado empleado) {
        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = MySQLDBConexion.getConexion();
            
            String sql = "INSERT INTO " + EMPLEADO + 
                    " VALUES(null,?,?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, empleado.getNombre());
            pstm.setString(2, empleado.getApellido());
            pstm.setString(3, generarLogin(empleado));
            pstm.setString(4, generarLogin(empleado));
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
                    "DELETE FROM " + EMPLEADO +
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
    public int actualizar(Empleado empleado) {
        int salida = -1;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {           
            conn = MySQLDBConexion.getConexion();
            
            String sql =
                "UPDATE " + EMPLEADO +
                " SET nombre = ?, apellido = ?, login = ? " +
                " WHERE id = ?";            
  
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, empleado.getNombre());
            pstm.setString(2, empleado.getApellido());
            pstm.setString(3, empleado.getLogin());
            pstm.setInt(4, empleado.getId());
            
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
    public Empleado getById(int id) {
        Empleado empleado = null;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {           
            conn = MySQLDBConexion.getConexion();
            
            String sql =
                    "SELECT *" +
                    " FROM " + EMPLEADO +
                    " WHERE id = ?";            
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            rs.next();       
            
            empleado = new Empleado();            
            empleado.setId(rs.getInt("id"));
            empleado.setNombre(rs.getString("nombre"));
            empleado.setApellido(rs.getString("apellido"));
            empleado.setLogin(rs.getString("login"));
            empleado.setClave(rs.getString("clave"));

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

        return empleado;
    } 
    
    private String generarLogin(Empleado empleado) {
        return (
                empleado.getNombre().substring(0, 1) + 
                empleado.getApellido()
                ).toLowerCase();
    }
}
