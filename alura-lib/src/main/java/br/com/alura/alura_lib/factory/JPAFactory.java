package br.com.alura.alura_lib.factory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {

	private static EntityManagerFactory emf = Persistence
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
		em.close();
	}
}