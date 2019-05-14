package br.com.moviesilove.tmdb.helpers;

public abstract class TmdbApiHelper {

	private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3";
	private static final String TMDB_API_KEY = "1f54bd990f1cdfb230adb312546d765d";
	private static final String TMDB_API_LANG = "en-US";
	private static final String TMDB_API_ENDPOINT_MOVIES = "/movie";
	private static final String TMDB_API_ENDPOINT_UPCOMING_MOVIES = "/upcoming";
	private static final String TMDB_API_ENDPOINT_GENRES = "/genre";
	private static final String TMDB_API_ENDPOINT_APPEND_KEY = "?api_key=" + TMDB_API_KEY;
	private static final String TMDB_API_ENDPOINT_APPEND_LANGUAGE = "&language=" + TMDB_API_LANG;
	private static final String TMDB_API_ENDPOINT_APPEND_PAGE = "&page=";

	public static String getUpcomingMoviesUrlWithPage(final Integer page) {
		String url = TMDB_API_BASE_URL + TMDB_API_ENDPOINT_MOVIES + TMDB_API_ENDPOINT_UPCOMING_MOVIES;
		return  appendWithApiKeyAndLanguage(url) + TMDB_API_ENDPOINT_APPEND_PAGE
				+ page;
	}
	
	public static String getGenresUrl() {
		String url = TMDB_API_BASE_URL + TMDB_API_ENDPOINT_GENRES + TMDB_API_ENDPOINT_MOVIES + "/list";
		return  appendWithApiKeyAndLanguage(url);
	}
	
	public static String getMovieDetailsUrlByMovieId(final Integer movieId) {
		String url = TMDB_API_BASE_URL + TMDB_API_ENDPOINT_MOVIES + "/" + movieId;
		return appendWithApiKeyAndLanguage(url);
	}

	private static String appendWithApiKeyAndLanguage(final String _url) {
		StringBuilder url = new StringBuilder(_url);
		url.append(TMDB_API_ENDPOINT_APPEND_KEY);
		url.append(TMDB_API_ENDPOINT_APPEND_LANGUAGE);
		return url.toString();
	}

}
