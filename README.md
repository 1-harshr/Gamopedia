# Gamopedia

A comprehensive video game database and encyclopedia app built with Kotlin Multiplatform and Compose Multiplatform.

## What is Gamopedia?

Gamopedia is a cross-platform application that provides users with access to a vast database of video games. The app allows users to discover, search, and explore detailed information about thousands of games across different platforms and genres.

## Features

- **Game Browse**: Browse through a curated list of popular and trending video games
- **Game Search**: Search for specific games using keywords and filters
- **Detailed Game Information**: View comprehensive details for each game including:
  - Game description and background images
  - Supported platforms (PC, PlayStation, Xbox, Nintendo, etc.)
  - Developers and publishers
  - Available stores and purchase options
  - User ratings and reviews
  - Genre tags and categories
- **Favorites**: Save your favorite games for quick access
- **Cross-Platform**: Available on Android, iOS, Desktop (Windows/Mac/Linux), and Web browsers

## Data Source

Gamopedia uses the [RAWG Video Games Database API](https://rawg.io/apidocs) to provide up-to-date and comprehensive game information from one of the largest video game databases available.

## Platforms Supported

- **Android**: Native Android app
- **iOS**: Native iOS app  
- **Desktop**: Windows, macOS, and Linux applications
- **Web**: Browser-based web application (WebAssembly)

## Technologies

- Kotlin Multiplatform
- Compose Multiplatform
- Ktor
- Koin
- SQLDelight
- Coil
- Kotlinx Serialization
- Navigation Compose
- Kotlinx Coroutines

## Architecture

Built with modern development practices using:
- **Kotlin Multiplatform**: Share code across all platforms
- **Compose Multiplatform**: Unified UI framework
- **Clean Architecture**: Organized with separate data, domain, and UI layers
- **Modular Design**: Feature-based modules for scalability and maintainability

Gamopedia demonstrates the power of Kotlin Multiplatform by delivering a consistent gaming experience across all major platforms while maintaining native performance and platform-specific optimizations.