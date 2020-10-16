package com.patrick.order

import com.patrick.notification.CustomerNotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.NumberFormatException

@Component
class OrderServiceImpl(@Autowired private val customerNotificationService: CustomerNotificationService): OrderService {

    val apple = "Apple"
    val orange = "Orange"
    val applePrice = 0.6
    val orangePrice = 0.25

    override fun order(args: Array<String>): String {
        val stock : Int = try {
            Integer.valueOf(args[args.size -1])
        }
        catch (ex: NumberFormatException) {
            5
        }
        var order = 0.0
        var numberOfApples = 0
        var numberOfOranges = 0
        var outOfStock = false

        if (args.size > stock) {
            outOfStock = true
        }
        else {
            for (shoppingGood in args) {
                if (apple == shoppingGood) {
                    numberOfApples++
                    if (numberOfApples % 2 != 0) {
                        order += applePrice
                    }
                } else if (orange == shoppingGood) {
                    numberOfOranges++
                    if (numberOfOranges % 3 != 0) {
                        order += orangePrice
                    }
                }
            }
        }

        val orderStatus = notifyCustomer(numberOfApples, numberOfOranges, outOfStock)
        return "Order price = $order, order status = $orderStatus"
    }

    private fun notifyCustomer(numberOfApples: Int, numberOfOranges: Int, outOfStock: Boolean): String {
        val orderStatus: String
        var deliveryTime = 1
        if (numberOfApples + numberOfOranges > 5) {
            deliveryTime = 2
        }
        if (!outOfStock) {
            orderStatus = "successful"
            customerNotificationService.sendMessage("Order complete")
            customerNotificationService.sendMessage("Estimated delivery time = $deliveryTime days")
        } else {
            orderStatus = "out of stock"
        }
        customerNotificationService.sendMessage("Current order status = $orderStatus")
        return orderStatus
    }
}



