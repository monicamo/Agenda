package br.com.ae.agenda.page;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.AbstractLink;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.PageLink;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import br.com.ae.agenda.entity.Tarefa;
import br.com.ae.agenda.service.AgendaService;

public class ListaTarefas extends BorderPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Servico
	private AgendaService service = new AgendaService();
	
	// controle
	@Bindable
	protected Table tarefas;
	
	@Bindable
	protected PageLink createLink = new PageLink("create", "Cadastrar Nova Tarefa", TarefaForm.class);
	
	// Chama um metodo
	@Bindable
	protected PageLink editLink = new PageLink("edit", "Editar", TarefaForm.class);

	@Bindable
	private ActionLink deleteLink = new ActionLink("delete", "Excluir", this, "excluir");

	public ListaTarefas() {
		setStateful(true);
		
		Table tarefas =  new Table("tarefas");
		
		tarefas.setClass(Table.CLASS_ORANGE1);
		tarefas.setSortedColumn("data");
		tarefas.setPageSize(7);
		
		Column data = new Column("data", "Data/Hora");
		data.setSortable(true);
		tarefas.addColumn(data);
		
		Column categoria = new Column("categoria.id", "Categoria");
		categoria.setSortable(true);
		tarefas.addColumn(categoria);
		
		Column action = new Column("action", "Ações");
		tarefas.addColumn(action);
		
		tarefas.setDataProvider(new DataProvider<Tarefa>() {
			public List<Tarefa> getData() {
				List<Tarefa> tarefas = service.getTarefas();
				return tarefas;
			}
		});
		
	}
	
	// Depois do construtor, este é o metodo chamando
	public void onInit() 
	{
		setStateful(true);
		
		tarefas = new Table("table");
		tarefas.setClass(Table.CLASS_ORANGE1);
		tarefas.setSortedColumn("data");
		tarefas.setPageSize(7);
		
		// cria colunas
		Column data = new Column("data", "Data/Hora");
		data.setSortable(true);
		// Decorator para formatar a celula
		data.setDecorator(new Decorator() 
		{
			public String render(Object object, Context context) 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Tarefa tarefa = (Tarefa) object;
				return sdf.format(tarefa.getData());
			} 
		});
		tarefas.addColumn(data);
		
		//tarefas.addColumn(new Column("categoria.nome", "Categoria"));
		Column categoria = new Column("categoria.nome", "Categoria");
		categoria.setSortable(true);
		tarefas.addColumn(categoria);
		
		Column acoes = new Column("acoes", "Ações");
		
		acoes.setDecorator(new LinkDecorator(tarefas, 
				new AbstractLink[] { editLink,  deleteLink }, "id" ));

		tarefas.addColumn(acoes);
		
		tarefas.setDataProvider( new DataProvider<Tarefa>() 
		{
			public List<Tarefa> getData() {
				List<Tarefa> tarefas = service.getTarefas();
				return tarefas;
			}
		});
	}
	
	public boolean excluir() {
		Integer id = deleteLink.getValueInteger();
		Tarefa tarefa = new Tarefa();
		tarefa.setId(id);
		service.deleteTarefa(tarefa);
		setRedirect(ListaTarefas.class);
		return true;
	}
	
	
}
