package com.mark.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferShortLeADelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readShortLeA().toInt()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}