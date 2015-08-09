package main.java.com.jersey.businesslogic;

import java.util.List;

import main.java.com.jersey.dataaccess.dao.CpAdvertisement;
import main.java.com.jersey.dataaccess.dao.SearchFilter;
import main.java.com.jersey.dataaccess.dao.UserProfile;
import main.java.com.jersey.presentation.CpAdFe;

public class AggregatedRequest {

	private SearchFilter searchFilter;
	private UserProfile userProfile;
	List<CpAdFe> cpAddList;
	List<CpAdvertisement> cpAddBe;

	/**
	 * @return the searchFilter
	 */
	public SearchFilter getSearchFilter() {
		return searchFilter;
	}
	/**
	 * @param searchFilter the searchFilter to set
	 */
	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}
	/**
	 * @return the userProfile
	 */
	public UserProfile getUserProfile() {
		return userProfile;
	}
	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	/**
	 * @return the cpAddList
	 */
	public List<CpAdFe> getCpAddList() {
		return cpAddList;
	}
	/**
	 * @param cpAddList the cpAddList to set
	 */
	public void setCpAddList(List<CpAdFe> cpAddList) {
		this.cpAddList = cpAddList;
	}
	/**
	 * @return the cpAddBe
	 */
	public List<CpAdvertisement> getCpAddBe() {
		return cpAddBe;
	}
	/**
	 * @param cpAddBe the cpAddBe to set
	 */
	public void setCpAddBe(List<CpAdvertisement> cpAddBe) {
		this.cpAddBe = cpAddBe;
	}
	

}
