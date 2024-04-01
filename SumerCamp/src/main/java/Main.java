import Entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SessionFactory sessionFactory;


    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Scanner scanner = new Scanner(System.in);
        int opcion;


        while (true){
            System.out.println("1. Listar entidades");
            System.out.println("2. Insertar entidad");
            System.out.println("3. Eliminar entidad");
            System.out.println("4. Actualizar entidad");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion=scanner.nextInt();
            if (opcion==1){
                listarEntidades();
            } else if (opcion==2) {
                insertarEntidad();
            }else if (opcion==3) {
                eliminarEntidad();
            }else if (opcion==4) {
                actualizarEntidad();
            }else if (opcion==5) {
                break;
            }else{
                continue;
            }
        }
        sessionFactory.close();

    }
    private static void listarEntidades() {
        Scanner scanner = new Scanner(System.in);
        int opcion2;

        do {
            System.out.println("Seleccione una entidad:");
            System.out.println("1. Guardian");
            System.out.println("2. Child");
            System.out.println("3. Camp");
            System.out.println("4. Activity");
            System.out.println("5. CampChild");
            System.out.println("6. ChildActivity");
            System.out.println("7. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion2 = scanner.nextInt();

            switch (opcion2) {
                case 1:
                    listarGuardians();
                    break;
                case 2:
                    listarChildren();
                    break;
                case 3:
                    listarCamps();
                    break;
                case 4:
                    listarActivities();
                    break;
                case 5:
                    listarCampChildren();
                    break;
                case 6:
                    listarChildActivities();
                    break;
                case 7:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione otra opción.");
            }

        } while (opcion2 != 7);

    }

    private static void listarGuardians() {
        Session session = sessionFactory.openSession();
        try {
            List<Guardian> guardians = session.createQuery("FROM Guardian").list();
            for (Guardian guardian : guardians) {
                System.out.println(guardian);
            }
        } finally {
            session.close();
        }
    }

    private static void listarChildren() {
        Session session = sessionFactory.openSession();
        try {
            List<Child> children = session.createQuery("FROM Child").list();
            for (Child child : children) {
                System.out.println(child);
            }
        } finally {
            session.close();
        }
    }

    private static void listarCamps() {
        Session session = sessionFactory.openSession();
        try {
            List<Camp> camps = session.createQuery("FROM Camp").list();
            for (Camp camp : camps) {
                System.out.println(camp);
            }
        } finally {
            session.close();
        }
    }

    private static void listarActivities() {
        Session session = sessionFactory.openSession();
        try {
            List<Activity> activities = session.createQuery("FROM Activity").list();
            for (Activity activity : activities) {
                System.out.println(activity);
            }
        } finally {
            session.close();
        }
    }

    private static void listarCampChildren() {
        Session session = sessionFactory.openSession();
        try {
            List<CampChild> campChildren = session.createQuery("FROM CampChild").list();
            for (CampChild campChild : campChildren) {
                System.out.println(campChild);
            }
        } finally {
            session.close();
        }
    }

    private static void listarChildActivities() {
        Session session = sessionFactory.openSession();
        try {
            List<ChildActivity> childActivities = session.createQuery("FROM ChildActivity").list();
            for (ChildActivity childActivity : childActivities) {
                System.out.println(childActivity);
            }
        } finally {
            session.close();
        }
    }






    private static void insertarEntidad() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione una entidad para insertar:");
            System.out.println("1. Guardian");
            System.out.println("2. Child");
            System.out.println("3. Camp");
            System.out.println("4. Activity");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    insertarGuardian();
                    break;
                case 2:
                    insertarChild();
                    break;
                case 3:
                    insertarCamp();
                    break;
                case 4:
                    insertarActivity();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione otra opción.");
            }

        } while (opcion != 5);
    }

    private static void insertarGuardian() {
        Scanner scanner = new Scanner(System.in);

        String fichero;
        System.out.print("Indica el archivo y su ruta: ");
        fichero = scanner.nextLine();
        try {
            FileAccessor fileAccessor = new FileAccessor();
            fileAccessor.readGuardiansFile(fichero);
            for (Guardian guardian : fileAccessor.getLlistaGuardians()) {
                fileAccessor.insertGuardian(guardian);
            }
            System.out.println("Guardianes insertados correctamente.");
        } catch (IOException | SQLException e) {
            System.out.println("Error al insertar guardianes: " + e.getMessage());
        }
    }

    private static void insertarChild() {
        Scanner scanner = new Scanner(System.in);
        String fichero;
        System.out.print("Indica el archivo y su ruta: ");
        fichero = scanner.nextLine();
        try {
            FileAccessor fileAccessor = new FileAccessor();
            fileAccessor.readChildrenFile(fichero);
            for (Child child : fileAccessor.getLlistaChildren()) {
                fileAccessor.insertChild(child);
            }
            System.out.println("Children insertados correctamente.");
        } catch (IOException | SQLException e) {
            System.out.println("Error al insertar children: " + e.getMessage());
        }
    }

    private static void insertarCamp() {
        Scanner scanner = new Scanner(System.in);

        String fichero;
        System.out.print("Indica el archivo y su ruta: ");
        fichero = scanner.nextLine();

        try {
            FileAccessor fileAccessor = new FileAccessor();
            fileAccessor.readCampsFile(fichero);
            for (Camp camp : fileAccessor.getLlistaCamps()) {
                fileAccessor.insertCamp(camp);
            }
            System.out.println("Camps insertados correctamente.");
        } catch (IOException | SQLException e) {
            System.out.println("Error al insertar camps: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertarActivity() {
        Scanner scanner = new Scanner(System.in);

        String fichero;
        System.out.print("Indica el archivo y su ruta: ");
        fichero = scanner.nextLine();

        try {
            FileAccessor fileAccessor = new FileAccessor();
            fileAccessor.readActivitiesFile(fichero);
            for (Activity activity : fileAccessor.getLlistaActivities()) {
                fileAccessor.insertActivity(activity);
            }
            System.out.println("Activities insertadas correctamente.");
        } catch (IOException | SQLException e) {
            System.out.println("Error al insertar activities: " + e.getMessage());
        }
    }






    private static void eliminarEntidad() {
        Scanner scanner = new Scanner(System.in);
        int opcion2;

        do {
            System.out.println("Seleccione una entidad a eliminar:");
            System.out.println("1. Guardian");
            System.out.println("2. Child");
            System.out.println("3. Camp");
            System.out.println("4. Activity");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion2 = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion2) {
                case 1:
                    eliminarGuardian();
                    break;
                case 2:
                    eliminarChild();
                    break;
                case 3:
                    eliminarCamp();
                    break;
                case 4:
                    eliminarActivity();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione otra opción.");
            }

        } while (opcion2 != 5);
    }

    private static void eliminarGuardian() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardián a eliminar: ");
        String dni = scanner.nextLine();

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Guardian guardian = (Guardian) session.get(Guardian.class, dni);
            if (guardian != null) {
                session.delete(guardian);
                System.out.println("Guardián eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún guardián con el DNI proporcionado.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void eliminarChild() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del Child a eliminar: ");
        int idChild = scanner.nextInt();

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Child child = (Child) session.get(Child.class, idChild);
            if (child != null) {
                session.delete(child);
                System.out.println("Child eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún Child con el ID proporcionado.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private static void eliminarCamp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del Camp a eliminar: ");
        int idCamp = scanner.nextInt();

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Camp camp = (Camp) session.get(Camp.class, idCamp);
            if (camp != null) {
                session.delete(camp);
                System.out.println("Camp eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún Camp con el ID proporcionado.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private static void eliminarActivity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID de la Activity a eliminar: ");
        int idActivity = scanner.nextInt();

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Activity activity = (Activity) session.get(Activity.class, idActivity);
            if (activity != null) {
                session.delete(activity);
                System.out.println("Activity eliminada correctamente.");
            } else {
                System.out.println("No se encontró ninguna Activity con el ID proporcionado.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }









    private static void actualizarEntidad() {
        Scanner scanner = new Scanner(System.in);
        int opcion2;

        do {
            System.out.println("Seleccione una entidad a actualizar:");
            System.out.println("1. Guardian");
            System.out.println("2. Child");
            System.out.println("3. Camp");
            System.out.println("4. Activity");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion2 = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion2) {
                case 1:
                    actualizarGuardian();
                    break;
                case 2:
                    actualizarChild();
                    break;
                case 3:
                    actualizarCamp();
                    break;
                case 4:
                    actualizarActivity();
                    break;
                case 5:

                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione otra opción.");
            }

        } while (opcion2 != 5);
    }
    private static void actualizarGuardian() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardián a actualizar: ");
        String dni = scanner.nextLine();
        scanner.next();

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Guardian guardian = (Guardian) session.get(Guardian.class, dni);
            if (guardian != null) {

                // Solicitar al usuario que ingrese los nuevos datos del guardián, excepto el primer campo (DNI)
                System.out.println("Ingrese el nuevo nombre del guardián: ");
                String nuevoNombre = scanner.nextLine();
                guardian.setName(nuevoNombre);

                System.out.println("Ingrese el nuevo teléfono del guardián: ");
                String nuevoTelefono = scanner.nextLine();
                guardian.setPhone(nuevoTelefono);

                System.out.println("Ingrese la nueva dirección del guardián: ");
                String nuevaDireccion = scanner.nextLine();
                guardian.setAddress(nuevaDireccion);

                System.out.println("Ingrese el nuevo email del guardián: ");
                String nuevoEmail = scanner.nextLine();
                guardian.setEmail(nuevoEmail);

                session.update(guardian);
                System.out.println("Guardián actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún guardián con el DNI proporcionado.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    private static void actualizarChild() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del Child a actualizar: ");
        int idChild = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Child child = (Child) session.get(Child.class, idChild);
            if (child != null) {
                System.out.println("Ingrese el nuevo nombre para el Child: ");
                String nuevoNombre = scanner.nextLine();

                // Manejar el formato de fecha
                System.out.println("Ingrese la nueva fecha de nacimiento para el Child (YYYY-MM-DD): ");
                String nuevaFechaStr = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date nuevaFecha = dateFormat.parse(nuevaFechaStr);
                // Convertir la fecha a String si es necesario
                // String nuevaFechaStr = dateFormat.format(nuevaFecha);

                // Actualizar solo el nombre y la fecha del Child
                child.setName(nuevoNombre);
                child.setBirthday(nuevaFecha);

                session.update(child);
                System.out.println("Child actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún Child con el ID proporcionado.");
            }
            tx.commit();
        } catch (HibernateException | ParseException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void actualizarCamp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del Camp a actualizar: ");
        int idCamp = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente después de leer el entero

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Camp camp = (Camp) session.get(Camp.class, idCamp);
            if (camp != null) {
                // Solicitar al usuario que ingrese los nuevos datos del campamento, excepto el primer campo (ID)
                System.out.println("Ingrese el nuevo sitio del campamento: ");
                String nuevoSitio = scanner.nextLine();
                camp.setSite(nuevoSitio);

                session.update(camp);
                System.out.println("Camp actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún Camp con el ID proporcionado.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    private static void actualizarActivity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID de la Activity a actualizar: ");
        int idActivity = scanner.nextInt();

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Activity activity = (Activity) session.get(Activity.class, idActivity);
            if (activity != null) {
                System.out.println("Ingrese la nueva descripción de la actividad: ");
                String nuevaDescripcion = scanner.nextLine();
                nuevaDescripcion= scanner.nextLine();
                activity.setDescription(nuevaDescripcion);

                session.update(activity);
                System.out.println("Activity actualizada correctamente.");
            } else {
                System.out.println("No se encontró ninguna Activity con el ID proporcionado.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
