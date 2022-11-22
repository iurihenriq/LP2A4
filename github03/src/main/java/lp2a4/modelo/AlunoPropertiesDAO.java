package lp2a4.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Properties;

import lp2a4.AlunoUtil;
import lp2a4.Logger;

public class AlunoPropertiesDAO implements AlunoDAO {

	private static final Logger log = new Logger(AlunoDAO.class);

	static final Properties baseAluno = new Properties();

	private static final String CAMINHO_ARQUIVO = "/tmp/baseAlunos.properties";

	static {
		try {
			final FileInputStream file = new FileInputStream(CAMINHO_ARQUIVO);
			baseAluno.load(file);
			log.info("Arquivo de propriedades carregado com sucesso!");
		} catch (Exception e) {
			log.error("Erro ao carregar arquivo de propriedades");
			e.printStackTrace();
		}

	}

	private static synchronized void save() {
		try (final FileOutputStream file = new FileOutputStream(CAMINHO_ARQUIVO)) {
			baseAluno.store(file, null);
			log.info("Arquivo de propriedades salvo com sucesso!");
		} catch (Exception e) {
			log.error("Erro ao gravar arquivo de propriedades");
			e.printStackTrace();
		}
	}

	public boolean existeMatricula(String matricula) {
		return baseAluno.containsKey("matricula." + matricula);
	}

	@Override
	public boolean create(AlunoPOJO aluno) {
		if (existeMatricula(aluno.getMatricula())) {
			log.error("Aluno já existe na base!!!");
			return false;
		}

		baseAluno.put("nome." + aluno.getMatricula(), aluno.getNome());
		baseAluno.put("matricula." + aluno.getMatricula(), aluno.getMatricula());
		baseAluno.put("endereco." + aluno.getMatricula(), aluno.getEndereco());
		baseAluno.put("dataIngresso." + aluno.getMatricula(), aluno.getDataIngresso().format(AlunoUtil.DATE_FORMAT));
		baseAluno.put("dataConclusao." + aluno.getMatricula(), aluno.getDataConclusao().format(AlunoUtil.DATE_FORMAT));
		save();
		return true;

	}

	@Override
	public AlunoPOJO retrieve(String matricula) {
		if (!existeMatricula(matricula)) {
			log.error("Aluno " + matricula + " não encontrado na base.");
			return null;
		}

		final AlunoPOJO aluno = new AlunoPOJO();
		aluno.setNome(baseAluno.getProperty("nome." + matricula));
		aluno.setMatricula(baseAluno.getProperty("matricula." + matricula));
		aluno.setEndereco(baseAluno.getProperty("endereco." + matricula));
		final String dtIngresso = baseAluno.getProperty("dataIngresso." + matricula);
		if (dtIngresso != null) {
			aluno.setDataIngresso(LocalDate.parse(dtIngresso, AlunoUtil.DATE_FORMAT));
		}
		final String dtConclusao = baseAluno.getProperty("dataConclusao." + matricula);
		if (dtConclusao != null) {
			aluno.setDataConclusao(LocalDate.parse(dtConclusao, AlunoUtil.DATE_FORMAT));
		}

		log.info("DAO retornando o aluno " + aluno.getMatricula());
		return aluno;
	}

	@Override
	public boolean update(AlunoPOJO aluno) {
		baseAluno.replace("nome." + aluno.getMatricula(), aluno.getNome());
		baseAluno.replace("matricula." + aluno.getMatricula(), aluno.getMatricula());
		baseAluno.replace("endereco." + aluno.getMatricula(), aluno.getEndereco());
		baseAluno.replace("dataIngresso." + aluno.getMatricula(),
				aluno.getDataIngresso().format(AlunoUtil.DATE_FORMAT));
		baseAluno.replace("dataConclusao." + aluno.getMatricula(),
				aluno.getDataConclusao().format(AlunoUtil.DATE_FORMAT));
		save();
		return true;
	}

	@Override
	public boolean delete(String matricula) {
		baseAluno.remove("nome." + matricula);
		baseAluno.remove("matricula." + matricula);
		baseAluno.remove("endereco." + matricula);
		baseAluno.remove("dataIngresso." + matricula);
		baseAluno.remove("dataConclusao." + matricula);
		save();
		return true;
	}

}
