package dragolandia.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    
    // Constructor privado para evitar instanciación
    private HibernateUtil() {}
    
    private static final EntityManagerFactory xestorEntidades = Persistence.createEntityManagerFactory("dragolandiaServizo");
    
    public static EntityManager getEntityManager(){
        return xestorEntidades.createEntityManager();
    }

    // Método para cerrar el EntityManagerFactory cuando termine la aplicación
    public static void shutdown() {
        if (xestorEntidades.isOpen()) {
            xestorEntidades.close();
        }
    }
}