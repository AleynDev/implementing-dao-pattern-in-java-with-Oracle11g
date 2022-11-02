package activity4;

import activity4.dao.DAOException;
import activity4.dao.oracle11g.Oracle11gDAOManager;
import activity4.model.Department;
import activity4.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArrayList<Department> departments = initDepartmentsData();
        ArrayList<Employee> employees = initEmployeesData();

        Oracle11gDAOManager manager = new Oracle11gDAOManager();

        // Delete records -------------------------------------------
        departments.forEach(department -> {
            try {
                manager.getDepartmentDAO().delete(department);
            } catch (DAOException ignored) {}
        });

        employees.forEach(employee -> {
            try {
                manager.getEmployeeDAO().delete(employee);
            } catch (DAOException ignored) {}
        });

        // Add records ----------------------------------------------
        departments.forEach(department -> {
            try {
                manager.getDepartmentDAO().insert(department);
            } catch (DAOException ignored) {}
        });

        employees.forEach(employee -> {
            try {
                manager.getEmployeeDAO().insert(employee);
            } catch (DAOException ignored) {}
        });

        // Update record --------------------------------------------
        Employee firstEmployee = new Employee(1,"Pepe Benavente","Director","2005-01-01",1550.95,10.5,1);
        try {
            manager.getEmployeeDAO().update(firstEmployee);
        } catch (DAOException ignored) {}

        // Get by id & get all --------------------------------------
        try {
            firstEmployee = manager.getEmployeeDAO().getById(4);
            System.out.println(firstEmployee);

            List<Department> dpt = manager.getDepartmentDAO().getAll();
            dpt.forEach(System.out::println);
        } catch (DAOException ignored) {}

    }

    private static ArrayList<Employee> initEmployeesData() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Pepe Benavente","Director","2005-01-01",3900.35,15.5,1));
        employees.add(new Employee(2,"Julian Perez","Conductor","2015-11-01",1200.50,3.0,4));
        employees.add(new Employee(3,"Carmen Calvo","Limpiadora","2021-01-01",900.35,0.0,3));
        employees.add(new Employee(4,"Jose Iglesias","Empleado","2017-01-01",1000.35,5.5,2));
        employees.add(new Employee(5,"Pepe Sanchez","Empleado","2013-01-01",1900.35,10.5,1));
        employees.add(new Employee(6,"Isabel Diaz","Empleado","2005-01-01",1400.35,11.5,4));
        return  employees;
    }

    private static ArrayList<Department> initDepartmentsData() {
        ArrayList<Department> dps = new ArrayList<>();
        dps.add(new Department(1,"Informatica","Aguimes"));
        dps.add(new Department(2,"Limpieza","Vecindario"));
        dps.add(new Department(3,"RRHH","Aguimes"));
        dps.add(new Department(4,"Administracion","Vecindario"));
        return dps;
    }

}
