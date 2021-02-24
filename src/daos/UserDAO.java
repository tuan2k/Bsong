package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;
import utils.ConnectDBUlti;
import utils.DefineUtil;

public class UserDAO {
	
private Connection conn;
	
	private Statement st;
	
	private ResultSet rs;
	
	private PreparedStatement pst;
	
	public ArrayList<User> getAllUser(){
		String SQL = "SELECT * FROM users";
		ArrayList<User> users = new ArrayList<>();
		conn = ConnectDBUlti.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				User user = new User(id, username, password, fullname);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst, rs);
		}
		return users;
	}
	
	public int numberOfUser() {
		int count  =0;
		String SQL = "SELECT COUNT(*) AS count FROM users";
		conn = ConnectDBUlti.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, st,rs);
		}
		return count;
	}
	
	public int add(User user){
		String SQL = "INSERT INTO users (username,password,fullname) VALUE (?,?,?)";
		int result = 0;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	
	public boolean Validate(User user){
		String SQL = "select * from users where username = ?";
		boolean result = true;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, user.getUsername());
			rs = pst.executeQuery();
			if (rs.next()) {
				result = false;
			}else result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	
	public int Delete(int id){
		
		String SQL = "DELETE FROM users where id = ?";
		int result = 0;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1,id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	
	public User getById(int id) {
		conn = ConnectDBUlti.getConnection();
		User user = null;
		String sql = "select * from users where id = ?";
		try {
			pst= conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				int uid = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				user = new User(uid, username, password, fullname);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectDBUlti.close(conn, pst);
		}
		return user;
	}

	public int update(User user) {
		String SQL = "UPDATE users set username= ?,password = ?,fullname = ? where id = ?";
		int result = 0;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setInt(4, user.getId());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	public User findByUserAndPassword(String username, String password) {
		conn = ConnectDBUlti.getConnection();
		User user = null;
		String sql = "select * from users where username=? 	AND password = ?";
		try {
				pst= conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, password);
				rs = pst.executeQuery();
				if (rs.next()) {
					int nid = rs.getInt("id");
					String nusername = rs.getString("username");
					String npassword = rs.getString("password");
					String nfullname = rs.getString("fullname");
					user = new User(nid, nusername, npassword, nfullname);
				}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally {
			ConnectDBUlti.close(conn, pst,rs);
		}
		return user;
	}
	
public List<User> getSongPagination(int offset) {
		
		ArrayList<User> list = new ArrayList<User>();
		final String query = "select * from users limit ?,?" ;
		try {
			conn = ConnectDBUlti.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				User user = new User(id, username, password, fullname);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
