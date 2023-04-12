package it.poste.generafile.configurations;

import java.util.List;

public class TabellaProdotti {

	private List<RigaConfProdotti> prodotti;

	public List<RigaConfProdotti> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<RigaConfProdotti> prodotti) {
		this.prodotti = prodotti;
	}
	
	
	public RigaConfProdotti getProdottoByCodiceAndAR(String codice, String ar) {
		return prodotti
				.stream()
				.filter(prodotto -> prodotto.getCodiceProdottoNPSO().equals(codice) && prodotto.getAr().equals(ar))
				.findFirst()
				.get();
				
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TabellaProdotti [prodotti=");
		builder.append(prodotti);
		builder.append("]");
		return builder.toString();
	}

}
