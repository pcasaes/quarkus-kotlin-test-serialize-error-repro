package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@QuarkusTest
class MyObjectTest {

    static MyObject createMyObject(String someValue) {
        MyObject myObject = new MyObject();
        myObject.someValue = someValue;
        return myObject;
    }

    static Stream<Arguments> testParameters() {
        return Stream.of(
                Arguments.of("does not work", Arrays.asList(createMyObject("does not work"), createMyObject("does not work"))),
                Arguments.of("works", List.of(createMyObject("works"), createMyObject("works")))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testParameters")
    @DisplayName("Test Some Value")
    void testSomeValue(String expectedValue, List<MyObject> myObjects) {
        myObjects.forEach(myObject ->
                Assertions.assertEquals(expectedValue, myObject.someValue));
    }


}
