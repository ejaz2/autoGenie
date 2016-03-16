package com.auto.service;

import java.util.List;

import com.auto.dao.DistributorDAO;
import com.auto.dao.PartsDAO;
import com.auto.dao.VendorDAO;
import com.auto.dto.DistributorDetailDTO;
import com.auto.dto.PartsDetailDTO;
import com.auto.dto.VendorDetailDTO;

public class AdminServiceImpl {
	DistributorDAO distributorDAO;
	PartsDAO partsDAO;
	VendorDAO vendorDAO;

	public AdminServiceImpl() {
		distributorDAO = new DistributorDAO();
		partsDAO = new PartsDAO();
		vendorDAO = new VendorDAO();
	}

	public int createDistributor(DistributorDetailDTO distributorDetailDTO) {
		return distributorDAO.createDistributor(distributorDetailDTO);
	}

	public void updateDistributor(DistributorDetailDTO distributorDetailDTO) {
		distributorDAO.updateDistributor(distributorDetailDTO);
	}

	public void deleteDistributor(DistributorDetailDTO distributorDetailDTO) {
		distributorDAO.deleteDistributor(distributorDetailDTO);
	}

	public List<DistributorDetailDTO> getDistributor(long did) {
		return distributorDAO.getDistributorDetails(did);
	}

	public int createParts(PartsDetailDTO partsDetailDTO) {
		return partsDAO.createParts(partsDetailDTO);
	}

	public void updateParts(PartsDetailDTO partsDetailDTO) {
		partsDAO.updateParts(partsDetailDTO);
	}

	public void deleteParts(PartsDetailDTO partsDetailDTO) {
		partsDAO.deleteParts(partsDetailDTO);
	}

	public List<PartsDetailDTO> getParts(long pid) {
		return partsDAO.getPartsDetails(pid);
	}

	public int createVendor(VendorDetailDTO vendorDetailDTO) {
		return vendorDAO.createVendor(vendorDetailDTO);
	}

	public void updateVendor(VendorDetailDTO vendorDetailDTO) {
		vendorDAO.updateVendor(vendorDetailDTO);
	}

	public void deleteVendor(VendorDetailDTO vendorDetailDTO) {
		vendorDAO.deleteVendor(vendorDetailDTO);
	}

	public List<VendorDetailDTO> getVendor(long vid) {
		return vendorDAO.getVendorDetails(vid);
	}

}
