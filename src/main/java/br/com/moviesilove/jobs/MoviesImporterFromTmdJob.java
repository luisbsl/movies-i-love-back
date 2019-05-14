package br.com.moviesilove.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.moviesilove.tmdb.service.TmdbImporterService;

@Component
public class MoviesImporterFromTmdJob {
	
	@Autowired
	private TmdbImporterService tmdbImporter;

	/**
	 * Every day at 00:00 every single upcoming movie will be imported from TMBD API
	 */
	@Scheduled(cron="00 00 * * * *")
	void runTmdbImporterJob() {
		tmdbImporter.importMovies();
	}

}
