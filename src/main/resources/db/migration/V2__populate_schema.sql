-- Devido as diretrizes de segurança do MySQL fiz um mapeamento da pasta "data" 
-- que aparece acima das migrations para o diretório "/var/lib/mysql-files/data" 
-- do meu container MySQL, dessa forma pude migrar os dados de forma simples sem
-- precisar fazer grandes modificações na aplicação

-- Populate Users table
LOAD DATA INFILE '/var/lib/mysql-files/data/users.txt'
INTO TABLE Users
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n'
(user_id, username, first_name, last_name, address_city, address_state, email, phone, @sports, @theatre, @concerts, @jazz, @classical, @opera, @rock, @vegas, @broadway, @musicals)
SET 
  sports = CASE 
            WHEN @sports = 'TRUE' THEN 1 
            WHEN @sports = 'FALSE' THEN 0 
            ELSE NULL 
          END,
  theatre = CASE 
             WHEN @theatre = 'TRUE' THEN 1 
             WHEN @theatre = 'FALSE' THEN 0 
             ELSE NULL 
           END,
  concerts = CASE 
              WHEN @concerts = 'TRUE' THEN 1 
              WHEN @concerts = 'FALSE' THEN 0 
              ELSE NULL 
            END,
  jazz = CASE 
           WHEN @jazz = 'TRUE' THEN 1 
           WHEN @jazz = 'FALSE' THEN 0 
           ELSE NULL 
         END,
  classical = CASE 
               WHEN @classical = 'TRUE' THEN 1 
               WHEN @classical = 'FALSE' THEN 0 
               ELSE NULL 
             END,
  opera = CASE 
           WHEN @opera = 'TRUE' THEN 1 
           WHEN @opera = 'FALSE' THEN 0 
           ELSE NULL 
         END,
  rock = CASE 
          WHEN @rock = 'TRUE' THEN 1 
          WHEN @rock = 'FALSE' THEN 0 
          ELSE NULL 
        END,
  vegas = CASE 
           WHEN @vegas = 'TRUE' THEN 1 
           WHEN @vegas = 'FALSE' THEN 0 
           ELSE NULL 
         END,
  broadway = CASE 
              WHEN @broadway = 'TRUE' THEN 1 
              WHEN @broadway = 'FALSE' THEN 0 
              ELSE NULL 
            END,
  musicals = CASE 
              WHEN @musicals = 'TRUE' THEN 1 
              WHEN @musicals = 'FALSE' THEN 0 
              ELSE NULL 
            END;

-- Populate Venues table
LOAD DATA INFILE '/var/lib/mysql-files/data/venues.txt'
INTO TABLE Venues
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n'
(venue_id, venue_name, city, state, seating_capacity);

-- Populate Categories table
LOAD DATA INFILE '/var/lib/mysql-files/data/categories.txt'
INTO TABLE Categories
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n'
(category_id, category_group, category_name, category_description);

-- Populate Dates table
LOAD DATA INFILE '/var/lib/mysql-files/data/dates_2008.txt'
INTO TABLE Dates
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n'
(id, calendar_date, day, week, month, quarter, year, @holiday_flag)
SET holiday_flag = CASE 
                     WHEN @holiday_flag = 'TRUE' THEN 1 
                     WHEN @holiday_flag = 'FALSE' THEN 0 
                     ELSE NULL 
                  END;

-- Populate Events table
LOAD DATA INFILE '/var/lib/mysql-files/data/events.txt'
INTO TABLE Events
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n'
(event_id, venue_id, category_id, date_id, event_name, event_start_time);

-- Populate Listings table
LOAD DATA INFILE '/var/lib/mysql-files/data/listings.txt'
INTO TABLE Listings
FIELDS TERMINATED BY '|'
LINES TERMINATED BY '\n'
(listing_id, seller_id, event_id, date_id, number_of_tickets, price_per_ticket, total_price, listing_timestamp);

-- Populate Sales table
LOAD DATA INFILE '/var/lib/mysql-files/data/sales.txt'
INTO TABLE Sales
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(sale_id, listing_id, seller_id, buyer_id, event_id, date_id, quantity_sold, price_paid, commission_amount, sale_timestamp);
