package com.orientation;

import java.io.IOException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;


public class SetOrientationCommand {
	
	private static final Logger LOGGER = Logger.getLogger(SetOrientationCommand.class);

	private Command command;
	
	private HttpCommandExecutor executor = null;

	private final String SET_SCREEN_ORIENTATION_COMMAND = "setScreenOrientation";

	public SetOrientationCommand(SessionId sessionId,Orientation orientation, HttpCommandExecutor executor) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("orientation", orientation.toString());
		command = createCommand(sessionId,SET_SCREEN_ORIENTATION_COMMAND, parameters);
		this.executor = executor;
	}

	
	public <T extends Boolean> Boolean execute() {
		try {
			Response response = executor.execute(command);
			if (response.getStatus() == 0) {
				LOGGER.info(response.getValue().toString());
				return true;
			}
		} catch (IOException e) {
			LOGGER.error("Unable to change orientation: "  + e.getMessage());
		}
		return false;
		
	}
	
	private Command createCommand(SessionId sessionId, String orientationCommand,
			HashMap<String, String> parameters) {
		command = new Command(sessionId,
				orientationCommand, parameters);
		return command;
	}

}
