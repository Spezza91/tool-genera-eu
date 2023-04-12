package it.poste.generafile.model;

public class OrgUnit {

	private String sapsdCode;
	private String name;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrgUnit [sapsdCode=");
		builder.append(sapsdCode);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
