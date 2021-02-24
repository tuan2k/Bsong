package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Contact;
import utils.ConnectDBUlti;
import utils.DefineUtil;

public class ContactDAO {
	
	private Connection conn;
	
	private Statement st;
	
	private ResultSet rs;
	
	private PreparedStatement pst;
	
	public List<Contact> getContacts(){
		String SQL = "SELECT * FROM contacts";
		List<Contact> contacts = new ArrayList<>();
		conn = ConnectDBUlti.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				Contact contact = new Contact(id, name, email, website, message);
				contacts.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst, rs);
		}
		return contacts;
	}
	public int numberOfContact() {
		int count  =0;
		String SQL = "SELECT COUNT(*) AS count FROM contacts";
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
	
	public int add(Contact contact){
		String SQL = "INSERT INTO contacts (name,email,website,message) VALUE (?,?,?,?)";
		int result = 0;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getWebsite());
			pst.setString(4, contact.getMessage());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	
	public int Delete(int id){
		String SQL = "DELETE FROM contacts where id = ?";
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
	

	public Contact getById(int id) {
		conn = ConnectDBUlti.getConnection();
		Contact contact = null;
		String sql = "select * from contacts where id = ?";
		try {
			pst= conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				int cid = rs.getInt("id");
				String cname = rs.getString("name");
				String cemail = rs.getString("email");
				String cwebsite = rs.getString("website");
				String message = rs.getString("message");
				contact = new Contact(cid, cname, cemail, cwebsite, message);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return contact;
	}

	public int update(Contact contact) {
		String SQL = "UPDATE contacts set name= ?,email=?,website=?,message=? where id = ?";
		int result = 0;
		conn = ConnectDBUlti.getConnection();
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3,contact.getWebsite());
			pst.setString(4, contact.getMessage());
			pst.setInt(5, contact.getId());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(conn, pst);
		}
		return result;
	}
	
public List<Contact> getSongPagination(int offset) {
		
		ArrayList<Contact> list = new ArrayList<Contact>();
		final String query = "select * from contacts limit ?,?" ;
		try {
			conn = ConnectDBUlti.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				Contact contact = new Contact(id, name, email, website, message);
				list.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
