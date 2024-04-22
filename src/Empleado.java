public class Empleado {
    protected String codigo;
    protected String nombreCompleto;
    protected String cedula;
    protected String direccion;
    protected String fechaInicio;

    public void setNombre(String nombre) {
        this.nombreCompleto = nombre;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;

    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCodigo() {
        return codigo;
    }
    public Empleado(String codigo, String nombreCompleto, String cedula, String direccion, String fechaInicio) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.direccion = direccion;
        this.fechaInicio = fechaInicio;
    }


    public void mostrarDatos() {
        System.out.println("Código: " + codigo + "\nNombre: " + nombreCompleto + "\nCédula: " + cedula +
                "\nDirección: " + direccion + "\nFecha de Inicio: " + fechaInicio);
    }


   public static boolean  validarCedula(String cedula){
        cedula = cedula.replaceAll("-","");
        int suma = 0;

        final char[] peso = { '1', '2', '1', '2', '1', '2', '1', '2', '1', '2' };


        if ((cedula == null) || (cedula.length() != 11)){
            return false;
        }

        try{
            Long.parseLong(cedula);

        } catch (Exception e){

            return false;
        }

        for (int i = 0; i < 10; i++){

        int a = Character.getNumericValue(cedula.charAt(i));

        int b = Character.getNumericValue(peso[i]);

        char[] mult = Integer.toString(a * b).toCharArray();

        if (mult.length > 1){
            a = Character.getNumericValue(mult[0]);
            b = Character.getNumericValue(mult[1]);
            } else{
                a = 0;
                b = Character.getNumericValue(mult[0]);
            }
            suma = suma + a + b;
        }

        int digito = (10 - (suma % 10)) % 10;

        if (digito != Character.getNumericValue(cedula.charAt(10))){
            return false;
        }

        return true;
    }
}






