/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// EXCEPTION IMPORTS
import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TaskListTest
{

  TaskList task_list;

  @Test
  public void addingItemsIncreasesSize()
  {
    task_list = new TaskList();
    assertEquals(0, task_list.get_number_of_tasks());

    task_list.add_task("title", "description", "2012-11-12", true);
    task_list.add_task("title2", "description2", "2013-11-13", false);
    task_list.add_task("title3", "description3", "2014-11-14", true);
    assertEquals(3, task_list.get_number_of_tasks());
  }

  /***************************** (1) ******************************/

  @Test
  public void completingTaskItemChangesStatus()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", false);
    assertEquals(false, task_list.get_nth_completion_status(0));

    task_list.edit_nth_completion_status(0, true);
    assertEquals(true, task_list.get_nth_completion_status(0));
  }

  /***************************** (2) ******************************/

  @Test
  public void completingTaskItemFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", false);
    assertEquals(false, task_list.get_nth_completion_status(0));

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.edit_nth_completion_status(21, true); });
    assertEquals(false, task_list.get_nth_completion_status(0));
  }

  /***************************** (3) ******************************/

  @Test
  public void editingItemDescriptionFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("description", task_list.get_nth_description(0));

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.edit_nth_description(1, "2nd description"); });

    assertEquals("description", task_list.get_nth_description(0));
  }

  /***************************** (4) ******************************/

  @Test
  public void editingItemDescriptionSucceedsWithExpectedValue()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("description", task_list.get_nth_description(0));

    task_list.edit_nth_description(0, "another description");
    assertEquals("another description", task_list.get_nth_description(0));
  }

  /***************************** (5) ******************************/

  @Test
  public void editingItemDueDateSucceedsWithExpectedValue()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("2012-11-12", task_list.get_nth_due_date(0));

    task_list.edit_nth_due_date(0, "3001-01-02");
    assertEquals("3001-01-02", task_list.get_nth_due_date(0));
  }

  /***************************** (6) ******************************/

  @Test
  public void editingItemTitleFailsWithEmptyString()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("title", task_list.get_nth_title(0));

    assertThrows(IllegalArgumentException.class, ()->
      { task_list.edit_nth_title(0, ""); });

    assertEquals("title", task_list.get_nth_title(0));
  }

  /***************************** (7) ******************************/

  @Test
  public void editingItemTitleFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("title", task_list.get_nth_title(0));

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.edit_nth_title(3, "2nd title"); });

    assertEquals("title", task_list.get_nth_title(0));
  }

  /***************************** (8) ******************************/

  @Test
  public void editingItemTitleSucceedsWithExpectedValue()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("title", task_list.get_nth_title(0));

    task_list.edit_nth_title(0, "alternative title");
    assertEquals("alternative title", task_list.get_nth_title(0));
  }

  /***************************** (9) ******************************/

  @Test
  public void editingTaskItemDueDateFailsWithInvalidDateFormat()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("2012-11-12", task_list.get_nth_due_date(0));

    assertThrows(DateTimeParseException.class, ()->
      { task_list.edit_nth_due_date(0, "2030/05/06"); });

    assertEquals("2012-11-12", task_list.get_nth_due_date(0));
  }

  /***************************** (10) ******************************/

  @Test
  public void editingTaskItemDueDateFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("2012-11-12", task_list.get_nth_due_date(0));

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.edit_nth_due_date(7, "2030-05-06"); });

    assertEquals("2012-11-12", task_list.get_nth_due_date(0));
  }

  /***************************** (11) ******************************/

  @Test
  public void editingTaskItemDueDateFailsWithInvalidYYYYMMDD()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    assertEquals("2012-11-12", task_list.get_nth_due_date(0));

    assertThrows(DateTimeParseException.class, ()->
      { task_list.edit_nth_due_date(0, "2020-13-99"); });

    assertEquals("2012-11-12", task_list.get_nth_due_date(0));
  }

  /***************************** (12) ******************************/

  @Test
  public void gettingItemDescriptionFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.get_nth_description(55); });
  }

  /***************************** (13) ******************************/

  @Test
  public void gettingItemDescriptionSucceedsWithValidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);
    task_list.add_task("title2", "description2", "2013-12-13", true);

    assertEquals("description2", task_list.get_nth_description(1));
  }

  /***************************** (14) ******************************/

  @Test
  public void gettingItemDueDateFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2012-11-12", true);

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.get_nth_due_date(4); });
  }

  /***************************** (15) ******************************/

  @Test
  public void gettingItemDueDateSucceedsWithValidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("title", "description", "2010-06-07", true);

    assertEquals("2010-06-07", task_list.get_nth_due_date(0));
  }

  /***************************** (16) ******************************/

  @Test
  public void gettingItemTitleFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("some title", "some description", "2010-06-07", true);

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.get_nth_title(7014); });
  }

  /***************************** (17) ******************************/

  @Test
  public void gettingItemTitleSucceedsWithValidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("some title", "some description", "2010-06-07", true);

    assertEquals("some title", task_list.get_nth_title(0));
  }

  /***************************** (18) ******************************/

  @Test
  public void newListIsEmpty()
  {
    task_list = new TaskList();
    assertEquals(0, task_list.get_number_of_tasks());
  }

  /***************************** (19) ******************************/

  @Test
  public void removingItemsDecreasesSize()
  {
    task_list = new TaskList();
    task_list.add_task("some title", "some description", "2010-06-07", true);
    task_list.add_task("some other title", "some other description", "2010-06-08", true);
    task_list.add_task("yet another title", "yet another description", "2010-06-09", true);
    assertEquals(3, task_list.get_number_of_tasks());

    task_list.delete_nth_task(0);
    assertEquals(2, task_list.get_number_of_tasks());
    task_list.delete_nth_task(0);
    assertEquals(1, task_list.get_number_of_tasks());
    task_list.delete_nth_task(0);
    assertEquals(0, task_list.get_number_of_tasks());
  }

  /***************************** (20) ******************************/

  @Test
  public void removingItemsFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("some title", "some description", "2010-06-07", true);
    task_list.add_task("some other title", "some other description", "2010-06-08", true);
    task_list.add_task("yet another title", "yet another description", "2010-06-09", true);
    assertEquals(3, task_list.get_number_of_tasks());

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.delete_nth_task(77); });
  }

  /***************************** (21) ******************************/

  @Test
  public void savedTaskListCanBeLoaded()
  throws FileNotFoundException, InvalidPathException, UnsupportedEncodingException, IOException
  {
    task_list = new TaskList();
    task_list.add_task("1st title", "1st description", "2020-01-01", true);
    task_list.add_task("2nd title", "2nd description", "2020-01-02", false);
    task_list.add_task("3rd title", "3rd description", "2020-01-03", true);
    assertEquals(3, task_list.get_number_of_tasks());

    task_list.save_tasks("3_tasks.txt");

    task_list.clear_all_tasks();
    assertEquals(0, task_list.get_number_of_tasks());

    task_list.load_tasks("3_tasks.txt");
    assertEquals(3, task_list.get_number_of_tasks());

    assertEquals("1st title", task_list.get_nth_title(0));
    assertEquals("1st description", task_list.get_nth_description(0));
    assertEquals("2020-01-01", task_list.get_nth_due_date(0));
    assertEquals(true, task_list.get_nth_completion_status(0));

    assertEquals("2nd title", task_list.get_nth_title(1));
    assertEquals("2nd description", task_list.get_nth_description(1));
    assertEquals("2020-01-02", task_list.get_nth_due_date(1));
    assertEquals(false, task_list.get_nth_completion_status(1));

    assertEquals("3rd title", task_list.get_nth_title(2));
    assertEquals("3rd description", task_list.get_nth_description(2));
    assertEquals("2020-01-03", task_list.get_nth_due_date(2));
    assertEquals(true, task_list.get_nth_completion_status(2));

  }

  /***************************** (22) ******************************/

  @Test
  public void uncompletingTaskItemChangesStatus()
  throws IndexOutOfBoundsException
  {
    task_list = new TaskList();
    task_list.add_task("1st title", "1st description", "2020-01-01", true);
    assertEquals(true, task_list.get_nth_completion_status(0));

    task_list.edit_nth_completion_status(0, false);

    assertEquals(false, task_list.get_nth_completion_status(0));
  }

  /***************************** (23) ******************************/

  @Test
  public void uncompletingTaskItemFailsWithInvalidIndex()
  {
    task_list = new TaskList();
    task_list.add_task("1st title", "1st description", "2020-01-01", true);
    assertEquals(true, task_list.get_nth_completion_status(0));

    assertThrows(IndexOutOfBoundsException.class, ()->
      { task_list.edit_nth_completion_status(8, false); });

    assertEquals(true, task_list.get_nth_completion_status(0));
  }

  /***************************** (24) ******************************/

} // END TaskListTest