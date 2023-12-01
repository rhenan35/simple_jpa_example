package br.hibernate.test.dao;

import br.hibernate.test.model.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void insert(Produto produto) {
        this.em.persist(produto);
    }

    public Produto searchById(Long id) {
        return this.em.find(Produto.class, id);
    }

    public List<Produto> searchAll() {
        String jpql = "SELECT p FROM Produto p";
        return this.em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> searchByName(String name) {
        String jpql = "SELECT p FROM Produto p WHERE p.name = :name";
        return this.em.createQuery(jpql, Produto.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Produto> searchByCategoryName(String name) {
        String jpql = "SELECT p FROM Produto p WHERE p.category.name = :name";
        return this.em.createQuery(jpql, Produto.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal searchValueByName(String name) {
        String jpql = "SELECT p.value FROM Produto p WHERE p.name = :name";
        return this.em.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
