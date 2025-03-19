package com.mlk.taskmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rH\u0016J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/mlk/taskmanager/data/repository/ProjectRepositoryImpl;", "Lcom/mlk/taskmanager/data/repository/ProjectRepository;", "projectDao", "Lcom/mlk/taskmanager/data/dao/ProjectDao;", "(Lcom/mlk/taskmanager/data/dao/ProjectDao;)V", "decrementTaskCount", "", "projectId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProject", "id", "getAllProjects", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/mlk/taskmanager/data/model/Project;", "getProjectById", "incrementTaskCount", "insertProject", "", "project", "(Lcom/mlk/taskmanager/data/model/Project;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProject", "app_debug"})
public final class ProjectRepositoryImpl implements com.mlk.taskmanager.data.repository.ProjectRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.dao.ProjectDao projectDao = null;
    
    @javax.inject.Inject()
    public ProjectRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.dao.ProjectDao projectDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Project>> getAllProjects() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getProjectById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.mlk.taskmanager.data.model.Project> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertProject(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Project project, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateProject(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Project project, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteProject(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object incrementTaskCount(int projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object decrementTaskCount(int projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}