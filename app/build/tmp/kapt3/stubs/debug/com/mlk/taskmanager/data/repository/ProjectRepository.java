package com.mlk.taskmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH&J\u0018\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0013"}, d2 = {"Lcom/mlk/taskmanager/data/repository/ProjectRepository;", "", "decrementTaskCount", "", "projectId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProject", "id", "getAllProjects", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/mlk/taskmanager/data/model/Project;", "getProjectById", "incrementTaskCount", "insertProject", "project", "(Lcom/mlk/taskmanager/data/model/Project;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProject", "app_debug"})
public abstract interface ProjectRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Project>> getAllProjects();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProjectById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.mlk.taskmanager.data.model.Project> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertProject(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Project project, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProject(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Project project, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteProject(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object incrementTaskCount(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object decrementTaskCount(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}