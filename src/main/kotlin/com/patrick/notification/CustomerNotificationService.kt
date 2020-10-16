package com.patrick.notification

interface CustomerNotificationService {

    fun sendMessage(msg: String?)

    fun listenOrder(message: String): String
}