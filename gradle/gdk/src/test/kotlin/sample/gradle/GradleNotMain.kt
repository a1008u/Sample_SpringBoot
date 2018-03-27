package sample.gradle

import junit.framework.Assert.assertEquals
import org.junit.Test


class GradleMainTest {

    @Test
    fun test1() {
        assertEquals("Hello, kotlin, world!", word())
    }

}
