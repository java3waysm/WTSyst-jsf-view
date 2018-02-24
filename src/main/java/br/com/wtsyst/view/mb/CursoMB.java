package br.com.wtsyst.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.wtsyst.bean.Curso;
import br.com.wtsyst.controle.CursoBCI;


@ManagedBean(name = "CursoMB")
@ViewScoped
public class CursoMB extends SpringBeanAutowiringSupport{
	
	private Curso bean;
	private List<Curso> list;
	@Autowired
	private CursoBCI controle;
	
	@PostConstruct
	public void init() {
		
		this.bean = new Curso();
		this.list = controle.select();
	}
	
	//------------------------------

	public Curso getBean() {
		return bean;
	}

	public void setBean(Curso bean) {
		this.bean = bean;
	}

	public List<Curso> getList() {
		return list;
	}

	public void setList(List<Curso> list) {
		this.list = list;
	}

	public CursoBCI getControle() {
		return controle;
	}

	public void setControle(CursoBCI controle) {
		this.controle = controle;
	}

	public void insert() {
		this.controle.insert(this.bean);
		this.init();
	}
	
	
	
}
