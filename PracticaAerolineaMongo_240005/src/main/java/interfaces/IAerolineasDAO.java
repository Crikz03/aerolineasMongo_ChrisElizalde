/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.mongodb.MongoException;
import excepciones.PersistenciaException;
import java.util.List;
import objetos.Aerolinea;

/**
 *
 * @author Chris
 */
public interface IAerolineasDAO {

    public boolean agregar(Aerolinea a) throws MongoException;

    public boolean actualizar(Aerolinea a) throws MongoException;

    public boolean eliminar(Aerolinea a) throws MongoException;

    public Aerolinea consultar(Aerolinea a) throws MongoException;

    public List<Aerolinea> consultarTodos() throws MongoException;
}
