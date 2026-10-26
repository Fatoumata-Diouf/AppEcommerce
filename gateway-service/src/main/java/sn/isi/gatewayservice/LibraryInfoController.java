package sn.isi.gatewayservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/library-info")
@RefreshScope
public class LibraryInfoController {

    @Value("${library.welcome-message:Bienvenue}")
    private String welcomeMessage;

    @Value("${library.max-loan-days:14}")
    private Integer maxLoanDays;

    @GetMapping
    public Map<String, Object> info() {
        return Map.of(
                "welcomeMessage", welcomeMessage,
                "maxLoanDays", maxLoanDays
        );
    }
}
