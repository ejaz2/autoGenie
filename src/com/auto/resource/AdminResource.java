package com.auto.resource;

import java.util.List;

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

import com.auto.dto.DistributorDetailDTO;
import com.auto.dto.PartsDetailDTO;
import com.auto.dto.VendorDetailDTO;
import com.auto.service.AdminServiceImpl;

@Path("admin")
public class AdminResource {

	AdminServiceImpl adminServiceImpl;

	public AdminResource() {
		adminServiceImpl = new AdminServiceImpl();
	}

	@RolesAllowed({ "ADMIN" })
	@POST
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	public int createDistributor(DistributorDetailDTO distributorDetailDTO) {
		return adminServiceImpl.createDistributor(distributorDetailDTO);
	}

	@RolesAllowed({ "ADMIN" })
	@PUT
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDistributor(DistributorDetailDTO distributorDetailDTO) {
		adminServiceImpl.updateDistributor(distributorDetailDTO);
	}

	@RolesAllowed({ "ADMIN" })
	@DELETE
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteDistributor(DistributorDetailDTO distributorDetailDTO) {
		adminServiceImpl.deleteDistributor(distributorDetailDTO);
	}

	@GET
	@Path("ditributor/{did}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DistributorDetailDTO> getDistributor(@PathParam("did") long did) {
		return adminServiceImpl.getDistributor(did);
	}

	@GET
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DistributorDetailDTO> getDistributors() {
		return adminServiceImpl.getDistributor(0);
	}

	@RolesAllowed({ "ADMIN" })
	@POST
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	public int createParts(PartsDetailDTO partsDetailDTO) {
		return adminServiceImpl.createParts(partsDetailDTO);
	}

	@RolesAllowed({ "ADMIN" })
	@PUT
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateParts(PartsDetailDTO partsDetailDTO) {
		adminServiceImpl.updateParts(partsDetailDTO);
	}

	@RolesAllowed({ "ADMIN" })
	@DELETE
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteParts(PartsDetailDTO partsDetailDTO) {
		adminServiceImpl.deleteParts(partsDetailDTO);
	}

	@GET
	@Path("parts/{pid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PartsDetailDTO> getParts(@PathParam("pid") long pid) {
		return adminServiceImpl.getParts(pid);
	}

	@GET
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PartsDetailDTO> getParts() {
		return adminServiceImpl.getParts(0);
	}

	@RolesAllowed({ "ADMIN" })
	@POST
	@Path("vendor")
	@Consumes(MediaType.APPLICATION_JSON)
	public int createVendor(VendorDetailDTO vendorDetailDTO) {
		return adminServiceImpl.createVendor(vendorDetailDTO);
	}

	@RolesAllowed({ "ADMIN" })
	@PUT
	@Path("vendor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateVendor(VendorDetailDTO vendorDetailDTO) {
		adminServiceImpl.updateVendor(vendorDetailDTO);
	}

	@RolesAllowed({ "ADMIN" })
	@DELETE
	@Path("vendor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteVendor(VendorDetailDTO vendorDetailDTO) {
		adminServiceImpl.deleteVendor(vendorDetailDTO);
	}

	@GET
	@Path("vendor/{vid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VendorDetailDTO> getVendor(@PathParam("vid") long vid) {
		return adminServiceImpl.getVendor(vid);
	}

	@GET
	@Path("vendor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VendorDetailDTO> getVendor() {
		return adminServiceImpl.getVendor(0);
	}
}
