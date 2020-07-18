package com.moviewreview.springboot.web.model;

public class Comments {

	String movieId;
	String comment;
	String user;
   
	public String getMovieId() {
		return movieId;
	}
	public void setId(String movieId) {
		this.movieId = movieId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}