package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTest {
    GeoServiceImpl geoServiceImpl;

    @BeforeEach
    public void beforeEachTest() {
        geoServiceImpl = new GeoServiceImpl();
    }

    @AfterEach
    public void afterEachTest() {
        geoServiceImpl = null;
    }

    @ParameterizedTest
    @MethodSource("byIpParam")   //      arrange
    public void byIp(Location expected, String ip) {
//      act
        Location result = geoServiceImpl.byIp(ip);
//      assert
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> byIpParam() {
        return Stream.of(
                Arguments.of(new Location(null, null, null, 0), GeoServiceImpl.LOCALHOST),
                Arguments.of(new Location("Moscow", Country.RUSSIA, "Lenina", 15), GeoServiceImpl.MOSCOW_IP),
                Arguments.of(new Location("New York", Country.USA, " 10th Avenue", 32), GeoServiceImpl.NEW_YORK_IP),
                Arguments.of(new Location("Moscow", Country.RUSSIA, null, 0), "172.0.00.00"),
                Arguments.of(new Location("New York", Country.USA, null, 0), "96.00.00.000"),
                Arguments.of(null, "45.xxxxxx")
        );
    }

    @Test
    public void byCoordinates() {
//      arrange
        double latitude = 55.7522200;
        double longitude = 37.6155600;
        Class<RuntimeException> expected = RuntimeException.class;
//      act
        Executable executable = () -> geoServiceImpl.byCoordinates(latitude, longitude);
//      assert
        Assertions.assertThrows(expected, executable);
    }
}