package payment_gateway_integration.Payment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "https://stripe-payment-gateway-ra8x.onrender.com")
@RestController
public class PaymentController {

    @PostMapping("/api/create-payment-intent")
    public PaymentResponse createPaymentIntent(@RequestBody PaymentRequest Request) throws StripeException {
          
        PaymentIntentCreateParams params =  PaymentIntentCreateParams.builder()
                .setAmount(Request.getAmount())
                .setCurrency(Request.getCurrency())
                .build();
       
         
        PaymentIntent intent = PaymentIntent.create(params);

        PaymentResponse response = new PaymentResponse();
        response.setClientSecret(intent.getClientSecret());

        return response;
    }
@ExceptionHandler(StripeException.class)
    public ErrorResponse handleStripeException(StripeException e) {
        
        return new ErrorResponse("Error: " + e.getMessage());
    }
}
