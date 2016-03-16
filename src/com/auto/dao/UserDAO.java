package com.auto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.auto.common.DBConnector;
import com.auto.dto.UserDTO;

public class UserDAO {
	private static Log LOGGER = LogFactory.getLog(UserDAO.class);
	private static final String CREATE_USER = "insert into user(f_name,l_name,email,u_type,contact) values(?,?,?,?,?)";
	private static final String UPDATE_USER = "update user set f_name = ? , l_name = ? ,email = ? , u_type = ? , contact = ? where uid = ?";
	private static final String GET_USER = "select uid,l_name,email,u_type,contact,f_name from user";
	private static final String USER_WHERE = " where uid = ?";
	private static final String DELETE_USER = "delete from user where uid = ?";

	public int createUser(UserDTO userDto) {
		Connection conn = DBConnector.getPooledConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			pstmt = conn.prepareStatement(CREATE_USER,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, userDto.getfName());
			pstmt.setString(2, userDto.getlName());
			pstmt.setString(3, userDto.getEmail());
			pstmt.setInt(4, userDto.getuType());
			pstmt.setLong(5, userDto.getContact());
			LOGGER.info("create user query : " + pstmt.toString());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			LOGGER.error("error in create user", e);
		} finally {
			DBConnector.close(rs);
			DBConnector.close(pstmt);
			DBConnector.close(conn);
		}
		return id;
	}

	public void updateUser(UserDTO userDto) {
		Connection conn = DBConnector.getPooledConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(UPDATE_USER);
			pstmt.setString(1, userDto.getfName());
			pstmt.setString(2, userDto.getlName());
			pstmt.setString(3, userDto.getEmail());
			pstmt.setInt(4, userDto.getuType());
			pstmt.setLong(5, userDto.getContact());
			pstmt.setLong(6, userDto.getUid());
			LOGGER.info("update user query : " + pstmt.toString());
			pstmt.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("error in update user", e);
		} finally {
			DBConnector.close(pstmt);
			DBConnector.close(conn);
		}
	}

	public List<UserDTO> getUserDetails(long uid) {
		Connection conn = DBConnector.getPooledConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		try {
			if (uid > 0) {
				pstmt = conn.prepareStatement(GET_USER + USER_WHERE);
				pstmt.setLong(1, uid);
			} else {
				pstmt = conn.prepareStatement(GET_USER);
			}
			LOGGER.info("get user details query : " + pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setUid(rs.getLong("uid"));
				dto.setfName(rs.getString("f_name"));
				dto.setlName(rs.getString("l_name"));
				dto.setEmail(rs.getString("email"));
				dto.setContact(rs.getLong("contact"));
				dto.setuType(rs.getInt("u_type"));
				usersList.add(dto);
			}
		} catch (Exception e) {
			LOGGER.error("error in get user details", e);
		} finally {
			DBConnector.close(rs);
			DBConnector.close(pstmt);
			DBConnector.close(conn);
		}
		return usersList;
	}

	public void deleteUser(long uid) {
		Connection conn = DBConnector.getPooledConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_USER);
			pstmt.setLong(1, uid);
			LOGGER.info("delete user query : " + pstmt.toString());
			pstmt.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("error in delete user", e);
		} finally {
			DBConnector.close(pstmt);
			DBConnector.close(conn);
		}
	}
}
