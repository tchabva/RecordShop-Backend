# TODO List
## MVP
- [ ] List all albums in stock
- [ ] Get album by ID
- [ ] Add new albums into the database
- [ ] Update album details
- [ ] Delete albums from the database 
  - [ ] Decrease the album stock until 0 then out stock
  - [ ] Should albums be removed from the database?
### Model Layer
- [ ] Add songs a class to the model layer
  - [ ] Relationships between songs and albums
  - [ ] Relationships between songs and artists
- [ ] Artist Service
- [ ] Stock Service
    - [ ] Add stock method
    - [ ] Delete stock method
### Service Layer
- [ ] Album Service
- [ ] Artist Service
- [ ] Stock Service
  - [ ] Add stock method
  - [ ] Delete stock method 
### Controller Layer
- [ ] GetMapping
  - [ ] List all albums in stock
  - [ ] List all albums in the DB
  - [ ] Get album by ID using path variables
- [ ] PostMapping
  - [ ] Add a new album to the database
- [ ] PatchMapping
  - [ ] Update album stock
- [ ] DeleteMapping
    - [ ] decrease album stock by one?
    - [ ] remove album from the DB?