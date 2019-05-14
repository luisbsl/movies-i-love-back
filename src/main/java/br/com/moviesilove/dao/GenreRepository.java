package br.com.moviesilove.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.moviesilove.tmdb.model.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

}
