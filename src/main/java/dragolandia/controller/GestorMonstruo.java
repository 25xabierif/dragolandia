package dragolandia.controller;

import org.hibernate.*;

import dragolandia.model.Monstruo;
import dragolandia.model.Tipo;

public class GestorMonstruo {
    
    /**
     * Método para añadir una fila monstruo a la tabla monstruos en la bd
     * @param nombre
     * @param vida
     * @param tipo
     * @param fuerza
     * @return
     */
    public boolean addMonstruo(String nombre, int vida, Tipo tipo, int fuerza){

        boolean registrado = false;

        if(validarMonstruo(nombre, vida, tipo)){

            Monstruo monstruo = new Monstruo(nombre, vida, tipo, fuerza);

            Transaction tx = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                
                tx = session.beginTransaction();
                session.persist(monstruo);
                tx.commit();
                registrado = true;
                System.out.println("El monstruo se ha registrado con éxito en la bd con id: "+monstruo.getId());

            } catch (Exception e) {
                if(tx!=null) tx.rollback();
                System.err.println("No se ha podido registrar el monstruo en la bd: "+e.getMessage());
                return registrado;
            }
        }
        return registrado;
    }

    /**
     * Método para comprobar que los parámetros del monstruo son correctos
     * @param nombre
     * @param vida
     * @param tipo
     * @return
     */
    private boolean validarMonstruo(String nombre, int vida, Tipo tipo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío");
            return false;
        }
        
        if (vida <= 0) {
            System.err.println("La vida debe ser mayor que 0");
            return false;
        }
        
        if (tipo == null) {
            System.err.println("El tipo no puede ser nulo");
            return false;
        }
        
        return true;
    }

    /**
     * Método que modifica el nombre del monstruo seǵun el id aportado
     * Devuelve true/false dependiendo de si ha conseguido su objetivo o no
     * @param id
     * @param nombre
     * @return
     */
    public boolean updateName(int id, String nombre){

        boolean actualizado = false;

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);

            if(monstruo != null){
                monstruo.setNombre(nombre);
                session.merge(monstruo);
                tx.commit();

                actualizado = true;
                System.out.println("Nuevo nombre: "+monstruo.getNombre()+".");
            }

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar el nombre del monstruo: "+e.getMessage());
            return actualizado;
        }

        return actualizado;
    }

    /**
     * Método que actualiza la vida del monstruo asociado al id
     * Devuelve true/false en función de si lo ha conseguido o no
     * @param id
     * @param vida
     * @return
     */
    public boolean updateVida(int id, int vida){

        boolean actualizado = false;

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);
            if(monstruo != null){
                monstruo.setVida(vida);
                session.merge(monstruo);
                tx.commit();

                actualizado = true;
                System.out.println("HP restante: "+monstruo.getVida());
            }


        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar el HP del monstruo: "+e.getMessage());
            return actualizado;
        }
        return actualizado;
    }

    /**
     * Método que actualiza la fuerza del monstruo asociado al id proporcionado
     * Devuelve true/false si lo ha conseguido o no
     * @param id
     * @param fuerza
     * @return
     */
    public boolean updateFuerza(int id, int fuerza){

        boolean actualizado = false;

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);

            if(monstruo!=null){
                monstruo.setFuerza(fuerza);
                session.merge(monstruo);
                tx.commit();

                actualizado = true;
                System.out.println("Nuevo nivel de fuerza: "+monstruo.getFuerza());
            }
        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar la fuerza del monstruo: "+e.getMessage());
            return actualizado;
        }
        return actualizado;
    }

    /**
     * Método empleado para actualizar el tipo del objeto monstruo instanciado y registrarlo en la BD
     * Entregamos un parámetro id para localizar el monstruo y actualizamos su tipo
     * Devuelve true/false en función de si ha conseguido hacerlo o no
     * @param id
     * @param tipo
     * @return
     */
    public boolean updateTipo(int id, Tipo tipo){

        boolean actualizado = false;

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);

            if(monstruo != null){
                monstruo.setTipo(tipo);
                session.merge(monstruo);
                tx.commit();

                actualizado = true;
                System.out.println("Nuevo tipo de monstruo: "+monstruo.getTipo());
            }

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar el tipo del monstruo: "+e.getMessage());
            return actualizado;
        }
        return actualizado;
    }

    /* public boolean updateBosque(int id, int idBosque){

        boolean actualizado = false;
        
        Transaction tx = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);

            Bosque bosque = session.get(Bosque.class, idBosque);

            if(monstruo != null && bosque != null){
                monstruo.setBosque(bosque);
                session.merge(monstruo);
                tx.commit();

                actualizado = true;
                System.out.println("El bosque del monstruo se ha actualizado con éxito: "+bosque.getNombre());
            }

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar el bosque del monstruo: "+e.getMessage());
            return actualizado;
        }
        return actualizado;
    } */
}
