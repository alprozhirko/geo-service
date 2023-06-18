package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {
    LocalizationServiceImpl localizationServiceImpl;

    @BeforeEach
    public void beforeEachTest() {
        localizationServiceImpl = new LocalizationServiceImpl();
    }

    @AfterEach
    public void afterEachTest() {
        localizationServiceImpl = null;
    }

    @ParameterizedTest
    @MethodSource("localeParams")   //      arrange
    public void locale(String expected, Country country) {
//      act
        String result = localizationServiceImpl.locale(country);
//      assert
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> localeParams() {
        return Stream.of(

                Arguments.of("Добро пожаловать", Country.RUSSIA),
                Arguments.of("Welcome", Country.USA),
                Arguments.of("Welcome", Country.BRAZIL),
                Arguments.of("Welcome", Country.GERMANY)
        );
    }
}