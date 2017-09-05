package ru.savimar.stafftime.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "status", schema = "public", catalog = "stafftime")
public class Status{
    private long id;
    private LocalDateTime time;

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
