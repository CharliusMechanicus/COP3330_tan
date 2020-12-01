/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.nio.file.Paths;

// EXCEPTION IMPORTS
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ContactList
{

  /*********************************************
    CONSTANTS
  **********************************************/

  // VERIFICATION THAT FILE IS OK TO PARSE THROUGH
  private static final String CONTACT_LIST_ID = "[~THIS SIGNIFIES THAT THIS FILE IS A CONTACT LIST~]";

  // INDICATES END OF CERTAIN CONTACT ITEM ENTRY IN EXTERNAL FILE
  private static final String CONTACT_PARTITION = "<~END_CONTACT~>";

  private static final String FIRST_NAME_INDICATOR = "<~FIRST_NAME~>";
  private static final String LAST_NAME_INDICATOR = "<~LAST_NAME~>";
  private static final String PHONE_NUMBER_INDICATOR = "<~PHONE_NUMBER~>";
  private static final String EMAIL_INDICATOR = "<~EMAIL_ADDRESS~>";

  /*********************************************
    CONSTRUCTORS
  **********************************************/
  public ContactList()
  {
    contact_item_arraylist = new ArrayList<ContactItem>();
  } // END ContactList CONSTRUCTOR

  /*********************************************
    PUBLIC INTERFACE
  **********************************************/

  /*************************************************************
    PUBLIC INTERFACE FUNCTION LISTING
    ---------------------------------
    int get_number_of_contacts();
    void add_contact(String, String, String, String);
    void edit_nth_contact(int, String, String, String, String);
    String get_nth_first_name(int);
    String get_nth_last_name(int);
    String get_nth_phone_number(int);
    String get_nth_email_address(int);
    void display_all_contacts();
    void clear_all_contacts();
    void remove_nth_contact(int);
    void save_contacts(String);
    void load_contacts(String);
  **************************************************************/

  public int get_number_of_contacts()
  {
    return contact_item_arraylist.size();
  } // END get_number_of_contacts()

  /********************************** NEXT FUNCTION ************************************/

  public void add_contact(String first_name_arg, String last_name_arg,
                          String phone_number_arg, String email_address_arg)
  throws IllegalArgumentException
  {

    ContactItem new_contact_item;
    new_contact_item = new ContactItem(first_name_arg, last_name_arg, phone_number_arg, email_address_arg);

    contact_item_arraylist.add(new_contact_item);

  } // END add_contact()

  /********************************** NEXT FUNCTION ************************************/

  // EDIT'S THE 'n'TH INDEXED CONTACT ('n' IS ZERO INDEXED)
  public void edit_nth_contact(int nth_index, String first_name_arg, String last_name_arg,
                               String phone_number_arg, String email_address_arg)
  throws IllegalArgumentException, IndexOutOfBoundsException
  {

    ContactItem contact_item = contact_item_arraylist.get(nth_index);

    contact_item.edit_contact_information(first_name_arg, last_name_arg, phone_number_arg, email_address_arg);

  } // END edit_nth_contact()

  /********************************** NEXT FUNCTION ************************************/

  // RETURNS FIRST NAME OF 'n'TH INDEXED CONTACT ('n' IS ZERO INDEXED)
  public String get_nth_first_name(int nth_index)
  throws IndexOutOfBoundsException
  {

    ContactItem contact_item;
    contact_item = contact_item_arraylist.get(nth_index);
    return contact_item.get_first_name();

  } // END get_nth_first_name()

  /********************************** NEXT FUNCTION ************************************/

  // RETURNS LAST NAME OF 'n'TH INDEXED CONTACT ('n' IS ZERO INDEXED)
  String get_nth_last_name(int nth_index)
  throws IndexOutOfBoundsException
  {

    ContactItem contact_item;
    contact_item = contact_item_arraylist.get(nth_index);
    return contact_item.get_last_name();

  } // END get_nth_last_name()

  /********************************** NEXT FUNCTION ************************************/

  // RETURNS PHONE NUMBER OF 'n'TH INDEXED CONTACT ('n' IS ZERO INDEXED)
  String get_nth_phone_number(int nth_index)
  throws IndexOutOfBoundsException
  {

    ContactItem contact_item;
    contact_item = contact_item_arraylist.get(nth_index);
    return contact_item.get_phone_number();

  } // END get_nth_phone_number()

  /********************************** NEXT FUNCTION ************************************/

  // RETURNS EMAIL ADDRESS OF 'n'TH INDEXED CONTACT ('n' IS ZERO INDEXED)
  String get_nth_email_address(int nth_index)
  throws IndexOutOfBoundsException
  {

    ContactItem contact_item;
    contact_item = contact_item_arraylist.get(nth_index);
    return contact_item.get_email_address();

  } // END get_nth_email_address()

  /********************************** NEXT FUNCTION ************************************/

  public void display_all_contacts()
  {

    for(int i = 0; i < contact_item_arraylist.size(); ++i)
    {
      System.out.printf("%d) %s%n", i, contact_item_arraylist.get(i));
    } // END for LOOP

  } // END display_all_contacts()

  /********************************** NEXT FUNCTION ************************************/

  public void clear_all_contacts()
  {
    contact_item_arraylist.clear();
  } // END clear_all_contacts()

  /********************************** NEXT FUNCTION ************************************/

  // REMOVES THE 'n'TH INDEXED CONTACT ('n' IS ZERO INDEXED)
  public void remove_nth_contact(int nth_index)
  throws IndexOutOfBoundsException
  {
    contact_item_arraylist.remove(nth_index);
  } // END remove_nth_contact()

  /********************************** NEXT FUNCTION ************************************/

  public void save_contacts(String filename)
  throws FileNotFoundException
  {

    /******************************************
      LOCAL VARIABLES
    *******************************************/

    Formatter output_formatter;

    ContactItem current_contact_item;
    String first_name;
    String last_name;
    String phone_number;
    String email_address;

    /*************************************************************/

    output_formatter = new Formatter(filename);

    // AS FIRST LINE, ADD CONTACT LIST ID SO OTHER TYPES OF FILES THAT CAN BE OPENED...
    // ...ARE NOT PARSED
    output_formatter.format("%s%n", CONTACT_LIST_ID);

    // ITERATE THROUGH ALL CONTACT ITEM ENTRIES AND SAVE INFORMATION FOR EACH
    for(int i = 0; i < contact_item_arraylist.size(); ++i)
    {

      current_contact_item = contact_item_arraylist.get(i);

      first_name = current_contact_item.get_first_name();
      last_name = current_contact_item.get_last_name();
      phone_number = current_contact_item.get_phone_number();
      email_address = current_contact_item.get_email_address();

      if(!first_name.isBlank())
      {
        output_formatter.format("%s%s%n", FIRST_NAME_INDICATOR, first_name);
      } // END if

      if(!last_name.isBlank())
      {
        output_formatter.format("%s%s%n", LAST_NAME_INDICATOR, last_name);
      } // END if

      if(!phone_number.isBlank())
      {
        output_formatter.format("%s%s%n", PHONE_NUMBER_INDICATOR, phone_number);
      } // END if

      if(!email_address.isBlank())
      {
        output_formatter.format("%s%s%n", EMAIL_INDICATOR, email_address);
      } // END if

      // IF IT'S NOT THE LAST CONTACT ITEM ENTRY
      if( i != (contact_item_arraylist.size() - 1) )
      {
        // INDICATE END OF CONTACT ITEM ENTRY IN FILE
        output_formatter.format("%s%n", CONTACT_PARTITION);
        continue;
      } // END if

      // OTHERWISE, WE'RE ON THE LAST CONTACT ITEM ENTRY
      output_formatter.format("%s", CONTACT_PARTITION);

    } // END for LOOP

    output_formatter.close();

  } // END save_contacts()

  /********************************** NEXT FUNCTION ************************************/

  public void load_contacts(String filename)
  throws InvalidPathException, UnsupportedEncodingException, IOException
  {

    /**************************************
      LOCAL VARIABLES
    ***************************************/

    Scanner file_input_scanner;
    String file_line_string;
    String[] split_string_array;
    boolean checked_1st_line_boolean = false;

    ContactItem contact_item;
    String first_name = "";
    String last_name = "";
    String phone_number = "";
    String email_address = "";

    /****************************************************/

    file_input_scanner = new Scanner(Paths.get(filename));
    // AT THIS POINT, WE CAN ASSUME THAT FILE WAS OPENED SUCCESSFULLY

    // IF CONTACT LIST IS NOT EMPTY, CLEAR THE LIST
    if(contact_item_arraylist.size() != 0)
      contact_item_arraylist.clear();

    /****************************************************/

    // SCAN ENTIRE FILE
    while(file_input_scanner.hasNext())
    {

      file_line_string = file_input_scanner.nextLine();

      // 1ST LINE OF CONTACT LIST SAVE FILE SHOULD HAVE CONTACT_LIST_ID
      // IF THIS FILE IS NOT A CONTACT LIST (SOME OTHER VIEWABLE FILE)
      if(!file_line_string.equals(CONTACT_LIST_ID) && !checked_1st_line_boolean)
      {
        throw new UnsupportedEncodingException("Error - File is not a contact list");
      } // END if

      // WHILE EACH FILE LINE IS AN ATTRIBUTE FOR A CERTAIN CONTACT
      while(!file_line_string.equals(CONTACT_PARTITION))
      {

        // EXTRACT CORRECT CONTACT FIELD
        /****************************************************************/
        if(file_line_string.startsWith(FIRST_NAME_INDICATOR))
        {
          split_string_array = file_line_string.split(FIRST_NAME_INDICATOR);
          first_name = split_string_array[1];
        } // END if

        else if(file_line_string.startsWith(LAST_NAME_INDICATOR))
        {
          split_string_array = file_line_string.split(LAST_NAME_INDICATOR);
          last_name = split_string_array[1];
        } // END else if

        else if(file_line_string.startsWith(PHONE_NUMBER_INDICATOR))
        {
          split_string_array = file_line_string.split(PHONE_NUMBER_INDICATOR);
          phone_number = split_string_array[1];
        } // END else if

        else if(file_line_string.startsWith(EMAIL_INDICATOR))
        {
          split_string_array = file_line_string.split(EMAIL_INDICATOR);
          email_address = split_string_array[1];
        } // END else if
        /****************************************************************/

        file_line_string = file_input_scanner.nextLine();

      } // END inner while LOOP

      // AT THIS POINT, WE HAVE ALL INFORMATION FOR A SINGLE CONTACT

      // SAVE CONTACT IN LOCAL MEMORY
      contact_item = new ContactItem(first_name, last_name, phone_number, email_address);
      contact_item_arraylist.add(contact_item);

      // REINITIALIZE FOR A NEW CONTACT
      first_name = "";
      last_name = "";
      phone_number = "";
      email_address = "";

      checked_1st_line_boolean = true;

    } // END while LOOP

    // AT THIS POINT, ALL CONTACTS HAVE BEEN SAVED TO LOCAL MEMORY FROM INPUT FILE

  } // END load_contacts()

  /*********************************************
    PRIVATE DATA
  **********************************************/

  private ArrayList<ContactItem> contact_item_arraylist;

} // END ContactList CLASS