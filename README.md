# Fuyl Product Challenge 🚀

A modern Android application demonstrating Clean Architecture, Jetpack Compose, and Paging 3, built with a focus on scalability, maintainability, and testability.

## 🏗 Architecture

The project follows **Clean Architecture** principles, strictly decoupling the layers:

- **Domain Layer**: Contains pure Kotlin business logic, models, and repository interfaces. No Android dependencies.
- **Data Layer**: Implements repository interfaces, handling data fetching from the Network (Ktor) and mapping DTOs to Domain models. Includes Paging 3 implementation.
- **Presentation Layer**: Built with **Jetpack Compose** and **Material 3**. Uses **MVVM** with **Unidirectional Data Flow (UDF)**. Domain models are mapped to `UiModel`s to keep the UI decoupled.

## 🛠 Tech Stack

- **Language**: Kotlin & Coroutines / Flow
- **UI**: Jetpack Compose (Material 3)
- **Dependency Injection**: Hilt
- **Networking**: Ktor Client with Kotlinx Serialization
- **Image Loading**: Coil
- **Paging**: Paging 3
- **Navigation**: Type-safe Jetpack Compose Navigation
- **Architecture**: MVVM + UDF + Clean Architecture

## 🧪 Testing Strategy

The project adopts a test-driven mindset with high coverage across layers:

- **Unit Tests**:
    - **ViewModels**: Testing state transitions using **Turbine**.
    - **Use Cases**: Verifying business logic and repository interactions.
    - **PagingSource**: Testing API success/failure scenarios.
    - **Mappers**: Ensuring correct data transformation between layers.
- **UI Tests**:
    - **Compose UI Tests**: Verifying component rendering and screen states.
- **Tools**: JUnit 4, MockK, Turbine, AndroidX Test.

## 📁 Project Structure

```
app/src/main/java/com/fuyl/challenge/
├── core/             # Cross-cutting concerns (Network, DI, Theme, Common UI)
├── data/             # Data implementations, Paging, and Mappers
├── domain/           # Business logic, Use Cases, and Repository Interfaces
└── presentation/     # UI Screens, ViewModels, and UI Models
```

## 🚀 Getting Started

1. Clone the repository.
2. Open in Android Studio (Ladybug or newer recommended).
3. Run `./gradlew assembleDebug` to build the project.
4. Run `./gradlew test` to execute unit tests.
5. Run `./gradlew connectedAndroidTest` to execute UI tests.

## 📝 Error Handling

The app uses a robust error handling mechanism:
- **`AppException`**: A sealed class hierarchy for categorized errors (Client, Server, Unknown).
- **`safeApiCall`**: A wrapper to catch network exceptions and map them to `Result<T>`.
- **UI Feedback**: Errors are propagated to the UI and displayed via state-driven error messages.
