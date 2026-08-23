import { useState } from "react";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import CheckoutForm from "./CheckoutForm";


const stripePromise = loadStripe("pk_test_PUT_YOUR_PUBLISHABLE_KEY_HERE");

function App() {
  const [amount, setAmount] = useState(500);
  const [clientSecret, setClientSecret] = useState(null);

 
  async function handleStartPayment() {
    const response = await fetch("https://stripe-payment-gateway-ra8x.onrender.com/api/create-payment-intent", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        amount: amount * 100, 
        currency: "inr",
      }),
    });

    const data = await response.json();
    console.log("Received data:", data);  
    setClientSecret(data.clientSecret);
  }

  return (
    <div style={{ maxWidth: "400px", margin: "50px auto", fontFamily: "sans-serif" }}>
      <h1>Simple Checkout</h1>

      {!clientSecret && (
        <div>
          <label>
            Amount (INR):{" "}
            <input
              type="number"
              value={amount}
              onChange={(e) => setAmount(Number(e.target.value))}
            />
          </label>
          <br />
          <br />
          <button onClick={handleStartPayment}>Continue to Payment</button>
        </div>
      )}

      {clientSecret && (
        <Elements stripe={stripePromise} options={{ clientSecret }}>
          <CheckoutForm />
        </Elements>
      )}
    </div>
  );
}

export default App;