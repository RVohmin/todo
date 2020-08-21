package ru.job4j.todo.persistence;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "describe")
    private String describe;

    @Column (name = "created")
    Timestamp created;

    @Column (name = "done")
    private boolean done;

    public Task() {
    }

    public Task(String describe, boolean done) {
        this.describe = describe;
        this.done = done;
    }

    public Task(int id, String describe, boolean done) {
        this(describe, done);
        this.id = id;
    }

    public Task(int id, String describe, Timestamp created, boolean done) {
        this(id, describe, done);
        this.created = created;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id &&
                done == task.done &&
                Objects.equals(describe, task.describe) &&
                Objects.equals(created, task.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, describe, created, done);
    }
}
