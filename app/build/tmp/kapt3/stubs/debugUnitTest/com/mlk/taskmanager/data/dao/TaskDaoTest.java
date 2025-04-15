package com.mlk.taskmanager.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0007J\f\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0007J\f\u0010\t\u001a\u00060\u0006j\u0002`\u0007H\u0007J\f\u0010\n\u001a\u00060\u0006j\u0002`\u0007H\u0007J\f\u0010\u000b\u001a\u00060\u0006j\u0002`\u0007H\u0007J\f\u0010\f\u001a\u00060\u0006j\u0002`\u0007H\u0007J\b\u0010\r\u001a\u00020\u0006H\u0007J\f\u0010\u000e\u001a\u00060\u0006j\u0002`\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/mlk/taskmanager/data/dao/TaskDaoTest;", "", "()V", "taskDao", "Lcom/mlk/taskmanager/data/dao/TaskDao;", "deleteTask should call dao delete method", "", "Lkotlinx/coroutines/test/TestResult;", "getActiveTasks should return non-completed tasks", "getAllTasks should return flow of tasks", "getAllTasksSync should return tasks synchronously", "getTaskById should return task with specified id", "insertTask should return id", "setup", "updateTask should call dao update method", "app_debugUnitTest"})
public final class TaskDaoTest {
    private com.mlk.taskmanager.data.dao.TaskDao taskDao;
    
    public TaskDaoTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
}