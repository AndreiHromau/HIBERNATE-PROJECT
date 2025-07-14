package org.example.hibernatecore.dao;

import org.example.hibernatecore.model.Contact;

import java.util.List;

public interface ContactDao {

    List<Contact> getAllContacts();

    Contact getContactById(Long id);

    Long addContact(Contact contact);

    void updatePhoneNumber(Long id, String phoneNumber);

    void updateEmail(Long id, String email);

    void deleteContact(Long id);
}
