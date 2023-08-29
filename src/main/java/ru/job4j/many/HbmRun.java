package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.many.model.Brand;
import ru.job4j.many.model.Car;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Car one = Car.of("X1");
            Car two = Car.of("X2");
            Car three = Car.of("X3");
            Car four = Car.of("X4");
            Car five = Car.of("X5");
            session.save(one);
            session.save(two);
            session.save(three);
            session.save(four);
            session.save(five);
            Brand brand = Brand.of("BMW");
            brand.addCar(session.load(Car.class, 1));
            brand.addCar(session.load(Car.class, 2));
            brand.addCar(session.load(Car.class, 3));
            brand.addCar(session.load(Car.class, 4));
            brand.addCar(session.load(Car.class, 5));
            session.save(brand);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}