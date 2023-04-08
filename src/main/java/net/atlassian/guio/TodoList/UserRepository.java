package net.atlassian.guio.TodoList;

import net.atlassian.guio.TodoList.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByTelegramToken (String token);
}


