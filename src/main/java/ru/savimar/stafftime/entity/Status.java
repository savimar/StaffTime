package ru.savimar.stafftime.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Status {
    private long id;
    private LocalDateTime time;
    private String name;

    public static final String GET = "Status.get";
    public static final String ALL_SORTED = "Status.getAll";
    public static final String DELETE = "Status.delete";

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public LocalDateTime getTime() {
        return time;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (id != status.id) return false;
        if (time != null ? !time.equals(status.time) : status.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
