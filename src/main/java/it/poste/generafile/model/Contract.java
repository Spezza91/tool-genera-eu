package it.poste.generafile.model;

public class Contract {
	private String contractCode;
	private String providerContract;
	private String billingType;

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getProviderContract() {
		return providerContract;
	}

	public void setProviderContract(String providerContract) {
		this.providerContract = providerContract;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contract [contractCode=");
		builder.append(contractCode);
		builder.append(", providerContract=");
		builder.append(providerContract);
		builder.append(", billingType=");
		builder.append(billingType);
		builder.append("]");
		return builder.toString();
	}

}
