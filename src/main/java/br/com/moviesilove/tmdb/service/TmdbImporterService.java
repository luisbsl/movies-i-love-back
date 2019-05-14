package br.com.moviesilove.tmdb.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.moviesilove.dao.GenreRepository;
import br.com.moviesilove.dao.MovieRepository;
import br.com.moviesilove.tmdb.helpers.TmdbApiHelper;
import br.com.moviesilove.tmdb.model.Genre;
import br.com.moviesilove.tmdb.model.GenreList;
import br.com.moviesilove.tmdb.model.Movie;
import br.com.moviesilove.tmdb.model.MovieList;

@Service
public final class TmdbImporterService {

	private Integer currentPage = 1;
	private List<MovieList> movieCollectionList = new ArrayList<>();

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private GenreRepository genreRepository;

	private List<Genre> genres = new ArrayList<Genre>();

	private void createMovieCollection() {
		RestTemplate restTemplate = new RestTemplate();
		MovieList movieList = restTemplate.getForObject(TmdbApiHelper.getUpcomingMoviesUrlWithPage(currentPage),
				MovieList.class);
		
		movieList.setMovies(removeRealeasedMovies(movieList.getMovies()));

		movieList.getMovies().forEach(movie -> {
			movie.setGenres(getFullGenreList(movie.getGenres()));
		});

		movieCollectionList.add(movieList);

		currentPage++;
		if (currentPage < movieList.getTotalPages()) {
			createMovieCollection();
		}
	}

	private List<Movie> removeRealeasedMovies(final List<Movie> _movies) {
		return  _movies
					.stream()
					.filter(movie -> compareTodayWithMovieReleaseDate(movie.getReleaseDate()) <= 0)
					.collect(Collectors.toList());
	}

	private int compareTodayWithMovieReleaseDate(final String movieReleaseDate) {
		LocalDate today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate releaseDate = LocalDate.parse(movieReleaseDate);
		return today.compareTo(releaseDate);
	}

	private List<Genre> getFullGenreList(final List<Genre> _genres) {
		List<Genre> genresToUpdate = new ArrayList<>();
		for (Genre _genre : _genres) {
			genresToUpdate.addAll(this.genres.stream().filter(genre -> genre.getId().equals(_genre.getId()))
					.collect(Collectors.toList()));
		}
		return genresToUpdate;
	}

	private void importGenres() {
		RestTemplate restTemplate = new RestTemplate();
		GenreList genreList = restTemplate.getForObject(TmdbApiHelper.getGenresUrl(), GenreList.class);
		genres = genreList.getGenres();
		genreRepository.saveAll(genreList.getGenres());
	}

	public void importMovies() {
		cleanDatabase();
		importGenres();
		createMovieCollection();

		for (MovieList movieList : movieCollectionList) {
			movieRepository.saveAll(movieList.getMovies());
		}
	}

	private void cleanDatabase() {
		movieRepository.deleteAll();
		genreRepository.deleteAll();
	}
}
