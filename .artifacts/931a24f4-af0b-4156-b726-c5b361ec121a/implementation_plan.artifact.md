# Implementation Plan - CP3406/CP5307 Assessment 1: Utility App

This implementation plan outlines the steps to extend the starter template into a fully functional Utility App adhering to assignment requirements (ViewModel, Repository pattern, Retrofit networking) while keeping the app simple and aligned with teacher instructions.

## User Review Required

> [!IMPORTANT]
> - **API Choice**: We propose using a simple public API (e.g., Cat Facts API `https://catfact.ninja/` or Quotable API) which requires no API key, authentication, or complex setup.
> - **Dependency Injection**: We will use clean manual DI / ViewModel factory pattern to keep dependencies lightweight and robust without adding complex Dagger/Hilt boilerplate.

## Proposed Changes

### Gradle Dependencies
#### [MODIFY] [build.gradle.kts](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/build.gradle.kts)
- Add Jetpack Lifecycle ViewModel Compose dependency.
- Add Retrofit and Gson converter dependencies for network requests.

### Data Layer (Retrofit & Repository)
#### [NEW] [UtilityApiService.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/data/UtilityApiService.kt)
- Define Retrofit API interface for fetching utility data (e.g., random fact).
#### [NEW] [UtilityResponse.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/data/UtilityResponse.kt)
- Data class matching API response JSON.
#### [NEW] [UtilityRepository.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/data/UtilityRepository.kt)
- Repository class abstracting data retrieval from Retrofit service.

### Presentation Layer (ViewModel & UI)
#### [NEW] [UtilityViewModel.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/ui/UtilityViewModel.kt)
- ViewModel managing loading state, fetched data, and error handling using Coroutines (`viewModelScope`).
#### [MODIFY] [MainActivity.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/MainActivity.kt)
- Retain existing `UtilityApp()` bottom navigation and `SettingsScreen()`.
- Update `UtilityScreen()` to observe `UtilityViewModel` state and display fetched data with a refresh/fetch button.

## Verification Plan

### Automated Tests
- Run Gradle build and tests (`gradle_build("app:assembleDebug")`).

### Manual Verification
- Deploy and verify bottom navigation switches smoothly between Utility and Settings screens.
- Verify Retrofit successfully fetches data from the API and displays it on the Utility screen.
