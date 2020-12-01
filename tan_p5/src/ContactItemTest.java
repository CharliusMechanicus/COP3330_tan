/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest
{

  ContactItem contact_item;

  @Test
  public void creationFailsWithAllBlankValues()
  {
    Exception thrown_exception;

    thrown_exception = assertThrows(IllegalArgumentException.class, ()->
                 { contact_item = new ContactItem("", " ", "  ", "    "); });

    assertEquals("Error - Contact fields can not be all blank", thrown_exception.getMessage());

    thrown_exception = assertThrows(IllegalArgumentException.class, ()->
                 { contact_item = new ContactItem(); });

    assertEquals("Error - Contact fields can not be all blank", thrown_exception.getMessage());
  }

  /***************************** (1) ******************************/

  @Test
  public void creationSucceedsWithBlankEmail()
  {
    contact_item = null;
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "");

    assertNotNull(contact_item);
    assertEquals("Joey", contact_item.get_first_name());
    assertEquals("Ford", contact_item.get_last_name());
    assertEquals("222-222-2222", contact_item.get_phone_number());
    assertEquals("", contact_item.get_email_address());
  }

  /***************************** (2) ******************************/

  @Test
  public void creationSucceedsWithBlankFirstName()
  {
    contact_item = null;
    contact_item = new ContactItem("", "Ford", "222-222-2222", "joey.ford@email.com");

    assertNotNull(contact_item);
    assertEquals("", contact_item.get_first_name());
    assertEquals("Ford", contact_item.get_last_name());
    assertEquals("222-222-2222", contact_item.get_phone_number());
    assertEquals("joey.ford@email.com", contact_item.get_email_address());
  }

  /***************************** (3) ******************************/

  @Test
  public void creationSucceedsWithBlankLastName()
  {
    contact_item = null;
    contact_item = new ContactItem("Joey", "", "222-222-2222", "joey.ford@email.com");

    assertNotNull(contact_item);
    assertEquals("Joey", contact_item.get_first_name());
    assertEquals("", contact_item.get_last_name());
    assertEquals("222-222-2222", contact_item.get_phone_number());
    assertEquals("joey.ford@email.com", contact_item.get_email_address());
  }

  /***************************** (4) ******************************/

  @Test
  public void creationSucceedsWithBlankPhone()
  {
    contact_item = null;
    contact_item = new ContactItem("Joey", "Ford", "", "joey.ford@email.com");

    assertNotNull(contact_item);
    assertEquals("Joey", contact_item.get_first_name());
    assertEquals("Ford", contact_item.get_last_name());
    assertEquals("", contact_item.get_phone_number());
    assertEquals("joey.ford@email.com", contact_item.get_email_address());
  }

  /***************************** (5) ******************************/

  @Test
  public void creationSucceedsWithNonBlankValues()
  {
    contact_item = null;
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "joey.ford@email.com");

    assertNotNull(contact_item);
    assertEquals("Joey", contact_item.get_first_name());
    assertEquals("Ford", contact_item.get_last_name());
    assertEquals("222-222-2222", contact_item.get_phone_number());
    assertEquals("joey.ford@email.com", contact_item.get_email_address());
  }

  /***************************** (6) ******************************/

  @Test
  public void editingFailsWithAllBlankValues()
  {
    contact_item = new ContactItem("notblank", "notblank", "notblank", "notblank");

    Exception thrown_exception;

    thrown_exception = assertThrows(IllegalArgumentException.class, ()->
                       { contact_item.edit_contact_information("", "", "", ""); });

    assertEquals("Error - Contact fields can not be all blank", thrown_exception.getMessage());
  }

  /***************************** (7) ******************************/

  @Test
  public void editingSucceedsWithBlankEmail()
  {
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "joey.ford@email.com");
    contact_item.edit_contact_information("Frank", "Whiting", "333-333-3333", "");

    assertEquals("Frank", contact_item.get_first_name());
    assertEquals("Whiting", contact_item.get_last_name());
    assertEquals("333-333-3333", contact_item.get_phone_number());
    assertEquals("", contact_item.get_email_address());
  }

  /***************************** (8) ******************************/

  @Test
  public void editingSucceedsWithBlankFirstName()
  {
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "joey.ford@email.com");
    contact_item.edit_contact_information("", "Whiting", "333-333-3333", "frank.whiting@email.com");

    assertEquals("", contact_item.get_first_name());
    assertEquals("Whiting", contact_item.get_last_name());
    assertEquals("333-333-3333", contact_item.get_phone_number());
    assertEquals("frank.whiting@email.com", contact_item.get_email_address());
  }

  /***************************** (9) ******************************/

  @Test
  public void editingSucceedsWithBlankLastName()
  {
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "joey.ford@email.com");
    contact_item.edit_contact_information("Frank", "", "333-333-3333", "frank.whiting@email.com");

    assertEquals("Frank", contact_item.get_first_name());
    assertEquals("", contact_item.get_last_name());
    assertEquals("333-333-3333", contact_item.get_phone_number());
    assertEquals("frank.whiting@email.com", contact_item.get_email_address());
  }

  /***************************** (10) ******************************/

  @Test
  public void editingSucceedsWithBlankPhone()
  {
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "joey.ford@email.com");
    contact_item.edit_contact_information("Frank", "Whiting", "", "frank.whiting@email.com");

    assertEquals("Frank", contact_item.get_first_name());
    assertEquals("Whiting", contact_item.get_last_name());
    assertEquals("", contact_item.get_phone_number());
    assertEquals("frank.whiting@email.com", contact_item.get_email_address());
  }

  /***************************** (11) ******************************/

  @Test
  public void editingSucceedsWithNonBlankValues()
  {
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "joey.ford@email.com");
    contact_item.edit_contact_information("first", "last", "111-111-1111", "first.last@email.com");

    assertEquals("first", contact_item.get_first_name());
    assertEquals("last", contact_item.get_last_name());
    assertEquals("111-111-1111", contact_item.get_phone_number());
    assertEquals("first.last@email.com", contact_item.get_email_address());
  }

  /***************************** (12) ******************************/

  @Test
  public void testToString()
  {
    contact_item = new ContactItem("Joey", "Ford", "222-222-2222", "joey.ford@email.com");

    assertEquals(String.format("Name: Joey Ford%nPhone: 222-222-2222%nEmail: joey.ford@email.com"),
                                contact_item.toString());
  }

  /***************************** (13) ******************************/

} // END ContactItemTest CLASS