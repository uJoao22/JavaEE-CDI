package br.com.alura.alura_lib.factory;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Definindo que essa class tem um escopo de aplicação, que ela tem uma instancia para minha class inteira
@ApplicationScoped
public class JPAFactory {

	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("livraria");

	//Dizendo para o CDI que este método é um produtor de EntityManager
	@Produces
	//Dizendo para o CDI que essa produção irá durar apenas dentro de um escopo que o CDI entenda, no caso durante uma requisição
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	//Colocando o @Disposes para ensinar o CDI que toda vez que precisar fechar o objeto, ele irá passar primeiro por aqui, 
	//antes de liberar
	public void close(@Disposes EntityManager em) {
		if(em.isOpen())
			em.close();
	}
	
	//Antes que o CDI destrua toda essa class o método abaixo será executado
	//Funciona como uma função callback do intercptor, assim que ele terminar de ser executado, este método será chamado
	@PreDestroy
	public void preDestroy() {
		if(emf.isOpen())
			emf.close();
	}
	
}