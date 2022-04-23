package io.javatuts;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils")
class MathUtilsTest {
	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to be run before all.");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
	}
	
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up...!");
	}
	
	@AfterAll
	static void finalCleanup() {
		System.out.println("This is final cleanup after all...!");
	}
	
	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class TestAdd{
		@Test
		@DisplayName("when adding two positive numbers")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1,1), "should return correct sum");
		}
		
		@Test
		@DisplayName("when adding two negative numbers")
		void testAddNegative() {
			assertEquals(-3, mathUtils.add(-1,-2), "should return correct sum");
		}
	}
	
//	@Test
	@DisplayName("test subtract method")
	@RepeatedTest(3)
	void testSubtract(RepetitionInfo repetitionInfo) {
		if(repetitionInfo.getCurrentRepetition()==2) {
			System.out.println("Currently in second repeption of testSubtract method");
		}
		int expected = 5;
		int actual = mathUtils.subtract(7, 2);
		assertEquals(expected, actual, () -> "Should return the correct difference, expected "+ expected+" but got "+actual);
	}
	
	
	@Test
	@DisplayName("Test multiply method")
	@Tag("Math")
	void testMultiply() {
//		assertEquals(4, mathUtils.multiply(2, 2), "Should return the right product");
		assertAll(
			() -> assertEquals(4, mathUtils.multiply(2, 2)),
			() -> assertEquals(6, mathUtils.multiply(-2, -3)),
			() -> assertEquals(-18, mathUtils.multiply(2, -9),"should be equal"),
			() -> assertNotEquals(16, mathUtils.multiply(4, 6),"Product Should not be equal")	// This is assertNotEquals
				);
	}
	
	@Test
	@DisplayName("Test circle parameter method")
	@Tag("Circle")
	void testcomputeCirclePerimeter() {
		System.out.println("Runnning "+testInfo.getDisplayName()+ " with tags "+testInfo.getTags());
		assertEquals(62.83185307179586, mathUtils.computeCircleParameter(10), () -> "should return correct circle parameter");
	}
	
	@Test
	@EnabledOnOs(OS.MAC)
	void testDivide() {
//		MathUtils mathUtils = new MathUtils();
		assertThrows(ArithmeticException.class,() -> mathUtils.divide(1, 0), "Divide by zero should throw");
	}
	
	@Test
	@Tag("Circle")
	void testComputeCircleArea() {
//		MathUtils mathUtils = new MathUtils();
		testReporter.publishEntry("Currently running "+testInfo.getDisplayName()+ " with tags "+testInfo.getTags());
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10),"Should return right Circle Area");
	}
		
	@DisplayName("TDD method...Should not run")
	@Disabled
	@Test
	void testDisabled() {
		fail("This test should be disabled.");
	}
	
	@Test
	@DisplayName("Testing is Server Running")
	void isServerRunning() {
		boolean isServerRunning = false;
		assumeTrue(isServerRunning);					// Note it's assumeTrue not assertTrue
		System.out.println("The Server is Up and Running");
	}
}
