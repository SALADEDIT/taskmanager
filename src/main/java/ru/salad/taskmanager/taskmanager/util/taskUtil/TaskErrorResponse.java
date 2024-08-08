package ru.salad.taskmanager.taskmanager.util.taskUtil;

import lombok.Data;

@Data
public class TaskErrorResponse {
    private String message;
    private Long timestamp;

    public TaskErrorResponse(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
