# TODO List
## MVP
- [x] List all albums in stock
- [x] Get album by ID
- [x] Add new albums into the database
- [ ] Update album details
  - [ ] Increase the stock by a stock ItemDTO
  - [ ] Should update album details be removed from the database?
- [ ] Delete albums from the database 
  - [ ] Decrease the album stock until 0 then out stock
  - [ ] Should albums be removed from the database?
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
  - [ ] Update album stock
- [ ] DeleteMapping
    - [ ] decrease album stock by one?
    - [ ] remove album from the DB?
- [ ] Health Endpoint
## Misc
- Look into JPARepository and possible flexibility for queries