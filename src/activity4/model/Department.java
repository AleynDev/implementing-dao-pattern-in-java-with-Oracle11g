package activity4.model;

public class Department {

    private Integer id;
    private String name;
    private String locality;

    public Department(Integer id, String name, String locality) {
        this.id = id;
        this.name = name;
        this.locality = locality;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocality() {
        return locality;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locality='" + locality + '\'' +
                '}';
    }
}
