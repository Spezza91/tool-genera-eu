package it.poste.generafile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.poste.generafile.configurations.RigaConfProdotti;
import it.poste.generafile.configurations.TabellaProdotti;
import it.poste.generafile.configurations.UserConfig;
import it.poste.generafile.exceptions.ValueErrorException;
import it.poste.generafile.exceptions.ValueNotFoundException;
import it.poste.generafile.kafka.model.PrenotazioneNpso;
import it.poste.generafile.model.Analytic;
import it.poste.generafile.model.BodyTag;
import it.poste.generafile.model.Booker;
import it.poste.generafile.model.Booking;
import it.poste.generafile.model.Contract;
import it.poste.generafile.model.Customer;
import it.poste.generafile.model.HeaderTag;
import it.poste.generafile.model.OrgUnit;
import it.poste.generafile.model.User;
import it.poste.utils.FileZipper;
import it.poste.utils.Utils;

public class FileBuilder {

	private UserConfig userConfig;

	private TabellaProdotti tabProdotti;

	private List<String> numeriRaccomandataConf;

	private Boolean readNumeriRaccomadantaFromConf;

	public FileBuilder() {
	}

	public void createFile() {

		try {

			/**
			 * Leggi conf
			 */
			readPropertiesFromFile();

			String newDirectory = createNewTestDirectory(userConfig.getBookingId(), userConfig.getProductCode(),
					userConfig.getItemNumber());

			// crea il file
			String fileName = writeFile(newDirectory);

			if (null != fileName) {
				String zipFileName = zipFile(fileName, newDirectory);
				
				if (null != zipFileName) {
				
				createFileFromString("1_LOAD_FILE.bat", newDirectory, "curl -vvv -H \"x-ms-blob-type: BlockBlob\" --upload-file " + zipFileName + " --url \""+ userConfig.getBlobStoragePath() + zipFileName + userConfig.getStorageConnectionInfo()+"\"");
				
				//mettere a configurazione URL e PATH per il blob storage
				
				String jsonString = createJsonStringFromConfiguration(zipFileName);
				
				String kafkaProducerCom =
						"@ECHO OFF\r\n" + 
						"java -jar " + System.getProperty("user.dir") +System.getProperty("file.separator")+ "kafka-producer-0.0.1-SNAPSHOT-jar-with-dependencies.jar \"kafka-pcl1.svil.kafka.aztestposte:9092,kafka-pcl1.svil.kafka.aztestposte:9092,kafka-pcl3.svil.kafka.aztestposte:9092\" \"NPSO.CORRISPONDENZA.ALL.PRENOTAZIONI\" "+jsonString+"\r\n" + 
						"pause";
				
				createFileFromString("2_RUN_KAFKA.bat", newDirectory, kafkaProducerCom);

				createFileFromString("3_RUN_TEST.bat", newDirectory, "1_LOAD_FILE.bat\r\n2_RUN_KAFKA.bat");
				
				}
			}


		} catch (ValueNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ValueErrorException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Errore duante la lettura/scrittura del file: ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Errore generico");
			e.printStackTrace();
		}

	}

	private String createJsonStringFromConfiguration(String fileName) throws JsonProcessingException {

		PrenotazioneNpso json = new PrenotazioneNpso();
		ObjectMapper mapper = new ObjectMapper();
		RigaConfProdotti prodottoByCodiceAndAR = tabProdotti.getProdottoByCodiceAndAR(userConfig.getProductCode(), userConfig.getHasAR());
		
		json.setBookingId(userConfig.getBookingId());
		json.setCanalePrenotazione("PSO");
		json.setCentroAccettazione("55333");
		json.setCodiceContratto(userConfig.getContractCode());
		json.setCodiceProdotto(userConfig.getProductCode());
		json.setCodiceSapCliente(userConfig.getSapSdCode());
		json.setCodiceServizioAccessorio(new ArrayList<>());
		json.setCodiceUnitaOrganizzativa("ST_FK00001");
		json.setCodiceUtenza("pino.operatoredue.coeco21");
		json.setDataInvio(userConfig.getAcceptanceDate());
		json.setFrazionarioAccettazione("55333");
		json.setNumeroPezzi(Integer.parseInt(userConfig.getItemNumber()));
		json.setPathExport("https://tpsopresa01azwe.blob.core.windows.net/ppsopreadvising/PPDTest/" + fileName);
		json.setProcesso("SMA");
		json.setProdotto(prodottoByCodiceAndAR.getCausaleProdottoTT());
		json.setProgressivo(1);
		json.setServizioAccessorio(new ArrayList<>());
		json.setStato("Preadvising Disponibile");
		json.setTipoElaborazione("DA STAMPATORE");
		json.setTipoLavorazione("Consolidamento distinta");
		json.setTotPallet(1);
		
		return mapper.writeValueAsString(json).replaceAll("\"", "\\\\\"");
	}

	private void createFileFromString(String fileName, String newDirectory, String fileContent) {

		try {

			String sysSeparator = System.getProperty("file.separator");

			File file = new File(newDirectory + sysSeparator + fileName);

			try (FileWriter writer = new FileWriter(file)) {
				writer.write(fileContent);
			} catch (Exception e) {
				System.out.println("errore durante la scrittura del file: " + fileName);
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("errore durante la creazione del file: " + fileName);
			e.printStackTrace();
		}

	}

	private String zipFile(String fileName, String directory) {

		try {

			String zipNameExt = fileName.replace(".ndjson", ".zip");

			String sysSeparator = System.getProperty("file.separator");

			File fileZip = new File(directory + sysSeparator + zipNameExt);

			File fileToZip = new File(directory + sysSeparator + fileName);

			FileZipper zipper = new FileZipper();

			if (zipper.zipFile(fileToZip, fileZip)) {
				System.out.println("è stato creato il file: " + zipNameExt);

			} else {
				System.out.println("si è verificato un errore durante la compressione del file: " + fileName);
			}

			fileToZip.delete();
			
			return zipNameExt;
		} catch (Exception e) {
			System.out.println("Si è verificato un errore durante la compressione del file");
			e.printStackTrace();
		}
		return null;
	}

	private String createNewTestDirectory(String bookingId, String productCode, String itemNumber) throws IOException {

		String sysSeparator = System.getProperty("file.separator");
		String dirPathName = System.getProperty("user.dir") + sysSeparator + "TEST_" + userConfig.getBookingId() + "_"
				+ userConfig.getProductCode() + "_" + userConfig.getItemNumber();

		File dir = new File(dirPathName);
		dir.mkdir();

		System.out.println("Directory " + dirPathName + " is created!");
		return dirPathName;

	}

	/**
	 * 
	 * @param directory: where the file will be created
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private String writeFile(String directory) throws IOException, ParseException {

		ObjectMapper mapper = new ObjectMapper();

		Integer fileLines = Integer.parseInt(userConfig.getItemNumber());

		if (readNumeriRaccomadantaFromConf) {
			fileLines = numeriRaccomandataConf.size();
		}

		// componi nome file
		String newFileName = createFileName();
		String sysSeparator = System.getProperty("file.separator");

		// crea file (in cartella \generated ?)

		System.out.println();
		System.out.println("Preparazione del nuovo file...");

		File fileRes = new File(directory + sysSeparator + newFileName);
		if (!fileRes.createNewFile()) {
			System.out.println("Non e' stato possibile creare il file, e' già presente un file con lo stesso nome?");
			return null;
		}

		FileOutputStream fos = new FileOutputStream(fileRes);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		// componi header in base alle conf

		RigaConfProdotti confProdotto = tabProdotti.getProdottoByCodiceAndAR(userConfig.getProductCode(),
				userConfig.getHasAR());

		HeaderTag header = createHeader(newFileName, confProdotto);
		bw.write(mapper.writeValueAsString(header));
		bw.newLine();

		// componi ogni singolo body in base alle conf e accoda al file

		// num racc 647886892003
		// itz RA000000001IT

		Long idItem = Long.parseLong(userConfig.getStartIdItem());
		Long codiceRitorno = Long.parseLong(userConfig.getStartCodiceRitorno());
		Long idSpare6 = 0l;

		String startCodiceInvio = userConfig.getStartCodiceInvio();
		String prefissoCodiceInvioCounter = "";
		String suffissoCodiceInvioCounter = "";
		String codiceInvioNumerico = "";
		Long codiceInvioCounter = Long.parseLong(startCodiceInvio.substring(2, 11));

		boolean formatoInternazionale = startCodiceInvio.matches("[A-Z]{2}[0-9]{9}[A-Z]{2}");

		if (formatoInternazionale) {
			prefissoCodiceInvioCounter = startCodiceInvio.substring(0, 2);
			codiceInvioCounter = Long.parseLong(startCodiceInvio.substring(2, 11));
			suffissoCodiceInvioCounter = startCodiceInvio.substring(11, 13);
		} else {
			codiceInvioCounter = Long.parseLong(startCodiceInvio);
		}

		SimpleDateFormat spare6DateFormatter = new SimpleDateFormat("yyyyMMdd_HHmm");
		String spare6Date = "IUN_" + spare6DateFormatter.format(new Date(System.currentTimeMillis())) + "_";

		for (int i = 0; i < fileLines; i++) {

			String codiceInvioCalcolato = "";

			if (readNumeriRaccomadantaFromConf) {
				codiceInvioCalcolato = numeriRaccomandataConf.get(i);
			} else {
				if (formatoInternazionale) {
					codiceInvioNumerico = Utils.paddingZero(9, codiceInvioCounter);
					codiceInvioCalcolato = prefissoCodiceInvioCounter + codiceInvioNumerico
							+ suffissoCodiceInvioCounter;
				} else {
					codiceInvioCalcolato = Utils.calculateItemWithCheckDigit("" + codiceInvioCounter);
				}
			}

			String codiceRitornoCalcolato = Utils.calculateItemWithCheckDigit("" + codiceRitorno);

			String idSpare6Calcolato = spare6Date + idSpare6;

			if (confProdotto.getAr().equals("N")) {
				codiceRitornoCalcolato = "";
			}

			BodyTag bodyTag = createBodyTag(idItem, codiceInvioCalcolato, codiceRitornoCalcolato, confProdotto,
					idSpare6Calcolato);

			bw.write(mapper.writeValueAsString(bodyTag));
			bw.newLine();

			idItem++;
			codiceInvioCounter++;
			codiceRitorno++;
			idSpare6++;
		}

		bw.close();

		return newFileName;

	}

	private BodyTag createBodyTag(Long idItem, String codiceInvio, String codiceRitorno, RigaConfProdotti confProdotto,
			String idSpare6Calcolato) {

		BodyTag body = new BodyTag();

		Analytic analytic = new Analytic();
		analytic.setAgencyCode("");
		analytic.setApplicant("");
		analytic.setAttorney("");
		analytic.setBailiff("");
		analytic.setBoxCode("");
		analytic.setCertificationCode("");
		analytic.setChronologicalNumber("");
		analytic.setCivicNumber("12A");
		analytic.setCodeDigitalFile("");
		analytic.setContentType("");
		analytic.setCountersign("");
		analytic.setCountry("");
		analytic.setCountryName("");
		analytic.setDelegateEmail("");
		analytic.setDelegateFiscalCode("");
		analytic.setDelegateMobilePhone("");
		analytic.setDelegateName("");
		analytic.setDelegatePhone("");
		analytic.setDestinationAddress("Via del Corso");
		analytic.setDestinationCap("00113");
		analytic.setDestinationCode("EU");
		analytic.setDestinationName("Marco Rossi");
		analytic.setDestinationProv("RM");
		analytic.setDigitalDelivery("N");
		analytic.setErrorCode("");
		analytic.setExtAddress("");
		analytic.setExtAddressDetail("");
		analytic.setExtAddressName("");
		analytic.setExtClientContractCode("");
		analytic.setExtClientIdUser("");
		analytic.setExtCustomerName("");
		analytic.setExtJobId("");
		analytic.setExtLottoUser("");
		analytic.setExtPrintExport("");
		analytic.setExtPrintingLine("");
		analytic.setExtPrintLineName("");
		analytic.setExtProgressiveUser("");
		analytic.setFee("");
		analytic.setFileNameSinn("");
		analytic.setFiscalCode("");
		analytic.setFlagDemat("");
		analytic.setHasAR(userConfig.getHasAR());
		analytic.setHuCode("1");
		analytic.setIdentityCardNumber("");
		analytic.setIdItem("PSO" + Utils.paddingZero(7, idItem));
		analytic.setInsuranceValue("");
		analytic.setInvoice("");
		analytic.setInvoiceExpirationDate("");
		analytic.setIsPeak("");
		analytic.setIsRegistered("Y");
		analytic.setLanguageCode("");
		analytic.setLocality("Roma");
		analytic.setLottoCode("");
		analytic.setObject1Coo("");
		analytic.setObject1Currency("");
		analytic.setObject1CustomsCode("");
		analytic.setObject1Unit("");
		analytic.setObject1Value("");
		analytic.setObject1Weight("");
		analytic.setObject2Coo("");
		analytic.setObject2Currency("");
		analytic.setObject2CustomsCode("");
		analytic.setObject2Unit("");
		analytic.setObject2Value("");
		analytic.setObject2Weight("");
		analytic.setObject3Coo("");
		analytic.setObject3Currency("");
		analytic.setObject3CustomsCode("");
		analytic.setObject3Unit("");
		analytic.setObject3Value("");
		analytic.setObject3Weight("");
		analytic.setObject4Coo("");
		analytic.setObject4Currency("");
		analytic.setObject4CustomsCode("");
		analytic.setObject4Unit("");
		analytic.setObject4Value("");
		analytic.setObject4Weight("");
		analytic.setOutcome("OK");
		analytic.setOwnerEmail("");
		analytic.setOwnerFiscalCode("");
		analytic.setOwnerMobilePhone("");
		analytic.setOwnerName("");
		analytic.setOwnerPhone("");
		analytic.setPeakActive("N");
		analytic.setPec("");
		analytic.setPrintServiceDate("");
		analytic.setProcessSpare1("");
		analytic.setProcessSpare10("");
		analytic.setProcessSpare2("");
		analytic.setProcessSpare3("");
		analytic.setProcessSpare4("");
		analytic.setProcessSpare5("");
		analytic.setProcessSpare6("");
		analytic.setProcessSpare7("");
		analytic.setProcessSpare8("");
		analytic.setProcessSpare9("");
		analytic.setProductCode(userConfig.getProductCode());
		analytic.setProductSdCode(confProdotto.getProdottoSD());
		analytic.setQuantityEnvelopesBox("");
		analytic.setReturnCode(codiceRitorno);
		analytic.setSecondReceiverEmail("");
		analytic.setSecondReceiverFiscalCode("");
		analytic.setSecondReceiverMobilePhone("");
		analytic.setSecondReceiverName("");
		analytic.setSecondReceiverPhone("");
		analytic.setSendCode(codiceInvio);
		analytic.setSenderAddress("");
		analytic.setSenderCivicNumber("");
		analytic.setSenderCountry("");
		analytic.setSenderData("");
		analytic.setSenderEmail("");
		analytic.setSenderFiscalCode("");
		analytic.setSenderLocality("");
		analytic.setSenderName("");
		analytic.setSenderOffice("");
		analytic.setSenderPhone("");
		analytic.setSenderProvince("");
		analytic.setSenderVat("");
		analytic.setSenderZip("");
		analytic.setSendServiceDate("");
		analytic.setSize("Standard");
		analytic.setSpare1("");
		analytic.setSpare10("");
		analytic.setSpare2("");
		analytic.setSpare3("");
		analytic.setSpare4("");
		analytic.setSpare5("");
		analytic.setSpare6(idSpare6Calcolato);
		analytic.setSpare7(idSpare6Calcolato + "_REQUEST");
		analytic.setSpare8("");
		analytic.setSpare9("");
		analytic.setTelephone("");
		analytic.setToolResponseState("");
		analytic.setUnitFee("");
		analytic.setVasFam(confProdotto.getVasFarm());
		analytic.setVasProd(confProdotto.getVasProdotto());
		analytic.setVat("");
		analytic.setWeight("");

		body.setAnalytic(analytic);

		return body;
	}

	private HeaderTag createHeader(String fileName, RigaConfProdotti confProdotto) {

		HeaderTag header = new HeaderTag();

		Booker booker = new Booker();
		booker.setFiscalCode("00113430573");
		booker.setName("Postel");
		booker.setPostecomCode("547.211.622");
		booker.setSapsdCode("1234567890");
		booker.setVat("00113430573");

		Booking booking = new Booking();
		booking.setAcceptanceDate(userConfig.getAcceptanceDate());
		booking.setAcceptanceServices(confProdotto.getVasProdotto());
		booking.setAcceptanceType("Analitica");
		booking.setBookingChild("");
		booking.setBookingDate(userConfig.getAcceptanceDate());
		booking.setBookingId(userConfig.getBookingId());
		booking.setBookingSeq("1");
		booking.setBookingType("DA AUTOPRODOTTO");
		booking.setCodeType("L");
		booking.setDataGen(userConfig.getAcceptanceDate());
		booking.setDescription("");
		booking.setDisName(fileName);
		booking.setIdsName("");
		booking.setIsRegistered("Y");
		booking.setItemNumber(userConfig.getItemNumber());
		booking.setItemNumberKo("0");
		booking.setItemNumberOk(userConfig.getItemNumber());
		booking.setOfficeCode("55333");
		booking.setOfficeName("Milano CSI");
		booking.setPostage("SMA");
		booking.setPostBags("");
		booking.setPrintingCenter("");

		Contract contract = new Contract();
		contract.setBillingType("Fatturazione su accettato");
		contract.setContractCode(userConfig.getContractCode());
		contract.setProviderContract(userConfig.getContractCode());

		Customer customer = new Customer();
		customer.setFiscalCode("");
		customer.setName("");
		customer.setOrgUnitActive("N");
		customer.setPostecomCode("547.211.622");
		customer.setSapsdCode(userConfig.getSapSdCode());
		customer.setVat("");

		OrgUnit orgUnit = new OrgUnit();
		orgUnit.setName("");
		orgUnit.setSapsdCode("");

		User user = new User();
		user.setCode("user.test.mn");
		user.setEmail("m.piscitelli@posteitaliane.it");

		header.setBooker(booker);
		header.setBooking(booking);
		header.setContract(contract);
		header.setCustomer(customer);
		header.setOrgUnit(orgUnit);
		header.setUser(user);

		return header;
	}

	/*
	 * ese: ExportUnico_2050008_01_20230309153729.ndjson
	 * 
	 */
	private String createFileName() throws ParseException {

		SimpleDateFormat formatterConfig = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat formatterFileName = new SimpleDateFormat("yyyyMMddHHmmss");

		String myDate = userConfig.getAcceptanceDate();
		Date date = formatterConfig.parse(myDate);

		String formattedDate = formatterFileName.format(date);

		String newFileName = "ExportUnico_" + userConfig.getBookingId() + "_01_" + formattedDate + ".ndjson";

		return newFileName;
	}

	/**
	 * Lettura e check dei valori dal file di configurazione
	 * 
	 * @throws Exception
	 */
	private void readPropertiesFromFile() throws Exception {
		PropertiesReader propertiesReader = new PropertiesReader();

		tabProdotti = propertiesReader.loadTabellaProdotti("conf/tabellaProdotti.csv");
		userConfig = propertiesReader.loadUserConfig("conf/config.properties");

		readNumeriRaccomadantaFromConf = Boolean.parseBoolean(userConfig.getUsaFilePerNumeriRaccomandata());

		if (readNumeriRaccomadantaFromConf) {
			numeriRaccomandataConf = propertiesReader.readNumeriRaccomandataFromConf("conf/numeriRaccomandata.conf");
		}

		System.out.println("CONFIGURAZIONE: ");
		System.out.println(userConfig.toString());

	}

}
