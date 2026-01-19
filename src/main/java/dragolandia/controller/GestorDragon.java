package dragolandia.controller;

public class GestorDragon {
    
    /**
     * Método para validar los parámetros del dragón según los siguientes criterios:
     * El nombre del dragón no puede ser nulo o ser un conjunto de caracteres vacío.
     * La resistencia del dragón no puede ser igual o inferior a 0.
     * La intensidad del fuego del dragón no puede ser igual o inferior a 0.
     * @param nombre
     * @param intensidadFuego
     * @param resistencia
     * @return
     */
    public boolean validarDragon(String nombre, int intensidadFuego, int resistencia){
        boolean valido = true;
        if(!validarNombre(nombre)||!validarIF(intensidadFuego)||!validarRes(resistencia)){
            valido = false;
        }
        return valido;
    }

    public boolean validarNombre(String nombre){
        boolean valido = true;
        if(nombre.toLowerCase().trim() == null || nombre.toLowerCase().trim().length() <= 0){
            valido = false;
            System.out.println("Introduce un nombre de draǵon válido.");
        }
        return valido;
    }

    public boolean validarIF(int intensidadFuego){
        boolean valido = true;
        if(intensidadFuego <= 0){
            valido = false;
            System.out.println("La intensidad del fuego del dragón no puede ser igual o inferior a 0.");
        }
        return valido;
    }

    public boolean validarRes(int resistencia){
        boolean valido = true;
        if(resistencia <= 0){
            valido = false;
            System.out.println("La resistencia del dragón no puede ser igual o inferior a 0.");
        }
        return valido;
    }

}
