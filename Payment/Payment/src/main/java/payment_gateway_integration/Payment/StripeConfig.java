package payment_gateway_integration.Payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import com.stripe.Stripe;
 

@Component   
public class StripeConfig {

    @Value("${stripe.secret.key}")  
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
}
