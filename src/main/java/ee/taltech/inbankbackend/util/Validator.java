package ee.taltech.inbankbackend.util;

import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeValidator;
import ee.taltech.inbankbackend.exceptions.*;

import ee.taltech.inbankbackend.config.DecisionEngineConstants;

public class Validator {
    private static final EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();

    public Validator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Validates the personal code.
     *
     * @param personalCode The personal code to validate.
     * @throws InvalidPersonalCodeException If the personal code is invalid.
     */
    public static void validatePersonalCode(String personalCode) throws InvalidPersonalCodeException {
        if (!validator.isValid(personalCode)) {
            throw new InvalidPersonalCodeException("Invalid personal code!");
        }
    }

    /**
     * Validates the loan amount.
     *
     * @param loanAmount The loan amount to validate.
     * @throws InvalidLoanAmountException If the loan amount is invalid.
     */
    public static void validateLoanAmount(Long loanAmount) throws InvalidLoanAmountException {
        if ((DecisionEngineConstants.MINIMUM_LOAN_AMOUNT > loanAmount)
                || (loanAmount > DecisionEngineConstants.MAXIMUM_LOAN_AMOUNT)) {
            throw new InvalidLoanAmountException("Invalid loan amount!");
        }
    }

    /**
     * Validates the loan period.
     *
     * @param loanPeriod The loan period to validate.
     * @throws InvalidLoanPeriodException If the loan period is invalid.
     */
    public static void validateLoanPeriod(int loanPeriod) throws InvalidLoanPeriodException {
        if ((DecisionEngineConstants.MINIMUM_LOAN_PERIOD > loanPeriod)
                || (loanPeriod > DecisionEngineConstants.MAXIMUM_LOAN_PERIOD)) {
            throw new InvalidLoanPeriodException("Invalid loan period!");
        }
    }

    // TODO: Add age validation;
}
