# Settling Scores - Rock Paper Scissors Game

A RESTful API demonstration project built with Spring Boot, featuring an interactive Rock Paper Scissors game with real-time score tracking.

![Java](https://img.shields.io/badge/Java-25-orange?style=flat&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen?style=flat&logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-8.x-blue?style=flat&logo=gradle)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat)

## Features

- **Interactive Gameplay**: Browser-based Rock Paper Scissors game
- **RESTful API**: 7 comprehensive endpoints demonstrating HTTP methods
- **Real-time Score Tracking**: Persistent score tracking across game rounds
- **CORS Enabled**: API accessible from external clients (Postman, curl, etc.)
- **Vanilla JavaScript Frontend**: No framework dependencies, pure JavaScript and Fetch API
- **In-Memory Storage**: Lightweight data management with static Score object

## Technology Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java 25 |
| **Framework** | Spring Boot 4.0.2 |
| **Web Layer** | Spring Web MVC |
| **Build Tool** | Gradle 8.x with Wrapper |
| **Frontend** | HTML5, Vanilla JavaScript, Fetch API |
| **Testing** | JUnit 5, Spring Boot Test |
| **Development** | Spring Boot DevTools |

## Prerequisites

- **JDK 25** or higher
- **Gradle 8.x** (or use the included Gradle Wrapper)
- **Modern web browser** (Chrome, Firefox, Safari, Edge)

## Installation & Setup

Clone the repository:
```bash
git clone <repository-url>
cd springboot-settlingscores
```

Build the project:
```bash
./gradlew build
```

Run the application:
```bash
./gradlew bootRun
```

The application starts on `http://localhost:8080`

Verify the server is running:
```bash
curl http://localhost:8080/health-check
```

Expected response: `Situation Normal All Fired Up!`

## API Documentation

### 1. Health Check

**GET** `/health-check`

Returns a health status message.

```bash
curl http://localhost:8080/health-check
```

**Response:**
```
Situation Normal All Fired Up!
```

---

### 2. Get Complete Score

**GET** `/score`

Returns the complete score object with wins, ties, and losses.

```bash
curl http://localhost:8080/score
```

**Response:**
```json
{
  "wins": 5,
  "ties": 2,
  "losses": 3
}
```

---

### 3. Get Specific Score Value

**GET** `/score/{winslossesorties}`

Retrieves a specific score field by name.

**Path Parameter:** `winslossesorties` - accepts `"wins"`, `"ties"`, or `"losses"` (case-insensitive)

Get wins:
```bash
curl http://localhost:8080/score/wins
```

Get ties:
```bash
curl http://localhost:8080/score/ties
```

Get losses:
```bash
curl http://localhost:8080/score/losses
```

**Response:** Integer value (e.g., `5`)

---

### 4. Increment Score Counter

**POST** `/score/{increasewinslossesorties}`

Increments a specific score field by 1 and returns the updated value.

**Path Parameter:** `increasewinslossesorties` - accepts `"wins"`, `"ties"`, or `"losses"` (case-insensitive)

Increment wins:
```bash
curl -X POST http://localhost:8080/score/wins
```

Increment ties:
```bash
curl -X POST http://localhost:8080/score/ties
```

Increment losses:
```bash
curl -X POST http://localhost:8080/score/losses
```

**Response:** Updated integer value (e.g., `6`)

---

### 5. Replace Entire Score

**PUT** `/score`

Replaces the entire score object with new values.

```bash
curl -X PUT http://localhost:8080/score \
  -H "Content-Type: application/json" \
  -d '{"wins": 10, "ties": 5, "losses": 7}'
```

**Request Body:**
```json
{
  "wins": 10,
  "ties": 5,
  "losses": 7
}
```

**Response:**
```json
{
  "wins": 10,
  "ties": 5,
  "losses": 7
}
```

---

### 6. Update Wins Field

**PATCH** `/score/wins`

Updates only the wins field using a query parameter.

**Query Parameter:** `new-value` - the new wins value

```bash
curl -X PATCH "http://localhost:8080/score/wins?new-value=15"
```

**Response:**
```json
{
  "wins": 15,
  "ties": 5,
  "losses": 7
}
```

---

### 7. Delete Score

**DELETE** `/score`

Resets the score object to null.

```bash
curl -X DELETE http://localhost:8080/score
```

**Response:** No content (204)

**Note:** This will cause null pointer errors on subsequent requests until a new score is created via PUT.

---

## Usage Guide

### Playing the Game

1. Start the application (see Installation & Setup)
2. Open your web browser to `http://localhost:8080`
3. Click on **rock**, **paper**, or **scissors**
4. View the game result and updated scores

### How It Works

1. **User selects a gesture** by clicking a link
2. **JavaScript generates random computer choice** from `['rock', 'paper', 'scissors']`
3. **Game logic determines outcome** using Rock Paper Scissors rules
4. **Frontend POSTs to increment endpoint** (`/score/wins`, `/score/ties`, or `/score/losses`)
5. **Frontend GETs updated score** from `/score` endpoint
6. **UI displays results and updated scores** in the browser

## Project Structure

```
springboot-settlingscores/
├── src/
│   ├── main/
│   │   ├── java/com/legion/rps/settling_scores/
│   │   │   ├── SettlingScoresApplication.java   # Main application entry point
│   │   │   ├── ScoreController.java              # REST controller with 7 endpoints
│   │   │   └── Score.java                        # Score data model
│   │   └── resources/
│   │       ├── application.yaml                  # Application configuration
│   │       └── static/
│   │           └── index.html                    # Game frontend
│   └── test/
│       └── java/com/legion/rps/settling_scores/
│           └── SettlingScoresApplicationTests.java  # Integration tests
├── build.gradle                                  # Gradle build configuration
├── gradlew                                       # Gradle wrapper (Unix)
├── gradlew.bat                                   # Gradle wrapper (Windows)
└── README.md                                     # This file
```

### Key Files

- **SettlingScoresApplication.java**: Spring Boot application entry point with `@SpringBootApplication`
- **ScoreController.java**: REST controller annotated with `@RestController` and `@CrossOrigin`, contains all 7 API endpoints
- **Score.java**: POJO with three integer fields: `wins`, `ties`, `losses`, plus getters/setters
- **index.html**: Vanilla JavaScript frontend with game logic and Fetch API calls
- **application.yaml**: Spring configuration defining application name as "settling-scores"

## Architecture & Design Decisions

### In-Memory Storage

**Decision:** Use a static `Score` object in the controller
**Rationale:** Demonstrates API design and REST principles without database complexity
**Trade-off:** Data resets on application restart (intentional for portfolio simplicity)

```java
static Score score = new Score(0, 0, 0);
```

### RESTful Design

The API demonstrates proper HTTP method semantics:

- **GET**: Read operations (health check, retrieve score)
- **POST**: Create/increment operations (add to counters)
- **PUT**: Full replacement (replace entire score object)
- **PATCH**: Partial update (modify only wins field)
- **DELETE**: Remove resource (reset score to null)

Each method serves its semantic purpose according to REST principles.

### CORS Configuration

The `@CrossOrigin` annotation enables cross-origin requests, allowing:
- External API testing tools (Postman, Insomnia)
- Command-line tools (curl, HTTPie)
- Frontend development servers
- Third-party integrations

### Separation of Concerns

The project follows MVC architecture:

- **Model**: `Score.java` represents data structure
- **Controller**: `ScoreController.java` handles HTTP requests/responses
- **View**: `index.html` provides user interface

This separation maintains clean code organization and testability.

### Frontend Simplicity

**Decision:** Vanilla JavaScript without frameworks
**Benefits:**
- No build step or compilation required
- Demonstrates core JavaScript competency
- Minimal dependencies
- Instant browser compatibility

The frontend uses modern Fetch API with async/await for clean, readable asynchronous code.

## Testing

Run all tests:
```bash
./gradlew test
```

### Current Test Coverage

- **Context Load Test**: Verifies Spring application context initializes successfully
- **Location**: `src/test/java/com/legion/rps/settling_scores/SettlingScoresApplicationTests.java`

The test ensures all Spring beans and configurations load without errors.

## License

MIT License

Copyright (c) 2026

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
