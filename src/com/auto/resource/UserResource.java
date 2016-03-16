package com.auto.resource;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.auto.dto.OrderDTO;
import com.auto.dto.UserDTO;
import com.auto.service.UserServiceImpl;

@PermitAll
@Path("user")
@RolesAllowed("user")
public class UserResource {
	UserServiceImpl serviceImpl;

	public UserResource() {
		serviceImpl = new UserServiceImpl();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int createUser(UserDTO userDTO) {
		return serviceImpl.createUser(userDTO);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUser(UserDTO userDTO) {
		serviceImpl.updateUser(userDTO);
	}

	@GET
	@Path("{uid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDTO> getUser(@PathParam("uid") long uid) {
		return serviceImpl.getUser(uid);
	}

	@DELETE
	@Path("{uid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteUser(@PathParam("uid") long uid) {
		serviceImpl.deleteUser(uid);
	}

	@POST
	@Path("{uid}/order")
	@Consumes(MediaType.APPLICATION_JSON)
	public void placeOrder(OrderDTO orderDTO) {
		serviceImpl.placeOrder(orderDTO);
	}
}
