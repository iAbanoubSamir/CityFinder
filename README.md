# City Finder

City Finder is an Android application developed as part of an assignment for an Android Developer Internship at [Klivvr](https://www.klivvr.com/). This application allows users to search for cities efficiently using a Trie data structure for prefix-based searching.

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
    - [MVI Architecture Pattern](#mvi-architecture-pattern)
    - [Clean Architecture](#clean-architecture)
    - [Modularization](#modularization)
- [Technologies Used](#technologies-used)
- [Searching Algorithm](#searching-algorithm)
- [Setup and Installation](#setup-and-installation)
- [License](#license)

## Project Overview

City Finder is an Android application that helps users find cities based on a search query. It utilizes a Trie data structure for efficient prefix-based searching, ensuring fast and responsive user interactions.

## Architecture

### MVI Architecture Pattern

The Model-View-Intent (MVI) architecture pattern is employed to separate concerns and manage the application's state in a predictable manner.

- **Model**: Represents the application's state and business logic. It handles data transformations and interactions with the data source.
- **View**: Responsible for rendering the UI and displaying data. It listens to state changes and updates the UI accordingly.
- **Intent**: Represents user actions and events that trigger state changes. It captures user inputs and communicates them to the Model.

In City Finder, MVI ensures a clear separation of concerns, making the codebase easier to manage and test.

### Clean Architecture

Clean Architecture promotes a separation of concerns by dividing the application into layers, each with a distinct responsibility:

- **Data Layer**: Manages data sources and repositories. It handles data fetching, caching, and persistence.
- **Domain Layer**: Contains business logic and use cases. It defines the core functionality of the application and interacts with the data layer.
- **Presentation Layer**: Handles UI logic and state management. It interacts with the domain layer and renders data to the user.

This separation helps in achieving a modular and maintainable codebase, where changes in one layer have minimal impact on others.

### Modularization

Modularization is used to structure the project into distinct modules, promoting code reuse and better organization. The strategy used in City Finder includes:

- **:app**: Contains application-level components and scaffolding classes such as `MainActivity` and `Application` class.
- **:core:common**: Houses common code used across different modules.
- **:core:ui**: Contains reusable composables and UI components shared across modules.
- **:feature:cities**:
    - **:data**: Includes data sources and repository implementations.
    - **:domain**: Contains business logic, use cases, and repository interfaces.
    - **:presentation**: Manages UI interfaces and logic for the Cities feature.

This modularization strategy improves maintainability, testability, and scalability by isolating different parts of the application into manageable modules.

## Technologies Used

- **Android Studio**: The IDE used for Android development.
- **Kotlin**: The programming language used for app development.
- **Compose**: UI toolkit for building native Android UIs.
- **Coroutines**: Used for asynchronous programming.
- **Koin**: Dependency injection framework.
- **Navigation Compose**: For navigating between composables.
- **Kotlinx Serialization**: For parsing JSON data.

## Searching Algorithm

### Trie Data Structure

The Trie data structure is employed for efficient prefix-based searching. Key advantages include:

- **Prefix Matching Efficiency**: Tries allow for quick prefix searches, which is ideal for features like autocomplete and search-as-you-type.
- **Performance**: Provides O(m) time complexity for search operations, where m is the length of the prefix, making it faster than linear or binary search for large datasets.
- **Scalability**: Handles large datasets efficiently, maintaining performance even as the number of entries grows.

The Trie was chosen for its ability to quickly match prefixes and efficiently manage large lists of cities, ensuring a responsive search experience for users.

## Setup and Installation

To run the City Finder application locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/iAbanoubSamir/CityFinder.git
   ```
2. Open the project in Android Studio.
3. Build and run the application on an emulator or device.

## License

This project is licensed under the MIT License.
