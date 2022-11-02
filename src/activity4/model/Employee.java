package activity4.model;

import java.time.LocalDate;

public class Employee {

    private Integer id;
    private String name;
    private String position;
    private LocalDate initDate;
    private Double salary;
    private Double salaryPlus;
    private Integer idDept;

    public Employee(Integer id, String name, String position, String initDate, Double salary, Double salaryPlus, Integer idDept) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.initDate = LocalDate.parse(initDate);
        this.salary = salary;
        this.salaryPlus = salaryPlus;
        this.idDept = idDept;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getSalaryPlus() {
        return salaryPlus;
    }

    public Integer getIdDept() {
        return idDept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", admissionDate=" + initDate +
                ", salary=" + salary +
                ", commission=" + salaryPlus +
                ", idDept=" + idDept +
                '}';
    }
}
