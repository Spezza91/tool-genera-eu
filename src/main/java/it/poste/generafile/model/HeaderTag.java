package it.poste.generafile.model;

public class HeaderTag {

	private Booking booking;
	private Customer customer;
	private User user;
	private Booker booker;
	private Contract contract;
	private OrgUnit orgUnit;

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Booker getBooker() {
		return booker;
	}

	public void setBooker(Booker booker) {
		this.booker = booker;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public OrgUnit getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(OrgUnit orgUnit) {
		this.orgUnit = orgUnit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HeaderTag [booking=");
		builder.append(booking);
		builder.append(", customer=");
		builder.append(customer);
		builder.append(", user=");
		builder.append(user);
		builder.append(", booker=");
		builder.append(booker);
		builder.append(", contract=");
		builder.append(contract);
		builder.append(", orgUnit=");
		builder.append(orgUnit);
		builder.append("]");
		return builder.toString();
	}

}
