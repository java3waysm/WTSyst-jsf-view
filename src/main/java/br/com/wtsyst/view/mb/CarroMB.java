package br.com.wtsyst.view.mb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.wtsyst.bean.Carro;
import br.com.wtsyst.bean.Pessoa;
import br.com.wtsyst.controle.CarroBCI;

@ManagedBean(name = "CarroMB")
public class CarroMB  extends SpringBeanAutowiringSupport {
	
	private Carro bean;
	@Autowired
	private CarroBCI controle;

	@PostConstruct
	public void init() {
		this.list = controle.select();
		this.bean = new Carro();
		Calendar c = new GregorianCalendar();

		this.bean.setAno(c);
	}
	
	private List<Carro> list;

	public Carro getBean() {
		return bean;
	}
	public void setBean(Carro bean) {
		this.bean = bean;
	}
	public List<Carro> getList() {
		return list;
	}
	public void setList(List<Carro> list) {
		this.list = list;
	}
	
	public void cadastrar() {
		controle.insert(this.bean);
		this.init();
	}
	
	
	
	public void povoarTabela() {
		Map<Integer, String> cores =  new LinkedHashMap<Integer, String>();
		cores.put(0, "Azul");
		cores.put(1, "Preto");
		cores.put(2, "Prata");
		cores.put(3, "Branco");
		cores.put(4, "Vermelho");
		cores.put(5, "Amarelo");
		cores.put(6, "Rosa");
		
		
		
		Map<Integer, Calendar> anos =  new LinkedHashMap<Integer, Calendar>();
		
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, 2004);
		anos.put(0, c);
		
		Calendar c1 = new GregorianCalendar();
		c1.set(Calendar.YEAR, 2009);
		anos.put(1, c1);
		
		Calendar c2 = new GregorianCalendar();
		c2.set(Calendar.YEAR, 2016);
		anos.put(2, c2);
	
		Calendar c3 = new GregorianCalendar();
		c3.set(Calendar.YEAR, 2018);
		anos.put(3, c3);
		
		Calendar c5 = new GregorianCalendar();
		c5.set(Calendar.YEAR, 2018);
		anos.put(4, c5);
		
		Calendar c6 = new GregorianCalendar();
		c6.set(Calendar.YEAR, 1985);
		anos.put(5, c6);
		
		
		Calendar c7 = new GregorianCalendar();
		c7.set(Calendar.YEAR, 2019);
		anos.put(6, c7);
		
		
		Map<Integer, String> nomes =  new LinkedHashMap<Integer, String>();

		nomes.put(0, "Gol");
		nomes.put(1, "Pálio");
		nomes.put(2, "Fusca");
		nomes.put(3, "Fusion");
		nomes.put(4, "Cruiz");
		nomes.put(5, "BMW");
		nomes.put(6, "Ferrari");
		
		
		

		Map<Integer, String> pessoasNomes =  new LinkedHashMap<Integer, String>();

		pessoasNomes.put(0, "Maria");
		pessoasNomes.put(1, "José");
		pessoasNomes.put(2, "João");
		pessoasNomes.put(3, "Thiago");
		pessoasNomes.put(4, "Suzana");
		pessoasNomes.put(5, "Andréia");
		pessoasNomes.put(6, "Gustavo");
	
		Random r = new Random();
		

		for (int i = 0; i < 100; i++) {
			Carro novoCarro = new Carro();
			novoCarro.setAno(anos.get(r.nextInt(6)));
			novoCarro.setCor(cores.get(r.nextInt(6)));
			novoCarro.setNome(nomes.get(r.nextInt(6)));
			
			Pessoa p = new Pessoa();
			p.setNome(pessoasNomes.get(r.nextInt(6)));
			
			novoCarro.setPessoa(p);
			controle.insert(novoCarro);
		}
		
		this.init();
		
	}
	
}
