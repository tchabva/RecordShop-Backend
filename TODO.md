# TODO List
## MVP
- [x] UPDATE README
- [x] List all albums in stock
- [x] Get album by ID
- [x] Add new albums into the database
- [x] Update album details
- [x] Delete albums from the database
### Model Layer
- [ ] Add songs a class to the model layer
  - [ ] Relationships between songs and albums
  - [ ] Relationships between songs and artists
- [ ] ArtistDTO
### Service Layer
- [ ] Album Service
  - [x] Add New Album
  - [x] Update album stock by id
  - [x] Delete album by ID
- [ ] Artist Service
  - [x] Add Artist method
  - [x] Get All Artists
  - [x] Get or create AlbumArtist
- [ ] Stock Service
  - [x] Add stock method
  - [x] Get stock by ID
### Controller Layer
- [ ] GetMapping
  - [x] List all albums in stock
  - [ ] List all albums in the DB
  - [x] Get album by ID using path variables
- [x] PostMapping
  - [x] Add a new album to the database
- [ ] PatchMapping
  - [ ] Update album stock change method implementation for this
- [x] PutMapping
  - [x] Update Album Details
- [ ] DeleteMapping
    - [ ] decrease album stock by one?
    - [x] remove album from the DB?
- [ ] Health Endpoint
## Misc
- [x] Migrate to JpaRepository
- [ ] Data validation
- [ ] Cascading
- [x] Generic caching
  - [ ] Implement generic caching across the other service layers
- [x] Table rows
- [ ] Sprint extra tasks