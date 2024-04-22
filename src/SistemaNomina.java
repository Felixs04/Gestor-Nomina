import java.util.*;


public class SistemaNomina{
    private static List<Empleado> empleados = new ArrayList<>();
    private static List<VolantePago> volantes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu(){

        boolean salir = false;
        while (!salir) {
            System.out.println("1-Registrar empleado\n2-Modificar empleado\n3-Mostrar empleados\n4-Generar volante\n5-Editar volante\n6-Mostar volante\n7-Finalizar programa");

            boolean exc = false;
            int opcion = 0;
            while (!exc) {
                try {
                    opcion = scanner.nextInt();
                    exc = true;
                } catch (InputMismatchException e) {
                    System.out.println("Caracter incorrecto\n............\nIgresar opcion valida");
                    scanner.next();
                }
            }


            scanner.nextLine(); // Consumir nueva línea
            switch (opcion) {
                case 1:
                    registrarEmpleado();
                    break;
                case 2:
                    modificarEmpleado();
                    break;
                case 3:
                    mostrarEmpleados();
                    break;
                case 4:
                    generarVolante();
                    break;
                case 5:
                    editarVolante();
                    break;
                case 6:
                    mostrarVolante();
                    break;
                case 7:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }



    private static void registrarEmpleado() {
        System.out.println("Ingrese código del empleado:");
        String codigo = scanner.nextLine();
        if (empleados.stream().anyMatch(emp -> emp.getCodigo().equals(codigo))) {
            System.out.println("Empleado ya registrado!");
            return;
        }

        System.out.println("Ingrese nombre completo:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese cédula:");
        String cedula = scanner.nextLine();
        while (!Empleado.validarCedula(cedula)){
            System.out.println("Numero de cedula incorrecto");
            cedula = scanner.nextLine();
        }
        System.out.println("Ingrese dirección:");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese fecha de inicio (dd/mm/aaaa):");
        String fechaInicio = scanner.nextLine();

        Empleado nuevoEmpleado = new Empleado(codigo, nombre, cedula, direccion, fechaInicio);
        empleados.add(nuevoEmpleado);
        System.out.println("Empleado registrado exitosamente.");
    }



    private static void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        for (Empleado emp : empleados) {
            emp.mostrarDatos();
            System.out.println("----------");
        }
    }



    private static void generarVolante() {

        boolean valido = false;
        System.out.println("Ingrese ID del volante:");
        String id = scanner.nextLine();
        if (volantes.stream().anyMatch(vol -> vol.getId().equals(id))) {
            System.out.println("Volante ya registrado!");
            return;
        }

        System.out.println("Ingrese título del volante:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese descripción:");
        String descripcion = scanner.nextLine();

        double salarioBruto = 0;
        while (!valido) {
            try {
                System.out.println("Ingrese salario bruto:");
                salarioBruto = scanner.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Digito incorrecto");
                scanner.next();
            }

        }

        scanner.nextLine();  // Consumir nueva línea
        System.out.println("Ingrese fecha de pago (dd/mm/aaaa):");
        String fechaPago = scanner.nextLine();

        System.out.println("Ingrese código del empleado asociado:");
        String codigoEmp = scanner.nextLine();

        Empleado empleadoAsociado = empleados.stream().filter(emp -> emp.getCodigo().equals(codigoEmp)).findFirst().orElse(null);
        if (empleadoAsociado == null) {
            System.out.println("Empleado no encontrado!");
            return;
        }

        VolantePago nuevoVolante = new VolantePago(id, titulo, descripcion, salarioBruto, fechaPago, empleadoAsociado);
        volantes.add(nuevoVolante);
        System.out.println("Volante de pago registrado exitosamente.");
    }



    private static void mostrarVolante() {
        if (volantes.isEmpty()) {
            System.out.println("No hay volantes registrados.");
            return;
        }
        System.out.println("Ingrese ID del volante a mostrar:");
        String id = scanner.nextLine();
        VolantePago volante = volantes.stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
        if (volante == null) {
            System.out.println("Volante no encontrado!");
            return;
        }
        volante.mostrarDatos();
        System.out.println("ISR:" + volante.calcularISR());
        System.out.println("AFP:" + volante.calcularAFP());
        System.out.println("SRS:" + volante.calcularSRS());
        System.out.println("Salario neto:" + volante .calcularSalarioNeto());
    }



    private static void modificarEmpleado() {
        System.out.println("Ingrese código del empleado a modificar:");
        String codigo = scanner.nextLine();
        Empleado empleado = empleados.stream().filter(emp -> emp.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (empleado == null) {
            System.out.println("Empleado no encontrado!");
            return;
        }

        System.out.println("Ingrese nuevo nombre completo:");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Ingrese nueva cédula:");
        String nuevaCedula = scanner.nextLine();
        System.out.println("Ingrese nueva dirección:");
        String nuevaDireccion = scanner.nextLine();
        System.out.println("Ingrese nueva fecha de inicio (dd/mm/aaaa):");
        String nuevaFechaInicio = scanner.nextLine();

        empleado.setNombre(nuevoNombre);
        empleado.setCedula(nuevaCedula);
        empleado.setDireccion(nuevaDireccion);
        empleado.setFechaInicio(nuevaFechaInicio);

        System.out.println("Empleado modificado exitosamente.");

    }



    private static void editarVolante() {
        System.out.println("Ingrese ID del volante a editar:");
        String id = scanner.nextLine();
        VolantePago volante = volantes.stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
        if (volante == null) {
            System.out.println("Volante no encontrado!");
            return;
        }

        System.out.println("Ingrese nuevo título del volante:");
        String nuevoTitulo = scanner.nextLine();
        System.out.println("Ingrese nueva descripción:");
        String nuevaDescripcion = scanner.nextLine();

        double nuevoSalarioBruto = 0;
        boolean n = false;
        while (!n) {
            try {
                System.out.println("Ingrese nuevo salario bruto:");
                nuevoSalarioBruto = scanner.nextDouble();
                n = true;
            } catch (InputMismatchException e) {
                System.out.println("Digito incorrecto");
                scanner.next();
            }

        }

        scanner.nextLine(); // Consumir nueva línea
        System.out.println("Ingrese nueva fecha de pago (dd/mm/aaaa):");
        String nuevaFechaPago = scanner.nextLine();

        volante.setTitulo(nuevoTitulo);
        volante.setDescripcion(nuevaDescripcion);
        volante.setSalarioBruto(nuevoSalarioBruto);
        volante.setFechaPago(nuevaFechaPago);

        System.out.println("Volante editado exitosamente.");
    }

}


