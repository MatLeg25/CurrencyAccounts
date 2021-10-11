package com.example.demo.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
public class PeselValidator {

    private final int PESEL_LENGTH = 11;

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
        if(pesel.length() == PESEL_LENGTH) {
            return true;
        }
        log.warn("Incorrect pesel ["+pesel+"] - invalid length");
        return false;
    }


    //TODO more detailed verification
    //The current implementation of age validation works with the date of birth in range 1900-2099
    //The current implementation assume adult person when: (current year - birth year) >= 18 - NO ACCURATE VERIFICATION BASED ON CALENDAR DAY
    //Age validation according to PESEL, based on: https://obywatel.gov.pl/pl/dokumenty-i-dane-osobowe/czym-jest-numer-pesel
    private boolean isAdult(String pesel) {

        int ADULT_AGE = 18;
        int peselCentury;

        LocalDateTime time = LocalDateTime.now();
        int currentYear = time.getYear();
        int currentMonth = time.getMonthValue();
        int currentDay = time.getDayOfMonth();

        int peselYear = Integer.parseInt(pesel.substring(0,2));
        int peselMonth = Integer.parseInt(pesel.substring(2,4));
        int peselDay = Integer.parseInt(pesel.substring(4,6));

        //The current implementation of age validation works with the date of birth in range 1900-2099
        peselCentury = (peselMonth<=12) ? 1900 : 2000;

        return ((currentYear-ADULT_AGE)>=(peselYear+peselCentury));
    }

}
