package io.kola.events

class EventWithParam<out T>(private val eventHandler: EventHandlerWithParam<T>) {
    operator fun set(key: String, handler: (T) -> Unit) = eventHandler.set(key, handler)
    infix fun register(handler: (T) -> Unit) = eventHandler.register(handler)
    infix fun unregister(token: String) = eventHandler.unregister(token)
    operator fun plusAssign(handler: (T) -> Unit) = eventHandler.plusAssign(handler)
    operator fun minusAssign(key: String) = eventHandler.minusAssign(key)
    fun clear() = eventHandler.clear()
}