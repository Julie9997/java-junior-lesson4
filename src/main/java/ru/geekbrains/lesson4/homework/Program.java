package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson4.models.Course;
import ru.geekbrains.lesson4.models.Student;

import java.util.List;

/**
 * Задание
 * =======
 * Создайте базу данных (например, SchoolDB).
 * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
 * Настройте Hibernate для работы с вашей базой данных.
 * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 * Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */

public class Program {
    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.school.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()){

            // Создание объекта
            Course course = new Course("Java",12);
            System.out.println(course);
            save(course,sessionFactory);

            Course retrievedCourse=load(course.getId(),sessionFactory);
            System.out.println(retrievedCourse);

            retrievedCourse.setTitle("Python");
            retrievedCourse.setDuration(24);

            update(retrievedCourse,sessionFactory);

            delete(retrievedCourse,sessionFactory);

            Course course2 = new Course("C#",12);
            System.out.println(course2);
            save(course2,sessionFactory);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void save(Course course,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        session.save(course);
        session.getTransaction().commit();

        System.out.println("Курс успешно сохранен");

    }
    public static Course load(int id,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        Course retrievedCourse = session.get(Course.class, id);
        session.getTransaction().commit();


        return retrievedCourse;
    }

    public static void update(Course course,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        session.update(course);

        session.getTransaction().commit();
        System.out.println("Курс успешно обновлен");
    }
    public static void delete(Course course,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        session.delete(course);

        session.getTransaction().commit();
        System.out.println("Курс успешно удален");

    }
}
