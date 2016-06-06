// FootprintTextForPCB.java v1.1
// Copyright (C) 2015 Erich S. Heinzle, a1039181@gmail.com

//    see LICENSE-gpl-v2.txt for software license
//    see README.txt
//    
//    This program is free software; you can redistribute it and/or
//    modify it under the terms of the GNU General Public License
//    as published by the Free Software Foundation; either version 2
//    of the License, or (at your option) any later version.
//    
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//    
//    You should have received a copy of the GNU General Public License
//    along with this program; if not, write to the Free Software
//    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
//    
//    FootprintTextForPCB Copyright (C) 2015 Erich S. Heinzle a1039181@gmail.com

//
//  A utility for turning text strings into silkscreen line elements which can
//  be added to footprints for labelling purposes.
//  v1.1 of the utility uses the free Hershey Sans 1 Stroke Font and outputs
//  0.01mil (imperial, square bracketed) units. 
//


import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FootprintTextForPCB {

  public static void main (String [] args) throws IOException {

    String workingText = "demonstration1234567890";
    float magnificationRatio = 1.0f;
    double angle = 0;

    // this is the class which contains the font definition
    // and methods to generate gEDA PCB layout compatible 
    // line elements to render the text
    PCBFontWrangler font = new PCBFontWrangler();

    //font.generateNewArray();

    // with the preliminaries out of the way we check
    // if the user has shared any ones and zeroes with us

    if (args.length != 0) {
      for (int index = 0; index < args.length; index ++) {
        if (args[index].startsWith("-t") && ((index + 1) < args.length)) {
          workingText = args[index + 1];  // text to be rendered
          index++;
        }
        else if (args[index].startsWith("-m") && ((index + 1) < args.length)) {
          magnificationRatio = Float.parseFloat(args[index + 1]); 
          index++;  // magnification ratio to be used
        }
        else if (args[index].startsWith("-a") && ((index + 1) < args.length)) {
          angle = Math.PI*((Integer.parseInt(args[index + 1]))/1800.0);
          index++; //magnification angle at which to render, CCW is positive
        } // in decidegrees, i.e. 1800 is used for 180 degrees
        else if (args[index].startsWith("-c")) {
          font.cyrillicMode();
        }
        else if (args[index].startsWith("-g")) {
          font.greekMode();
        }
        else {
          printUsage();
          System.exit(0);
        }        
      }
    }

    if (workingText.contains("U+")) {

    }

    String output = "Element[\"\" \"" 
        + workingText 
        + "\" \"\" \"\" 0 0 0 -4000 0 100 \"\"]\n(";

    if (workingText.contains("U+")) {
      ArrayList<Integer> unicode
          = stringToUnicode(workingText, false); 

      //testing
      unicode = new ArrayList<Integer>();
      for (int i = 32; i < 127; i++) {
        unicode.add(i);
      }
      //testing

      output = output
          + font.renderString(unicode,0,0,angle,magnificationRatio)
          + ")\n";
    } else {
      output = output
          + font.renderString(workingText,0,0,angle,magnificationRatio)
          + ")\n"; 
    }


    
    String filename = workingText.replaceAll("[^a-zA-Z0-9-]", "_");

    if (magnificationRatio != 1.0) {
      filename = filename + "-" + magnificationRatio + "x.fp";
    }
    else {
      filename = filename + ".fp";
    }

    PrintWriter Footprint = new PrintWriter(filename);
    Footprint.println(output);
    Footprint.close();
    
    System.out.println("Writing \"" 
                       + workingText 
                       + "\" as silkscreen elements in: " 
                       + filename );

  }

  public static void printUsage() {
    System.out.println("\nUsage: \n\n    java FootprintTextForPCB -t \"Text for conversion to .fp\" -m X.XXXX -a YYYY\n");
    System.out.println("    \"Text for conversion to .fp\" is ASCII text, which can include spaces,");
    System.out.println("    and X.XXXX is an optional magnification ratio; default = 1.0)\n");
    System.out.println("    and YYYY is an optional angulation of the text, counterclockwise positive, in deci-degrees\n"); 
    System.out.println("    i.e. 450 is 45 degrees counterclockwise, 1800 is upside down, 180 degrees\n");
    System.out.println("    If run without any command line arguments, a demonstration footprint file")
;
    System.out.println("    called demonstration1234567890.fp, will be generated\n"); 
  }

static int hexadecimalToInteger(char textChar, int power) {
    int hexVal = 0;
    switch (textChar) {
    case '0':
        break;
    case '1':
        hexVal += 1*Math.pow(16,power);
        break;
    case '2':
        hexVal += 2*Math.pow(16,power);
        break;
    case '3':
        hexVal += 3*Math.pow(16,power);
        break;
    case '4':
        hexVal += 4*Math.pow(16,power);
        break;
    case '5':
        hexVal += 5*Math.pow(16,power);
        break;
    case '6':
        hexVal += 6*Math.pow(16,power);
        break;
    case '7':
        hexVal += 7*Math.pow(16,power);
        break;
    case '8':
        hexVal += 8*Math.pow(16,power);
        break;
    case '9':
        hexVal += 9*Math.pow(16,power);
        break;
    case 'A':
        hexVal += 10*Math.pow(16,power);
        break;
    case 'a':
        hexVal += 10*Math.pow(16,power);
        break;
    case 'B':
        hexVal += 11*Math.pow(16,power);
        break;
    case 'b':
        hexVal += 11*Math.pow(16,power);
        break;
    case 'C':
        hexVal += 12*Math.pow(16,power);
        break;
    case 'c':
        hexVal += 12*Math.pow(16,power);
        break;
    case 'D':
        hexVal += 13*Math.pow(16,power);
        break;
    case 'd':
        hexVal += 13*Math.pow(16,power);
        break;
    case 'E':
        hexVal += 14*Math.pow(16,power);
        break;
    case 'e':
        hexVal += 14*Math.pow(16,power);
        break;
    case 'F':
        hexVal += 15*Math.pow(16,power);
        break;
    case 'f':
        hexVal += 15*Math.pow(16,power);
        break;
    default:
        hexVal = 0;
    }
    //System.out.println("Hexadecimal: " + textChar 
    //                   + " ->Integer: " + hexVal);
    return hexVal;
}

static int unicodeToInteger(String text) {
  boolean verbose = false;
  int hexVal = 0;
  //System.out.println("About to process unicode text: " + text);
  for (int textChar = 0; 
       textChar < (text.length()-2); // skip the "U+" start
       textChar++) {
    if (verbose) {
      System.out.println("Processing: " 
                         + text.charAt(text.length()-textChar-1));
    }
    char c;
    c = text.charAt(text.length()-textChar-1);
    hexVal += hexadecimalToInteger(c, textChar);
  }
  return hexVal;
}

static ArrayList<Integer> stringToUnicode(String textToParse,
                      boolean extraSpaces ) {
    String tempString = textToParse;
    String tempString2;
    boolean verbose = false;
    ArrayList<Integer> glyphsToRender = new ArrayList<Integer>();
    while (tempString.length() > 0) {
        if ((tempString.length() < 4) ||
            (tempString.length() == 5)) {
            for (int index = 0; index < tempString.length(); index++) {
                glyphsToRender.add((int)tempString.charAt(index));
                if (verbose) {
                  System.out.println("Processing: "
                                     + tempString.charAt(index));
                }
                if (extraSpaces) {
                  glyphsToRender.add(32); // add space
                }
            }
            tempString = "";
            if (verbose) {
                System.out.println("textToParse length: "
                                   + tempString.length());
            }
        } else if (tempString.startsWith("U+")
                   && ((tempString.length() == 4)
                       || (tempString.length() == 6))) {
          glyphsToRender.add(unicodeToInteger(tempString));
          tempString = "";
          if (verbose) {
            System.out.println("textToParse length: "
                               + tempString.length());
          }
        } else if (tempString.startsWith("U+")
                   && (tempString.length() > 5)
                   && (tempString.substring(4,6).equals("U+"))) {
            tempString2 = tempString.substring(0,4);
            tempString = tempString.substring(4);
            if (verbose) {
                System.out.println("textToParse length: "
                                   + tempString.length());
            }
            glyphsToRender.add(unicodeToInteger(tempString2));
        } else if (tempString.startsWith("U+")
                   && (tempString.length() > 7)
                   && (tempString.substring(6,8).equals("U+"))) {
            tempString2 = tempString.substring(0,6);
            tempString = tempString.substring(6);
            //tempString = tempString.substr(6);
            glyphsToRender.add(unicodeToInteger(tempString2));
        } else if (tempString.startsWith("U+")
                   && (tempString.length() > 9)
                   && (tempString.substring(8,10).startsWith("U+"))) {
            tempString2 = tempString.substring(0,8);
            //tempString = tempString.substr(8);
            tempString = tempString.substring(8);
            if (verbose) {
              System.out.println("textToParse length: "
                                 + tempString.length());
            }
            glyphsToRender.add(unicodeToInteger(tempString2));
        } else if (tempString.startsWith("U+")
                   && (tempString.length() > 7)) {
            tempString2 = tempString.substring(0,8);
            //tempString = tempString.substr(8);
            tempString = tempString.substring(8);
            if (verbose) {
              System.out.println("textToParse length: "
                                 + tempString.length());
            }
            glyphsToRender.add(unicodeToInteger(tempString2));
        } else {
            glyphsToRender.add((int)tempString.charAt(0));
            if (extraSpaces) {
              glyphsToRender.add(32); // add space
            }
            //tempString = tempString.substr(1);
            tempString = tempString.substring(1);
            if (verbose) {
              System.out.println("TempString now: "
                                 + tempString);
            }
        }
     }
    if (extraSpaces) {
      glyphsToRender.add(32); // add space
    }
    if (verbose) {
      System.out.println("Code vector size: " 
                         + glyphsToRender.size());
      for (int index = 0; index < glyphsToRender.size(); index++) {
        System.out.println("ASCII: "
                           + glyphsToRender.get(index));
      }
    }
    return glyphsToRender;
}



}
