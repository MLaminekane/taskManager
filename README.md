# Task Manager

A comprehensive productivity application for managing tasks, routines, and schedules with Google Calendar integration.

![Task Manager Logo](app/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

## Features

- **Task Management:** Create, edit, and organize tasks with customizable categories
- **Routines:** Set up recurring task sequences for daily/weekly habits
- **Pomodoro Timer:** Boost productivity with built-in time management technique
- **Location-Based Reminders:** Get notified about tasks when arriving at specific locations
- **Google Calendar Integration:** Sync your tasks with Google Calendar
- **Authentication:** Choose between local account and Google authentication
- **Notifications:** Stay on top of your schedule with timely reminders
- **Dark Mode Support:** Comfortable usage in any lighting condition

## Technologies Used

- **Kotlin:** Modern programming language for Android development
- **Jetpack Compose:** Declarative UI toolkit for native UI
- **Room Database:** Local data persistence
- **Hilt:** Dependency injection
- **WorkManager:** Background task scheduling
- **Google Maps & Location Services:** Location-based features
- **Material Design 3:** Modern Android UI components
- **Coroutines & Flow:** Asynchronous programming
- **Google Calendar API:** Calendar integration and synchronization
- **Retrofit:** API communication

## Getting Started

### Prerequisites

- Android Studio Arctic Fox (2020.3.1) or higher
- Minimum SDK 26 (Android 8.0 Oreo)
- Google Maps API key (for location features)
- Google Calendar API credentials (for calendar integration)

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/taskManager.git
   ```

2. Open the project in Android Studio

3. Create a `local.properties` file in the root directory with:
   ```
   MAPS_API_KEY=your_google_maps_api_key
   ```

4. Configure your Google Calendar API credentials:
   - Follow instructions at [Google Calendar API Setup](https://developers.google.com/calendar/api/quickstart/android)
   - Place the credentials file in the appropriate location

5. Build and run the application

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/mlk/taskmanager/
│   │   │   ├── data/             # Data layer (models, repositories, database)
│   │   │   ├── di/               # Dependency injection modules
│   │   │   ├── service/          # Background services and workers
│   │   │   ├── ui/               # UI components and screens
│   │   │   │   ├── home/         # Home screen
│   │   │   │   ├── pomodoro/     # Pomodoro timer
│   │   │   │   └── ...           # Other UI components
│   │   │   └── util/             # Utility classes
│   │   └── res/                  # Android resources
│   └── test/                     # Unit tests
└── ...
```

## Contributing

Contributions are welcome! Here's how you can contribute to Task Manager:

### Issues & Feature Requests

- Before creating a new issue, please check if it already exists
- Use the issue templates when available
- Provide detailed reproduction steps for bugs
- For feature requests, describe the desired behavior and use cases

### Development Process

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Implement your changes
4. Add tests when applicable
5. Ensure all tests pass (`./gradlew test`)
6. Make sure your code follows the project style guidelines
7. Commit your changes (`git commit -m 'Add some amazing feature'`)
8. Push to the branch (`git push origin feature/amazing-feature`)
9. Open a Pull Request

### Pull Request Guidelines

- Link the issue that your PR addresses
- Include screenshots or GIFs for UI changes
- Update documentation if necessary
- Make sure CI checks pass
- Get at least one code review before merging

### Code Style

- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Write comments for complex logic
- Include documentation for public APIs
- Keep functions small and focused on a single responsibility

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Icons from [Material Design Icons](https://fonts.google.com/icons)
- Calendar integration using [Google Calendar API](https://developers.google.com/calendar)
- Location services powered by [Google Maps Platform](https://mapsplatform.google.com/)

---

*by MLK*
