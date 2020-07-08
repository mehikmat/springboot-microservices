package microservice.demo.currencyconverter.service;

import microservice.demo.currencyconverter.model.CurrencyConverterBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forexservice", url = "localhost:8000")
public interface ForexServiceProxy {

    @GetMapping("/forexservice/from/{from}/to/{to}")
    public CurrencyConverterBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
