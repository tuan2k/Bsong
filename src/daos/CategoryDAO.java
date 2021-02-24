package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import utils.ConnectDBUlti;
import utils.DefineUtil;

public class CategoryDAO {
	
	private Connection conn;
	
	private Statement st;
	
	private ResultSet rs;
	
	private PreparedStatement pst;
	
	public List<Category> getCategories(){
		String SQL = "SELECT id, name FROM categories";
		List<Category> categories = new ArrayList<>();
		conn = ConnectDBUlti.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Category category = new Category(id, name);
				categories.add(category);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst, rs);
		}
		return categories;
	}
	
	public int numberOfCat() {
		int count  =0;
		String SQL = "SELECT COUNT(*) AS count FROM categories";
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
	
	public int add(Category cat){
		String SQL = "INSERT INTO categories (name) VALUE (?)";
		int result = 0;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, cat.getName());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	
	public String getCatnameByid(int cat_id) {
		String name = "";
		conn = ConnectDBUlti.getConnection();
		String sql = "select * from categories where id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			rs = pst.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectDBUlti.close(conn, pst);
		}
		return name;
	}
	
	public int Delete(int id){
		String SQL = "DELETE FROM categories where id = ?";
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
	

	public Category getById(int id) {
		conn = ConnectDBUlti.getConnection();
		Category cat = null;
		String sql = "select * from categories where id = ?";
		try {
			pst= conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				int cid = rs.getInt("id");
				String cname = rs.getString("name");
				cat = new Category(cid,cname);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectDBUlti.close(conn, pst,rs);
		}
		return cat;
	}

	public int update(Category cat) {
		String SQL = "UPDATE categories set name= ? where id = ?";
		int result = 0;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, cat.getName());
			pst.setInt(2, cat.getId());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	
public List<Category> getCatPagination(int offset) {
		
		ArrayList<Category> list = new ArrayList<Category>();
		final String query = "select * from categories limit ?,?" ;
		try {
			conn = ConnectDBUlti.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");				
				Category cat = new Category(id, name);
				list.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBUlti.close(conn, pst,rs);
		}
		return list;
	}

public boolean getByName(String name) {
	String SQL = "select * from categories where name = ?";
	boolean result = true;
	conn = ConnectDBUlti.getConnection();
	try {
		pst = conn.prepareStatement(SQL);
		pst.setString(1, name);
		rs = pst.executeQuery();
		if (rs.next()) {
			result = false;
		}else result = true;
		
	} catch (SQLException e) {
		e.printStackTrace();
		result = false;
	} finally {
		ConnectDBUlti.close(conn, pst,rs);
	}
	return result;
}
	
}
