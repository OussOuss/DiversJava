package design.pattern.builder;

public interface LoanBuilder {
	void loanApply();
	void loanApproval();
	void loanSanction();
	Loan loanDisburse();
}
