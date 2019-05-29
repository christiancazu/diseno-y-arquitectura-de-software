/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Curso;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface ICursoDAO {
  
    public List<Curso> listarCursos();
    
    public int insertarCurso(Curso curso);
}
