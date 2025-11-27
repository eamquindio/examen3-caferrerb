package edu.eam.ingesoft.fundamentos.parqueadero.logica;

/**
 * Clase que representa un vehículo registrado en el parqueadero.
 * El vehículo es responsable de conocer su tarifa según su tipo.
 */
public class Vehiculo {

    // ==================== ATRIBUTOS ====================

    private String placa;
    private int modelo;
    private String color;
    private Propietario propietario;
    private String tipo;

    // ==================== CONSTRUCTOR ====================

    /**
     * Crea una nueva instancia de Vehiculo.
     * @param placa Identificador único del vehículo
     * @param modelo Año del vehículo
     * @param color Color del vehículo
     * @param propietario Objeto Propietario dueño del vehículo
     * @param tipo Tipo de vehículo ("SEDAN", "SUV" o "CAMION")
     */
    public Vehiculo(String placa, int modelo, String color, Propietario propietario, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.propietario = propietario;
        this.tipo = tipo;
    }

    // ==================== GETTERS ====================

    /**
     * @return La placa del vehículo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @return El modelo (año) del vehículo
     */
    public int getModelo() {
        return modelo;
    }

    /**
     * @return El color del vehículo
     */
    public String getColor() {
        return color;
    }

    /**
     * @return El propietario del vehículo
     */
    public Propietario getPropietario() {
        return propietario;
    }

    /**
     * @return El tipo de vehículo ("SEDAN", "SUV" o "CAMION")
     */
    public String getTipo() {
        return tipo;
    }

    // ==================== MÉTODOS DE NEGOCIO ====================

    /**
     * Determina la tarifa por hora de estacionamiento según el tipo de vehículo.
     * - SEDAN: $1,500
     * - SUV: $2,300
     * - CAMION: $3,000
     * @return La tarifa por hora
     */
    public double obtenerTarifaHora() {
        switch (tipo) {
            case "SEDAN":
                return 1500;
            case "SUV":
                return 2300;
            case "CAMION":
                return 3000;
            default:
                return 0;
        }
    }
}
