package hql;

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
            Candidate one = Candidate.of("Alex", 4, 300);
            Candidate two = Candidate.of("Nikolay", 3, 250);
            Candidate three = Candidate.of("Nikita", 2, 200);
            session.save(one);
            session.save(two);
            session.save(three);
            Query query = session.createQuery("from Candidate");
            for (Object st : query.list()) {
                System.out.println(st);
            }
            query = session.createQuery("from Candidate s where s.id = 1");
            System.out.println(query.uniqueResult());
            query = session.createQuery("from Candidate s where s.name = :name");
            query.setParameter("name", "Nikolay");
            System.out.println(query.uniqueResult());
            query = session.createQuery(
                    "update Candidate s set s.name = :name where s.id = :fId"
            );
            query.setParameter("name", "Alexey");
            query.setParameter("fId", 1);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            session = sf.openSession();
            query = session.createQuery("from Candidate s where s.id = 1");
            System.out.println(query.uniqueResult());
            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 3)
                    .executeUpdate();
            query = session.createQuery("from Candidate");
            for (Object st : query.list()) {
                System.out.println(st);
            }
/*
Student one = Student.of("Alex", 21, "Moscow");
Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
Student three = Student.of("Nikita", 25, "Kaliningrad");
session.save(one);
session.save(two);
session.save(three);
*/
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
