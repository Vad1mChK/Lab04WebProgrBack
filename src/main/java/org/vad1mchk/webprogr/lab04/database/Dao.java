package org.vad1mchk.webprogr.lab04.database;

import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

import java.util.List;

public interface Dao<E> {
    List<E> selectAll();

    E selectById(long id);

    E insert(E element);

    E update(E element);

    void deleteAll();

    void delete(E element);
}