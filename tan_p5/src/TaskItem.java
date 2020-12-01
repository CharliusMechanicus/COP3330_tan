/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import java.time.LocalDate;

// EXCEPTION IMPORTS
import java.time.format.DateTimeParseException;

public class TaskItem
{

  /******************************************
    CONSTRUCTORS
  *******************************************/

  // PREVENT ZERO ARGUMENT CONSTRUCTOR FROM INSTANTIATING ANY OBJECTS
  private TaskItem()
  {
  } // END TaskItem CONSTRUCTOR

  /**************************** NEXT FUNCTION *******************************/

  public TaskItem(String title, String description, String date_string, boolean completion_status)
  {

    // PREVENT TASKITEM CREATION IF ILLEGAL TITLE
    if(title.length() < 1)
    {
      throw new IllegalArgumentException("Error - Title can not be blank");
    } // END if

    /**************************************************/

    // COULD THROW 'DateTimeParseException'
    LocalDate local_date_ref = LocalDate.parse(date_string);

    this.task_title = title;
    this.task_description = description;
    this.task_due_date = local_date_ref;
    this.task_completion_boolean = completion_status;

  } // END TaskItem(String, String, String, boolean) CONSTRUCTOR

  /******************************************
    PUBLIC INTERFACE
  *******************************************/

  /*************************************************************
    PUBLIC INTERFACE FUNCTION LISTING
    ---------------------------------
    void edit_task_title(String);
    void edit_task_description(String);
    void edit_task_due_date(String);
    void edit_task_completion(boolean);
    String get_task_title();
    String get_task_description();
    String get_task_due_date();
    boolean get_task_completion();
    LocalDate get_raw_due_date();
    String toString();
  ***************************************************************/

  public void edit_task_title(String title)
  throws IllegalArgumentException
  {
    // IF 'title' IS A VALID TITLE
    if(title.length() >= 1)
    {
      task_title = title;
      return;
    } // END if

    // OTHERWISE, 'title' IS NOT A VALID TITLE
    throw new IllegalArgumentException("Error - Title can not be blank");

  } // END edit_task_title()

  /**************************** NEXT FUNCTION *******************************/

  public void edit_task_description(String description)
  {
    task_description = description;
  } // END edit_task_description()

  /**************************** NEXT FUNCTION *******************************/

  public void edit_task_due_date(String due_date_string)
  throws DateTimeParseException
  {
    LocalDate local_date_ref;

    local_date_ref = LocalDate.parse(due_date_string);
    task_due_date = local_date_ref;

  } // END edit_task_due_date()

  /**************************** NEXT FUNCTION *******************************/

  public void edit_task_completion(boolean completion_status)
  {
    task_completion_boolean = completion_status;
  } // END edit_task_completion()

  /**************************** NEXT FUNCTION *******************************/

  public String get_task_title()
  {
    return task_title;
  } // END get_task_title()

  /**************************** NEXT FUNCTION *******************************/

  public String get_task_description()
  {
    return task_description;
  } // END get_task_description()

  /**************************** NEXT FUNCTION *******************************/

  public String get_task_due_date()
  {
    return task_due_date.toString();
  } // END get_task_due_date()

  /**************************** NEXT FUNCTION *******************************/

  public boolean get_task_completion()
  {
    return task_completion_boolean;
  } // END get_task_completion()

  /**************************** NEXT FUNCTION *******************************/

  public LocalDate get_raw_due_date()
  {
    return task_due_date;
  } // END get_raw_due_date()

  /**************************** NEXT FUNCTION *******************************/

  @Override
  public String toString()
  {

    // IF TASK IS MARKED AS COMPLETE
    if(task_completion_boolean)
    {
      // IF THERE'S NO TASK DESCRIPTION
      if(task_description.isBlank() || task_description.equals(null))
      {
        return "*** " + String.format("[%s] %s", task_due_date, task_title);
      } // END if

      return "*** " + String.format("[%s] %s | %s", task_due_date, task_title, task_description);
    } // END if

    /***********************************************************/

    // OTHERWISE, TASK IS NOT COMPLETE
    else
    {
      // IF THERE'S NO TASK DESCRIPTION
      if(task_description.isBlank() || task_description.equals(null))
      {
        return String.format("[%s] %s", task_due_date, task_title);
      } // END if

      return String.format("[%s] %s | %s", task_due_date, task_title, task_description);
    } // END else

  } // END toString()

  /******************************************
    PRIVATE DATA
  *******************************************/

  private String task_title;
  private String task_description;
  private LocalDate task_due_date;
  private boolean task_completion_boolean;

} // END TaskItem CLASS