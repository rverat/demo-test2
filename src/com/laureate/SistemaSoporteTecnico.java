package com.laureate;

import com.laureate.exception.EquipoDuplicadoException;
import com.laureate.exception.OpcionInvalidaException;
import com.laureate.exception.TicketNoEncontradoException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SistemaSoporteTecnico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // Instancia de personal (solo para demo)
        PersonalAdministrativo admin = new PersonalAdministrativo("P001", "Maria", "admin@example.com", "123456");
        PersonalAulaYLab personalAulaYLab = new PersonalAulaYLab("P003", "Elliot", "personalaulaylab@example.com", "123456");
        PersonalOnsite personalOnsite = new PersonalOnsite("P002", "David", "personalonsite@example.com", "123456");


        GestionTickets gestionTickets = new GestionTicketImpl();
        GestionEquipos gestionEquipos = new GestionEquiposImpl();

        Object user = null;
        boolean authenticated = false;

        user = getObject(authenticated, scanner, admin, user, personalOnsite, personalAulaYLab);

        while (!salir) {
            try {
                mostrarMenu();
                System.out.print("Seleccione una opción: ");
                int opcion = leerOpcion(scanner);

                switch (opcion) {
                    case 1:
                        if (user instanceof PersonalAdministrativo) {
                            registrarEquipo(scanner, gestionEquipos);
                        } else {
                            System.out.println("No puedes registrar equipos");
                        }
                        break;

                    case 2:
                        listarEquipos(gestionEquipos);
                        break;

                    case 3:
                        if (user instanceof PersonalAulaYLab) {
                            registrarTicket(scanner, gestionEquipos, gestionTickets, (PersonalAulaYLab) user);
                        } else {
                            System.out.println("No puedes registrar ticket");
                        }
                        break;

                    case 4:
                        if (user instanceof PersonalOnsite) {
                            actualizarTicket(scanner, gestionTickets);
                        } else {
                            System.out.println("No puedes actualizar ticket");
                        }
                        break;

                    case 5:
                        listarTickets(gestionTickets);
                        break;

                    case 6:
                        if (user instanceof PersonalOnsite) {
                            personalOnsite.enviarCorreo("El ticket ha sido atendido.");
                        } else {
                            System.out.println("No puedes enviar correo");
                        }
                        break;

                    case 7:
                        user = cambiarDeUsuario(scanner, admin, personalOnsite, personalAulaYLab);
                        break;

                    case 8:
                        salir = true;
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void mostrarLogin() {
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║             INICIO DE SESIÓN            ║");
        System.out.println("╚═════════════════════════════════════════╝");
    }

    private static void mostrarMenu() {
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║           SISTEMA DE SOPORTE TÉCNICO    ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.println("║  1. Registrar equipo                    ║");
        System.out.println("║  2. Listar equipos                      ║");
        System.out.println("║  3. Registrar ticket                    ║");
        System.out.println("║  4. Actualizar ticket                   ║");
        System.out.println("║  5. Listar tickets                      ║");
        System.out.println("║  6. Enviar correo                       ║");
        System.out.println("║  7. Cambiar de usuario                  ║");
        System.out.println("║  8. Salir                               ║");
        System.out.println("╚═════════════════════════════════════════╝");
    }

    private static int leerOpcion(Scanner scanner) throws OpcionInvalidaException {
        try {
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            throw new OpcionInvalidaException("Por favor, ingrese un número válido.");
        }
    }

    private static void registrarEquipo(Scanner scanner, GestionEquipos gestionEquipos) {
        System.out.print("Código del equipo: ");
        String codEquipo = scanner.next();
        scanner.nextLine();
        System.out.print("Tipo de equipo: ");
        String tipoEquipo = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        Equipo equipo = new Equipo(codEquipo, tipoEquipo, descripcion);
        try {
            gestionEquipos.registrarEquipo(equipo);
        } catch (EquipoDuplicadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarEquipos(GestionEquipos gestionEquipos) {
        List<Equipo> equipos = gestionEquipos.listarEquipos();
        equipos.forEach(System.out::println);
    }

    private static void registrarTicket(Scanner scanner, GestionEquipos gestionEquipos, GestionTickets gestionTickets, PersonalAulaYLab user) {
        List<Equipo> equiposDisponibles = gestionEquipos.listarEquipos();
        if (equiposDisponibles.isEmpty()) {
            System.out.println("No hay equipos registrados. Registre un equipo antes de crear un ticket.");
            return;
        }

        System.out.println("Equipos disponibles:");
        equiposDisponibles.forEach(System.out::println);

        System.out.print("Ingrese el código del equipo para registrar el ticket: ");
        String codEquipoSeleccionado = scanner.next();


        Equipo equipoSeleccionado = equiposDisponibles.stream()
                .filter(e -> e.getCodEquipo().equals(codEquipoSeleccionado))
                .findFirst().orElse(null);

        if (equipoSeleccionado == null) {
            System.out.println("Equipo no encontrado. Asegúrese de ingresar un código válido.");
            return;
        }
        scanner.nextLine();


        System.out.print("Código del ticket: ");
        String codTicket = scanner.next();
        String codUser = user.codPersona;
        scanner.nextLine();
        System.out.print("Descripción del ticket: ");
        String descTicket = scanner.nextLine();
        String estadoTicket = "Pendiente";

        Ticket nuevoTicket = new Ticket(codTicket, equipoSeleccionado, codUser, descTicket, estadoTicket, LocalDateTime.now());
        gestionTickets.registrarTicket(nuevoTicket);
    }

    private static void actualizarTicket(Scanner scanner, GestionTickets gestionTickets) {
        System.out.print("Código del ticket: ");
        String codTicketUpdate = scanner.next();
        scanner.nextLine();
        System.out.print("Descripción: ");
        String descUpdate = scanner.nextLine();
        String estadoUpdate = "Resuelto";
        try {
            gestionTickets.actualizarTicket(codTicketUpdate, descUpdate, estadoUpdate);
        } catch (TicketNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarTickets(GestionTickets gestionTickets) {
        List<Ticket> tickets = gestionTickets.listarTickets();
        tickets.forEach(System.out::println);
    }

    private static Object cambiarDeUsuario(Scanner scanner, PersonalAdministrativo admin, PersonalOnsite personalOnsite, PersonalAulaYLab personalAulaYLab) {
        return getObject(false, scanner, admin, null, personalOnsite, personalAulaYLab);
    }

    private static Object getObject(boolean authenticated, Scanner scanner, PersonalAdministrativo admin, Object user, PersonalOnsite personalOnsite, PersonalAulaYLab personalAulaYLab) {
        while (!authenticated) {
            mostrarLogin();
            System.out.print("Correo: ");
            String codPersona = scanner.next();

            System.out.print("Contraseña: ");
            String password = scanner.next();

            if (codPersona.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
                authenticated = true;
                user = admin;
                System.out.println("Bienvenido al sistema, " + admin.getNombre());
            } else if (codPersona.equals(personalOnsite.getEmail()) && password.equals(personalOnsite.getPassword())) {
                authenticated = true;
                user = personalOnsite;
                System.out.println("Login exitoso. Bienvenido al sistema, " + personalOnsite.getNombre());
            } else if (codPersona.equals(personalAulaYLab.getEmail()) && password.equals(personalAulaYLab.getPassword())) {
                authenticated = true;
                user = personalAulaYLab;
                System.out.println("Login exitoso. Bienvenido al sistema, " + personalAulaYLab.getNombre());
            } else {
                System.out.println("Error con la autenticación. Intente nuevamente.");
            }
        }
        return user;
    }
}