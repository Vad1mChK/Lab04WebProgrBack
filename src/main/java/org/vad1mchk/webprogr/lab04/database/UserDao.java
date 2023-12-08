package org.vad1mchk.webprogr.lab04.database;

import jakarta.ejb.*;
import jakarta.persistence.*;
import org.vad1mchk.webprogr.lab04.model.entity.User;

import java.util.List;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserDao implements Dao<User> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> selectAll() {
        return entityManager.createQuery("Select u from User u").getResultList();
    }

    @Override
    public User selectById(long id) {
        return entityManager.find(User.class, id);
    }

    public User selectByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User insert(User element) {
        entityManager.persist(element);
        return element;
    }

    @Override
    public User update(User element) {
        entityManager.merge(element);
        return element;
    }

    @Override
    public void delete(User element) {
        if (entityManager.contains(element)) {
            entityManager.remove(element);
        } else {
            entityManager.remove(entityManager.merge(element));
        }
    }
}
