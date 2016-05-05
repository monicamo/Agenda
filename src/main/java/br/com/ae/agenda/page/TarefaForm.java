package br.com.ae.agenda.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.DateField;
import org.apache.click.util.Bindable;

import br.com.ae.agenda.entity.Categoria;
import br.com.ae.agenda.entity.Tarefa;
import br.com.ae.agenda.service.AgendaService;

public class TarefaForm extends BorderPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Bindable
	Integer id;

	private AgendaService service = new AgendaService();
	private Form form = new Form("form");

	public void onInit() {

		DateField data = new DateField("data", "Data/Hora");
		data.setFormatPattern("dd/MM/yyyy HH:mm");
		data.setStyle("silver");
		data.setShowTime(true);
		form.add(data);

		Select categoria = new Select("categoria.id", "Categoria:");
		categoria.setDefaultOption(Option.EMPTY_OPTION);

		categoria.setDataProvider(new DataProvider<Option>() {
			public Iterable<Option> getData() {
				List<Categoria> categorias = service.getCategorias();

				List<Option> options = new ArrayList<Option>();
				for (Categoria categoria : categorias) {
					options.add(new Option(categoria.getId(), categoria
							.getNome()));
				}
				return options;
			}
		});
		form.add(categoria);

		TextArea descricao = new TextArea("descricao", "Descrição");
		descricao.setCols(40);
		descricao.setRows(5);
		form.add(descricao);
		
		HiddenField tarefaId = new HiddenField("id", Integer.class);
		form.add(tarefaId);

		Submit submit = new Submit("submit", "Cadastrar", this, "gravar");
		form.add(submit);

		form.setButtonAlign("right");
		addControl(form);
	}

	public boolean gravar() {
		if (form.isValid()) {
			Tarefa tarefa = new Tarefa();
			form.copyTo(tarefa);
			service.saveOrUpdateTarefa(tarefa);
			setRedirect(ListaTarefas.class);
		}
		return true;
	}
	
	@Override
	public void onRender() {
		if (id != null) {
			Tarefa tarefa = service.loadTarefa(id);
			form.copyFrom(tarefa);
		}
	}
}
