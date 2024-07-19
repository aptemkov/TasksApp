Перед использованием нужно ввести BEARER_API_KEY в файле gradle.properties

В файле app:build.gradle вводится токен бота и чат
В терминале можно вызвать следующие таски из лекции по Gradle: 

./gradlew :app:printHello
./gradlew :app:printUserName
./gradlew :app:helloUser

А также таски для отправки apk файла:

./gradlew :app:validateApkForDebug
./gradlew :app:validateApkForRelease

./gradlew :app:getApkDetailsForDebug
./gradlew :app:getApkDetailsForRelease

./gradlew :app:reportTelegramApkForDebug
./gradlew :app:reportTelegramApkForRelease