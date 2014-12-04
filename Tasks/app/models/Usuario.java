package models;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe responsável por gerenciar as tarefas do usuario
 * @author Abner M. C. Araujo
 */
public class Usuario {

	private Set<Tarefa> tarefas;

	public Usuario() { 
		tarefas = new TreeSet<>();
	}
	
	/**
	 * Adiciona uma tarefa em andamento
	 * @param tar
	 * 		Tarefa a ser adicionada
	 */
	public void addTarefa(Tarefa tar) {
		tarefas.add(tar);
	}

	/**
	 * Remove uma tarefa do sistema
	 * @param tar
	 * 		Tarefa a ser removida
	 * @throws TarefaNaoEncontradaException
	 * 		Lança exceção caso a tarefa não seja encontrada
	 */
	public void removeTarefa(Tarefa tar) throws TarefaException { 
		if (!tarefas.contains(tar))
			throw new TarefaException("Tarefa não encontrada");
		tarefas.remove(tar);
	}
	
	/**
	 * Concluí uma tarefa
	 * @param tar
	 * 		Tarefa a ser concluída
	 * @throws TarefaException
	 * 		Lança exceção caso a tarefa não seja encontrada ou já tenha sido concluída
	 */
	public void concluiTarefa(Tarefa tar) throws TarefaException {
		if (!tarefas.contains(tar))
			throw new TarefaException("Tarefa não encontrada");
		if (!tar.isConcluido())
			tarefas.remove(tar);
		tar.concluiTarefa();
		//Reinsere o elemento com a precedencia correta
		tarefas.add(tar);
	}
	
	/**
	 * Retorna todas as tarefas
	 * @return
	 * 		Todas as tarefas cadastradas até o momento
	 */
	public Tarefa[] getTarefas() {
		Tarefa[] tars = new Tarefa[tarefas.size()];
		tarefas.toArray(tars);
		return tars;
	}

	private Tarefa[] toTarefas(Object[] o) {
		Tarefa[] tars = new Tarefa[o.length];
		for (int i = 0; i < o.length; i++) { 
			tars[i] = (Tarefa)o[i];
		}
		return tars;
	}
	
	/**
	 * Retorna as  tarefas por semana
	 * @param semana
	 * 		Semana das tarefas a serem retornadas
	 * @return
	 * 		Todas as tarefas daquela semana
	 */
	public Tarefa[] getTarefas(int semana) { 
		ArrayList<Tarefa> tarefasEncontradas = new ArrayList<>();
		for (Tarefa tar : tarefas) {
			if (tar.getSemana() == semana)
				tarefasEncontradas.add(tar);
		}
		return toTarefas(tarefasEncontradas.toArray());
	}
	
	/**
	 * Informa a quantidade de tarefas cadastradas
	 * @return
	 * 		A quantidade de tarefas cadastradas
	 */
	public int countTarefas() {
		return tarefas.size();
	}
	
	/**
	 * Informa a quantidade de tarefas cadastradas numa semana
	 * @param semana
	 * 		Semana a qual se quer inferir a quantidade de tarefas
	 * @return
	 * 		A quantidade de tarefas naquela semana
	 */
	public int countTarefas(int semana) {
		return getTarefas(semana).length;
	}

	/**
	 * Informa a quantidade de tarefas concluidas
	 * @return
	 * 		A quantidade de tarefas concluidas
	 */
	public int countTarefasConcluidas() { 
		int count = 0;
		for (Tarefa tar : tarefas) {
			if (tar.isConcluido())
				count++;
		}
		return count;
	}
	
	/**
	 * Informa a quantidade de tarefas concluidas numa semana
	 * @param semana
	 * 		Semana a qual se quer inferir a quantidade de tarefas concluidas
	 * @return
	 * 		A quantidade de tarefas concluidas naquela semana
	 */
	public int countTarefasConcluidas(int semana) {
		Tarefa[] tars = getTarefas(semana);
		int count = 0;
		for (Tarefa tar : tars) {
			if (tar.isConcluido())
				count++;
		}
		return count;
	}
	
	/**
	 * Procura por uma tarefa a partir de seu hashcode
	 * @param hashcode
	 * 		Hashcode da tarefa
	 * @return
	 * 		Tarefa associada ao hashcode informado
	 */
	public Tarefa procuraTarefa(long hashcode) {
		for (Tarefa tar : tarefas) {
			if (tar.hashCode() == hashcode)
				return tar;
		}
		return null;
	}
	
}
