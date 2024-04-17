package ee.taltech.inbankbackend.util;

import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeValidator;
import ee.taltech.inbankbackend.exceptions.*;

import ee.taltech.inbankbackend.config.DecisionEngineConstants;

public class Validator {
    private static final EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();

    public Validator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validatePersonalCode(String personalCode) throws InvalidPersonalCodeException {
        if (!validator.isValid(personalCode)) {
            throw new InvalidPersonalCodeException("Invalid personal code!");
        }
    }

    public static void validateLoanAmount(Long loanAmount) throws InvalidLoanAmountException {
        if ((DecisionEngineConstants.MINIMUM_LOAN_AMOUNT > loanAmount)
                || (loanAmount > DecisionEngineConstants.MAXIMUM_LOAN_AMOUNT)) {
            throw new InvalidLoanAmountException("Invalid loan amount!");
        }
    }

    public static void validateLoanPeriod(int loanPeriod) throws InvalidLoanPeriodException {
        if ((DecisionEngineConstants.MINIMUM_LOAN_PERIOD > loanPeriod)
                || (loanPeriod > DecisionEngineConstants.MAXIMUM_LOAN_PERIOD)) {
            throw new InvalidLoanPeriodException("Invalid loan period!");
        }
    }

    // TODO: Add age validation;
}
