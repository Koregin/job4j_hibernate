package ru.job4j.manytomany;

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
            Author one = Author.of("Брайан Гетц");
            Author two = Author.of("Джошуа Блох");
            Author three = Author.of("Джон Толкин");

            Book first = Book.of("Java Concurrency in practice");
            first.getAuthors().add(one);
            first.getAuthors().add(two);

            Book second = Book.of("Java. Эффективное программирование");
            second.getAuthors().add(two);

            Book third = Book.of("Властелин колец");
            third.getAuthors().add(three);

            /*
            session.persist(first);
            session.persist(second);
            session.persist(third);
            */

            Book book = session.get(Book.class, 1);
            session.remove(book);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
