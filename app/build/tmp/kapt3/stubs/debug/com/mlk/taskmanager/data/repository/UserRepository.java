package com.mlk.taskmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0004J,\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u0014H\u00a6@\u00a2\u0006\u0002\u0010\u0004J,\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0012J\"\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u0012\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"Lcom/mlk/taskmanager/data/repository/UserRepository;", "", "getCurrentUserId", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserById", "Lkotlinx/coroutines/flow/Flow;", "Lcom/mlk/taskmanager/data/model/User;", "userId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUserLoggedIn", "", "loginUser", "Lkotlin/Result;", "email", "", "password", "loginUser-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "registerUser", "registerUser-0E7RQCE", "updateUserProfile", "name", "profilePictureUrl", "app_debug"})
public abstract interface UserRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserById(long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.mlk.taskmanager.data.model.User>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isUserLoggedIn(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentUserId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateUserProfile(@org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String profilePictureUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}