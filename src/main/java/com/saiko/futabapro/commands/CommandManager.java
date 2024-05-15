package com.saiko.futabapro.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandManager {

    private final GatewayDiscordClient client;
    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager(GatewayDiscordClient client) {
        this.client = client;
    }

    @PostConstruct
    public void init() {
        client.getEventDispatcher().on(ChatInputInteractionEvent.class)
                .flatMap(this::handle)
                .subscribe();
    }

    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
        ApplicationCommandRequest commandRequest = ApplicationCommandRequest.builder()
                .name(command.getName())
                .description(command.getDescription())
                .addAllOptions(command.getOptions())
                .build();
        long applicationId = client.getRestClient().getApplicationId().block();
        client.getRestClient().getApplicationService()
                .createGlobalApplicationCommand(applicationId, commandRequest)
                .subscribe();
    }

    private Mono<Void> handle(ChatInputInteractionEvent event) {
        Command command = commands.get(event.getCommandName());
        if (command != null) {
            return command.handle(event);
        }
        return Mono.empty();
    }
}

