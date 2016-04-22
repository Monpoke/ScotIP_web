package scotip.dao;

import org.hibernate.Session;
import scotip.util.HibernateFactory;

/**
 * Created by Pierre on 21/04/2016.
 */
public class BaseDAO {
    private Session session;

    /**
     * Gets the session.
     */
    public BaseDAO(){
    }




    /**
     * Returns the Hibernate SessionUtil.
     *
     * @return
     */
    public Session getSession() {
        if(session==null){
            session=HibernateFactory.getSession();
        }
        return session;
    }

    public void close() {
        if (session != null) {
            session.close();
            session=null;
        }
    }
}
