package activity4.dao.oracle11g;

import activity4.dao.DAOException;
import activity4.dao.DepartmentDAO;
import activity4.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ora11gDepartmentDAO implements DepartmentDAO {

    private final String INSERT = "INSERT INTO department(id_department,name_department,locality) VALUES(?, ?, ?)";
    private final String UPDATE = "UPDATE department SET name_department = ?, locality = ? WHERE id_department = ?";
    private final String DELETE = "DELETE FROM department WHERE id_department = ?";
    private final String GET_ALL = "SELECT id_department,name_department,locality FROM department";
    private final String GET_BY_ID = "SELECT id_department,name_department,locality FROM department WHERE id_department = ?";

    private final Connection conn;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public Ora11gDepartmentDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department a) throws DAOException {
        try {
            pStat = conn.prepareStatement(INSERT);
            pStat.setInt(1, a.getId());
            pStat.setString(2, a.getName());
            pStat.setString(3, a.getLocality());
            pStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error INSERT Ora11gDepartmentDAO", e);
        } finally {
            closePS("INSERT");
        }
    }

    @Override
    public void update(Department a) throws DAOException {
        try {
            pStat = conn.prepareStatement(UPDATE);
            pStat.setString(1, a.getName());
            pStat.setString(2, a.getLocality());
            pStat.setInt(3, a.getId());
            pStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error UPDATE Ora11gDepartmentDAO", e);
        } finally {
            closePS("UPDATE");
        }
    }

    @Override
    public void delete(Department a) throws DAOException {
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
    public Department getById(Integer id) throws DAOException {
        Department department = null;
        try {
            pStat = conn.prepareStatement(GET_BY_ID);
            pStat.setInt(1, id);
            rs = pStat.executeQuery();
            if (rs.next()) {
                department = convert(rs);
            } else {
                throw new DAOException("Ora11gDepartmentDAO - Record not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Error GET_BY_ID Ora11gDepartmentDAO", e);
        } finally {
            closeRS("GET_BY_ID");
            closePS("GET_BY_ID");
        }
        return department;
    }

    @Override
    public List<Department> getAll() throws DAOException {
        List<Department> departmentList = new ArrayList<>();
        try {
            pStat = conn.prepareStatement(GET_ALL);
            rs = pStat.executeQuery();
            while (rs.next()) {
                departmentList.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error GET_ALL Ora11gDepartmentDAO", e);
        } finally {
            closeRS("GET_ALL");
            closePS("GET_ALL");
        }
        return departmentList;
    }

    private Department convert(ResultSet rs) throws SQLException {
        return new Department(
                rs.getInt("id_department"),
                rs.getString("name_department"),
                rs.getString("locality")
        );
    }

    private void closeRS(String info) throws DAOException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DAOException("Error closing " + info + " Ora11gDepartmentDAO", e);
            }
        }
    }

    private void closePS(String info) throws DAOException {
        if (pStat != null) {
            try {
                pStat.close();
            } catch (SQLException e) {
                throw new DAOException("Error closing " + info + " Ora11gDepartmentDAO", e);
            }
        }
    }

}