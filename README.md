# Inventory Service - Ecommerce BackEnd

## Overview
The **Inventory Service** is a crucial part of the **Ecommerce BackEnd** application, responsible for managing stock levels of products. This service allows for adding, retrieving, and reducing stock quantities, ensuring that stock levels are consistently maintained and available for other services.

## Purpose
The **Inventory Service** enables:
- **Stock Management**: Supports operations to add, retrieve, and decrease product stock levels.
- **Inventory Updates**: Provides endpoints for other services to query and update stock levels, supporting real-time inventory management in the application.

## Key Features
- **Add Stock**: Allows administrators or other services to increase stock for a product.
- **Retrieve Stock Level**: Provides the current stock level for a specific product.
- **Reduce Stock**: Supports reducing stock levels, useful when orders are placed and fulfilled.

## API Endpoints
Here are the primary endpoints available in the **Inventory Service**:

| Endpoint                  | Method | Description                                | Access     |
|---------------------------|--------|--------------------------------------------|------------|
| `/inventory/addStock`     | POST   | Add stock for a specific product           | Internal   |
| `/inventory/{productId}`  | GET    | Retrieve the stock level for a product ID  | Internal   |
| `/inventory/reduceStock`  | PUT    | Reduce stock for a specific product        | Internal   |

> **Note**: These endpoints are generally internal, intended for inter-service communication to manage stock accurately within the system.

## Dependencies
- **Java 17** (or compatible version)
- **Spring Boot**: Provides the foundational framework for building the service.
- **MySQL**: Stores stock data, allowing for quick retrieval and updates.

## Configuration
Configuration for the **Inventory Service** includes:
- **Database Connection**: Manages connection to MySQL for persisting and retrieving stock data.

Key configuration properties are located in the `application.yml` file.

## Integration with Product and Order Services
The **Inventory Service** interacts with other services to maintain accurate stock levels:
- **Product Service**: Updates stock levels when products are added or modified.
- **Order Service**: Reduces stock when orders are placed, ensuring that inventory is managed in real time.

## Security
The **Inventory Service** endpoints are primarily intended for inter-service communication, ensuring stock levels remain accurate and up-to-date across the **Ecommerce BackEnd** application.

This **Inventory Service** plays a vital role in tracking and maintaining inventory within the application.
