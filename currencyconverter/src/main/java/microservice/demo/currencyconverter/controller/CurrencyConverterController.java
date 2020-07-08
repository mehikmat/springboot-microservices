package microservice.demo.currencyconverter.controller;

import microservice.demo.currencyconverter.model.CurrencyConverterBean;
import microservice.demo.currencyconverter.service.ForexServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConverterController {
    @Autowired
    private ForexServiceProxy proxy;

    @GetMapping("/currencyconverter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConverterBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        ////////////Can be used RestTemplate
        /*Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConverterBean> responseEntity = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/forexservice/from/{from}/to/{to}",
                        CurrencyConverterBean.class,
                        uriVariables);

        CurrencyConverterBean response = responseEntity.getBody();

        return new CurrencyConverterBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());*/

        //////////// Can be used Feign client
        CurrencyConverterBean response = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConverterBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
