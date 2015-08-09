package main.java.com.jersey.dataaccess;

import java.util.List;

import main.java.com.jersey.dataaccess.dao.CpAdvertisement;
import main.java.com.jersey.dataaccess.dao.SearchFilter;
import main.java.com.jersey.dataaccess.dao.UserProfile;
import main.java.com.jersey.presentation.CpAdFe;

public class AggregatedResultsDb {

	List<CpAdvertisement> cpAddList;
	TransactionDetails txnDetails;
	List<CpAdFe> cpAddResponseList;
	SearchFilter searchFilter;
	UserProfile userProfile;
	String exitCode;
	
	List<Object[]> queryResults;

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
	 * @return the queryResults
	 */
	public List<Object[]> getQueryResults() {
		return queryResults;
	}

	/**
	 * @param queryResults the queryResults to set
	 */
	public void setQueryResults(List<Object[]> queryResults) {
		this.queryResults = queryResults;
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
