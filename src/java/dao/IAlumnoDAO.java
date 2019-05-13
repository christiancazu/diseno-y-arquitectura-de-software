/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Alumno;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IAlumnoDAO {
    
    public Alumno getById(int id);
    
    public List<Alumno> getAll();
    
    public List<Alumno> getAllByNameSurname(String cadena, String tipo);
    
    public boolean store(Alumno alumno);
    
    public Alumno update(int id, Alumno alumno);
    
    public boolean delete(int id);
    
}
