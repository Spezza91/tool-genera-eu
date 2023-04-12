package it.poste.generafile.configurations;

import java.util.Properties;

import it.poste.generafile.configurations.checks.DateFormatOval;
import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 * Configurazioni utente
 * 
 * @author david
 *
 */
public class UserConfig {

	public final static String BOOKING_ID = "bookingId";
	public final static String ACCEPTANCE_DATE = "acceptanceDate";
	public final static String ITEM_NUMBER = "itemNumber";
	public final static String SAP_SD_CODE = "sapSdCode";
	public final static String CONTRACT_CODE = "contractCode";
	public final static String START_CODICE_INVIO = "startCodiceInvio";
	public final static String START_CODICE_RITORNO = "startCodiceRitorno";
	public final static String START_ID_ITEM = "startIdItem";
	public final static String PRODUCT_CODE = "productCode";
	public final static String HAS_AR = "hasAR";
	public final static String READ_RACC_FROM_FILE = "usaFilePerNumeriRaccomandata";
	public final static String READ_FROM_CSV = "readFromCSV";
	public final static String BLOB_STORAGE_PATH = "blobStoragePath";
	public final static String STORAGE_CONNECTION_INFO = "storageConnectionInfo";

	private Properties properties;

	@NotNull(message = "Errore di configurazione, bookingId non valorizzato")
	@NotEmpty(message = "Errore di configurazione, bookingId non valorizzato")
	@Length(message = "Errore di configurazione, bookingId non valorizzato correttamente, deve essere di 7 cifre!", max = 7, min = 7)
	private String bookingId;

	@NotNull(message = "Errore di configurazione, acceptanceDate non valorizzato")
	@NotEmpty(message = "Errore di configurazione, acceptanceDate non valorizzato")
	@DateFormatOval(message = "Errore di configurazione, acceptanceDate non valorizzata correttamente")
	private String acceptanceDate;

	@NotNull(message = "Errore di configurazione, itemNumber non valorizzato")
	@NotEmpty(message = "Errore di configurazione, itemNumber non valorizzato")
	@Digits(message = "Errore di configurazione, itemNumber non valorizzato correttamente")
	@NotNegative(message = "Errore di configurazione, itemNumber non valorizzato correttamente, deve essere maggiore di 0")
	private String itemNumber;

	@NotNull(message = "Errore di configurazione, sapSdCode non valorizzato")
	@NotEmpty(message = "Errore di configurazione, sapSdCode non valorizzato")
	private String sapSdCode;

	@NotNull(message = "Errore di configurazione, contractCode non valorizzato")
	@NotEmpty(message = "Errore di configurazione, contractCode non valorizzato")
	private String contractCode;

	@NotNull(message = "Errore di configurazione, startCodiceInvio non valorizzato")
	@NotEmpty(message = "Errore di configurazione, startCodiceInvio non valorizzato")
	private String startCodiceInvio;

	@NotNull(message = "Errore di configurazione, startCodiceRitorno non valorizzato")
	@NotEmpty(message = "Errore di configurazione, startCodiceRitorno non valorizzato")
	private String startCodiceRitorno;

	@NotNull(message = "Errore di configurazione, startIdItem non valorizzato")
	@NotEmpty(message = "Errore di configurazione, startIdItem non valorizzato")
	@Digits(message = "Errore di configurazione, startIdItem non valorizzato correttamente")
	@NotNegative(message = "Errore di configurazione, startIdItem non valorizzato correttamente, deve essere maggiore di 0")
	private String startIdItem;

	@NotNull(message = "Errore di configurazione, productCode non valorizzato")
	@NotEmpty(message = "Errore di configurazione, productCode non valorizzato")
	private String productCode;

	@NotNull(message = "Errore di configurazione, hasAR non valorizzato")
	@NotEmpty(message = "Errore di configurazione, hasAR non valorizzato")
	@MatchPattern(pattern = "Y|N", message = "Errore di configurazione, hasAR non valorizzato correttamente, valori ammessi: Y/N")
	private String hasAR;

	@NotNull(message = "Errore di configurazione, usaFilePerNumeriRaccomandata non valorizzato")
	@NotEmpty(message = "Errore di configurazione, usaFilePerNumeriRaccomandata non valorizzato")
	@MatchPattern(pattern = "true|false", message = "Errore di configurazione, usaFilePerNumeriRaccomandata non valorizzato, valori ammessi 'true/false'")
	private String usaFilePerNumeriRaccomandata;

	@NotNull(message = "Errore di configurazione, readFromCSV non valorizzato")
	@NotEmpty(message = "Errore di configurazione, readFromCSV non valorizzato")
	@MatchPattern(pattern = "true|false", message = "Errore di configurazione, readFromCSV non valorizzato, valori ammessi 'true/false'")
	private String readFromCSV;

	@NotNull(message = "Errore di configurazione, blobStoragePath non valorizzato")
	@NotEmpty(message = "Errore di configurazione, blobStoragePath non valorizzato")
	private String blobStoragePath;

	@NotNull(message = "Errore di configurazione, storageConnectionInfo non valorizzato")
	@NotEmpty(message = "Errore di configurazione, storageConnectionInfo non valorizzato")
	private String storageConnectionInfo;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getSapSdCode() {
		return sapSdCode;
	}

	public void setSapSdCode(String sapsdCode) {
		this.sapSdCode = sapsdCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getStartCodiceInvio() {
		return startCodiceInvio;
	}

	public void setStartCodiceInvio(String startCodiceInvio) {
		this.startCodiceInvio = startCodiceInvio;
	}

	public String getStartCodiceRitorno() {
		return startCodiceRitorno;
	}

	public void setStartCodiceRitorno(String startCodiceRitorno) {
		this.startCodiceRitorno = startCodiceRitorno;
	}

	public String getStartIdItem() {
		return startIdItem;
	}

	public void setStartIdItem(String startIdItem) {
		this.startIdItem = startIdItem;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getHasAR() {
		return hasAR;
	}

	public void setHasAR(String hasAR) {
		this.hasAR = hasAR;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getUsaFilePerNumeriRaccomandata() {
		return usaFilePerNumeriRaccomandata;
	}

	public void setUsaFilePerNumeriRaccomandata(String usaFilePerNumeriRaccomandata) {
		this.usaFilePerNumeriRaccomandata = usaFilePerNumeriRaccomandata;
	}

	public String getReadFromCSV() {
		return readFromCSV;
	}

	public void setReadFromCSV(String readFromCSV) {
		this.readFromCSV = readFromCSV;
	}

	public String getBlobStoragePath() {
		return blobStoragePath;
	}

	public void setBlobStoragePath(String blobStoragePath) {
		this.blobStoragePath = blobStoragePath;
	}

	public String getStorageConnectionInfo() {
		return storageConnectionInfo;
	}

	public void setStorageConnectionInfo(String storageConnectionInfo) {
		this.storageConnectionInfo = storageConnectionInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserConfig [properties=");
		builder.append(properties);
		builder.append(", bookingId=");
		builder.append(bookingId);
		builder.append(", acceptanceDate=");
		builder.append(acceptanceDate);
		builder.append(", itemNumber=");
		builder.append(itemNumber);
		builder.append(", sapSdCode=");
		builder.append(sapSdCode);
		builder.append(", contractCode=");
		builder.append(contractCode);
		builder.append(", startCodiceInvio=");
		builder.append(startCodiceInvio);
		builder.append(", startCodiceRitorno=");
		builder.append(startCodiceRitorno);
		builder.append(", startIdItem=");
		builder.append(startIdItem);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append(", hasAR=");
		builder.append(hasAR);
		builder.append(", usaFilePerNumeriRaccomandata=");
		builder.append(usaFilePerNumeriRaccomandata);
		builder.append(", readFromCSV=");
		builder.append(readFromCSV);
		builder.append(", blobStoragePath=");
		builder.append(blobStoragePath);
		builder.append(", storageConnectionInfo=");
		builder.append(storageConnectionInfo);
		builder.append("]");
		return builder.toString();
	}

}
