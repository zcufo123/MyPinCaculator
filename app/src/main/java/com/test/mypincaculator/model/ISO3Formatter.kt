package com.test.mypincaculator.model

import kotlin.experimental.or
import kotlin.experimental.xor

class ISO3Formatter : Formatter() {
    companion object {
        private const val HEADER: Byte = 0x03
    }

    override fun format(pin: String, pan: String): ByteArray {
        val result = ByteArray(LENGTH)
        val truncatedPan = getTruncatedPan(pan)
        val pinArray = pin.map { it.toString().toByte() }.toByteArray()
        result[0] = setHiNibbleValue(HEADER) or setLowNibbleValue(pinArray.size.toByte())
        for (i in 1 until result.lastIndex) {
            val index: Int = (i - 1) * 2;
            val hiNibbleValue = if (index <= pin.lastIndex) pinArray[index] else RandomByte()
            val loNibbleValue = if ((index + 1) <= pin.lastIndex) pinArray[index + 1] else RandomByte()
            result[i] = setHiNibbleValue(hiNibbleValue) or setLowNibbleValue(loNibbleValue)
        }
        for (i in 0 until result.lastIndex) {
            result[i] = result[i] xor truncatedPan[i]
        }
        return result
    }

    private fun RandomByte(): Byte {
        return (10..15).random().toByte()
    }
}