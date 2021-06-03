package com.test.mypincaculator.model

import com.test.mypincaculator.extension.hexStringToByteArray

abstract class Formatter {
    companion object {
        const val DEFAULT_PAN = "1111222233334444"
        const val LENGTH = 8
    }

    abstract fun format(pin: String, pan: String): ByteArray

    protected fun setHiNibbleValue(value: Byte): Byte = (0xF0 and (value.toInt() shl 4)).toByte()

    protected fun setLowNibbleValue(value: Byte): Byte = (0x0F and value.toInt()).toByte()

    protected fun getTruncatedPan(value: String): ByteArray {
        val subString = value.substring(value.length - 12 - 1, value.length - 1)
        return ("0000$subString").hexStringToByteArray()
    }
}