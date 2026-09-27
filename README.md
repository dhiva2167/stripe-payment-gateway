# 💳 Stripe Payment Gateway Integration

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Stripe](https://img.shields.io/badge/Stripe-626CD9?style=for-the-badge&logo=Stripe&logoColor=white)

A secure, enterprise-grade payment processing backend built with Java and Spring Boot. This service wraps the Stripe API to handle payments, refunds, and asynchronous webhook events reliably.

## 🚀 Overview

Network failures are inevitable in distributed systems. If a payment request times out, retrying it blindly could result in a customer being double-charged. This project solves that problem by implementing **Idempotency Keys**, ensuring that even if a network retry occurs, a transaction is only processed exactly once.

It also features strict webhook signature verification to guarantee that incoming events (like payment successes or failures) genuinely originated from Stripe and haven't been tampered with.

## ✨ Key Features

- **Idempotency Guarantee:** Prevents duplicate charges during network retries using idempotency keys. (Tested successfully under 500 concurrent request load).
- **Secure Webhook Processing:** Verifies cryptographic signatures of all incoming Stripe webhooks.
- **End-to-End Flow:** Supports checkout session creation, payment intents, and refund processing.

## 🏗️ Tech Stack
- **Language:** Java 17+
- **Framework:** Spring Boot 3
- **Payment Provider:** Stripe Java SDK

## ⚙️ Local Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/dhiva2167/stripe-payment-gateway.git
   cd stripe-payment-gateway
   ```

2. **Configure Environment Variables:**
   Create an `application.properties` or `.env` file with your Stripe keys:
   ```properties
   stripe.api.key=sk_test_your_secret_key
   stripe.webhook.secret=whsec_your_webhook_secret
   ```

3. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```

## 📡 API Endpoints (Example)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/payments/create` | Initializes a new payment intent. Requires an idempotency key. |
| `POST` | `/api/v1/webhooks/stripe` | Listens for asynchronous events from Stripe. |

## 🧪 Testing
- The service was subjected to a 500 concurrent request load test, simulating aggressive network retries. Resulted in **zero duplicate transactions**.
