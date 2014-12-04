package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TarefaFeitaTest {

	private Usuario usr;
	
	@Before
	public void setUp() { 
		usr = new Usuario(); 
	}
	
	@Test
	public void criaTarefa() throws Exception {
		Tarefa tar = new Tarefa("Exemplo de uma tarefa", "Descrição", 0, Prioridade.MEDIA);
		//Testa se o contador está alterando
		usr.addTarefa(tar);
		Assert.assertEquals(1, usr.countTarefas());
		//Verifica se não está permitindo inserir repetidos
		usr.addTarefa(tar);
		Assert.assertEquals(1, usr.countTarefas());
		//Verifica se está retornando a lista corretamente
		Assert.assertArrayEquals(new Tarefa[]{tar}, usr.getTarefas());
		//Verifica as condições de exceção
		try {
			new Tarefa("Exemplo de erro", "Tarefa definida acima da sexta semana", 7, Prioridade.MEDIA);
			Assert.fail(); 
		} catch(TarefaException e) { }
		try {
			new Tarefa(" ", "Outro exemplo de erro", 0, Prioridade.MEDIA);
			Assert.fail();
		} catch(TarefaException e) { }
	}

	@Test
	public void removeTarefa() throws Exception {
		Tarefa tar = new Tarefa("Exemplo de uma tarefa", "Descrição", 0, Prioridade.MEDIA);
		//Verifica se o contador altera e volta ao original
		usr.addTarefa(tar);
		usr.removeTarefa(tar);
		Assert.assertEquals(0, usr.countTarefas());
		//Verifica se está retornando a lista corretamente
		Assert.assertArrayEquals(new Tarefa[]{}, usr.getTarefas());
		//Verifica se é lançado exceção caso uma tarefa seja removida duas vezes
		try {
			usr.removeTarefa(tar);
			Assert.fail();
		} catch(TarefaException e) { }
	}
	
	@Test
	public void concluiTarefa() throws Exception { 
		Tarefa tar = new Tarefa("Exemplo de uma tarefa", "Descrição", 0, Prioridade.MEDIA);
		//Verifica se tarefas recem-adicionadas não são concluídas
		usr.addTarefa(tar);
		Assert.assertEquals(0, usr.countTarefasConcluidas());
		//Verifica se o contador de tarefas concluidas se altera
		usr.concluiTarefa(tar);
		Assert.assertEquals(1, usr.countTarefasConcluidas());
		//Verifica se há exceção caso a tarefa seja concluida duas vezes
		try {
			usr.concluiTarefa(tar);
			Assert.fail();
		} catch(TarefaException e) { }
		usr.removeTarefa(tar);
		Assert.assertEquals(0, usr.countTarefasConcluidas());
	}
	
	@Test
	public void ordenaTarefas() throws Exception { 
		//A lista é ordenada por semana, prioridade e ordem alfabética,
		//nesta precedência
		Tarefa tar0 = new Tarefa("Tarefa A", null, 0, Prioridade.BAIXA);
		Tarefa tar1 = new Tarefa("Tarefa B", null, 0, Prioridade.BAIXA);
		Tarefa tar2 = new Tarefa("Tarefa C", null, 0, Prioridade.ALTA);
		Tarefa tar3 = new Tarefa("Tarefa D", null, 1, Prioridade.ALTA);
		Tarefa tar4 = new Tarefa("Tarefa A", null, 1, Prioridade.BAIXA);
		Tarefa tar5 = new Tarefa("Tarefa B", null, 1, Prioridade.MEDIA);
		//Testa se a precedência está sendo calculada corretamente
		Assert.assertTrue(tar2.compareTo(tar0) < 0);
		Assert.assertTrue(tar0.compareTo(tar1) < 0);
		Assert.assertTrue(tar2.compareTo(tar3) < 0);
		Assert.assertTrue(tar3.compareTo(tar4) < 0);
		usr.addTarefa(tar0);
		usr.addTarefa(tar1);
		usr.addTarefa(tar2);
		usr.addTarefa(tar3);
		usr.addTarefa(tar4);
		usr.addTarefa(tar5);
		//Verifica se tarefas concluidas aparecem primeiro do que os em andamento
		usr.concluiTarefa(tar0);
		Assert.assertFalse(tar2.compareTo(tar0) < 0);
		//Verifica a ordem dos itens da lista sendo retornada corretamente
		Assert.assertArrayEquals(new Tarefa[]{tar0, tar2, tar1, tar3, tar5, tar4}, 
				usr.getTarefas());
	}
	
	@Test
	public void ordenaTarefasPorSemana() throws Exception {
		Tarefa tar0 = new Tarefa("Tarefa A", null, 0, Prioridade.BAIXA);
		Tarefa tar1 = new Tarefa("Tarefa B", null, 0, Prioridade.BAIXA);
		Tarefa tar2 = new Tarefa("Tarefa C", null, 0, Prioridade.ALTA);
		Tarefa tar3 = new Tarefa("Tarefa D", null, 1, Prioridade.ALTA);
		Tarefa tar4 = new Tarefa("Tarefa A", null, 1, Prioridade.BAIXA);
		usr.addTarefa(tar0);
		usr.addTarefa(tar1);
		usr.addTarefa(tar2);
		usr.addTarefa(tar3);
		usr.addTarefa(tar4);
		Assert.assertArrayEquals(new Tarefa[]{tar2, tar0, tar1}, usr.getTarefas(0));
		Assert.assertEquals(3, usr.countTarefas(0));
		Assert.assertArrayEquals(new Tarefa[]{tar3, tar4}, usr.getTarefas(1));
		Assert.assertEquals(2, usr.countTarefas(1));
		usr.concluiTarefa(tar4);
		Assert.assertArrayEquals(new Tarefa[]{tar4, tar3}, usr.getTarefas(1));
		Assert.assertEquals(2, usr.countTarefas(1));
		Assert.assertEquals(1, usr.countTarefasConcluidas(1));
		Assert.assertArrayEquals(new Tarefa[]{}, usr.getTarefas(3));
		Assert.assertEquals(0, usr.countTarefas(3));
	}
	
}
