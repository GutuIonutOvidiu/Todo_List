package net.atlassian.guio.TodoList;

import net.atlassian.guio.TodoList.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<TodoList, Long> {
}
