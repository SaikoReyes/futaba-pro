package com.saiko.futabapro.commands;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class EchoCommand implements Command {

    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public String getDescription() {
        return "Echoes back the provided text.";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String message = event.getOption("message").get().getValue().get().asString();
        return event.reply(message);
    }

    @Override
    public List<ApplicationCommandOptionData> getOptions() {
        return Collections.singletonList(ApplicationCommandOptionData.builder()
                .name("message")
                .description("The message to echo back")    
                .type(3)            
                .required(true)
                .build());
    }
}

