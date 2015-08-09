package main.java.com.jersey.dataaccess;

import org.hibernate.Transaction;

public class TransactionDetails {
         String txnStaus;
         String txnDesc;
         String txnErrorCode;
         String txnErrorDesc;
         Transaction txn;
         Integer generatedIdentifier;
		/**
		 * @return the txnStaus
		 */
		public String getTxnStaus() {
			return txnStaus;
		}
		/**
		 * @param txnStaus the txnStaus to set
		 */
		public void setTxnStaus(String txnStaus) {
			this.txnStaus = txnStaus;
		}
		/**
		 * @return the txnDesc
		 */
		public String getTxnDesc() {
			return txnDesc;
		}
		/**
		 * @param txnDesc the txnDesc to set
		 */
		public void setTxnDesc(String txnDesc) {
			this.txnDesc = txnDesc;
		}
		/**
		 * @return the txnErrorCode
		 */
		public String getTxnErrorCode() {
			return txnErrorCode;
		}
		/**
		 * @param txnErrorCode the txnErrorCode to set
		 */
		public void setTxnErrorCode(String txnErrorCode) {
			this.txnErrorCode = txnErrorCode;
		}
		/**
		 * @return the txnErrorDesc
		 */
		public String getTxnErrorDesc() {
			return txnErrorDesc;
		}
		/**
		 * @param txnErrorDesc the txnErrorDesc to set
		 */
		public void setTxnErrorDesc(String txnErrorDesc) {
			this.txnErrorDesc = txnErrorDesc;
		}
		/**
		 * @return the txn
		 */
		public Transaction getTxn() {
			return txn;
		}
		/**
		 * @param txn the txn to set
		 */
		public void setTxn(Transaction txn) {
			this.txn = txn;
		}
		/**
		 * @return the generatedIdentifier
		 */
		public Integer getGeneratedIdentifier() {
			return generatedIdentifier;
		}
		/**
		 * @param generatedIdentifier the generatedIdentifier to set
		 */
		public void setGeneratedIdentifier(Integer generatedIdentifier) {
			this.generatedIdentifier = generatedIdentifier;
		}
}
