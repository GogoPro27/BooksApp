-- Insert Countries
INSERT INTO country (id, name, continent) VALUES
    (1, 'United States', 'North America'),
    (2, 'United Kingdom', 'Europe'),
    (3, 'Germany', 'Europe'),
    (4, 'France', 'Europe'),
    (5, 'Japan', 'Asia'),
    (6, 'Canada', 'North America'),
    (7, 'Italy', 'Europe'),
    (8, 'Spain', 'Europe'),
    (9, 'Sweden', 'Europe'),
    (10, 'Australia', 'Oceania');

-- Insert Authors
INSERT INTO author (id, name, surname, country_id) VALUES
    (1, 'Mark', 'Twain', 1),
    (2, 'Jane', 'Austen', 2),
    (3, 'Johann', 'Goethe', 3),
    (4, 'Victor', 'Hugo', 4),
    (5, 'Haruki', 'Murakami', 5),
    (6, 'Margaret', 'Atwood', 6),
    (7, 'Dante', 'Alighieri', 7),
    (8, 'Miguel', 'de Cervantes', 8),
    (9, 'Astrid', 'Lindgren', 9),
    (10, 'Tim', 'Winton', 10);

-- Insert Books
INSERT INTO book (name, category, author_id, available_copies) VALUES
    ('The Adventures of Tom Sawyer', 'CLASSICS', 1, 10),
    ('Pride and Prejudice', 'NOVEL', 2, 15),
    ('Faust', 'DRAMA', 3, 5),
    ('Les Misérables', 'HISTORY', 4, 8),
    ('Kafka on the Shore', 'FANTASY', 5, 12),
    ('The Handmaid''s Tale', 'THRILLER', 6, 9),
    ('The Divine Comedy', 'CLASSICS', 7, 4),
    ('Don Quixote', 'CLASSICS', 8, 6),
    ('Cloudstreet', 'NOVEL', 10, 7);