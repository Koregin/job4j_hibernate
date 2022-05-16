package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            /*
            Candidate one = Candidate.of("Ivan", 5, 90000.0);
            Candidate two = Candidate.of("Petr", 10, 150000.0);
            Candidate three = Candidate.of("Evgeny", 15, 200000.0);
            session.save(one);
            session.save(two);
            session.save(three);
            */
            /* Select all candidates */
            Query query = session.createQuery("from Candidate");
            for (Object candidate : query.list()) {
                System.out.println(candidate);
            }
            /* Select candidate by id */
            System.out.println(session.createQuery("from Candidate c where c.id = :fId")
                    .setParameter("fId", 1)
                    .uniqueResult());
            /* Select candidate by name */
            System.out.println(session.createQuery("from Candidate c where c.name = :fName")
                    .setParameter("fName", "Petr")
                    .uniqueResult());
            /* Candidate update */
            session.createQuery("update Candidate c set c.experience = :newExpirience, "
                            + "c.salary = :newSalary where c.id = :fId")
                    .setParameter("newExpirience", 6)
                    .setParameter("newSalary", 100000.0)
                    .setParameter("fId", 1)
                    .executeUpdate();
            /* Remove candidate by id */
            session.createQuery("delete Candidate where id = :fId")
                    .setParameter("fId", 2)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
