package lp2a4.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lp2a4.AlunoUtil;

public class AlunoDAOJDBC implements AlunoDAO {

	public static Connection connectDB() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/AlunoIuri", "root", "123456");
			return conexao;
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar com o banco de dados: " + e);
			return null;
		}

	}

	@Override
	public boolean create(AlunoPOJO aluno) {
		String instrucaoSQL = "INSERT INTO Aluno (matricula, nome, endereco, data_inicio, data_termino) VALUES (?,?,?,?,?)";

		PreparedStatement statement;

		try {
			statement = AlunoDAOJDBC.connectDB().prepareStatement(instrucaoSQL);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getEndereco());
			statement.setString(4,
					aluno.getDataIngresso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.setString(5,
					aluno.getDataConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.execute();
			statement.close();
			AlunoDAOJDBC.connectDB().close();
			return true;
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a inserção: " + e);
			return false;
		}
	}

	@Override
	public AlunoPOJO retrieve(String matricula) {
		try {
			AlunoPOJO aluno = new AlunoPOJO();
			PreparedStatement statement = connectDB().prepareStatement("SELECT * FROM Aluno WHERE matricula = ?;");
			statement.setString(1, matricula);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String dtIngresso = result.getString("data_inicio");
				String dtConclusao = result.getString("data_termino");
				aluno.setMatricula(matricula);
				aluno.setNome(nome);
				aluno.setEndereco(endereco);
				aluno.setDataIngresso(LocalDate.parse(dtIngresso, AlunoUtil.DATE_FORMAT));
				aluno.setDataConclusao(LocalDate.parse(dtConclusao, AlunoUtil.DATE_FORMAT));
			}
			statement.close();
			AlunoDAOJDBC.connectDB().close();
			return aluno;
		} catch (Exception e) {
			System.out.println("Não foi possível consultar os dados." + e);
			return null;
		}

	}

	@Override
	public boolean update(AlunoPOJO aluno) {
		String instrucaoSQL = "UPDATE Aluno SET matricula = ?, nome = ?, endereco = ?, data_inicio = ?, data_termino = ? WHERE matricula = ?;";

		PreparedStatement statement;

		try {
			statement = AlunoDAOJDBC.connectDB().prepareStatement(instrucaoSQL);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getEndereco());
			statement.setString(4,
					aluno.getDataIngresso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.setString(5,
					aluno.getDataConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.setString(6, aluno.getMatricula());
			statement.execute();
			statement.close();
			AlunoDAOJDBC.connectDB().close();
			return true;
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a atualização: " + e);
			return false;
		}
	}

	@Override
	public boolean delete(String matricula) {
		try {
			PreparedStatement statement = AlunoDAOJDBC.connectDB()
					.prepareStatement("DELETE FROM Aluno WHERE matricula = ?;");
			statement.setString(1, matricula);
			statement.execute();
			statement.close();
			AlunoDAOJDBC.connectDB().close();
			return true;
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a deleção: " + e);
			return false;
		}

	}
}