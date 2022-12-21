package algstudent.s4;

import java.util.ArrayList;

public class MapColoring {
	private String[] colors;
	private Country[] countries;
	private static final String COLORS_PATH = "src/main/java/algstudent/s4/colors.txt";
	private static final String COUNTRIES_PATH = "src/main/java/algstudent/s4/borders.txt";

//	public MapColoring(String colorsPath, String countriesPath) {
//		setColors(Parser.loadColors(colorsPath));
//		setCountries(Parser.loadCountries(countriesPath));
//	}
	//calls setters
	public MapColoring() {
		setColors(Parser.loadColors(COLORS_PATH));
		setCountries(Parser.loadCountries(COUNTRIES_PATH));
	}
	
	//method for printing colors
	public void printColors() {
		System.out.println("Colors = ");
		for (String color : colors)
			System.out.print(color + ", ");
		System.out.println();
	}

	public Country[] getCountries() {
		return countries;
	}

	public void setCountries(Country[] countries) {
		this.countries = countries;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public void printCountries() {
		for (Country country : countries)
			System.out.println(country.toString());
	}

	public void greedyColoring() {
		
		ArrayList<String> used;

		for (int i = 0; i < countries.length; i++) {
					
			used = new ArrayList<String>();
			for (int j = 0; j < countries[i].getFrontiers().size(); j++) {
				if(countries[i].getFrontiers().get(j).getColor() != null && !countries[i].getFrontiers().get(j).getColor().equals(countries[i].getColor()))
					used.add(countries[i].getFrontiers().get(j).getColor());
			}
			setColor(used, countries[i]);
		}

	}

	private void setColor(ArrayList<String> used, Country country) {
		for(int i = 0;i<colors.length;i++) {
			if(!used.contains(colors[i])) {
				country.setColor(colors[i]);
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		MapColoring mc = new MapColoring();//constructor saves info from txt files into fields
		mc.printColors();// color and counties fields are printed
		mc.printCountries();
		
		mc.greedyColoring();
		System.out.println("\n\n---------------------------------------------\n\n");
		mc.printCountries();

	}

}
