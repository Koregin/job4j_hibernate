package ru.job4j.model;

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
            CarModel x1 = CarModel.of("X1");
            session.save(x1);
            CarModel x2 = CarModel.of("X2");
            session.save(x2);
            CarModel x3 = CarModel.of("X3");
            session.save(x3);
            CarModel x4 = CarModel.of("X4");
            session.save(x4);
            CarModel x5 = CarModel.of("X5");
            session.save(x5);

            CarBrand brand = CarBrand.of("BMW");
            brand.addModel(session.load(CarModel.class, 1));
            brand.addModel(session.load(CarModel.class, 2));
            brand.addModel(session.load(CarModel.class, 3));
            brand.addModel(session.load(CarModel.class, 4));
            brand.addModel(session.load(CarModel.class, 5));

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
