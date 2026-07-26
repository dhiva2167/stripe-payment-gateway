package payment_gateway_integration.Payment;

public class PaymentRequest {
    
     private Long amount;
     private String currency;

     public String getCurrency(){
            return currency;
     }
     public void setCurrency(String currency){
            this.currency = currency;
     }
     public Long getAmount(){
            return amount;
     }
     public void setAmount(Long amount){
            this.amount = amount;
     }

}
