package ru.savimar.stafftime.entity;

import javax.persistence.*;

@Entity
public class Employee {
    private long id;
    private String name;
    private Status status;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 2044)
    public String getName() {
        return name;
    }

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "employee_id")
    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee() {

    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee emploee = (Employee) o;

        if (id != emploee.id) return false;
        if (name != null ? !name.equals(emploee.name) : emploee.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
