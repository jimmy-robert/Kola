package io.kola.events

import java.util.*

class EventHandler {
    private val handlers = mutableListOf<() -> Unit>()
    private val handlersByToken = mutableMapOf<String, () -> Unit>()

    operator fun set(key: String, handler: () -> Unit) {
        if (key in handlersByToken) {
            val currentHandler = handlersByToken[key]
            handlers.remove(currentHandler)
        }
        handlers += handler
        handlersByToken[key] = handler
    }

    operator fun invoke() {
        for (handler in handlers) handler()
    }

    fun register(handler: () -> Unit): String {
        val token = UUID.randomUUID().toString()
        if (token in handlersByToken) return register(handler)
        set(token, handler)
        return token
    }

    fun unregister(token: String) {
        val handler = handlersByToken[token]
        if (handler != null) {
            handlers -= handler
            handlersByToken -= token
        }
    }

    operator fun plusAssign(handler: () -> Unit) {
        register(handler)
    }

    operator fun minusAssign(token: String) {
        unregister(token)
    }

    fun clear() {
        handlers.clear()
        handlersByToken.clear()
    }

    fun unregisterAll() {
        for ((token, _) in handlersByToken) unregister(token)
    }
}