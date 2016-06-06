// PCBFontWrangler.java v1.0
// Copyright (C) 2016 Erich S. Heinzle, a1039181@gmail.com

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
//    PCBFontWrangler Copyright (C) 2016 Erich S. Heinzle a1039181@gmail.com

//
//  A font handling class for use with gEDA PCB for generating
//  silkscreen text which can be added to footprints for labelling
//  purposes.
//  The class implements the various free Hershey Fonts, and outputs
//  0.01mil (imperial, square bracketed) units. 
//

import java.util.ArrayList;
//import java.util.Printwriter;
import java.io.*;


public class PCBFontWrangler {

  // we start by defining the jagged long array of stroked font data
  // {width, kerning, thickness}, followed by {x1,y2, x2,y2, ... , xn,yn}
  // for ascii characters 32 -> 126 inclusive, so, 95 characters encoded in total
  // widthstroke thickness of 800 centimils is a bit thick, so will try 545

  long [][] fontData = HersheySansOneStroke.fontData; // default

  double fontMagnificationRatio = 1.0;
  long defaultMinimumThickness = 1000; // in centimils, i.e. 10 mil line thickness minimum 

  boolean cyrillicMode = false;
  boolean greekMode = false;

  public void greekMode() {
    greekMode = true;
    fontData = HersheyGreek.fontData;
    if (cyrillicMode) {
      cyrillicMode = false; // can't be both
    }
  } 

  public void cyrillicMode() {
    cyrillicMode = true;
    fontData = HersheyCyrillic.fontData;
    if (greekMode) {
      greekMode = false; // can't be both
    }
  }

  String stringToRender = null;

  public void PCBFontWrangler() {
  }

  public void PCBFontWrangler(long minimumLineThickness) {
    defaultMinimumThickness = minimumLineThickness;
  }

  // this returns the character width in centimils
  public long width(int c) {
    // this is ((Xmax - Xmin) +  kerning), but not thickness

    int index = 0;
    if (c >= 1040 && c <= 1103) {
      index = c - 975;
      return (long)(HersheyCyrillic.fontData[index*2][0]*fontMagnificationRatio);
    } else {
      index = c-32;
      return (long)(fontData[index*2][0]*fontMagnificationRatio);
    }
  }

  // this returns the character kerning in centimils
  public long kerning(int c) {
    int index = 0;
    if (c >= 1040 && c <= 1103) {
      index = c - 975;
      return (long)(HersheyCyrillic.fontData[index*2][1]*fontMagnificationRatio);
    } else {
      index = c-32;
      return (long)(fontData[index*2][1]*fontMagnificationRatio);
    }
  }

  // this returns the silk line thickness in centimils
  public long thickness(int c) {
    int index = 0;
    long calculatedThickness = 0;
    //System.out.println("Unicode char: " + c);
    if (c >= 1040 && c <= 1103) {
      index = c - 975;
      calculatedThickness
          = (long)(HersheyCyrillic.fontData[index*2][2]*fontMagnificationRatio);
    } else {
      index = c-32;
      calculatedThickness 
          = (long)(fontData[index*2][2]*fontMagnificationRatio);
    }
    
    if (calculatedThickness < defaultMinimumThickness)
      return defaultMinimumThickness;
    else {
      return calculatedThickness;
    }
  }

  // this returns the offset needed to centre the font relative
  // to the y-axis in centimils
  public long yCentredOffset() {
    long textCentreOffsetYcentimil = -1000 - (5333/2);
    // the hershey sans 1 stroke font is offset +1000,
    // and is ~5333 high
    return (long)(textCentreOffsetYcentimil*fontMagnificationRatio);
  }

  // this returns the height of the font in centimils
  public long height() {
    long heightCentimil = 5333;
    // the hershey sans 1 stroke font data is 5333 high,
    // and offset +1000 centimil
    return (long)(heightCentimil*fontMagnificationRatio);
  }

  // We are using centimils when retrieving stored
  // character width and when outputting gEDA elements.
  // This method changes the default 1.0 magnification
  // ratio to that needed for a Kicad text element
  public void setKicadMWidthNm(long widthNm) {
    long nativeMCharFontWidth = width('m') - kerning('m') + thickness('m');
    // overall width of usual drawn element, now used to calculate:
    fontMagnificationRatio = (widthNm/(254.0))/nativeMCharFontWidth;
  }

  // the following can be used when creating new font data arrays
  // if things like x-offsets need to be fine tuned.
  public void generateNewArray() throws IOException {
    File tester = new File("SansThing.txt");
    PrintWriter output = new PrintWriter(tester);
    for (int i = 0; i < fontData.length; i = i+2) {

      String secondLine = "},\n{";
      String completeLine = "";

      long glyphMaxX = 0;
      long glyphMinX = 100000;
      long xOffset = 0;

      //first we calculate offset required, if any
      for (int j = 0;
           j < fontData[i+1].length;
           j = j+2) {
        long currentX = fontData[i+1][j]; // can add offsets here
        if (currentX > glyphMaxX) {
           glyphMaxX = currentX;
        }
        if (currentX < glyphMinX) {
           glyphMinX = currentX;
        }
      }
      xOffset = glyphMinX - 1000; // used to start all chars at x=1000

      for (int j = 0;
           j < fontData[i+1].length;
           j = j+2) {
        long currentX = fontData[i+1][j]; // can add offsets here
        if (currentX > glyphMaxX) {
           glyphMaxX = currentX;
        }
        // can add x-offsets or y-offests etc to following line(s)
        secondLine = secondLine + (currentX-xOffset) + ","; // "x"
        secondLine = secondLine
                    + fontData[i+1][j+1]; // "y"
        if (j < (fontData[i+1].length - 2)) {
          secondLine = secondLine + ",";
        }
      }
      secondLine = secondLine + "},";
      System.out.println(secondLine);

      String firstLine = "{";
      for (int j = 0; j < fontData[i].length; j++) {
        if ((j == 0) && (fontData[i][j] == 9999)) {
          firstLine = firstLine + (glyphMaxX - xOffset + 200);
        } else {
          firstLine = firstLine + fontData[i][j];
        }
        if (j < (fontData[i].length - 1)) {
          firstLine = firstLine + ", ";
        }
      }
      completeLine = firstLine + secondLine;

      output.println(completeLine);
    }
    output.close();
  }

  public double fontMagnificationRatio() {
    return fontMagnificationRatio;
  }

  public void setStringToRender(String passedString) {
    stringToRender = passedString;
  }

  public long renderedWidthCentimil(ArrayList<Integer> unicodeVals) {
    // we need to figure out the width of the rendered string
    // we start by summing the widths of the individuals chars
    // remembering that hershey.width() returns ((maxX-minX) + kerning)
    long widthCentimil = 0;
    for (int i = 0; i < unicodeVals.size(); i++) {
      widthCentimil += width(unicodeVals.get(i)); 
      //System.out.println("RenderedWidth() now: " + widthCentimil);
    }

    // now we subtract the final kerning, add thickness, and 
    // account for the + 1000 offset of a gEDA font symbol
    // along the x-axis 
    if (widthCentimil > 0) { // in case string to display not set
      widthCentimil = widthCentimil
          - kerning(unicodeVals.get(unicodeVals.size()-1))
          // lopped off end kerning to get final width
          + (long)(1000*fontMagnificationRatio())
          // accounted for final char being an extra +1000 along x axis
          + thickness(unicodeVals.get(unicodeVals.size()-1)); 
      // and accounted for thickness of drawn lines
    }
    return (long)(widthCentimil*fontMagnificationRatio);
  }

  public long renderedWidthCentimil() {
    // we need to figure out the width of the rendered string
    // we start by summing the widths of the individuals chars
    // remembering that hershey.width() returns ((maxX-minX) + kerning)
    long widthCentimil = 0;
    for (int i = 0; i < stringToRender.length(); i++) {
      widthCentimil += width(stringToRender.charAt(i)); 
      //System.out.println("RenderedWidth() now: " + widthCentimil);
    }
    // now we subtract the final kerning, add thickness, and 
    // account for the + 1000 offset of a gEDA font symbol
    // along the x-axis 
    if (widthCentimil > 0) { // in case string to display not set
      widthCentimil = widthCentimil
          - kerning(stringToRender.charAt(stringToRender.length()-1))
          // lopped off end kerning to get final width
          + (long)(1000*fontMagnificationRatio())
          // accounted for final char being an extra +1000 along x axis
          + thickness((int)(stringToRender.charAt(stringToRender.length()-1))); 
      // and accounted for thickness of drawn lines
    }
    return (long)(widthCentimil*fontMagnificationRatio);
  }

  // this accepts the character ID as an int, x,y cooords
  // in decimils, and ignores the metric flag and
  // returns a character without rotation or
  // scaling, in centimil layuot format, placed
  // with it's upper left corner located at x,y   
  public String drawChar(int c, long xOffset, long yOffset, boolean metric) {
    double magRatio = 1.0; // default 1.0
    return drawChar(c, xOffset, yOffset, magRatio, metric);
  }

  // this accepts the character ID as an int, x,y cooords
  // in decimils, and a magnification ratio, and ignores the
  // metric flag and returns a character without rotation or
  // scaling, in centimil layout format, placed
  // with it's upper left corner located at x,y 
  public String drawChar(int c, long xOffset, long yOffset, double magRatio, boolean metric) {
    int index = ((c-32)*2)+1;
    // every second row of our array of long vectors
    // has the silk element descriptors
    String elements = "";

    //testing
    long [] strokes = fontData[index];

    // magRatio is the footprint magnification ratio,
    // not the font magnification ratio
    // we apply font magnification with
    // fontMagnificationRatio elsewhere   
    double theta = 0;
    elements = generateSilk(c, strokes, xOffset, yOffset, theta, magRatio, metric);
    return elements;
  }


  // this accepts the character ID as an int, x,y cooords
  // in centimils, and ignores the metric flag and
  // returns a character without rotation or scaling, centred on x,y 
  // with centimil PCB layout dimensions
  public String drawCentredChar(int c, long xOffset, long yOffset, boolean metric) {
    double magRatio = 1.0; // default 1.0, no footprint scaling
    double theta = 0; // default is no rotation
    return drawCentredChar(c, xOffset, yOffset, magRatio, theta, metric);
  }

  // this accepts the character ID as an int, x,y cooords in
  // centimils, rotation in radians, ignores the metric flag and
  // returns character with rotation and/or scaling, centred on x,y
  // with centimil PCB layout dimensions   
  public String drawCentredChar(int c, long xOffset, long yOffset, double magRatio, double theta, boolean metric) {

    int index = 0; //((c-32)*2)+1;

    if (c >= 1040 && c <= 1103) {
      index = ((c - 975)*2)+1;
      System.out.println("DrawCentred index: " + index);
    } else {
      index = ((c-32)*2)+1;
    }

    // i.e. every second row of our array of long vectors has
    // the silk element descriptors

    //testing
    long [] strokes = fontData[index];

    long centredYoffset = yOffset; // try this + yCentredOffset();
    String results = generateSilk(c, strokes, xOffset, yOffset, theta, magRatio, metric);
    return results;
  }

  // this will render a kicad module defined text field at coords
  // given in nanometres, with rotation specified in decdiegrees
  // i.e. in tenths of a degree
  // and produce gEDA PCB output using centimil units
  public String renderKicadText(String theString, long xCoordNm, long yCoordNm, long kicadDecidegrees, long kicadMWidthNm, double footprintMagnificationRatio) {
    setKicadMWidthNm(kicadMWidthNm); // scale to suit .mod definition
    double theta = kicadDecidegrees*Math.PI/1800; // convert to radians
    return renderString(theString, xCoordNm/254, yCoordNm/254, theta, footprintMagnificationRatio);
  }

  // this accepts radians as the rotation value
  // and coordinates in decimils
  // and produce gEDA PCB layout output in centimil units
  public String renderString(String theString, long xCoord, long yCoord, double theta, double footprintMagnificationRatio) {
    setStringToRender(theString);
    String elements = "";
    long renderedWidth = (long)(renderedWidthCentimil());
    long deltaTextWidth = 0;
    long firstCharXOffset = 0;
    // we first see how long the rendered string will be
    if (theString.length() != 0) {
      char firstChar = stringToRender.charAt(0);
      firstCharXOffset = (long)(((width((int)(firstChar))
                                  - kerning(firstChar))/2)*footprintMagnificationRatio);
    }
    // we then calculate where the string will start, and more
    // specifically, where the centre of the first character
    // will be as x,y cooords in centimils
    long currentX = (long)((xCoord -
                           (renderedWidth*Math.cos(theta))/2
                           + (firstCharXOffset*Math.cos(theta)))*footprintMagnificationRatio);
    long currentY = (long)((yCoord +
                           (renderedWidth*Math.sin(theta))/2
                           - (firstCharXOffset*Math.sin(theta)))*footprintMagnificationRatio);
    // we now walk along the centre line of the rendered string
    // which is angled theta radians to the x axis, dropping a
    // rendered character along it at suitable, calculated, spacings,
    // with each rendered character also rotated theta radians   
    for (int i = 0; i < stringToRender.length(); i++) {
      elements = elements +
          drawCentredChar((int)(stringToRender.charAt(i)), currentX, currentY, footprintMagnificationRatio, theta, true);
      if (i != (stringToRender.length()-1)) {
        deltaTextWidth =
            (long)(footprintMagnificationRatio*(width((int)(stringToRender.charAt(i)))/2 +
                                                width((int)(stringToRender.charAt(i+1)))/2));
      }
      currentX += (long)(deltaTextWidth*Math.cos(theta));
      currentY -= (long)(deltaTextWidth*Math.sin(theta));
    }
    return elements;
  }

  //----------
  // this accepts radians as the rotation value
  // and coordinates in decimils
  // and a list of unicode values as integers
  // and produces gEDA PCB layout output in centimil units
  public String renderString(ArrayList<Integer> unicodeList, long xCoord, long yCoord, double theta, double footprintMagnificationRatio) {
    //setStringToRender(theString);
    String elements = "";
    long renderedWidth = (long)(renderedWidthCentimil(unicodeList));
    long deltaTextWidth = 0;
    long firstCharXOffset = 0;

    if (false) {
      System.out.println("About to process: " 
                         + unicodeList.size());
      for (int index = 0; index < unicodeList.size(); index++) {
        System.out.println("ASCII: "
                           + unicodeList.get(index));
      }
    }

    // we first see how long the rendered string will be
    if (unicodeList.size() != 0) {
      int firstChar = unicodeList.get(0);
      //System.out.println("Using firstChar: " + firstChar);
      firstCharXOffset = (long)(((width(firstChar)
                                  - kerning(firstChar))/2)*footprintMagnificationRatio);
    }
    // we then calculate where the string will start, and more
    // specifically, where the centre of the first character
    // will be as x,y cooords in centimils
    long currentX = (long)((xCoord -
                           (renderedWidth*Math.cos(theta))/2
                           + (firstCharXOffset*Math.cos(theta)))*footprintMagnificationRatio);
    long currentY = (long)((yCoord +
                           (renderedWidth*Math.sin(theta))/2
                           - (firstCharXOffset*Math.sin(theta)))*footprintMagnificationRatio);
    // we now walk along the centre line of the rendered string
    // which is angled theta radians to the x axis, dropping a
    // rendered character along it at suitable, calculated, spacings,
    // with each rendered character also rotated theta radians   
    for (int i = 0; i < unicodeList.size(); i++) {
      elements = elements +
          drawCentredChar(unicodeList.get(i), currentX, currentY, footprintMagnificationRatio, theta, true);
      if (i != (unicodeList.size()-1)) {
        deltaTextWidth =
            (long)(footprintMagnificationRatio*(width(unicodeList.get(i)))/2 +
                   width(unicodeList.get(i+1))/2);
      }
      currentX += (long)(deltaTextWidth*Math.cos(theta));
      currentY -= (long)(deltaTextWidth*Math.sin(theta));
    }
    return elements;
  }


  private String generateSilk(int c, long [] strokes, long offsetX, long offsetY, double theta, double magRatio, boolean metric) { 
    String output = "";
    double magnify = magRatio*fontMagnificationRatio;
    // our final magnification ratio for rendering the elements is
    // the product of footprint and font magnification ratios
    // we don't use the metric flag for now
    // we try to make the final stroke thickness more
    // visually appealing after magnification,
    // rather than simply magnifiying the original thickness 
    if ((c-32) > 0) { // i.e. if not the empty space character = ASCII32
      long finalThickness = (long)magnify*thickness(c);
      if ((finalThickness > (long)((width('m')/6.0))) && (finalThickness > defaultMinimumThickness)){
        finalThickness = (long)((width('m')/6.0)*magRatio);
      }
      // we create some temporary variables for translation
      // and rotation and, again, translation of the x,y coordinates
      long TrX1 = 0;
      long TrX2 = 0;
      long TrY1 = 0;
      long TrY2 = 0;
      long tempX1 = 0;
      long tempY1 = 0;
      long tempX2 = 0;
      long tempY2 = 0;

      double testTheta = Math.PI*3/4;
      for (int i = 0; i < strokes.length; i = i + 4) {
        // first, we translate the stroked character data
        // so that it is centred on the origin (0,0)
        TrX1 = (long)(strokes[i] - (width(c) - kerning(c))/2);
        TrY1 = (long)(strokes[i+1] + yCentredOffset());
        TrX2 = (long)(strokes[i+2] - (width(c) - kerning(c))/2);
        TrY2 = (long)(strokes[i+3] + yCentredOffset());

        // now we apply our intended rotation of theta radians
        tempX1 = rotatedXCoord(TrX1, TrY1, theta);
        tempY1 = rotatedYCoord(TrX1, TrY1, theta);
        tempX2 = rotatedXCoord(TrX2, TrY2, theta);
        tempY2 = rotatedYCoord(TrX2, TrY2, theta);

        // now we translate the rotated coords back to where they
        // belong and apply the necessary magnification
        // TODO: test that the scaling/magnification works properly
        TrX1 = (long)(tempX1*magnify + offsetX);
        TrY1 = (long)(tempY1*magnify + offsetY);
        TrX2 = (long)(tempX2*magnify + offsetX);
        TrY2 = (long)(tempY2*magnify + offsetY);

        // and finish by generating the centimil unit
        // pcb layout text line with the transformed coords
        output = output + "ElementLine[" +
            TrX1 + " " + TrY1 + " " + 
            TrX2 + " " + TrY2 + " " +
            finalThickness + "]\n";
      }
      output = output + "#\n";
    }
    return output;
  }

  // a small routine to rotate an X coordinate around 0,0
  // by an amount theta, in radians
  // requires original (x,y) coords and theta
  private long rotatedXCoord(long xOrig, long yOrig, double theta) {
    // just to confuse things, we recall that up is -y direction
    return (long)(Math.cos(theta)*xOrig + Math.sin(theta)*yOrig);
  }

  // a small routine to rotate a Y coordinate around 0,0
  // by an amount theta, in radians
  // requires original (x,y) coords and theta
  private long rotatedYCoord(long xOrig, long yOrig, double theta) {
    // just to confuse things, we recall that up is -y direction
    return (long)(-Math.sin(theta)*xOrig + Math.cos(theta)*yOrig);
  }

}
