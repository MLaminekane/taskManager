package com.mlk.taskmanager;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mlk.taskmanager.data.api.WeatherApiService;
import com.mlk.taskmanager.data.dao.ProjectDao;
import com.mlk.taskmanager.data.dao.RoutineDao;
import com.mlk.taskmanager.data.local.TaskDatabase;
import com.mlk.taskmanager.data.repository.ProjectRepository;
import com.mlk.taskmanager.data.repository.ProjectRepositoryImpl;
import com.mlk.taskmanager.data.repository.RoutineRepositoryImpl;
import com.mlk.taskmanager.data.repository.SettingsRepository;
import com.mlk.taskmanager.data.repository.TaskRepository;
import com.mlk.taskmanager.data.repository.WeatherRepository;
import com.mlk.taskmanager.di.ApiModule_ProvideOkHttpClientFactory;
import com.mlk.taskmanager.di.ApiModule_ProvideRetrofitFactory;
import com.mlk.taskmanager.di.ApiModule_ProvideWeatherApiServiceFactory;
import com.mlk.taskmanager.di.AppModule_ProvideNotificationManagerFactory;
import com.mlk.taskmanager.di.AppModule_ProvideSettingsRepositoryFactory;
import com.mlk.taskmanager.di.AppModule_ProvideTaskRepositoryFactory;
import com.mlk.taskmanager.di.AppModule_ProvideWeatherRepositoryFactory;
import com.mlk.taskmanager.di.DatabaseModule_ProvideProjectDaoFactory;
import com.mlk.taskmanager.di.DatabaseModule_ProvideRoutineDaoFactory;
import com.mlk.taskmanager.di.DatabaseModule_ProvideTaskDatabaseFactory;
import com.mlk.taskmanager.di.ServiceModule_ProvideNotificationManagerFactory;
import com.mlk.taskmanager.service.BootCompletedReceiver;
import com.mlk.taskmanager.service.BootCompletedReceiver_MembersInjector;
import com.mlk.taskmanager.service.CalendarSyncService;
import com.mlk.taskmanager.service.GeofenceBroadcastReceiver;
import com.mlk.taskmanager.service.GeofenceBroadcastReceiver_MembersInjector;
import com.mlk.taskmanager.service.LocationReminderService;
import com.mlk.taskmanager.service.TimeNotificationReceiver;
import com.mlk.taskmanager.service.TimeNotificationReceiver_MembersInjector;
import com.mlk.taskmanager.ui.MainActivity;
import com.mlk.taskmanager.ui.calendar.CalendarViewModel;
import com.mlk.taskmanager.ui.calendar.CalendarViewModel_HiltModules_KeyModule_ProvideFactory;
import com.mlk.taskmanager.ui.home.HomeViewModel;
import com.mlk.taskmanager.ui.home.HomeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.mlk.taskmanager.ui.pomodoro.PomodoroViewModel;
import com.mlk.taskmanager.ui.pomodoro.PomodoroViewModel_HiltModules_KeyModule_ProvideFactory;
import com.mlk.taskmanager.ui.project.ProjectDetailViewModel;
import com.mlk.taskmanager.ui.project.ProjectDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import com.mlk.taskmanager.ui.routines.RoutineDetailViewModel;
import com.mlk.taskmanager.ui.routines.RoutineDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import com.mlk.taskmanager.ui.routines.RoutinesViewModel;
import com.mlk.taskmanager.ui.routines.RoutinesViewModel_HiltModules_KeyModule_ProvideFactory;
import com.mlk.taskmanager.ui.settings.SettingsViewModel;
import com.mlk.taskmanager.ui.settings.SettingsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.mlk.taskmanager.ui.tasks.TasksViewModel;
import com.mlk.taskmanager.ui.tasks.TasksViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerTaskManagerApplication_HiltComponents_SingletonC {
  private DaggerTaskManagerApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public TaskManagerApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements TaskManagerApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public TaskManagerApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements TaskManagerApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public TaskManagerApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements TaskManagerApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public TaskManagerApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements TaskManagerApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TaskManagerApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements TaskManagerApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TaskManagerApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements TaskManagerApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public TaskManagerApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements TaskManagerApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public TaskManagerApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends TaskManagerApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends TaskManagerApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends TaskManagerApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends TaskManagerApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of(CalendarViewModel_HiltModules_KeyModule_ProvideFactory.provide(), HomeViewModel_HiltModules_KeyModule_ProvideFactory.provide(), PomodoroViewModel_HiltModules_KeyModule_ProvideFactory.provide(), ProjectDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide(), RoutineDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide(), RoutinesViewModel_HiltModules_KeyModule_ProvideFactory.provide(), SettingsViewModel_HiltModules_KeyModule_ProvideFactory.provide(), TasksViewModel_HiltModules_KeyModule_ProvideFactory.provide());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends TaskManagerApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<CalendarViewModel> calendarViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<PomodoroViewModel> pomodoroViewModelProvider;

    private Provider<ProjectDetailViewModel> projectDetailViewModelProvider;

    private Provider<RoutineDetailViewModel> routineDetailViewModelProvider;

    private Provider<RoutinesViewModel> routinesViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private Provider<TasksViewModel> tasksViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.calendarViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.pomodoroViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.projectDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.routineDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.routinesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.tasksViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(8).put("com.mlk.taskmanager.ui.calendar.CalendarViewModel", ((Provider) calendarViewModelProvider)).put("com.mlk.taskmanager.ui.home.HomeViewModel", ((Provider) homeViewModelProvider)).put("com.mlk.taskmanager.ui.pomodoro.PomodoroViewModel", ((Provider) pomodoroViewModelProvider)).put("com.mlk.taskmanager.ui.project.ProjectDetailViewModel", ((Provider) projectDetailViewModelProvider)).put("com.mlk.taskmanager.ui.routines.RoutineDetailViewModel", ((Provider) routineDetailViewModelProvider)).put("com.mlk.taskmanager.ui.routines.RoutinesViewModel", ((Provider) routinesViewModelProvider)).put("com.mlk.taskmanager.ui.settings.SettingsViewModel", ((Provider) settingsViewModelProvider)).put("com.mlk.taskmanager.ui.tasks.TasksViewModel", ((Provider) tasksViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<String, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.mlk.taskmanager.ui.calendar.CalendarViewModel 
          return (T) new CalendarViewModel(singletonCImpl.provideTaskRepositoryProvider.get());

          case 1: // com.mlk.taskmanager.ui.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.provideTaskRepositoryProvider.get(), singletonCImpl.routineRepositoryImplProvider.get(), singletonCImpl.bindProjectRepositoryProvider.get(), singletonCImpl.provideWeatherRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.mlk.taskmanager.ui.pomodoro.PomodoroViewModel 
          return (T) new PomodoroViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.mlk.taskmanager.ui.project.ProjectDetailViewModel 
          return (T) new ProjectDetailViewModel(singletonCImpl.bindProjectRepositoryProvider.get(), singletonCImpl.provideTaskRepositoryProvider.get());

          case 4: // com.mlk.taskmanager.ui.routines.RoutineDetailViewModel 
          return (T) new RoutineDetailViewModel(singletonCImpl.routineRepositoryImplProvider.get(), singletonCImpl.calendarSyncServiceProvider.get(), singletonCImpl.provideSettingsRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 5: // com.mlk.taskmanager.ui.routines.RoutinesViewModel 
          return (T) new RoutinesViewModel(singletonCImpl.routineRepositoryImplProvider.get(), singletonCImpl.calendarSyncServiceProvider.get());

          case 6: // com.mlk.taskmanager.ui.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.provideSettingsRepositoryProvider.get(), singletonCImpl.calendarSyncServiceProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.mlk.taskmanager.ui.tasks.TasksViewModel 
          return (T) new TasksViewModel(singletonCImpl.provideTaskRepositoryProvider.get(), singletonCImpl.locationReminderServiceProvider.get(), singletonCImpl.provideNotificationManagerProvider2.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends TaskManagerApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends TaskManagerApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends TaskManagerApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<TaskDatabase> provideTaskDatabaseProvider;

    private Provider<ProjectDao> provideProjectDaoProvider;

    private Provider<ProjectRepositoryImpl> projectRepositoryImplProvider;

    private Provider<ProjectRepository> bindProjectRepositoryProvider;

    private Provider<TaskRepository> provideTaskRepositoryProvider;

    private Provider<NotificationManager> provideNotificationManagerProvider;

    private Provider<LocationReminderService> locationReminderServiceProvider;

    private Provider<com.mlk.taskmanager.service.NotificationManager> provideNotificationManagerProvider2;

    private Provider<RoutineDao> provideRoutineDaoProvider;

    private Provider<RoutineRepositoryImpl> routineRepositoryImplProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<WeatherApiService> provideWeatherApiServiceProvider;

    private Provider<WeatherRepository> provideWeatherRepositoryProvider;

    private Provider<CalendarSyncService> calendarSyncServiceProvider;

    private Provider<SettingsRepository> provideSettingsRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideTaskDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<TaskDatabase>(singletonCImpl, 1));
      this.provideProjectDaoProvider = DoubleCheck.provider(new SwitchingProvider<ProjectDao>(singletonCImpl, 3));
      this.projectRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 2);
      this.bindProjectRepositoryProvider = DoubleCheck.provider((Provider) projectRepositoryImplProvider);
      this.provideTaskRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TaskRepository>(singletonCImpl, 0));
      this.provideNotificationManagerProvider = DoubleCheck.provider(new SwitchingProvider<NotificationManager>(singletonCImpl, 6));
      this.locationReminderServiceProvider = DoubleCheck.provider(new SwitchingProvider<LocationReminderService>(singletonCImpl, 5));
      this.provideNotificationManagerProvider2 = DoubleCheck.provider(new SwitchingProvider<com.mlk.taskmanager.service.NotificationManager>(singletonCImpl, 4));
      this.provideRoutineDaoProvider = DoubleCheck.provider(new SwitchingProvider<RoutineDao>(singletonCImpl, 8));
      this.routineRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<RoutineRepositoryImpl>(singletonCImpl, 7));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 12));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 11));
      this.provideWeatherApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<WeatherApiService>(singletonCImpl, 10));
      this.provideWeatherRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<WeatherRepository>(singletonCImpl, 9));
      this.calendarSyncServiceProvider = DoubleCheck.provider(new SwitchingProvider<CalendarSyncService>(singletonCImpl, 13));
      this.provideSettingsRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<SettingsRepository>(singletonCImpl, 14));
    }

    @Override
    public void injectTaskManagerApplication(TaskManagerApplication taskManagerApplication) {
    }

    @Override
    public void injectBootCompletedReceiver(BootCompletedReceiver bootCompletedReceiver) {
      injectBootCompletedReceiver2(bootCompletedReceiver);
    }

    @Override
    public void injectGeofenceBroadcastReceiver(
        GeofenceBroadcastReceiver geofenceBroadcastReceiver) {
      injectGeofenceBroadcastReceiver2(geofenceBroadcastReceiver);
    }

    @Override
    public void injectTimeNotificationReceiver(TimeNotificationReceiver timeNotificationReceiver) {
      injectTimeNotificationReceiver2(timeNotificationReceiver);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private BootCompletedReceiver injectBootCompletedReceiver2(BootCompletedReceiver instance) {
      BootCompletedReceiver_MembersInjector.injectTaskRepository(instance, provideTaskRepositoryProvider.get());
      BootCompletedReceiver_MembersInjector.injectNotificationManager(instance, provideNotificationManagerProvider2.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private GeofenceBroadcastReceiver injectGeofenceBroadcastReceiver2(
        GeofenceBroadcastReceiver instance) {
      GeofenceBroadcastReceiver_MembersInjector.injectLocationReminderService(instance, locationReminderServiceProvider.get());
      GeofenceBroadcastReceiver_MembersInjector.injectTaskRepository(instance, provideTaskRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private TimeNotificationReceiver injectTimeNotificationReceiver2(
        TimeNotificationReceiver instance) {
      TimeNotificationReceiver_MembersInjector.injectNotificationManager(instance, provideNotificationManagerProvider2.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.mlk.taskmanager.data.repository.TaskRepository 
          return (T) AppModule_ProvideTaskRepositoryFactory.provideTaskRepository(singletonCImpl.provideTaskDatabaseProvider.get(), singletonCImpl.bindProjectRepositoryProvider.get());

          case 1: // com.mlk.taskmanager.data.local.TaskDatabase 
          return (T) DatabaseModule_ProvideTaskDatabaseFactory.provideTaskDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.mlk.taskmanager.data.repository.ProjectRepositoryImpl 
          return (T) new ProjectRepositoryImpl(singletonCImpl.provideProjectDaoProvider.get());

          case 3: // com.mlk.taskmanager.data.dao.ProjectDao 
          return (T) DatabaseModule_ProvideProjectDaoFactory.provideProjectDao(singletonCImpl.provideTaskDatabaseProvider.get());

          case 4: // com.mlk.taskmanager.service.NotificationManager 
          return (T) AppModule_ProvideNotificationManagerFactory.provideNotificationManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.locationReminderServiceProvider.get());

          case 5: // com.mlk.taskmanager.service.LocationReminderService 
          return (T) new LocationReminderService(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideNotificationManagerProvider.get());

          case 6: // android.app.NotificationManager 
          return (T) ServiceModule_ProvideNotificationManagerFactory.provideNotificationManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.mlk.taskmanager.data.repository.RoutineRepositoryImpl 
          return (T) new RoutineRepositoryImpl(singletonCImpl.provideRoutineDaoProvider.get());

          case 8: // com.mlk.taskmanager.data.dao.RoutineDao 
          return (T) DatabaseModule_ProvideRoutineDaoFactory.provideRoutineDao(singletonCImpl.provideTaskDatabaseProvider.get());

          case 9: // com.mlk.taskmanager.data.repository.WeatherRepository 
          return (T) AppModule_ProvideWeatherRepositoryFactory.provideWeatherRepository(singletonCImpl.provideWeatherApiServiceProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 10: // com.mlk.taskmanager.data.api.WeatherApiService 
          return (T) ApiModule_ProvideWeatherApiServiceFactory.provideWeatherApiService(singletonCImpl.provideRetrofitProvider.get());

          case 11: // retrofit2.Retrofit 
          return (T) ApiModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 12: // okhttp3.OkHttpClient 
          return (T) ApiModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 13: // com.mlk.taskmanager.service.CalendarSyncService 
          return (T) new CalendarSyncService(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.routineRepositoryImplProvider.get());

          case 14: // com.mlk.taskmanager.data.repository.SettingsRepository 
          return (T) AppModule_ProvideSettingsRepositoryFactory.provideSettingsRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
