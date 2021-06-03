package com.test.mypincaculator

import com.test.mypincaculator.extension.hexStringToByteArray
import com.test.mypincaculator.extension.toHex
import org.junit.Test

class HexStringAndByteArrayUnitTest {
    @Test
    fun hexStringTest1() {
        assert(byteArrayOf(0x12, 0x34).toHex() == "1234")
    }

    @Test
    fun hexStringTest2() {
        assert(byteArrayOf(0x1F, 0x3D, 0x70).toHex() == "1F3D70")
    }

    @Test
    fun hexByteArrayTest1() {
        assert("1234".hexStringToByteArray().contentEquals(byteArrayOf(0x12, 0x34)))
    }

    @Test
    fun hexByteArrayTest2() {
        assert("1F3D70".hexStringToByteArray().contentEquals(byteArrayOf(0x1F, 0x3D, 0x70)))
    }
}