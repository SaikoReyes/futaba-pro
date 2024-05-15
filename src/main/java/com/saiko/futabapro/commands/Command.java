package com.saiko.futabapro.commands;

import java.util.List;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import reactor.core.publisher.Mono;

public interface Command {
    String getName();
    Mono<Void> handle(ChatInputInteractionEvent event);
    String getDescription();
    List<ApplicationCommandOptionData> getOptions();
}

