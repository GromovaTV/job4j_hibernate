package ru.job4j.manytomany.books;

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
            Book book1 = Book.of("Двенадцать стульев");
            Book book2 = Book.of("Игра престолов");
            Book book3 = Book.of("Битва королей");
            Writer writer1 = Writer.of("Илья Ильф");
            Writer writer2 = Writer.of("Евгений Петров");
            Writer writer3 = Writer.of("Джордж Мартин");
            writer1.getBooks().add(book1);
            writer2.getBooks().add(book1);
            writer3.getBooks().add(book2);
            writer3.getBooks().add(book3);
            session.persist(writer1);
            session.persist(writer2);
            session.persist(writer3);
            Writer writer = session.get(Writer.class, 1);
            session.remove(writer);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}