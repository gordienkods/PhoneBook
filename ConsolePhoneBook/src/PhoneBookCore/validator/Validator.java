package PhoneBookCore.validator;

import Exceptions.UnexpectedValidationCase;

public class Validator {

    private final static Integer PHONE_NUMBER_MAX_LENGTH = 15;
    private final static char [] VALID_CHARS_IN_PHONE_NUMBER = new char [] {'0','1','2','3','4','5','6','7','8','9','-'};

    private static StringBuilder errors = new StringBuilder("PLEASE FIX NEXT ERRORS:\n");

    public static String validateThisData(String inputData, Rule validationRule){
        switch (validationRule){
            case PHONE_NUMBER : {
                                    Boolean trigger;
                                    trigger = phoneNumberLengthValidator(inputData);
                                    if (trigger) {
                                       trigger = phoneNumberCharsValidator(inputData);
                                    }
                                    if (!trigger) {
                                        return errors.toString();
                                    } else {
                                        return "";
                                    }
                               }
            default: throw new UnexpectedValidationCase();
        }
    }

    private static Boolean phoneNumberLengthValidator(String phoneNumber){
        if(phoneNumber.length() > PHONE_NUMBER_MAX_LENGTH){
            errors.append("PHONE NUMBER should be no longer then '" + PHONE_NUMBER_MAX_LENGTH + "' ");
            return false;
        }
        return true;
    }

    private static Boolean phoneNumberCharsValidator(String phoneNumber){
        StringBuilder sb = new StringBuilder("[ ");
        Boolean result = true;
        for (char charInPhoneNumber : phoneNumber.toCharArray()){
            Boolean isCharValid = false;
            for (char validChar : VALID_CHARS_IN_PHONE_NUMBER){
                if (charInPhoneNumber == validChar){
                    isCharValid = true;
                    break;
                }
            }
            if (!isCharValid) {
                sb.append(charInPhoneNumber);
                result = false;
            }
        }
        sb.append( " ] - are not valid chars for PHONE NUMBER!\n");
        if (!result) {
            errors.append(sb);
        }
        return result;
    }

}
