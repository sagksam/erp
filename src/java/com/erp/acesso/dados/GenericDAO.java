package com.erp.acesso.dados;

import com.erp.modelo.classes.comuns.EntidadeBase;
import com.erp.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

//Classe com métodos genericos de acesso a dados
public class GenericDAO implements Serializable {

    private static final long serialVersionUID = -169258812805375171L;

    private EntityManager entityManager = JPAUtil.getEntityManager();

    //Método genérico para salvar registros no banco de dados
    public <T> T save(T t) throws Exception {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
        }

        return t;
    }

    //Método genérico para salvar uma lista no banco de dados
    public <T> List<T> save(List<T> ts) throws Exception {

        List<T> list = new ArrayList();

        for (T t : ts) {
            list.add(save(t));
        }

        return list;
    }

    //Método genérico para atualizar registros no banco de dados
    public <T> T update(T t) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
        }

        return t;
    }

    //Método genérico para atualizar uma lista no banco de dados
    public <T> List<T> update(List<T> ts) throws Exception {

        List<T> list = new ArrayList();

        for (T t : ts) {
            list.add(update(t));
        }

        return list;
    }

    //Método genérico para deletar um registro do banco de dados
    public <T> T delete(T t) throws Exception {

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(t.getClass(), ((EntidadeBase) t).getId()));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
        }

        return t;
    }

    //Método genérico para deletar uma lista do banco de dados
    public <T> List<T> delete(List<T> ts) throws Exception {

        List<T> list = new ArrayList();

        for (T t : ts) {
            list.add(delete(t));
        }

        return list;
    }

    //Método genérico para encontrar um registro no banco de dados através do id
    public <T> T findById(Object id, Class c) {

        T entity = (T) entityManager.find(c, id);
        entityManager.detach(entity);
        return entity;
    }

}
