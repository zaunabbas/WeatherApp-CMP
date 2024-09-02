This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


<h1 align="center">Weather Demo App</h1>
 
A simple Weather forecast demo app using [OpenWeatherMap API](https://openweathermap.org/) with modern tech-stacks KMP/CMP, MVVM-Clean- Architecture and UI built with Jetpack Compose.


## Download
Go to the [Download Link](https://drive.google.com/file/d/1qVkXiJYj34AfvbM-7W_ZjcfDV7pdDWT_/view?usp=sharing) to download the latest APK.

## Screenshots
<p align="center">
<img src="/preview/preview0.png" width="32%"/>
<img src="/preview/preview1.png" width="32%"/>
<img src="/preview/preview2.png" width="32%"/>
<img src="/preview/preview3.png" width="32%"/>
</p>

## Tech stack & Open-source libraries
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection.
- JetPack
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Jetpack Compose - Modern UI Tool Kit.
- Architecture
  - MVVM + Clean Architecture (View - ViewModel - Model)
  - Repository pattern
- [Ktor Client](https://ktor.io/docs/client-create-multiplatform-application.html) - construct the REST APIs and paging network data.
- [Kottie](https://github.com/ismai117/kottie) - Render After Effects animations natively on KMP
- Other Libs for, Korlinx dateTime & Louid-Country

## Features:
- Load Current weather
- Load Today's hourly weather
