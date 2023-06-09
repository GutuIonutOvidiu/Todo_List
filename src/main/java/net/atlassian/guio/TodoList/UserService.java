package net.atlassian.guio.TodoList;

import net.atlassian.guio.TodoList.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User todoUser) {

        return userRepository.save(todoUser);
    }

    public User login (String token){
        return userRepository.findUserByTelegramToken(token);
    }

}

