CREATE DATABASE IF NOT EXISTS onlinestore;
USE onlinestore;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(1000),
    category_id INT NOT NULL,
    CONSTRAINT fk_products_categories
        FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO categories (id, name) VALUES
(1, 'Elektronika'),
(2, 'Moda'),
(3, 'Dom i ogród'),
(4, 'Motoryzacja'),
(5, 'Sport i turystyka'),
(6, 'Książki i multimedia'),
(7, 'Zdrowie i uroda'),
(8, 'Zabawki i gry');

INSERT INTO products (id, name, price, description, category_id) VALUES
(1, 'Smartfon Samsung Galaxy A55', 1899.99, 'Smartfon z ekranem AMOLED, 128 GB pamięci i dobrym aparatem.', 1),
(2, 'Laptop Lenovo IdeaPad 15', 2999.00, 'Laptop do nauki, pracy biurowej i codziennego użytkowania.', 1),
(3, 'Słuchawki Sony WH-CH720N', 399.99, 'Bezprzewodowe słuchawki z redukcją hałasu.', 1),
(4, 'Monitor LG 27 cali', 899.99, 'Monitor Full HD do pracy i rozrywki.', 1),

(5, 'Kurtka zimowa męska', 349.99, 'Ciepła kurtka zimowa z kapturem.', 2),
(6, 'Buty sportowe Nike', 429.99, 'Wygodne buty sportowe do codziennego użytku.', 2),
(7, 'Koszula biała elegancka', 129.99, 'Klasyczna koszula do pracy i formalnych okazji.', 2),
(8, 'Jeansy slim fit', 199.99, 'Granatowe jeansy o kroju slim fit.', 2),

(9, 'Odkurzacz Philips PowerPro', 599.99, 'Odkurzacz bezworkowy do domu.', 3),
(10, 'Lampa biurkowa LED', 89.99, 'Regulowana lampa LED do pracy i nauki.', 3),
(11, 'Zestaw garnków Tefal', 499.99, 'Komplet garnków ze stali nierdzewnej.', 3),
(12, 'Krzesło biurowe ergonomiczne', 699.99, 'Wygodne krzesło do pracy przy komputerze.', 3),

(13, 'Olej silnikowy 5W30 Castrol', 159.99, 'Olej silnikowy syntetyczny do samochodów osobowych.', 4),
(14, 'Uchwyt samochodowy na telefon', 49.99, 'Regulowany uchwyt na telefon do auta.', 4),
(15, 'Kompresor samochodowy', 129.99, 'Przenośny kompresor do pompowania kół.', 4),
(16, 'Zestaw kosmetyków samochodowych', 89.99, 'Zestaw do czyszczenia i pielęgnacji auta.', 4),

(17, 'Hantle regulowane 2x10 kg', 299.99, 'Zestaw hantli do treningu domowego.', 5),
(18, 'Mata do jogi', 79.99, 'Antypoślizgowa mata do jogi i ćwiczeń.', 5),
(19, 'Plecak turystyczny 40L', 249.99, 'Plecak na wycieczki i trekking.', 5),
(20, 'Bidon sportowy 750 ml', 39.99, 'Lekki bidon na trening i rower.', 5),

(21, 'Gra planszowa Catan', 149.99, 'Popularna gra strategiczna dla całej rodziny.', 6),
(22, 'Książka Clean Code', 179.99, 'Książka o dobrych praktykach programowania.', 6),
(23, 'Klawiatura mechaniczna', 299.99, 'Klawiatura mechaniczna dla graczy i programistów.', 6),
(24, 'Mysz gamingowa Logitech', 199.99, 'Precyzyjna mysz dla graczy.', 6),

(25, 'Szczoteczka soniczna Oral-B', 249.99, 'Elektryczna szczoteczka soniczna.', 7),
(26, 'Suszarka do włosów Philips', 189.99, 'Suszarka z regulacją temperatury.', 7),
(27, 'Maszynka do golenia Braun', 349.99, 'Bezprzewodowa maszynka do golenia.', 7),
(28, 'Zestaw witamin', 59.99, 'Podstawowy zestaw witamin na co dzień.', 7),

(29, 'Klocki LEGO City', 199.99, 'Zestaw klocków LEGO dla dzieci.', 8),
(30, 'Puzzle 1000 elementów', 49.99, 'Puzzle krajobrazowe dla dorosłych i dzieci.', 8),
(31, 'Samochód zdalnie sterowany', 149.99, 'Auto RC z pilotem.', 8),
(32, 'Pluszowy miś', 69.99, 'Miękki pluszak dla dzieci.', 8);