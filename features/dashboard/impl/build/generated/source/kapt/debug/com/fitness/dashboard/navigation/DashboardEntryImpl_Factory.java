package com.fitness.dashboard.navigation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DashboardEntryImpl_Factory implements Factory<DashboardEntryImpl> {
  @Override
  public DashboardEntryImpl get() {
    return newInstance();
  }

  public static DashboardEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DashboardEntryImpl newInstance() {
    return new DashboardEntryImpl();
  }

  private static final class InstanceHolder {
    private static final DashboardEntryImpl_Factory INSTANCE = new DashboardEntryImpl_Factory();
  }
}
