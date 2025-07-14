package org.example.hibernatecore.dao;

import org.example.hibernatecore.model.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ContactDaoImpl implements ContactDao {

    private final SessionFactory sessionFactory;

    public ContactDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Contact> getAllContacts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Contact", Contact.class).list();
        }
    }

    @Override
    public Contact getContactById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Contact.class, id);
        }
    }

    @Override
    public Long addContact(Contact contact) {
        Transaction transaction = null;
        Long contactId = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            contactId = (Long) session.save(contact);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return contactId;
    }

    @Override
    public void updatePhoneNumber(Long id, String phoneNumber) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                contact.setPhoneNumber(phoneNumber);
                session.update(contact);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void updateEmail(Long id, String email) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                contact.setEmail(email);
                session.update(contact);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteContact(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
