package it.poste.generafile.model;

public class Customer {

	private String sapsdCode;
	private String name;
	private String fiscalCode;
	private String vat;
	private String postecomCode;
	private String orgUnitActive;

	public String getSapsdCode() {
		return sapsdCode;
	}

	public void setSapsdCode(String sapsdCode) {
		this.sapsdCode = sapsdCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getPostecomCode() {
		return postecomCode;
	}

	public void setPostecomCode(String postecomCode) {
		this.postecomCode = postecomCode;
	}

	public String getOrgUnitActive() {
		return orgUnitActive;
	}

	public void setOrgUnitActive(String orgUnitActive) {
		this.orgUnitActive = orgUnitActive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [sapsdCode=");
		builder.append(sapsdCode);
		builder.append(", name=");
		builder.append(name);
		builder.append(", fiscalCode=");
		builder.append(fiscalCode);
		builder.append(", vat=");
		builder.append(vat);
		builder.append(", postecomCode=");
		builder.append(postecomCode);
		builder.append(", orgUnitActive=");
		builder.append(orgUnitActive);
		builder.append("]");
		return builder.toString();
	}

}
