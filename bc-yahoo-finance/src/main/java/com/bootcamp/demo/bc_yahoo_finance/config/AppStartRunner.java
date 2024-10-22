package com.bootcamp.demo.bc_yahoo_finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bootcamp.demo.bc_yahoo_finance.entity.StockSymbolEntity;
import com.bootcamp.demo.bc_yahoo_finance.repository.StockSymbolRepository;


@Component
public class AppStartRunner implements CommandLineRunner {
  @Autowired
    private StockSymbolRepository stockSymbolRepository;

  @Override
    public void run(String... args) throws Exception {
        String[] symbols = {"0388.HK", "0700.HK"};

        for (String symbol : symbols) {
            StockSymbolEntity stockSymbol = new StockSymbolEntity();
            stockSymbol.setSymbol(symbol);
            stockSymbolRepository.save(stockSymbol);
        }

        System.out.println("Stock symbols loaded into the database.");
    }

  
}
