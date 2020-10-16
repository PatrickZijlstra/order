package com.patrick.notification

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito
import org.springframework.kafka.core.KafkaTemplate


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerNotificationServiceImplTest {

    private val kafkaTemplate: KafkaTemplate<*, *>? = Mockito.mock(KafkaTemplate::class.java)
    private val customerNotificationService: CustomerNotificationService = CustomerNotificationServiceImpl(kafkaTemplate as KafkaTemplate<String, String>)


    @Test
    fun sendNotificationTest() {
        customerNotificationService.sendMessage("Message")
        Mockito.verify(kafkaTemplate, Mockito.times(1))
    }

    @Test
    fun listenOrderTest() {
        assertEquals("Message received", customerNotificationService.listenOrder("Message"))


    }
}