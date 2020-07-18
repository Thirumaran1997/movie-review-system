package com.moviewreview.springboot.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.moviewreview.springboot.web.model.Comments;
import com.moviewreview.springboot.web.model.Movies;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

@Service
public class IndexService {

	private final static String url = "jdbc:mysql://localhost:3306/moviesdb";
	private final static String user = "root";
	private final static String password = "Thiru6497@";

	private static Connection initialiseConnection() {
		Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(url, user, password);
	        System.out.println("Connected to the DataBase server successfully.");

	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return conn;
	}

	public Map<String, Object> validateUser(Map<String,Object> paramMap) {
		Map<String,Object> returnMap = new HashMap<>();
		Connection conn = initialiseConnection();
		PreparedStatement st;
		try {
			String user = paramMap.get("username").toString();
			String password = paramMap.get("password").toString();
			st = conn.prepareStatement("Select * from user_data where username= ? and password = ? ");
			st.setString(1, user);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
				returnMap.put("result", "fail");
				while (rs.next()) {
					returnMap.put("result", "pass");
					returnMap.put("role", rs.getString("ROLE"));
					returnMap = getMoviesList(conn,returnMap);
				}
			conn.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return returnMap;
	}

	private Map<String, Object> getMoviesList(Connection conn, Map<String, Object> returnMap) throws SQLException {
		PreparedStatement st = conn.prepareStatement("Select * from movie_data");
		ResultSet rs = st.executeQuery();
		List<Movies> movieList = new ArrayList<>(); 
		while (rs.next()) {
			Movies movie = new Movies();
			movie.setName(rs.getString("name"));
			movie.setGenre(rs.getString("genre"));
			movie.setRating(rs.getString("rating"));
			movie.setTime(rs.getString("time"));
			movie.setYear(rs.getString("year"));
			movieList.add(movie);
		}
		returnMap.put("movieList", movieList);
		return returnMap;
	}
	
	private Map<String, Object> getCommentsList(Connection conn, Map<String, Object> returnMap) throws SQLException {
		PreparedStatement st = conn.prepareStatement("Select * from comment_info where movie_id = ?");
		st.setString(1,(String) returnMap.get("movieId"));
		ResultSet rs = st.executeQuery();
		
		List<Comments> commentList = new ArrayList<>(); 
		while (rs.next()) {
			Comments comment = new Comments();
			comment.setUser(rs.getString("user"));
			comment.setComment(rs.getString("comment"));
			commentList.add(comment);
		}
		returnMap.put("commentsList", commentList);
		return returnMap;
	}
}
