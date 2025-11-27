package edu.eam.ingesoft.fundamentos.parqueadero.logica;

/**
 * Clase que representa un servicio de parqueo en el parqueadero.
 * El servicio es responsable de calcular su duración y costo.
 */
public class Servicio {

    // ==================== ATRIBUTOS ====================

    private int horaIngreso;
    private int horaSalida;
    private Vehiculo vehiculo;
    private double costo;

    // ==================== CONSTRUCTOR ====================

    /**
     * Crea una nueva instancia de Servicio y calcula su costo.
     * @param horaIngreso Hora en que ingresó el vehículo (1-22)
     * @param horaSalida Hora en que salió el vehículo (2-23)
     * @param vehiculo Objeto Vehiculo que utilizó el servicio
     */
    public Servicio(int horaIngreso, int horaSalida, Vehiculo vehiculo) {
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.vehiculo = vehiculo;
        this.costo = calcularCosto();
    }

    // ==================== GETTERS ====================

    /**
     * @return La hora de ingreso del vehículo
     */
    public int getHoraIngreso() {
        return horaIngreso;
    }

    /**
     * @return La hora de salida del vehículo
     */
    public int getHoraSalida() {
        return horaSalida;
    }

    /**
     * @return El vehículo asociado al servicio
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @return El costo calculado del servicio
     */
    public double getCosto() {
        return costo;
    }

    // ==================== MÉTODOS DE NEGOCIO ====================

    /**
     * Calcula la duración del servicio en horas.
     * @return Número de horas de uso (horaSalida - horaIngreso)
     */
    public int calcularHoras() {
        return horaSalida - horaIngreso;
    }

    /**
     * Calcula el costo total del servicio aplicando tarifas y descuentos.
     * Fórmula: costo = horas × tarifaVehículo × (1 - descuentoPropietario)
     * recordar que el vehiculo tiene el propietario asociado y este tiene el metodo para obtener el descuento
     * @return El costo total del servicio
     */
    public double calcularCosto() {
        int horas = calcularHoras();
        double tarifaHora = vehiculo.obtenerTarifaHora();
        double descuento = vehiculo.getPropietario().obtenerDescuento();

        double subtotal = horas * tarifaHora;
        double valorDescuento = subtotal * descuento;

        return subtotal - valorDescuento;
    }
}
