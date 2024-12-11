# Record Shop API

## Project Overview
The Record Shop API is a backend inventory management system designed to modernise the 
record shop's stock, query this stock and update it accordingly.

## Assumptions 
- That users will always check the database before adding an album, and thus just add to the stock if they album is already present in the database.
- That an Album can only have one Artist
  - e.g. In the case of a joint album it would create a new single "Artist" with both their names (or the group name).
- The genres are an Enum list that are only assigned to albums and not songs
## Technology Stack
- Spring Boot
- Java
- PostgreSQL
- JUnit
## Project Setup
### Prerequisites
- Java Development Kit 21
- Integrated Development Environment (IDE)
  - IntelliJ IDEA, Eclipse, Visual Studio Code etc.
  - Database: PostgreSQL
### Installation Steps
1. Clone the repository
```SHELL
git clone https://github.com/tchabva/recordshopbackend
```
2. Open the project in your IDE.
## Running the Application
### Using the IDE
1. Open the project
2. Locate and run the `main` method in the `NorthcodersRecordShopBackendApplication` class.
## API Endpoints
### Albums
- `GET /albums` - List all albums
- `GET /albums/{id}` - Get album by ID
- `POST /albums` - Add new album
  ```json
  {
    "title": "Album Title",
    "artist": "Artist Name",
    "genre": 1,
    "releaseDate": "YYYY-MM-DD",
    "stock": 10,
    "price": 10
  }
  ```
- `PUT /albums/{id}` - Update album details
  ```json
  {
    "title": "Album Title",
    "artist": "Artist Name",
    "genre": 1,
    "releaseDate": "YYYY-MM-DD",
    "stock": 10,
    "price": 11
  }
  ```
- `DELETE /albums/{id}` - Delete album
## Testing
1. Navigate to the `java` folder in `test`.
2. Right-click on folder to run all the tests.
3. Or navigate to individual tests in the respective packages.
## Architecture Considerations
- Using the MVC architecture for a separation of concerns.
- Using the Spring Boot library and framework for dependency injection.
- Implemented caching, with a scheduled method that will automatically purge expired items.

## Future Improvements
I am looking to continue iterating and adding to this project by providing the capability to:
- listing all albums by a given artist
- listing all albums by a given release year
- listing all albums by a given genre
- getting album information by album name