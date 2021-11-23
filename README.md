# RoomHiltMVVM

This sample project is a demo application that is based on modern Android architectures. 
It will fetch the data from the network and cache it using the Room DB via MVVM pattern. We are using the API's for this purpose.

## App Architecture
The app uses clean architecture and MVVM pattern.


##  Core Features
* Implement the MVVM Architecture Pattern
* Mediate data operations with the Repository Pattern
* Observe and respond to changing data using LiveData.
* Used Kotlin Coroutines
* Uses Room for Data persistence
* ViewModel
* Dagger Hilt for dependency injection.
  * Recyclerview DiffUtils-- To improve Recyclerview performance
* Handle app navigation with Navigation Component
* Minimum SDK level 23
* Timber Logging library

## Core Libraries
*   [Dagger 2](https://dagger.dev/) for dependency injection
*   [Retrofit 2](https://github.com/square/retrofit) and [RxJava](https://github.com/ReactiveX/RxJava) for networking
*   [Gson](https://github.com/google/gson) for parsing JSON
*   [Navigation](https://developer.android.com/jetpack/compose/navigation) to navigate between screens
*   Kotlin Coroutines

## List of recommendations for future improvements
* Jetpack Compose for building UI. 

## Testing
* I added local unit tests to test the usecases
* To test the REST API interface defined for Retrofit I made use of [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
* for UI testing I used [Espresso](https://developer.android.com/training/testing/espresso)


