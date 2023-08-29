package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Vacancy v1 = Vacancy.of("Java Developer", 150);
            Vacancy v2 = Vacancy.of("Java Developer", 200);
            Vacancy v3 = Vacancy.of("Java Developer", 300);
            Vacancy v4 = Vacancy.of("Java Developer", 350);
            session.save(v1);
            session.save(v2);
            session.save(v3);
            session.save(v4);
            VacancyDB vdb1 = VacancyDB.of();
            VacancyDB vdb2 = VacancyDB.of();
            vdb2.addVacancy(v1);
            vdb2.addVacancy(v2);
            vdb1.addVacancy(v3);
            vdb1.addVacancy(v4);
            session.save(vdb1);
            session.save(vdb2);
            Candidate one = Candidate.of("Alex", 4, 300, vdb1);
            Candidate two = Candidate.of("Nikolay", 2, 150, vdb2);
            session.save(one);
            session.save(two);
            Candidate rsl = session.createQuery(
                    "select distinct c from Candidate c "
                            + "join fetch c.vacancyDB vdb "
                            + "join fetch vdb.vacancies v "
                            + "where c.id = :cId", Candidate.class
            ).setParameter("cId", 1).uniqueResult();
            System.out.println(rsl);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
