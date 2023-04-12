package it.poste.generafile.configurations;

public class RigaConfProdotti {

	private String descrizioneProdotto;
	private String prodottoSD;
	private String causaleProdottoTT;
	private String codiceProdottoNPSO;
	private String codiceFamigliaProdottoDU;
	private String vasFarm;
	private String vasProdotto;
	private String ar;
	private String internazionale;

	public String getDescrizioneProdotto() {
		return descrizioneProdotto;
	}

	public void setDescrizioneProdotto(String descrizioneProdotto) {
		this.descrizioneProdotto = descrizioneProdotto;
	}

	public String getProdottoSD() {
		return prodottoSD;
	}

	public void setProdottoSD(String prodottoSD) {
		this.prodottoSD = prodottoSD;
	}

	public String getCausaleProdottoTT() {
		return causaleProdottoTT;
	}

	public void setCausaleProdottoTT(String causaleProdottoTT) {
		this.causaleProdottoTT = causaleProdottoTT;
	}

	public String getCodiceProdottoNPSO() {
		return codiceProdottoNPSO;
	}

	public void setCodiceProdottoNPSO(String codiceProdottoNPSO) {
		this.codiceProdottoNPSO = codiceProdottoNPSO;
	}

	public String getCodiceFamigliaProdottoDU() {
		return codiceFamigliaProdottoDU;
	}

	public void setCodiceFamigliaProdottoDU(String codiceFamigliaProdottoDU) {
		this.codiceFamigliaProdottoDU = codiceFamigliaProdottoDU;
	}

	public String getVasFarm() {
		return vasFarm;
	}

	public void setVasFarm(String vasFarm) {
		this.vasFarm = vasFarm;
	}

	public String getVasProdotto() {
		return vasProdotto;
	}

	public void setVasProdotto(String vasProdotto) {
		this.vasProdotto = vasProdotto;
	}

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	public String getInternazionale() {
		return internazionale;
	}

	public void setInternazionale(String internazionale) {
		this.internazionale = internazionale;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RigaConfProdotti [descrizioneProdotto=");
		builder.append(descrizioneProdotto);
		builder.append(", prodottoSD=");
		builder.append(prodottoSD);
		builder.append(", causaleProdottoTT=");
		builder.append(causaleProdottoTT);
		builder.append(", codiceProdottoNPSO=");
		builder.append(codiceProdottoNPSO);
		builder.append(", codiceFamigliaProdottoDU=");
		builder.append(codiceFamigliaProdottoDU);
		builder.append(", vasFarm=");
		builder.append(vasFarm);
		builder.append(", vasProdotto=");
		builder.append(vasProdotto);
		builder.append(", ar=");
		builder.append(ar);
		builder.append(", internazionale=");
		builder.append(internazionale);
		builder.append("]");
		return builder.toString();
	}

}
