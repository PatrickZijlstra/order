package com.patrick.order

import com.patrick.notification.CustomerNotificationService
import com.patrick.notification.CustomerNotificationServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceImplTest {

    private val customerNotificationService: CustomerNotificationService = Mockito.mock(CustomerNotificationServiceImpl::class.java)
    private val orderServiceImpl: OrderServiceImpl = OrderServiceImpl(customerNotificationService)

    @Test
    fun orderTest() {
        Mockito.doNothing().`when`(customerNotificationService)

        // Happy flow
        assertEquals("Order price = 0.85, order status = successful", orderServiceImpl.order(arrayOf("Apple", "Orange")))
        // Happy flow with stock
        assertEquals("Order price = 0.85, order status = successful", orderServiceImpl.order(arrayOf("Apple", "Orange", "15")))
        // Happy flow second apple free
        assertEquals("Order price = 1.45, order status = successful", orderServiceImpl.order(arrayOf("Apple", "Apple", "Orange", "Apple")))
        // Happy flow three oranges for the price of two
        assertEquals("Order price = 0.5, order status = successful", orderServiceImpl.order(arrayOf("Orange", "Orange", "Orange")))
        // Unhappy flow with low stock
        assertEquals("Order price = 0.0, order status = out of stock", orderServiceImpl.order(arrayOf("Apple", "Orange", "1")))
        // Unhappy flow with low stock
        assertEquals("Order price = 0.0, order status = out of stock", orderServiceImpl.order(arrayOf("Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "1")))
        // Unhappy flow empty input
        assertEquals("Order price = 0.0, order status = successful", orderServiceImpl.order(arrayOf("")))
        // Unhappy flow invalid input
        assertEquals("Order price = 0.0, order status = successful", orderServiceImpl.order(arrayOf("Pear", "Kiwi")))
    }

}
