package net.atlassian.guio.TodoList.entities;

import jakarta.persistence.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.Generated;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="firstName")
    private   String firstName;
    @Column(name="secondName")
    private   String secondName;
    @Column(name="userName",unique = true)
    private   String userName;
    @Column(name="emailAdress",unique = true)
    private   String emailAdress;
    @Column(name="telegramToken",unique = true)
    private   String telegramToken;

    @OneToMany(mappedBy = "asignee")
    private List<Task> my_task;

    public User(){

    }
    public User(String firstName, String secondName, String userName, String emailAdress, String token) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.userName = userName;
        this.emailAdress = emailAdress;
        this.telegramToken = token;
    }

    @OneToMany(mappedBy = "reporter")
    private List<Task> task_reported_by_me;

    @OneToMany(mappedBy = "creator")
    private List<TodoList> my_lists;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getTelegramToken() {
        return telegramToken;
    }

    public List<Task> getMy_task() {
        return my_task;
    }

    public List<Task> getTask_reported_by_me() {
        return task_reported_by_me;
    }

    public List<TodoList> getMy_lists() {
        return my_lists;
    }

    public User(Long id, String firstName, String secondName, String userName, String emailAdress, String telegramToken) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.emailAdress = emailAdress;
        this.telegramToken = telegramToken;
        this.userName = userName;
    }


}
