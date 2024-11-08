/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import conexion.ConexionBD;
import excepciones.PersistenciaException;
import interfaces.IAerolineasDAO;
import java.util.ArrayList;
import java.util.List;
import objetos.Aerolinea;
import org.bson.conversions.Bson;

/**
 *
 * @author Chris
 */
public class AerolineasDAO implements IAerolineasDAO {

    private final MongoCollection<Aerolinea> coleccionAerolineas;

    public AerolineasDAO() {
        this.coleccionAerolineas = ConexionBD.crearConexion().getCollection("airlines", Aerolinea.class);
    }

    @Override
    public boolean agregar(Aerolinea a) throws MongoException {
        try {
            this.coleccionAerolineas.insertOne(a);
            return true;
        } catch (Exception e) {
            throw new MongoException("No se pudo insertar la aerolinea." + a.getId());
        }
    }

    @Override
    public boolean actualizar(Aerolinea a) throws MongoException {
        try {
            Bson filtroID = Filters.eq("_id", a.getId());

            Bson actualizacionDatos = Updates.combine(
                    Updates.set("name", a.getName()),
                    Updates.set("country", a.getCountry()),
                    Updates.set("currency", a.getCurrency()),
                    Updates.set("lowcost", a.isLowcost())
            );
            this.coleccionAerolineas.updateOne(filtroID, actualizacionDatos);
            return true;
        } catch (Exception e) {
            throw new MongoException("No se pudo actualizar la aerolinea: " + a.getId());
        }
    }

    @Override
    public boolean eliminar(Aerolinea a) throws MongoException {
        try {
            DeleteResult result = this.coleccionAerolineas.deleteOne(Filters.eq("_id", a.getId()));
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Aerolinea consultar(Aerolinea a) throws MongoException{
        try {
            Aerolinea result = (Aerolinea) this.coleccionAerolineas.find(eq("_id", a.getId())).first();
            return result;
        } catch (Exception e) {
            throw new MongoException("No se pudo encontrar la aerolinea: " + a.getId());
        }
    }

    @Override
    public List<Aerolinea> consultarTodos() throws MongoException{
        try {
            List<Aerolinea> aerolineas = new ArrayList<>();
            this.coleccionAerolineas.find().into(aerolineas);
            return aerolineas;
        } catch (Exception e) {
            throw new MongoException("No se pudieron encontrar las aerolineas.");
        }
    }

}
