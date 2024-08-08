package ru.salad.taskmanager.taskmanager.util.groupUtil;

import lombok.Data;

@Data
public class GroupErrorResponse {
    private String message;
    private Long timestamp;

    public GroupErrorResponse(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}