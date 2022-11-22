package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Casa;

public class Problema {

	public static void main(String[] args) {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("atividade02");

		EntityManager em = emFactory.createEntityManager();

		List<Casa> casas = em.createQuery("FROM Casa", Casa.class).getResultList();
		System.out.println(casas);

	}

}
