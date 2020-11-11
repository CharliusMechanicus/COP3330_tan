/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 4
*************************************/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;

class TaskListTest
{

  TaskList task_list;
  TaskItem task_item;

  /***************************** (1) ******************************/

  @Test
  public void addingTaskItemsIncreasesSize()
  {

    task_list = new TaskList();

    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);
    assertEquals(1, task_list.get_number_of_task_items());

    task_list.add_task_item("Task2", "Second Task", "2020-11-12", false);
    assertEquals(2, task_list.get_number_of_task_items());

  }

  /***************************** (2) ******************************/

  @Test
  public void completingTaskItemChangesStatus()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    task_item = task_list.get_nth_task_item(0);
    assertEquals(false, task_item.get_task_completion());

    task_list.complete_nth_task_item(0);
    assertEquals(true, task_item.get_task_completion());

  }

  /***************************** (3) ******************************/

  @Test
  public void completingTaskItemFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    assertThrows(IndexOutOfBoundsException.class, () -> {task_list.complete_nth_task_item(7);});

  }

  /***************************** (4) ******************************/

  @Test
  public void editingTaskItemChangesValues()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    task_item = task_list.get_nth_task_item(0);

    assertEquals("Task1", task_item.get_task_title());
    assertEquals("First Task", task_item.get_task_description());
    assertEquals("2020-11-11", task_item.get_task_due_date());
    assertEquals(false, task_item.get_task_completion());

    task_list.edit_nth_task_item(0, "New title", "New description", "2020-12-12", true);

    assertEquals("New title", task_item.get_task_title());
    assertEquals("New description", task_item.get_task_description());
    assertEquals("2020-12-12", task_item.get_task_due_date());
    assertEquals(true, task_item.get_task_completion());

  }

  /***************************** (5) ******************************/

  @Test
  public void editingTaskItemDescriptionChangesValue()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    task_item = task_list.get_nth_task_item(0);
    assertEquals("First Task", task_item.get_task_description());

    task_list.edit_nth_task_description(0, "New description");
    assertEquals("New description", task_item.get_task_description());    

  }

  /***************************** (6) ******************************/

  @Test
  public void editingTaskItemDescriptionFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.edit_nth_task_description(101, "Some description");});

  }

  /***************************** (7) ******************************/

  @Test
  public void editingTaskItemDueDateChangesValue()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    task_item = task_list.get_nth_task_item(0);
    assertEquals("2020-11-11", task_item.get_task_due_date());

    task_list.edit_nth_task_due_date(0, "1980-03-03");
    assertEquals("1980-03-03", task_item.get_task_due_date());

  }

  /***************************** (8) ******************************/

  @Test
  public void editingTaskItemDueDateFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.edit_nth_task_due_date(5, "1980-03-03");});

  }

  /***************************** (9) ******************************/

  @Test
  public void editingTaskItemTitleChangesValue()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    task_item = task_list.get_nth_task_item(0);
    assertEquals("Task1", task_item.get_task_title());

    task_list.edit_nth_task_title(0, "New title");
    assertEquals("New title", task_item.get_task_title());

  }

  /**************************** (10) ******************************/

  @Test
  public void editingTaskItemTitleFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.edit_nth_task_title(35, "New title");});

  }

  /**************************** (11) ******************************/

  @Test
  public void gettingTaskItemDescriptionFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.get_nth_task_description(123);});

  }

  /**************************** (12) ******************************/

  @Test
  public void gettingTaskItemDescriptionSucceedsWithValidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    assertEquals("First Task", task_list.get_nth_task_description(0));

  }

  /**************************** (13) ******************************/

  @Test
  public void gettingTaskItemDueDateFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-11-11", false);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.get_nth_task_due_date(55);});

  }

  /**************************** (14) ******************************/

  @Test
  public void gettingTaskItemDueDateSucceedsWithValidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2018-09-09", false);

    assertEquals("2018-09-09", task_list.get_nth_task_due_date(0));

  }

  /**************************** (15) ******************************/

  @Test
  public void gettingTaskItemTitleFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2018-09-09", false);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.get_nth_task_title(44);});

  }

  /**************************** (16) ******************************/

  @Test
  public void gettingTaskItemTitleSucceedsWithValidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2018-09-09", false);

    assertEquals("Task1", task_list.get_nth_task_title(0));

  }

  /**************************** (17) ******************************/

  @Test
  public void newTaskListIsEmpty()
  {

    task_list = new TaskList();
    assertEquals(0, task_list.get_number_of_task_items());

  }

  /**************************** (18) ******************************/

  @Test
  public void removingTaskItemsDecreasesSize()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-06-07", true);
    task_list.add_task_item("Task2", "Second Task", "2020-06-08", true);
    task_list.add_task_item("Task3", "Third Task", "2020-06-09", true);

    assertEquals(3, task_list.get_number_of_task_items());

    task_list.delete_nth_task_item(0);
    assertEquals(2, task_list.get_number_of_task_items());

    task_list.delete_nth_task_item(0);
    assertEquals(1, task_list.get_number_of_task_items());

    task_list.delete_nth_task_item(0);
    assertEquals(0, task_list.get_number_of_task_items());

  }

  /**************************** (19) ******************************/

  @Test
  public void removingTaskItemsFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-06-07", true);
    task_list.add_task_item("Task2", "Second Task", "2020-06-08", true);
    task_list.add_task_item("Task3", "Third Task", "2020-06-09", true);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.delete_nth_task_item(3);});

  }

  /**************************** (20) ******************************/

  @Test
  public void savedTaskListCanBeLoaded()
  throws java.io.IOException
  {

    task_list = new TaskList();
    assertEquals(0, task_list.get_number_of_task_items());

    // MUST HAVE 'three_tasks.txt' FILE IN WORKING DIRECTORY
    Path file_path = Paths.get("three_tasks.txt");

    Scanner file_scanner = new Scanner(file_path);

    task_list.load_task_list_from_file(file_scanner);
    assertEquals(3, task_list.get_number_of_task_items());

    assertEquals("Task1", task_list.get_nth_task_title(0));
    assertEquals("Task2", task_list.get_nth_task_title(1));
    assertEquals("Task3", task_list.get_nth_task_title(2));

    assertEquals("First task", task_list.get_nth_task_description(0));
    assertEquals("Second task", task_list.get_nth_task_description(1));
    assertEquals("Third task", task_list.get_nth_task_description(2));

    assertEquals("2020-01-01", task_list.get_nth_task_due_date(0));
    assertEquals("2020-01-02", task_list.get_nth_task_due_date(1));
    assertEquals("2020-01-03", task_list.get_nth_task_due_date(2));

  }

  /**************************** (21) ******************************/

  @Test
  public void uncompletingTaskItemChangesStatus()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-01-01", true);

    task_item = task_list.get_nth_task_item(0);
    assertEquals(true, task_item.get_task_completion());

    task_list.uncomplete_nth_task_item(0);
    assertEquals(false, task_item.get_task_completion());

  }

  /**************************** (22) ******************************/

  @Test
  public void uncompletingTaskItemFailsWithInvalidIndex()
  {

    task_list = new TaskList();
    task_list.add_task_item("Task1", "First Task", "2020-01-01", true);

    assertThrows(IndexOutOfBoundsException.class, () ->
    {task_list.uncomplete_nth_task_item(18);});

  }

} // END TaskListTest CLASS