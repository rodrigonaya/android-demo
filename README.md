## Project Overview

**Name:** Concurrent API Calls Android App

**Description:** This Android application follows Clean Code specifications and is organized into three distinct layers: Presentation, Domain, and Data. Each layer is focused on a specific aspect of the application's functionality, ensuring a clear separation of concerns and maintainability. The app performs two concurrent API requests to retrieve content from a specified URL. It then processes the content to extract every 10th character and count the occurrence of each unique word. The results are displayed on the screen. The application also includes data caching for offline access and unit tests to ensure code reliability.

---

## Features

- **Concurrent API Requests**: Executes two API requests simultaneously to fetch and process content from a URL.
- **Every 10th Character Extraction**: Identifies and displays every 10th character from the fetched content.
- **Word Count Analysis**: Counts and displays the occurrence of each unique word in the fetched content.

---

## Tech Stack

- **Programming Language**: Kotlin
- **Asynchronous Work**: Kotlin Coroutines and StateFlow for state management
- **UI**: Jetpack Compose
- **Testing**: JUnit, Truth, Mockito
- **Database**: Room for data caching

---

## Testing

- **Unit Testing**: The domain layer of the application is thoroughly tested to ensure correctness and reliability.
- **Frameworks Used**:
   - **JUnit**: For running the tests.
   - **Truth**: For making assertions in tests.
   - **Mockito**: For mocking dependencies and verifying interactions.

---

## Caching

- **Implementation**: The application uses Room library to cache data locally.
- **Purpose**: This ensures that the fetched data is available offline after the first successful fetch, providing a seamless user experience even without an internet connection.

---

## Error Handling

- **Resource Class**: The application uses a sealed class called `Resource` to handle errors and data loading states.
- **Types**:
   - **Success**: Represents successful data loading with the fetched data.
   - **Error**: Represents a failure in data loading with an associated error message.
- **Usage**: This class helps in encapsulating and communicating the loading status and error messages effectively throughout the application.