package br.com.ae.agenda.service;

import java.util.List;

import br.com.ae.agenda.dao.CategoriaDAO;
import br.com.ae.agenda.dao.TarefaDAO;
import br.com.ae.agenda.entity.Categoria;
import br.com.ae.agenda.entity.Tarefa;


public class AgendaService {

	private static int tarefaId;

	public void saveOrUpdateTarefa(Tarefa tarefa) {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = categoriaDAO.load(tarefa.getCategoria().getId());
		tarefa.setCategoria(categoria);

		TarefaDAO tarefaDAO = new TarefaDAO();

		if (tarefa.getId() == null) {
			tarefa.setId(++tarefaId);
		}

		tarefaDAO.save(tarefa);
	}

	public List<Categoria> getCategorias() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		return categoriaDAO.getCategorias();
	}

	public List<Tarefa> getTarefas(int start, int count) {
		TarefaDAO tarefaDAO = new TarefaDAO();
		return tarefaDAO.getTarefas(start, count);
	}

	public List<Tarefa> getTarefas() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		return tarefaDAO.getTarefas(0, countTarefas());
	}

	public Tarefa loadTarefa(Integer id) {
		TarefaDAO tarefaDAO = new TarefaDAO();
		return tarefaDAO.load(id);
	}

	public void deleteTarefa(Tarefa tarefa) {
		TarefaDAO tarefaDAO = new TarefaDAO();
		tarefaDAO.delete(tarefa);
	}

	public int countTarefas() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		return tarefaDAO.count();
	}
}
