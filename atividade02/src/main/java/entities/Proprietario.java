package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Proprietario
 *
 */
@Entity

public class Proprietario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int id;
	@Column
	private String nome;

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Proprietario [id = " + id + ", nome = " + nome + "]";
	}

}
