package DAO;

import DTO.NewspaperArticleDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class NewspaperArticleDAO implements DaoInterface<NewspaperArticleDTO, Long>{
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
    public void persist(NewspaperArticleDTO entity) {
        getCurrentSession().save(entity);
        System.out.println(entity.getId());
    }

    public List<NewspaperArticleDTO> getArticlesIn24h(){
        return currentSession.createNativeQuery("SELECT * FROM articles").list();
    }

    public NewspaperArticleDTO getArticles(Long id){
        return getCurrentSession().get(NewspaperArticleDTO.class, id);
    }
}
