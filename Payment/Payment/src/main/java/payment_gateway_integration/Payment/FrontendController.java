package payment_gateway_integration.Payment;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FrontendController {

    @GetMapping("/payment")
    public String paymentPage() {
        return "payment"; // This will return the payment.html page
    }
  
}