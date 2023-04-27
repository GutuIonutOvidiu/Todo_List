package net.atlassian.guio.TodoList.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="list")
public class TodoList {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
