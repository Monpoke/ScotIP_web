package scotip.dao;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import scotip.entities.Line;

import java.util.List;

/**
 * Created by Pierre on 21/04/2016.
 */
public class LineDAO extends BaseDAO {

    /**
     * Returns the shared lines.
     *
     * @return
     */
    public List<Line> getSharedLines() {
        Session session = getSession();
        List<Line> sharedNumbers = session.createCriteria(Line.class)
                .add(Restrictions.eq("shared", true))
                .list();
        session.close();

        return sharedNumbers;
    }

    /**
     * Get shared number.
     *
     * @param id
     * @return
     */
    public Line getSharedNumber(int id) {
        Line toReturn = null;
        Session session = getSession();

        Query query = session.createQuery("from Line line WHERE line.lineId = :lineId");
        query.setParameter("lineId", id);
        try {
            toReturn = (Line) query.uniqueResult();
        } catch (NonUniqueResultException nure) {
            nure.printStackTrace();
        }
        session.close();

        return toReturn;
    }

}
