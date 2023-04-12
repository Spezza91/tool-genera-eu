package it.poste.generafile.model;

public class BodyTag {

	private Analytic analytic;

	public Analytic getAnalytic() {
		return analytic;
	}

	public void setAnalytic(Analytic analytic) {
		this.analytic = analytic;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BodyTag [analytic=");
		builder.append(analytic);
		builder.append("]");
		return builder.toString();
	}

}
