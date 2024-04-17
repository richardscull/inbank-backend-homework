package ee.taltech.inbankbackend.util;

import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeValidator;

import java.time.Period;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeParser;
import ee.taltech.inbankbackend.exceptions.*;

import ee.taltech.inbankbackend.config.DecisionEngineConstants;

public class Validator {
    private static final EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();
    private static final EstonianPersonalCodeParser parser = new EstonianPersonalCodeParser();

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

    /**
     * Validates the age of the customer based on their personal code.
     *
     * @param personalCode The personal code to validate.
     * @throws InvalidPersonalAgeException If the age is invalid.
     */
    public static void validateAgeForLoanPeriod(String personalCode, int loanPeriod)
            throws InvalidPersonalAgeException {
        try {
            Period age = parser.getAge(personalCode);
            Gender gender = parser.getGender(personalCode);

            Period ageWithPeriod = age.plusMonths(loanPeriod);
            double ageDouble = ageWithPeriod.getYears()
                    + ageWithPeriod.getMonths() / 12.0;

            if (age.getYears() < DecisionEngineConstants.MINIMUM_LOAN_AGE) {
                throw new InvalidPersonalAgeException("Customer is too young!");
            }

            switch (gender) {
                case MALE:
                    if (ageDouble > DecisionEngineConstants.MAXIMUM_LIFETIME_MALE) {
                        throw new InvalidPersonalAgeException("Customer is too old!");
                    }
                    break;
                case FEMALE:
                    if (ageDouble > DecisionEngineConstants.MAXIMUM_LIFETIME_FEMALE) {
                        throw new InvalidPersonalAgeException("Customer is too old!");
                    }
                    break;
                default:
                    throw new InvalidPersonalAgeException("Invalid gender!");
            }
        } catch (PersonalCodeException e) {
            throw new InvalidPersonalAgeException("Invalid personal code!");
        }
    }
}
