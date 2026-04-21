
create database EgyCinema


CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100) NOT NULL
);


CREATE TABLE Movies (
    MovieID INT PRIMARY KEY IDENTITY,
    Title NVARCHAR(255) NOT NULL,
    Duration INT,
    ReleaseDate DATE,
    CategoryID INT,

    ImgURL NVARCHAR(500),
    TrailerURL NVARCHAR(500),
    Status NVARCHAR(50) NOT NULL,

    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID),

    CONSTRAINT chk_MovieStatus
    CHECK (Status IN ('Now Showing', 'Coming Soon', 'Ended'))
);


CREATE TABLE Actors (
    ActorID INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100) NOT NULL
);

CREATE TABLE Movie_Actors (
    ID INT PRIMARY KEY IDENTITY,
    MovieID INT NOT NULL,
    ActorID INT NOT NULL,

    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
    FOREIGN KEY (ActorID) REFERENCES Actors(ActorID),

    CONSTRAINT UQ_MovieActor UNIQUE (MovieID, ActorID)
);


CREATE TABLE Cinemas (
    CinemaID INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100) NOT NULL,
    Location NVARCHAR(255)
);


CREATE TABLE ShowTimes (
    ShowID INT PRIMARY KEY IDENTITY,
    MovieID INT NOT NULL,
    CinemaID INT NOT NULL,
    ShowDate DATETIME NOT NULL,
    Price DECIMAL(10,2),

    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
    FOREIGN KEY (CinemaID) REFERENCES Cinemas(CinemaID)
);

CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100),
    Email NVARCHAR(100)
);

CREATE TABLE Favorites (
    FavoriteID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL,
    MovieID INT NOT NULL,

    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),

    CONSTRAINT UQ_Favorites UNIQUE (CustomerID, MovieID)
);

CREATE TABLE Bookings (
    BookingID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL,
    ShowID INT NOT NULL,
    BookingDate DATETIME DEFAULT GETDATE(),
    SeatsCount INT,

    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (ShowID) REFERENCES ShowTimes(ShowID)
);