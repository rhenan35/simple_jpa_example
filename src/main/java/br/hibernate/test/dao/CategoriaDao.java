package br.hibernate.test.dao;

import br.hibernate.test.model.Category;
import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void insert(Category category) {
        this.em.persist(category);
    }
}
