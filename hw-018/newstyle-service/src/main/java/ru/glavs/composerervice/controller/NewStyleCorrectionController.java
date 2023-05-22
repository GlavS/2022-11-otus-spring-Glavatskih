package ru.glavs.composerervice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class NewStyleCorrectionController {

    @Value("${controller.test.delay}")
    private long testDelay;

    @GetMapping("/newstyleof/{oldStyleDate}")
    public LocalDate convertToNewStyle(@PathVariable String oldStyleDate) throws Exception{
        LocalDate oldDate = LocalDate.parse(oldStyleDate);
        long plusDays = getDateDiff(oldDate.getYear());
        Thread.sleep(testDelay);
        return oldDate.plusDays(plusDays);
    }

    private long getDateDiff(int year) {
        return Math.floorDiv(year, 100) - Math.floorDiv(year, 400) - 2;
    }
}
