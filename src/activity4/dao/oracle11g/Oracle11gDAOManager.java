package activity4.dao.oracle11g;

import activity4.dao.DAOManager;
import activity4.dao.DepartmentDAO;
import activity4.dao.EmployeeDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle11gDAOManager implements DAOManager {

    private final Connection conn;
    private final String URL_DB;
    private final String USER;
    private final String PASSWD;

    private DepartmentDAO departmentDAO = null;
    private EmployeeDAO employeeDAO = null;

    public Oracle11gDAOManager() {
        this.URL_DB = "jdbc:oracle:thin:@localhost:49161:XE";
        this.USER = initCredential("user");
        this.PASSWD = initCredential("passwd");
        this.conn = giveConnection(this.URL_DB, this.USER, this.PASSWD);
    }

    @Override
    public DepartmentDAO getDepartmentDAO() {
        if (departmentDAO == null) {
            departmentDAO = new Ora11gDepartmentDAO(this.conn);
        }
        return departmentDAO;
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        if (employeeDAO == null) {
            employeeDAO = new Ora11gEmployeeDAO(this.conn);
        }
        return employeeDAO;
    }

    public Connection giveConnection(String url, String user, String passwd) {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL_DB, USER, PASSWD);
        } catch (Exception e) {
            System.out.println("Error: Connection Oracle11g");
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if ((connection != null) && (!connection.isClosed())) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error close connection");
        }
    }

    private static String initCredential(String credential) {
        String data = "";
        try {
            String fileName = "./src/resources/CredentialDB11g.txt";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(credential)) {
                    data = line.substring(line.indexOf(":") + 1).trim();
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error getting credentials");
        }
        return data;
    }
}
