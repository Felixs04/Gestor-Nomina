public class EmpleadoTiempoCompleto extends Empleado {
    private boolean estaActivo;

    public EmpleadoTiempoCompleto(String codigo, String nombreCompleto, String cedula, String direccion, String fechaInicio, boolean estaActivo) {
        super(codigo, nombreCompleto, cedula, direccion, fechaInicio);
        this.estaActivo = estaActivo;
    }
    public void setActivo(boolean activo) {
        this.estaActivo = activo;
    }
    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Está activo: " + estaActivo);
    }
}
