package PhoneBookCore.validator;

import Exceptions.UnexpectedValidationCase;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private final static Integer PHONE_NUMBER_MAX_LENGTH = 15;
    private final static Integer FIRST_NAME_MAX_LENGTH = 15;
    private final static Integer LAST_NAME_MAX_LENGTH = 15;
    private final static Integer EMAIL_MAX_LENGTH = 15;
    private final static char [] VALID_CHARS_IN_PHONE_NUMBER = new char [] {'0','1','2','3','4','5','6','7','8','9','-'};
    private final static String [] MANDATORY_CHARS_IN_EMAIL = new String [] {"@","."};
    private final static char [] EMAIL_INVALID_CHARS = new char [] {'%','#','/','+','&','?','*','\\'};
    private final static char [] INVALID_CHARS = new char [] {'%','#','@','/','+','&','?','*','\\'};


    private StringBuilder errors = new StringBuilder("\n\nPLEASE FIX NEXT ERRORS:\n");
    private List<Boolean> validationResults = new ArrayList<>();

    public void clearErrors(){
        errors = new StringBuilder("\n\nPLEASE FIX NEXT ERRORS:\n");
        validationResults = new ArrayList<>();
    }

    public String getErrors() {
        return errors.toString();
    }

    public Boolean validateThisData(String inputData, Rule validationRule){
        switch (validationRule){
            case FIRST_NAME : {
                if ( !lengthValidator(inputData, FIRST_NAME_MAX_LENGTH, "FIRST NAME")){
                    return false;
                }
                validationResults.add(invalidCharsValidator(inputData, INVALID_CHARS, "FIRST NAME"));
                return checkValidationResults(validationResults);
            }
            case LAST_NAME : {
                if ( !lengthValidator(inputData, LAST_NAME_MAX_LENGTH, "LAST NAME")){
                    return false;
                }
                validationResults.add(invalidCharsValidator(inputData, INVALID_CHARS, "LAST NAME"));
                return checkValidationResults(validationResults);
            }
            case PHONE_NUMBER : {
                if ( !lengthValidator(inputData,PHONE_NUMBER_MAX_LENGTH, "PHONE NUMBER")){
                    return false;
                }
                validationResults.add(validCharsValidator(inputData,VALID_CHARS_IN_PHONE_NUMBER, "PHONE NUMBER"));
                return checkValidationResults(validationResults);
            }
            case EMAIL : {
                if ( !lengthValidator(inputData, EMAIL_MAX_LENGTH, "EMAIL")){
                    return false;
                }
                validationResults.add(mandatoryCharsValidator(inputData, MANDATORY_CHARS_IN_EMAIL, "EMAIL"));
                validationResults.add(invalidCharsValidator(inputData,EMAIL_INVALID_CHARS, "EMAIL"));
                return checkValidationResults(validationResults);
            }
            default: throw new UnexpectedValidationCase();
        }
    }

    private Boolean lengthValidator(String inputString, Integer value, String msg){
        if(inputString.length() > value){
            errors.append(msg + " should be no longer than '" + value + "' ");
            return false;
        }
        return true;
    }

    private Boolean invalidCharsValidator(String inputString, char [] invalidChars, String msg){
        StringBuilder sb = new StringBuilder("[ ");
        Boolean result = true;
        for (char charInFirstName : inputString.toCharArray()){
            Boolean isCharValid = true;
            for (char invalidChar : invalidChars){
                if (charInFirstName == invalidChar){
                    isCharValid = false;
                    break;
                }
            }
            if (!isCharValid) {
                sb.append(charInFirstName);
                result = false;
            }
        }
        sb.append( " ] - are not valid chars for " + msg +  "!\n");
        if (!result) {
            errors.append(sb);
        }
        return result;
    }

    private Boolean validCharsValidator(String inputString, char [] validChars, String msg){
        StringBuilder sb = new StringBuilder("[ ");
        Boolean result = true;
        for (char charInString : inputString.toCharArray()){
            Boolean isCharValid = false;
            for (char validChar : validChars){
                if (charInString == validChar){
                    isCharValid = true;
                    break;
                }
            }
            if (!isCharValid) {
                sb.append(charInString);
                result = false;
            }
        }
        sb.append( " ] - are not valid chars for " + msg + "!\n");
        if (!result) {
            errors.append(sb);
        }
        return result;
    }

    private Boolean mandatoryCharsValidator (String inputString, String [] mandatoryChars, String msg) {
        StringBuilder sb = new StringBuilder("[ ");
        for (String charInString : mandatoryChars){
            if (!inputString.contains(charInString)) {
                sb.append(charInString);
            }
        }

        if (sb.length() > 2) {
            sb.append(" ] mandatory chars are absent in " + msg + "!\n");
            errors.append(sb);
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkValidationResults(List<Boolean> validationResults){
        for (Boolean validationResult :  validationResults){
            if (!validationResult) {
                return false;
            }
        }
        return true;
    }

}
