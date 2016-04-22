package scotip.fixtures;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scotip.entities.Line;
import scotip.util.HibernateFactory;

/**
 * Created by Pierre on 21/04/2016.
 */
public class LinesFixtures {

    LinesFixtures() {

        createDefaultNumber();

    }

    private void createDefaultNumber() {

        Session session = HibernateFactory.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // FRENCH LINE
            Line defaultAccess = new Line(33, "0362729020",true);
            defaultAccess.setCountry("France");
            session.save(defaultAccess);

            // GLASGOW LINE
            Line glasgow = new Line(44, "01412570023",true);
            glasgow.setCountry("United Kingdom");
            session.save(glasgow);

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();

            he.printStackTrace();
        } finally {
            session.close();
        }
    }

}
