package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Song;
import utils.ConnectDBUlti;
import utils.DefineUtil;

public class SongDAO {
	
	private Connection con;
	private Statement st;
	private PreparedStatement pstm;
	private ResultSet res;
	public ArrayList<Song> getAll(){
		ArrayList<Song> list = new ArrayList<Song>();
		final String query = "select * from songs" ;
		try {
			con = ConnectDBUlti.getConnection();
			st = con.createStatement();
			res = st.executeQuery(query);
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String preview = res.getString("preview_text");
				String detail = res.getString("detail_text");
				Timestamp date = res.getTimestamp("date_create");
				String pic = res.getString("picture");
				int counter = res.getInt("counter");
				int cat_id = res.getInt("cat_id");
				Song song = new Song(id, name, preview, detail, date, pic, counter, cat_id);
				list.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int numberOfSong() {
		int count  =0;
		String SQL = "SELECT COUNT(*) AS count FROM songs";
		con = ConnectDBUlti.getConnection();
		try {
			st = con.createStatement();
			res = st.executeQuery(SQL);
			if (res.next()) {
				count = res.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(con, st,res);
		}
		return count;
	}
	
	public int add(Song song){
		String SQL = "INSERT INTO songs (name,preview_text,detail_text,picture,cat_id) VALUE (?,?,?,?,?)";
		int result = 0;
		con = ConnectDBUlti.getConnection();
		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, song.getName());
			pstm.setString(2, song.getPreview());
			pstm.setString(3, song.getDetail());
			pstm.setString(4, song.getPic());
			pstm.setInt(5, song.getCat_id());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(con, pstm);
		}
		return result;
	}
	public int update(Song song) {
		String SQL = "UPDATE songs set name=?, preview_text=?, detail_text=?,picture=?,cat_id=? where id = ?";
		int result = 0;
		con = ConnectDBUlti.getConnection();
		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, song.getName());
			pstm.setString(2, song.getPreview());
			pstm.setString(3, song.getDetail());
			pstm.setString(4, song.getPic());
			pstm.setInt(5, song.getCat_id());
			pstm.setInt(6, song.getId());
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUlti.close(con, pstm);
		}
		return result;
	}
	public int delete(int id) {
		String SQL = "DELETE from songs where id = ?";
		int result = 0;
		con = ConnectDBUlti.getConnection();
		try {
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, id);
			result = pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Song getSongById(int _id) {
		Song song = null;
        con = ConnectDBUlti.getConnection();
        try {
        	
            String q = "select * from songs where id = ?";
            pstm  = con.prepareStatement(q);
            pstm.setInt(1,_id);
            res = pstm.executeQuery();
            if (res.next()) {
            	int id = res.getInt("id");
				String name = res.getString("name");
				String preview = res.getString("preview_text");
				String detail = res.getString("detail_text");
				Timestamp date = res.getTimestamp("date_create");
				String pic = res.getString("picture");
				int counter = res.getInt("counter");
				int cat_id = res.getInt("cat_id");
				song = new Song(id, name, preview, detail, date, pic, counter, cat_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return song;
    }

	public List<Song> getSongPagination(int offset) {
		
		ArrayList<Song> list = new ArrayList<Song>();
		final String query = "select * from songs limit ?,?" ;
		try {
			con = ConnectDBUlti.getConnection();
			pstm = con.prepareStatement(query);
			pstm.setInt(1, offset);
			pstm.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			res = pstm.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String preview = res.getString("preview_text");
				String detail = res.getString("detail_text");
				Timestamp date = res.getTimestamp("date_create");
				String pic = res.getString("picture");
				int counter = res.getInt("counter");
				int cat_id = res.getInt("cat_id");
				Song song = new Song(id, name, preview, detail, date, pic, counter, cat_id);
				list.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Song> getSongPaginationByCatID(int cid, int offset) {
		ArrayList<Song> list = new ArrayList<Song>();
		final String query = "select * from songs where cat_id = ? limit ?,?" ;
		try {
			con = ConnectDBUlti.getConnection();
			pstm = con.prepareStatement(query);
			pstm.setInt(1, cid);
			pstm.setInt(2, offset);
			pstm.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			res = pstm.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String preview = res.getString("preview_text");
				String detail = res.getString("detail_text");
				Timestamp date = res.getTimestamp("date_create");
				String pic = res.getString("picture");
				int counter = res.getInt("counter");
				int cat_id = res.getInt("cat_id");
				Song song = new Song(id, name, preview, detail, date, pic, counter, cat_id);
				list.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Song> getSongSearch(String nsearch) {
		ArrayList<Song> list = new ArrayList<Song>();
		final String query = "select * from songs where name LIKE ? " ;
		try {
			con = ConnectDBUlti.getConnection();
			pstm = con.prepareStatement(query);
			pstm.setString(1,"%"+ nsearch + "%");
			res = pstm.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String preview = res.getString("preview_text");
				String detail = res.getString("detail_text");
				Timestamp date = res.getTimestamp("date_create");
				String pic = res.getString("picture");
				int counter = res.getInt("counter");
				int cat_id = res.getInt("cat_id");
				Song song = new Song(id, name, preview, detail, date, pic, counter, cat_id);
				list.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Song> getSongPaginationBySearch(String search,int offset) {
		ArrayList<Song> list = new ArrayList<Song>();
		final String query = "select * from songs where name LIKE ? limit ?,?" ;
		try {
			con = ConnectDBUlti.getConnection();
			pstm = con.prepareStatement(query);
			pstm.setString(1, "%"+ search + "%");
			pstm.setInt(2, offset);
			pstm.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			res = pstm.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String preview = res.getString("preview_text");
				String detail = res.getString("detail_text");
				Timestamp date = res.getTimestamp("date_create");
				String pic = res.getString("picture");
				int counter = res.getInt("counter");
				int cat_id = res.getInt("cat_id");
				Song song = new Song(id, name, preview, detail, date, pic, counter, cat_id);
				list.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean getSongNameByName(String name) {
		String SQL = "select * from songs where name = ?";
		boolean result = true;
		con = ConnectDBUlti.getConnection();
		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, name);
			res = pstm.executeQuery();
			if (res.next()) {
				result = false;
			}else result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDBUlti.close(con, pstm,res);
		}
		return result;
	}

}
