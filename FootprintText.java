// FootprintText.java v1.0
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
//    FootprintText Copyright (C) 2015 Erich S. Heinzle a1039181@gmail.com

//
//  A utility for turning text strings into silkscreen line elements which can
//  be added to footprints for labelling purposes.
//  v1.0 of the utility uses the free Hershey Sans 1 Stroke Font and outputs
//  0.01mil (imperial, square bracketed) units. 
//

import java.io.*;
//import java.util.PrintWriter;

public class FootprintText extends FootprintElementArchetype {

  // using chars 32 -> 126 = 95 in total

  String workingText = "DefaultText-SpaceForRent";
              // default text to convert if nothing supplied
  double magnificationRatio = 1.0;      // default value of 1.0 yields default sized font text in gEDA PCB

  long yLayoutOffsetNm = 0;    // these are used to position the text relative to the module or layout centroid
  long xLayoutOffsetNm = 0;

  boolean metricFlag = false;  // not really needed for text, if we output silk strokes in decimil format regardless

  String kicadTextDescriptor = "";
  long kicadTextHeightNm = 0;
  long kicadTextWidthNm = 1320000;
  //
  // 1786000 -> ?70.37mil -> 67.3 actual
  // default value of 1.829mm for testing = 70.6mil, or 
  // 1327000 = 83.7mil
  public void FootprintText(long offsetX, long offsetY) {
    // (x,y) position relative to footprint or layout centroid
    xLayoutOffsetNm = offsetX;
    yLayoutOffsetNm = offsetY;
  }


  public String generateGEDAelement(long offsetX, long offsetY, float magnificationRatio) {

    xLayoutOffsetNm = offsetX;
    // x position relative to footprint or layout centroid
    yLayoutOffsetNm = offsetY;
    // y position relative to footprint or layout centroid

    double footprintMagnificationRatio = magnificationRatio;

    HersheySansFontClass hershey = new HersheySansFontClass();

    long xOffsetCentimil = 0;            
    // this is used to increment the x position of
    // the text character by character
    long yOffsetCentimil = 0;           
    // this is used to increment/set the y position
    // of the text character by character

    long textCentreOffsetXcentimil = 0;
    // Kicad specifies the x,y location of the CENTRE of the text

    hershey.setKicadMWidthNm(kicadTextWidthNm);

    long textCentreOffsetYcentimil = hershey.yCentredOffset();

    long textWidthCentimil = 0;
    String displayedElements  = "";

    // we need to figure out the width of the rendered string
    // we start by summing the widths of the individuals chars
    // remembering that hershey.width returns ((maxX-minX) + kerning)
    for (int i = 0; i < workingText.length(); i++) {
      textWidthCentimil += hershey.width((int)(workingText.charAt(i)));
    }
    // now we subtract the final kerning, add thickness, and 
    // account for the + 1000 offset of a gEDA font symbol
    // along the x-axis 
    textWidthCentimil = textWidthCentimil -
        hershey.kerning(workingText.charAt(workingText.length()-1))
        // lopped off end kerning to get final width
        + (long)(1000*hershey.fontMagnificationRatio)
        // accounted for final char being an extra +1000 along x axis
        + hershey.thickness((int)(workingText.charAt(workingText.length()-1))); 
        // and accounted for thickness of drawn lines

    // we now divide the string width by two to get the offset
    // needed to centre the rendered string
    textCentreOffsetXcentimil = (long)(textWidthCentimil / 2.0);

    // we now apply the overall x offset for the 
    // entire text string, relative to the footprint/module
    xOffsetCentimil = (long)((xLayoutOffsetNm/254 - textCentreOffsetXcentimil) * footprintMagnificationRatio);

    // we now apply the overall y offset for the entire
    // text string, relative to the footprint/module
    //    yOffsetCentimil = (long)((hershey.yCentredOffset() + yLayoutOffsetNm/254) * footprintMagnificationRatio);
    // we can call the auto - y - centring method of
    // the hersheyFontclass and let it do the work

    yOffsetCentimil = (long)((yLayoutOffsetNm/254) * footprintMagnificationRatio);
    
    for (int i = 0; i < workingText.length(); i++){
      displayedElements = displayedElements +
          hershey.drawYCentredChar((int)(workingText.charAt(i)), xOffsetCentimil, yOffsetCentimil, footprintMagnificationRatio, true);
      xOffsetCentimil += 
          (long)(hershey.width((int)(workingText.charAt(i)))*footprintMagnificationRatio);
    }
    return displayedElements;
  }

  public void populateElement(String moduleDefinition, boolean metric) {
    kicadTextDescriptor = moduleDefinition;
        // for testing
    workingText = kicadTextDescriptor; //"Sample Text";
  }

}
