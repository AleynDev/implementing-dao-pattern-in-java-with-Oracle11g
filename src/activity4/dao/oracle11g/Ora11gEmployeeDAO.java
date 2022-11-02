package activity4.dao.oracle11g;

import activity4.dao.DAOException;
import activity4.dao.EmployeeDAO;
import activity4.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Ora11gEmployeeDAO implements EmployeeDAO {

    private final String INSERT = "INSERT INTO employee(id_employee, name_employee, position, init_date, salary, salary_plus, id_depart) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE employee SET name_employee = ?, position = ?, init_date = ?, salary = ?, salary_plus = ?, id_depart = ? WHERE id_employee = ?";
    private final String DELETE = "DELETE FROM employee WHERE id_employee = ?";
    private final String GET_ALL = "SELECT id_employee, name_employee, position, init_date, salary, salary_plus, id_depart FROM employee";
    private final String GET_BY_ID = "SELECT id_employee, name_employee, position, init_date, salary, salary_plus, id_depart FROM employee WHERE id_employee = ?";

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private final Connection conn;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public Ora11gEmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Employee a) throws DAOException {
        try {
            pStat = conn.prepareStatement(INSERT);
            pStat.setInt(1, a.getId());
            pStat.setString(2, a.getName());
            pStat.setString(3, a.getPosition());
            pStat.setObject(4, a.getInitDate());
            pStat.setDouble(5, a.getSalary());
            pStat.setDouble(6, a.getSalaryPlus());
            pStat.setInt(7, a.getIdDept());
            pStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error INSERT Ora11gEmployeeDAO", e);
        } finally {
            closePS("INSERT");
        }
    }

    @Override
    public void update(Employee a) throws DAOException {
        try {
            pStat = conn.prepareStatement(UPDATE);
            pStat.setString(1, a.getName());
            pStat.setString(2, a.getPosition());
            pStat.setObject(3, a.getInitDate());
            pStat.setDouble(4, a.getSalary());
            pStat.setDouble(5, a.getSalaryPlus());
            pStat.setInt(6, a.getIdDept());
            pStat.setInt(7, a.getId());
            pStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error UPDATE Ora11gEmployeeDAO", e);
        } finally {
            closePS("UPDATE");
        }
    }

    @Override
    public void delete(Employee a) throws DAOException {
        try {
            pStat = conn.prepareStatement(DELETE);
            pStat.setInt(1, a.getId());
            pStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error DELETE Ora11gDepartmentDAO", e);
        } finally {
            closePS("DELETE");
        }
    }

    @Override
    public Employee getById(Integer id) throws DAOException {
        Employee employee = null;
        try {
            pStat = conn.prepareStatement(GET_BY_ID);
            pStat.setInt(1, id);
            rs = pStat.executeQuery();
            if (rs.next()) {
                employee = convert(rs);
            } else {
                throw new DAOException("Ora11gEmployeeDAO - Record not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Error GET_BY_ID Ora11gEmployeeDAO", e);
        } finally {
            closeRS("GET_BY_ID");
            closePS("GET_BY_ID");
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() throws DAOException{
        List<Employee> employeeList = new ArrayList<>();
        try {
            pStat = conn.prepareStatement(GET_ALL);
            rs = pStat.executeQuery();
            while (rs.next()) {
                employeeList.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error GET_ALL Ora11gEmployeeDAO", e);
        } finally {
            closeRS("GET_ALL");
            closePS("GET_ALL");
        }
        return employeeList;
    }

    private Employee convert(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id_employee"),
                rs.getString("name_employee"),
                rs.getString("position"),
                sdf.format(rs.getDate("init_date")),
                rs.getDouble("salary"),
                rs.getDouble("salary_plus"),
                rs.getInt("id_depart")
        );
    }

    private void closeRS(String info) throws DAOException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DAOException("Error closing " + info + " Ora11gEmployeeDAO", e);
            }
        }
    }

    private void closePS(String info) throws DAOException {
        if (pStat != null) {
            try {
                pStat.close();
            } catch (SQLException e) {
                throw new DAOException("Error closing " + info + " Ora11gEmployeeDAO", e);
            }
        }
    }

}