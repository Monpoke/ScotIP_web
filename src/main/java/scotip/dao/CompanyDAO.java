package scotip.dao;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import scotip.entities.Company;

/**
 * Created by svevia on 23/04/2016.
 */
public class CompanyDAO extends BaseDAO{

    public Company getCompany(String mail) {
        Company toReturn = null;
        Session session = getSession();

        Query query = session.createQuery("from Company company WHERE company.ContactMail = :mail");
        query.setParameter("mail", mail);
        try {
            toReturn = (Company) query.uniqueResult();
        } catch (NonUniqueResultException nure) {
            nure.printStackTrace();
        }
        session.close();

        return toReturn;
    }






}
