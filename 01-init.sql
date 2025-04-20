CREATE TABLE country (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         continent VARCHAR(255) NOT NULL
);

CREATE TABLE author (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        surname VARCHAR(255) NOT NULL,
                        country_id BIGINT REFERENCES country(id)
);

CREATE TABLE book (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      category VARCHAR(255) NOT NULL,
                      author_id BIGINT REFERENCES author(id),
                      available_copies BIGINT
);

CREATE TABLE users (
                       username VARCHAR(255) PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);

CREATE TABLE user_book_wishlist (
                                    user_username VARCHAR(255) REFERENCES users(username),
                                    book_id BIGINT REFERENCES book(id),
                                    PRIMARY KEY (user_username, book_id)
);

CREATE TABLE user_rented_books (
                                   user_username VARCHAR(255) REFERENCES users(username),
                                   book_id BIGINT REFERENCES book(id),
                                   PRIMARY KEY (user_username, book_id)
);
