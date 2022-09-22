package taskmanagementt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import taskmanagementt.controller.entity.Task;
import taskmanagementt.controller.entity.User;
import taskmanagementt.repository.TaskRepository;
import taskmanagementt.repository.UserRepository;

import java.util.List;

@Controller
public class TaskController {

    @Autowired

    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/tasks/add")
    public String AddTaskPage(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users",users);
        return "addTask";
    }

    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute Task task){
        if (task.getUser()!= null && task.getUser().getId()==0){
            task.setUser(null);
        }
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String tasksPage(ModelMap modelMap){
        List<Task> tasks = taskRepository.findAll();
        modelMap.addAttribute("tasks",tasks);
        return "tasks";
    }
}
