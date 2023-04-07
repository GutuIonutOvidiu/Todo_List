package net.atlassian.guio.TodoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    public TodoList addList(TodoList list) {
        return listRepository.save(list);
    }

}
