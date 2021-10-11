package com.example.demo.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
public class PeselValidator {


    public boolean validate(String pesel) {
        return isNumericPesel(pesel) && isValidLengthPesel(pesel) && isAdult(pesel);
    }


    private boolean isNumericPesel(String pesel) {
        try {
            Long.parseLong(pesel);
        } catch (NumberFormatException e) {
            log.warn("Incorrect pesel ["+pesel+"] - invalid character");
            return false;
        }
        return true;
    }


    private boolean isValidLengthPesel(String pesel) {
        if(pesel.length() == 11) {
            return true;
        }
        log.warn("Incorrect pesel ["+pesel+"] - invalid length");
        return false;
    }


    //TODO more detailed verification
    //The current implementation of age validation works with the date of birth in range 1900-2099
    //Age validation according to PESEL, based on: https://obywatel.gov.pl/pl/dokumenty-i-dane-osobowe/czym-jest-numer-pesel
    private boolean isAdult(String pesel) {

        int ADULT_AGE = 18;
        int peselCentury;

        LocalDateTime time = LocalDateTime.now();
        int currentYear = time.getYear();
        int currentMonth = time.getMonthValue();
        int currentDay = time.getDayOfMonth();
        //System.out.println("CURRENt DATE: "+ currentDay+"|"+currentMonth+"|"+currentYear);

        int peselYear = Integer.parseInt(pesel.substring(0,2));
        int peselMonth = Integer.parseInt(pesel.substring(2,4));
        int peselDay = Integer.parseInt(pesel.substring(4,6));

        //The current implementation of age validation works with the date of birth in range 1900-2099
        peselCentury = (peselMonth<=12) ? 1900 : 2000;
        //if(peselMonth<12) {peselCentury=1900;} else {peselCentury=2000;}

        //System.out.println("PESEL DATE: "+ peselDay+"|"+peselMonth+"|"+(peselYear+peselCentury));

        System.out.print("WERYFIKCJA WIEKU: ");
        System.out.println((currentYear-ADULT_AGE)>=(peselYear+peselCentury));

        return ((currentYear-ADULT_AGE)>=(peselYear+peselCentury));
    }

}
