/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
    public List<Curso> listarCursos() {
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
    public int insertarCurso(Curso curso) {
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
}
