package Modelo;

import Entidad.Alumno;
import Utils.MysqlDBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModelAlumno {
    
    private final String tbAlumno = "tbAlumno";

    public List<Alumno> listaAlumno() {        
        List<Alumno> alumnos = new ArrayList<Alumno>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = (Connection) MysqlDBConexion.getConexion();
            
            String sql = "SELECT * FROM " + tbAlumno;
            
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            Alumno obj = null;
            
            while (rs.next()) {
                obj = new Alumno();
                obj.setIdAlumno(rs.getInt("idtbalumno"));
                obj.setNombre(rs.getString("nombre"));
                obj.setApellido(rs.getString("apellido"));
                obj.setEdad(rs.getInt("edad"));
                alumnos.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (Exception e2) {
            }
        }
        return alumnos;
    }

    public int insertaAlumno(Alumno obj) {
        int salida = -1;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {            
            conn = (Connection) MysqlDBConexion.getConexion();
            
            String sql = "insert into " + tbAlumno + " values(null,?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getNombre());
            pstm.setString(2, obj.getApellido());
            pstm.setInt(3, obj.getEdad());
            salida = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return salida;
    }
    
    public List<Alumno> filtroAlumno(String apellido) {        
        List<Alumno> alumnos = new ArrayList<Alumno>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = (Connection) MysqlDBConexion.getConexion();
            
            String sql =
                    "SELECT * " +
                    "FROM " + tbAlumno + " " +
                    "WHERE apellido LIKE ?";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + apellido + "%");
            rs = pstm.executeQuery();
            
            Alumno obj = null;
            
            while (rs.next()) {
                obj = new Alumno();
                obj.setIdAlumno(rs.getInt("idtbalumno"));
                obj.setNombre(rs.getString("nombre"));
                obj.setApellido(rs.getString("apellido"));
                obj.setEdad(rs.getInt("edad"));
                alumnos.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (Exception e2) {
            }
        }
        return alumnos;
    }
    
    public Alumno buscarAlumno(int id) {        
        Alumno alumno = new Alumno();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = (Connection) MysqlDBConexion.getConexion();
            
            String sql =
                    "SELECT * " +
                    "FROM " + tbAlumno + " " +
                    "WHERE idtbalumno = ?";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, Integer.toString(id));
            rs = pstm.executeQuery();
                        
            rs.next();            
                alumno.setIdAlumno(rs.getInt("idtbalumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setEdad(rs.getInt("edad"));
            
            
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (Exception e2) {
            }
        }
        return alumno;
    }
    
    public int actualizarAlumno(Alumno alumno) throws Exception {
        Connection conn = null;
        PreparedStatement pstm = null;

        conn = (Connection) MysqlDBConexion.getConexion();

        String sql =
                "UPDATE " + tbAlumno + " " +
                "SET nombre = ?, apellido = ?, edad = ? " +
                "WHERE idtbalumno = ?";

        pstm = conn.prepareStatement(sql);
        pstm.setString(1, alumno.getNombre());
        pstm.setString(2, alumno.getApellido());
        pstm.setString(3, Integer.toString(alumno.getEdad()));
        pstm.setString(4, Integer.toString(alumno.getIdAlumno()));
        
        return  pstm.executeUpdate();        
    }
    
}
