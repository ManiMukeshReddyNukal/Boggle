package com.thebyte.boggle.ui.main

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*

import org.junit.Test

class Fragment1Test {

    @Test
    fun ifValidButtonPress() {
        val output = Fragment1().ifValidButtonPress(1,2)
        assert(output)
    }
}