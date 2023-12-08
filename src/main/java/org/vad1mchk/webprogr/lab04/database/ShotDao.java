package org.vad1mchk.webprogr.lab04.database;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.vad1mchk.webprogr.lab04.model.entity.Shot;
import org.vad1mchk.webprogr.lab04.model.entity.User;

import java.util.List;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ShotDao implements Dao<Shot> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shot> selectAll() {
        return entityManager.createQuery("Select s from Shot s").getResultList();
    }

    @Override
    public Shot selectById(long id) {
        return entityManager.find(Shot.class, id);
    }

    @Override
    public Shot insert(Shot element) {
        entityManager.persist(element);
        return element;
    }

    @Override
    public Shot update(Shot element) {
        entityManager.merge(element);
        return element;
    }

    @Override
    public void delete(Shot element) {
        if (entityManager.contains(element)) {
            entityManager.remove(element);
        } else {
            entityManager.remove(entityManager.merge(element));
        }
    }

    public void deleteAllByOwner(User owner) {
        Query query = entityManager.createQuery("DELETE FROM Shot WHERE owner=:o");
        query.setParameter("o", owner);
        query.executeUpdate();
    }
}
