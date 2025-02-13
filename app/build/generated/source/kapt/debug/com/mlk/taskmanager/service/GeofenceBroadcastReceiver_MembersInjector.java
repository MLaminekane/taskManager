package com.mlk.taskmanager.service;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class GeofenceBroadcastReceiver_MembersInjector implements MembersInjector<GeofenceBroadcastReceiver> {
  private final Provider<LocationReminderService> locationReminderServiceProvider;

  public GeofenceBroadcastReceiver_MembersInjector(
      Provider<LocationReminderService> locationReminderServiceProvider) {
    this.locationReminderServiceProvider = locationReminderServiceProvider;
  }

  public static MembersInjector<GeofenceBroadcastReceiver> create(
      Provider<LocationReminderService> locationReminderServiceProvider) {
    return new GeofenceBroadcastReceiver_MembersInjector(locationReminderServiceProvider);
  }

  @Override
  public void injectMembers(GeofenceBroadcastReceiver instance) {
    injectLocationReminderService(instance, locationReminderServiceProvider.get());
  }

  @InjectedFieldSignature("com.mlk.taskmanager.service.GeofenceBroadcastReceiver.locationReminderService")
  public static void injectLocationReminderService(GeofenceBroadcastReceiver instance,
      LocationReminderService locationReminderService) {
    instance.locationReminderService = locationReminderService;
  }
}
