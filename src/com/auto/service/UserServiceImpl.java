package com.auto.service;

import java.util.List;

import com.auto.dao.OrderDAO;
import com.auto.dao.UserDAO;
import com.auto.dto.OrderDTO;
import com.auto.dto.UserDTO;

public class UserServiceImpl {
	UserDAO userDAO;
	OrderDAO orderDAO;

	public UserServiceImpl() {
		userDAO = new UserDAO();
		orderDAO = new OrderDAO();
	}

	public int createUser(UserDTO userDTO) {
		return userDAO.createUser(userDTO);
	}

	public void updateUser(UserDTO userDTO) {
		userDAO.updateUser(userDTO);
	}

	public List<UserDTO> getUser(long uid) {
		return userDAO.getUserDetails(uid);
	}

	public void deleteUser(long uid) {
		userDAO.deleteUser(uid);
	}

	public void placeOrder(OrderDTO orderDTO) {
		orderDAO.placeOrder(orderDTO);
	}

}
