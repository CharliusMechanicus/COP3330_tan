/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// EXCEPTION IMPORTS
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;

class ContactListTest
{

  ContactList contact_list;

  @Test
  public void addingItemsIncreasesSize()
  {
    contact_list = new ContactList();
    assertEquals(0, contact_list.get_number_of_contacts());

    contact_list.add_contact("Willy", "Wonka", "555-555-5555", "willy.wonka@chocofactory.com");
    assertEquals(1, contact_list.get_number_of_contacts());

    contact_list.add_contact("Oscar", "Grouch", "444-444-4444", "oscar.grouch@sesamestreet.com");
    assertEquals(2, contact_list.get_number_of_contacts());
  }

  /***************************** (1) ******************************/

  @Test
  public void editingItemsFailsWithAllBlankValues()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Willy", "Wonka", "555-555-5555", "willy.wonka@chocofactory.com");
    assertEquals(1, contact_list.get_number_of_contacts());

    assertThrows(IllegalArgumentException.class, ()->
                 { contact_list.edit_nth_contact(0, "", "", "", ""); });

    assertEquals("Willy", contact_list.get_nth_first_name(0));
    assertEquals("Wonka", contact_list.get_nth_last_name(0));
    assertEquals("555-555-5555", contact_list.get_nth_phone_number(0));
    assertEquals("willy.wonka@chocofactory.com", contact_list.get_nth_email_address(0));
  }

  /***************************** (2) ******************************/

  @Test
  public void editingItemsFailsWithInvalidIndex()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Willy", "Wonka", "555-555-5555", "willy.wonka@chocofactory.com");
    assertEquals(1, contact_list.get_number_of_contacts());

    assertThrows(IndexOutOfBoundsException.class, ()->
                 { contact_list.edit_nth_contact(557321, "Oscar", "Grouch", "444-444-4444", "oscar.grouch@sesamestreet.com"); });
  }

  /***************************** (3) ******************************/

  @Test
  public void editingSucceedsWithBlankFirstName()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Willy", "Wonka", "555-555-5555", "willy.wonka@chocofactory.com");
    assertEquals(1, contact_list.get_number_of_contacts());

    contact_list.edit_nth_contact(0, "", "Grouch", "444-444-4444", "oscar.grouch@sesamestreet.com");
    assertEquals("", contact_list.get_nth_first_name(0));
    assertEquals("Grouch", contact_list.get_nth_last_name(0));
    assertEquals("444-444-4444", contact_list.get_nth_phone_number(0));
    assertEquals("oscar.grouch@sesamestreet.com", contact_list.get_nth_email_address(0));
  }

  /***************************** (4) ******************************/

  @Test
  public void editingSucceedsWithBlankLastName()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Willy", "Wonka", "555-555-5555", "willy.wonka@chocofactory.com");
    assertEquals(1, contact_list.get_number_of_contacts());

    contact_list.edit_nth_contact(0, "Oscar", "", "444-444-4444", "oscar.grouch@sesamestreet.com");
    assertEquals("Oscar", contact_list.get_nth_first_name(0));
    assertEquals("", contact_list.get_nth_last_name(0));
    assertEquals("444-444-4444", contact_list.get_nth_phone_number(0));
    assertEquals("oscar.grouch@sesamestreet.com", contact_list.get_nth_email_address(0));
  }

  /***************************** (5) ******************************/

  @Test
  public void editingSucceedsWithBlankPhone()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Willy", "Wonka", "555-555-5555", "willy.wonka@chocofactory.com");
    assertEquals(1, contact_list.get_number_of_contacts());

    contact_list.edit_nth_contact(0, "Oscar", "Grouch", "", "oscar.grouch@sesamestreet.com");
    assertEquals("Oscar", contact_list.get_nth_first_name(0));
    assertEquals("Grouch", contact_list.get_nth_last_name(0));
    assertEquals("", contact_list.get_nth_phone_number(0));
    assertEquals("oscar.grouch@sesamestreet.com", contact_list.get_nth_email_address(0));
  }

  /***************************** (6) ******************************/

  @Test
  public void editingSucceedsWithNonBlankValues()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Willy", "Wonka", "555-555-5555", "willy.wonka@chocofactory.com");
    assertEquals(1, contact_list.get_number_of_contacts());

    assertEquals("Willy", contact_list.get_nth_first_name(0));
    assertEquals("Wonka", contact_list.get_nth_last_name(0));
    assertEquals("555-555-5555", contact_list.get_nth_phone_number(0));
    assertEquals("willy.wonka@chocofactory.com", contact_list.get_nth_email_address(0));

    contact_list.edit_nth_contact(0, "Oscar", "Grouch", "444-444-4444", "oscar.grouch@sesamestreet.com");
    assertEquals("Oscar", contact_list.get_nth_first_name(0));
    assertEquals("Grouch", contact_list.get_nth_last_name(0));
    assertEquals("444-444-4444", contact_list.get_nth_phone_number(0));
    assertEquals("oscar.grouch@sesamestreet.com", contact_list.get_nth_email_address(0));
  }

  /***************************** (7) ******************************/

  @Test
  public void newListIsEmpty()
  {
    contact_list = new ContactList();
    assertEquals(0, contact_list.get_number_of_contacts());
  }

  /***************************** (8) ******************************/

  @Test
  public void removingItemsDecreasesSize()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Some", "Guy", "111-111-1111", "some.guy@email.com");
    contact_list.add_contact("Another", "Guy", "222-222-2222", "another.guy@email.com");
    contact_list.add_contact("Additional", "Dude", "333-333-3333", "additional.dude@email.com");

    assertEquals(3, contact_list.get_number_of_contacts());

    contact_list.remove_nth_contact(0);
    assertEquals(2, contact_list.get_number_of_contacts());
    contact_list.remove_nth_contact(0);
    assertEquals(1, contact_list.get_number_of_contacts());
    contact_list.remove_nth_contact(0);
    assertEquals(0, contact_list.get_number_of_contacts());
  }

  /***************************** (9) ******************************/

  @Test
  public void removingItemsFailsWithInvalidIndex()
  {
    contact_list = new ContactList();
    contact_list.add_contact("Some", "Guy", "111-111-1111", "some.guy@email.com");
    contact_list.add_contact("Another", "Guy", "222-222-2222", "another.guy@email.com");
    contact_list.add_contact("Additional", "Dude", "333-333-3333", "additional.dude@email.com");
    assertEquals(3, contact_list.get_number_of_contacts());

    assertThrows(IndexOutOfBoundsException.class, ()->
                 { contact_list.remove_nth_contact(7); });

    assertEquals(3, contact_list.get_number_of_contacts());
  }

  /***************************** (10) ******************************/

  @Test
  public void savedContactListCanBeLoaded()
  {
    contact_list = new ContactList();
    contact_list.add_contact("First", "Guy", "111-111-1111", "first.guy@email.com");
    contact_list.add_contact("Second", "Man", "222-222-2222", "second.man@email.com");
    contact_list.add_contact("Third", "Dude", "333-333-3333", "third.dude@email.com");
    contact_list.add_contact("Fourth", "Fellow", "444-444-4444", "fourth.fellow@email.com");
    assertEquals(4, contact_list.get_number_of_contacts());

    /***************************************************/
    // TRY TO SAVE CURRENT CONTACTS
    try
    {
      contact_list.save_contacts("four_contacts.txt");
    } // END try

    catch(FileNotFoundException ex)
    {
      fail("Could not save contacts to external file");
    } // END catch
    /***************************************************/

    // DELETE ALL CURRENT CONTACTS
    contact_list.clear_all_contacts();
    assertEquals(0, contact_list.get_number_of_contacts());

    /***************************************************/
    // TRY TO LOAD CONTACTS
    try
    {
      contact_list.load_contacts("four_contacts.txt");
    }

    catch(InvalidPathException | IOException ex)
    {
      fail("Could not load external file with saved contacts");
    }
    /***************************************************/

    assertEquals(4, contact_list.get_number_of_contacts());

    assertEquals("First", contact_list.get_nth_first_name(0));
    assertEquals("Guy", contact_list.get_nth_last_name(0));
    assertEquals("111-111-1111", contact_list.get_nth_phone_number(0));
    assertEquals("first.guy@email.com", contact_list.get_nth_email_address(0));

    assertEquals("Second", contact_list.get_nth_first_name(1));
    assertEquals("Man", contact_list.get_nth_last_name(1));
    assertEquals("222-222-2222", contact_list.get_nth_phone_number(1));
    assertEquals("second.man@email.com", contact_list.get_nth_email_address(1));

    assertEquals("Third", contact_list.get_nth_first_name(2));
    assertEquals("Dude", contact_list.get_nth_last_name(2));
    assertEquals("333-333-3333", contact_list.get_nth_phone_number(2));
    assertEquals("third.dude@email.com", contact_list.get_nth_email_address(2));

    assertEquals("Fourth", contact_list.get_nth_first_name(3));
    assertEquals("Fellow", contact_list.get_nth_last_name(3));
    assertEquals("444-444-4444", contact_list.get_nth_phone_number(3));
    assertEquals("fourth.fellow@email.com", contact_list.get_nth_email_address(3));

  }

  /***************************** (11) ******************************/

} // END ContactListTest CLASS