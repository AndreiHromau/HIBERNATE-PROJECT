package org.example.hibernatecore;

import org.example.hibernatecore.dao.ContactDaoImpl;
import org.example.hibernatecore.model.Contact;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.hibernate.cfg.Configuration;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HibernateCoreApplicationTest {

    private SessionFactory sessionFactory;
    private ContactDaoImpl contactDao;

    @BeforeAll
    public void setup() {
        // Создаем фабрику с конфигурацией для тестовой базы данных
        sessionFactory = new Configuration()
                .configure("/Hibernate.cfg.xml")
                .buildSessionFactory();

        contactDao = new ContactDaoImpl(sessionFactory);
    }

    @AfterAll
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testAddAndGetContact() {
        Contact contact = new Contact(null, "Test", "User", "testuser@example.com", "5551234");
        Long id = contactDao.addContact(contact);
        assertNotNull(id);

        Contact retrieved = contactDao.getContactById(id);
        assertNotNull(retrieved);
        assertEquals("Test", retrieved.getFirstName());
    }

    @Test
    public void testUpdatePhoneNumber() {
        Contact contact = new Contact(null, "Update", "Test", "update@example.com", "0000000");
        Long id = contactDao.addContact(contact);

        contactDao.updatePhoneNumber(id, "9999999");
        Contact updated = contactDao.getContactById(id);
        assertEquals("9999999", updated.getPhoneNumber());
    }

    @Test
    public void testUpdateEmail() {
        Contact contact = new Contact(null, "Email", "Test", "oldemail@example.com", "1111111");
        Long id = contactDao.addContact(contact);

        contactDao.updateEmail(id, "newemail@example.com");
        Contact updated = contactDao.getContactById(id);
        assertEquals("newemail@example.com", updated.getEmail());
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact(null, "Delete", "Test", "delete@example.com", "2222222");
        Long id = contactDao.addContact(contact);

        contactDao.deleteContact(id);
        Contact deleted = contactDao.getContactById(id);
        assertNull(deleted);
    }

    @Test
    public void testGetAllContacts() {
        List<Contact> contacts = contactDao.getAllContacts();
        assertNotNull(contacts);
    }
}