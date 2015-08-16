// FootprintTextForPCB.java v1.0
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
//  v1.0 of the utility uses the free Hershey Sans 1 Stroke Font and outputs
//  0.01mil (imperial, square bracketed) units. 
//

import java.io.*;

public class FootprintTextForPCB {

  // using chars 32 -> 126 = 95 in total

  public static void main(String [] args) throws IOException {

    String workingText = "demonstration"; // default text to convert if nothing supplied
    double magnificationRatio = 1.0;      // default value of 1.0 yields default sized font text in gEDA PCB
    int offset = 0;                       // this is used to increment the x position of the text character by character

    FontStrokes[] HersheySansFont = new FontStrokes[95];

    HersheySansFont[0] = new Ascii032();
    HersheySansFont[1] = new Ascii033();
    HersheySansFont[2] = new Ascii034();
    HersheySansFont[3] = new Ascii035();
    HersheySansFont[4] = new Ascii036();
    HersheySansFont[5] = new Ascii037();
    HersheySansFont[6] = new Ascii038();
    HersheySansFont[7] = new Ascii039();
    HersheySansFont[8] = new Ascii040();
    HersheySansFont[9] = new Ascii041();
    HersheySansFont[10] = new Ascii042();
    HersheySansFont[11] = new Ascii043();
    HersheySansFont[12] = new Ascii044();
    HersheySansFont[13] = new Ascii045();
    HersheySansFont[14] = new Ascii046();
    HersheySansFont[15] = new Ascii047();
    HersheySansFont[16] = new Ascii048();
    HersheySansFont[17] = new Ascii049();
    HersheySansFont[18] = new Ascii050();
    HersheySansFont[19] = new Ascii051();
    HersheySansFont[20] = new Ascii052();
    HersheySansFont[21] = new Ascii053();
    HersheySansFont[22] = new Ascii054();
    HersheySansFont[23] = new Ascii055();
    HersheySansFont[24] = new Ascii056();
    HersheySansFont[25] = new Ascii057();
    HersheySansFont[26] = new Ascii058();
    HersheySansFont[27] = new Ascii059();
    HersheySansFont[28] = new Ascii060();
    HersheySansFont[29] = new Ascii061();
    HersheySansFont[30] = new Ascii062();
    HersheySansFont[31] = new Ascii063();
    HersheySansFont[32] = new Ascii064();
    HersheySansFont[33] = new Ascii065();
    HersheySansFont[34] = new Ascii066();
    HersheySansFont[35] = new Ascii067();
    HersheySansFont[36] = new Ascii068();
    HersheySansFont[37] = new Ascii069();
    HersheySansFont[38] = new Ascii070();
    HersheySansFont[39] = new Ascii071();
    HersheySansFont[40] = new Ascii072();
    HersheySansFont[41] = new Ascii073();
    HersheySansFont[42] = new Ascii074();
    HersheySansFont[43] = new Ascii075();
    HersheySansFont[44] = new Ascii076();
    HersheySansFont[45] = new Ascii077();
    HersheySansFont[46] = new Ascii078();
    HersheySansFont[47] = new Ascii079();
    HersheySansFont[48] = new Ascii080();
    HersheySansFont[49] = new Ascii081();
    HersheySansFont[50] = new Ascii082();
    HersheySansFont[51] = new Ascii083();
    HersheySansFont[52] = new Ascii084();
    HersheySansFont[53] = new Ascii085();
    HersheySansFont[54] = new Ascii086();
    HersheySansFont[55] = new Ascii087();
    HersheySansFont[56] = new Ascii088();
    HersheySansFont[57] = new Ascii089();
    HersheySansFont[58] = new Ascii090();
    HersheySansFont[59] = new Ascii091();
    HersheySansFont[60] = new Ascii092();
    HersheySansFont[61] = new Ascii093();
    HersheySansFont[62] = new Ascii094();
    HersheySansFont[63] = new Ascii095();
    HersheySansFont[64] = new Ascii096();
    HersheySansFont[65] = new Ascii097();
    HersheySansFont[66] = new Ascii098();
    HersheySansFont[67] = new Ascii099();
    HersheySansFont[68] = new Ascii100();
    HersheySansFont[69] = new Ascii101();
    HersheySansFont[70] = new Ascii102();
    HersheySansFont[71] = new Ascii103();
    HersheySansFont[72] = new Ascii104();
    HersheySansFont[73] = new Ascii105();
    HersheySansFont[74] = new Ascii106();
    HersheySansFont[75] = new Ascii107();
    HersheySansFont[76] = new Ascii108();
    HersheySansFont[77] = new Ascii109();
    HersheySansFont[78] = new Ascii110();
    HersheySansFont[79] = new Ascii111();
    HersheySansFont[80] = new Ascii112();
    HersheySansFont[81] = new Ascii113();
    HersheySansFont[82] = new Ascii114();
    HersheySansFont[83] = new Ascii115();
    HersheySansFont[84] = new Ascii116();
    HersheySansFont[85] = new Ascii117();
    HersheySansFont[86] = new Ascii118();
    HersheySansFont[87] = new Ascii119();
    HersheySansFont[88] = new Ascii120();
    HersheySansFont[89] = new Ascii121();
    HersheySansFont[90] = new Ascii122();
    HersheySansFont[91] = new Ascii123();
    HersheySansFont[92] = new Ascii124();
    HersheySansFont[93] = new Ascii125();
    HersheySansFont[94] = new Ascii126();

    // with the preliminaries out of the way we check
    // if the user has shared any ones and zeroes with us

    if (args.length != 0) {
      for (int index = 0; index < args.length; index ++) {
        if (args[index].startsWith("-t") && ((index + 1) < args.length)) {
          workingText = args[index + 1];
          index++;
        } else if (args[index].startsWith("-m") && ((index + 1) < args.length)) {
          magnificationRatio = Double.parseDouble(args[index + 1]);
          index++;
        } else {
          printUsage();
          System.exit(0);
        }        
      }
    }

    String displayedElements  = "";
    String footprintHeader = "Element[\"\" \"" + workingText + "\" \"\" \"\" 0 0 0 -4000 0 100 \"\"]\n(\n";    

    for (int i = 0; i < workingText.length(); i++){
      displayedElements = displayedElements + HersheySansFont[((int)workingText.charAt(i) - 32)].element(offset, magnificationRatio, true);
      offset += HersheySansFont[((int)workingText.charAt(i) - 32)].width();
    }

    String filename = workingText.replaceAll("[^a-zA-Z1-9-]", "_");
    if (magnificationRatio != 1.0) {
      filename = filename + "-" + magnificationRatio + "x.fp";
    }
    else {
      filename = filename + ".fp";
    }
    PrintWriter Footprint = new PrintWriter(filename);
    Footprint.println(footprintHeader + displayedElements + ")\n");
    Footprint.close();

    System.out.println("Writing \"" + workingText + "\" as silkscreen elements in: " + filename );

  }

  public static void printUsage() {
    System.out.println("\nUsage: \n\n    java FootprintTextForPCB -t \"my Text For Conversion To Silkscreen Stroke Elements\" -m X.XXXX\n");
    System.out.println("    \"my Text For Conversion To Silkscreen Stroke Elements\" is ASCII text, which can include spaces,");
    System.out.println("    and X.XXXX is an optional magnification ratio; default = 1.0)\n");
    System.out.println("    If run without any command line arguments, a demonstration footprint file");
    System.out.println("    called demonstration.fp, will be generated\n"); 
  }

  private static class FontStrokes {
    int kerning = 1200;
    int thickness = 1800;
    int [] strokes = {0,0,0,0};
   
    public void setKern(int kern) {
      kerning = kern;
    }

    public int kern() {
      return kerning;
    }
    public String element(int xOffset, double magRatio, boolean metric) {
      return "archetype\n";
    }
    public int width() {
      return 0;
    }
    //    public String element() {
    //  return "";
    //}

  }

  private static String generateSilk(int [] strokes, int thickness, int kerning, int offsetX, double magRatio, boolean metric) { 
    String output = "";
    int offsetY = 0;
    double magnify = magRatio;
    for (int i = 0; i < strokes.length; i = i + 4) {
      output = output + "ElementLine[" +
          (long)(magnify*(strokes[i] + offsetX)) + " " + (long)(magnify*(strokes[i+1] + offsetY)) + " " + 
          (long)(magnify*(strokes[i+2] + offsetX)) + " " + (long)(magnify*(strokes[i+3] + offsetY)) + " " +
          (long)(magnify*thickness) + "]\n"; 
    }
    output = output + "#\n";
    //    output = output + ")\n";
    return output;
  }

  private static int maximumWidth(int [] strokes) {
    int minX = 0;
    int minY = 0;
    int maxX = 0;
    int maxY = 0;
    int width = 0;
    if (strokes.length != 0) {
      minX = strokes[0];
      minY = strokes[1];
      maxX = strokes[0];
      maxY = strokes[1];
      for (int i = 2; i < strokes.length; i += 2) {
        if (minX > strokes[i]) {
          minX = strokes[i];
        }
        if (maxX < strokes[i]) {
          maxX = strokes[i];
        }
        if (minY > strokes[i+1]) {
          minY = strokes[i+1];
        }
        if (maxY < strokes[i+1]) {
          maxY = strokes[i+1];
        }
      }
      width = maxX - minX;
      // we don't use minY and maxY for now
    }
    return width;
  }

  private static class Ascii032 extends FontStrokes { // ' '
    int kerning = 1800;
    int [] strokes = {};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return kerning;
    }
  }
  private static class Ascii033 extends FontStrokes { // ''!''',1200]
    int kerning = 1200;
    int [] strokes = {1191,1000,1191,3666,1191,4619,1000,4809,1000,4809,1191,4999,1191,4999,1381,4809,1381,4809,1191,4619};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii034 extends FontStrokes { // ''"''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,2334,2523,1000,2523,2334};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii035 extends FontStrokes { // ''#''',1200]
    int kerning = 1200;
    int [] strokes = {2573,1000,1239,5000,3916,1000,2582,5000,1339,2325,4207,2325,1000,3675,3866,3675};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii036 extends FontStrokes { // ''$''',1200]
    int kerning = 1200;
    int [] strokes = {2333,300,2333,5824,3666,1633,3286,1252,3286,1252,2714,1062,2714,1062,1952,1062,1952,1062,1381,1252,1381,1252,1000,1633,1000,1633,1000,2015,1000,2015,1191,2395,1191,2395,1381,2586,1381,2586,1762,2776,1762,2776,2905,3157,2905,3157,3286,3347,3286,3347,3477,3538,3477,3538,3666,3919,3666,3919,3666,4490,3666,4490,3286,4872,3286,4872,2714,5062,2714,5062,1952,5062,1952,5062,1381,4872,1381,4872,1000,4490};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii037 extends FontStrokes { // ''%''',1200]
    int kerning = 1200;
    int [] strokes = {11285,1000,7857,4999,8809,1000,9191,1380,9191,1380,9191,1762,9191,1762,9000,2142,9000,2142,8619,2333,8619,2333,8238,2333,8238,2333,7857,1952,7857,1952,7857,1571,7857,1571,8048,1190,8048,1190,8428,1000,8428,1000,8809,1000,8809,1000,9191,1190,9191,1190,9762,1380,9762,1380,10332,1380,10332,1380,10905,1190,10905,1190,11285,1000,10523,3666,10143,3857,10143,3857,9952,4237,9952,4237,9952,4619,9952,4619,10332,4999,10332,4999,10714,4999,10714,4999,11095,4809,11095,4809,11285,4428,11285,4428,11285,4047,11285,4047,10905,3666,10905,3666,10523,3666,};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii038 extends FontStrokes { // ''&''',1200]
    int kerning = 1200;
    int [] strokes = {4809,2714,4809,2523,4809,2523,4618,2333,4618,2333,4428,2333,4428,2333,4237,2523,4237,2523,4046,2905,4046,2905,3666,3857,3666,3857,3285,4428,3285,4428,2905,4809,2905,4809,2523,4999,2523,4999,1762,4999,1762,4999,1380,4809,1380,4809,1189,4619,1189,4619,1000,4237,1000,4237,1000,3857,1000,3857,1189,3476,1189,3476,1380,3285,1380,3285,2714,2523,2714,2523,2905,2333,2905,2333,3094,1952,3094,1952,3094,1571,3094,1571,2905,1190,2905,1190,2523,1000,2523,1000,2142,1190,2142,1190,1952,1571,952,1571,1952,1952,1952,1952,2142,2523,2142,2523,2523,3095,2523,3095,3475,4428,3475,4428,3857,4809,3857,4809,4237,4999,4237,4999,4618,4999,4618,4999,4809,4809,4809,4809,4809,4619};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii039 extends FontStrokes { // ''''''',1200]
    int kerning = 1200;
    int [] strokes = {1191,1381,1000,1191,1000,1191,1191,1000,1191,1000,1381,1191,1381,1191,1381,1571,1381,1571,1191,1952,1191,1952,1000,2143};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii040 extends FontStrokes { // ''('',1200]
    int kerning = 1200;
    int [] strokes = {2333,0,1952,381,1952,381,1571,952,1571,952,1189,1715,1189,1715,1000,2667,1000,2667,1000,3429,1000,3429,1189,4381,1189,4381,1571,5143,1571,5143,1952,5714,1952,5714,2333,6095};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii041 extends FontStrokes { // '')'',1200]
    int kerning = 1200;
    int [] strokes = {1000,0,1380,381,1380,381,1762,952,1762,952,2143,1715,2143,1715,2332,2667,2332,2667,2332,3429,2332,3429,2143,4381,2143,4381,1762,5143,1762,5143,1380,5714,1380,5714,1000,6095};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii042 extends FontStrokes { // ''*''',1200]
    int kerning = 1200;
    int [] strokes = {2667,1000,2667,5000,1000,2001,4333,4001,4333,2001,1000,4001};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii043 extends FontStrokes { // ''+''',1200]
    int kerning = 1200;
    int [] strokes = {2714,1250,2714,4678,1000,2964,4428,2964};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii044 extends FontStrokes { // '',''',1200]
    int kerning = 1200;
    int [] strokes = {1381,4891,1191,5080,1191,5080,1000,4891,1000,4891,1191,4700,1191,4700,1381,4891,1381,4891,1381,5271,1381,5271,1000,5652};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
  }
  private static class Ascii045 extends FontStrokes { // ''-''',1200]
    int kerning = 1200;
    int [] strokes = {0,3000,2500,3000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii046 extends FontStrokes { // ''.''',1200]
    int kerning = 1200;
    int [] strokes = {1190,4700,1000,4891,1000,4891,1190,5080,1190,5080,1380,4891,1380,4891,1190,4700};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii047 extends FontStrokes { // ''/''',1200]
    int kerning = 1200;
    int [] strokes = {3251,1000,1000,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii048 extends FontStrokes { // ''0''',1200],#numbers,are,new,
    int kerning = 1200;
    int [] strokes = {2143,1000,1572,1190,1572,1190,1190,1761,1190,1761,1000,2713,1000,2713,1000,3285,1000,3285,1190,4238,1190,4238,1572,4809,1572,4809,2143,5000,2143,5000,2524,5000,2524,5000,3095,4809,3095,4809,3476,4238,3476,4238,3667,3285,3667,3285,3667,2713,3667,2713,3476,1761,3476,1761,3095,1190,3095,1190,2524,1000,2524,1000,2143,1000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii049 extends FontStrokes { // ''1''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1761,1380,1571,1380,1571,1952,1000,1952,1000,1952,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii050 extends FontStrokes { // ''2''',1200]
    int kerning = 1200;
    int [] strokes = {1190,1952,1190,1761,1190,1761,1381,1381,1381,1381,1570,1190,1570,1190,1952,1000,1952,1000,2713,1000,2713,1000,3095,1190,3095,1190,3285,1381,3285,1381,3475,1761,3475,1761,3475,2143,3475,2143,3285,2524,3285,2524,2904,3095,2904,3095,1000,5000,1000,5000,3666,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii051 extends FontStrokes { // ''3''',1200]
    int kerning = 1200;
    int [] strokes = {1380,1000,3475,1000,3475,1000,2332,2524,2332,2524,2904,2524,2904,2524,3284,2713,3284,2713,3475,2904,3475,2904,3666,3476,3666,3476,3666,3856,3666,3856,3475,4428,3475,4428,3095,4809,3095,4809,2523,5000,2523,5000,1952,5000,1952,5000,1380,4809,1380,4809,1190,4618,1190,4618,1000,4238};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii052 extends FontStrokes { // ''4''',1200]
    int kerning = 1200;
    int [] strokes = {2904,1000,1000,3666,1000,3666,3856,3666,2904,1000,2904,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii053 extends FontStrokes { // ''5''',1200]
    int kerning = 1200;
    int [] strokes = {3286,1000,1381,1000,1381,1000,1190,2713,1190,2713,1381,2524,1381,2524,1952,2333,1952,2333,2524,2333,2524,2333,3095,2524,3095,2524,3476,2904,3476,2904,3667,3476,3667,3476,3667,3856,3667,3856,3476,4428,3476,4428,3095,4809,3095,4809,2524,5000,2524,5000,1952,5000,1952,5000,1381,4809,1381,4809,1190,4618,1190,4618,1000,4238};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii054 extends FontStrokes { // ''6''',1200]
    int kerning = 1200;
    int [] strokes = {3285,1571,3095,1190,3095,1190,2523,1000,2523,1000,2143,1000,2143,1000,1570,1190,1570,1190,1190,1761,1190,1761,1000,2713,1000,2713,1000,3666,1000,3666,1190,4428,1190,4428,1570,4809,1570,4809,2143,5000,2143,5000,2333,5000,2333,5000,2904,4809,2904,4809,3285,4428,3285,4428,3475,3856,3475,3856,3475,3666,3475,3666,3285,3095,3285,3095,2904,2713,2904,2713,2333,2524,2333,2524,2143,2524,2143,2524,1570,2713,1570,2713,1190,3095,1190,3095,1000,3666};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii055 extends FontStrokes { // ''7''',1200]
    int kerning = 1200;
    int [] strokes = {3666,1000,1761,5000,1000,1000,3666,1000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii056 extends FontStrokes { // ''8''',1200]
    int kerning = 1200;
    int [] strokes = {1952,1000,1380,1190,1380,1190,1190,1571,1190,1571,1190,1952,1190,1952,1380,2333,1380,2333,1761,2524,1761,2524,2523,2713,2523,2713,3095,2904,3095,2904,3475,3285,3475,3285,3666,3666,3666,3666,3666,4238,3666,4238,3475,4618,3475,4618,3284,4809,3284,4809,2713,5000,2713,5000,1952,5000,1952,5000,1380,4809,1380,4809,1190,4618,1190,4618,1000,4238,1000,4238,1000,3666,1000,3666,1190,3285,1190,3285,1570,2904,1570,2904,2142,2713,2142,2713,2904,2524,2904,2524,3284,2333,3284,2333,3475,1952,3475,1952,3475,1571,3475,1571,3284,1190,3284,1190,2713,1000,2713,1000,1952,1000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii057 extends FontStrokes { // ''9''',1200]
    int kerning = 1200;
    int [] strokes = {3475,2333,3285,2904,3285,2904,2904,3285,2904,3285,2332,3476,2332,3476,2142,3476,2142,3476,1571,3285,1571,3285,1189,2904,1189,2904,1000,2333,1000,2333,1000,2143,1000,2143,1189,1571,1189,1571,1571,1190,1571,1190,2142,1000,2142,1000,2332,1000,2332,1000,2904,1190,2904,1190,3285,1571,3285,1571,3475,2333,3475,2333,3475,3285,3475,3285,3285,4238,3285,4238,2904,4809,2904,4809,2332,5000,2332,5000,1952,5000,1952,5000,1380,4809,1380,4809,1189,4428};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii058 extends FontStrokes { // '':''',1200]
    int kerning = 1200;
    int [] strokes = {1190,3400,1000,3591,1000,3591,1190,3781,1190,3781,1380,3591,1380,3591,1190,3400,1190,4734,1000,4925,1000,4925,1190,5114,1190,5114,1380,4925,1380,4925,1190,4734};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii059 extends FontStrokes { // '';''',1200]
    int kerning = 1200;
    int [] strokes = {1191,3400,1000,3591,1000,3591,1191,3781,1191,3781,1380,3591,1380,3591,1191,3400,1380,4925,1191,5114,1191,5114,1000,4925,1000,4925,1191,4734,1191,4734,1380,4925,1380,4925,1380,5305,1380,5305,1000,5686};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii060 extends FontStrokes { // ''<''',1200]
    int kerning = 1200;
    int [] strokes = {3540,1000,1000,3001,1000,3001,3540,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii061 extends FontStrokes { // ''=''',1200]
    int kerning = 1200;
    int [] strokes = {0,2400,2500,2400,0,3600,2500,3600};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii062 extends FontStrokes { // ''>''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,3540,3001,3540,3001,1000,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii063 extends FontStrokes { // ''?''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1952,1000,1762,1000,1762,1191,1381,1191,1381,1380,1191,1380,1191,1762,1000,1762,1000,2523,1000,2523,1000,2905,1191,2905,1191,3095,1381,3095,1381,3285,1762,3285,1762,3285,2143,3285,2143,3095,2523,3095,2523,2905,2714,2905,2714,2143,3095,2143,3095,2143,3666,2143,4618,1952,4809,1952,4809,2143,5000,2143,5000,2332,4809,2332,4809,2143,4618};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii064 extends FontStrokes { // ''@''',1200]
    int kerning = 1200;
    int [] strokes = {4751,3000,4500,2499,4500,2499,4001,2250,4001,2250,3250,2250,3250,2250,2751,2499,2751,2499,2500,2750,2500,2750,2250,3501,2250,3501,2250,4250,2250,4250,2500,4751,2500,4751,3001,5000,3001,5000,3751,5000,3751,5000,4251,4751,4251,4751,4500,4250,3250,2250,2751,2750,2751,2750,2500,3501,2500,3501,2500,4250,2500,4250,2751,4751,2751,4751,3001,5000,4751,2250,4500,4250,4500,4250,4500,4751,4500,4751,5001,5000,5001,5000,5502,5000,5502,5000,6001,4501,6001,4501,6251,3750,6251,3750,6251,3250,6251,3250,6001,2499,6001,2499,5751,2000,5751,2000,5251,1499,5251,1499,4751,1249,4751,1249,4001,1000,4001,1000,3250,1000,3250,1000,2500,1249,2500,1249,2000,1499,2000,1499,1501,2000,1501,2000,1250,2499,1250,2499,1000,3250,1000,3250,1000,4000,1000,4000,1250,4751,1250,4751,1501,5250,1501,5250,2000,5751,2000,5751,2500,6001,2500,6001,3250,6250,3250,6250,4001,6250,4001,6250,4751,6001,4751,6001,5251,5751,5251,5751,5502,5501,5001,2250,4751,4250,4751,4250,4751,4751,4751,4751,5001,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii065 extends FontStrokes { // ''A''',1200]
    int kerning = 1200;
    int [] strokes = {1524,1000,0,5000,1524,1000,3048,5000,572,3667,2477,3667};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii066 extends FontStrokes { // ''B''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1763,3666,1763,3666,2143,3666,2143,3476,2524,3476,2524,3285,2715,3285,2715,2714,2905,1000,2905,2714,2905,2714,2905,3285,3095,3285,3095,3476,3286,3476,3286,3666,3667,3666,3667,3666,4238,3666,4238,3476,4620,3476,4620,3285,4809,3285,4809,2714,5000,2714,5000,1000,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii067 extends FontStrokes { // ''C''',1200]
    int kerning = 1200;
    int [] strokes = {3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000,2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3477,1000,3477,1190,4048,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii068 extends FontStrokes { // ''D''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,2334,1000,2334,1000,2905,1191,2905,1191,3286,1572,3286,1572,3476,1952,3476,1952,3666,2524,3666,2524,3666,3477,3666,3477,3476,4048,3476,4048,3286,4429,3286,4429,2905,4809,2905,4809,2334,5000,2334,5000,1000,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii069 extends FontStrokes { // ''E''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,3476,1000,1000,2905,2523,2905,1000,5000,3476,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
  }
  private static class Ascii070 extends FontStrokes { // ''F''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,3475,1000,1000,2905,2523,2905};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii071 extends FontStrokes { // ''G''',1200]
    int kerning = 1200;
    int [] strokes = {3857,1952,3666,1572,3666,1572,3286,1191,3286,1191,2905,1000,2905,1000,2143,1000,2143,1000,1762,1191,1762,1191,1381,1572,1381,1572,1191,1952,1191,1952,1000,2524,1000,2524,1000,3477,1000,3477,1191,4048,1191,4048,1381,4429,1381,4429,1762,4809,1762,4809,2143,5000,2143,5000,2905,5000,2905,5000,3286,4809,3286,4809,3666,4429,3666,4429,3857,4048,3857,4048,3857,3477,2905,3477,3857,3477};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii072 extends FontStrokes { // ''H''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,3667,5000,3667,1000,1000,2905,3667,2905};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii073 extends FontStrokes { // ''I''',1200]
    int kerning = 1200;
    int [] strokes = {0,1000,1000,1000,500,1000,500,5000,0,5000,1000,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii074 extends FontStrokes { // ''J''',1200]
    int kerning = 1200;
    int [] strokes = {2905,1000,2905,4048,2905,4048,2715,4620,2715,4620,2524,4809,2524,4809,2143,5000,2143,5000,1763,5000,1763,5000,1381,4809,1381,4809,1191,4620,1191,4620,1000,4048,1000,4048,1000,3667};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii075 extends FontStrokes { // ''K''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,3666,1000,1000,3667,1952,2715,3666,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii076 extends FontStrokes { // ''L''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,5000,3285,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii077 extends FontStrokes { // ''M''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,2523,5000,4048,1000,2523,5000,4048,1000,4048,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii078 extends FontStrokes { // ''N''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,3666,5000,3666,1000,3666,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii079 extends FontStrokes { // ''O''',1200]
    int kerning = 1200;
    int [] strokes = {2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3476,1000,3476,1190,4049,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048,3857,4048,4046,3476,4046,3476,4046,2524,4046,2524,3857,1952,3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii080 extends FontStrokes { // ''P''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1762,3666,1762,3666,2334,3666,2334,3476,2714,3476,2714,3285,2905,3285,2905,2714,3095,2714,3095,1000,3095};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii081 extends FontStrokes { // ''Q''',1200]
    int kerning = 1200;
    int [] strokes = {2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3476,1000,3476,1190,4048,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048,3857,4048,4047,3476,4047,3476,4047,2524,4047,2524,3857,1952,3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000,2714,4238,3857,5381};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii082 extends FontStrokes { // ''R''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1762,3666,1762,3666,2143,3666,2143,3476,2524,3476,2524,3285,2714,3285,2714,2714,2905,2714,2905,1000,2905,2333,2905,3666,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii083 extends FontStrokes { // ''S''',1200]
    int kerning = 1200;
    int [] strokes = {3666,1572,3285,1191,3285,1191,2714,1000,2714,1000,1952,1000,1952,1000,1380,1191,1380,1191,1000,1572,1000,1572,1000,1952,1000,1952,1189,2334,1189,2334,1380,2524,1380,2524,1762,2714,1762,2714,2905,3095,2905,3095,3285,3286,3285,3286,3475,3476,3475,3476,3666,3857,3666,3857,3666,4429,3666,4429,3285,4809,3285,4809,2714,5000,2714,5000,1952,5000,1952,5000,1380,4809,1380,4809,1000,4429};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii084 extends FontStrokes { // ''T''',1200]
    int kerning = 1200;
    int [] strokes = {2333,1000,2333,5000,1000,1000,3666,1000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii085 extends FontStrokes { // ''U''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,3857,1000,3857,1191,4429,1191,4429,1571,4809,1571,4809,2143,5000,2143,5000,2523,5000,2523,5000,3095,4809,3095,4809,3476,4429,3476,4429,3666,3857,3666,3857,3666,1000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii086 extends FontStrokes { // ''V''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,2523,5000,4048,1000,2523,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii087 extends FontStrokes { // ''W''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,1952,5000,2905,1000,1952,5000,2905,1000,3857,5000,4809,1000,3857,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii088 extends FontStrokes { // ''X''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,3666,5000,3666,1000,1000,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii089 extends FontStrokes { // ''Y''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,2523,2905,2523,2905,2523,5000,4048,1000,2523,2905};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii090 extends FontStrokes { // ''Z''',1200]
    int kerning = 1200;
    int [] strokes = {3666,1000,1000,5000,1000,1000,3666,1000,1000,5000,3666,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii091 extends FontStrokes { // ''[''',1200]
    int kerning = 1200;
    int [] strokes = {1000,0,1000,6094,1191,0,1191,6094,1000,0,2334,0,1000,6094,2334,6094};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii092 extends FontStrokes { // ''\''',1200]
    int kerning = 1200;
    int [] strokes = {1000,1000,3251,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii093 extends FontStrokes { // '']''',1200]
    int kerning = 1200;
    int [] strokes = {7666,0,7666,6094,7857,0,7857,6094,6524,0,7857,0,6524,6094,7857,6094};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii094 extends FontStrokes { // ''^''',1200]
    int kerning = 1200;
    int [] strokes = {2523,1000,1000,3222,2523,1000,4046,3222};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii095 extends FontStrokes { // ''_''',1200]
    int kerning = 1200;
    int [] strokes = {1000,5000,3500,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii096 extends FontStrokes { // ''`'',1200] ###### needs tweaking #####
    int kerning = 1200;
    int [] strokes = {1191,1381,1000,1191,1000,1191,1191,1000,1191,1000,1381,1191,1381,1191,1381,1571,1381,1571,1191,1952,1191,1952,1000,2143};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii097 extends FontStrokes { // ''a''',1200],##new,,
    int kerning = 1200;
    int [] strokes = {3286,2333,3286,4999,3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii098 extends FontStrokes { // ''b''',1200],###new,,
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,2904,1381,2523,1381,2523,1761,2333,1761,2333,2333,2333,2333,2333,2713,2523,2713,2523,3095,2904,3095,2904,3286,3475,3286,3475,3286,3856,3286,3856,3095,4427,3095,4427,2713,4809,2713,4809,2333,5000,2333,5000,1761,5000,1761,5000,1381,4809,1381,4809,1000,4427};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii099 extends FontStrokes { // ''c''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii100 extends FontStrokes { // ''d''',1200],###new,,
    int kerning = 1200;
    int [] strokes = {3284,1000,3284,5000,3284,2904,2904,2523,2904,2523,2523,2333,2523,2333,1952,2333,1952,2333,1570,2523,1570,2523,1189,2904,1189,2904,1000,3475,1000,3475,1000,3856,1000,3856,1189,4427,1189,4427,1570,4809,1570,4809,1952,5000,1952,5000,2523,5000,2523,5000,2904,4809,2904,4809,3284,4427};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii101 extends FontStrokes { // ''e''',1200],#NEW,,
    int kerning = 1200;
    int [] strokes = {1000,3474,3284,3474,3284,3474,3284,3094,3284,3094,3094,2713,3094,2713,2904,2522,2904,2522,2523,2333,2523,2333,1952,2333,1952,2333,1570,2522,1570,2522,1189,2903,1189,2903,1000,3474,1000,3474,1000,3856,1000,3856,1189,4427,1189,4427,1570,4808,1570,4808,1952,4999,1952,4999,2523,4999,2523,4999,2904,4808,2904,4808,3284,4427};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii102 extends FontStrokes { // ''f''',1200],####new,,
    int kerning = 1200;
    int [] strokes = {2523,1000,2143,1000,2143,1000,1761,1190,1761,1190,1570,1761,1570,1761,1570,5000,1000,2333,2333,2333};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii103 extends FontStrokes { // ''g''',1200],#NEW,,
    int kerning = 1200;
    int [] strokes = {3286,2333,3286,5379,3286,5379,3095,5951,3095,5951,2904,6142,2904,6142,2524,6331,2524,6331,1952,6331,1952,6331,1572,6142,3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii104 extends FontStrokes { // ''h''',1200],###new,,
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,1000,3095,1570,2523,1570,2523,1952,2333,1952,2333,2523,2333,2523,2333,2904,2523,2904,2523,3095,3095,3095,3095,3095,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii105 extends FontStrokes { // ''i''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1190,2324,1190,4990,1000,990,1190,1181,1190,1181,1381,990,1381,990,1190,800,1190,800,1000,990};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii106 extends FontStrokes { // ''j''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1952,2324,1952,5561,1952,5561,1761,6133,1761,6133,1380,6323,1380,6323,1000,6323,1761,990,1952,1181,1952,1181,2143,990,2143,990,1952,800,1952,800,1761,990};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii107 extends FontStrokes { // ''k''',1200],##new,,
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000,2904,2333,1000,4238,1761,3475,3095,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii108 extends FontStrokes { // ''l''',1200],##new,,
    int kerning = 1200;
    int [] strokes = {1000,1000,1000,5000};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii109 extends FontStrokes { // ''m''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,1000,4999,1000,3094,1570,2522,1570,2522,1952,2333,1952,2333,2523,2333,2523,2333,2904,2522,2904,2522,3094,3094,3094,3094,3094,4999,3094,3094,3666,2522,3666,2522,4046,2333,4046,2333,4618,2333,4618,2333,4998,2522,4998,2522,5189,3094,5189,3094,5189,4999};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii110 extends FontStrokes { // ''n''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,1000,4999,1000,3094,1572,2522,1572,2522,1952,2333,1952,2333,2524,2333,2524,2333,2904,2522,2904,2522,3095,3094,3095,3094,3095,4999};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii111 extends FontStrokes { // ''o''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1952,2333,1570,2522,1570,2522,1189,2903,1189,2903,1000,3474,1000,3474,1000,3856,1000,3856,1189,4427,1189,4427,1570,4808,1570,4808,1952,4999,1952,4999,2523,4999,2523,4999,2904,4808,2904,4808,3284,4427,3284,4427,3475,3856,3475,3856,3475,3474,3475,3474,3284,2903,3284,2903,2904,2522,2904,2522,2523,2333,2523,2333,1952,2333};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii112 extends FontStrokes { // ''p''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,1000,6333,1000,2903,1381,2523,1381,2523,1761,2333,1761,2333,2333,2333,2333,2333,2714,2523,2714,2523,3095,2903,3095,2903,3286,3476,3286,3476,3286,3856,3286,3856,3095,4428,3095,4428,2714,4808,2714,4808,2333,4999,2333,4999,1761,4999,1761,4999,1381,4808,1381,4808,1000,4428};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii113 extends FontStrokes { // ''q''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {3285,2333,3285,6333,3285,2903,2904,2523,2904,2523,2524,2333,2524,2333,1952,2333,1952,2333,1571,2523,1571,2523,1190,2903,1190,2903,1000,3476,1000,3476,1000,3856,1000,3856,1190,4428,1190,4428,1571,4808,1571,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3285,4428};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii114 extends FontStrokes { // ''r''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,1000,4999,1000,3476,1190,2903,1190,2903,1572,2523,1572,2523,1952,2333,1952,2333,2524,2333};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii115 extends FontStrokes { // ''s''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {3094,2903,2904,2523,2904,2523,2332,2333,2332,2333,1761,2333,1761,2333,1189,2523,1189,2523,1000,2903,1000,2903,1189,3285,1189,3285,1570,3476,1570,3476,2523,3666,2523,3666,2904,3856,2904,3856,3094,4237,3094,4237,3094,4428,3094,4428,2904,4808,2904,4808,2332,4999,2332,4999,1761,4999,1761,4999,1189,4808,1189,4808,1000,4428};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii116 extends FontStrokes { // ''t''',1200],##new,,
    int kerning = 1200;
    int [] strokes = {1572,1000,1572,4238,1572,4238,1761,4809,1761,4809,2143,5000,2143,5000,2524,5000,1000,2333,2333,2333};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii117 extends FontStrokes { // ''u''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,1000,4237,1000,4237,1190,4808,1190,4808,1572,4999,1572,4999,2143,4999,2143,4999,2524,4808,2524,4808,3095,4237,3095,2333,3095,4999};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii118 extends FontStrokes { // ''v''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,2142,4999,3284,2333,2142,4999};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii119 extends FontStrokes { // ''w''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,1761,4999,2523,2333,1761,4999,2523,2333,3286,4999,4047,2333,3286,4999};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii120 extends FontStrokes { // ''x''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1000,2333,3095,4999,3095,2333,1000,4999};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii121 extends FontStrokes { // ''y''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {1190,2333,2333,4999,3476,2333,2333,4999,2333,4999,1952,5760,1952,5760,1572,6142,1572,6142,1190,6333,1190,6333,1000,6333};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii122 extends FontStrokes { // ''z''',1200],#new,,
    int kerning = 1200;
    int [] strokes = {3095,2333,1000,4999,1000,2333,3095,2333,1000,4999,3095,4999};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii123 extends FontStrokes { // ''{''',1200]
    int kerning = 1200;
    int [] strokes = {1952,0,1572,191,1572,191,1381,380,1381,380,1191,762,1191,762,1191,1143,1191,1143,1381,1523,1381,1523,1572,1714,1572,1714,1763,2095,1763,2095,1763,2475,1763,2475,1381,2857,1572,191,1381,571,1381,571,1381,952,1381,952,1572,1332,1572,1332,1763,1523,1763,1523,1952,1905,1952,1905,1952,2285,1952,2285,1763,2666,1763,2666,1000,3048,1000,3048,1763,3428,1763,3428,1952,3809,1952,3809,1952,4189,1952,4189,1763,4571,1763,4571,1572,4761,1572,4761,1381,5142,1381,5142,1381,5523,1381,5523,1572,5904,1381,3237,1763,3619,1763,3619,1763,4000,1763,4000,1572,4380,1572,4380,1381,4571,1381,4571,1191,4952,1191,4952,1191,5332,1191,5332,1381,5714,1381,5714,1572,5904,1572,5904,1952,6094};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii124 extends FontStrokes { // ''|''',1200]
    int kerning = 1200;
    int [] strokes = {1000,250,1000,5750};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii125 extends FontStrokes { // ''}''',1200]
    int kerning = 1200;
    int [] strokes = {1000,0,1381,191,1381,191,1572,380,1572,380,1762,762,1762,762,1762,1143,1762,1143,1572,1523,1572,1523,1381,1714,1381,1714,1191,2095,1191,2095,1191,2475,1191,2475,1572,2857,1381,191,1572,571,1572,571,1572,952,1572,952,1381,1332,1381,1332,1191,1523,1191,1523,1000,1905,1000,1905,1000,2285,1000,2285,1191,2666,1191,2666,1952,3048,1952,3048,1191,3428,1191,3428,1000,3809,1000,3809,1000,4189,1000,4189,1191,4571,1191,4571,1381,4761,1381,4761,1572,5142,1572,5142,1572,5523,1572,5523,1381,5904,1572,3237,1191,3619,1191,3619,1191,4000,1191,4000,1381,4380,1381,4380,1572,4571,1572,4571,1762,4952,1762,4952,1762,5332,1762,5332,1572,5714,1572,5714,1381,5904,1381,5904,1000,6094};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
  private static class Ascii126 extends FontStrokes { // ''~''',1200]
    int kerning = 1200;
    int [] strokes = {1000,3643,1000,3262,1000,3262,1191,2691,1191,2691,1572,2500,1572,2500,1952,2500,1952,2500,2334,2691,2334,2691,3095,3262,3095,3262,3477,3452,3477,3452,3857,3452,3857,3452,4238,3262,4238,3262,4429,2881,1000,3262,1191,2881,1191,2881,1572,2691,1572,2691,1952,2691,1952,2691,2334,2881,2334,2881,3095,3452,3095,3452,3477,3643,3477,3643,3857,3643,3857,3643,4238,3452,4238,3452,4429,2881,4429,2881,4429,2500};
    int thickness = 800;
    public String element(int xOffset, double magRatio, boolean metric) {
      return generateSilk(strokes, thickness, kerning, xOffset, magRatio, metric);
    }
    public int width() {
      return (maximumWidth(strokes) + kerning);
    }
  }
}
