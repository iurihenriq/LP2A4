package lp2a4.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lp2a4.Logger;

public class AlunoCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = new Logger(AlunoCRUDServlet.class);

	public AlunoCRUDServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		final String acaoParam = request.getParameter("acao");

		try {
			final CommandEnum acao = CommandEnum
					.valueOf(acaoParam != null ? acaoParam : CommandEnum.DESCONHECIDO.toString());
			final Command comando = Command.commandFactory(acao);
			comando.execute(request, response);
		} catch (Exception e) {
			log.info("Não conseguiu carregar o comando referente ao parametro acao=[" + acaoParam + "]");
		}
	}

}