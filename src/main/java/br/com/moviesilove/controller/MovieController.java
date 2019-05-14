package br.com.moviesilove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moviesilove.service.MovieService;
import br.com.moviesilove.tmdb.model.MovieList;

@CrossOrigin(origins = "http://159.89.134.42", maxAge = 3600)
@RestController
@RequestMapping("v1/movie")
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping()
	public MovieList getAllMoviesOrderByTitle() {		
		return movieService.getAllMoviesOrderByTitle();
	}

}
