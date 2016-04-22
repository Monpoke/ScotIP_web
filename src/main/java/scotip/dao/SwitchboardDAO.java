package scotip.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import scotip.entities.Line;
import scotip.entities.Switchboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 21/04/2016.
 */
public class SwitchboardDAO extends BaseDAO {

    /**
     * Try to save
     *
     * @param s
     */
    public void save(Switchboard s) {

        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(s);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {

            close();
        }

    }


    /**
     * Checks if access code is not taken.
     * @param l
     * @return
     */
    public boolean accessCodeIsAvailable(int phoneCodeAccess, Line l){
        Session session=getSession();
        Transaction tx=null;
        List resultList = new ArrayList<>();
        try{

            /*resultList = session.createCriteria(Switchboard.class)
                    .add(Restrictions.eq("phoneCodeAccess", phoneCodeAccess))
                    .add.list();*/

        } catch(HibernateException he){
            if(tx!=null){ tx.rollback(); }
            he.printStackTrace();
        } finally {
            session.close();
        }

        return resultList.size() == 0;
    }


}
