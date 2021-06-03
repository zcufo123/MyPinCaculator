package com.test.mypincaculator

import com.test.mypincaculator.extension.toHex
import com.test.mypincaculator.model.Formatter
import com.test.mypincaculator.model.ISO3Formatter
import org.junit.Test

class FormatterUnitTest {

    private val randomPAN = "5871643145689532"
    private val formatterISO3 = ISO3Formatter()

    @Test
    fun formatterISO3Test1() {
        val result = formatterISO3.format("1234", Formatter.DEFAULT_PAN).toHex()
        assert(result.startsWith("341226", true))
    }

    @Test
    fun formatterISO3Test2() {
        val result = formatterISO3.format("4351", Formatter.DEFAULT_PAN).toHex()
        assert(result.startsWith("344343", true))
    }

    @Test
    fun formatterISO3Test3() {
        val result = formatterISO3.format("123456789012", Formatter.DEFAULT_PAN).toHex()
        assert(result.startsWith("3C1226745BA326", true))
    }

    @Test
    fun formatterISO3Test4() {
        val result = formatterISO3.format("597531687167", Formatter.DEFAULT_PAN).toHex()
        assert(result.startsWith("3C5967134B4253", true))
    }

    @Test
    fun formatterISO3Test5() {
        val result = formatterISO3.format("1234", randomPAN).toHex()
        assert(result.startsWith("341222", true))
    }

    @Test
    fun formatterISO3Test6() {
        val result = formatterISO3.format("4351", randomPAN).toHex()
        assert(result.startsWith("344347", true))
    }

    @Test
    fun formatterISO3Test7() {
        val result = formatterISO3.format("123456789012", randomPAN).toHex()
        assert(result.startsWith("3C1222156CC69B", true))
    }

    @Test
    fun formatterISO3Test8() {
        val result = formatterISO3.format("597531687167", randomPAN).toHex()
        assert(result.startsWith("3C5963727C27EE", true))
    }

}