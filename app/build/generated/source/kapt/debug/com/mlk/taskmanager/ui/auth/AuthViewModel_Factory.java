package com.mlk.taskmanager.ui.auth;

import com.mlk.taskmanager.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public AuthViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new AuthViewModel_Factory(userRepositoryProvider);
  }

  public static AuthViewModel newInstance(UserRepository userRepository) {
    return new AuthViewModel(userRepository);
  }
}
