rary
/
README.md

Assignment 1: Converter and Pixel System
Description
This Java program provides four conversion tools using the command line interface:

1. Convert ASCII characters to their decimal values.

2. Convert numbers among binary, decimal, octal, and hexadecimal.

3. Convert a PNG image into a text file containing RGB pixel values.

4. Reconstruct a PNG image from a correctly formatted RGB text file.

Author
Nathan Fitzpatrick

Requirements
Java Development Kit (JDK) 8 or newer

A terminal or command prompt

input.png for the image-to-text option

input.txt for the text-to-image option

This program uses only standard Java libraries.

Files
Main.java — contains the complete program

README.md — explains how to compile and use the program

input.png — image read by option 3, when that option is used

input.txt — RGB data read by option 4, when that option is used

output.txt — created by option 3

output.png — created by option 4

The input files must be placed in the same directory from which the program is run. Existing output files with the same names may be overwritten.

Compiling and Running
Open a terminal in the project directory and compile the program:

javac Main.java
Then run it:

java Main
At the opening prompt, enter 1, 2, 3, or 4 to select a conversion tool.

Option 1: ASCII to Decimal
This option accepts a line of text and prints each character with its decimal character value.

Example:

Enter an ASCII string: Cat
ASCII    Decimal
C becomes 67
a becomes 97
t becomes 116
Option 2: Number-Base Converter
This option accepts a number in one of four bases and displays the corresponding binary, decimal, octal, and hexadecimal values.

Select the input base with one of these values:
Input type	Base selection	Example input
Binary	    2	              101
Octal	      8	              17
Decimal	    10	            25
Hexadecimal	16	            FF
The converter uses 8-bit values. It supports unsigned values from 0 through 255 and signed decimal values from -128 through 127. A negative decimal input is displayed using its 8-bit two's-complement representation.

Example:

Base: 10
Enter the number: 5
Here are your conversions:
Binary: 101
Decimal: 5
Octal: 5
Hexadecimal: 5
Signed two's-complement number: 5
Negative two's-complement example:

Base: 2
Enter the number: 11111111
Here are your conversions:
Binary: 11111111
Decimal: 255
Octal: 377
Hexadecimal: FF
Signed two's-complement number: -1
If the input contains digits that are invalid for the selected base, the program prints an error message.

Option 3: Image to Pixel Values
This option reads input.png and creates output.txt.

The first line of output.txt contains the image width followed by its height:

width height
The remaining lines contain the red, green, and blue values for every pixel. Pixels are processed from left to right and rows are processed from top to bottom.

Example for a two-pixel-wide, one-pixel-high image:

2 1
255 0 0 0 0 255
In this example, the first pixel is red and the second pixel is blue.

Option 4: Pixel Values to Image
This option reads RGB values from input.txt and creates output.png.

The input file must follow the same format produced by option 3:

The first two integers are the width and height.

Every pixel is represented by three integers in red, green, blue order.

Each RGB component must be between 0 and 255.

The file must provide exactly enough RGB groups to fill the specified image dimensions.

Example input.txt:

2 1
255 0 0 0 0 255
This produces a two-pixel-wide image containing one red pixel and one blue pixel.

Additional Information
The program performs one selected conversion and then exits.

The number-base converter is limited to 8-bit values.

Negative input is accepted only when decimal is selected as the input base.

Image conversion supports PNG files and RGB color values; transparency is not preserved.

Image and text file names are fixed as input.png, input.txt, output.txt, and output.png.

LICENSE

Licensed under the MIT license.
