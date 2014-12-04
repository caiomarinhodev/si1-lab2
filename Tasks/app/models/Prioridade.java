package models;


public enum Prioridade {
	BAIXA(0, "Baixa"), MEDIA(1, "Média"), ALTA(2, "Alta");

	private final Integer precedencia;
	private final String descricao;

	private Prioridade(Integer precedencia, String descricao) {
		this.precedencia = precedencia;
		this.descricao = descricao;
	}

	public Integer getPrecedencia() {
		return precedencia;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
}