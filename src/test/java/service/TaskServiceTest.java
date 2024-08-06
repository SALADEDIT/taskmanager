package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;
import ru.salad.taskmanager.taskmanager.services.TaskService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@SpringBootTest()
@ContextConfiguration(classes = TaskService.class)
public class TaskServiceTest {
    private static final Integer TASK_ID = 1;
    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private CompanyRepository companyRepository;
    @Autowired
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTaskById_Success() {
        Optional<Task> toBeReturned = Optional.of(new Task(TASK_ID));

        doReturn(toBeReturned).when(taskRepository).findById(TASK_ID);

        Optional<Task> taskById = taskService.getTaskById(TASK_ID);

        assertThat(taskById).isPresent();
        assertThat(taskById.get()).isEqualTo(toBeReturned.get());
    }

//    @Test
//
//    void testGetTaskById_SuccessTR() {
//        Optional<Task> toBeReturned = Optional.of(new Task(TASK_ID));
//
//        doReturn(toBeReturned).when(taskRepository).findById(TASK_ID);
//
//        Optional<Task> taskById = taskService.getTaskById(TASK_ID);
//
//        assertThat(taskById).isPresent();
//        assertThat(taskById.get()).isEqualTo(toBeReturned.get());
//    }



//    @Test
//    void testCreateTask() {
//        // Arrange
//        Task task = new Task("Test Task", "Test Description", Instant.now().plusSeconds(3600),
//                Status.IN_PROGRESS, new Company());
//        when(taskRepository.save(task)).thenReturn(task);
//
//        // Act
//        Task createdTask = taskService.createTask(task);
//
//        // Assert
//        assertThat(createdTask).isNotNull();
//        assertThat(createdTask.getTitle()).isEqualTo("Test Task");
//        assertThat(createdTask.getDescription()).isEqualTo("Test Description");
//        assertThat(createdTask.getStatus()).isEqualTo(Status.IN_PROGRESS);
//        verify(taskRepository, times(1)).save(task);

//    }

    //    @Test
//    void testDeleteTask() {
//        // Arrange
//        Integer taskId = 1;
//
//        // Act
//        taskService.deleteTask(taskId);
//
//        // Assert
//        verify(taskRepository, times(1)).deleteById(taskId);

//    }

}
