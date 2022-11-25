package peaksoft.service.serviceimpl;

import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.getAllTask();
    }

    @Override
    public List<Task> getAllTask(Long lessonId) {
        return taskRepository.getAllTask(lessonId);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public void saveTask(Long lessonId, Task task) {
        taskRepository.saveTask(lessonId, task);
    }

    @Override
    public void updateTask(Long id, Task task) {
        taskRepository.updateTask(id, task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }
}