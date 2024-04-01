import Entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class FileAccessor {

    private ArrayList<Guardian> llistaGuardians = new ArrayList<>();
    private ArrayList<Child> llistaChildren = new ArrayList<>();
    private ArrayList<Activity> llistaActivities = new ArrayList<>();
    private ArrayList<Camp> llistaCamps = new ArrayList<>();
    private ArrayList<ChildActivity> llistaChildActivities = new ArrayList<>();
    private ArrayList<CampChild> llistaCampChildren = new ArrayList<>();

    public ArrayList<Guardian> getLlistaGuardians() {
        return llistaGuardians;
    }

    public ArrayList<Child> getLlistaChildren() {
        return llistaChildren;
    }

    public ArrayList<Activity> getLlistaActivities() {
        return llistaActivities;
    }

    public ArrayList<Camp> getLlistaCamps() {
        return llistaCamps;
    }

    public ArrayList<ChildActivity> getLlistaChildActivities() {
        return llistaChildActivities;
    }

    public ArrayList<CampChild> getLlistaCampChildren() {
        return llistaCampChildren;
    }

    private Connection connection;

    public FileAccessor() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/summer_camp";
        String user = "postgres";
        String password = "postgres";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void readGuardiansFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try (Scanner scanner = new Scanner(linea).useDelimiter(",")) {
                    String  dni = scanner.next();
                    String name = scanner.next();
                    String phone = scanner.next();
                    String address = scanner.next();
                    String email = scanner.next();
                    Guardian guardian = new Guardian(dni, name, phone, address, email);
                    llistaGuardians.add(guardian);
                }
            }
        }
    }

    public void readChildrenFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try (Scanner scanner = new Scanner(linea).useDelimiter(",")) {
                    int idChild = scanner.nextInt();
                    String name = scanner.next();
                    // Convertir la fecha de nacimiento de String a Date
                    String birthdayStr = scanner.next();
                    Date birthday = null;
                    try {
                        birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
                    } catch (ParseException e) {
                        System.out.println("Error al convertir la fecha: " + e.getMessage());
                        continue; // Saltar a la siguiente línea en caso de error
                    }
                    boolean specialMenu = scanner.nextBoolean();
                    String dniGuardian = scanner.next();

                    Child child = new Child(idChild, name, birthday, specialMenu, dniGuardian);
                    llistaChildren.add(child);

                } catch (NoSuchElementException e) {
                    System.out.println("Error: Se esperaban más elementos en la línea: " + linea);
                }
            }
        }
    }

    public void readActivitiesFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try (Scanner scanner = new Scanner(linea).useDelimiter(",")) {
                    int idActivity = scanner.nextInt();
                    String description = scanner.next();
                    Activity activity = new Activity(idActivity, description);
                    llistaActivities.add(activity);
                }
            }
        }
    }

    public void readCampsFile(String filename) throws IOException, ParseException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try (Scanner scanner = new Scanner(linea).useDelimiter(",")) {
                    int idCamp = scanner.nextInt();
                    String site = scanner.next();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fromDate = dateFormat.parse(scanner.next());
                    Date toDate = dateFormat.parse(scanner.next());

                    Camp camp = new Camp(idCamp, site, fromDate, toDate);
                    llistaCamps.add(camp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void readChildActivitiesFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try (Scanner scanner = new Scanner(linea).useDelimiter(",")) {
                    int idChild = scanner.nextInt();
                    int idActivity = scanner.nextInt();
                    Child child = findChildById(idChild);
                    Activity activity = findActivityById(idActivity);
                    ChildActivity childActivity = new ChildActivity(child, activity);
                    llistaChildActivities.add(childActivity);
                }
            }
        }
    }

    public void readCampChildrenFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try (Scanner scanner = new Scanner(linea).useDelimiter(",")) {
                    int idCamp = scanner.nextInt();
                    int idChild = scanner.nextInt();
                    Camp camp = findCampById(idCamp);
                    Child child = findChildById(idChild);
                    CampChild campChild = new CampChild(camp, child);
                    llistaCampChildren.add(campChild);
                }
            }
        }
    }

    private Guardian findGuardianByDni(String dni) {
        for (Guardian guardian : llistaGuardians) {
            if (guardian.getDni().equals(dni)) {
                return guardian;
            }
        }
        return null; // Handle not found case appropriately
    }

    private Child findChildById(int idChild) {
        for (Child child : llistaChildren) {
            if (child.getIdChild() == idChild) {
                return child;
            }
        }
        return null; // Handle not found case appropriately
    }

    private Activity findActivityById(int idActivity) {
        for (Activity activity : llistaActivities) {
            if (activity.getIdActivity() == idActivity) {
                return activity;
            }
        }
        return null; // Handle not found case appropriately
    }

    private Camp findCampById(int idCamp) {
        for (Camp camp : llistaCamps) {
            if (camp.getIdCamp() == idCamp) {
                return camp;
            }
        }
        return null; // Handle not found case appropriately
    }

    public void insertGuardian(Guardian guardian) throws SQLException {
        String query = "INSERT INTO guardian (dni, name, phone, address, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, guardian.getDni());
            statement.setString(2, guardian.getName());
            statement.setString(3, guardian.getPhone());
            statement.setString(4, guardian.getAddress());
            statement.setString(5, guardian.getEmail());
            statement.executeUpdate();
        }
    }

    public void insertChild(Child child) throws SQLException {
        String query = "INSERT INTO child (idchild, name, birthday, specialmenu, dniguardian) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, child.getIdChild());
            statement.setString(2, child.getName());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date birthdayDate = child.getBirthday();

            java.sql.Date sqlBirthdayDate = new java.sql.Date(birthdayDate.getTime());

            statement.setDate(3, sqlBirthdayDate);

            statement.setBoolean(4, child.isSpecialMenu());
            statement.setString(5, child.getDniguardian());
            statement.executeUpdate();
        }
    }
    public void insertActivity(Activity activity) throws SQLException {
        String query = "INSERT INTO activity (idactivity, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, activity.getIdActivity());
            statement.setString(2, activity.getDescription());
            statement.executeUpdate();
        }
    }

    public void insertCamp(Camp camp) throws SQLException {
        String query = "INSERT INTO camp (idcamp, site, fromdate, todate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, camp.getIdCamp());
            statement.setString(2, camp.getSite());
            statement.setDate(3, new java.sql.Date(camp.getFromDate().getTime()));
            statement.setDate(4, new java.sql.Date(camp.getToDate().getTime()));
            statement.executeUpdate();
        }
    }
    public void insertCampChild(CampChild campChild) throws SQLException {
        if (campChild.getCamp() != null && campChild.getChild() != null) { // Verificar que tanto Camp como Child no sean null
            String query = "INSERT INTO camp_child (id_camp, id_child) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, campChild.getCamp().getIdCamp());
                statement.setInt(2, campChild.getChild().getIdChild());
                statement.executeUpdate();
            }
        } else {
            System.out.println("Error: El objeto Camp o Child en CampChild es null.");
        }
    }

    public void insertChildActivity(ChildActivity childActivity) throws SQLException {
        if (childActivity.getChild() != null && childActivity.getActivity() != null) { // Verificar que tanto Child como Activity no sean null
            String query = "INSERT INTO child_activity (id_child, id_activity) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, childActivity.getChild().getIdChild());
                statement.setInt(2, childActivity.getActivity().getIdActivity());
                statement.executeUpdate();
            }
        } else {
            System.out.println("Error: El objeto Child o Activity en ChildActivity es null.");
        }
    }
}
