package br.com.moviesilove.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moviesilove.dao.MovieRepository;
import br.com.moviesilove.tmdb.model.Movie;
import br.com.moviesilove.tmdb.model.MovieList;

@Service
public class MovieService {	
	@Autowired
	private MovieRepository movieRepository;

	public MovieList getAllMoviesOrderByTitle() {
		List<Movie> movies = new ArrayList<Movie>();
		movieRepository.findByOrderByTitleAsc().forEach(movies::add);
		MovieList movieList = new MovieList();
		movieList.setMovies(movies);
		movieList.setTotalResults(movies.size());
		return movieList;
	}
}
