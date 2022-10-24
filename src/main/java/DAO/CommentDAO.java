package DAO;


import DTO.CommentDTO;
import DTO.NewspaperArticleDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CommentDAO implements DaoInterface<CommentDTO, Long>{
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
    public void persist(CommentDTO entity) {
        getCurrentSession().save(entity);
    }
}
