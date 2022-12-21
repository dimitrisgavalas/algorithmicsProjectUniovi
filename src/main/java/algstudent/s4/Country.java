package algstudent.s4;

import java.util.ArrayList;

public class Country {

	private ArrayList<Country> frontier;
	private String color;
	private String name;

	public Country(String name) {

		this.name = name;
		this.frontier = new ArrayList<Country>();
	}

	public Country(String name, ArrayList<Country> countries) {

		this.name = name;
		if (countries != null)
			this.frontier = countries;
		else
			this.frontier = new ArrayList<Country>();
	}

	public ArrayList<Country> getFrontiers() {
		return frontier;
	}

	public void setFrontiers(ArrayList<Country> frontier) {
		this.frontier = frontier;
	}

	public void addFrontier(Country country) {
		if (!frontier.contains(country))
			frontier.add(country);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		String aux = "[Country = " + getName();
		aux += " - Color = " + getColor();
		aux += " - Frontiers with = ";
		for (var country : frontier)
			aux += country.getName() + ", ";
		aux += " ]";
		return aux;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
