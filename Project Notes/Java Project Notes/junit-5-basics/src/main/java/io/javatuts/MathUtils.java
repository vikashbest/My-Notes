package io.javatuts;

public class MathUtils {

	public int add(int a, int b) {
		return a+b;
	}
	public int subtract(int a, int b) {
		return a-b;
	}
	public int multiply(int a, int b) {
		return a*b;
	}
	public int divide(int a, int b) {
		return a/b;
	}
	
	public double computeCircleArea(double radius) {
		return Math.PI*radius*radius;
	}
	
	public double computeCircleParameter(double radius) {
		return 2*Math.PI*radius;
	}
}
