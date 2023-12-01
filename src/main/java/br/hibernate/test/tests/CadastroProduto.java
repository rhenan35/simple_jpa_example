package br.hibernate.test.tests;

import br.hibernate.test.dao.CategoriaDao;
import br.hibernate.test.dao.ProdutoDao;
import br.hibernate.test.model.Produto;
import br.hibernate.test.util.JpaUtil;
import br.hibernate.test.model.Category;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {
    public static void main(String[] args) {
        Cadastrar();
        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.searchById(1l);
        System.out.println(p.getValue());
        List<Produto> todos = produtoDao.searchByCategoryName("CELULARES");
        todos.forEach(p2 -> System.out.println(p2.getName()));

        BigDecimal value = produtoDao.searchValueByName("Iphone Xr");
        System.out.println("Preço do Produto: " + value);
    }

    private static void Cadastrar() {
        Category celulares = new Category("CELULARES");
        Produto celular = new Produto("Iphone Xr", "celular muito bom", new BigDecimal("800"), celulares);

        EntityManager em = JpaUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        categoriaDao.insert(celulares);
        produtoDao.insert(celular);
        em.getTransaction().commit();
        em.close();
    }
}
