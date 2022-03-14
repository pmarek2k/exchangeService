package com.pmarek.exchangeService.Controllers;

import com.pmarek.exchangeService.Services.GoldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoldController {

    private final GoldService goldService;
    private final static Logger logger = LogManager.getLogger(GoldController.class);

    @Autowired
    public GoldController(GoldService goldService){
        this.goldService = goldService;
    }

    @GetMapping(path = "/api/gold-price/average", produces = "application/json")
    @ResponseBody
    public double getAverageGoldPriceForLast14BusinessDays(){
        logger.info("Gold-price API call has been received.");
        return goldService.getAverageGoldPriceForLastBusinessDays(14);
    }
}
