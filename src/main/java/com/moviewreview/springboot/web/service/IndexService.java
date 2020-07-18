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
import java.sql.Statement;
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
	
	public Map<String, Object> getMovieList() throws SQLException {
		Connection conn = initialiseConnection();
		Map<String,Object> returnMap = new HashMap<>();
		return getMoviesList(conn,returnMap);
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
			movie.setDescription(rs.getString("description"));
			movie.setId(rs.getString("id"));
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

	public Map<String, Object> postData(Map userDetailMap) {
		Connection conn = initialiseConnection();
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("status","fail");
		try {
			PreparedStatement st = conn.prepareStatement("Insert into movie_data (name,year,genre,time,rating,description) values (?,?,?,?,?,?)");
			st.setString(1, (String) userDetailMap.get("name"));
			st.setString(2, (String)(userDetailMap.get("year")));
			st.setString(3, (String) userDetailMap.get("genre"));
			st.setString(4, (String) userDetailMap.get("time"));
			st.setString(5, (String) userDetailMap.get("rating"));
			st.setString(6, (String) userDetailMap.get("description"));
			int status = st.executeUpdate();
			returnMap.put("status","pass");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
	public Map<String, Object> updateUserDetails(Map userDetailMap) throws SQLException {
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("status", "fail");
		Connection conn = initialiseConnection();
		Statement stmt = conn.createStatement();
		String userId = (String)(userDetailMap.get("id"));
		String name = (String) userDetailMap.get("name");
		String year = (String) userDetailMap.get("year");
		String genre = (String) (userDetailMap.get("genre"));
		String time = (String) userDetailMap.get("time");
		String rating = (String) userDetailMap.get("rating");
		String description = (String) userDetailMap.get("description");
		PreparedStatement st;
		try {
			st = conn.prepareStatement("update movie_data set name = ? , year = ? , genre = ? , time = ? , rating = ? , description = ? where id = ? ");
			st.setString(1, name);
			st.setString(2, year);
			st.setString(3, genre);
			st.setString(4, time);
			st.setString(5, rating);
			st.setString(6, description);
			st.setString(7, userId);
			st.executeUpdate();
			returnMap.put("status", "pass");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnMap;
	}

	public Map<String, Object> deleteUserDetails(Map userDetailMap) throws SQLException {
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("status", "fail");
		Connection conn = initialiseConnection();
		Statement stmt = conn.createStatement();
		String userId = (String)(userDetailMap.get("id"));
		PreparedStatement st;
		try {
			st = conn.prepareStatement("delete from movie_data where id = ? ");
			st.setString(1, userId);
			st.executeUpdate();
			returnMap.put("status", "pass");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnMap;
	}
}
