import Model.PublicacionPostgres;
import Services.IDAO;
import Services.ImplDAO;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final IDAO dao = new ImplDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Bucle principal del menú
        while (true) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearPublicacion();
                    break;
                case 2:
                    listarPublicaciones();
                    break;
                case 3:
                    buscarPublicacion();
                    break;
                case 4:
                    actualizarPublicacion();
                    break;
                case 5:
                    eliminarPublicacion();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    return; // Termina el programa
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println(); // Salto de línea para claridad
        }
    }

    private static void mostrarMenu() {
        System.out.println("--- CRUD de Publicaciones ---");
        System.out.println("1. Crear nueva publicación");
        System.out.println("2. Listar todas las publicaciones");
        System.out.println("3. Buscar publicación por ID");
        System.out.println("4. Actualizar publicación");
        System.out.println("5. Eliminar publicación");
        System.out.println("0. Salir");
        System.out.println("-----------------------------");
    }

    private static void crearPublicacion() {
        System.out.println("\n--- 1. Crear Nueva Publicación ---");
        String nombre = leerTexto("Ingrese el nombre de la publicación: ");
        String descripcion = leerTexto("Ingrese la descripción: ");

        PublicacionPostgres nuevaPublicacion = new PublicacionPostgres();
        nuevaPublicacion.setNombrePublicacion(nombre);
        nuevaPublicacion.setDescripcionPublicacion(descripcion);
        nuevaPublicacion.setFechaPublicacion(LocalDate.now());

        dao.insert(nuevaPublicacion);
        System.out.println("¡Publicación creada exitosamente!");
        // Nota: El ID se asigna automáticamente por la secuencia en la BD.
    }

    private static void listarPublicaciones() {
        System.out.println("\n--- 2. Listar Todas las Publicaciones ---");
        List<PublicacionPostgres> publicaciones = dao.getAll("PublicacionPostgres.findAll", PublicacionPostgres.class);

        if (publicaciones == null || publicaciones.isEmpty()) {
            System.out.println("No se encontraron publicaciones.");
        } else {
            publicaciones.forEach(System.out::println);
        }
    }

    private static void buscarPublicacion() {
        System.out.println("\n--- 3. Buscar Publicación por ID ---");
        int id = leerEntero("Ingrese el ID de la publicación a buscar: ");
        PublicacionPostgres pub = dao.findById(id, PublicacionPostgres.class);

        if (pub != null) {
            System.out.println("Publicación encontrada: " + pub);
        } else {
            System.out.println("No se encontró ninguna publicación con el ID: " + id);
        }
    }

    private static void actualizarPublicacion() {
        System.out.println("\n--- 4. Actualizar Publicación ---");
        int id = leerEntero("Ingrese el ID de la publicación a actualizar: ");
        PublicacionPostgres pub = dao.findById(id, PublicacionPostgres.class);

        if (pub == null) {
            System.out.println("No se encontró ninguna publicación con el ID: " + id);
            return;
        }

        System.out.println("Datos actuales: " + pub);
        String nombre = leerTexto("Ingrese el nuevo nombre (deje en blanco para no cambiar): ");
        String descripcion = leerTexto("Ingrese la nueva descripción (deje en blanco para no cambiar): ");

        if (nombre != null && !nombre.trim().isEmpty()) {
            pub.setNombrePublicacion(nombre);
        }
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            pub.setDescripcionPublicacion(descripcion);
        }

        dao.update(pub);
        System.out.println("¡Publicación actualizada exitosamente!");
        System.out.println("Nuevos datos: " + dao.findById(id, PublicacionPostgres.class));
    }

    private static void eliminarPublicacion() {
        System.out.println("\n--- 5. Eliminar Publicación ---");
        int id = leerEntero("Ingrese el ID de la publicación a eliminar: ");
        PublicacionPostgres pub = dao.findById(id, PublicacionPostgres.class);

        if (pub == null) {
            System.out.println("No se encontró ninguna publicación con el ID: " + id);
            return;
        }

        System.out.println("Se eliminará la siguiente publicación: " + pub);
        String confirmacion = leerTexto("¿Está seguro? (S/N): ");

        if (confirmacion.equalsIgnoreCase("S")) {
            dao.delete(pub);
            System.out.println("¡Publicación eliminada exitosamente!");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer (el salto de línea)
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
                scanner.nextLine(); // Limpiar el buffer de la entrada incorrecta
            }
        }
    }
}