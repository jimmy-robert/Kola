package io.kola.events

import java.util.*

class EventHandlerWithParam<T> {
    private val handlers = mutableListOf<(T) -> Unit>()
    private val handlersByToken = mutableMapOf<String, (T) -> Unit>()

    operator fun set(key: String, handler: (T) -> Unit) {
        if (key in handlersByToken) {
            val currentHandler = handlersByToken[key]
            handlers.remove(currentHandler)
        }
        handlers += handler
        handlersByToken[key] = handler
    }

    operator fun invoke(value: T) {
        for (handler in handlers) handler(value)
    }

    fun register(handler: (T) -> Unit): String {
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

    operator fun plusAssign(handler: (T) -> Unit) {
        register(handler)
    }

    operator fun minusAssign(token: String) {
        unregister(token)
    }

    fun clear() {
        handlers.clear()
        handlersByToken.clear()
    }
}