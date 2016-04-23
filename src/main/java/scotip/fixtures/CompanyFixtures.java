package scotip.fixtures;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scotip.entities.Company;
import scotip.entities.Line;
import scotip.util.HibernateFactory;

/**
 * Created by Pierre on 21/04/2016.
 */
public class CompanyFixtures {

    CompanyFixtures() {

        createDefaultCompany();

    }

    private void createDefaultCompany() {

        Session session = HibernateFactory.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // FRENCH LINE
            Company company = getSampleCompany();

            session.save(company);

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();

            he.printStackTrace();
        } finally {
            session.close();
        }
    }


    /**
     * Sample company
     * @return
     */
    public static Company getSampleCompany() {
        Company company = new Company();
        company.setName("SCOTIP Software");

        company.setAddress("101, Baker Street");
        company.setCity("Holmes");
        company.setPostcode("59650");
        company.setCountry("United Kingdom");

        company.setContactMail("sherlock@watson.io");
        company.setContactName("Sherlock Holmes");
        company.setContactPhone("118218");
                return company;
    }
}
