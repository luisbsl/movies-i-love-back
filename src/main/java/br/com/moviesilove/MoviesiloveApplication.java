package br.com.moviesilove;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.moviesilove.tmdb.service.TmdbImporterService;

@SpringBootApplication
@EnableScheduling
public class MoviesiloveApplication {

	@Autowired
	private TmdbImporterService tmdbImporter;

	public static void main(String[] args) {
		SpringApplication.run(MoviesiloveApplication.class, args);
	}

	@PostConstruct
	private void init() {
		tmdbImporter.importMovies();
	}
}