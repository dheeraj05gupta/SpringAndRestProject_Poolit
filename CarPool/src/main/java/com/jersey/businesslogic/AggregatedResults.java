package main.java.com.jersey.businesslogic;

import java.util.List;

import main.java.com.jersey.dataaccess.TransactionDetails;
import main.java.com.jersey.dataaccess.dao.CpAdvertisement;
import main.java.com.jersey.dataaccess.dao.SearchFilter;
import main.java.com.jersey.dataaccess.dao.UserProfile;
import main.java.com.jersey.presentation.CpAdFe;
import main.java.com.jersey.presentation.UserContactDetails;

import org.springframework.stereotype.Component;

@Component
public class AggregatedResults {

	List<CpAdvertisement> cpAddList;
	List<CpAdFe> cpAddResponseList;
	TransactionDetails txnDetails;
	SearchFilter searchFilter;
	UserProfile userProfile;
	String exitCode;
	UserContactDetails userContactDetils;
	
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
	 * @return the txnDetails
	 */
	public TransactionDetails getTxnDetails() {
		return txnDetails;
	}

	/**
	 * @param txnDetails the txnDetails to set
	 */
	public void setTxnDetails(TransactionDetails txnDetails) {
		this.txnDetails = txnDetails;
	}

	/**
	 * @return the cpAddList
	 */
	public List<CpAdvertisement> getCpAddList() {
		return cpAddList;
	}

	/**
	 * @param cpAddList the cpAddList to set
	 */
	public void setCpAddList(List<CpAdvertisement> cpAddList) {
		this.cpAddList = cpAddList;
	}

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
	 * @return the exitCode
	 */
	public String getExitCode() {
		return exitCode;
	}

	/**
	 * @param exitCode the exitCode to set
	 */
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

	

	/**
	 * @return the userContactDetils
	 */
	public UserContactDetails getUserContactDetils() {
		return userContactDetils;
	}

	/**
	 * @param userContactDetils the userContactDetils to set
	 */
	public void setUserContactDetils(UserContactDetails userContactDetils) {
		this.userContactDetils = userContactDetils;
	}

	/**
	 * @return the cpAddResponseList
	 */
	public List<CpAdFe> getCpAddResponseList() {
		return cpAddResponseList;
	}

	/**
	 * @param cpAddResponseList the cpAddResponseList to set
	 */
	public void setCpAddResponseList(List<CpAdFe> cpAddResponseList) {
		this.cpAddResponseList = cpAddResponseList;
	}
	
}
