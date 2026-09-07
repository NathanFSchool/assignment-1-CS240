import java.util.Scanner;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Hello User! Do you want to convert? \nType '1' for ASCII to Decimal,\n'2' for " +
                "a number-base converter supporting binary, decimal, octal, and hexadecimal,\n'3' for image to" +
                " pixel values or\n'4' for pixel values to an image: ");
        int z = 0;
        //This while loop ensures the user picks a function, while not stopping the program if they don't
        while (z != 1) {
            char choice = scnr.next().charAt(0);

            switch (choice) {
                case '1': z = 1;
                    asciiToDec(scnr);
                    break;
                case '2': z = 1;
                    terminal(scnr);
                    break;
                case '3': z = 1;
                System.out.println("Converting image to text file now!");
                    pixToText();
                    break;
                case '4': z = 1;
                System.out.println("Converting text to image file now!");
                    textToPix();
                    break;
                default:
                    System.out.print("Input Invalid. Please try again: ");
                    break;
            }
        }

    }

//method for task 1, ASCII decimal converter

    public static void asciiToDec(Scanner scnr){
        scnr.nextLine();
        System.out.print("Enter an ASCII string: "); //prompts user to write a string of ascii characters
        String name = scnr.nextLine();
        System.out.println("ASCII \t Decimal");
        //this loop goes through each character and parses it to its decimal value
        for(int i = 0; i < name.length(); i++){
            System.out.println(name.charAt(i) + " becomes " + (int) name.charAt(i));
        }
    }

//method for task 2, number base converter
    public static void terminal(Scanner scnr){
        //this prompts the user to pick which type of number they're inputting
        System.out.print("----------------\nChoose the type of the number input:\n2 for Binary\n10 for Decimal\n" +
                "8 for Octal\n16 for Hexadecimal\nBase: ");

        int base = scnr.nextInt();
        //if they input an incorrect type the program stops
        if (base != 2 && base != 8 &&
                base != 10 && base != 16) {
            System.out.println("Invalid base.");
            return;
        }
        //then they are prompted to enter their number
        System.out.print("Enter the number: ");
        String input = scnr.next();

        //this try-catch ensures that the number they entered is actually the type they specified
        try {

            int number = Integer.parseInt(input, base);

            // this ensures that only a decimal input can have a negative sign on the end
            if (base == 10 && number >= -128 && number <= 255) {
                number = number & 0xFF;
            }
            //if the number isn't within the bounds then it's not within 8 bits which we don't like!
            else if (number < 0 || number > 255) {
                System.out.println("The number must fit in 8 bits.");
                scnr.close();
                return;
            }

            // This interprets the number as a signed number
            int signedNumber;

            if (number >= 128) {
                signedNumber = number - 256;
            }
            else {
                signedNumber = number;
            }
            //these just parse the number into each respective type
            System.out.println("Here are your conversions:");
            System.out.println("Binary: " + Integer.toBinaryString(number));
            System.out.println("Decimal: " + number);
            System.out.println("Octal: " + Integer.toOctalString(number));
            System.out.println("Hexadecimal: " + Integer.toHexString(number).toUpperCase());
            //signed two's is only relevant IF they entered a signed binary number
            //in which case it'll display the decimal value as negative
            System.out.println("Signed two's-complement number: " + signedNumber);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid number for that base.");
        }
    }

//methods for task 3, image to text converter
    public static String convert(Color color) { //this method is for formatting the rbg for when
        int red = color.getRed();               //it gets printed in the file
        int green = color.getGreen();
        int blue = color.getBlue();

        return red + " " + green + " " + blue + " ";
    }
    public static void pixToText() throws IOException {
        //this sets up the inputs and outputs which are in the file directory
        PrintWriter outputFile = new PrintWriter("output.txt");
        BufferedImage imageFile = ImageIO.read(new File("./input.png"));
        int width = imageFile.getWidth();
        int height = imageFile.getHeight();
        //this helps format the output file in the way I decided which lists the width and height before any values
        outputFile.print(width + " " + height + "\n");
        //this iterates through each pixel in the image and takes note of their RGB values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(imageFile.getRGB(x, y));
                String convertedColor = convert(color);
                outputFile.print(convertedColor);
            }
            outputFile.println();
        }
        outputFile.close(); //mandatory file closure
    }

//method for task 4, text to image converter
public static void textToPix() throws IOException {
    Scanner keyboard = new Scanner(System.in);

    // This sets up the input from the text file
    File inputFile = new File("input.txt");
    Scanner fileReader = new Scanner(inputFile);

    //I specifically formatted things so the first two values in the file are the height and width
    //and this just grabs those for later use
    int width = fileReader.nextInt();
    int height = fileReader.nextInt();

    // This creates our blank canvas using the width and height
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Also using the height and width, we iterate through every pixel grabbing rgb values as we go
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            // again the format has red, then green, then blue on repeat and this reads that
            int red = fileReader.nextInt();
            int green = fileReader.nextInt();
            int blue = fileReader.nextInt();

            //Then this draws our image!
            Color pixelColor = new Color(red, green, blue);
            image.setRGB(x, y, pixelColor.getRGB());
        }
    }

    // BAM the image is saved
    File outputFile = new File("output.png");
    ImageIO.write(image, "png", outputFile);

    fileReader.close();
    keyboard.close();

    System.out.println("The image was saved as output.png.");
}

} //sacred holy program end
