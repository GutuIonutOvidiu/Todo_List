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

@BotController
public class MyTelegramBot implements TelegramMvcController {

    @Autowired
    private UserService userService;

    @Value("${bot.token}")
    private String token;

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

    @MessageRequest("/hello {firstname:[\\S]+} {lastname:[\\S]+} {username:[\\S]+} {email:[\\S]+}")
    public String helloWithNewUserDetails(@BotPathVariable("firstname") String firstname, @BotPathVariable("lastname") String lastame,@BotPathVariable("username") String username,@BotPathVariable("email") String email) {
        net.atlassian.guio.TodoList.entities.User newUser = new net.atlassian.guio.TodoList.entities.User(firstname,lastame,username,email, this.token);
        this.userService.addUser(newUser);
        // Return a string if you need to reply with a simple message
        return "Hello, " + username + " your accont hase been created";
    }

    @MessageRequest("/helloCallback")
    public String helloWithCustomCallback(TelegramRequest request, User user) {
        request.setCallback(new Callback() {
            @Override
            public void onResponse(BaseRequest request, BaseResponse response) {
                // TODO
            }

            @Override
            public void onFailure(BaseRequest request, IOException e) {
                // TODO
            }
        });
        return "Hello, " + user.firstName() + "!";
    }

    //comanda noua register user
}
