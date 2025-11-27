package edu.eam.ingesoft.fundamentos.parqueadero.logica;

import java.util.ArrayList;

/**
 * Clase principal que gestiona todas las operaciones del parqueadero.
 * Coordina propietarios, vehículos y servicios.
 */
public class Parqueadero {

    // ==================== ATRIBUTOS ====================

    private ArrayList<Propietario> propietarios;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Servicio> servicios;

    // ==================== CONSTRUCTOR ====================

    /**
     * Crea una nueva instancia del Parqueadero con las listas vacías.
     */
    public Parqueadero() {
        this.propietarios = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }

    // ==================== MÉTODOS DE BÚSQUEDA ====================

    /**
     * Busca un propietario en el sistema por su cédula.
     * @param cedula Cédula del propietario a buscar
     * @return El propietario encontrado, o null si no existe
     */
    public Propietario buscarPropietario(String cedula) {
        for (Propietario propietario : propietarios) {
            if (propietario.getCedula().equals(cedula)) {
                return propietario;
            }
        }
        return null;
    }

    /**
     * Busca un vehículo en el sistema por su placa.
     * @param placa Placa del vehículo a buscar
     * @return El vehículo encontrado, o null si no existe
     */
    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return vehiculo;
            }
        }
        return null;
    }

    // ==================== MÉTODOS DE REGISTRO ====================

    /**
     * Registra un nuevo propietario en el sistema.
     * @param cedula Cédula del nuevo propietario
     * @param nombre Nombre del nuevo propietario
     * @return true si se registró exitosamente, false si la cédula ya existe
     */
    public boolean registrarPropietario(String cedula, String nombre) {
        if (buscarPropietario(cedula) != null) {
            return false;
        }

        Propietario nuevoPropietario = new Propietario(cedula, nombre);
        propietarios.add(nuevoPropietario);
        return true;
    }

    /**
     * Registra un nuevo vehículo en el sistema.
     * @param placa Placa del nuevo vehículo
     * @param modelo Año del vehículo
     * @param color Color del vehículo
     * @param cedula Cédula del propietario del vehículo
     * @param tipo Tipo de vehículo ("SEDAN", "SUV" o "CAMION")
     * @return true si se registró exitosamente, false si la placa ya existe o el propietario no existe
     */
    public boolean registrarVehiculo(String placa, int modelo, String color, String cedula, String tipo) {
        if (buscarVehiculo(placa) != null) {
            return false;
        }

        Propietario propietario = buscarPropietario(cedula);
        if (propietario == null) {
            return false;
        }

        Vehiculo nuevoVehiculo = new Vehiculo(placa, modelo, color, propietario, tipo);
        vehiculos.add(nuevoVehiculo);
        return true;
    }

    // ==================== MÉTODO PARA ACUMULAR HORAS ====================

    /**
     * Acumula horas de uso a un cliente específico.
     * @param cedula Cédula del propietario
     * @param horas Número de horas a acumular
     * @return true si se acumularon las horas, false si el propietario no existe
     */
    public boolean acumularHorasCliente(String cedula, int horas) {
        Propietario propietario = buscarPropietario(cedula);

        if (propietario == null) {
            return false;
        }

        propietario.acumularHoras(horas);
        return true;
    }

    // ==================== MÉTODO DE REGISTRO DE SERVICIO ====================

    /**
     * Registra un nuevo servicio de parqueo con todas las validaciones.
     * @param placa Placa del vehículo que usará el servicio
     * @param horaIngreso Hora de entrada (1-22)
     * @param horaSalida Hora de salida (2-23)
     * @return El costo del servicio, o -1 si falla alguna validación
     */
    public double registrarServicio(String placa, int horaIngreso, int horaSalida) {
        // Validar hora de ingreso (1-22)
        if (horaIngreso < 1 || horaIngreso > 22) {
            return -1;
        }

        // Validar hora de salida (2-23)
        if (horaSalida < 2 || horaSalida > 23) {
            return -1;
        }

        // Validar que hora de salida sea mayor que hora de ingreso
        if (horaSalida <= horaIngreso) {
            return -1;
        }

        // Buscar el vehículo
        Vehiculo vehiculo = buscarVehiculo(placa);
        if (vehiculo == null) {
            return -1;
        }

        // Crear el servicio
        Servicio nuevoServicio = new Servicio(horaIngreso, horaSalida, vehiculo);

        // Acumular horas al propietario
        int horas = nuevoServicio.calcularHoras();
        String cedulaPropietario = vehiculo.getPropietario().getCedula();
        acumularHorasCliente(cedulaPropietario, horas);

        // Agregar el servicio a la lista
        servicios.add(nuevoServicio);

        // Retornar el costo
        return nuevoServicio.getCosto();
    }

    // ==================== MÉTODOS DE ESTADÍSTICAS ====================

    /**
     * Calcula el total de dinero recaudado por todos los servicios.
     * @return La suma total de todos los costos de servicios
     */
    public double calcularTotalRecaudado() {
        double total = 0;

        for (Servicio servicio : servicios) {
            total += servicio.getCosto();
        }

        return total;
    }

    /**
     * Cuenta cuántos clientes tienen categoría VIP.
     * @return Cantidad de clientes VIP
     */
    public int contarClientesVIP() {
        int contador = 0;

        for (Propietario propietario : propietarios) {
            if (propietario.esVIP()) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Encuentra el cliente con mayor cantidad de horas acumuladas.
     * @return El propietario con más horas, o null si no hay propietarios
     */
    public Propietario obtenerClienteMasHoras() {
        if (propietarios.isEmpty()) {
            return null;
        }

        Propietario mayor = propietarios.get(0);

        for (Propietario propietario : propietarios) {
            if (propietario.getHorasAcumuladas() > mayor.getHorasAcumuladas()) {
                mayor = propietario;
            }
        }

        return mayor;
    }

    // ==================== GETTERS PARA LAS LISTAS ====================

    /**
     * @return La lista de propietarios registrados
     */
    public ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }

    /**
     * @return La lista de vehículos registrados
     */
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     * @return La lista de servicios registrados
     */
    public ArrayList<Servicio> getServicios() {
        return servicios;
    }
}
