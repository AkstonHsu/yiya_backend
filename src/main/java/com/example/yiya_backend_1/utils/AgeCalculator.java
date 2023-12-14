package com.example.yiya_backend_1.utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {
    public static int calculateAge(LocalDate birthdate) {
        if (birthdate == null) {
            return 0;
            // 或者抛出异常，表示日期为空
        }

        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }
}
