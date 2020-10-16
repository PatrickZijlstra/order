package com.patrick.notification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class CustomerNotificationServiceImpl(@Autowired private val kafkaTemplate: KafkaTemplate<String, String>): CustomerNotificationService {

    val topic = "orderTopic"

    override fun sendMessage(msg: String?) {
        kafkaTemplate.send(topic, msg)
    }

    @KafkaListener(topics = ["orderTopic"], groupId = "order")
    override fun listenOrder(message: String): String {
        println("Received Message in group order: $message")
        return "Message received"
    }
}