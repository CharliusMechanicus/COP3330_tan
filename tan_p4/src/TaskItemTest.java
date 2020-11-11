/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 4
*************************************/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest
{

  TaskItem task_item;

  /***************************** (1) ******************************/

  @Test
  public void creatingTaskItemFailsWithInvalidDueDate()
  {

    Exception thrown_exception = assertThrows(IllegalArgumentException.class, () -> 
                                 {new TaskItem("Task1", "First task", "2020-13-98", false);});

    assertEquals("Illegal date", thrown_exception.getMessage());

  }

  /***************************** (2) ******************************/

  @Test
  public void creatingTaskItemFailsWithInvalidTitle()
  {

    Exception thrown_exception = assertThrows(IllegalArgumentException.class, () -> 
                                 {new TaskItem("", "First task", "2020-01-01", false);});

    assertEquals("Illegal title", thrown_exception.getMessage());

  }

  /***************************** (3) ******************************/

  @Test
  public void creatingTaskItemSucceedsWithValidDueDate()
  {

    task_item = new TaskItem("Task1", "First task", "2020-02-03", false);

    assertEquals("2020-02-03", task_item.get_task_due_date());

  }

  /***************************** (4) ******************************/

  @Test
  public void creatingTaskItemSucceedsWithValidTitle()
  {

    task_item = new TaskItem("Valid title", "Some task", "2020-01-01", true);

    assertEquals("Valid title", task_item.get_task_title());

  }

  /***************************** (5) ******************************/

  @Test
  public void settingTaskItemDueDateFailsWithInvalidDate()
  {

    task_item = new TaskItem();
    task_item.set_task_due_date("2020-11-98");

    assertEquals(null, task_item.get_raw_due_date());

  }

  /***************************** (6) ******************************/

  @Test
  public void settingTaskItemDueDateSucceedsWithValidDate()
  {

    task_item = new TaskItem();
    task_item.set_task_due_date("2024-05-21");

    assertEquals("2024-05-21", task_item.get_task_due_date());

  }

  /***************************** (7) ******************************/

  @Test
  public void settingTaskItemTitleFailsWithInvalidTitle()
  {

    task_item = new TaskItem();

    Exception thrown_exception = assertThrows(IllegalArgumentException.class, () ->
                                 {task_item.set_task_title("");});

    assertEquals("Illegal title", thrown_exception.getMessage());

  }

  /***************************** (8) ******************************/

  @Test
  public void settingTaskItemTitleSucceedsWithValidTitle()
  {

    task_item = new TaskItem();
    task_item.set_task_title("Valid title");

    assertEquals("Valid title", task_item.get_task_title());

  }

} // END TaskItemTest CLASS