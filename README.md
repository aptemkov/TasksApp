
# Tasks App
This app helps users stay organized by allowing them to create and manage tasks effortlessly. Each task can have a priority level, deadline, detailed description, and even an audio note for added convenience. With seamless cloud synchronization, users can access their tasks from any device without losing progress. The app leverages Backend-Driven UI technology for a dynamic and adaptive interface, and its reliability is reinforced by a comprehensive suite of unit tests.

## Technologies
- Android SDK
- Kotlin
- Jetpack Compose
- DivKit
- Kotlin Coroutines
- Flow
- Hilt
- Retrofit 2
- OkHttp
- GSON
- Coil
- Room 
- ViewModel
- MVVM
- Clean Architecture
- Mockito
- JUnit

## Want to try?
- Edit BEARER_API_KEY key at `local.properties`

# 📸Screenshots

<img src="https://github.com/aptemkov/TasksApp/blob/master/screenshots/screen1.jpg" width="200"> | 
<img src="https://github.com/aptemkov/TasksApp/blob/master/screenshots/screen2.jpg" width="200"> | 
<img src="https://github.com/aptemkov/TasksApp/blob/master/screenshots/screen3.jpg" width="200"> | 
<img src="https://github.com/aptemkov/TasksApp/blob/master/screenshots/screen4.jpg" width="200"> | 

## Gradle tasks

File app:build.gradle contains Telegram Bot token and chat id.
Also there are some Gradle tasks: 

./gradlew :app:printHello
./gradlew :app:printUserName
./gradlew :app:helloUser

And tasks for sending APK to telegram bot:

./gradlew :app:validateApkForDebug
./gradlew :app:validateApkForRelease

./gradlew :app:getApkDetailsForDebug
./gradlew :app:getApkDetailsForRelease

./gradlew :app:reportTelegramApkForDebug
./gradlew :app:reportTelegramApkForRelease
