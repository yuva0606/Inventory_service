package com.yuva.inventory.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.yuva.inventory.servicee.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBusConfig {

    @Autowired
    private InventoryService inventoryService;

    @Bean
    public ServiceBusProcessorClient serviceBusProcessorClient() {
        ServiceBusProcessorClient client =  new ServiceBusClientBuilder()
                .connectionString("Endpoint=sb://reducestock.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=9b74FGBhA0tqYhjHPFJae/unVbzMlhfB2+ASbE8dM8s=")
                .processor()
                .queueName("stock-queue")
                .processMessage(context -> inventoryService.processMessage(context))
                .processError(context -> System.err.println("Error occurred: " + context.getException()))
                .buildProcessorClient();
        client.start();
        return client;
    }
}
