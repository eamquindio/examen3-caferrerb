package edu.eam.ingesoft.fundamentos.parqueadero.logica;

/**
 * Clase que representa un propietario de vehículo en el parqueadero.
 * El propietario es responsable de gestionar su propia información,
 * calcular su categoría y descuento según las horas acumuladas.
 */
public class Propietario {

    // ==================== ATRIBUTOS ====================

    private String cedula;
    private String nombre;
    private int horasAcumuladas;

    // ==================== CONSTRUCTOR ====================

    /**
     * Crea una nueva instancia de Propietario.
     * @param cedula Número de identificación único del propietario
     * @param nombre Nombre completo del propietario
     */
    public Propietario(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.horasAcumuladas = 0;
    }

    // ==================== GETTERS ====================

    /**
     * @return La cédula del propietario
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @return El nombre del propietario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return El total de horas acumuladas en el parqueadero
     */
    public int getHorasAcumuladas() {
        return horasAcumuladas;
    }

    // ==================== MÉTODOS DE NEGOCIO ====================

    /**
     * Incrementa las horas acumuladas del propietario.
     * @param horas Cantidad de horas a sumar
     */
    public void acumularHoras(int horas) {
        this.horasAcumuladas += horas;
    }

    /**
     * Determina la categoría del cliente según sus horas acumuladas.
     * - ESTANDAR: 0 a 100 horas
     * - ESPECIAL: 101 a 500 horas
     * - VIP: más de 500 horas
     * @return La categoría del cliente ("ESTANDAR", "ESPECIAL" o "VIP")
     */
    public String obtenerCategoria() {
        if (horasAcumuladas <= 100) {
            return "ESTANDAR";
        } else if (horasAcumuladas <= 500) {
            return "ESPECIAL";
        } else {
            return "VIP";
        }
    }

    /**
     * Calcula el porcentaje de descuento según la categoría del cliente.
     * - ESTANDAR: 0% (0.0)
     * - ESPECIAL: 10% (0.10)
     * - VIP: 15% (0.15)
     * @return El porcentaje de descuento como valor decimal
     */
    public double obtenerDescuento() {
        String categoria = obtenerCategoria();

        switch (categoria) {
            case "ESTANDAR":
                return 0.0;
            case "ESPECIAL":
                return 0.10;
            case "VIP":
                return 0.15;
            default:
                return 0.0;
        }
    }

    /**
     * Indica si el propietario tiene categoría VIP.
     * @return true si tiene más de 500 horas acumuladas, false en caso contrario
     */
    public boolean esVIP() {
        return horasAcumuladas > 500;
    }
}
