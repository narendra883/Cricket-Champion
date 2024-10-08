
# 🏏 Cricket Champion - Spring Boot Application

**Cricket Champion** is a Spring Boot application that allows users to manage cricket tournaments, players, teams, and match statistics. The app handles the creation of players, teams, and matches, along with tracking players' statistics across matches.

## 🚀 Features
- Create and manage **Players**.
- Record **Player Statistics** (e.g., runs, wickets, catches, etc.).
- Create and manage **Teams**.
- Create and manage **Matches**, including assigning players to teams and matches.
- View and update statistics for each player across different matches.

## 🛠️ Tech Stack
- **Backend**: Spring Boot, Spring Data JPA, Hibernate
- **Database**: MySQL (or any JPA-supported database)
- **Build Tool**: Maven
- **REST API**: JSON-based RESTful API for CRUD operations.

## 📦 Entities

### 1. `Player`
Represents a cricket player with basic information such as name and role.
- **Fields**:
  - `player_id`: Long (Primary Key)
  - `playerName`: String
  - `playerRole`: String (Batsman, Bowler, All-Rounder)
  - `team`: Team (Many-to-One relationship)
  - `stats`: PlayerStatistics (One-to-One relationship)

### 2. `PlayerStatistics`
Stores the performance data of a player.
- **Fields**:
  - `statsId`: Integer (Primary Key)
  - `runs`: Integer
  - `wickets`: Integer
  - `matchesPlayed`: Integer
  - `catches`: Integer
  - `sixes`: Integer
  - `fours`: Integer

### 3. `Team`
Represents a team with a collection of players.
- **Fields**:
  - `team_id`: Long (Primary Key)
  - `teamName`: String
  - `players`: List of Players (One-to-Many relationship)

### 4. `Match`
Represents a match between two teams, including statistics.
- **Fields**:
  - `match_id`: Long (Primary Key)
  - `teamA`: Team (Many-to-One relationship)
  - `teamB`: Team (Many-to-One relationship)
  - `date`: LocalDateTime
  - `location`: String

## 📚 API Endpoints

### Players
- **POST** `/players` - Create a new player
- **GET** `/players/{playerId}` - Retrieve a player by ID
- **GET** `/players` - Retrieve all players
- **PUT** `/players/{playerId}` - Update player details
- **DELETE** `/players/{playerId}` - Delete a player
- **POST** `/players/{playerId}/stats` - Add or update player's statistics

### Player Statistics
- **GET** `/players/{playerId}/stats` - Retrieve player's statistics
- **POST** `/players/{playerId}/stats` - Add or update player's statistics

### Teams
- **POST** `/teams` - Create a new team
- **GET** `/teams/{teamId}` - Retrieve a team by ID
- **GET** `/teams` - Retrieve all teams
- **PUT** `/teams/{teamId}` - Update team details
- **DELETE** `/teams/{teamId}` - Delete a team

### Matches
- **POST** `/matches` - Create a new match
- **GET** `/matches/{matchId}` - Retrieve a match by ID
- **GET** `/matches` - Retrieve all matches
- **PUT** `/matches/{matchId}` - Update match details
- **DELETE** `/matches/{matchId}` - Delete a match

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL (or any JPA-supported database)
  
### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/cricket-champion.git
   cd cricket-champion
   ```

2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/cricket_champion
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```



3. The application will run on `http://localhost:8080`.



