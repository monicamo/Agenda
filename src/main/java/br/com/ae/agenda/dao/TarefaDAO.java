package br.com.ae.agenda.dao;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.ae.agenda.entity.Categoria;
import br.com.ae.agenda.entity.Tarefa;

public class TarefaDAO {
	  
	  private static Map<Integer, Tarefa> tarefasMap = new LinkedHashMap<Integer, Tarefa>();
	  
	  static {
	    CategoriaDAO categoriaDAO = new CategoriaDAO();
	    Categoria categoria1 = categoriaDAO.load(1);
	    Categoria categoria2 = categoriaDAO.load(2);
	    
	    for (int i = 1; i <= 20; i++) {
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DAY_OF_MONTH, i);
	      c.set(2010, 10, 11 + i, 15, 00);
	      
	      Tarefa tarefa = null;
	      tarefa = new Tarefa();
	      tarefa.setId(i);
	      tarefa.setData(c.getTime());
	      tarefa.setDescricao("Teste " + i);
	      
	      if (i % 2 == 0) {
	        tarefa.setCategoria(categoria1);
	      } else {
	        tarefa.setCategoria(categoria2);
	      }
	      tarefasMap.put(tarefa.getId(), tarefa);
	    }
	  }

	  public void save(Tarefa tarefa) {
	    tarefasMap.put(tarefa.getId(), tarefa);
	  }
	  
	  public void delete(Tarefa tarefa) {
	    tarefasMap.remove(tarefa.getId());
	  }
	  
	  public List<Tarefa> getTarefas(int start, int count) {
	    
	    List<Tarefa> tarefas = new ArrayList<Tarefa>(tarefasMap.values());
	    List<Tarefa> algumasTarefas = new ArrayList<Tarefa>();
	    
	    for (int i = start; i < count; i++) {
	      algumasTarefas.add(tarefas.get(i));
	    }
	    
	    return algumasTarefas;
	  }
	  
	  public Tarefa load(Integer id) {
	    return tarefasMap.get(id);
	  }
	  
	  public int count() {
	    return tarefasMap.size();
	  }
}
