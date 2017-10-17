package io.kola.events

import java.util.*

class EventHandler<T> {
    private val handlers = mutableListOf<(T) -> Unit>()
    private val handlersByToken = mutableMapOf<String, (T) -> Unit>()

    operator fun plusAssign(handler: (T) -> Unit) {
        register(handler)
    }

    operator fun minusAssign(token: String) {
        unregister(token)
    }

    operator fun set(key: String, handler: (T) -> Unit) {
        handlersByToken[key] = handler
    }

    operator fun invoke(value: T) {
        for (handler in handlers) handler(value)
    }

    fun register(handler: (T) -> Unit): String {
        val token = UUID.randomUUID().toString()
        handlers += handler
        handlersByToken[token] = handler
        return token
    }

    fun unregister(token: String) {
        val handler = handlersByToken[token]
        if (handler != null) {
            handlers -= handler
            handlersByToken -= token
        }
    }

    fun unregisterAll(tokens: Collection<String>) {
        for (token in tokens) unregister(token)
    }
}