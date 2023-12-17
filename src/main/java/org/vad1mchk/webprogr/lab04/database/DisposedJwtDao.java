package org.vad1mchk.webprogr.lab04.database;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.vad1mchk.webprogr.lab04.model.entity.DisposedJwt;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DisposedJwtDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(DisposedJwt element) {
        entityManager.persist(element);
    }

    public boolean exists(String jwt) {
        if (jwt == null || jwt.trim().isEmpty()) {
            return false;
        }
        return entityManager.find(DisposedJwt.class, jwt) != null;
    }
}
