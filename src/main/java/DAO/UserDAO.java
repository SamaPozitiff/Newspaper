package DAO;

import DTO.UserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDAO implements DaoInterface<UserDTO, Long>{
    private Session currentSession;
    private Transaction currentTransaction;

    public Session getCurrentSession(){
        return currentSession;
    }

    public Session openSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }
    @Override
    public void persist(UserDTO entity) {
        getCurrentSession().save(entity);
    }
}
