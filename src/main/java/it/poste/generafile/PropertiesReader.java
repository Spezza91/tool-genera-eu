
package it.poste.generafile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import it.poste.generafile.configurations.RigaConfProdotti;
import it.poste.generafile.configurations.TabellaProdotti;
import it.poste.generafile.configurations.UserConfig;
import it.poste.generafile.exceptions.ValueErrorException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class PropertiesReader {

	public Properties readProperties(String pathFile) throws IOException {

		Properties prop = new Properties();

		/* non per test in locale! */
		try (InputStream input = new FileInputStream(pathFile)) {

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {

			System.out.println("caricamento configurazioni non riuscito!");
			ex.printStackTrace();
			throw ex;
		}

		return prop;
	}

	public Properties readPropertiesCsv(String pathFile) throws Exception {
		
		Properties prop = new Properties();

		/* non per test in locale! */
		try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
			
			//skip header
			reader.readLine();
			
			String confLine = reader.readLine();
			
			if(confLine != null) {
				
				String[] splitConf = confLine.split(Pattern.quote("|"));
				
				prop.setProperty(UserConfig.BOOKING_ID, splitConf[0]);
				prop.setProperty(UserConfig.ACCEPTANCE_DATE, splitConf[1]);
				prop.setProperty(UserConfig.ITEM_NUMBER, splitConf[2]);
				prop.setProperty(UserConfig.SAP_SD_CODE, splitConf[3]);
				prop.setProperty(UserConfig.CONTRACT_CODE, splitConf[4]);
				prop.setProperty(UserConfig.START_CODICE_INVIO, splitConf[5]);
				prop.setProperty(UserConfig.START_CODICE_RITORNO, splitConf[6]);
				prop.setProperty(UserConfig.START_ID_ITEM, splitConf[7]);
				prop.setProperty(UserConfig.PRODUCT_CODE, splitConf[8]);
				prop.setProperty(UserConfig.HAS_AR, splitConf[9]);
				prop.setProperty(UserConfig.READ_RACC_FROM_FILE, splitConf[10]);
				prop.setProperty(UserConfig.BLOB_STORAGE_PATH, splitConf[11]);
				prop.setProperty(UserConfig.STORAGE_CONNECTION_INFO, splitConf[12]);
				
			}

		} catch (Exception ex) {

			System.out.println("caricamento configurazioni non riuscito!");
			ex.printStackTrace();
			throw ex;
		}

		return prop;
	}

	/*
	 * i file csv saranno presenti sotto la cartella '/conf'
	 */

	public TabellaProdotti loadTabellaProdotti(String pathFile) throws FileNotFoundException, IOException {

		TabellaProdotti tabellaProdotti = new TabellaProdotti();
		List<RigaConfProdotti> listaProdotti = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {

			String line = reader.readLine();

			while (line != null) {

				String[] split = line.split(Pattern.quote("|"));

				RigaConfProdotti prodotto = new RigaConfProdotti();

				prodotto.setDescrizioneProdotto(split[0]);
				prodotto.setProdottoSD(split[1]);
				prodotto.setCausaleProdottoTT(split[2]);
				prodotto.setCodiceProdottoNPSO(split[3]);
				prodotto.setCodiceFamigliaProdottoDU(split[4]);
				prodotto.setVasFarm(split[5]);
				prodotto.setVasProdotto(split[6]);
				prodotto.setAr(split[7]);
				prodotto.setInternazionale(split[8]);

				listaProdotti.add(prodotto);

				// read next line
				line = reader.readLine();
			}

			tabellaProdotti.setProdotti(listaProdotti);
		}

		return tabellaProdotti;
	}

	public UserConfig loadUserConfig(String pathFile) throws Exception {

		UserConfig userConfig = new UserConfig();
		Properties properties = this.readProperties(pathFile);

		userConfig.setReadFromCSV(properties.getProperty(UserConfig.READ_FROM_CSV));
		
		if("true".equals(userConfig.getReadFromCSV())) {
			System.out.println("reading properties from [conf/config.csv]...");
			properties = this.readPropertiesCsv("conf/config.csv");
		}
		
		userConfig.setAcceptanceDate(properties.getProperty(UserConfig.ACCEPTANCE_DATE));
		userConfig.setBookingId(properties.getProperty(UserConfig.BOOKING_ID));
		userConfig.setContractCode(properties.getProperty(UserConfig.CONTRACT_CODE));
		userConfig.setHasAR(properties.getProperty(UserConfig.HAS_AR));
		userConfig.setItemNumber(properties.getProperty(UserConfig.ITEM_NUMBER));
		userConfig.setProductCode(properties.getProperty(UserConfig.PRODUCT_CODE));
		userConfig.setSapSdCode(properties.getProperty(UserConfig.SAP_SD_CODE));
		userConfig.setStartCodiceInvio(properties.getProperty(UserConfig.START_CODICE_INVIO));
		userConfig.setStartCodiceRitorno(properties.getProperty(UserConfig.START_CODICE_RITORNO));
		userConfig.setStartIdItem(properties.getProperty(UserConfig.START_ID_ITEM));
		userConfig.setUsaFilePerNumeriRaccomandata(properties.getProperty(UserConfig.READ_RACC_FROM_FILE));
		userConfig.setBlobStoragePath(properties.getProperty(UserConfig.BLOB_STORAGE_PATH));
		userConfig.setStorageConnectionInfo(properties.getProperty(UserConfig.STORAGE_CONNECTION_INFO));

		validateUserConfig(userConfig);

		return userConfig;
	}

	private void validateUserConfig(UserConfig config) {

		final Validator validator = new Validator();
		List<ConstraintViolation> violations;

		violations = validator.validate(config);

		if (violations.size() > 0) {
			System.out.println("");
			violations.forEach(violation -> {
				System.out.println(violation.getMessage());
			});
		}
	}

	public List<String> readNumeriRaccomandataFromConf(String filePath) throws FileNotFoundException, IOException {

		List<String> raccList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = null;
			while ((line = reader.readLine()) != null) {

				if (line.trim().startsWith("#")) {
					continue;
				}

				// check!
				if (line.length() < 12 || line.length() > 13) {
					throw new ValueErrorException(
							"Errore nella lista dei numeri raccomandata, valore [" + line + "] non valido");
				}

				raccList.add(line);

			}
		}

		return raccList;
	}

}
