package Part2;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * NOTE THAT THIS FILE WILL NOT COMPILE UNTIL YOU HAVE COPIED OVER YOUR
 * EMISSION, COUNTRY, AND SECTOR CLASSES TO THE part2 DIRECTORY
 */
public class PartTwoTestCases {

    /**
     * Tests the implementation of the Emission class.
     *
     * TO-DO: Examine this test case to know what you should name your public methods.
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testEmissionImplSpecifics() throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList("getCO2", "getN2O", "getCH4");

        final List<Class> expectedMethodReturns = Arrays.asList(double.class, double.class, double.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[0], new Class[0]);

        verifyImplSpecifics(Emission.class, expectedMethodNames, expectedMethodReturns,
                expectedMethodParameters);
    }

    /**
     * Tests the part2 implementation of the Country class.
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testCountryImplSpecifics() throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList("getName", "getEmissions", "getYearWithHighestEmissions");

        final List<Class> expectedMethodReturns = Arrays.asList(String.class, Map.class, int.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[0], new Class[0]);

        verifyImplSpecifics(Country.class, expectedMethodNames, expectedMethodReturns,
                expectedMethodParameters);
    }

    /**
     * Tests the part2 implementation of the Sector class.
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testSectorImplSpecifics() throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList("getName", "getEmissions", "getYearWithHighestEmissions");

        final List<Class> expectedMethodReturns = Arrays.asList(String.class, Map.class, int.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[0], new Class[0]);

        verifyImplSpecifics(Sector.class, expectedMethodNames, expectedMethodReturns,
                expectedMethodParameters);
    }

    private static void verifyImplSpecifics(final Class<?> clazz, final List<String> expectedMethodNames,
                                            final List<Class> expectedMethodReturns, final List<Class[]> expectedMethodParameters)
            throws NoSuchMethodException {
        assertEquals(0, clazz.getFields().length, "Unexpected number of public fields");

        final List<Method> publicMethods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers())).collect(Collectors.toList());

        assertEquals(expectedMethodNames.size(), publicMethods.size(),
                "Unexpected number of public methods");

        assertTrue(expectedMethodNames.size() == expectedMethodReturns.size(),
                "Invalid test configuration");
        assertTrue(expectedMethodNames.size() == expectedMethodParameters.size(),
                "Invalid test configuration");

        for (int i = 0; i < expectedMethodNames.size(); i++) {
            Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i), expectedMethodParameters.get(i));
            assertEquals(expectedMethodReturns.get(i), method.getReturnType());
        }
    }

    @Test
    public void testYearWithHighestEmissionsSector(){
        Map<Integer, Double> emissions = new HashMap<>();
        emissions.put(3211, 113.0);
        emissions.put(122, 3213.0);
        emissions.put(4324, 20_441.0);
        emissions.put(1994, 3321.0);
        emissions.put(2023, 222_737.0);

        Sector mySector = new Sector("sector", emissions);
        assertEquals(2023, mySector.getYearWithHighestEmissions());
    }

    @Test
    public void testYearWithHighestEmissionsCountry() {
        Map<Integer, Emission> emissions = new HashMap<>();
        emissions.put(500, new Emission(940.0,241.0,4321.0));
        emissions.put(2, new Emission(443245.0,32112.0,32112.0));
        emissions.put(1, new Emission(22.0,228761.0,5.0));
        emissions.put(333,new Emission(7213.0,24212.0,16451.0));
        emissions.put(666, new Emission(555_000_000.0,2542.0,111.0));

        Country myCountry = new Country("country", emissions);

        assertEquals(666,myCountry.getYearWithHighestEmissions());
    }




}
