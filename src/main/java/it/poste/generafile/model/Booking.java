package it.poste.generafile.model;

public class Booking {

	private String dataGen;
	private String disName;
	private String bookingId;
	private String bookingSeq;
	private String acceptanceType;
	private String isRegistered;
	private String bookingDate;
	private String acceptanceServices;
	private String codeType;
	private String acceptanceDate;
	private String officeCode;
	private String officeName;
	private String itemNumber;
	private String printingCenter;
	private String bookingType;
	private String postage;
	private String idsName;
	private String itemNumberOk;
	private String itemNumberKo;
	private String bookingChild;
	private String postBags;
	private String description;

	public String getDataGen() {
		return dataGen;
	}

	public void setDataGen(String dataGen) {
		this.dataGen = dataGen;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingSeq() {
		return bookingSeq;
	}

	public void setBookingSeq(String bookingSeq) {
		this.bookingSeq = bookingSeq;
	}

	public String getAcceptanceType() {
		return acceptanceType;
	}

	public void setAcceptanceType(String acceptanceType) {
		this.acceptanceType = acceptanceType;
	}

	public String getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(String isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getAcceptanceServices() {
		return acceptanceServices;
	}

	public void setAcceptanceServices(String acceptanceServices) {
		this.acceptanceServices = acceptanceServices;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getPrintingCenter() {
		return printingCenter;
	}

	public void setPrintingCenter(String printingCenter) {
		this.printingCenter = printingCenter;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public String getIdsName() {
		return idsName;
	}

	public void setIdsName(String idsName) {
		this.idsName = idsName;
	}

	public String getItemNumberOk() {
		return itemNumberOk;
	}

	public void setItemNumberOk(String itemNumberOk) {
		this.itemNumberOk = itemNumberOk;
	}

	public String getItemNumberKo() {
		return itemNumberKo;
	}

	public void setItemNumberKo(String itemNumberKo) {
		this.itemNumberKo = itemNumberKo;
	}

	public String getBookingChild() {
		return bookingChild;
	}

	public void setBookingChild(String bookingChild) {
		this.bookingChild = bookingChild;
	}

	public String getPostBags() {
		return postBags;
	}

	public void setPostBags(String postBags) {
		this.postBags = postBags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Booking [dataGen=");
		builder.append(dataGen);
		builder.append(", disName=");
		builder.append(disName);
		builder.append(", bookingId=");
		builder.append(bookingId);
		builder.append(", bookingSeq=");
		builder.append(bookingSeq);
		builder.append(", acceptanceType=");
		builder.append(acceptanceType);
		builder.append(", isRegistered=");
		builder.append(isRegistered);
		builder.append(", bookingDate=");
		builder.append(bookingDate);
		builder.append(", acceptanceServices=");
		builder.append(acceptanceServices);
		builder.append(", codeType=");
		builder.append(codeType);
		builder.append(", acceptanceDate=");
		builder.append(acceptanceDate);
		builder.append(", officeCode=");
		builder.append(officeCode);
		builder.append(", officeName=");
		builder.append(officeName);
		builder.append(", itemNumber=");
		builder.append(itemNumber);
		builder.append(", printingCenter=");
		builder.append(printingCenter);
		builder.append(", bookingType=");
		builder.append(bookingType);
		builder.append(", postage=");
		builder.append(postage);
		builder.append(", idsName=");
		builder.append(idsName);
		builder.append(", itemNumberOk=");
		builder.append(itemNumberOk);
		builder.append(", itemNumberKo=");
		builder.append(itemNumberKo);
		builder.append(", bookingChild=");
		builder.append(bookingChild);
		builder.append(", postBags=");
		builder.append(postBags);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}