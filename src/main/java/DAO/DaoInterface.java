package DAO;

import java.io.Serializable;

public interface DaoInterface <T, Id extends Serializable> {

    void persist(T entity);

}
