package net.atlassian.guio.TodoList;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    private final Long id;
    @Column(name="firstName")
    private final String firstName;
    @Column(name="secondName")
    private final String secondName;
    @Column(name="userName")
    private final String userName;
    @Column(name="emailAdress")
    private final String emailAdress;
    @Column(name="telegramToken")
    private final String telegramToken;

    @OneToMany(mappedBy = "asignee")
    private List<Task> my_task;

    @OneToMany(mappedBy = "reporter")
    private List<Task> task_reported_by_me;

    @OneToMany(mappedBy = "creator")
    private List<TodoList> my_lists;

    public User(Long id, String firstName, String secondName, String userName, String emailAdress, String telegramToken) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.emailAdress = emailAdress;
        this.telegramToken = telegramToken;
        this.userName = userName;
    }

}
