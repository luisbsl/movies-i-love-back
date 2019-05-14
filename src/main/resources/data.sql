DROP TABLE IF EXISTS movie_genre;
 
CREATE TABLE movie_genre (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  movie_id INT,
  genre_id INT
);