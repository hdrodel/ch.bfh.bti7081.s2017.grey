package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.TaskDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.TaskService;

import java.util.List;

/**
 * @Author Quentin
 */
public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    public TaskServiceImpl () {
        taskDao = new TaskDao();
    }

    @Override
    public Task findTaskById(long id){
        return taskDao.findTaskById(id);
    }

    @Override
    public void createTask(String name, Appointment appointment) {
        taskDao.createTask(name, appointment);
    }

    @Override
    public void addDrugsToTask(Task task, List<Drug> drugs, int amount, String units) {
        taskDao.addDrugsToTask(task, drugs, amount, units);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Override
    public List<Task> getTasksByAppointment(Appointment appointment) {
        return taskDao.getTasksByAppointment(appointment);
    }

    @Override
    public void setDuration(Task task, int amount) {
        taskDao.setDuration(task, amount);
    }

    @Override
    public void addToDuration(Task task, int amount) {
        int newAmount = task.getDuration() + amount;
        setDuration(task, newAmount);
    }

    @Override
    public void removeFromDuration(Task task, int amount) {
        int newAmount = task.getDuration() - amount;
        setDuration(task, newAmount);
    }

    @Override
    public void toggleActiveStatus(Task task, Boolean status) {
        taskDao.toggleActiveStatus(task, status);
    }
}
