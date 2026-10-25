# Walkthrough - CP3406/CP5307 Assessment 1: Utility App

We successfully extended the starter template to implement all assessment requirements (ViewModel, Repository pattern, and Retrofit networking) while preserving the required two-screen structure, bottom navigation, and Material 3 design.

## Changes Made

### Gradle Configuration
#### [MODIFY] [build.gradle.kts](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/build.gradle.kts)
- Added Jetpack Lifecycle ViewModel Compose (`lifecycle-viewmodel-compose`).
- Added Retrofit (`retrofit`) and Gson converter (`converter-gson`) dependencies for network requests.

### Data Layer (Retrofit & Repository Pattern)
#### [NEW] [UtilityResponse.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/data/UtilityResponse.kt)
- Data class for parsing public API JSON responses (Cat Facts API).
#### [NEW] [UtilityApiService.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/data/UtilityApiService.kt)
- Retrofit HTTP interface defining the `getFact()` suspend function.
#### [NEW] [UtilityRepository.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/data/UtilityRepository.kt)
- Repository encapsulating Retrofit instance creation and network error handling.

### Presentation Layer (ViewModel & UI)
#### [NEW] [UtilityViewModel.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/ui/UtilityViewModel.kt)
- Lifecycle ViewModel managing `utilityData`, `isLoading` state, and coroutine execution (`viewModelScope.launch`).
#### [MODIFY] [MainActivity.kt](file:///C:/Users/BaNGla pC/Downloads/MobileTech_UtilityApp-main/app/src/main/java/au/edu/jcu/assessment/utilityapp/MainActivity.kt)
- Preserved existing `UtilityApp()` bottom navigation and `SettingsScreen()`.
- Updated `UtilityScreen()` to observe `UtilityViewModel` and fetch live data from the network API when clicked.

## Verification Results

### Automated Build Verification
- Ran `./gradlew app:assembleDebug` -> **Success (Build finished successfully)**.
