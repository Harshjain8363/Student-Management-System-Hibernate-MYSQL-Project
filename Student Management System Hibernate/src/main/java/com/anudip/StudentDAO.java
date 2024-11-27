package com.anudip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class StudentDAO {
private SessionFactory sessionFactory;
	
    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void insertEntity(Student entity) {
    	Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            System.out.println("Entity inserted successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Student> fetchAllRecords() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Student> records = null;

        try {
            transaction = session.beginTransaction();

            // Using HQL to fetch all records
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            records = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return records;
    }
    public void updateMarksById(String sId, int newMarks) {
        Transaction transaction = null;
        try {
        	Session session = sessionFactory.openSession();
        
            transaction = session.beginTransaction();

            // HQL update query
            String hql = "UPDATE Student SET marks = :newMarks WHERE sId = :sId";
            Query query = session.createQuery(hql);
            query.setParameter("newMarks", newMarks);
            query.setParameter("sId", sId);

            int rowsAffected = query.executeUpdate(); // Execute update
            System.out.println("Rows affected: " + rowsAffected);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
}
    public void deleteById(String sId) {
        Transaction transaction = null;
        try {
        	Session session = sessionFactory.openSession();
        
            transaction = session.beginTransaction();

            // HQL delete query
            String hql = "DELETE FROM Student WHERE sId = :sId";
            Query query = session.createQuery(hql);
            query.setParameter("sId", sId);

            int rowsAffected = query.executeUpdate(); // Execute delete
            System.out.println("Rows affected: " + rowsAffected);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
}
    public Student getById(String sId) {
    	Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Student entity = null;

        try {
            transaction = session.beginTransaction();

            // HQL query to fetch data by ID
            String hql = "FROM Student WHERE sId = :sId";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("sId", sId);

            // Retrieve the single result
            entity = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return entity;
    }

}
