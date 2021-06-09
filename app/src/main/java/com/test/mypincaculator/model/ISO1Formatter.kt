package com.test.mypincaculator.model

import kotlin.experimental.or
import kotlin.experimental.xor

class ISO1Formatter : Formatter() {

    companion object {
        private const val HEADER: Byte = 0x01
        private const val DEFAULT_VALUE: Byte = 0xFF.toByte()
    }

    override fun format(pin: String, pan: String): ByteArray {
        val result = ByteArray(LENGTH)
        val truncatedPan = getTruncatedPan(pan)
        val pinArray = pin.map { it.toString().toByte() }.toByteArray()
        result[0] = setHiNibbleValue(HEADER) or setLowNibbleValue(pinArray.size.toByte())
        for (i in 1 until result.lastIndex) {
            val index: Int = (i - 1) * 2
            val hiNibbleValue = if (index <= pin.lastIndex) pinArray[index] else DEFAULT_VALUE
            val loNibbleValue =
                if ((index + 1) <= pin.lastIndex) pinArray[index + 1] else DEFAULT_VALUE
            result[i] = setHiNibbleValue(hiNibbleValue) or setLowNibbleValue(loNibbleValue)
        }
        for (i in 0 until result.lastIndex) {
            result[i] = result[i] xor truncatedPan[i]
        }
        return result
    }
}