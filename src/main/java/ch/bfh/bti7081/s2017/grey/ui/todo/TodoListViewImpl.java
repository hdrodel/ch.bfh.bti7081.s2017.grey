package ch.bfh.bti7081.s2017.grey.ui.todo;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.ui.tabs.PatientTabsPresenter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ken
 */
public class TodoListViewImpl extends VerticalLayout implements TodoListView {

  private static final long serialVersionUID = 1L;

  private PatientTabsPresenter patientTabsPresenter;

  private List<TodoListViewListener> listeners = new ArrayList<>();
  private Button newTask = null;

  /**
   * @param patientTabsPresenter parent Presenter
   */
  public TodoListViewImpl(PatientTabsPresenter patientTabsPresenter) {
    this.patientTabsPresenter = patientTabsPresenter;
  }

  @Override
  public void addTask(Task task) {
    TodoPresenter taskView = new TodoPresenter(patientTabsPresenter, task);
    addComponent((Component) taskView.getView());
  }

  @Override
  public void addNewTaskButton() {
    newTask = new Button("Neuer Task");
    newTask.addStyleName("newTaskButton");
    addComponent(newTask);
    newTask.addClickListener(event -> openForm());
  }

  private void openForm() {
    Window window = new Window("Neuer Task");
    window.setWidth(300.0f, Unit.PIXELS);
    window.setModal(true);
    FormLayout content = new FormLayout();
    content.setMargin(true);
    TextField taskName = new TextField();
    taskName.setPlaceholder("Taskbeschreibung");
    content.addComponent(taskName);
    Button saveTask = new Button("Task erstellen");
    content.addComponent(saveTask);
    saveTask.addClickListener(e -> {
      for (TodoListViewListener listener : listeners) {
        listener.saveNewTask(taskName.getValue(), window);
      }
    });

    window.setContent(content);
    UI.getCurrent().addWindow(window);
  }

  @Override
  public void addListener(TodoListViewListener listener) {
    listeners.add(listener);
  }

  @Override
  public void closeWindow(Window window) {
    window.close();
  }
}
