package controllers;

import models.Prioridade;
import models.Tarefa;
import models.TarefaException;
import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static Form<Tarefa> tarefaForm = Form.form(Tarefa.class);
	private static Usuario usr;
	
    public static Result index() {
		
		return redirect(routes.Application.goToSemana(1));  
    } 
    
    public static void criaTarefas() { 
		usr = new Usuario();
		try {
			Tarefa tar0 = new Tarefa("Estudar SI", "Fazer o segundo lab", 0, Prioridade.ALTA);
			Tarefa tar1 = new Tarefa("Estudar OpenCV", "Ler tutoriais e conseguir fazer no mínimo um reconhecedor de gênero", 0, Prioridade.MEDIA);
			Tarefa tar2 = new Tarefa("Ir para o parque do povo", "Festejar com os amigos", 1, Prioridade.BAIXA);
			Tarefa tar3 = new Tarefa("Estudar para lógica", "Prova próxima, fazer a lista  de exercícios", 1, Prioridade.ALTA);
			Tarefa tar4 = new Tarefa("Terminar meu jogo", "Refatorar o código, inserir mais personagens", 2, Prioridade.BAIXA);
			usr.addTarefa(tar0);
			usr.addTarefa(tar1);
			usr.addTarefa(tar2);
			usr.addTarefa(tar3);
			usr.addTarefa(tar4);
		} catch (TarefaException e) {
			e.printStackTrace();
		}
    }
    
    public static Result goToSemana(int semana) {
    	if (usr == null)
    		criaTarefas();
    	return ok(index.render(semana-1, usr, tarefaForm));
    }
    
    public static Result removeTarefa(long id, int semana) {
    	Tarefa tar = usr.procuraTarefa(id);
    	if (tar == null) {
    		return badRequest("Tarefa não encontrada");
    	}
    	try {
    		usr.removeTarefa(tar);
    	} catch(Exception e) { 
    		return internalServerError(e.getMessage());
    	}
    	return redirect(routes.Application.goToSemana(semana));
    }
 
    public static Result concluiTarefa(long hashcode, int semana) {
    	Tarefa tar = usr.procuraTarefa(hashcode);
    	if (tar == null) {
    		return badRequest("Tarefa não encontrada");
    	}
    	try {
    		usr.concluiTarefa(tar);
    	} catch(Exception e) { 
    		return internalServerError(e.getMessage());
    	}
    	return redirect(routes.Application.goToSemana(semana));
    }
    
    public static Result adicionaTarefa() { 
    	Form<Tarefa> form = tarefaForm.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(form.errors().toString());
		} else {
			Tarefa tarefa = form.get();
			usr.addTarefa(tarefa);
			return redirect(routes.Application.goToSemana(tarefa.getSemana()+1));
		}
    }
}
