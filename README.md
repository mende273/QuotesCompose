# QuotesCompose
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white&style=for-the-badge)
![Jetpack Compose](https://img.shields.io/static/v1?style=for-the-badge&message=Jetpack+Compose&color=4285F4&logo=Jetpack+Compose&logoColor=FFFFFF&label=)
![Android](https://img.shields.io/badge/Api%2026+-3DDC84?logo=android&logoColor=white&style=for-the-badge)
![Release](https://img.shields.io/github/v/release/mende273/quotescompose?logo=github&color=lightblue&logoColor=white&labelColor=black&style=for-the-badge)

Android application demonstrating the use of JetPack Compose with Hilt, Navigation Component and MVVM architecture

## App Screenshots
| Light Theme | Dark Theme |
| --- | --- |
| <img src="/screenshots/1_light.png" width="250"> | <img src="/screenshots/1_dark.png" width="250"> |
|  |  |
| <img src="/screenshots/2_light.png" width="250"> | <img src="/screenshots/2_dark.png" width="250"> |
|  |  |
| <img src="/screenshots/3_light.png" width="250"> | <img src="/screenshots/3_dark.png" width="250"> |

## Api
The project is using the free api from [quotable.io](https://github.com/lukePeavey/quotable)

## PreBuild
The preBuild depends on 2 tasks: <b>ktlint</b> and <b>Detekt</b>. You can manually run the tasks with <code>./gradlew ktlintFormat</code> and <code>./gradlew detekt</code>

## Built With
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow) - Official Kotlin's tooling for performing asynchronous work.
- [Android Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers build state-of-the-art applications.
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Navigation Compose is a framework for navigating between composables while taking advantage of the Navigation component’s infrastructure and features.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel is designed to store and manage UI-related data in a lifecycle conscious way.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room library provides an abstraction layer over SQLite to allow for more robust database access.
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android.
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for Jetpack Compose.
- [OkHttp](https://github.com/square/okhttp) - An HTTP client for making network calls.
- [Retrofit](https://github.com/square/retrofit) - A library for building REST API clients.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

## Static Code Analysis
- [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - A library for formatting Kotlin code according to official guidelines.
- [Detekt](https://github.com/detekt/detekt) - A static code analysis library for Kotlin.
