package io.kola.events

class Event(private val eventHandler: EventHandler) {
    operator fun set(key: String, handler: () -> Unit) = eventHandler.set(key, handler)
    infix fun register(handler: () -> Unit) = eventHandler.register(handler)
    infix fun unregister(token: String) = eventHandler.unregister(token)
    operator fun plusAssign(handler: () -> Unit) = eventHandler.plusAssign(handler)
    operator fun minusAssign(key: String) = eventHandler.minusAssign(key)
    fun clear() = eventHandler.clear()
}