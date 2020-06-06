package com.discover.app;

import com.discover.app.exception.DownStreamException;
import com.discover.app.model.AccountSummary;
import com.discover.app.model.MoneyAccount;
import com.discover.app.service.AccountService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("enterprise/customer/v1")
@Slf4j
public class AccountController {

    private AccountService accountService;
    @Autowired private RestTemplate restTemplate;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "cardinfo")
    public ResponseEntity<AccountSummary> getAccountInfo() throws DownStreamException {
        log.info("calling account summary");
        try {
            return ResponseEntity.ok(accountService.getAccountSummary());
        } catch (RuntimeException e) {
            throw new DownStreamException("Account not found");
        }
    }

    @GetMapping(value = "moneyinfo")
    public Flux<MoneyAccount> getMoneyAccountInfo() throws DownStreamException {
        return accountService.monoAccount();
    }

    @GetMapping(value = "test", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MoneyAccount> getDooo() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);

        MoneyAccount moneyAccount =
                MoneyAccount.builder().name("Mike").accountBalance("23.44").build();
        MoneyAccount moneyAccount1 =
                MoneyAccount.builder().name("Kate").accountBalance("23.02").build();
        return Flux.just(moneyAccount, moneyAccount1);
        //        return Flux.fromIterable(list).delayElements(Duration.ofSeconds(2)).log();
    }

    @GetMapping(value = "/")
    public String hello() {
        log.info("calling default endpoint showing sleuth functionality");
        return "hello people";
    }

    @GetMapping(value = "/home")
    public String helloHome() {
        log.info(" calling /home");
        return restTemplate.getForObject(
                "http://localhost:8080/enterprise/customer/v1/", String.class);
    }

    @GetMapping(value = "list")
    public ResponseEntity<String> getValue(@RequestHeader Map<String, String> headers) {
        log.info("calling the json placholder");
        headers.forEach(
                (k, v) -> {
                    if (k.toLowerCase().startsWith("foo ")) {
                        log.info("Pragma : {}", v);
                    }
                });

        ResponseEntity<String> value =
                restTemplate.exchange(
                        "https://jsonplaceholder.typicode.com/todos/19",
                        HttpMethod.GET,
                        null,
                        String.class);
        log.info("calling:{}", value.getHeaders().getContentType());
        log.info("calling header:{}", value.getHeaders().get("Foo"));
        log.info("calling:{}", value);
        return value;
    }

    @GetMapping(value = "lists/{id}")
    public ResponseEntity<String> getNewValue(@PathVariable("id") int number)
            throws URISyntaxException {
        log.info("calling the json placholder");
        RequestEntity<?> request =
                RequestEntity.get(
                                new URI(
                                        String.format(
                                                "https://jsonplaceholder.typicode.com/todos/%s",
                                                number)))
                        .build();
        return restTemplate.exchange(request, String.class);
        // return value;
    }
}
