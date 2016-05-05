package br.com.ae.agenda.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ae.agenda.entity.Categoria;

public class CategoriaDAO {

	  private static Map<Integer, Categoria> categoriasMap = new HashMap<Integer, Categoria>();
	  
	  static {
	    Categoria c = null;
	    
	    c = new Categoria();
	    c.setId(1);
	    c.setNome("Pessoal");
	    categoriasMap.put(c.getId(), c);
	    
	    c = new Categoria();
	    c.setId(2);
	    c.setNome("Profissional");
	    categoriasMap.put(c.getId(), c);
	  }
	  
	  public List<Categoria> getCategorias() {
	    return new ArrayList<Categoria>(categoriasMap.values());
	  }
	  
	  public Categoria load(Integer id) {
	    return categoriasMap.get(id);
	  }
}
