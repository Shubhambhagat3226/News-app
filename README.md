# News Application

This is a News Application built with Spring Boot, providing user authentication and news searching capabilities.

## Features

*   **User Authentication:**
    *   Secure registration and login using Spring Security and JWT (JSON Web Tokens).
    *   BCrypt password encoding for enhanced security.
    *   Role-based authorization for accessing news content.
*   **News Search:**
    *   Keyword-based news article search powered by a third-party News API.
    *   Displays search results with titles, descriptions, and images.
    *   Maintains a search history for convenient re-searching.
*   **News Details View:**
    *   Displays detailed information for selected news articles.
    *   Links to the original news article source.
*   **Frontend:**
    *   User-friendly interface built with Thymeleaf for dynamic content rendering and static resources (CSS, JS).

## Technologies Used

*   **Spring Boot:** Java-based framework for web application development.
*   **Spring Security:** Authentication and authorization framework.
*   **JWT (JSON Web Tokens):** Standard for creating access tokens.
*   **Thymeleaf:** Template engine for generating HTML views.
*   **MySQL:** Relational database for user account storage.
*   **Lombok:** Java library to reduce boilerplate code.
*   **Jsoup:** Library to parse and extract data from HTML content.
*   **Maven:** Build tool for managing project dependencies and building the application.

## Prerequisites

*   **Java 17 or higher:** Requires Java 17 or a later version to compile and run.
*   **Maven:** Necessary to build and manage project dependencies.
*   **MySQL Database:** A running MySQL instance for user account storage.
*   **News API Key:** An API key from a news provider (e.g., NewsAPI.org) to fetch news data.

## Setup and Installation

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/Shubhambhagat3226/News-app.git
    cd shubhambhagat3226-news-app
    ```

2.  **Configure the Database:**

    *   Create a database named `news_application` in your MySQL instance.
    *   Update `src/main/resources/application.properties` with your MySQL credentials:

        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/news_application
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        ```

3.  **Configure API Keys and Secrets:**

    *   Obtain a News API key and set the `news.api.key` property in `application.properties`.
    *   Generate a secure JWT secret key and set the `jwt.secret` property in `application.properties`.

        ```properties
        news.api.key=${KEY}
        news.api.url=${URL}
        jwt.secret=${SECRET}
        ```

    *   Note: Consider using environment variables for sensitive information in production.

4.  **Build the application:**

    ```bash
    ./mvnw clean install
    ```

## Running the Application

1.  **Run the Spring Boot application:**

    ```bash
    ./mvnw spring-boot:run
    ```

2.  **Access the Application:**

    *   The application will be running at `http://localhost:8000`.
    *   Register a new user at `http://localhost:8000/register`.
    *   Login with the registered user at `http://localhost:8000/login`.
    *   Access the home page and search for news articles at `http://localhost:8000/home`.

## Configuration Notes

*   The API keys (`news.api.key`, `jwt.secret`) are set as placeholders `${KEY}`, `${URL}`, and `${SECRET}` in `application.properties`.  It's *strongly* recommended to use environment variables or a more secure configuration mechanism (like Spring Cloud Config) to manage these secrets, especially in production.
*   `spring.jpa.hibernate.ddl-auto=update` is used for automatic schema updates.  In production, use a database migration tool for better control.
*   The `SecurityConfig` disables CSRF protection.  Enable it in production along with appropriate CSRF token handling.

## Future Enhancements

*   Implement more advanced search filters (e.g., categories, sources).
*   Add user profile management (e.g., update password).
*   Implement caching to reduce News API usage.
*   Improve UI/UX and responsiveness.
*   Add pagination for search results.
