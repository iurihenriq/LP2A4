package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Casa
 *
 */
@Entity

public class Casa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int id;
	@Column
	private String tamanho;
	@ManyToOne
	@JoinColumn(name = "idProprietario")
	private Proprietario proprietario;

	public int getId() {
		return id;
	}

	public String getTamanho() {
		return tamanho;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		return "Casa [id = " + id + ", tamanho = " + tamanho + ", proprietario = " + proprietario + "]";
	}

}
