package dev.Zerpyhis.TaskNotifier.exception;

public class TasksNotFound extends RuntimeException {
    public TasksNotFound(String message) {
        super(message);
    }
}
