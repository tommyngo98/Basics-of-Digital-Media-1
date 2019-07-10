import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

public class GLDM_1 implements PlugIn {

	final static String[] choices = { "Schwarzes Bild", "Gelbes Bild", "Schwarz/Weiss Verlauf",
			"Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf", "Französische Fahne", "Bahamische Fahne",
			"Japanische Fahne", "Japanische Fahne mit weichen Kanten", "Gelb zu Blau", "Rot-Grün", "Schwarze Streifen",
			"Weiß-Gelb-Rot" };

	private String choice;

	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen
		ij.exitWhenQuitting(true);

		GLDM_1 imageGeneration = new GLDM_1();
		imageGeneration.run("");
	}

	public void run(String arg) {

		int width = 566; // Breite
		int height = 400; // Hoehe

		// RGB-Bild erzeugen
		ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, height, 1, NewImage.FILL_BLACK);
		ImageProcessor ip = imagePlus.getProcessor();

		// Arrays fuer den Zugriff auf die Pixelwerte
		int[] pixels = (int[]) ip.getPixels();

		dialog();

		////////////////////////////////////////////////////////////////
		// Hier bitte Ihre Aenderungen / Erweiterungen

		if (choice.equals("Schwarzes Bild")) {
			generateBlack(width, height, pixels);
		}

		if (choice.equals("Gelbes Bild")) {
			generateYellow(width, height, pixels);
		}

		if (choice.equals("Französische Fahne")) {
			generateFrenchFlag(width, height, pixels);
		}

		if (choice.equals("Schwarz/Weiss Verlauf")) {
			generateBlackToWhite(width, height, pixels);
		}

		if (choice.equals("Bahamische Fahne")) {
			generateBahamasFlag(width, height, pixels);
		}

		if (choice.equals("Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf")) {
			generateBlackBlueRed(width, height, pixels);
		}

		if (choice.equals("Japanische Fahne")) {
			generateJapanFlag(width, height, pixels);
		}

		if (choice.equals("Gelb zu Blau")) {
			generateYellowToBlue(width, height, pixels);
		}

		if (choice.equals("Rot-Grün")) {
			generateRedToGreen(width, height, pixels);
		}

		if (choice.equals("Schwarze Streifen")) {
			generateBlackStripes(width, height, pixels);
		}

		if (choice.equals("Weiß-Gelb-Rot")) {
			generateWhiteYellowRed(width, height, pixels);
		}
		////////////////////////////////////////////////////////////////////

		// neues Bild anzeigen
		imagePlus.show();
		imagePlus.updateAndDraw();
	}

	private void generateBlack(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				// Schwarzes Bild
				int r = 0;
				int g = 0;
				int b = 0;
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void generateYellow(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				// Gelbes Bild
				int r = 255;
				int g = 255;
				int b = 0;

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void generateFrenchFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				// Blauer Anteil links
				if (x < width * 0.33) {
					int r = 0;
					int g = 0;
					int b = 255;

					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}
				// Weißer Anteil in der Mitte
				else if (x >= width * 0.33 && x <= width * 0.67) {
					int r = 255;
					int g = 255;
					int b = 255;

					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}
				// Roter Anteil rechts
				else if (x > width * 0.67) {
					int r = 255;
					int g = 0;
					int b = 0;

					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}
			}
		}
	}

	private void generateBlackToWhite(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				// Farbverlauf von links nach rechts, von schwarz nach weiß, da die Farbwerte
				// anfangs
				// alle auf 0 und am Ende alle auf 255.

				int r = x * 255 / width;
				int g = x * 255 / width;
				int b = x * 255 / width;
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void generateBahamasFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen

				// Türkiser Anteil oben und unten
				if ((y < height * 0.33) || (y > height * 0.67)) {
					int r = 0;
					int g = 170;
					int b = 210;

					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}

				// Gelber Anteil in der Mitte
				if ((y >= height * 0.33) && (y <= height * 0.67)) {
					int r = 240;
					int g = 240;
					int b = 50;

					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}

				// Schwarzes Dreieck links
				if ((x < y) && (x < (height - y))) {

					int r = 0;
					int g = 0;
					int b = 0;

					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}
			}
		}
	}

	private void generateBlackBlueRed(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen

				int r = x * 255 / width;
				int g = 0;
				int b = y * 255 / height;

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void generateJapanFlag(int width, int height, int[] pixels) {

		// Mittelpunktkoordinaten für den roten Punkt der Flagge
		int xM = width / 2;
		int yM = height / 2;
		int rad = 100;

		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen

				int deltaX = x - xM;
				int deltaY = y - yM;
				// Satz des Pythagoras
				int c = (int) Math.hypot(deltaX, deltaY);

				int r = 0;
				int g = 0;
				int b = 0;

				// Pixel im Radius (Roter Punkt)
				if (c < rad) {
					r = 255;
					g = 0;
					b = 0;
				}
				// Pixel außerhalb des Radius (Weißer Hintergrund)
				else {
					r = 255;
					g = 255;
					b = 255;
				}

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}

		}
	}

	private void generateYellowToBlue(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen

				int r = (width - x) * 255 / width;
				int g = (width - x) * 255 / width;
				int b = x * 255 / width;

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void generateRedToGreen(int width, int height, int[] pixels) {

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pos = y * width + x;

				int a = 128;

				int r = ((width - x) * 255) / width;
				int g = (y * 255) / height;
				int b = 0;

				pixels[pos] = (a << 24) | (r << 16) | (g << 8) | b;
			}
		}

	}

	private void generateBlackStripes(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				// Schwarzes Bild
				if (x % 10 == 0) {
					int r = 0;
					int g = 0;
					int b = 0;
					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				} else {
					int r = 255;
					int g = 255;
					int b = 255;
					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}
			}
		}
	}

	private void generateWhiteYellowRed(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x;
				int r = 255;

				int g = 255;
				if (x > width / 2) {
					g = (width - (x * 2 + 1)) * 255 / width;
				}

				int b = (width - x * 2) * 255 / width;
				if (b < 0) {
					b = 0;
				}

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void dialog() {
		// Dialog fuer Auswahl der Bilderzeugung
		GenericDialog gd = new GenericDialog("Bildart");

		gd.addChoice("Bildtyp", choices, choices[0]);

		gd.showDialog(); // generiere Eingabefenster

		choice = gd.getNextChoice(); // Auswahl uebernehmen

		if (gd.wasCanceled())
			System.exit(0);
	}
}
