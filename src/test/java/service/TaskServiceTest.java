package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.entity.Status;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;
import ru.salad.taskmanager.taskmanager.services.TaskService;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Task.class)
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    private static final Integer TASK_ID = 1;
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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



    @Test
    void testGetTaskById_Success() {
//        doReturn(Optional.of(new Task(TASK_ID))).when(taskRepository.findById(TASK_ID));

        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(new Task(TASK_ID)));
        Optional<Task> actualResult = taskService.getTaskById(TASK_ID);

        assertTrue(actualResult.isPresent());

        Task expectedResult = new Task(TASK_ID);
        assertEquals(expectedResult, actualResult.get());
//       actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
