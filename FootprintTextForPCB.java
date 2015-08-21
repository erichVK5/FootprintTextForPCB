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

public class FootprintTextForPCB {

  public static void main (String [] args) throws IOException {

    FootprintText tester = new FootprintText();
    String workingText = "demonstration1234567890";
    float magnificationRatio = 1.0f;

    // with the preliminaries out of the way we check
    // if the user has shared any ones and zeroes with us

    if (args.length != 0) {
      for (int index = 0; index < args.length; index ++) {
        if (args[index].startsWith("-t") && ((index + 1) < args.length)) {
          workingText = args[index + 1];
          index++;
        }
        else if (args[index].startsWith("-m") && ((index + 1) < args.length)) {
          magnificationRatio = Float.parseFloat(args[index + 1]);
          index++;
        }
        else {
          printUsage();
          System.exit(0);
        }        
      }
    }

    // we tell the FootprintText calss what to display
    tester.populateElement(workingText, true);

    String output = "Element[\"\" \"" 
        + workingText 
        + "\" \"\" \"\" 0 0 0 -4000 0 100 \"\"]\n("
        + tester.generateGEDAelement(0,0,magnificationRatio)
        + ")\n";
    
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
    System.out.println("\nUsage: \n\n    java FootprintTextForPCB -t \"my Text For Conversion To Silkscreen Stroke Elements\" -m X.XXXX\n");
    System.out.println("    \"my Text For Conversion To Silkscreen Stroke Elements\" is ASCII text, which can include spaces,");
    System.out.println("    and X.XXXX is an optional magnification ratio; default = 1.0)\n");
    System.out.println("    If run without any command line arguments, a demonstration footprint file")
;
    System.out.println("    called demonstration1234567890.fp, will be generated\n"); 
  }

}
