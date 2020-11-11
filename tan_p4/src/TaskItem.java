/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 4
*************************************/

import java.time.LocalDate;

// EXCEPTION IMPORTS
import java.time.format.DateTimeParseException;

public class TaskItem
{

  /*****************************************************************
    CONSTRUCTORS
  ******************************************************************/

  public TaskItem()
  {
    task_title = "";
    task_description = "";
    task_due_date = null;
    task_completion_boolean = false;
  } // END TaskItem CONSTRUCTOR

  /*******************************************************/

  public TaskItem(String title, String description, String date_string, boolean completion_status)
  {

    // PREVENT TASKITEM CREATION IF ILLEGAL TITLE
    if(title.length() < 1)
    {
      throw new IllegalArgumentException("Illegal title");
    } // END if

    /**************************************************/

    try
    {
      LocalDate local_date_ref = LocalDate.parse(date_string);
    } // END try

    // PREVENT TASKITEM CREATION IF ILLEGAL DATE
    catch(DateTimeParseException ex)
    {
      throw new IllegalArgumentException("Illegal date");
    } // END catch

    /**************************************************/

    set_task_title(title);
    set_task_description(description);
    set_task_due_date(date_string);
    set_task_completion(completion_status);

  } // END TaskItem CONSTRUCTOR

  /*****************************************************************
    PUBLIC INTERFACE
  ******************************************************************/

  public void set_task_title(String title)
  {
    // IF 'title' IS A VALID TITLE
    if(title.length() >= 1)
    {
      task_title = title;
      return;
    } // END if

    // OTHERWISE, IF 'title' IS NOT A VALID TITLE
    throw new IllegalArgumentException("Illegal title");

  } // END set_task_title()

  /*************************************************/

  public String get_task_title()
  {
    return task_title;
  } // END get_task_title()

  /*************************************************/

  public void set_task_description(String description)
  {
    task_description = description;
  } // END set_task_description()

  /*************************************************/

  public String get_task_description()
  {
    return task_description;
  } // END get_task_description()

  /*************************************************/

  public void set_task_due_date(String due_date_string)
  {
    LocalDate local_date_ref;

    try
    {
      local_date_ref = LocalDate.parse(due_date_string);
      task_due_date = local_date_ref;
    } // END try

    catch(DateTimeParseException ex)
    {
      task_due_date = null;
    } // END catch

  } // END set_task_due_date()

  /*************************************************/

  public String get_task_due_date()
  {
    return task_due_date.toString();
  } // END get_task_due_date()

  /*************************************************/

  public LocalDate get_raw_due_date()
  {
    return task_due_date;
  } // END get_raw_due_date()

  /*************************************************/

  public void set_task_completion(boolean completion_status)
  {
    task_completion_boolean = completion_status;
  } // END set_task_completion()

  /*************************************************/

  public boolean get_task_completion()
  {
    return task_completion_boolean;
  } // END get_task_completion()

  /*************************************************/

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

  /*****************************************************************
    PRIVATE DATA
  ******************************************************************/

  private String task_title;
  private String task_description;
  private LocalDate task_due_date;
  private boolean task_completion_boolean;

} // END TaskItem CLASS