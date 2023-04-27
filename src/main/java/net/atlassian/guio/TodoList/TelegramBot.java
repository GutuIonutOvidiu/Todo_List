package net.atlassian.guio.TodoList;

import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.TelegramRequest;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

@BotController
public class TelegramBot implements TelegramMvcController {

    @Autowired
    private UserService userService;
    private String token;

    //public TelegramBot(String token) {
    //    this.token = token;
    //}
    public net.atlassian.guio.TodoList.entities.User login(){
        return this.userService.login(this.token);
    }

    @Override
    public String getToken() {
        return token;
    }


    @BotRequest(value = "/hello", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest hello(User user, Chat chat) {
        net.atlassian.guio.TodoList.entities.User loggedInUser = this.login();
        if (loggedInUser == null){
            return new SendMessage(chat.id(), "This user is not registered. Please, sign up");
        }

        return new SendMessage(chat.id(), "Hello, " + loggedInUser.getUserName() + "!");
    }

    @MessageRequest(value = "/test")
    public String test(User user, Chat chat) {
        return "ok";
    }

}
