-- Users Table
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(150) NOT NULL UNIQUE,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    address_city VARCHAR(150),
    address_state VARCHAR(150),
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(20),
    sports BOOLEAN DEFAULT NULL,
    theatre BOOLEAN DEFAULT NULL,
    concerts BOOLEAN DEFAULT NULL,
    jazz BOOLEAN DEFAULT NULL,
    classical BOOLEAN DEFAULT NULL,
    opera BOOLEAN DEFAULT NULL,
    rock BOOLEAN DEFAULT NULL,
    vegas BOOLEAN DEFAULT NULL,
    broadway BOOLEAN DEFAULT NULL,
    musicals BOOLEAN DEFAULT NULL
);

-- Venues Table
CREATE TABLE Venues (
    venue_id INT PRIMARY KEY AUTO_INCREMENT,
    venue_name VARCHAR(150) NOT NULL,
    city VARCHAR(150),
    state VARCHAR(150),
    seating_capacity INT
);

-- Categories Table
CREATE TABLE Categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_group VARCHAR(150),
    category_name VARCHAR(150) NOT NULL,
    category_description TEXT
);

-- Dates Table
CREATE TABLE Dates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    calendar_date DATE NOT NULL,
    day VARCHAR(2) NOT NULL,
    week INT NOT NULL,
    month VARCHAR(3) NOT NULL,
    quarter INT NOT NULL,
    year INT NOT NULL,
    holiday_flag BOOLEAN DEFAULT FALSE
);

-- Events Table
CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    venue_id INT NOT NULL,
    category_id INT NOT NULL,
    date_id INT NOT NULL,
    event_name VARCHAR(150) NOT NULL,
    event_start_time DATETIME NOT NULL,
    FOREIGN KEY (venue_id) REFERENCES Venues(venue_id),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id),
    FOREIGN KEY (date_id) REFERENCES Dates(id)
);

-- Listings Table
CREATE TABLE Listings (
    listing_id INT PRIMARY KEY AUTO_INCREMENT,
    seller_id INT NOT NULL,
    event_id INT NOT NULL,
    date_id INT NOT NULL,
    number_of_tickets INT NOT NULL,
    price_per_ticket DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    listing_timestamp DATETIME NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id),
    FOREIGN KEY (date_id) REFERENCES Dates(id)
);

-- Sales Table
CREATE TABLE Sales (
    sale_id INT PRIMARY KEY AUTO_INCREMENT,
    listing_id INT NOT NULL,
    seller_id INT NOT NULL,
    buyer_id INT NOT NULL,
    event_id INT NOT NULL,
    date_id INT NOT NULL,
    quantity_sold INT NOT NULL,
    price_paid DECIMAL(10, 2) NOT NULL,
    commission_amount DECIMAL(10, 2) NOT NULL,
    sale_timestamp DATETIME NOT NULL,
    FOREIGN KEY (listing_id) REFERENCES Listings(listing_id),
    FOREIGN KEY (seller_id) REFERENCES Users(user_id),
    FOREIGN KEY (buyer_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id),
    FOREIGN KEY (date_id) REFERENCES Dates(id)
);
