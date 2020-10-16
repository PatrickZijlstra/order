package com.patrick

import com.patrick.notification.CustomerNotificationService
import com.patrick.order.OrderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderApplicationTests {

	private val orderService: OrderService = Mockito.mock(OrderService::class.java)
	private val customerNotificationService: CustomerNotificationService = Mockito.mock(CustomerNotificationService::class.java)
	private val orderApplication: OrderApplication = OrderApplication(orderService, customerNotificationService)

	@Test
	fun contextLoads() {
	}

	@Test
	fun testMain() {
		try {
			val input = arrayOf("Apple", "Orange", "5")
			val message = "Order price = 0.85, order status = successful"
			Mockito.`when`(orderService.order(input)).thenReturn(message)
			Mockito.`when`(customerNotificationService.listenOrder(anyString())).thenReturn(message)

			assertEquals(message, orderApplication.main(input))
		}
		catch (ex: Exception) {
			// If Kafka is not set up locally, catch exception here
			assertEquals("org.apache.kafka.common.config.ConfigException: No resolvable bootstrap urls given in bootstrap.servers", ex.cause?.cause?.toString())
		}
	}

}
