package net.atlassian.guio.TodoList;

import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.TelegramRequest;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.List;

@BotController
public class AdminTelegramBot implements TelegramMvcController {


    private List<TelegramBot> nonAdminBots;

    public AdminTelegramBot(@Autowired List<TelegramBot> nonAdminBots) {
        this.nonAdminBots = nonAdminBots;
    }

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

    @MessageRequest("/register_user {firstname:[\\S]+} {lastname:[\\S]+} {username:[\\S]+} {email:[\\S]+} {telegram_token:[\\S]+}")
    public String registerUserDetails(@BotPathVariable("firstname") String firstname, @BotPathVariable("lastname") String lastame,@BotPathVariable("username") String username,@BotPathVariable("email") String email,Chat chat) {
        net.atlassian.guio.TodoList.entities.User newUser = new net.atlassian.guio.TodoList.entities.User(firstname,lastame,username,email, this.token);
        try {
            this.userService.addUser(newUser);
        }catch (Exception e){
            return e.toString();
        }
        // Return a string if you need to reply with a simple message
        return "Hello, " + username + " your accont hase been created";
    }

    @MessageRequest("/test_admin")
    public String test_admin() {
             return "Hello,";// + this.nonAdminBots.size();
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

}
