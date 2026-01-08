package dragolandia.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dragolandia.model.Bosque;
import dragolandia.model.Dragon;
import dragolandia.model.Monstruo;

public class GestorBosque {
    
    public boolean addBosque(String nombre, int nivelPeligro, Monstruo monstruoJefe, Dragon dragon, List<Monstruo> monstruos){
        
        boolean added = false;

        if(validarBosque(nombre, nivelPeligro)){

            Transaction tx = null;

            try (Session session = HibernateUtil.getEntityManager()) {
                
                try {
                    
                    tx = session.beginTransaction();

                } catch (Exception e) {
                    if(tx!=null && tx.isActive()) tx.rollback();
                    System.err.println("No se ha podido registrar el bosque: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("Error en la sesión hibernate: "+e.getMessage());
            }
        }
        return added;
    }

    public boolean validarBosque(String nombre, int nivelPeligro){
        boolean bosqueValido = true;
        if(nombre == null || nombre.trim().isEmpty()){
            System.err.println("El nombre no puede estar vacío.");
            bosqueValido = false;
        }
        if(nivelPeligro <= 0){
            System.err.println("El nivel de peligro debe ser mayor que 0");
            bosqueValido = false;
        }
        return bosqueValido;
    }
}

