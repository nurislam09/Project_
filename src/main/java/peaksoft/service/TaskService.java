package peaksoft.service;

import peaksoft.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();
    List<Task> getAllTask(Long lessonId);
    Task getTaskById(Long id);
    void saveTask(Long lessonId, Task task);
    void updateTask(Long id, Task task);
    void deleteTask(Long id);
}
