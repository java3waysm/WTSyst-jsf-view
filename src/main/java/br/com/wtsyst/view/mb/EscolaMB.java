package br.com.wtsyst.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.wtsyst.bean.Escola;
import br.com.wtsyst.controle.EscolaBCI;

@ManagedBean(name ="EscolaMB")
@ViewScoped
public class EscolaMB extends SpringBeanAutowiringSupport {
	
	private Escola bean;
	private List<Escola> list;
	
	@Autowired
	private EscolaBCI controle;
	
	@PostConstruct
	public void init() {
		
		this.bean =new Escola();
		this.list = controle.select();
	}
	
	public Escola getBean() {
		
		return bean;
	}
	
	public void setBean(Escola bean) {
		
	this.bean=bean;
	
	}
	
	public List<Escola> getList(){
		return list ;
	}

	public void setList(List<Escola>list) {
		this.list = list;

	}
	
	public void insert() {
		
		this.controle.insert(this.bean);
		this.init();
	}
	
	
	
	
	
	
	
	
	
	
}






