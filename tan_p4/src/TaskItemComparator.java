/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 4
*************************************/

import java.util.Comparator;
import java.time.LocalDate;

public class TaskItemComparator implements Comparator<TaskItem>
{

  @Override
  public int compare(TaskItem task_item1, TaskItem task_item2)
  {

    /*********************************************
      VARIABLES
    **********************************************/

    LocalDate date1;
    LocalDate date2;

    String title1;
    String title2;

    boolean completed_boolean1;
    boolean completed_boolean2;

    /**********************************************/

    // COMPARE TASK ITEMS BY DATE
    date1 = task_item1.get_raw_due_date();
    date2 = task_item2.get_raw_due_date();

    if(date1.compareTo(date2) < 0)
    {
      return -1;
    } // END if

    if(date1.compareTo(date2) > 0)
    {
      return 1;
    } // END if

    /**********************************************/

    // AT THIS POINT, THE TASK ITEMS' DUE DATES ARE EQUAL
    // NOW, COMPARE BY TASK TITLE

    title1 = task_item1.get_task_title();
    title2 = task_item2.get_task_title();

    if(title1.compareToIgnoreCase(title2) < 0)
    {
      return -1;
    } // END if

    if(title1.compareToIgnoreCase(title2) > 0)
    {
      return 1;
    } // END if

    /**********************************************/

    // AT THIS POINT, THE TASK ITEMS' DUE DATES ARE EQUAL
    // ...AND THE TASK TITLES ARE ALSO EQUAL
    // NOW, COMPARE WHETHER THE TASKS ARE COMPLETED
    // (SKIP COMPARING BY TASK DESCRIPTION SINCE DESCRIPTIONS ARE OPTIONAL)

    completed_boolean1 = task_item1.get_task_completion();
    completed_boolean2 = task_item2.get_task_completion();

    // IF TASK1 IS COMPLETED, BUT TASK 2 IS NOT COMPLETED
    if(completed_boolean1 && !completed_boolean2)
    {
      return 1;
    } // END if

    // IF TASK1 IS NOT COMPLETED, BUT TASK2 IS COMPLETED
    if(!completed_boolean1 && completed_boolean2)
    {
      return -1;
    } // END if

    // IF BOTH TASK1 AND TASK2 ARE COMPLETED
    if(completed_boolean1 && completed_boolean2)
    {
      return 0;
    } // END if

    // IF BOTH TASK1 AND TASK2 ARE NOT COMPLETED
    if(!completed_boolean1 && !completed_boolean2)
    {
      return 0;
    } // END if

    /**********************************************/

    return 0; // IF SOMEHOW GET TO THIS POINT, CONSIDER THE TASKS EQUAL

  } // END compare()

} // END TaskItemComparator CLASS