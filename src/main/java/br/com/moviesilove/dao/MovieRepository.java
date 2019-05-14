package br.com.moviesilove.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.moviesilove.tmdb.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	List<Movie> findByOrderByTitleAsc();
	
}
