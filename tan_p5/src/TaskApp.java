/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import java.util.Scanner;
import java.time.LocalDate;

// EXCEPTION IMPORTS
import java.time.format.DateTimeParseException;
import java.nio.file.InvalidPathException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskApp extends ConsoleBasedMenuApp
{

  /***************************************************
    APPLICATION DATA
  ****************************************************/

  private MenuStateEnum current_menu_state;
  private TaskList master_task_list;

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

  public TaskApp()
  {
    master_task_list = new TaskList();
  } // END TaskApp CONSTRUCTOR

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
    void execute_mark_item_as_complete_sub_screen();
    void execute_unmark_completed_item_sub_screen();
    void execute_save_list_sub_screen();
    void execute_load_list_sub_screen();
    void process_add_item_request(String, String, String, boolean);
    void process_edit_nth_item_request(int);
    void process_remove_nth_item_request(int);
    void process_mark_nth_item_as_completed_request(int);
    void process_unmark_nth_completed_item_request(int);
    void process_save_task_list_request(String);
    void process_load_task_list_request(String);
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

  } // END execute_proper_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_main_menu_screen()
  {

    /*******************************************
      LOCAL VARIABLES
    ********************************************/

    int menu_choice_number = -1;

    /**************************************************************/

    System.out.println("Task List Main Menu");
    System.out.println("-------------------");
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
        if(master_task_list.get_number_of_tasks() > 0)
          master_task_list.clear_all_tasks();
        System.out.println("new task list has been created");
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

    System.out.println("Task List Operation Menu");
    System.out.println("---------------------------");
    System.out.println("");

    System.out.println("1) view the list");
    System.out.println("2) add an item");
    System.out.println("3) edit an item");
    System.out.println("4) remove an item");
    System.out.println("5) mark an item as completed");
    System.out.println("6) unmark a completed item");
    System.out.println("7) save the current list");
    System.out.println("8) quit to the main menu (current list will be discarded)");
    System.out.println("");

    System.out.println("Enter a number for your choice:");
    System.out.printf("> ");
    menu_choice_number = grab_numeric_menu_screen_choice();
    System.out.println("");

    /*******************************************************/
    // AT THIS POINT, WE CAN ASSUME A VALID MENU CHOICE BY USER

    switch(menu_choice_number)
    {

      /**************************/
      case 1: // VIEW LIST
      /**************************/
        execute_view_list_sub_screen();
        break;

      /*********************************/
      case 2: // ADD TASK ITEM TO LIST
      /*********************************/
        execute_add_item_sub_screen();
        break;

      /***********************************/
      case 3: // EDIT EXISTING TASK ITEM
      /***********************************/
        execute_edit_item_sub_screen();
        break;

      /**************************************************/
      case 4: // REMOVE TASK ITEM FROM CURRENT TASK LIST
      /**************************************************/
        execute_remove_item_sub_screen();
        break;

      /*************************************/
      case 5: // MARK TASK ITEM AS COMPLETE
      /*************************************/
        execute_mark_item_as_complete_sub_screen();
        break;

      /*************************************/
      case 6: // UNMARK COMPLETED TASK ITEM
      /*************************************/
        execute_unmark_completed_item_sub_screen();
        break;

      /****************************/
      case 7: // SAVE CURRENT LIST
      /****************************/
        execute_save_list_sub_screen();
        break;

      /****************/
      case 8: // QUIT
      /****************/
        current_menu_state = MenuStateEnum.MAIN_MENU;
        master_task_list.clear_all_tasks();
        break;

    } // END switch

  } // END execute_list_operation_menu_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_view_list_sub_screen()
  {

    System.out.println("Current Tasks (sorted by due date)");
    System.out.println("----------------------------------");
    System.out.println("");

    /************************************************/

    // IF NO TASKS TO DISPLAY
    if(master_task_list.get_number_of_tasks() == 0)
    {
      System.out.println("[No tasks]");
    } // END if

    /************************************************/

    // OTHERWISE, THERE ARE TASKS TO DISPLAY
    else
    {
      master_task_list.display_all_tasks();
    } // END else

    /************************************************/

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

    String title;
    String description;
    String due_date;

    /************************************************************/

    System.out.println("Add Task Item");
    System.out.println("-------------");
    System.out.println("");

    System.out.println("Task title:");
    System.out.printf("> ");
    clear_input_buffer();
    title = user_input_scanner.nextLine();

    System.out.println("Task description:");
    System.out.printf("> ");
    clear_input_buffer();
    description = user_input_scanner.nextLine();

    System.out.println("Task due date (YYYY-MM-DD):");
    System.out.printf("> ");
    clear_input_buffer();
    due_date = user_input_scanner.nextLine();

    System.out.println("");

    /************************************************************/

    process_add_item_request(title, description, due_date, false);
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

    System.out.println("Edit Task Item");
    System.out.println("--------------");
    System.out.println("");

    /*****************************************/

    // IF NO TASKS TO DISPLAY
    if(master_task_list.get_number_of_tasks() == 0)
    {
      System.out.println("[No tasks]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************/

    // OTHERWISE, THERE ARE TASKS TO DISPLAY

    master_task_list.display_all_tasks();
    System.out.println("");
    System.out.println("Enter a number corresponding to the task you wish to edit:");
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

    System.out.println("Remove Task Item");
    System.out.println("----------------");
    System.out.println("");

    /*****************************************/

    // IF NO CONTACTS TO DISPLAY
    if(master_task_list.get_number_of_tasks() == 0)
    {
      System.out.println("[No tasks]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************/

    // OTHERWISE, THERE ARE TASKS TO DISPLAY

    master_task_list.display_all_tasks();
    System.out.println("");
    System.out.println("Enter a number corresponding to the task you wish to remove:");
    System.out.printf("> ");

    numeric_input = grab_any_numeric_input();
    System.out.println("");

    /******************************************/

    process_remove_nth_item_request(numeric_input);
    System.out.println("");

  } // END execute_remove_item_sub_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_mark_item_as_complete_sub_screen()
  {

    /********************************************
      LOCAL VARIABLES
    *********************************************/

    int numeric_input;

    /*****************************************************/

    System.out.println("Mark Task Item (tasks shown below are incomplete)");
    System.out.println("-------------------------------------------------");
    System.out.println("");

    /*****************************************/

    // IF THERE ARE NO INCOMPLETE TASKS TO DISPLAY
    if(master_task_list.get_number_of_incomplete_tasks() == 0)
    {
      System.out.println("[No incomplete tasks]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************/

    // OTHERWISE, THERE ARE INCOMPLETE TASKS TO DISPLAY

    master_task_list.display_incomplete_tasks();
    System.out.println("");
    System.out.println("Enter a number corresponding to the task you wish to mark as completed:");
    System.out.printf("> ");

    numeric_input = grab_any_numeric_input();
    System.out.println("");

    /*****************************************/

    process_mark_nth_item_as_completed_request(numeric_input);
    System.out.println("");

  } // END execute_mark_item_as_complete_sub_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_unmark_completed_item_sub_screen()
  {

    /********************************************
      LOCAL VARIABLES
    *********************************************/

    int numeric_input;

    /*****************************************************/

    System.out.println("Unmark Task Item (tasks shown below are completed)");
    System.out.println("--------------------------------------------------");
    System.out.println("");

    /*****************************************/

    // IF THERE ARE NO COMPLETED TASKS TO DISPLAY
    if(master_task_list.get_number_of_completed_tasks() == 0)
    {
      System.out.println("[No completed tasks]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************/

    // OTHERWISE, THERE ARE COMPLETED TASKS TO DISPLAY

    master_task_list.display_completed_tasks();
    System.out.println("");
    System.out.println("Enter a number corresponding to the completed task you wish to unmark:");
    System.out.printf("> ");

    numeric_input = grab_any_numeric_input();
    System.out.println("");

    /*****************************************/

    process_unmark_nth_completed_item_request(numeric_input);
    System.out.println("");

  } // END execute_unmark_completed_item_sub_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_save_list_sub_screen()
  {

    /***********************************************
      LOCAL VARIABLES
    ************************************************/

    String filename_to_save_as;

    /*****************************************************/

    System.out.println("Save Task List");
    System.out.println("--------------");
    System.out.println("");

    /*****************************************************/

    // IF NO TASKS TO SAVE
    if(master_task_list.get_number_of_tasks() == 0)
    {
      System.out.println("[No tasks to save]");
      System.out.println("");

      System.out.println("Provide any input to go back:");
      System.out.printf("> ");

      clear_input_buffer();
      user_input_scanner.nextLine();
      System.out.println("");
      return;
    } // END if

    /*****************************************************/

    // OTHERWISE, THERE ARE TASKS TO SAVE

    if(master_task_list.get_number_of_tasks() == 1)
    {
      System.out.println("[1 task to save]");
      System.out.println("");
    } // END if

    // OTHERWISE, NUMBER OF TASKS IS > 1
    else
    {
      System.out.printf("[%d tasks to save]%n%n", master_task_list.get_number_of_tasks());
    } // END else

    System.out.println("Enter the filename you wish to save your task list as (ex: list.txt)");
    System.out.printf("> ");
    clear_input_buffer();
    filename_to_save_as = user_input_scanner.nextLine();
    System.out.println("");

    process_save_task_list_request(filename_to_save_as);
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

    System.out.println("Load Task List");
    System.out.println("--------------");
    System.out.println("");

    System.out.println("Enter the filename to load:");
    System.out.printf("> ");

    clear_input_buffer();
    filename_to_load = user_input_scanner.nextLine();
    System.out.println("");

    process_load_task_list_request(filename_to_load);

  } // END execute_load_list_sub_screen()

  /********************************* NEXT FUNCTION **************************************/

  private void process_add_item_request(String title, String description, String due_date,
  boolean completion_status)
  {

    try
    {
      master_task_list.add_task(title, description, due_date, completion_status);
      System.out.println("Task successfully added");
    } // END try

    /********************************************************/

    catch(IllegalArgumentException ex)
    {

      System.out.printf("%s%n", ex.getMessage());

      // IN CASE USER INPUT IS ILLEGAL FOR DUE DATE AS WELL
      try
      {
        LocalDate.parse(due_date);
      } // END try

      catch(DateTimeParseException ex2)
      {
        System.out.println("Error - illegal date - check format and numeric values");
      } // END catch

    } // END catch

    /********************************************************/

    catch(DateTimeParseException ex)
    {
      System.out.println("Error - illegal date - check format and numeric values");
    } // END catch

  } // END process_add_item_request()

  /********************************* NEXT FUNCTION **************************************/

  private void process_edit_nth_item_request(int numeric_input)
  {

    /************************************************
      LOCAL VARIABLES
    *************************************************/

    String title_input;
    String description_input;
    String due_date_input;

    /************************************************************/

    try
    {

      // SEE IF NUMERIC INPUT IS A VALID NUMBER FOR OUR TASK LIST
      master_task_list.get_nth_title(numeric_input);

      /***********************************************************/
      // PROCESS TITLE EDIT
      System.out.println("Enter a new title:");
      System.out.printf("> ");
      clear_input_buffer();
      title_input = user_input_scanner.nextLine();

      master_task_list.edit_nth_title(numeric_input, title_input);
      System.out.println("Title successfully edited");
      System.out.println("");
      /***********************************************************/

      /***********************************************************/
      // PROCESS DESCRIPTION EDIT
      System.out.println("Enter a new description:");
      System.out.printf("> ");
      clear_input_buffer();
      description_input = user_input_scanner.nextLine();

      master_task_list.edit_nth_description(numeric_input, description_input);
      System.out.println("Description successfully edited");
      System.out.println("");
      /***********************************************************/

      /***********************************************************/
      // PROCESS DUE DATE EDIT
      System.out.println("Enter a new task due date (YYYY-MM-DD):");
      System.out.printf("> ");
      clear_input_buffer();
      due_date_input = user_input_scanner.nextLine();

      master_task_list.edit_nth_due_date(numeric_input, due_date_input);
      System.out.println("Task due date successfully edited");
      /***********************************************************/

    } // END try

    // IN CASE USER ENTERS A NUMBER THAT DOES NOT CORRESPOND TO A TASK ITEM
    catch(IndexOutOfBoundsException ex)
    {
      System.out.println("");
      System.out.println("Error - number entered does not correspond to a task item");
    } // END catch

    // IN CASE OF ILLEGAL TITLE
    catch(IllegalArgumentException ex)
    {
      System.out.println("");
      System.out.printf("%s%n", ex.getMessage());
    } // END catch

    // IN CASE OF ILLEGAL DUE DATE
    catch(DateTimeParseException ex)
    {
      System.out.println("");
      System.out.println("Error - illegal date - check format and numeric values");
    } // END catch

  } // END process_edit_nth_item_request()

  /********************************* NEXT FUNCTION **************************************/

  private void process_remove_nth_item_request(int numeric_input)
  {

    try
    {
      master_task_list.delete_nth_task(numeric_input);
      System.out.println("Task item successfully removed");
    } // END try

    catch(IndexOutOfBoundsException ex)
    {
      System.out.println("Error - number entered does not correspond to a task item");
    } // END catch

  } // END process_remove_nth_item_request()

  /********************************* NEXT FUNCTION **************************************/

  private void process_mark_nth_item_as_completed_request(int numeric_input)
  {

    try
    {
      master_task_list.edit_nth_completion_status(numeric_input, true);
      System.out.println("Task item successfully marked as completed");
    } // END try

    catch(IndexOutOfBoundsException ex)
    {
      System.out.println("Error - number entered does not correspond to a task item");
    } // END catch

    catch(IllegalArgumentException ex)
    {
      System.out.println("Error - number entered does not correspond to an incomplete task item");
    } // END catch

  } // END process_mark_nth_item_as_completed_request()

  /********************************* NEXT FUNCTION **************************************/

  private void process_unmark_nth_completed_item_request(int numeric_input)
  {

    try
    {
      master_task_list.edit_nth_completion_status(numeric_input, false);
      System.out.println("Task item successfully unmarked");
    } // END try

    catch(IndexOutOfBoundsException ex)
    {
      System.out.println("Error - number entered does not correspond to a task item");
    } // END catch

    catch(IllegalArgumentException ex)
    {
      System.out.println("Error - number entered does not correspond to a completed task item");
    } // END catch

  } // END process_unmark_nth_completed_item_request()

  /********************************* NEXT FUNCTION **************************************/

  private void process_save_task_list_request(String filename_to_save_as)
  {

    try
    {
      master_task_list.save_tasks(filename_to_save_as);
      System.out.println("Task list successfully saved");
    } // END try

    catch(FileNotFoundException ex)
    {
      System.out.println("Error - unable to save - check for legal filename");
    } // END catch

  } // END process_save_task_list_request()

  /********************************* NEXT FUNCTION **************************************/

  private void process_load_task_list_request(String filename_to_load)
  {

    try
    {
      master_task_list.load_tasks(filename_to_load);
      System.out.println("Task list successfully loaded");
      System.out.println("");
      current_menu_state = MenuStateEnum.LIST_OPERATION_MENU;
    } // END try

    catch(InvalidPathException ex)
    {
      System.out.println("Error - unable to load - check for valid filename");
      System.out.println("");
    } // END catch

    // IN CASE A FILE OPENED THAT IS NOT A TASK LIST
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

  } // END process_load_task_list_request()

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
        if(menu_choice_number >= 1 && menu_choice_number <= 8)
        {
          return true; // VALID MENU CHOICE
        } // END if

        break;

    } // END switch

    /******************************************/

    // OTHERWISE, INVALID MENU CHOICE
    return false;

  } // END is_menu_choice_valid()

} // END TaskApp CLASS