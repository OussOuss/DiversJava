package pattern.builder;

/**
 * Builder pattern builds a complex object using simple objects and using a step
 * by step approach. This type of design pattern comes under creational pattern
 * as this pattern provides one of the best ways to create an object.
 * 
 * A Builder class builds the final object step by step. This builder is
 * independent of other objects.
 * 
 * @author otahiri
 *
 */
public class BuilderPatternMain {

	public static void main(String[] args) {
		LoanManager loanManager = new LoanManager(new HomeLoanBuilder());
		Loan homeLoan = loanManager.buildLoan();
		System.out.println(homeLoan);

		loanManager = new LoanManager(new CarLoanBuilder());
		Loan carLoan = loanManager.buildLoan();
		System.out.println(carLoan);

		loanManager = new LoanManager(new PersonalLoanBuilder());
		Loan personalLoan = loanManager.buildLoan();
		System.out.println(personalLoan);
	}

}
