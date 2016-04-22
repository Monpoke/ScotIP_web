package scotip.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import scotip.entities.*;
import scotip.entities.Company;

/**
 * Created by Pierre on 21/04/2016.
 */
public class HibernateFactory {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;



    /**
     * HAVE TO REGISTER ALL ENTITIES CLASS HERE
     * @param configuration
     */
    private static void registerCLASS(Configuration configuration) {

        configuration.addAnnotatedClass(Module.class)
                .addAnnotatedClass(Company.class)
                .addAnnotatedClass(Line.class)
             //   .addAnnotatedClass(ModuleModel.class)
                .addAnnotatedClass(Switchboard.class);
    }


    /**
     * INIT THE CLASS
     */
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("config/hibernate.cfg.xml");

            registerCLASS(configuration);

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


    /**
     * PROVIDE A HIBERNATE SESSION
     * @return
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }


    /**
     * PROVIDE A HIBERNATE AUTOMATIC SESSION
     * @return
     * @throws HibernateException
     */
    public static Session getCurrentSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }



}
