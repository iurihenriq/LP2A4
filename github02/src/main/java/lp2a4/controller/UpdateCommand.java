package lp2a4.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lp2a4.modelo.AlunoDAO;
import lp2a4.modelo.AlunoPOJO;

public class UpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final AlunoPOJO aluno = ControllerUtil.transform(request);

		final AlunoDAO dao = AlunoDAO.getInstance();

		if (dao.update(aluno)) {
			response.getWriter().append("Aluno foi atualizado com sucesso!!!");
		} else {
			response.getWriter().append("Atualização não funcionou!!!");
		}
	}

}
