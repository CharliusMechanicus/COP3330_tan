/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 4
*************************************/

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.util.Scanner;

public class TaskList
{

  /*****************************************************************
    PUBLIC INTERFACE
  ******************************************************************/

  public void add_task_item(String title, String description, String due_date, boolean completion_status)
  {
    TaskItem task_item =  new TaskItem();

    task_item.set_task_title(title);
    task_item.set_task_description(description);
    task_item.set_task_due_date(due_date);
    task_item.set_task_completion(completion_status);

    task_item_arraylist.add(task_item);
  } // END add_task_item()

  /**************************************************************/

  public void delete_all_task_items()
  {
    task_item_arraylist.clear();
  } // END delete_all_task_items()

  /**************************************************************/

  // DELETES THE 'n'th TASK ITEM
  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public void delete_nth_task_item(int n)
  {
    task_item_arraylist.remove(n);
  } // END delete_nth_task_item

  /**************************************************************/

  // RETRIEVES THE 'n'th TASK ITEM FROM THE TASK LIST
  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public TaskItem get_nth_task_item(int n)
  {
    return task_item_arraylist.get(n);
  } // END get_nth_task_item()

  /**************************************************************/

  // RETURNS THE NUMBER OF TASK ITEMS IN THE TASK LIST
  public int get_number_of_task_items()
  {
    return task_item_arraylist.size();
  } // END get_number_of_task_items()

  /**************************************************************/

  // REPLACES THE 'n'th TASK ITEM IN THE TASK LIST WITH 'new_task_item'
  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public void replace_nth_task_item(int n, TaskItem new_task_item)
  {
    task_item_arraylist.set(n, new_task_item);
  } // END replace_nth_task_item()

  /**************************************************************/

  // SORTS BY DUE DATE, THEN BY TITLE, THEN BY TASK COMPLETION STATUS
  public void sort_all_tasks()
  {
    if(task_item_arraylist.size() > 1)
      Collections.sort(task_item_arraylist, new TaskItemComparator());
  } // END sort_all_tasks()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public void complete_nth_task_item(int n)
  {
    get_nth_task_item(n).set_task_completion(true);
  } // END complete_nth_task_item()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  // REMOVES COMPLETION STATUS OF 'n'th TASK ITEM
  public void uncomplete_nth_task_item(int n)
  {
    get_nth_task_item(n).set_task_completion(false);
  } // END uncomplete_nth_task_item()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public void edit_nth_task_item(int n, String new_title, String new_description,
  String new_due_date, boolean new_completion_status)
  {

    get_nth_task_item(n).set_task_title(new_title);
    get_nth_task_item(n).set_task_description(new_description);
    get_nth_task_item(n).set_task_due_date(new_due_date);
    get_nth_task_item(n).set_task_completion(new_completion_status);

  } // END edit_nth_task_item()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public void edit_nth_task_title(int n, String new_title)
  {
    get_nth_task_item(n).set_task_title(new_title);
  } // END edit_nth_task_title()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public void edit_nth_task_description(int n, String new_description)
  {
    get_nth_task_item(n).set_task_description(new_description);
  } // END edit_nth_task_description()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public void edit_nth_task_due_date(int n, String new_due_date)
  {
    get_nth_task_item(n).set_task_due_date(new_due_date);
  } // END edit_nth_task_due_date()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public String get_nth_task_title(int n)
  {
    return get_nth_task_item(n).get_task_title();
  } // END get_nth_task_title()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public String get_nth_task_description(int n)
  {
    return get_nth_task_item(n).get_task_description();
  } // END get_nth_task_description()

  /**************************************************************/

  // 'n'th NUMBERING SCHEME IS ZERO INDEXED
  public String get_nth_task_due_date(int n)
  {
    return get_nth_task_item(n).get_task_due_date();
  } // END get_nth_task_due_date()

  /**************************************************************/

  // FUNCTION ASSUMES 'number_string' TO REPRESENT A NUMBER
  public boolean determine_boolean_from_string(String number_string)
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

  /**************************************************************/

  // FUNCTION ASSUMES THAT 'file_scanner' HAS BEEN INITIALIZED TO AN APPROPRIATE OUTPUT FILE FOR TASK ITEMS
  public void load_task_list_from_file(Scanner file_scanner)
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
    final String FILE_FIELD_PARTITION = "<~PARTITION~>";

    /***************************************************************/

    // EMPTY CURRENT TASK LIST IF THERE'S ANY EXISTING DATA
    if(get_number_of_task_items() > 0)
      delete_all_task_items();

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

      add_task_item(current_title, current_description, current_due_date, current_completion_status);

    } // END while LOOP

  } // END load_task_list_from_file()

  /*****************************************************************
    CONSTRUCTORS
  ******************************************************************/

  public TaskList()
  {
    task_item_arraylist = new ArrayList<TaskItem>();
  } // END TaskList CONSTRUCTOR

  /*****************************************************************
    PRIVATE DATA
  ******************************************************************/

  ArrayList<TaskItem> task_item_arraylist;

} // END TaskList CLASS