/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 4
*************************************/

import java.util.Scanner;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;

// EXCEPTION IMPORTS
import java.util.InputMismatchException;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;

public class App
{

  /************************************************************************************
  |  FUNCTION PROTOTYPES                                                              |
  |  -------------------                                                              |
  |                                                                                   |
  |  void clear_input_scanner_buffer();                                               |
  |                                                                                   |
  |  <VALIDATION FUNCTIONS>                                                           |
  |  boolean validate_main_menu_selection();                                          |
  |  boolean validate_list_operation_menu_selection();                                |
  |  boolean is_menu_selection_valid();                                               |
  |  boolean is_numeric_selection_valid(int, int, int);                               |
  |  boolean is_numeric_selection_valid_from_these_numbers(int, ArrayList<Integer>);  |
  |  boolean is_task_title_valid(String);                                             |
  |                                                                                   |
  |  <INFORMATION RETRIEVAL FUNCTIONS>                                                |
  |  ArrayList<Integer> get_indices_of_incomplete_tasks(TaskList);                    |
  |  ArrayList<Integer> get_indices_of_completed_tasks(TaskList);                     |
  |                                                                                   |
  |  <INPUT RETRIEVAL FUNCTIONS>                                                      |
  |  void get_numeric_menu_selection();                                               |
  |  void get_numeric_selection(String, int, int);                                    |
  |  void get_numeric_selection_from_these_numbers(String, ArrayList<Integer>);       |
  |  void get_single_string();                                                        |
  |  void get_title_description_date(String, String, String);                         |
  |  void get_proper_input();                                                         |
  |                                                                                   |
  |  <PROCESSING FUNCTIONS>                                                           |
  |  void process_main_menu_input();                                                  |
  |  boolean determine_boolean_from_string(String);                                   |
  |  void load_task_list_from_file(Scanner);                                          |
  |  void process_load_menu_input();                                                  |
  |  void process_list_operation_menu_input();                                        |
  |  void process_add_item_menu_input();                                              |
  |  void process_edit_item_menu_input();                                             |
  |  void process_remove_item_menu_input();                                           |
  |  void process_mark_item_menu_input();                                             |
  |  void process_unmark_item_menu_input();                                           |
  |  void process_save_list_menu_input();                                             |
  |  void process_input();                                                            |
  |                                                                                   |
  |  <DISPLAY FUNCTIONS>                                                              |
  |  int display_all_tasks();                                                         |
  |  int display_incomplete_tasks(boolean);                                           |
  |  int display_completed_tasks(boolean);                                            |
  |  void display_numeric_selection_request();                                        |
  |  void display_main_menu();                                                        |
  |  void display_list_operation_menu();                                              |
  |  void display_list_view_menu();                                                   |
  |  void display_add_item_menu();                                                    |
  |  void display_edit_item_menu();                                                   |
  |  void display_remove_item_menu();                                                 |
  |  void display_mark_item_menu();                                                   |
  |  void display_unmark_item_menu();                                                 |
  |  void display_save_list_menu();                                                   |
  |  void display_load_menu();                                                        |
  |  void display_proper_menu();                                                      |
  |                                                                                   |
  |  void clean_up();                                                                 |
  ************************************************************************************/

  /*****************************************************************
    APPLICATION CONSTANTS
  ******************************************************************/

  private static final int MAX_MAIN_MENU_OPTIONS = 3;
  private static final int MAX_LIST_OPERATION_MENU_OPTIONS = 8;
  private static final String FILE_FIELD_PARTITION = "<~PARTITION~>";

  /*****************************************************************
    PRIVATE DATA
  ******************************************************************/

  private static boolean is_program_still_running = true;
  private static boolean file_load_success_boolean;

  private static MenuStateEnum menu_state = MenuStateEnum.MAIN_MENU;
  private static int numeric_menu_selection = -1;

  private static Scanner user_input_scanner = new Scanner(System.in);

  private static final TaskList task_list = new TaskList();

  private static String title_data = "";
  private static String description_data = "";
  private static String due_date_data = "";

  /*****************************************************************
    HELPER FUNCTIONS
  ******************************************************************/

  private static void clear_input_scanner_buffer()
  {
      user_input_scanner = new Scanner(System.in);
  } // END clear_input_scanner_buffer()

  /************************************* NEXT FUNCTION **************************************/

  private static boolean validate_main_menu_selection()
  {
    if(numeric_menu_selection >= 1 && numeric_menu_selection <= MAX_MAIN_MENU_OPTIONS)
    {
      return true;
    } // END if

    return false;
  } // END validate_main_menu_selection()

  /************************************* NEXT FUNCTION **************************************/

  private static boolean validate_list_operation_menu_selection()
  {
    if(numeric_menu_selection >= 1 && numeric_menu_selection <= MAX_LIST_OPERATION_MENU_OPTIONS)
    {
      return true;
    } // END if

    return false;
  } // END validate_list_operation_menu_selection()

  /************************************* NEXT FUNCTION **************************************/

  private static boolean is_menu_selection_valid()
  {

    boolean validation_boolean = false;

    /************************************************/

    switch(menu_state)
    {

      case MAIN_MENU:
        validation_boolean = validate_main_menu_selection();
        break;

      case LIST_OPERATION_MENU:
        validation_boolean = validate_list_operation_menu_selection();
        break;

      case LIST_VIEW_MENU:
        validation_boolean = true; // NO VALIDATION REQUIRED, ANY INPUT ACCEPTED
        break;

      case EDIT_ITEM_MENU:
        validation_boolean = true; // NO VALIDATION REQUIRED, ANY INPUT ACCEPTED WHEN NO ITEMS TO EDIT
        break;

      case REMOVE_ITEM_MENU:
        validation_boolean = true; // NO VALIDATION REQUIRED, ANY INPUT ACCEPTED WHEN NO ITEMS TO REMOVE
        break;

      case MARK_ITEM_MENU:
        validation_boolean = true; // NO VALIDATION REQUIRED, ANY INPUT ACCEPTED WHEN NO ITEMS TO MARK
        break;

      case UNMARK_ITEM_MENU:
        validation_boolean = true; // NO VALIDATION REQUIRED, ANY INPUT ACCEPTED WHEN NO ITEMS TO UNMARK
        break;

    } // END switch

    /************************************************/

    if(!validation_boolean)
      System.out.println("Invalid menu selection");

    return validation_boolean;

  } // END is_menu_selection_valid()

  /************************************* NEXT FUNCTION **************************************/

  private static boolean is_numeric_selection_valid(int number_to_test, int valid_lower_value, int valid_upper_value)
  {

    if(number_to_test >= valid_lower_value && number_to_test <= valid_upper_value)
    {
      return true;
    } // END if

    return false;

  } // END is_numeric_selection_valid()

  /************************************* NEXT FUNCTION **************************************/

  // TESTS TO SEE IF 'number_to_test' IS CONTAINED WITHIN 'these_numbers'
  private static boolean is_numeric_selection_valid_from_these_numbers(int number_to_test, ArrayList<Integer> these_numbers)
  {

    return these_numbers.contains(Integer.valueOf(number_to_test));

  } // END is_numeric_selection_valid_from_these_numbers()

  /************************************* NEXT FUNCTION **************************************/

  private static boolean is_task_title_valid(String title)
  {
    if(title.length() >= 1)
    {
      return true;
    } // END if

    return false;
  } // END is_task_title_valid()

  /************************************* NEXT FUNCTION **************************************/

  private static ArrayList<Integer> get_indices_of_incomplete_tasks(TaskList task_list_src)
  {

    /*********************************************
      VARIABLES
    **********************************************/

    ArrayList<Integer> indices_arraylist = new ArrayList<Integer>();
    int total_number_of_tasks;
    TaskItem current_task_item;

    /**************************************************************/

    total_number_of_tasks = task_list_src.get_number_of_task_items();

    for(int i = 0; i < total_number_of_tasks; ++i)
    {

      current_task_item = task_list_src.get_nth_task_item(i);

      // IF CURRENT TASK IS COMPLETED
      if(current_task_item.get_task_completion())
      {
        continue;
      } // END if

      // OTHERWISE, CURRENT TASK IS NOT COMPLETED

      indices_arraylist.add(i); // REMEMBER THE INCOMPLETE TASK'S INDEX

    } // END for LOOP

    return indices_arraylist;

  } // END get_indices_of_incomplete_tasks()

  /************************************* NEXT FUNCTION **************************************/

  private static ArrayList<Integer> get_indices_of_completed_tasks(TaskList task_list_src)
  {

    /*********************************************
      VARIABLES
    **********************************************/

    ArrayList<Integer> indices_arraylist = new ArrayList<Integer>();
    int total_number_of_tasks;
    TaskItem current_task_item;

    /**************************************************************/

    total_number_of_tasks = task_list_src.get_number_of_task_items();

    for(int i = 0; i < total_number_of_tasks; ++i)
    {

      current_task_item = task_list_src.get_nth_task_item(i);

      // IF CURRENT TASK IS COMPLETED
      if(current_task_item.get_task_completion())
      {
        indices_arraylist.add(i); // REMEMBER THE COMPLETED TASK'S INDEX
      } // END if

      // OTHERWISE, CURRENT TASK IS NOT COMPLETED
      continue;

    } // END for LOOP

    return indices_arraylist;

  } // END get_indices_of_completed_tasks()

  /************************************* NEXT FUNCTION **************************************/

  private static void get_numeric_menu_selection()
  {

    clear_input_scanner_buffer();

    do
    {

      // TRY AND GET VALID INPUT
      try
      {
        System.out.printf("> ");
        numeric_menu_selection = user_input_scanner.nextInt();
        clear_input_scanner_buffer();
      } // END try

      /************************************************************/

      // INTEGER NOT PROVIDED BY USER
      catch(InputMismatchException ex)
      {
        // LIST VIEW MENU AND OTHER MENUS IN CERTAIN CIRCUMSTANCES CAN HAVE ANY INPUT
        if(menu_state != MenuStateEnum.LIST_VIEW_MENU && menu_state != MenuStateEnum.EDIT_ITEM_MENU
           && menu_state != MenuStateEnum.REMOVE_ITEM_MENU && menu_state != MenuStateEnum.MARK_ITEM_MENU
           && menu_state != MenuStateEnum.UNMARK_ITEM_MENU)
        {
          System.out.println("Input was not an integer");
        } // END if

        clear_input_scanner_buffer(); // GET RID OF INVALID INPUT
        numeric_menu_selection = -1;
      } // END catch

    }
    while(!is_menu_selection_valid());

  } // END get_numeric_menu_selection()

  /************************************* NEXT FUNCTION **************************************/

  // GETS NUMERIC INPUT FROM USER AND VERIFIES THAT IT IS IN THE INTEVAL OF [valid_lower_value, valid_upper_value]
  // IF INPUT IS INVALID, 'error_msg' IS DISPLAYED TO USER
  private static void get_numeric_selection(String error_msg, int valid_lower_value, int valid_upper_value)
  {

    /*******************************************
      VARIABLES
    ********************************************/

    boolean selection_validity_boolean = true;

    /*******************************************************/

    clear_input_scanner_buffer();

    do
    {

      if(!selection_validity_boolean)
        System.out.println(error_msg);

      try
      {
        System.out.printf("> ");
        numeric_menu_selection = user_input_scanner.nextInt();
        clear_input_scanner_buffer();
      } // END try

      /****************************************************/

      catch(InputMismatchException ex)
      {
        System.out.println("Input was not an integer");
        clear_input_scanner_buffer();
        numeric_menu_selection = -1;
      } // END catch

      selection_validity_boolean = is_numeric_selection_valid(numeric_menu_selection, valid_lower_value, valid_upper_value);

    }
    while(!selection_validity_boolean);

  } // END get_numeric_selection()

  /************************************* NEXT FUNCTION **************************************/

  private static void get_numeric_selection_from_these_numbers(String error_msg, ArrayList<Integer> these_numbers)
  {

    /*******************************************
      VARIABLES
    ********************************************/

    boolean selection_validity_boolean = true;

    /*******************************************************/

    clear_input_scanner_buffer();

    do
    {

      if(!selection_validity_boolean)
        System.out.println(error_msg);

      try
      {
        System.out.printf("> ");
        numeric_menu_selection = user_input_scanner.nextInt();
        clear_input_scanner_buffer();
      } // END try

      /****************************************************/

      catch(InputMismatchException ex)
      {
        System.out.println("Input was not an integer");
        clear_input_scanner_buffer();
        numeric_menu_selection = -1;
      } // END catch

      selection_validity_boolean = is_numeric_selection_valid_from_these_numbers(numeric_menu_selection, these_numbers);

    }
    while(!selection_validity_boolean);

  } // END get_numeric_selection_from_these_numbers()

  /************************************* NEXT FUNCTION **************************************/

  private static void get_single_string()
  {

    clear_input_scanner_buffer();

    System.out.printf("> ");
    title_data = user_input_scanner.nextLine();

  } // END get_single_string()

  /************************************* NEXT FUNCTION **************************************/

  // GETS TASK TITLE, TASK DESCRIPTION, AND TASK DUE DATE FROM USER INPUT
  private static void get_title_description_date(String title_request_msg, String description_request_msg, String due_date_request_msg)
  {

    System.out.println(title_request_msg);
    System.out.printf("> ");
    title_data = user_input_scanner.nextLine();

    /*****************************************************/

    System.out.println(description_request_msg);
    System.out.printf("> ");
    description_data = user_input_scanner.nextLine();

    /*****************************************************/

    System.out.println(due_date_request_msg);
    System.out.printf("> ");
    due_date_data = user_input_scanner.nextLine();

  } // END get_title_description_date()

  /************************************* NEXT FUNCTION **************************************/

  private static void get_proper_input()
  {

    /*****************************************
      VARIABLES
    ******************************************/

    int number_of_tasks;
    ArrayList<Integer> integer_arraylist;

    /************************************************/

    switch(menu_state)
    {

      case MAIN_MENU:
        get_numeric_menu_selection();
        break;

      /**************************************/

      case LOAD_MENU:
        get_single_string();
        break;

      /**************************************/

      case LIST_OPERATION_MENU:
        get_numeric_menu_selection();
        break;

      /**************************************/

      case LIST_VIEW_MENU:
        get_numeric_menu_selection(); // GET ANY INPUT TO EXIT LIST VIEW
        break;

      /**************************************/

      case ADD_ITEM_MENU:
        get_title_description_date("Task title:", "Task description:", "Task due date (YYYY-MM-DD):");
        break;

      /**************************************/

      case EDIT_ITEM_MENU:
        number_of_tasks = task_list.get_number_of_task_items();

        // IF NO TASKS ARE BEING DISPLAYED
        if(number_of_tasks == 0)
        {
          get_numeric_menu_selection(); // GET ANY INPUT TO EXIT MENU
          break;
        } // END if

        // OTHERWISE, AT LEAST ONE TASK IS BEING DISPLAYED
        get_numeric_selection("Invalid task number", 0, (number_of_tasks - 1));
        get_title_description_date("New task title:", "New task description", "New task due date (YYYY-MM-DD):");
        break;

      /**************************************/

      case REMOVE_ITEM_MENU:
        number_of_tasks = task_list.get_number_of_task_items();

        // IF NO TASKS ARE BEING DISPLAYED
        if(number_of_tasks == 0)
        {
          get_numeric_menu_selection(); // GET ANY INPUT TO EXIT MENU
          break;
        } // END if

        // OTHERWISE, AT LEAST ONE TASK IS BEING DISPLAYED
        get_numeric_selection("Invalid task number", 0, (number_of_tasks - 1));
        break;

      /**************************************/

      case MARK_ITEM_MENU:

        // GET NUMBER OF INCOMPLETE TASKS IN TASK LIST WITHOUT DISPLAYING THEM
        number_of_tasks = display_incomplete_tasks(false);

        // IF NO TASKS ARE BEING DISPLAYED
        if(number_of_tasks == 0)
        {
          get_numeric_menu_selection(); // GET ANY INPUT TO EXIT MENU
          break;
        } // END if

        // OTHERWISE, AT LEAST ONE TASK IS BEING DISPLAYED
        integer_arraylist = get_indices_of_incomplete_tasks(task_list);
        get_numeric_selection_from_these_numbers("Invalid task number", integer_arraylist);
        break;

      /**************************************/

      case UNMARK_ITEM_MENU:

        // GET NUMBER OF COMPLETED TASKS IN TASK LIST WITHOUT DISPLAYING THEM
        number_of_tasks = display_completed_tasks(false);

        // IF NO TASKS ARE BEING DISPLAYED
        if(number_of_tasks == 0)
        {
          get_numeric_menu_selection(); // GET ANY INPUT TO EXIT MENU
          break;
        } // END if

        // OTHERWISE, AT LEAST ONE TASK IS BEING DISPLAYED
        integer_arraylist = get_indices_of_completed_tasks(task_list);
        get_numeric_selection_from_these_numbers("Invalid task number", integer_arraylist);
        break;

      /**************************************/

      case SAVE_LIST_MENU:
        get_single_string();
        break;

    } // END switch

  } // END get_proper_input()

  /************************************* NEXT FUNCTION **************************************/

  // THIS FUNCTION ASSUMES THAT A VALID CHOICE HAS BEEN INPUT FOR THE MAIN MENU
  private static void process_main_menu_input()
  {

    switch(numeric_menu_selection)
    {

      case 1:
        task_list.delete_all_task_items();
        System.out.println("New task list has been created");
        break;

      /*************************************************/

      case 2:
        break; // NO PROCESSING NEEDED

      /*************************************************/

      case 3:
        is_program_still_running = false;
        System.out.println("Quitting program");
        break;

    } // END switch

  } // END process_main_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  // FUNCTION ASSUMES 'number_string' TO REPRESENT A NUMBER
  private static boolean determine_boolean_from_string(String number_string)
  {

    if( (number_string.stripTrailing()).equals("0") )
    {
      return false;
    } // END if

    // OTHERWISE, NON-ZERO VALUE
    else
    {
      return true;
    } // END else

  } // END determine_boolean_from_string()

  /************************************* NEXT FUNCTION **************************************/

  // FUNCTION ASSUMES THAT 'file_scanner' HAS BEEN INITIALIZED TO AN APPROPRIATE OUTPUT FILE FOR TASK ITEMS
  private static void load_task_list_from_file(Scanner file_scanner)
  {

    /**********************************************
      VARIABLES
    ***********************************************/

    String current_title;
    String current_description;
    String current_due_date;
    boolean current_completion_status;

    String current_line;
    String[] string_array;

    /***************************************************************/

    // EMPTY CURRENT TASK LIST IF THERE'S ANY EXISTING DATA
    if(task_list.get_number_of_task_items() > 0)
      task_list.delete_all_task_items();

    /***************************************************************/

    // SWEEP THROUGH ENTIRE OUTPUT FILE
    while(file_scanner.hasNext())
    {

      current_line = file_scanner.nextLine();

      // IF THERE'S A BLANK LINE, SKIP IT
      if(current_line.isBlank())
        continue;

      /******************************************/

      // AT THIS POINT, 'current_line' CONTAINS INFORMATION

      // SEPARATE TASK LIST FIELDS FROM 'current_line'
      string_array = current_line.split(FILE_FIELD_PARTITION);

      // IF 'current_line' CONTAINED A TASK DESCRIPTION
      if(string_array.length == 4)
      {
        current_title = string_array[0];
        current_description = string_array[1];
        current_due_date = string_array[2];
        current_completion_status = determine_boolean_from_string(string_array[3]);
      }

      // OTHERWISE, 'current_line' HAD NO TASK DESCRIPTION
      else
      {
        current_title = string_array[0];
        current_description = "";
        current_due_date = string_array[1];
        current_completion_status = determine_boolean_from_string(string_array[2]);
      } // END else if

      task_list.add_task_item(current_title, current_description, current_due_date, current_completion_status);

    } // END while LOOP

  } // END load_task_list_from_file()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_load_menu_input()
  {

    try(Scanner file_scanner = new Scanner(Paths.get(title_data)))
    {
      load_task_list_from_file(file_scanner);
      System.out.println("Task list has been loaded");
      file_load_success_boolean = true;
    } // END try

    /*****************************************************/

    // FILE LOAD ATTEMPT FAILED
    catch(InvalidPathException | IOException ex)
    {
      System.out.println("ERROR! Task list was unable to load. Check filename.");
      file_load_success_boolean = false;
    } // END catch

  } // END process_load_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_list_operation_menu_input()
  {

    switch(numeric_menu_selection)
    {

      case 1:
        break; // NO PROCESSING NEEDED

      case 2:
        break; // NO PROCESSING NEEDED

      case 3:
        break; // NO PROCESSING NEEDED

      case 4:
        break; // NO PROCESSING NEEDED

      case 5:
        break; // NO PROCESSING NEEDED

      case 6:
        break; // NO PROCESSING NEEDED

      case 7:
        break; // NO PROCESSING NEEDED

      case 8:
        break; // NO PROCESSING NEEDED

    } // END switch

  } // END process_list_operation_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_add_item_menu_input()
  {

    boolean invalid_boolean = false;

    // VALIDATE TITLE
    if(title_data.length() < 1)
    {
      System.out.println("WARNING! Title must be at least 1 character long");
      invalid_boolean = true;
    } // END if

    /************************************************/

    // VALIDATE DUE DATE
    try
    {
      LocalDate local_date_ref = LocalDate.parse(due_date_data);
    } // END try

    catch(DateTimeParseException ex)
    {
      System.out.println("WARNING! Invalid due date");
      invalid_boolean = true;
    } // END catch

    /************************************************/

    // IF INVALID INPUT WAS COLLECTED
    if(invalid_boolean)
    {
      System.out.println("Error - task not created");
      return; // DO NOT ADD TASK ITEM
    } // END if

    /************************************************/

    // AT THIS POINT, VALID INPUT IS CONFIRMED

    task_list.add_task_item(title_data, description_data, due_date_data, false);
    System.out.println("Success - task created");

  } // END process_add_item_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_edit_item_menu_input()
  {

    // IF THERE ARE NO TASKS TO EDIT
    if(task_list.get_number_of_task_items() == 0)
    {
      return; // DO NOTHING, NO PROCESSING REQUIRED
    } // END if

    /************************************************/

    boolean invalid_boolean = false;

    // VALIDATE TITLE
    if(title_data.length() < 1)
    {
      System.out.println("WARNING! Title must be at least 1 character long");
      invalid_boolean = true;
    } // END if

    /************************************************/

    // VALIDATE DUE DATE
    try
    {
      LocalDate local_date_ref = LocalDate.parse(due_date_data);
    } // END try

    catch(DateTimeParseException ex)
    {
      System.out.println("WARNING! Invalid due date");
      invalid_boolean = true;
    } // END catch

    /************************************************/

    // IF INVALID INPUT WAS COLLECTED
    if(invalid_boolean)
    {
      System.out.println("Error - could not edit task");
      return; // DO NOT EDIT TASK ITEM
    } // END if

    /************************************************/

    // AT THIS POINT, VALID INPUT IS CONFIRMED

    boolean old_boolean_value = task_list.get_nth_task_item(numeric_menu_selection).get_task_completion();

    // MAKE NEW TASK ITEM WITH EDITED INFO + SAME BOOLEAN VALUE
    TaskItem task_item_edit = new TaskItem(title_data, description_data, due_date_data, old_boolean_value);

    task_list.replace_nth_task_item(numeric_menu_selection, task_item_edit);

    System.out.println("Success - task edited");

  } // END process_edit_item_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_remove_item_menu_input()
  {

    // IF THERE ARE NO TASKS TO REMOVE
    if(task_list.get_number_of_task_items() == 0)
    {
      return; // DO NOTHING, NO PROCESSING REQUIRED
    } // END if

    /************************************************/

    task_list.delete_nth_task_item(numeric_menu_selection);
    System.out.println("Task removed");

  } // END process_remove_item_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_mark_item_menu_input()
  {

    // GET NUMBER OF INCOMPLETE TASKS WITHOUT DISPLAYING THEM
    int number_of_incomplete_tasks = display_incomplete_tasks(false);

    // IF THERE ARE NO INCOMPLETE TASKS TO MARK
    if(number_of_incomplete_tasks == 0)
    {
      return; // DO NOTHING, NO PROCESSING REQUIRED
    } // END if

    /*****************************************************/

    (task_list.get_nth_task_item(numeric_menu_selection)).set_task_completion(true); // MARK TASK AS COMPLETE
    System.out.println("Task marked as complete (***)");

  } // END process_mark_item_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_unmark_item_menu_input()
  {

    // GET NUMBER OF COMPLETED TASKS WITHOUT DISPLAYING THEM
    int number_of_completed_tasks = display_completed_tasks(false);

    // IF THERE ARE NO COMPLETED TASKS TO UNMARK
    if(number_of_completed_tasks == 0)
    {
      return; // DO NOTHING, NO PROCESSING REQUIRED
    } // END if

    /*****************************************************/

    (task_list.get_nth_task_item(numeric_menu_selection)).set_task_completion(false); // UNMARK TASK AS INCOMPLETE
    System.out.println("Task unmarked");

  } // END process_unmark_item_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_save_list_menu_input()
  {

    /**********************************************
      VARIABLES
    ***********************************************/

    TaskItem current_task_item;
    int total_number_of_tasks;

    String current_task_title;
    String current_task_description;
    String current_task_due_date;
    int current_completion_status;

    Formatter file_output_formatter = null;

    /********************************************************/

    total_number_of_tasks = task_list.get_number_of_task_items();

    // IF THERE'S NOTHING TO SAVE
    if(total_number_of_tasks == 0)
    {
      System.out.println("No data to save - Saving operation aborted");
      return;
    } // END if

    /********************************************************/

    // AT THIS POINT, THERE IS SOMETHING TO TRY AND SAVE
    try
    {

      file_output_formatter = new Formatter(title_data);

      // WRITE ALL TASK ITEMS TO OUTPUT FILE
      for(int i = 0; i < total_number_of_tasks; ++i)
      {

        current_task_item = task_list.get_nth_task_item(i);

        current_task_title = current_task_item.get_task_title();
        current_task_description = current_task_item.get_task_description();
        current_task_due_date = current_task_item.get_task_due_date();

        // IF CURRENT TASK IS MARKED AS COMPLETE
        if(current_task_item.get_task_completion())
        {
          current_completion_status = 1;
        } // END if

        // OTHERWISE, CURRENT TASK IS MARKED AS INCOMPLETE
        else
        {
          current_completion_status = 0;
        }

        /**********************************************************/

        // IF TASK DESCRIPTION IS NOT BLANK, INCLUDE IT IN OUTPUT FILE
        if(!current_task_description.isBlank())
        {

          // MAKE EACH LINE IN OUTPUT TEXT FILE A TASK ITEM
          // LINE WILL BE IN ORDER OF: TITLE, DESCRIPTION, DUE DATE, COMPLETION STATUS
          file_output_formatter.format("%s%s%s%s%s%s%d%s", current_task_title, FILE_FIELD_PARTITION,
          current_task_description, FILE_FIELD_PARTITION, current_task_due_date, FILE_FIELD_PARTITION,
          current_completion_status, System.lineSeparator());

        } // END if

        // OTHERWISE, DESCRIPTION IS BLANK, DO NOT INCLUDE IT IN OUTPUT FILE
        else
        {

          // MAKE EACH LINE IN OUTPUT TEXT FILE A TASK ITEM
          // LINE WILL BE IN ORDER OF: TITLE, DUE DATE, COMPLETION STATUS
          file_output_formatter.format("%s%s%s%s%d%s", current_task_title, FILE_FIELD_PARTITION,
          current_task_due_date, FILE_FIELD_PARTITION, current_completion_status, System.lineSeparator());

        } // END else

      } // END for LOOP

      System.out.println("Task list has been saved");

    } // END try

    /********************************************************/

    catch(FileNotFoundException ex)
    {
      System.out.println("Unable to save - Check that entered filename is legal");
    } // END catch

    /********************************************************/

    finally
    {
      if(file_output_formatter != null)
        file_output_formatter.close(); // RELEASE RESOURCES
    } // END finally

  } // END process_save_list_menu_input()

  /************************************* NEXT FUNCTION **************************************/

  private static void process_input()
  {

    switch(menu_state)
    {

      case MAIN_MENU:
        process_main_menu_input();
        break;

      case LOAD_MENU:
        process_load_menu_input();
        break;

      case LIST_OPERATION_MENU:
        process_list_operation_menu_input();
        break;

      case LIST_VIEW_MENU:
        break; // NO PROCESSING NEEDED

      case ADD_ITEM_MENU:
        process_add_item_menu_input();
        break;

      case EDIT_ITEM_MENU:
        process_edit_item_menu_input();
        break;

      case REMOVE_ITEM_MENU:
        process_remove_item_menu_input();
        break;

      case MARK_ITEM_MENU:
        process_mark_item_menu_input();
        break;

      case UNMARK_ITEM_MENU:
        process_unmark_item_menu_input();
        break;

      case SAVE_LIST_MENU:
        process_save_list_menu_input();
        break;

    } // END switch

    System.out.println("");

  } // END process_input()

  /************************************* NEXT FUNCTION **************************************/

  // DISPLAYS ALL TASKS IN THE TASK LIST
  // RETURNS THE NUMBER OF TASKS THAT WERE DISPLAYED
  private static int display_all_tasks()
  {

    /*******************************************
      VARIABLES
    ********************************************/

    TaskItem current_task_item;
    int number_of_tasks = task_list.get_number_of_task_items();

    /**********************************************************/

    // DISPLAY ALL TASKS
    for(int i = 0; i < number_of_tasks; ++i)
    {
      current_task_item = task_list.get_nth_task_item(i);
      System.out.printf("%d) %s%s", i, current_task_item, System.lineSeparator());
    } // END for LOOP

    return number_of_tasks;

  } // END display_all_tasks()

  /************************************* NEXT FUNCTION **************************************/

  // PARAMETER 'to_display_boolean' CAN BE SET TO FALSE IF ONE WISHES ONLY FOR THE RETURN VALUE
  // RETURN VALUE GIVES THE NUMBER OF INCOMPLETE TASKS IN THE TASK LIST
  private static int display_incomplete_tasks(boolean to_display_boolean)
  {

    /***********************************************
      VARIABLES
    ************************************************/

    int number_of_incomplete_tasks = 0;
    int total_number_of_tasks;
    TaskItem current_task_item;

    /*********************************************************/

    total_number_of_tasks = task_list.get_number_of_task_items();

    for(int i = 0; i < total_number_of_tasks; ++i)
    {

      current_task_item = task_list.get_nth_task_item(i);

      // IF THE TASK IS COMPLETED
      if(current_task_item.get_task_completion())
      {
        continue;
      } // END if

      /**********************************************/

      // OTHERWISE, TASK IS NOT COMPLETED

      if(to_display_boolean)
      {
        System.out.printf("%d) %s%s", i, current_task_item, System.lineSeparator());
      } // END if

      ++number_of_incomplete_tasks;

    } // END for LOOP

    return number_of_incomplete_tasks;

  } // END display_incomplete_tasks()

  /************************************* NEXT FUNCTION **************************************/

  // PARAMETER 'to_display_boolean' CAN BE SET TO FALSE IF ONE WISHES ONLY FOR THE RETURN VALUE
  // RETURN VALUE GIVES THE NUMBER OF COMPLETED TASKS IN THE TASK LIST
  private static int display_completed_tasks(boolean to_display_boolean)
  {

    /***********************************************
      VARIABLES
    ************************************************/

    int number_of_complete_tasks = 0;
    int total_number_of_tasks;
    TaskItem current_task_item;

    /*********************************************************/

    total_number_of_tasks = task_list.get_number_of_task_items();

    for(int i = 0; i < total_number_of_tasks; ++i)
    {

      current_task_item = task_list.get_nth_task_item(i);

      // IF THE TASK IS NOT COMPLETED
      if( !(current_task_item.get_task_completion()) )
      {
        continue;
      } // END if

      /**********************************************/

      // OTHERWISE, TASK IS COMPLETED

      if(to_display_boolean)
      {
        System.out.printf("%d) %s%s", i, current_task_item, System.lineSeparator());
      } // END if

      ++number_of_complete_tasks;

    } // END for LOOP

    return number_of_complete_tasks;

  } // END display_completed_tasks()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_numeric_selection_request()
  {
    System.out.println("Enter a number for your selection:");
  } // END display_numeric_selection_request()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_main_menu()
  {

    // DISPLAY HEADER
    System.out.println("----------");
    System.out.println("MAIN MENU");
    System.out.println("----------");
    System.out.println("");

    // DISPLAY SELECTION OPTIONS
    System.out.println("1) Create a new list");
    System.out.println("2) Load an existing list");
    System.out.println("3) Quit");
    System.out.println("");

    display_numeric_selection_request();

  } // END display_main_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_list_operation_menu()
  {

    // DISPLAY HEADER
    System.out.println("--------------------");
    System.out.println("LIST OPERATION MENU");
    System.out.println("--------------------");
    System.out.println("");

    // DISPLAY SELECTION OPTIONS
    System.out.println("1) View the list");
    System.out.println("2) Add an item");
    System.out.println("3) Edit an item");
    System.out.println("4) Remove an item");
    System.out.println("5) Mark an item as completed");
    System.out.println("6) Unmark a completed item");
    System.out.println("7) Save the current list");
    System.out.println("8) Quit to the main menu (current data will be lost)");
    System.out.println("");

    display_numeric_selection_request();

  } // END display_list_operation_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_list_view_menu()
  {

    // DISPLAY HEADER
    System.out.println("-------------------------------------");
    System.out.println("LIST VIEW (tasks sorted by due date)");
    System.out.println("-------------------------------------");
    System.out.println("");

    /***************************************************/

    task_list.sort_all_tasks(); // SORT TASK LIST BEFORE DISPLAYING

    // IF NO TASKS WERE DISPLAYED
    if( display_all_tasks() == 0 )
    {
      System.out.println("[nothing to display]");
    } // END if

    /***************************************************/

    System.out.println("");
    System.out.println("Input anything to exit list view:");

  } // END display_list_view_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_add_item_menu()
  {

    // DISPLAY HEADER
    System.out.println("---------");
    System.out.println("ADD ITEM");
    System.out.println("---------");
    System.out.println("");

  } // END display_add_item_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_edit_item_menu()
  {

    int number_of_tasks_displayed;

    // DISPLAY HEADER
    System.out.println("----------");
    System.out.println("EDIT ITEM");
    System.out.println("----------");
    System.out.println("");

    task_list.sort_all_tasks(); // SORT TASK LIST BEFORE DISPLAYING
    number_of_tasks_displayed = display_all_tasks();

    // IF NO TASKS WERE DISPLAYED
    if( number_of_tasks_displayed == 0 )
    {
      System.out.println("[nothing to display]");
      System.out.println("");
      System.out.println("Input anything to exit edit item menu:");
      return;
    } // END if

    System.out.println("");
    System.out.println("Enter the number for a task you wish to edit:");

  } // END display_edit_item_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_remove_item_menu()
  {

    int number_of_tasks_displayed;

    // DISPLAY HEADER
    System.out.println("------------");
    System.out.println("REMOVE ITEM");
    System.out.println("------------");
    System.out.println("");

    task_list.sort_all_tasks(); // SORT TASK LIST BEFORE DISPLAYING
    number_of_tasks_displayed = display_all_tasks();

    // IF NO TASKS WERE DISPLAYED
    if( number_of_tasks_displayed == 0 )
    {
      System.out.println("[nothing to display]");
      System.out.println("");
      System.out.println("Input anything to exit remove item menu:");
      return;
    } // END if

    System.out.println("");
    System.out.println("Enter the number for a task you wish to remove:");

  } // END display_remove_item_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_mark_item_menu()
  {

    int number_of_tasks_displayed;

    // DISPLAY HEADER
    System.out.println("---------------------------------------------");
    System.out.println("MARK ITEM (tasks shown below are incomplete)");
    System.out.println("---------------------------------------------");
    System.out.println("");

    task_list.sort_all_tasks(); // SORT TASK LIST BEFORE DISPLAYING
    number_of_tasks_displayed = display_incomplete_tasks(true);

    // IF NO TASKS WERE DISPLAYED
    if(number_of_tasks_displayed == 0)
    {
      System.out.println("[nothing to display]");
      System.out.println("");
      System.out.println("Input anything to exit mark item menu:");
      return;
    } // END if

    System.out.println("");
    System.out.println("Enter the number for a task you wish to mark as complete:");

  } // END display_mark_item_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_unmark_item_menu()
  {

    int number_of_tasks_displayed;

    // DISPLAY HEADER
    System.out.println("----------------------------------------------");
    System.out.println("UNMARK ITEM (tasks shown below are completed)");
    System.out.println("----------------------------------------------");
    System.out.println("");

    task_list.sort_all_tasks(); // SORT TASK LIST BEFORE DISPLAYING
    number_of_tasks_displayed = display_completed_tasks(true);

    // IF NO TASKS WERE DISPLAYED
    if(number_of_tasks_displayed == 0)
    {
      System.out.println("[nothing to display]");
      System.out.println("");
      System.out.println("Input anything to exit unmark item menu:");
      return;
    } // END if

    System.out.println("");
    System.out.println("Enter the number for a task you wish to unmark to remove its completion status:");

  } // END display_unmark_item_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_save_list_menu()
  {

    // DISPLAY HEADER
    System.out.println("----------");
    System.out.println("SAVE LIST");
    System.out.println("----------");
    System.out.println("");

    System.out.println("Enter the filename to save as:");

  } // END display_save_list_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_load_menu()
  {

    // DISPLAY HEADER
    System.out.println("-----");
    System.out.println("LOAD");
    System.out.println("-----");
    System.out.println("");

    System.out.println("Enter the filename to load:");

  } // END display_load_menu()

  /************************************* NEXT FUNCTION **************************************/

  private static void display_proper_menu()
  {

    switch(menu_state)
    {

      /**************/
      case MAIN_MENU:
      /**************/
        if(numeric_menu_selection == 1)
        {
          display_list_operation_menu();
          menu_state = MenuStateEnum.LIST_OPERATION_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 2)
        {
          display_load_menu();
          menu_state = MenuStateEnum.LOAD_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 3)
        {
          break; // NO MENU TO DISPLAY
        } // END if

        break;

      /**************/
      case LOAD_MENU:
      /**************/
        if(file_load_success_boolean)
        {
          display_list_operation_menu();
          menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        } // END if

        else
        {
          display_main_menu();
          menu_state = MenuStateEnum.MAIN_MENU;
        } // END else

        title_data = ""; // DON'T NEED FILENAME ANYMORE
        break;

      /************************/
      case LIST_OPERATION_MENU:
      /************************/
        if(numeric_menu_selection == 1)
        {
          display_list_view_menu();
          menu_state = MenuStateEnum.LIST_VIEW_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 2)
        {
          display_add_item_menu();
          menu_state = MenuStateEnum.ADD_ITEM_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 3)
        {
          display_edit_item_menu();
          menu_state = MenuStateEnum.EDIT_ITEM_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 4)
        {
          display_remove_item_menu();
          menu_state = MenuStateEnum.REMOVE_ITEM_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 5)
        {
          display_mark_item_menu();
          menu_state = MenuStateEnum.MARK_ITEM_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 6)
        {
          display_unmark_item_menu();
          menu_state = MenuStateEnum.UNMARK_ITEM_MENU;
          break;
        } // END if

        if(numeric_menu_selection == 7)
        {
          display_save_list_menu();
          menu_state = MenuStateEnum.SAVE_LIST_MENU;
          break;
        } // END if

        if(numeric_menu_selection == MAX_LIST_OPERATION_MENU_OPTIONS)
        {
          display_main_menu();
          menu_state = MenuStateEnum.MAIN_MENU;
          break;
        } // END if

        break;

      /******************/
      case LIST_VIEW_MENU:
      /******************/
        display_list_operation_menu();
        menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        break;

      /******************/
      case ADD_ITEM_MENU:
      /******************/
        display_list_operation_menu();
        menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        break;

      /******************/
      case EDIT_ITEM_MENU:
      /******************/
        display_list_operation_menu();
        menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        break;

      /********************/
      case REMOVE_ITEM_MENU:
      /********************/
        display_list_operation_menu();
        menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        break;

      /********************/
      case MARK_ITEM_MENU:
      /********************/
        display_list_operation_menu();
        menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        break;

      /**********************/
      case UNMARK_ITEM_MENU:
      /**********************/
        display_list_operation_menu();
        menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        break;

      /********************/
      case SAVE_LIST_MENU:
      /********************/
        display_list_operation_menu();
        menu_state = MenuStateEnum.LIST_OPERATION_MENU;
        title_data = ""; // DON'T NEED OUTPUT FILENAME ANYMORE
        break;

    } // END switch

    numeric_menu_selection = -1; // NEGATIVE VALUE TO DENOTE NO CHOICE

  } // END display_proper_menu()

  /************************************* NEXT FUNCTION **************************************/

  // PERFORMS ANY END PROGRAM HOUSEKEEPING
  private static void clean_up()
  {
    user_input_scanner.close();
  } // END clean_up()

  /*****************************************************************
    MAIN
  ******************************************************************/

  public static void main(String[] args)
  {

    display_main_menu();

    do
    {

      get_proper_input();
      process_input();
      display_proper_menu();

    }
    while(is_program_still_running);

    clean_up();

  } // END main()

} // END App CLASS