package br.com.alura.alura_lib.tx;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.alura.alura_lib.tx.annotation.Transacional;

//A anotação abaixo faz com que essa classe intercepte a chamada de um metodo
@Interceptor
//A anotação Transacional criada por mim, associa esse intercptador à anotação citada 
@Transacional
//Adicionando o Intercptor - MANEIRA 2, definindo o nivel de prioridade dele através de ENUM's
@Priority(Interceptor.Priority.APPLICATION)
public class GerenciadorDeTransacao implements Serializable{

	private static final long serialVersionUID = -1392064408660679407L;
	
	private EntityManager em;
	
	@Inject
	public GerenciadorDeTransacao(EntityManager em) {
		this.em = em;
	}

	//Dizendo que ao redor de uma invocção de método este método será invocado
	@AroundInvoke
	public Object executaComTransacao(InvocationContext context) {
		em.getTransaction().begin();
		
		try {
			//Este método pega o método que esta sendo intercptado e executa ele aqui, passando o resultado dele para um objeto
			Object resultado = context.proceed();
			em.getTransaction().commit();
			return resultado;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
}