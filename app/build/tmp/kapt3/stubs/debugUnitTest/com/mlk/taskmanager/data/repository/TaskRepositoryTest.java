package com.mlk.taskmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0007J\f\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0007J\f\u0010\r\u001a\u00060\nj\u0002`\u000bH\u0007J\f\u0010\u000e\u001a\u00060\nj\u0002`\u000bH\u0007J\f\u0010\u000f\u001a\u00060\nj\u0002`\u000bH\u0007J\f\u0010\u0010\u001a\u00060\nj\u0002`\u000bH\u0007J\b\u0010\u0011\u001a\u00020\nH\u0007J\f\u0010\u0012\u001a\u00060\nj\u0002`\u000bH\u0007J\f\u0010\u0013\u001a\u00060\nj\u0002`\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/mlk/taskmanager/data/repository/TaskRepositoryTest;", "", "()V", "projectRepository", "Lcom/mlk/taskmanager/data/repository/ProjectRepository;", "taskDao", "Lcom/mlk/taskmanager/data/dao/TaskDao;", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepositoryImpl;", "deleteTask should call DAO delete method", "", "Lkotlinx/coroutines/test/TestResult;", "deleteTask should decrement project task count when projectId is not null", "getAllTasks should return flow of tasks from DAO", "getAllTasksSync should return list of tasks from DAO", "insertTask should call DAO insert method", "insertTask should update project task count when projectId is not null", "setup", "updateTask should call DAO update method", "updateTask should update project counters when projectId changes", "app_debugUnitTest"})
public final class TaskRepositoryTest {
    private com.mlk.taskmanager.data.dao.TaskDao taskDao;
    private com.mlk.taskmanager.data.repository.ProjectRepository projectRepository;
    private com.mlk.taskmanager.data.repository.TaskRepositoryImpl taskRepository;
    
    public TaskRepositoryTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
}