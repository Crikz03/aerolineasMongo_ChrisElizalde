/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practicaaerolineamongo_240005;

import com.mongodb.MongoException;
import datos.Aerolineas;
import datos.AerolineasDAO;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Aerolinea;
import org.bson.types.ObjectId;

/**
 *
 * @author Chris
 */
public class PracticaAerolineaMongo_240005 {

    public static void main(String[] args)  {
        AerolineasDAO aerolineadao = new AerolineasDAO();
        Aerolineas aerolineas = new Aerolineas();

        // Agregar una nueva aerolinea.
        Aerolinea aerolinea1 = new Aerolinea("USA", "PESOS MEXICANOS", "Papu's Airlines", false);
        
//        try {
//            aerolineadao.agregar(aerolinea1);
//            System.out.println("Se ha agregado correctamente.");
//        } catch (MongoException e) {
//            try {
//                throw new PersistenciaException("No se ha podido agregar bro.");
//            } catch (PersistenciaException ex) {
//                Logger.getLogger(PracticaAerolineaMongo_240005.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }


        //Metodo eliminar de 2 formas, con el puro id o el objeto.
        //Remplazar con el id del objeto previamente agregado en la base de datos de mongo.
//        aerolineas.deleteAirlineById(new ObjectId("672dd6dde9e9245b75179149"));
        Aerolinea eliminar = new Aerolinea(new ObjectId("672ddbb5f23cbf61d96d0730"));
//
        boolean eli = aerolineadao.eliminar(eliminar);
        if(eli){
            System.out.println("Aerolinea eliminada bro.");
        } else{
            System.out.println("La aerolinea no ha sido eliminada papu.");
        }
//        
//        Aerolinea busqueda = aerolineadao.consultar(eliminar);
//        if(busqueda == null){
//            System.out.println("No se encontro nada en la base de datos.");
//        } else{
//            System.out.println(busqueda.toString());
//        }
        

//        System.out.println(aerolineas.getAllAirlines());
        
    }
}
