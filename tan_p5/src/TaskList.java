/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Collections;

// EXCEPTION IMPORTS
import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TaskList
{

  /*********************************************
    CONSTANTS
  **********************************************/

  // VERIFICATION THAT FILE IS OK TO PARSE THROUGH AS A TASK LIST
  private final static String TASK_LIST_ID = "[~THIS SIGNIFIES THAT THIS FILE IS A TASK LIST~]";

  // SEPARATES TASK ITEM FIELDS INTO DISTINCT TOKENS IN A SAVED TASK LIST
  private final static String FILE_FIELD_PARTITION = "<~PARTITION~>";

  /*********************************************
    CONSTRUCTORS
  **********************************************/

  public TaskList()
  {
    task_item_arraylist = new ArrayList<TaskItem>();
  } // END TaskList() CONSTRUCTOR

  /*********************************************
    PUBLIC INTERFACE
  **********************************************/

  /*************************************************************
    PUBLIC INTERFACE FUNCTION LISTING
    ---------------------------------
    int get_number_of_tasks();
    int get_number_of_completed_tasks();
    int get_number_of_incomplete_tasks();
    void display_all_tasks();
    void display_completed_tasks();
    void display_incomplete_tasks();
    void add_task(String, String, String, boolean);
    void edit_nth_title(int, String);
    void edit_nth_description(int, String);
    void edit_nth_due_date(int, String);
    void edit_nth_completion_status(int, boolean);
    String get_nth_title(int);
    String get_nth_description(int);
    String get_nth_due_date(int);
    boolean get_nth_completion_status(int);
    void clear_all_tasks();
    void delete_nth_task(int);
    void save_tasks(String);
    void load_tasks(String);
  **************************************************************/

  public int get_number_of_tasks()
  {
    return task_item_arraylist.size();
  } // END get_number_of_tasks()

  /********************************** NEXT FUNCTION ************************************/

  public int get_number_of_completed_tasks()
  {

    /******************************************
      LOCAL VARIABLES
    *******************************************/

    int completed_tasks_counter = 0;
    TaskItem current_task_item;

    /*******************************************************/

    for(int i = 0; i < task_item_arraylist.size(); ++i)
    {

      current_task_item = task_item_arraylist.get(i);

      // IF CURRENT TASK ITEM IS COMPLETED, COUNT IT
      if(current_task_item.get_task_completion())
        ++completed_tasks_counter;

    } // END for LOOP

    /*******************************************************/

    return completed_tasks_counter;

  } // END get_number_of_completed_tasks()

  /********************************** NEXT FUNCTION ************************************/

  public int get_number_of_incomplete_tasks()
  {
    return ( get_number_of_tasks() - get_number_of_completed_tasks() );
  } // END get_number_of_incomplete_tasks()

  /********************************** NEXT FUNCTION ************************************/

  public void display_all_tasks()
  {

    if(task_item_arraylist.isEmpty())
      return;

    sort_all_tasks();

    for(int i = 0; i < task_item_arraylist.size(); ++i)
    {
      System.out.printf("%d) %s%n", i, task_item_arraylist.get(i));
    } // END for LOOP

  } // END display_all_tasks()

  /********************************** NEXT FUNCTION ************************************/

  public void display_completed_tasks()
  {

    if(task_item_arraylist.isEmpty())
      return;

    /*****************************************
      LOCAL VARIABLES
    ******************************************/

    TaskItem current_task_item;

    /*********************************************************/

    sort_all_tasks();

    for(int i = 0; i < task_item_arraylist.size(); ++i)
    {
      current_task_item = task_item_arraylist.get(i);

      // IF CURRENT TASK ITEM IS MARKED AS COMPLETED, DISPLAY IT
      if(current_task_item.get_task_completion())
        System.out.printf("%d) %s%n", i, current_task_item);
    } // END for LOOP

  } // END display_completed_tasks()

  /********************************** NEXT FUNCTION ************************************/

  public void display_incomplete_tasks()
  {

    if(task_item_arraylist.isEmpty())
      return;

    /*****************************************
      LOCAL VARIABLES
    ******************************************/

    TaskItem current_task_item;

    /*********************************************************/

    sort_all_tasks();

    for(int i = 0; i < task_item_arraylist.size(); ++i)
    {
      current_task_item = task_item_arraylist.get(i);

      // IF CURRENT TASK ITEM IS NOT MARKED AS COMPLETED, DISPLAY IT
      if(!current_task_item.get_task_completion())
        System.out.printf("%d) %s%n", i, current_task_item);
    } // END for LOOP

  } // END display_incomplete_tasks()

  /********************************** NEXT FUNCTION ************************************/

  public void add_task(String title, String description, String due_date, boolean completion_status)
  throws IllegalArgumentException, DateTimeParseException
  {

    TaskItem new_task_item = new TaskItem(title, description, due_date, completion_status);
    task_item_arraylist.add(new_task_item);

  } // END add_task()

  /********************************** NEXT FUNCTION ************************************/

  // EDITS 'n'TH TASK'S TITLE ('n' IS ZERO INDEXED)
  public void edit_nth_title(int nth_index, String new_title)
  throws IndexOutOfBoundsException, IllegalArgumentException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);
    task_item_ref.edit_task_title(new_title);

  } // END edit_nth_title()

  /********************************** NEXT FUNCTION ************************************/

  // EDITS 'n'TH TASK'S DESCRIPTION ('n' IS ZERO INDEXED)
  public void edit_nth_description(int nth_index, String new_description)
  throws IndexOutOfBoundsException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);
    task_item_ref.edit_task_description(new_description);

  } // END edit_nth_description()

  /********************************** NEXT FUNCTION ************************************/

  // EDITS 'n'TH TASK'S DUE DATE ('n' IS ZERO INDEXED)
  public void edit_nth_due_date(int nth_index, String new_due_date)
  throws IndexOutOfBoundsException, DateTimeParseException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);
    task_item_ref.edit_task_due_date(new_due_date);

  } // END edit_nth_due_date()

  /********************************** NEXT FUNCTION ************************************/

  // EDITS 'n'TH TASK'S COMPLETION STATUS ('n' IS ZERO INDEXED)
  public void edit_nth_completion_status(int nth_index, boolean new_completion_status)
  throws IndexOutOfBoundsException, IllegalArgumentException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);

    // IF COMPLETION BOOLEAN IS ALREADY AT 'new_completion_status' VALUE
    if(task_item_ref.get_task_completion() == new_completion_status)
    {
      throw new IllegalArgumentException();
    } // END if

    task_item_ref.edit_task_completion(new_completion_status);
    
  } // END edit_nth_completion_status()

  /********************************** NEXT FUNCTION ************************************/

  // RETRIEVES 'n'TH TASK'S TITLE ('n' IS ZERO INDEXED)
  public String get_nth_title(int nth_index)
  throws IndexOutOfBoundsException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);
    return task_item_ref.get_task_title();

  } // END get_nth_title()

  /********************************** NEXT FUNCTION ************************************/

  // RETRIEVES 'n'TH TASK'S DESCRIPTION ('n' IS ZERO INDEXED)
  public String get_nth_description(int nth_index)
  throws IndexOutOfBoundsException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);
    return task_item_ref.get_task_description();

  } // END get_nth_description()

  /********************************** NEXT FUNCTION ************************************/

  // RETRIEVES 'n'TH TASK'S DUE DATE ('n' IS ZERO INDEXED)
  public String get_nth_due_date(int nth_index)
  throws IndexOutOfBoundsException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);
    return task_item_ref.get_task_due_date();

  } // END get_nth_due_date()

  /********************************** NEXT FUNCTION ************************************/

  // RETRIEVES 'n'TH TASK'S COMPLETION STATUS ('n' IS ZERO INDEXED)
  public boolean get_nth_completion_status(int nth_index)
  throws IndexOutOfBoundsException
  {

    TaskItem task_item_ref = task_item_arraylist.get(nth_index);
    return task_item_ref.get_task_completion();

  } // END get_nth_completion_status()

  /********************************** NEXT FUNCTION ************************************/

  public void clear_all_tasks()
  {
    task_item_arraylist.clear();
  } // END clear_all_tasks()

  /********************************** NEXT FUNCTION ************************************/

  // DELETES 'n'TH TASK FROM TASK LIST ('n' IS ZERO INDEXED)
  public void delete_nth_task(int nth_index)
  throws IndexOutOfBoundsException
  {
    task_item_arraylist.remove(nth_index);
  } // END delete_nth_task()

  /********************************** NEXT FUNCTION ************************************/

  public void save_tasks(String filename_string)
  throws FileNotFoundException
  {

    /********************************************
      LOCAL VARIABLES
    *********************************************/

    Formatter file_output_formatter;

    TaskItem current_task_item;
    String current_title;
    String current_description;
    String current_due_date;
    boolean current_completion_status;

    /******************************************************/

    // COULD THROW 'FileNotFoundException'
    file_output_formatter = new Formatter(filename_string);

    // ADD 'TASK_LIST_ID' AS 1ST LINE TO IDENTIFY IT AS A VALID FILE TO PARSE
    file_output_formatter.format("%s%n", TASK_LIST_ID);

    /******************************************************/

    // GO THROUGH ALL TASK ITEMS AND SAVE THEM
    for(int i = 0; i < task_item_arraylist.size(); ++i)
    {

      /*******************************************************************/
      // GET CURRENT TASK ITEM'S FIELD ATTRIBUTES
      current_task_item = task_item_arraylist.get(i);
      current_title = current_task_item.get_task_title();
      current_description = current_task_item.get_task_description();
      current_due_date = current_task_item.get_task_due_date();
      current_completion_status = current_task_item.get_task_completion();
      /*******************************************************************/

      // IF THE TASK HAS NO DESCRIPTION
      if(current_description.isBlank())
      {
        file_output_formatter.format("%s%n", (current_title + FILE_FIELD_PARTITION +
          current_due_date + FILE_FIELD_PARTITION + (current_completion_status ? "true" : "false")) );
      } // END if

      // OTHERWISE, THE TASK HAS A DESCRIPTION
      else
      {
        file_output_formatter.format("%s%n", (current_title + FILE_FIELD_PARTITION +
          current_description + FILE_FIELD_PARTITION + current_due_date + FILE_FIELD_PARTITION +
          (current_completion_status ? "true" : "false")) );
      } // END else

    } // END for LOOP

    /******************************************************/
    // AT THIS POINT, ALL TASKS HAVE BEEN SAVED TO OUTPUT FILE

    file_output_formatter.close();

  } // END save_tasks()

  /********************************** NEXT FUNCTION ************************************/

  public void load_tasks(String filename_string)
  throws InvalidPathException, UnsupportedEncodingException, IOException
  {

    /********************************************
      LOCAL VARIABLES
    *********************************************/

    Scanner file_input_scanner;
    String file_line_string;

    String[] split_task_fields_array;
    String current_task_title = "";
    String current_task_description = "";
    String current_task_due_date = "";
    boolean current_task_completion;

    /**************************************************************/

    file_input_scanner = new Scanner(Paths.get(filename_string));

    // RETRIEVE FIRST LINE OF INPUT FILE
    file_line_string = file_input_scanner.nextLine();

    // IF FILE OPENED IS NOT A FILE CONTAINING TASK LIST INFO, LOAD FAILS
    if(!file_line_string.equals(TASK_LIST_ID))
    {
      throw new UnsupportedEncodingException("Error - File is not a task list");
    } // END if

    /**************************************************************/
    // AT THIS POINT, WE CAN ASSUME THAT WE ARE IN A TASK LIST FILE

    // IF TASK LIST IS NOT EMPTY
    if(task_item_arraylist.size() > 0)
    {
      task_item_arraylist.clear();
    } // END if

    /**************************************************************/

    // READ THROUGH ENTIRE TASK LIST FILE
    while(file_input_scanner.hasNext())
    {

      // GET INFORMATION FOR ONE TASK ITEM
      file_line_string = file_input_scanner.nextLine();

      // IF RETRIEVED A BLANK LINE
      if(file_line_string.isBlank())
        continue;

      // GET SEPARATE TASK FIELD TOKENS
      split_task_fields_array = file_line_string.split(FILE_FIELD_PARTITION);

      /***************************************************************************************/
      // IF IT IS A TASK ITEM WITH NO DESCRIPTION
      if(split_task_fields_array.length == 3)
      {
        current_task_title = split_task_fields_array[0];
        current_task_due_date = split_task_fields_array[1];

        if(split_task_fields_array[2].equals("true"))
          current_task_completion = true;
        else
          current_task_completion = false;

        this.add_task(current_task_title, "", current_task_due_date, current_task_completion);
      } // END if
      /******************************************************/

      // OTHERWISE, IT IS A TASK ITEM WITH A DESCRIPTION
      else
      {
        current_task_title = split_task_fields_array[0];
        current_task_description = split_task_fields_array[1];
        current_task_due_date = split_task_fields_array[2];

        if(split_task_fields_array[3].equals("true"))
          current_task_completion = true;
        else
          current_task_completion = false;

        this.add_task(current_task_title, current_task_description, current_task_due_date, current_task_completion);
      } // END else
     /***************************************************************************************/

    } // END while LOOP

    // AT THIS POINT, THE ENTIRE TASK LIST FILE HAS BEEN READ AND LOADED INTO LOCAL MEMORY

  } // END load_tasks()

  /*********************************************
    HELPER FUNCTIONS
  **********************************************/

  // SORTS BY DUE DATE, THEN BY TITLE, THEN BY TASK COMPLETION STATUS
  private void sort_all_tasks()
  {

    if(task_item_arraylist.size() > 1)
      Collections.sort(task_item_arraylist, new TaskItemComparator());

  } // END sort_all_tasks()

  /*********************************************
    PRIVATE DATA
  **********************************************/

  ArrayList<TaskItem> task_item_arraylist;

} // END TaskList CLASS