package de.krambri.Coins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


import lib.PatPeter.SQLibrary.Database;
import lib.PatPeter.SQLibrary.MySQL;


public class methods{
	Coins plugin;
	String host;
	String port;
	String db;
	String user;
	String pw;
	Database sql;
	
	
	
	
	public methods(){
		
	}
	

	public int getCoins(String Playername){
		
		String query = "SELECT `Coins` FROM `coins` WHERE `Player` = '" + Playername + "' LIMIT 0, 30 ";
		ResultSet rs;
		String rss = null;
		try {
			rs = sql.query(query);
			while (rs.next()) {
				rss = rs.getString("Coins");
			}
			return Integer.parseInt(rss);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public boolean addCoins(String Playername, int coins){
		String query = "SELECT `Coins` FROM `coins` WHERE `Player` = '" + Playername + "' LIMIT 0, 30 ";
		ResultSet rs;
		int rss = 0;
		try {
			rs = sql.query(query);
			while (rs.next()) {
				rss = Integer.getInteger(rs.getString("Coins"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		int erg = rss + coins;
		
		query = "UPDATE `coins` SET `Coins`= " + erg + " WHERE `Player` = '" + Playername + "'";
		try {
			sql.query(query);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean removeUser(String Playername){
		
		String query = "DELETE FROM `coins` WHERE `Player` = '" + Playername + "'";
		try {
			sql.query(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	@SuppressWarnings("null")
	public boolean addUser(String Playername){
		ResultSet rs = null;
		String rss = null;
		String query = " SELECT * FROM `coins` WHERE `Player` = '" + Playername + "'";
		try {
			sql.query(query);
			while (rs.next()) {
				rss = rs.getString("Player");
			}
		} catch (SQLException e) {
		}
		if(rss == null){
			query = "INSERT INTO `coins`(`Player`, `Coins`) VALUES ('" + Playername + "','" + plugin.getConfig().getString("Coins.Startamount") + "')";
			try {
				sql.query(query);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	public boolean removeCoins(String Playername, int coins){
		String query = "SELECT `Coins` FROM `coins` WHERE `Player` = '" + Playername + "' LIMIT 0, 30 ";
		ResultSet rs;
		int rss = 0;
		try {
			rs = sql.query(query);
			while (rs.next()) {
				rss = Integer.getInteger(rs.getString("Coins"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		int erg = rss - coins;
		
		query = "UPDATE `coins` SET `Coins`= " + erg + " WHERE `Player` = '" + Playername + "'";
		try {
			sql.query(query);
		} catch (SQLException e) {
			return false;
		}
		return true;

	}
	
	public void instantiat(Coins instance){
		this.plugin = instance;
		host = plugin.getConfig().getString("MySQL.host"); 
		port = plugin.getConfig().getString("MySQL.port");
		db = plugin.getConfig().getString("MySQL.database");
		user = plugin.getConfig().getString("MySQL.user");
		pw = plugin.getConfig().getString("MySQL.password");
		sql = new MySQL(Logger.getLogger("Minecraft"),"[KrambriChat]", host, port, db, user, pw);
		sql.open();
	}
	
	
}
