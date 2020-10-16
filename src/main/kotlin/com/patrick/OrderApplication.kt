package com.patrick

import com.patrick.notification.CustomerNotificationService
import com.patrick.order.OrderService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrderApplication(private val orderService: OrderService, private val customerNotificationService: CustomerNotificationService) {

	fun main(args: Array<String>): String {
		runApplication<OrderApplication>(*args)
		val result = orderService.order(args)
		return customerNotificationService.listenOrder(result)
	}
}
