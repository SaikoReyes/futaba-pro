package com.saiko.futabapro;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FutabaProApplication {

	@Value("${discord.token}")
    private String token;

	public static void main(String[] args) {
		SpringApplication.run(FutabaProApplication.class, args);
	}

	@Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();
    }

}
