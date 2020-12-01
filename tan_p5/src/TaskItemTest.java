/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// EXCEPTION IMPORTS
import java.time.format.DateTimeParseException;

public class TaskItemTest
{

  TaskItem task_item;

  @Test
  public void constructorFailsWithInvalidDueDate()
  {
    assertThrows(DateTimeParseException.class, ()->
      { task_item = new TaskItem("title", "description", "2020-13-42", true); });
  }

  /***************************** (1) ******************************/

  @Test
  public void constructorFailsWithInvalidTitle()
  {
    Exception thrown_exception = assertThrows(IllegalArgumentException.class, ()->
      { task_item = new TaskItem("", "description", "2020-11-28", true); });

    assertEquals("Error - Title can not be blank", thrown_exception.getMessage());
  }

  /***************************** (2) ******************************/

  @Test
  public void constructorSucceedsWithValidDueDate()
  {
    task_item = new TaskItem("title", "description", "2020-11-28", true);

    assertEquals("2020-11-28", task_item.get_task_due_date());
  }

  /***************************** (3) ******************************/

  @Test
  public void constructorSucceedsWithValidTitle()
  {
    task_item = new TaskItem("validtitle", "description", "2020-11-28", true);

    assertEquals("validtitle", task_item.get_task_title());
  }

  /***************************** (4) ******************************/

  @Test
  public void editingDescriptionSucceedsWithExpectedValue()
  {
    task_item = new TaskItem("validtitle", "description", "2020-11-28", true);
    assertEquals("description", task_item.get_task_description());

    task_item.edit_task_description("new description");
    assertEquals("new description", task_item.get_task_description());
  }

  /***************************** (5) ******************************/

  @Test
  public void editingDueDateFailsWithInvalidDateFormat()
  {
    task_item = new TaskItem("title", "description", "2020-11-28", true);
    assertEquals("2020-11-28", task_item.get_task_due_date());

    assertThrows(DateTimeParseException.class, ()->
     { task_item.edit_task_due_date("2020/12/13"); } );

    assertEquals("2020-11-28", task_item.get_task_due_date());
  }

  /***************************** (6) ******************************/

  @Test
  public void editingDueDateFailsWithInvalidYYYYMMDD()
  {
    task_item = new TaskItem("title", "description", "2020-11-28", true);
    assertEquals("2020-11-28", task_item.get_task_due_date());

    assertThrows(DateTimeParseException.class, ()->
     { task_item.edit_task_due_date("1885-50-99"); } );

    assertEquals("2020-11-28", task_item.get_task_due_date());
  }

  /***************************** (7) ******************************/

  @Test
  public void editingDueDateSucceedsWithExpectedValue()
  {
    task_item = new TaskItem("title", "description", "2020-11-28", true);
    assertEquals("2020-11-28", task_item.get_task_due_date());

    task_item.edit_task_due_date("2021-05-23");
    assertEquals("2021-05-23", task_item.get_task_due_date());
  }

  /***************************** (8) ******************************/

  @Test
  public void editingTitleFailsWithEmptyString()
  {
    task_item = new TaskItem("title", "description", "2020-11-28", true);
    assertEquals("title", task_item.get_task_title());

    assertThrows(IllegalArgumentException.class, ()->
                 { task_item.edit_task_title(""); });

    assertEquals("title", task_item.get_task_title());
  }

  /***************************** (9) ******************************/

  @Test
  public void editingTitleSucceedsWithExpectedValue()
  {
    task_item = new TaskItem("title", "description", "2020-11-28", true);
    assertEquals("title", task_item.get_task_title());

    task_item.edit_task_title("a new title");
    assertEquals("a new title", task_item.get_task_title());
  }

  /***************************** (10) ******************************/

} // END TaskItemTest CLASS