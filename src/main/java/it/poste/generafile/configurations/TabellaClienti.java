package it.poste.generafile.configurations;

import java.util.List;

public class TabellaClienti {

	private List<RigaConfClienti> clienti;

	public List<RigaConfClienti> getClienti() {
		return clienti;
	}

	public void setClienti(List<RigaConfClienti> clienti) {
		this.clienti = clienti;
	}
	
	
//	public RigaConfClienti getByContratto(String contratto) {
//		return clienti
//				.stream()
//				.filter(cliente -> cliente.get)
//	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TabellaClienti [clienti=");
		builder.append(clienti);
		builder.append("]");
		return builder.toString();
	}
	
}
