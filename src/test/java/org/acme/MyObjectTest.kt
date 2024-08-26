package org.acme

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@QuarkusTest
class MyObjectTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("testParameters")
    @DisplayName("Test Some Value")
    fun testSomeValue(expectedValue: String, myObjects: List<MyObject>) {
        myObjects.forEach { myObject ->
            Assertions.assertEquals(expectedValue, myObject.someValue)
        }
    }

    companion object {

        @JvmStatic
        fun testParameters(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("test", listOf(createMyObject("test"), createMyObject("test"))),
                Arguments.of("test2", listOf(createMyObject("test2"), createMyObject("test2")))
            )
        }

        fun createMyObject(someValue: String): MyObject {
            val myObject = MyObject()
            myObject.someValue = someValue
            return myObject
        }
    }
}