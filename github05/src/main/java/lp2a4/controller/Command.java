package lp2a4.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	static Command commandFactory(CommandEnum tipoComando) {

		final Command comando;
		switch (tipoComando) {
		case CREATE:
			comando = new CreateCommand();
			break;
		case RETRIEVE:
			comando = new RetrieveCommand();
			break;
		case UPDATE:
			comando = new UpdateCommand();
			break;
		case NAVIGATE_UPDATE:
			comando = new NavigateUpdateCommand();
			break;
		case DELETE:
			comando = new DeleteCommand();
			break;
		default:
			comando = new UnknowedCommand();
			break;
		}

		return comando;
	}
}
