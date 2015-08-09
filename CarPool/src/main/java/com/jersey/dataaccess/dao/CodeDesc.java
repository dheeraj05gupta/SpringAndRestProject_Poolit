package main.java.com.jersey.dataaccess.dao;

public class CodeDesc {
	private String code;
	private String desc;

	public CodeDesc(){
		super();
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	 /*   @Override
	  public boolean equals(Object codeDesc) {
	        boolean retVal = false;

	        if (codeDesc instanceof CodeDesc){
	        	if(null != codeDesc){
	            CodeDesc cd = (CodeDesc) codeDesc;
	            retVal =  (null != cd.code)?cd.code.equals(this.code):false;
	        	}
	        }

	     return retVal;
	  }

	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 17 * hash + (this.code != null ? this.code.hashCode() : 0);
	        return hash;
	    }*/
}
