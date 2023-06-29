package com.company.Personalmgmt.practise;

class Calculator {

	public double add(double a, double b) {

		double output = a + b;

		return output;
	}
}

public class B extends Calculator {

	public void gone() {

		System.out.println("Class B");
	}

	public static void main(String[] args) {

		B ob = new B();

		ob.gone();

		double response = ob.add(12.5, 13.6);

		System.out.println("Add : " + response);

	}

}
