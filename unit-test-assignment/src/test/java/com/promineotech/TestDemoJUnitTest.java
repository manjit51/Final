package com.promineotech;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() ->
				testDemo.addPositive(a,b))
					.isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	private static Stream<Arguments> argumentsForAddPositive(){
		Stream<Arguments> s = Stream.of(
			arguments(2,4,6,false),
			arguments(5,-5,0,true),
			arguments(-10,-20,-30,true),
			arguments(5,15,20,false)
			
			);
		return s;
	}
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4,5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40,50)).isEqualTo(90);
		assertThat(testDemo.addPositive(100,1)).isEqualTo(101);
	}
	
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForMultiplyPositive")
	void assertThatTwoPositiveNumbersAreMultipliedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.multiplyPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() ->
				testDemo.multiplyPositive(a,b))
					.isInstanceOf(IllegalArgumentException.class);
		}
	}
	private static Stream<Arguments> argumentsForMultiplyPositive(){
		Stream<Arguments> s = Stream.of(
			arguments(5,5,25,false),
			arguments(5,-5,-25,true)
			
		);
		return s;
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}
}
