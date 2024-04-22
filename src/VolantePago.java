public class VolantePago implements ICalculadoraDeducciones {
    private String id;
    private String titulo;
    private String descripcion;
    private double salarioBruto;
    private String fechaPago;
    private Empleado empleado;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public VolantePago(String id, String titulo, String descripcion, double salarioBruto, String fechaPago, Empleado empleado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.salarioBruto = salarioBruto;
        this.fechaPago = fechaPago;
        this.empleado = empleado;
    }

    public String getId() {
        return id;
    }

    @Override
    public double calcularISR() {
        return salarioBruto * 0.10;
    }

    @Override
    public double calcularAFP() {
        return salarioBruto * 0.0287;
    }

    @Override
    public double calcularSRS() {
        return salarioBruto * 0.5;
    }

    @Override
    public double calcularSalarioNeto() {
        double isr = calcularISR();
        double afp = calcularAFP();
        double srs = calcularSRS();
        return salarioBruto - isr - afp - srs;
    }


    public void mostrarDatos() {
        System.out.println("ID: " + id + "\nTítulo: " + titulo + "\nDescripción: " + descripcion +
                "\nSalario Bruto: " + salarioBruto + "\nFecha de Pago: " + fechaPago);
        empleado.mostrarDatos();
    }

}
