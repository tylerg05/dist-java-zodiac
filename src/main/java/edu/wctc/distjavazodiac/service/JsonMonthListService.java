package edu.wctc.distjavazodiac.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wctc.distjavazodiac.entity.Month;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class JsonMonthListService implements MonthListService {
    private Month[] monthArray;

    @Override
    public Month[] getMonths() {
        return monthArray;
    }

    @PostConstruct
    public void initMonths() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            monthArray = mapper.readValue(Paths.get("months.json").toFile(), Month[].class);
        } catch (IOException e) {
            e.printStackTrace();
            monthArray = new Month[0];
        }
    }
}
