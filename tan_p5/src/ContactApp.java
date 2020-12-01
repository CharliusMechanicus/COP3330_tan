/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import java.util.Scanner;

// EXCEPTION IMPORTS
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ContactApp extends ConsoleBasedMenuApp
{

  /***************************************************
    APPLICATION DATA
  ****************************************************/

  private MenuStateEnum current_menu_state;
  private ContactList master_contact_list;

  /***************************************************
    PUBLIC INTERFACE
  ****************************************************/

  @Override
  public void run_program()
  {

   // INITIALIZE DATA
   this.program_is_still_running = true;
   this.current_menu_state = MenuStateEnum.MAIN_MENU;

    while(this.program_is_still_running)
    {
      execute_proper_screen();
    } // END while LOOP

  } // END run_program()

  /***************************************************
    CONSTRUCTORS
  ****************************************************/

  public ContactApp()
  {
    master_contact_list = new ContactList();
  } // END ContactApp CONSTRUCTOR

  /***************************************************
    HELPER FUNCTIONS
  ****************************************************/

  /************************************************************************
    HELPER FUNCTION LISTING
    -----------------------
    void execute_proper_screen();
    void execute_main_menu_screen();
    void execute_list_operation_menu_screen();
    void execute_view_list_sub_screen();
    void execute_add_item_sub_screen();
    void execute_edit_item_sub_screen();
    void execute_remove_item_sub_screen();
    void execute_save_list_sub_screen();
    void execute_load_list_sub_screen();
    void process_edit_nth_item_request(int);
    void process_remove_nth_item_request(int);
    void process_save_contact_list_request(String);
    void process_load_contact_list_request(String);
    boolean is_menu_choice_valid(int);
  *************************************************************************/

  private void execute_proper_screen()
  {

    switch(current_menu_state)
    {

      case MAIN_MENU:
        execute_main_menu_screen();
        break;

      case LIST_OPERATION_MENU:
        execute_list_operation_menu_screen();
        break;

    } // END switch

  } // END execute_proper_menu()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_main_menu_screen()
  {

    /*******************************************
      LOCAL VARIABLES
    ********************************************/

    int menu_choice_number = -1;

    /**************************************************************/

    System.out.println("Contact List Main Menu");
    System.out.println("----------------------");
    System.out.println("");

    System.out.println("1) create a new list");
    System.out.println("2) load an existing list");
    System.out.println("3) quit");
    System.out.println("");

    System.out.println("Enter a number for your choice:");
    System.out.printf("> ");
    menu_choice_number = grab_numeric_menu_screen_choice();

    /*******************************************************/
    // AT THIS POINT, WE CAN ASSUME A VALID MENU CHOICE BY USER

    switch(menu_choice_number)
    {

      /********************************/
      case 1: // CREATE NEW LIST
      /********************************/
        current_menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        if(master_contact_list.get_number_of_contacts() > 0)
          master_contact_list.clear_all_contacts();
        System.out.println("new contact list has been created");
        System.out.println("");
        break;

      /********************************/
      case 2: // LOAD EXISTING LIST
      /********************************/
        System.out.println("");
        execute_load_list_sub_screen();
        break;

      /********************************/
      case 3: // QUIT
      /********************************/
        System.out.println("");
        this.program_is_still_running = false;
        break;

    } // END switch

  } // END execute_main_menu_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_list_operation_menu_screen()
  {

    /*******************************************
      LOCAL VARIABLES
    ********************************************/

    int menu_choice_number = -1;

    /**************************************************************/

    System.out.println("Contact List Operation Menu");
    System.out.println("---------------------------");
    System.out.println("");

    System.out.println("1) view the list");
    System.out.println("2) add an item");
    System.out.println("3) edit an item");
    System.out.println("4) remove an item");
    System.out.println("5) save the current list");
    System.out.println("6) quit to the main menu (current list will be discarded)");
    System.out.println("");

    System.out.println("Enter a number for your choice:");
    System.out.printf("> ");
    menu_choice_number = grab_numeric_menu_screen_choice();
    System.out.println("");

    /*******************************************************/
    // AT THIS POINT, WE CAN ASSUME A VALID MENU CHOICE BY USER

    switch(menu_choice_number)
    {

      /********************************/
      case 1: // VIEW LIST
      /********************************/
        execute_view_list_sub_screen();
        break;

      /***********************************/
      case 2: // ADD CONTACT ITEM TO LIST
      /***********************************/
        execute_add_item_sub_screen();
        break;

      /*************************************/
      case 3: // EDIT EXISTING CONTACT ITEM
      /*************************************/
        execute_edit_item_sub_screen();
        break;
      
      /********************************************************/
      case 4: // REMOVE CONTACT ITEM FROM CURRENT CONTACT LIST
      /********************************************************/
        execute_remove_item_sub_screen();
        break;

      /********************************/
      case 5: // SAVE CURRENT LIST
      /********************************/
        execute_save_list_sub_screen();
        break;

      /********************************/
      case 6: // QUIT
      /********************************/
        current_menu_state = MenuStateEnum.MAIN_MENU;
        master_contact_list.clear_all_contacts();
        break;

    } // END switch

  } // END execute_list_operation_menu_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_view_list_sub_screen()
  {

    System.out.println("Current Contacts");
    System.out.println("----------------");
    System.out.println("");

    /*****************************************/

    // IF NO CONTACTS TO DISPLAY
    if(master_contact_list.get_number_of_contacts() == 0)
    {
      System.out.println("[No contacts]");
    } // END if

    /*****************************************/

    // OTHERWISE, THERE ARE CONTACTS TO DISPLAY
    else
    {
      master_contact_list.display_all_contacts();
    } // END else

    /*****************************************/

    System.out.println("");
    System.out.println("Provide any input to go back:");
    System.out.printf("> ");

    clear_input_buffer();
    user_input_scanner.nextLine();
    System.out.println("");

  } // END execute_view_list_sub_screen()

/********************************* NEXT FUNCTION **************************************/

  private void execute_add_item_sub_screen()
  {

    /************************************************
      LOCAL VARIABLES
    *************************************************/

    String first_name_input;
    String last_name_input;
    String phone_number_input;
    String email_address_input;

    /************************************************************/

    System.out.println("Add Contact Item");
    System.out.println("----------------");
    System.out.println("");

    System.out.println("First name:");
    System.out.printf("> ");
    clear_input_buffer();
    first_name_input = user_input_scanner.nextLine();

    System.out.println("Last name:");
    System.out.printf("> ");
    clear_input_buffer();
    last_name_input = user_input_scanner.nextLine();

    System.out.println("Phone number:");
    System.out.printf("> ");
    clear_input_buffer();
    phone_number_input = user_input_scanner.nextLine();

    System.out.println("Email address:");
    System.out.printf("> ");
    clear_input_buffer();
    email_address_input = user_input_scanner.nextLine();

    System.out.println("");

    /************************************************************/

    // TRY TO ADD NEW CONTACT TO LIST
    try
    {
      master_contact_list.add_contact(first_name_input, last_name_input,
        phone_number_input, email_address_input);
      System.out.println("Contact successfully added");
    } // END try

    // IN CASE CONTACT ADD FAILS
    catch(IllegalArgumentException ex)
    {
      System.out.printf("%s%n", ex.getMessage());
    } // END catch

    /************************************************************/

    System.out.println("");

  } // END execute_add_item_sub_screen()

/********************************* NEXT FUNCTION **************************************/

  private void execute_edit_item_sub_screen()
  {

    /************************************************
      LOCAL VARIABLES
    *************************************************/

    int numeric_input;

    /************************************************************/

    System.out.println("Edit Contact Item");
    System.out.println("-----------------");
    System.out.println("");

    /*****************************************/

    // IF NO CONTACTS TO DISPLAY
    if(master_contact_list.get_number_of_contacts() == 0)
    {
      System.out.println("[No contacts]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************/

    // OTHERWISE, THERE ARE CONTACTS TO DISPLAY

    master_contact_list.display_all_contacts();
    System.out.println("");
    System.out.println("Enter a number corresponding to the contact you wish to edit:");
    System.out.printf("> ");

    numeric_input = grab_any_numeric_input();

    /*****************************************/

    process_edit_nth_item_request(numeric_input);
    System.out.println("");

  } // END execute_edit_item_sub_screen()

/********************************* NEXT FUNCTION **************************************/

  private void execute_remove_item_sub_screen()
  {

    /********************************************
      LOCAL VARIABLES
    *********************************************/

    int numeric_input;

    /*****************************************************/

    System.out.println("Remove Contact Item");
    System.out.println("-------------------");
    System.out.println("");

    /*****************************************/

    // IF NO CONTACTS TO DISPLAY
    if(master_contact_list.get_number_of_contacts() == 0)
    {
      System.out.println("[No contacts]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************/

    // OTHERWISE, THERE ARE CONTACTS TO DISPLAY

    master_contact_list.display_all_contacts();
    System.out.println("");
    System.out.println("Enter a number corresponding to the contact you wish to remove:");
    System.out.printf("> ");

    numeric_input = grab_any_numeric_input();
    System.out.println("");

    /*****************************************/

    process_remove_nth_item_request(numeric_input);
    System.out.println("");

  } // END execute_remove_item_sub_screen()

/********************************* NEXT FUNCTION **************************************/

  private void execute_save_list_sub_screen()
  {

    /***********************************************
      LOCAL VARIABLES
    ************************************************/

    String filename_to_save_as;

    /*****************************************************/

    System.out.println("Save Contact List");
    System.out.println("-----------------");
    System.out.println("");

    /*****************************************************/

    // IF NO CONTACTS TO SAVE
    if(master_contact_list.get_number_of_contacts() == 0)
    {
      System.out.println("[No contacts to save]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************************/

    // OTHERWISE, THERE ARE CONTACTS TO SAVE

    if(master_contact_list.get_number_of_contacts() == 1)
    {
      System.out.println("[1 contact to save]");
      System.out.println("");
    } // END if

    // OTHERWISE, NUMBER OF CONTACTS IS > 1
    else
    {
      System.out.printf("[%d contacts to save]%n%n", master_contact_list.get_number_of_contacts());
    } // END else

    System.out.println("Enter the filename you wish to save your contact list as (ex: list.txt)");
    System.out.printf("> ");
    clear_input_buffer();
    filename_to_save_as = user_input_scanner.nextLine();
    System.out.println("");

    process_save_contact_list_request(filename_to_save_as);
    System.out.println("");

  } // END execute_save_list_sub_screen()

/********************************* NEXT FUNCTION **************************************/

  private void execute_load_list_sub_screen()
  {

    /************************************************
      LOCAL VARIABLES
    *************************************************/

    String filename_to_load;

    /************************************************************/

    System.out.println("Load Contact List");
    System.out.println("-----------------");
    System.out.println("");

    System.out.println("Enter the filename to load:");
    System.out.printf("> ");

    clear_input_buffer();
    filename_to_load = user_input_scanner.nextLine();
    System.out.println("");

    process_load_contact_list_request(filename_to_load);

  } // END execute_load_list_sub_screen()

/********************************* NEXT FUNCTION **************************************/

  private void process_edit_nth_item_request(int numeric_input)
  {

    /************************************************
      LOCAL VARIABLES
    *************************************************/

    String first_name_input;
    String last_name_input;
    String phone_number_input;
    String email_address_input;

    /************************************************************/

    try
    {
      // SEE IF NUMERIC INPUT IS A VALID NUMBER FOR OUR CONTACT LIST
      master_contact_list.get_nth_first_name(numeric_input);

      System.out.println("Enter a new first name:");
      System.out.printf("> ");
      clear_input_buffer();
      first_name_input = user_input_scanner.nextLine();

      System.out.println("Enter a new last name:");
      System.out.printf("> ");
      clear_input_buffer();
      last_name_input = user_input_scanner.nextLine();

      System.out.println("Enter a new phone number:");
      System.out.printf("> ");
      clear_input_buffer();
      phone_number_input = user_input_scanner.nextLine();

      System.out.println("Enter a new email address:");
      System.out.printf("> ");
      clear_input_buffer();
      email_address_input = user_input_scanner.nextLine();

      master_contact_list.edit_nth_contact(numeric_input, first_name_input, last_name_input,
                                           phone_number_input, email_address_input);

      System.out.println("");
      System.out.println("Contact item successfully edited");
    } // END try

    // IN CASE USER ENTERS A NUMBER THAT DOES NOT CORRESPOND TO A CONTACT ITEM
    catch(IndexOutOfBoundsException ex)
    {
      System.out.println("");
      System.out.println("Error - number entered does not correspond to a contact item");
    } // END catch

    // IN CASE USER ENTERS INVALID INFORMATION FOR A CONTACT ITEM
    catch(IllegalArgumentException ex)
    {
      System.out.println("");
      System.out.printf("%s%n", ex.getMessage());
    } // END catch

  } // END process_edit_nth_item_request()

/********************************* NEXT FUNCTION **************************************/

  private void process_remove_nth_item_request(int numeric_input)
  {

    // TRY TO REMOVE CONTACT ITEM
    try
    {
      master_contact_list.remove_nth_contact(numeric_input);
      System.out.println("Contact item successfully removed");
    } // END try

    // IN CASE USER ENTERS A NUMBER THAT DOES NOT CORRESPOND TO A CONTACT ITEM
    catch(IndexOutOfBoundsException ex)
    {
      System.out.println("Error - number entered does not correspond to a contact item");
    } // END catch

  } // END process_remove_nth_item_request()

/********************************* NEXT FUNCTION **************************************/

  private void process_save_contact_list_request(String filename_to_save_as)
  {

    try
    {
      master_contact_list.save_contacts(filename_to_save_as);
      System.out.println("Contact list successfully saved");
    } // END try

    catch(FileNotFoundException ex)
    {
      System.out.println("Error - unable to save - check for legal filename");
    } // END catch

  } // END process_save_contact_list_request()

/********************************* NEXT FUNCTION **************************************/

  private void process_load_contact_list_request(String filename_to_load)
  {

    try
    {
      master_contact_list.load_contacts(filename_to_load);
      System.out.println("Contact list successfully loaded");
      System.out.println("");
      current_menu_state = MenuStateEnum.LIST_OPERATION_MENU;
    } // END try

    catch(InvalidPathException ex)
    {
      System.out.println("Error - unable to load - check for valid filename");
      System.out.println("");
    } // END catch

    // IN CASE A FILE OPENED THAT IS NOT A CONTACT LIST
    catch(UnsupportedEncodingException ex)
    {
      System.out.printf("%s%n", ex.getMessage());
      System.out.println("");
    } // END catch

    catch(IOException ex)
    {
      System.out.println("Error - file not found - unable to load:");
      System.out.printf("%s%n", ex.getMessage());
      System.out.println("");
    } // END catch

  } // END process_load_contact_list_request()

/********************************* NEXT FUNCTION **************************************/

  // DIRECTLY CONTROLS THE VALIDITY OF 'grab_numeric_menu_screen_choice' INHERITED FROM
  // ...ABSTRACT SUPERCLASS
  @Override
  protected boolean is_menu_choice_valid(int menu_choice_number)
  {

    switch(current_menu_state)
    {

      case MAIN_MENU:
        if(menu_choice_number >= 1 && menu_choice_number <= 3)
        {
          return true; // VALID MENU CHOICE
        } // END if

        break;

      /******************************************/

      case LIST_OPERATION_MENU:
        if(menu_choice_number >= 1 && menu_choice_number <= 6)
        {
          return true; // VALID MENU CHOICE
        } // END if

        break;

    } // END switch

    /******************************************/

    // OTHERWISE, INVALID MENU CHOICE
    return false;

  } // END is_menu_choice_valid()

} // END ContactApp CLASS