package br.com.moviesilove.tmdb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {

	@Id
	private Integer id;
	private String title;

	@Column(length = 2000)
	private String overview;

	@JsonProperty("genre_ids")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
	private List<Genre> genres;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("release_date")
	private String releaseDate;

	@Override
	public String toString() {
		return "Value{" + "id=" + id + ", title='" + title + '\'' + '}';
	}

}
