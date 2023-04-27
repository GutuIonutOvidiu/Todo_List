package net.atlassian.guio.TodoList;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TelegramBotConfig {

    @Bean
    public List<TelegramBot> nonAdminBots() {
        List<TelegramBot> bots = new ArrayList<>();
        return bots;
    }

    @Bean
    @Primary
    public AdminTelegramBot adminTelegramBot() {
        return new AdminTelegramBot(nonAdminBots());
    }
}
