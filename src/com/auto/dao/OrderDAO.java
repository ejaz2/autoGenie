package com.auto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.auto.common.DBConnector;
import com.auto.dto.OrderDTO;
import com.auto.exception.DataAccessSqlException;

public class OrderDAO {
	private static Log LOGGER = LogFactory.getLog(OrderDAO.class);
	private static final String CREATE_ORDER = "insert into orders (gid,pid,description,order_status,invoice,pdid,uid) values (?,?,?,?,?,?,?)";

	public void placeOrder(OrderDTO orderDTO) throws DataAccessSqlException {
		Connection conn = DBConnector.getPooledConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(CREATE_ORDER);
			pstmt.setLong(1, orderDTO.getVid());
			pstmt.setLong(2, orderDTO.getPid());
			pstmt.setString(3, orderDTO.getDescription());
			pstmt.setString(4, orderDTO.getOrderStatus());
			pstmt.setDouble(5, orderDTO.getInvoice());
			pstmt.setLong(6, orderDTO.getDid());
			pstmt.setLong(7, orderDTO.getUid());
			LOGGER.info("create order query : " + pstmt.toString());
			pstmt.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("error in create order", e);
			throw new DataAccessSqlException("error in create order");
		} finally {
			DBConnector.close(pstmt);
			DBConnector.close(conn);
		}
	}

}
