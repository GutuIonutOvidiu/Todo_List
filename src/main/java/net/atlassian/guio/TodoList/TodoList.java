package net.atlassian.guio.TodoList;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="list")
public class TodoList {

    @Id
    private final Long id;
   @OneToMany(mappedBy = "todoList")
    private List<Task> tasks;

   @ManyToOne
   @JoinColumn(name="user_id")
   private User creator;

    public TodoList(Long id, List<Task> tasks) {
        this.id = id;
        this.tasks = tasks;
    }
}
