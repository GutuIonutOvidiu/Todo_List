package net.atlassian.guio.TodoList;

import net.atlassian.guio.TodoList.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {

		SpringApplication.run(TodoListApplication.class, args);


	}

}
