package br.com.moviesilove.tmdb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MovieList {

	@JsonProperty("results")
	private List<Movie> movies;

	@JsonProperty("total_pages")
	private Integer totalPages;

	@JsonProperty("total_results")
	private Integer totalResults;

}
