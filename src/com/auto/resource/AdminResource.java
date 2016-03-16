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

//@RolesAllowed("ADMIN")
@Path("admin")
public class AdminResource {

	AdminServiceImpl adminServiceImpl;

	public AdminResource() {
		adminServiceImpl = new AdminServiceImpl();
	}

	@POST
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	public int createDistributor(DistributorDetailDTO distributorDetailDTO) {
		return adminServiceImpl.createDistributor(distributorDetailDTO);
	}

	@PUT
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDistributor(DistributorDetailDTO distributorDetailDTO) {
		adminServiceImpl.updateDistributor(distributorDetailDTO);
	}

	@DELETE
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteDistributor(DistributorDetailDTO distributorDetailDTO) {
		adminServiceImpl.deleteDistributor(distributorDetailDTO);
	}

	@RolesAllowed("ADMIN")
	@GET
	@Path("ditributor/{did}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DistributorDetailDTO> getDistributor(@PathParam("did") long did) {
		return adminServiceImpl.getDistributor(did);
	}

	@RolesAllowed("ADMIN")
	@GET
	@Path("ditributor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DistributorDetailDTO> getDistributors() {
		return adminServiceImpl.getDistributor(0);
	}

	@POST
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	public int createParts(PartsDetailDTO partsDetailDTO) {
		return adminServiceImpl.createParts(partsDetailDTO);
	}

	@PUT
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateParts(PartsDetailDTO partsDetailDTO) {
		adminServiceImpl.updateParts(partsDetailDTO);
	}

	@DELETE
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteParts(PartsDetailDTO partsDetailDTO) {
		adminServiceImpl.deleteParts(partsDetailDTO);
	}

	@RolesAllowed("USER")
	@GET
	@Path("parts/{pid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PartsDetailDTO> getParts(@PathParam("pid") long pid) {
		return adminServiceImpl.getParts(pid);
	}

	@RolesAllowed("USER")
	@GET
	@Path("parts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PartsDetailDTO> getParts() {
		return adminServiceImpl.getParts(0);
	}

	@POST
	@Path("vendor")
	@Consumes(MediaType.APPLICATION_JSON)
	public int createVendor(VendorDetailDTO vendorDetailDTO) {
		return adminServiceImpl.createVendor(vendorDetailDTO);
	}

	@PUT
	@Path("vendor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateVendor(VendorDetailDTO vendorDetailDTO) {
		adminServiceImpl.updateVendor(vendorDetailDTO);
	}

	@DELETE
	@Path("vendor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteVendor(VendorDetailDTO vendorDetailDTO) {
		adminServiceImpl.deleteVendor(vendorDetailDTO);
	}

	@GET
	@Path("vendor/{vid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<VendorDetailDTO> getVendor(@PathParam("vid") long vid) {
		return adminServiceImpl.getVendor(vid);
	}

	@GET
	@Path("vendor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<VendorDetailDTO> getVendor() {
		return adminServiceImpl.getVendor(0);
	}
}
