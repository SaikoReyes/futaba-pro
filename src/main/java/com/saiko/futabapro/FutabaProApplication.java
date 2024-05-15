package com.saiko.futabapro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.saiko.futabapro.commands.CommandManager;
import com.saiko.futabapro.commands.EchoCommand;
import com.saiko.futabapro.commands.PingCommand;


@SpringBootApplication
public class FutabaProApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FutabaProApplication.class, args);
		CommandManager manager = context.getBean(CommandManager.class);

		manager.registerCommand(context.getBean(PingCommand.class));
        manager.registerCommand(context.getBean(EchoCommand.class));
	}

}
