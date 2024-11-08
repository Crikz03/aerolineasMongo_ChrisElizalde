/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import conexion.ConexionBD;
import java.util.ArrayList;
import objetos.Aerolinea;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Chris
 */
public class Aerolineas {

    private final MongoCollection<Document> coleccionAerolineas;

    public Aerolineas() {
        this.coleccionAerolineas = ConexionBD.crearConexion().getCollection("airlines");
    }

    private Document objectToDocument(Aerolinea a) {
        Document aerolinea = new Document()
                .append("name", a.getName())
                .append("country", a.getCountry())
                .append("currency", a.getCurrency())
                .append("lowcost", a.isLowcost());
        return aerolinea;
    }

    public ObjectId insertAirline(Aerolinea a) {
        ObjectId id = new ObjectId();
        Document aerolinea = this.objectToDocument(a).append("_id", id);
        this.coleccionAerolineas.insertOne(aerolinea);
        return id;
    }

    public boolean updateAirline(Aerolinea a) {
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
        } catch (MongoException e) {
            throw new MongoException("No se pudo actualizar la aerolinea: " + a.getId());
        }
    }

    public boolean deleteAirlineById(ObjectId id) {
        try {
            Bson idQuery = Filters.eq("_id", id);
            DeleteResult result = this.coleccionAerolineas.deleteOne(idQuery);
            return result.getDeletedCount() == 1;
        } catch (MongoException e) {
            throw new MongoException("No se pudo eliminar la aerolinea: " + id);
        }
    }

    public ArrayList<Aerolinea> getAllAirlines() {
        ArrayList<Aerolinea> aerolineas = new ArrayList();

        MongoCursor<Document> cursor = this.coleccionAerolineas.find().iterator();
        try {
            while (cursor.hasNext()) {

                Document d = cursor.next();

                Aerolinea a = new Aerolinea(d.getObjectId("_id"),
                        d.getString("country"),
                        d.getString("currency"),
                        d.getString("name"),
                        d.getBoolean("lowcost"));
                aerolineas.add(a);
            }
            return aerolineas;
        } finally {
            cursor.close();
        }
    }
}
