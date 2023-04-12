package it.poste.generafile.kafka.model;

import java.util.ArrayList;

public class PrenotazioneNpso {

	private String bookingId;
	private String centroAccettazione;
	private String prodotto;
	private ArrayList<String> servizioAccessorio;
	private String stato;
	private Integer totPallet;
	private String dataInvio;
	private Integer numeroPezzi;
	private String processo;
	private String tipoElaborazione;
	private String tipoLavorazione;
	private String codiceContratto;
	private Integer progressivo;
	private String canalePrenotazione;
	private String codiceUtenza;
	private String codiceUnitaOrganizzativa;
	private String pathExport;
	private String codiceSapCliente;
	private String frazionarioAccettazione;
	private ArrayList<String> codiceServizioAccessorio;
	private String codiceProdotto;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getCentroAccettazione() {
		return centroAccettazione;
	}

	public void setCentroAccettazione(String centroAccettazione) {
		this.centroAccettazione = centroAccettazione;
	}

	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public ArrayList<String> getServizioAccessorio() {
		return servizioAccessorio;
	}

	public void setServizioAccessorio(ArrayList<String> servizioAccessorio) {
		this.servizioAccessorio = servizioAccessorio;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Integer getTotPallet() {
		return totPallet;
	}

	public void setTotPallet(Integer totPallet) {
		this.totPallet = totPallet;
	}

	public String getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(String dataInvio) {
		this.dataInvio = dataInvio;
	}

	public Integer getNumeroPezzi() {
		return numeroPezzi;
	}

	public void setNumeroPezzi(Integer numeroPezzi) {
		this.numeroPezzi = numeroPezzi;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getTipoElaborazione() {
		return tipoElaborazione;
	}

	public void setTipoElaborazione(String tipoElaborazione) {
		this.tipoElaborazione = tipoElaborazione;
	}

	public String getTipoLavorazione() {
		return tipoLavorazione;
	}

	public void setTipoLavorazione(String tipoLavorazione) {
		this.tipoLavorazione = tipoLavorazione;
	}

	public String getCodiceContratto() {
		return codiceContratto;
	}

	public void setCodiceContratto(String codiceContratto) {
		this.codiceContratto = codiceContratto;
	}

	public Integer getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	public String getCanalePrenotazione() {
		return canalePrenotazione;
	}

	public void setCanalePrenotazione(String canalePrenotazione) {
		this.canalePrenotazione = canalePrenotazione;
	}

	public String getCodiceUtenza() {
		return codiceUtenza;
	}

	public void setCodiceUtenza(String codiceUtenza) {
		this.codiceUtenza = codiceUtenza;
	}

	public String getCodiceUnitaOrganizzativa() {
		return codiceUnitaOrganizzativa;
	}

	public void setCodiceUnitaOrganizzativa(String codiceUnitaOrganizzativa) {
		this.codiceUnitaOrganizzativa = codiceUnitaOrganizzativa;
	}

	public String getPathExport() {
		return pathExport;
	}

	public void setPathExport(String pathExport) {
		this.pathExport = pathExport;
	}

	public String getCodiceSapCliente() {
		return codiceSapCliente;
	}

	public void setCodiceSapCliente(String codiceSapCliente) {
		this.codiceSapCliente = codiceSapCliente;
	}

	public String getFrazionarioAccettazione() {
		return frazionarioAccettazione;
	}

	public void setFrazionarioAccettazione(String frazionarioAccettazione) {
		this.frazionarioAccettazione = frazionarioAccettazione;
	}

	public ArrayList<String> getCodiceServizioAccessorio() {
		return codiceServizioAccessorio;
	}

	public void setCodiceServizioAccessorio(ArrayList<String> codiceServizioAccessorio) {
		this.codiceServizioAccessorio = codiceServizioAccessorio;
	}

	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrenotazioneNpso [bookingId=");
		builder.append(bookingId);
		builder.append(", centroAccettazione=");
		builder.append(centroAccettazione);
		builder.append(", prodotto=");
		builder.append(prodotto);
		builder.append(", servizioAccessorio=");
		builder.append(servizioAccessorio);
		builder.append(", stato=");
		builder.append(stato);
		builder.append(", totPallet=");
		builder.append(totPallet);
		builder.append(", dataInvio=");
		builder.append(dataInvio);
		builder.append(", numeroPezzi=");
		builder.append(numeroPezzi);
		builder.append(", processo=");
		builder.append(processo);
		builder.append(", tipoElaborazione=");
		builder.append(tipoElaborazione);
		builder.append(", tipoLavorazione=");
		builder.append(tipoLavorazione);
		builder.append(", codiceContratto=");
		builder.append(codiceContratto);
		builder.append(", progressivo=");
		builder.append(progressivo);
		builder.append(", canalePrenotazione=");
		builder.append(canalePrenotazione);
		builder.append(", codiceUtenza=");
		builder.append(codiceUtenza);
		builder.append(", codiceUnitaOrganizzativa=");
		builder.append(codiceUnitaOrganizzativa);
		builder.append(", pathExport=");
		builder.append(pathExport);
		builder.append(", codiceSapCliente=");
		builder.append(codiceSapCliente);
		builder.append(", frazionarioAccettazione=");
		builder.append(frazionarioAccettazione);
		builder.append(", codiceServizioAccessorio=");
		builder.append(codiceServizioAccessorio);
		builder.append(", codiceProdotto=");
		builder.append(codiceProdotto);
		builder.append("]");
		return builder.toString();
	}

}
