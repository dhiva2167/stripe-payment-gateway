import { useState } from "react";
import { PaymentElement, useStripe, useElements } from "@stripe/react-stripe-js";

function CheckoutForm() {
  const stripe = useStripe();
  const elements = useElements();
  const [message, setMessage] = useState(null);

  async function handleSubmit(event) {
    event.preventDefault(); // stop the page from refreshing on submit

    if (!stripe || !elements) return;

    const result = await stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: "http://localhost:5173", // where Stripe sends the user after payment
      },
    });

    if (result.error) {
      setMessage(result.error.message);
    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <PaymentElement />
      <br />
      <button type="submit" disabled={!stripe}>
        Pay Now
      </button>
      {message && <p style={{ color: "red" }}>{message}</p>}
    </form>
  );
}

export default CheckoutForm;