package com.mlk.taskmanager.di

import com.mlk.taskmanager.data.repository.RoutineRepository
import com.mlk.taskmanager.data.repository.RoutineRepositoryImpl
import com.mlk.taskmanager.data.repository.ProjectRepository
import com.mlk.taskmanager.data.repository.ProjectRepositoryImpl
import com.mlk.taskmanager.data.repository.UserRepository
import com.mlk.taskmanager.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindRoutineRepository(
        routineRepositoryImpl: RoutineRepositoryImpl
    ): RoutineRepository
    
    @Binds
    @Singleton
    abstract fun bindProjectRepository(
        projectRepositoryImpl: ProjectRepositoryImpl
    ): ProjectRepository
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}