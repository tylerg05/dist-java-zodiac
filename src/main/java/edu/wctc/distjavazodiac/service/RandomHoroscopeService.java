package edu.wctc.distjavazodiac.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wctc.distjavazodiac.entity.Birthday;
import edu.wctc.distjavazodiac.entity.Horoscope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

/*
Random horoscopes from https://cafeastrology.com/dailyhoroscopesall-tomorrow.html
 */
@Service
public class RandomHoroscopeService implements HoroscopeService {
    private String[] allHoroscopes;
    private ZodiacService zodiacService;

    @Autowired
    public RandomHoroscopeService(ZodiacService zodiacService) {
        this.zodiacService = zodiacService;
    }

    @PostConstruct
    public void initHoroscopes() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            allHoroscopes = mapper.readValue(Paths.get("horoscopes.json").toFile(), String[].class);
        } catch (IOException e) {
            e.printStackTrace();
            allHoroscopes = new String[]{"Today will not be good for reading JSON files"};
        }
    }


    @Override
    public Horoscope getHoroscope(Birthday birthday) {
        String sign;
        if (birthday.getZodiacType().toLowerCase().startsWith("w")) {
            sign = zodiacService.getWesternZodiacSign(birthday);
        } else {
            sign = zodiacService.getEasternZodiacSign(birthday);
        }

        Horoscope hscope = new Horoscope();
        hscope.setSign(sign);

        int randomIndex = (int)(Math.random() * allHoroscopes.length);
        hscope.setHoroscope(allHoroscopes[randomIndex]);
        return hscope;
    }
}
