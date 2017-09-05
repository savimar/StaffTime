package entity;import javax.persistence.*;@Entity@Table(name = "emploee", schema = "public", catalog = "stafftime")public class Employee {    private long id;    private String name;    @Id    @Column(name = "id", nullable = false)    public long getId() {        return id;    }    public void setId(long id) {        this.id = id;    }    @Basic    @Column(name = "name", nullable = false, length = 2044)    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    @Override    public boolean equals(Object o) {        if (this == o) return true;        if (o == null || getClass() != o.getClass()) return false;        Employee emploee = (Employee) o;        if (id != emploee.id) return false;        if (name != null ? !name.equals(emploee.name) : emploee.name != null) return false;        return true;    }    @Override    public int hashCode() {        int result = (int) (id ^ (id >>> 32));        result = 31 * result + (name != null ? name.hashCode() : 0);        return result;    }}