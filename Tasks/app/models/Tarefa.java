package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * Classe responsável por guardar as informações de cada tarefa
 * @author Abner M. C. Araujo
 */
@Entity
public class Tarefa implements Comparable<Tarefa> {
	
	private Prioridade prioridade;
	private String nome, descricao;
	private boolean concluido;
	private int semana;
	@Id @GeneratedValue
	private int id;
	
	public Tarefa() { }
	
	/**
	 * Construtor da classe
	 * @param nome
	 * 		Nome da tarefa
	 * @param descricao
	 * 		Descrição da tarefa
	 * @param semana
	 * 		Semana que se deseja concluir a tarefa
	 * @param prior
	 * 		Prioridade da tarefa
	 * @throws TarefaException
	 * 		Lança exceção caso o nome seja vazio ou semana superior a 6
	 */
	public Tarefa(String nome, String descricao, int semana, Prioridade prior) 
		throws TarefaException {
		setSemana(semana);
		setNome(nome);
		this.descricao = descricao;
		this.prioridade = prior;
		this.setConcluido(false);
	}

	public Prioridade getPrioridade() { 
		return prioridade;
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() { 
		return descricao;
	}
	
	public int getSemana() { 
		return semana;
	}
	
	/**
	 * Informa se a tarefa foi concluida
	 * @return
	 * 		Retorna o status da tarefa
	 */
	public boolean isConcluido() { 
		return concluido;
	}
	
	public void setPrioridade(Prioridade prior) {
		this.prioridade = prior;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Define um novo nome para a tarefa
	 * @param nome
	 * 		Nome da tarefa
	 * @throws TarefaException
	 * 		Lança exceção caso o nome seja vazio
	 */
	public void setNome(String nome) throws TarefaException {
		if (nome == null || nome.trim().equals("")) {
			throw new TarefaException("Nome da tarefa inválido");
		}
		this.nome = nome;
	}

	/**
	 * Define uma nova semana para a tarefa
	 * @param semana
	 * 		Semana da tarefa
	 * @throws TarefaException
	 * 		Lança exceção caso a semana seja superior a 6
	 */
	public void setSemana(int semana) throws TarefaException {
		if (semana < 0 || semana > 6) { 
			throw new TarefaException("Semana inválida");
		}
		this.semana = semana;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	
	/**
	 * Conclui uma tarefa
	 * @throws TarefaException
	 * 		Lança exceção caso a tarefa já tenha sido concluída
	 */
	protected void concluiTarefa() throws TarefaException {
		if (isConcluido())
			throw new TarefaException("Tarefa já concluída");
		setConcluido(true);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.toLowerCase().hashCode());
		result = prime * result + semana;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.toLowerCase().equals(other.nome.toLowerCase()))
			return false;
		if (semana != other.semana)
			return false;
		return true;
	}

	@Override
	public int compareTo(Tarefa o) {
		if (this.semana != o.semana)
			return this.semana - o.semana;
		if (this.isConcluido() != o.isConcluido())
			return o.isConcluido() ? 1 : -1;
		if (this.prioridade.ordinal() != o.prioridade.ordinal())
			return o.prioridade.ordinal() - this.prioridade.ordinal();
		return this.nome.compareTo(o.nome);
	}

	@Override
	public String toString() {
		return "Tarefa [nome=" + nome + ", concluido=" + isConcluido()
				+ ", semana=" + semana + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
