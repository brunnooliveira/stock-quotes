package br.com.bgdo.stockquotesapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Log4j2
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class StockQuotesApiApplication {

  private final QuoteRepository quoteRepository;

  public static void main(String[] args) {
    SpringApplication.run(StockQuotesApiApplication.class, args);
  }

  @Scheduled(fixedDelay = 1000)
  public void generateDate() {
		log.info(quoteRepository.findFirstBySymbolOrderByTimestampDesc("TESTE")
      .map(this::generateNewData)
      .orElseGet(this::initializeData));
  }

  private Quote initializeData() {
    return quoteRepository.save(Quote.builder()
      .symbol("TESTE")
      .openValue(0.222222)
      .closeValue(0.222222)
      .timestamp(new Date())
      .build());
  }

  private Quote generateNewData(Quote quote) {
    return quoteRepository.save(Quote.builder()
      .symbol(quote.getSymbol())
      .openValue(quote.getOpenValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
      .closeValue(quote.getCloseValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
      .timestamp(new Date())
      .build());
  }

}

