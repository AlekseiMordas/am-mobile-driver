package com.orientation;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;

public class GetOrientationCommand {

	private static final Logger LOGGER = Logger.getLogger(GetOrientationCommand.class);

	private Command command;
	
	private HttpCommandExecutor executor = null;

	private final String GET_SCREEN_ORIENTATION_COMMAND = "getScreenOrientation";


	public GetOrientationCommand(SessionId sessionId, HttpCommandExecutor executor) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		command = createCommand(sessionId,GET_SCREEN_ORIENTATION_COMMAND, parameters);
		this.executor = executor;
	}
	
	public <T extends String> String execute() {
		try {
			Response response = executor.execute(command);
			if (response.getStatus() == 0) {
				LOGGER.info("Orientatuion:" + response.getValue().toString());
			} else {
				LOGGER.warn(response.getValue().toString());
			}
			return response.getValue().toString();
		} catch (IOException e) {
			LOGGER.error("Unable to get orientation  : " + e.getMessage());
			throw new RuntimeException();
		}

	}
	
	private Command createCommand(SessionId sessionId, String orientationCommand,
			HashMap<String, String> parameters) {
		command = new Command(sessionId,
				orientationCommand, parameters);
		return command;
	}
}
