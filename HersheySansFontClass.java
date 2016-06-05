// HersheySansFontClass.java v1.0
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
//    HersheySansFontClass Copyright (C) 2015 Erich S. Heinzle a1039181@gmail.com

//
//  A font class for use with gEDA PCB for generating silkscreen text
//  which can be added to footprints for labelling purposes.
//  The class implements the free Hershey Sans 1 Stroke Font and outputs
//  0.01mil (imperial, square bracketed) units. 
//

import java.util.ArrayList;
//import java.util.Printwriter;
import java.io.*;

public class HersheySansFontClass {

  // we start by defining the jagged long array of stroked font data
  // {width, kerning, thickness}, followed by {x1,y2, x2,y2, ... , xn,yn}
  // for ascii characters 32 -> 126 inclusive, so, 95 characters encoded in total
  // widthstroke thickness of 800 centimils is a bit thick, so will try 545
  long [][] fontData = {{1800, 1800, 800}, {}, 
                        {1581, 1200, 800}, 
                        {1191,1000,1191,3666,1191,4619,1000,4809,1000,4809,1191,4999,1191,4999,1381,4809,1381,4809,1191,4619}, 
                        {2723, 1200, 800}, 
                        {1000,1000,1000,2334,2523,1000,2523,2334}, 
                        {4407, 1200, 800}, 
                        {2573,1000,1239,5000,3916,1000,2582,5000,1339,2325,4207,2325,1000,3675,3866,3675}, 
                        {3866, 1200, 800}, 
                        {2333,300,2333,5824,3666,1633,3286,1252,3286,1252,2714,1062,2714,1062,1952,1062,1952,1062,1381,1252,1381,1252,1000,1633,1000,1633,1000,2015,1000,2015,1191,2395,1191,2395,1381,2586,1381,2586,1762,2776,1762,2776,2905,3157,2905,3157,3286,3347,3286,3347,3477,3538,3477,3538,3666,3919,3666,3919,3666,4490,3666,4490,3286,4872,3286,4872,2714,5062,2714,5062,1952,5062,1952,5062,1381,4872,1381,4872,1000,4490},
                        {4628, 1200, 800},
                        {11285,1000,7857,4999,8809,1000,9191,1380,9191,1380,9191,1762,9191,1762,9000,2142,9000,2142,8619,2333,8619,2333,8238,2333,8238,2333,7857,1952,7857,1952,7857,1571,7857,1571,8048,1190,8048,1190,8428,1000,8428,1000,8809,1000,8809,1000,9191,1190,9191,1190,9762,1380,9762,1380,10332,1380,10332,1380,10905,1190,10905,1190,11285,1000,10523,3666,10143,3857,10143,3857,9952,4237,9952,4237,9952,4619,9952,4619,10332,4999,10332,4999,10714,4999,10714,4999,11095,4809,11095,4809,11285,4428,11285,4428,11285,4047,11285,4047,10905,3666,10905,3666,10523,3666},
                        {5057, 1200, 800}, 
                        {4809,2714,4809,2523,4809,2523,4618,2333,4618,2333,4428,2333,4428,2333,4237,2523,4237,2523,4046,2905,4046,2905,3666,3857,3666,3857,3285,4428,3285,4428,2905,4809,2905,4809,2523,4999,2523,4999,1762,4999,1762,4999,1380,4809,1380,4809,1189,4619,1189,4619,1000,4237,1000,4237,1000,3857,1000,3857,1189,3476,1189,3476,1380,3285,1380,3285,2714,2523,2714,2523,2905,2333,2905,2333,3094,1952,3094,1952,3094,1571,3094,1571,2905,1190,2905,1190,2523,1000,2523,1000,2142,1190,2142,1190,1952,1571,952,1571,1952,1952,1952,1952,2142,2523,2142,2523,2523,3095,2523,3095,3475,4428,3475,4428,3857,4809,3857,4809,4237,4999,4237,4999,4618,4999,4618,4999,4809,4809,4809,4809,4809,4619}, 
                        {1581, 1200, 800}, 
                        {1191,1381,1000,1191,1000,1191,1191,1000,1191,1000,1381,1191,1381,1191,1381,1571,1381,1571,1191,1952,1191,1952,1000,2143}, 
                        {2533, 1200, 800}, 
                        {2333,0,1952,381,1952,381,1571,952,1571,952,1189,1715,1189,1715,1000,2667,1000,2667,1000,3429,1000,3429,1189,4381,1189,4381,1571,5143,1571,5143,1952,5714,1952,5714,2333,6095}, 
                        {2532, 1200, 800}, 
                        {1000,0,1380,381,1380,381,1762,952,1762,952,2143,1715,2143,1715,2332,2667,2332,2667,2332,3429,2332,3429,2143,4381,2143,4381,1762,5143,1762,5143,1380,5714,1380,5714,1000,6095}, 
                        {4533, 1200, 800}, 
                        {2667,1000,2667,5000,1000,2001,4333,4001,4333,2001,1000,4001}, 
                        {4628, 1200, 800}, 
                        {2714,1250,2714,4678,1000,2964,4428,2964}, {1581, 1200, 800}, 
                        {1381,4891,1191,5080,1191,5080,1000,4891,1000,4891,1191,4700,1191,4700,1381,4891,1381,4891,1381,5271,1381,5271,1000,5652}, 
                        {3700, 1200, 800}, 
                        {0,3000,2500,3000}, 
                        {1580, 1200, 800}, 
                        {1190,4700,1000,4891,1000,4891,1190,5080,1190,5080,1380,4891,1380,4891,1190,4700}, 
                        {3451, 1200, 800}, 
                        {3251,1000,1000,5000}, 
                        {3867, 1200, 800}, 
                        {2143,1000,1572,1190,1572,1190,1190,1761,1190,1761,1000,2713,1000,2713,1000,3285,1000,3285,1190,4238,1190,4238,1572,4809,1572,4809,2143,5000,2143,5000,2524,5000,2524,5000,3095,4809,3095,4809,3476,4238,3476,4238,3667,3285,3667,3285,3667,2713,3667,2713,3476,1761,3476,1761,3095,1190,3095,1190,2524,1000,2524,1000,2143,1000}, 
                        {2152, 1200, 800}, 
                        {1000,1761,1380,1571,1380,1571,1952,1000,1952,1000,1952,5000},
                        {3866, 1200, 800}, 
                        {1190,1952,1190,1761,1190,1761,1381,1381,1381,1381,1570,1190,1570,1190,1952,1000,1952,1000,2713,1000,2713,1000,3095,1190,3095,1190,3285,1381,3285,1381,3475,1761,3475,1761,3475,2143,3475,2143,3285,2524,3285,2524,2904,3095,2904,3095,1000,5000,1000,5000,3666,5000}, 
                        {3866, 1200, 800}, 
                        {1380,1000,3475,1000,3475,1000,2332,2524,2332,2524,2904,2524,2904,2524,3284,2713,3284,2713,3475,2904,3475,2904,3666,3476,3666,3476,3666,3856,3666,3856,3475,4428,3475,4428,3095,4809,3095,4809,2523,5000,2523,5000,1952,5000,1952,5000,1380,4809,1380,4809,1190,4618,1190,4618,1000,4238}, 
                        {4056, 1200, 800}, 
                        {2904,1000,1000,3666,1000,3666,3856,3666,2904,1000,2904,5000}, 
                        {3867, 1200, 800}, 
                        {3286,1000,1381,1000,1381,1000,1190,2713,1190,2713,1381,2524,1381,2524,1952,2333,1952,2333,2524,2333,2524,2333,3095,2524,3095,2524,3476,2904,3476,2904,3667,3476,3667,3476,3667,3856,3667,3856,3476,4428,3476,4428,3095,4809,3095,4809,2524,5000,2524,5000,1952,5000,1952,5000,1381,4809,1381,4809,1190,4618,1190,4618,1000,4238}, 
                        {3675, 1200, 800}, 
                        {3285,1571,3095,1190,3095,1190,2523,1000,2523,1000,2143,1000,2143,1000,1570,1190,1570,1190,1190,1761,1190,1761,1000,2713,1000,2713,1000,3666,1000,3666,1190,4428,1190,4428,1570,4809,1570,4809,2143,5000,2143,5000,2333,5000,2333,5000,2904,4809,2904,4809,3285,4428,3285,4428,3475,3856,3475,3856,3475,3666,3475,3666,3285,3095,3285,3095,2904,2713,2904,2713,2333,2524,2333,2524,2143,2524,2143,2524,1570,2713,1570,2713,1190,3095,1190,3095,1000,3666}, 
                        {3866, 1200, 800}, 
                        {3666,1000,1761,5000,1000,1000,3666,1000}, 
                        {3866, 1200, 800}, 
                        {1952,1000,1380,1190,1380,1190,1190,1571,1190,1571,1190,1952,1190,1952,1380,2333,1380,2333,1761,2524,1761,2524,2523,2713,2523,2713,3095,2904,3095,2904,3475,3285,3475,3285,3666,3666,3666,3666,3666,4238,3666,4238,3475,4618,3475,4618,3284,4809,3284,4809,2713,5000,2713,5000,1952,5000,1952,5000,1380,4809,1380,4809,1190,4618,1190,4618,1000,4238,1000,4238,1000,3666,1000,3666,1190,3285,1190,3285,1570,2904,1570,2904,2142,2713,2142,2713,2904,2524,2904,2524,3284,2333,3284,2333,3475,1952,3475,1952,3475,1571,3475,1571,3284,1190,3284,1190,2713,1000,2713,1000,1952,1000}, 
                        {3675, 1200, 800}, 
                        {3475,2333,3285,2904,3285,2904,2904,3285,2904,3285,2332,3476,2332,3476,2142,3476,2142,3476,1571,3285,1571,3285,1189,2904,1189,2904,1000,2333,1000,2333,1000,2143,1000,2143,1189,1571,1189,1571,1571,1190,1571,1190,2142,1000,2142,1000,2332,1000,2332,1000,2904,1190,2904,1190,3285,1571,3285,1571,3475,2333,3475,2333,3475,3285,3475,3285,3285,4238,3285,4238,2904,4809,2904,4809,2332,5000,2332,5000,1952,5000,1952,5000,1380,4809,1380,4809,1189,4428}, 
                        {1580, 1200, 800}, 
                        {1190,3400,1000,3591,1000,3591,1190,3781,1190,3781,1380,3591,1380,3591,1190,3400,1190,4734,1000,4925,1000,4925,1190,5114,1190,5114,1380,4925,1380,4925,1190,4734}, 
                        {1580, 1200, 800}, 
                        {1191,3400,1000,3591,1000,3591,1191,3781,1191,3781,1380,3591,1380,3591,1191,3400,1380,4925,1191,5114,1191,5114,1000,4925,1000,4925,1191,4734,1191,4734,1380,4925,1380,4925,1380,5305,1380,5305,1000,5686}, 
                        {3740, 1200, 800}, 
                        {3540,1000,1000,3001,1000,3001,3540,5000}, 
                        {3700, 1200, 800}, 
                        {0,2400,2500,2400,0,3600,2500,3600}, 
                        {3740, 1200, 800}, 
                        {1000,1000,3540,3001,3540,3001,1000,5000}, 
                        {3485, 1200, 800}, 
                        {1000,1952,1000,1762,1000,1762,1191,1381,1191,1381,1380,1191,1380,1191,1762,1000,1762,1000,2523,1000,2523,1000,2905,1191,2905,1191,3095,1381,3095,1381,3285,1762,3285,1762,3285,2143,3285,2143,3095,2523,3095,2523,2905,2714,2905,2714,2143,3095,2143,3095,2143,3666,2143,4618,1952,4809,1952,4809,2143,5000,2143,5000,2332,4809,2332,4809,2143,4618}, 
                        {6451, 1200, 800}, 
                        {4751,3000,4500,2499,4500,2499,4001,2250,4001,2250,3250,2250,3250,2250,2751,2499,2751,2499,2500,2750,2500,2750,2250,3501,2250,3501,2250,4250,2250,4250,2500,4751,2500,4751,3001,5000,3001,5000,3751,5000,3751,5000,4251,4751,4251,4751,4500,4250,3250,2250,2751,2750,2751,2750,2500,3501,2500,3501,2500,4250,2500,4250,2751,4751,2751,4751,3001,5000,4751,2250,4500,4250,4500,4250,4500,4751,4500,4751,5001,5000,5001,5000,5502,5000,5502,5000,6001,4501,6001,4501,6251,3750,6251,3750,6251,3250,6251,3250,6001,2499,6001,2499,5751,2000,5751,2000,5251,1499,5251,1499,4751,1249,4751,1249,4001,1000,4001,1000,3250,1000,3250,1000,2500,1249,2500,1249,2000,1499,2000,1499,1501,2000,1501,2000,1250,2499,1250,2499,1000,3250,1000,3250,1000,4000,1000,4000,1250,4751,1250,4751,1501,5250,1501,5250,2000,5751,2000,5751,2500,6001,2500,6001,3250,6250,3250,6250,4001,6250,4001,6250,4751,6001,4751,6001,5251,5751,5251,5751,5502,5501,5001,2250,4751,4250,4751,4250,4751,4751,4751,4751,5001,5000}, 
                        {4248, 1200, 800}, 
                        {2524,1000,1000,5000,2524,1000,4048,5000,1572,3667,3477,3667}, 
                        {3866, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1763,3666,1763,3666,2143,3666,2143,3476,2524,3476,2524,3285,2715,3285,2715,2714,2905,1000,2905,2714,2905,2714,2905,3285,3095,3285,3095,3476,3286,3476,3286,3666,3667,3666,3667,3666,4238,3666,4238,3476,4620,3476,4620,3285,4809,3285,4809,2714,5000,2714,5000,1000,5000}, 
                        {4057, 1200, 800}, 
                        {3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000,2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3477,1000,3477,1190,4048,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048}, 
                        {3866, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,2334,1000,2334,1000,2905,1191,2905,1191,3286,1572,3286,1572,3476,1952,3476,1952,3666,2524,3666,2524,3666,3477,3666,3477,3476,4048,3476,4048,3286,4429,3286,4429,2905,4809,2905,4809,2334,5000,2334,5000,1000,5000}, 
                        {3676, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,3476,1000,1000,2905,2523,2905,1000,5000,3476,5000}, 
                        {3675, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,3475,1000,1000,2905,2523,2905}, 
                        {4057, 1200, 800}, 
                        {3857,1952,3666,1572,3666,1572,3286,1191,3286,1191,2905,1000,2905,1000,2143,1000,2143,1000,1762,1191,1762,1191,1381,1572,1381,1572,1191,1952,1191,1952,1000,2524,1000,2524,1000,3477,1000,3477,1191,4048,1191,4048,1381,4429,1381,4429,1762,4809,1762,4809,2143,5000,2143,5000,2905,5000,2905,5000,3286,4809,3286,4809,3666,4429,3666,4429,3857,4048,3857,4048,3857,3477,2905,3477,3857,3477}, 
                        {3867, 1200, 800}, 
                        {1000,1000,1000,5000,3667,5000,3667,1000,1000,2905,3667,2905}, 
                        {2200, 1200, 800}, 
                        {0,1000,1000,1000,500,1000,500,5000,0,5000,1000,5000}, 
                        {3105, 1200, 800}, 
                        {2905,1000,2905,4048,2905,4048,2715,4620,2715,4620,2524,4809,2524,4809,2143,5000,2143,5000,1763,5000,1763,5000,1381,4809,1381,4809,1191,4620,1191,4620,1000,4048,1000,4048,1000,3667}, 
                        {3866, 1200, 800}, 
                        {1000,1000,1000,5000,3666,1000,1000,3667,1952,2715,3666,5000},
                        {3485, 1200, 800}, 
                        {1000,1000,1000,5000,1000,5000,3285,5000}, 
                        {4248, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,2523,5000,4048,1000,2523,5000,4048,1000,4048,5000}, 
                        {3866, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,3666,5000,3666,1000,3666,5000}, 
                        {4246, 1200, 800}, 
                        {2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3476,1000,3476,1190,4049,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048,3857,4048,4046,3476,4046,3476,4046,2524,4046,2524,3857,1952,3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000}, 
                        {3866, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1762,3666,1762,3666,2334,3666,2334,3476,2714,3476,2714,3285,2905,3285,2905,2714,3095,2714,3095,1000,3095}, 
                        {4247, 1200, 800}, 
                        {2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3476,1000,3476,1190,4048,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048,3857,4048,4047,3476,4047,3476,4047,2524,4047,2524,3857,1952,3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000,2714,4238,3857,5381}, 
                        {3866, 1200, 800}, 
                        {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1762,3666,1762,3666,2143,3666,2143,3476,2524,3476,2524,3285,2714,3285,2714,2714,2905,2714,2905,1000,2905,2333,2905,3666,5000}, 
                        {3866, 1200, 800}, 
                        {3666,1572,3285,1191,3285,1191,2714,1000,2714,1000,1952,1000,1952,1000,1380,1191,1380,1191,1000,1572,1000,1572,1000,1952,1000,1952,1189,2334,1189,2334,1380,2524,1380,2524,1762,2714,1762,2714,2905,3095,2905,3095,3285,3286,3285,3286,3475,3476,3475,3476,3666,3857,3666,3857,3666,4429,3666,4429,3285,4809,3285,4809,2714,5000,2714,5000,1952,5000,1952,5000,1380,4809,1380,4809,1000,4429}, 
                        {3866, 1200, 800}, 
                        {2333,1000,2333,5000,1000,1000,3666,1000}, 
                        {3866, 1200, 800}, 
                        {1000,1000,1000,3857,1000,3857,1191,4429,1191,4429,1571,4809,1571,4809,2143,5000,2143,5000,2523,5000,2523,5000,3095,4809,3095,4809,3476,4429,3476,4429,3666,3857,3666,3857,3666,1000}, 
                        {4248, 1200, 800}, 
                        {1000,1000,2523,5000,4048,1000,2523,5000}, 
                        {5009, 1200, 800}, 
                        {1000,1000,1952,5000,2905,1000,1952,5000,2905,1000,3857,5000,4809,1000,3857,5000}, 
                        {3866, 1200, 800}, 
                        {1000,1000,3666,5000,3666,1000,1000,5000}, 
                        {4248, 1200, 800}, 
                        {1000,1000,2523,2905,2523,2905,2523,5000,4048,1000,2523,2905}, 
                        {3866, 1200, 800}, 
                        {3666,1000,1000,5000,1000,1000,3666,1000,1000,5000,3666,5000}, 
                        {2534, 1200, 800}, 
                        {1000,0,1000,6094,1191,0,1191,6094,1000,0,2334,0,1000,6094,2334,6094}, 
                        {3451, 1200, 800}, 
                        {1000,1000,3251,5000}, 
                        {2533, 1200, 800}, 
                        {7666,0,7666,6094,7857,0,7857,6094,6524,0,7857,0,6524,6094,7857,6094}, 
                        {4246, 1200, 800}, 
                        {2523,1000,1000,3222,2523,1000,4046,3222}, 
                        {3700, 1200, 800}, 
                        {1000,5000,3500,5000}, 
                        {1581, 1200, 800}, 
                        {1191,1381,1000,1191,1000,1191,1191,1000,1191,1000,1381,1191,1381,1191,1381,1571,1381,1571,1191,1952,1191,1952,1000,2143}, 
                        {3486, 1200, 800}, 
                        {3286,2333,3286,4999,3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427}, 
                        {3486, 1200, 800}, 
                        {1000,1000,1000,5000,1000,2904,1381,2523,1381,2523,1761,2333,1761,2333,2333,2333,2333,2333,2713,2523,2713,2523,3095,2904,3095,2904,3286,3475,3286,3475,3286,3856,3286,3856,3095,4427,3095,4427,2713,4809,2713,4809,2333,5000,2333,5000,1761,5000,1761,5000,1381,4809,1381,4809,1000,4427}, 
                        {3486, 1200, 800}, 
                        {3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427}, 
                        {3484, 1200, 800}, 
                        {3284,1000,3284,5000,3284,2904,2904,2523,2904,2523,2523,2333,2523,2333,1952,2333,1952,2333,1570,2523,1570,2523,1189,2904,1189,2904,1000,3475,1000,3475,1000,3856,1000,3856,1189,4427,1189,4427,1570,4809,1570,4809,1952,5000,1952,5000,2523,5000,2523,5000,2904,4809,2904,4809,3284,4427}, 
                        {3484, 1200, 800}, 
                        {1000,3474,3284,3474,3284,3474,3284,3094,3284,3094,3094,2713,3094,2713,2904,2522,2904,2522,2523,2333,2523,2333,1952,2333,1952,2333,1570,2522,1570,2522,1189,2903,1189,2903,1000,3474,1000,3474,1000,3856,1000,3856,1189,4427,1189,4427,1570,4808,1570,4808,1952,4999,1952,4999,2523,4999,2523,4999,2904,4808,2904,4808,3284,4427}, 
                        {2723, 1200, 800}, 
                        {2523,1000,2143,1000,2143,1000,1761,1190,1761,1190,1570,1761,1570,1761,1570,5000,1000,2333,2333,2333}, 
                        {3486, 1200, 800}, 
                        {3286,2333,3286,5379,3286,5379,3095,5951,3095,5951,2904,6142,2904,6142,2524,6331,2524,6331,1952,6331,1952,6331,1572,6142,3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427}, 
                        {3295, 1200, 800}, 
                        {1000,1000,1000,5000,1000,3095,1570,2523,1570,2523,1952,2333,1952,2333,2523,2333,2523,2333,2904,2523,2904,2523,3095,3095,3095,3095,3095,5000}, 
                        {1581, 1200, 800}, 
                        {1190,2324,1190,4990,1000,990,1190,1181,1190,1181,1381,990,1381,990,1190,800,1190,800,1000,990}, 
                        {2343, 1200, 800}, 
                        {1952,2324,1952,5561,1952,5561,1761,6133,1761,6133,1380,6323,1380,6323,1000,6323,1761,990,1952,1181,1952,1181,2143,990,2143,990,1952,800,1952,800,1761,990}, 
                        {3295, 1200, 800}, 
                        {1000,1000,1000,5000,2904,2333,1000,4238,1761,3475,3095,5000}, 
                        {1200, 1200, 800}, 
                        {1000,1000,1000,5000}, 
                        {5389, 1200, 800}, 
                        {1000,2333,1000,4999,1000,3094,1570,2522,1570,2522,1952,2333,1952,2333,2523,2333,2523,2333,2904,2522,2904,2522,3094,3094,3094,3094,3094,4999,3094,3094,3666,2522,3666,2522,4046,2333,4046,2333,4618,2333,4618,2333,4998,2522,4998,2522,5189,3094,5189,3094,5189,4999}, 
                        {3295, 1200, 800}, 
                        {1000,2333,1000,4999,1000,3094,1572,2522,1572,2522,1952,2333,1952,2333,2524,2333,2524,2333,2904,2522,2904,2522,3095,3094,3095,3094,3095,4999}, 
                        {3675, 1200, 800}, 
                        {1952,2333,1570,2522,1570,2522,1189,2903,1189,2903,1000,3474,1000,3474,1000,3856,1000,3856,1189,4427,1189,4427,1570,4808,1570,4808,1952,4999,1952,4999,2523,4999,2523,4999,2904,4808,2904,4808,3284,4427,3284,4427,3475,3856,3475,3856,3475,3474,3475,3474,3284,2903,3284,2903,2904,2522,2904,2522,2523,2333,2523,2333,1952,2333}, 
                        {3486, 1200, 800}, 
                        {1000,2333,1000,6333,1000,2903,1381,2523,1381,2523,1761,2333,1761,2333,2333,2333,2333,2333,2714,2523,2714,2523,3095,2903,3095,2903,3286,3476,3286,3476,3286,3856,3286,3856,3095,4428,3095,4428,2714,4808,2714,4808,2333,4999,2333,4999,1761,4999,1761,4999,1381,4808,1381,4808,1000,4428}, 
                        {3485, 1200, 800}, 
                        {3285,2333,3285,6333,3285,2903,2904,2523,2904,2523,2524,2333,2524,2333,1952,2333,1952,2333,1571,2523,1571,2523,1190,2903,1190,2903,1000,3476,1000,3476,1000,3856,1000,3856,1190,4428,1190,4428,1571,4808,1571,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3285,4428}, 
                        {2724, 1200, 800}, 
                        {1000,2333,1000,4999,1000,3476,1190,2903,1190,2903,1572,2523,1572,2523,1952,2333,1952,2333,2524,2333}, 
                        {3294, 1200, 800}, 
                        {3094,2903,2904,2523,2904,2523,2332,2333,2332,2333,1761,2333,1761,2333,1189,2523,1189,2523,1000,2903,1000,2903,1189,3285,1189,3285,1570,3476,1570,3476,2523,3666,2523,3666,2904,3856,2904,3856,3094,4237,3094,4237,3094,4428,3094,4428,2904,4808,2904,4808,2332,4999,2332,4999,1761,4999,1761,4999,1189,4808,1189,4808,1000,4428}, 
                        {2724, 1200, 800}, 
                        {1572,1000,1572,4238,1572,4238,1761,4809,1761,4809,2143,5000,2143,5000,2524,5000,1000,2333,2333,2333}, 
                        {3295, 1200, 800}, 
                        {1000,2333,1000,4237,1000,4237,1190,4808,1190,4808,1572,4999,1572,4999,2143,4999,2143,4999,2524,4808,2524,4808,3095,4237,3095,2333,3095,4999}, 
                        {3484, 1200, 800}, 
                        {1000,2333,2142,4999,3284,2333,2142,4999}, 
                        {4247, 1200, 800}, 
                        {1000,2333,1761,4999,2523,2333,1761,4999,2523,2333,3286,4999,4047,2333,3286,4999}, 
                        {3295, 1200, 800}, 
                        {1000,2333,3095,4999,3095,2333,1000,4999}, 
                        {3676, 1200, 800}, 
                        {1190,2333,2333,4999,3476,2333,2333,4999,2333,4999,1952,5760,1952,5760,1572,6142,1572,6142,1190,6333,1190,6333,1000,6333}, 
                        {3295, 1200, 800}, 
                        {3095,2333,1000,4999,1000,2333,3095,2333,1000,4999,3095,4999}, 
                        {2152, 1200, 800}, 
                        {1952,0,1572,191,1572,191,1381,380,1381,380,1191,762,1191,762,1191,1143,1191,1143,1381,1523,1381,1523,1572,1714,1572,1714,1763,2095,1763,2095,1763,2475,1763,2475,1381,2857,1572,191,1381,571,1381,571,1381,952,1381,952,1572,1332,1572,1332,1763,1523,1763,1523,1952,1905,1952,1905,1952,2285,1952,2285,1763,2666,1763,2666,1000,3048,1000,3048,1763,3428,1763,3428,1952,3809,1952,3809,1952,4189,1952,4189,1763,4571,1763,4571,1572,4761,1572,4761,1381,5142,1381,5142,1381,5523,1381,5523,1572,5904,1381,3237,1763,3619,1763,3619,1763,4000,1763,4000,1572,4380,1572,4380,1381,4571,1381,4571,1191,4952,1191,4952,1191,5332,1191,5332,1381,5714,1381,5714,1572,5904,1572,5904,1952,6094}, 
                        {1200, 1200, 800}, 
                        {1000,250,1000,5750}, 
                        {2152, 1200, 800}, 
                        {1000,0,1381,191,1381,191,1572,380,1572,380,1762,762,1762,762,1762,1143,1762,1143,1572,1523,1572,1523,1381,1714,1381,1714,1191,2095,1191,2095,1191,2475,1191,2475,1572,2857,1381,191,1572,571,1572,571,1572,952,1572,952,1381,1332,1381,1332,1191,1523,1191,1523,1000,1905,1000,1905,1000,2285,1000,2285,1191,2666,1191,2666,1952,3048,1952,3048,1191,3428,1191,3428,1000,3809,1000,3809,1000,4189,1000,4189,1191,4571,1191,4571,1381,4761,1381,4761,1572,5142,1572,5142,1572,5523,1572,5523,1381,5904,1572,3237,1191,3619,1191,3619,1191,4000,1191,4000,1381,4380,1381,4380,1572,4571,1572,4571,1762,4952,1762,4952,1762,5332,1762,5332,1572,5714,1572,5714,1381,5904,1381,5904,1000,6094}, 
                        {4629, 1200, 800}, 
                        {1000,3643,1000,3262,1000,3262,1191,2691,1191,2691,1572,2500,1572,2500,1952,2500,1952,2500,2334,2691,2334,2691,3095,3262,3095,3262,3477,3452,3477,3452,3857,3452,3857,3452,4238,3262,4238,3262,4429,2881,1000,3262,1191,2881,1191,2881,1572,2691,1572,2691,1952,2691,1952,2691,2334,2881,2334,2881,3095,3452,3095,3452,3477,3643,3477,3643,3857,3643,3857,3643,4238,3452,4238,3452,4429,2881,4429,2881,4429,2500} };


  double fontMagnificationRatio = 1.0;
  long defaultMinimumThickness = 1000; // in centimils, i.e. 10 mil line thickness minimum 

  boolean cyrillicMode = false;
  boolean greekMode = false;

  public void greekMode() {
    greekMode = true;
    if (cyrillicMode) {
      cyrillicMode = false; // can't be both
    }
  } 

  public void cyrillicMode() {
    cyrillicMode = true;
    if (greekMode) {
      greekMode = false; // can't be both
    }
  }

  String stringToRender = null;

  public void HersheyFontClass() {
  }

  public void HersheyFontClass(long minimumLineThickness) {
    defaultMinimumThickness = minimumLineThickness;
  }

  // this returns the character width in centimils
  public long width(int c) {
    // this is ((Xmax - Xmin) +  kerning), but not thickness

    int index = 0;
    if (cyrillicMode) {
      index = c-32;
      return (long)(HersheyCyrillic.fontData[index*2][0]*fontMagnificationRatio);

    } else if (c >= 1040 && c <= 1103) {
      index = c - 975;
      return (long)(HersheyCyrillic.fontData[index*2][0]*fontMagnificationRatio);
    } else {
      index = c-32;
      return (long)(HersheySans.fontData[index*2][0]*fontMagnificationRatio);
      //      return (long)(fontData[index*2][0]*fontMagnificationRatio);
    }
  }

  // this returns the character kerning in centimils
  public long kerning(int c) {
    int index = 0;
    System.out.println("Unicode char: " + c);
    if (cyrillicMode) {
      index = c-32;
      return (long)(HersheyCyrillic.fontData[index*2][1]*fontMagnificationRatio);
} else if (c >= 1040 && c <= 1103) {
      index = c - 975;
      System.out.println("Cyrillic index: " + index);
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
     System.out.println("Unicode char: " + c);
if (cyrillicMode) {
      index = c-32;
      calculatedThickness 
          = (long)(HersheyCyrillic.fontData[index*2][2]*fontMagnificationRatio);
} else if (c >= 1040 && c <= 1103) {
      index = c - 975;
      System.out.println("Cyrillic index: " + index);
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
    File tester = new File("thing.txt");
    PrintWriter output = new PrintWriter(tester);
    for (int i = 0; i < HersheyCyrillic.fontData.length; i = i+2) {
      String line = "{";
      for (int j = 0; j < HersheyCyrillic.fontData[i].length; j++) {
        line = line + HersheyCyrillic.fontData[i][j];
        if (j < (HersheyCyrillic.fontData[i].length - 1)) {
          line = line + ", ";
        }
      }
      line = line + "},\n{";
      for (int j = 0;
           j < HersheyCyrillic.fontData[i+1].length;
           j = j+2) {
        // can add x-offsets or y-offests etc to following line(s)
        line = line + (HersheyCyrillic.fontData[i+1][j]) + ","; // "x"
        line = line + HersheyCyrillic.fontData[i+1][j+1]; // "y"
        if (j < (HersheyCyrillic.fontData[i+1].length - 2)) {
          line = line + ",";
        }
      }
      line = line + "},";
      output.println(line);
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
    if (cyrillicMode) {
      strokes = HersheyCyrillic.fontData[index];
    } 

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

    if (cyrillicMode) {
      index = ((c-32)*2)+1;
    } else if (c >= 1040 && c <= 1103) {
      index = ((c - 975)*2)+1;
      System.out.println("DrawCentred index: " + index);
    } else {
      index = ((c-32)*2)+1;
    }

    // i.e. every second row of our array of long vectors has
    // the silk element descriptors

    //testing
    long [] strokes = fontData[index];
    if (cyrillicMode) {
      strokes = HersheyCyrillic.fontData[index];
    }
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


  // we define the jagged long array of stroked font data
  // {width, kerning, thickness},
  // followed by {x1,y2, x2,y2, ... , xn,yn}
  // for ascii characters 32 -> 126 inclusive, so,
  // 95 characters encoded in total for Hershey Sand Stroke font
  private static final class HersheySans {
    static final long [][] fontData = {{1800, 1800, 800}, {}, 
                                       {1581, 1200, 800}, 
                                       {1191,1000,1191,3666,1191,4619,1000,4809,1000,4809,1191,4999,1191,4999,1381,4809,1381,4809,1191,4619}, 
                                       {2723, 1200, 800}, 
                                       {1000,1000,1000,2334,2523,1000,2523,2334}, 
                                       {4407, 1200, 800}, 
                                       {2573,1000,1239,5000,3916,1000,2582,5000,1339,2325,4207,2325,1000,3675,3866,3675}, 
                                       {3866, 1200, 800}, 
                                       {2333,300,2333,5824,3666,1633,3286,1252,3286,1252,2714,1062,2714,1062,1952,1062,1952,1062,1381,1252,1381,1252,1000,1633,1000,1633,1000,2015,1000,2015,1191,2395,1191,2395,1381,2586,1381,2586,1762,2776,1762,2776,2905,3157,2905,3157,3286,3347,3286,3347,3477,3538,3477,3538,3666,3919,3666,3919,3666,4490,3666,4490,3286,4872,3286,4872,2714,5062,2714,5062,1952,5062,1952,5062,1381,4872,1381,4872,1000,4490},
                                       {4628, 1200, 800},
                                       {11285,1000,7857,4999,8809,1000,9191,1380,9191,1380,9191,1762,9191,1762,9000,2142,9000,2142,8619,2333,8619,2333,8238,2333,8238,2333,7857,1952,7857,1952,7857,1571,7857,1571,8048,1190,8048,1190,8428,1000,8428,1000,8809,1000,8809,1000,9191,1190,9191,1190,9762,1380,9762,1380,10332,1380,10332,1380,10905,1190,10905,1190,11285,1000,10523,3666,10143,3857,10143,3857,9952,4237,9952,4237,9952,4619,9952,4619,10332,4999,10332,4999,10714,4999,10714,4999,11095,4809,11095,4809,11285,4428,11285,4428,11285,4047,11285,4047,10905,3666,10905,3666,10523,3666},
                                       {5057, 1200, 800}, 
                                       {4809,2714,4809,2523,4809,2523,4618,2333,4618,2333,4428,2333,4428,2333,4237,2523,4237,2523,4046,2905,4046,2905,3666,3857,3666,3857,3285,4428,3285,4428,2905,4809,2905,4809,2523,4999,2523,4999,1762,4999,1762,4999,1380,4809,1380,4809,1189,4619,1189,4619,1000,4237,1000,4237,1000,3857,1000,3857,1189,3476,1189,3476,1380,3285,1380,3285,2714,2523,2714,2523,2905,2333,2905,2333,3094,1952,3094,1952,3094,1571,3094,1571,2905,1190,2905,1190,2523,1000,2523,1000,2142,1190,2142,1190,1952,1571,952,1571,1952,1952,1952,1952,2142,2523,2142,2523,2523,3095,2523,3095,3475,4428,3475,4428,3857,4809,3857,4809,4237,4999,4237,4999,4618,4999,4618,4999,4809,4809,4809,4809,4809,4619}, 
                                       {1581, 1200, 800}, 
                                       {1191,1381,1000,1191,1000,1191,1191,1000,1191,1000,1381,1191,1381,1191,1381,1571,1381,1571,1191,1952,1191,1952,1000,2143}, 
                                       {2533, 1200, 800}, 
                                       {2333,0,1952,381,1952,381,1571,952,1571,952,1189,1715,1189,1715,1000,2667,1000,2667,1000,3429,1000,3429,1189,4381,1189,4381,1571,5143,1571,5143,1952,5714,1952,5714,2333,6095}, 
                                       {2532, 1200, 800}, 
                                       {1000,0,1380,381,1380,381,1762,952,1762,952,2143,1715,2143,1715,2332,2667,2332,2667,2332,3429,2332,3429,2143,4381,2143,4381,1762,5143,1762,5143,1380,5714,1380,5714,1000,6095}, 
                                       {4533, 1200, 800}, 
                                       {2667,1000,2667,5000,1000,2001,4333,4001,4333,2001,1000,4001}, 
                                       {4628, 1200, 800}, 
                                       {2714,1250,2714,4678,1000,2964,4428,2964}, {1581, 1200, 800}, 
                                       {1381,4891,1191,5080,1191,5080,1000,4891,1000,4891,1191,4700,1191,4700,1381,4891,1381,4891,1381,5271,1381,5271,1000,5652}, 
                                       {3700, 1200, 800}, 
                                       {0,3000,2500,3000}, 
                                       {1580, 1200, 800}, 
                                       {1190,4700,1000,4891,1000,4891,1190,5080,1190,5080,1380,4891,1380,4891,1190,4700}, 
                                       {3451, 1200, 800}, 
                                       {3251,1000,1000,5000}, 
                                       {3867, 1200, 800}, 
                                       {2143,1000,1572,1190,1572,1190,1190,1761,1190,1761,1000,2713,1000,2713,1000,3285,1000,3285,1190,4238,1190,4238,1572,4809,1572,4809,2143,5000,2143,5000,2524,5000,2524,5000,3095,4809,3095,4809,3476,4238,3476,4238,3667,3285,3667,3285,3667,2713,3667,2713,3476,1761,3476,1761,3095,1190,3095,1190,2524,1000,2524,1000,2143,1000}, 
                                       {2152, 1200, 800}, 
                                       {1000,1761,1380,1571,1380,1571,1952,1000,1952,1000,1952,5000},
                                       {3866, 1200, 800}, 
                                       {1190,1952,1190,1761,1190,1761,1381,1381,1381,1381,1570,1190,1570,1190,1952,1000,1952,1000,2713,1000,2713,1000,3095,1190,3095,1190,3285,1381,3285,1381,3475,1761,3475,1761,3475,2143,3475,2143,3285,2524,3285,2524,2904,3095,2904,3095,1000,5000,1000,5000,3666,5000}, 
                                       {3866, 1200, 800}, 
                                       {1380,1000,3475,1000,3475,1000,2332,2524,2332,2524,2904,2524,2904,2524,3284,2713,3284,2713,3475,2904,3475,2904,3666,3476,3666,3476,3666,3856,3666,3856,3475,4428,3475,4428,3095,4809,3095,4809,2523,5000,2523,5000,1952,5000,1952,5000,1380,4809,1380,4809,1190,4618,1190,4618,1000,4238}, 
                                       {4056, 1200, 800}, 
                                       {2904,1000,1000,3666,1000,3666,3856,3666,2904,1000,2904,5000}, 
                                       {3867, 1200, 800}, 
                                       {3286,1000,1381,1000,1381,1000,1190,2713,1190,2713,1381,2524,1381,2524,1952,2333,1952,2333,2524,2333,2524,2333,3095,2524,3095,2524,3476,2904,3476,2904,3667,3476,3667,3476,3667,3856,3667,3856,3476,4428,3476,4428,3095,4809,3095,4809,2524,5000,2524,5000,1952,5000,1952,5000,1381,4809,1381,4809,1190,4618,1190,4618,1000,4238}, 
                                       {3675, 1200, 800}, 
                                       {3285,1571,3095,1190,3095,1190,2523,1000,2523,1000,2143,1000,2143,1000,1570,1190,1570,1190,1190,1761,1190,1761,1000,2713,1000,2713,1000,3666,1000,3666,1190,4428,1190,4428,1570,4809,1570,4809,2143,5000,2143,5000,2333,5000,2333,5000,2904,4809,2904,4809,3285,4428,3285,4428,3475,3856,3475,3856,3475,3666,3475,3666,3285,3095,3285,3095,2904,2713,2904,2713,2333,2524,2333,2524,2143,2524,2143,2524,1570,2713,1570,2713,1190,3095,1190,3095,1000,3666}, 
                                       {3866, 1200, 800}, 
                                       {3666,1000,1761,5000,1000,1000,3666,1000}, 
                                       {3866, 1200, 800}, 
                                       {1952,1000,1380,1190,1380,1190,1190,1571,1190,1571,1190,1952,1190,1952,1380,2333,1380,2333,1761,2524,1761,2524,2523,2713,2523,2713,3095,2904,3095,2904,3475,3285,3475,3285,3666,3666,3666,3666,3666,4238,3666,4238,3475,4618,3475,4618,3284,4809,3284,4809,2713,5000,2713,5000,1952,5000,1952,5000,1380,4809,1380,4809,1190,4618,1190,4618,1000,4238,1000,4238,1000,3666,1000,3666,1190,3285,1190,3285,1570,2904,1570,2904,2142,2713,2142,2713,2904,2524,2904,2524,3284,2333,3284,2333,3475,1952,3475,1952,3475,1571,3475,1571,3284,1190,3284,1190,2713,1000,2713,1000,1952,1000}, 
                                       {3675, 1200, 800}, 
                                       {3475,2333,3285,2904,3285,2904,2904,3285,2904,3285,2332,3476,2332,3476,2142,3476,2142,3476,1571,3285,1571,3285,1189,2904,1189,2904,1000,2333,1000,2333,1000,2143,1000,2143,1189,1571,1189,1571,1571,1190,1571,1190,2142,1000,2142,1000,2332,1000,2332,1000,2904,1190,2904,1190,3285,1571,3285,1571,3475,2333,3475,2333,3475,3285,3475,3285,3285,4238,3285,4238,2904,4809,2904,4809,2332,5000,2332,5000,1952,5000,1952,5000,1380,4809,1380,4809,1189,4428}, 
                                       {1580, 1200, 800}, 
                                       {1190,3400,1000,3591,1000,3591,1190,3781,1190,3781,1380,3591,1380,3591,1190,3400,1190,4734,1000,4925,1000,4925,1190,5114,1190,5114,1380,4925,1380,4925,1190,4734}, 
                                       {1580, 1200, 800}, 
                                       {1191,3400,1000,3591,1000,3591,1191,3781,1191,3781,1380,3591,1380,3591,1191,3400,1380,4925,1191,5114,1191,5114,1000,4925,1000,4925,1191,4734,1191,4734,1380,4925,1380,4925,1380,5305,1380,5305,1000,5686}, 
                                       {3740, 1200, 800}, 
                                       {3540,1000,1000,3001,1000,3001,3540,5000}, 
                                       {3700, 1200, 800}, 
                                       {0,2400,2500,2400,0,3600,2500,3600}, 
                                       {3740, 1200, 800}, 
                                       {1000,1000,3540,3001,3540,3001,1000,5000}, 
                                       {3485, 1200, 800}, 
                                       {1000,1952,1000,1762,1000,1762,1191,1381,1191,1381,1380,1191,1380,1191,1762,1000,1762,1000,2523,1000,2523,1000,2905,1191,2905,1191,3095,1381,3095,1381,3285,1762,3285,1762,3285,2143,3285,2143,3095,2523,3095,2523,2905,2714,2905,2714,2143,3095,2143,3095,2143,3666,2143,4618,1952,4809,1952,4809,2143,5000,2143,5000,2332,4809,2332,4809,2143,4618}, 
                                       {6451, 1200, 800}, 
                                       {4751,3000,4500,2499,4500,2499,4001,2250,4001,2250,3250,2250,3250,2250,2751,2499,2751,2499,2500,2750,2500,2750,2250,3501,2250,3501,2250,4250,2250,4250,2500,4751,2500,4751,3001,5000,3001,5000,3751,5000,3751,5000,4251,4751,4251,4751,4500,4250,3250,2250,2751,2750,2751,2750,2500,3501,2500,3501,2500,4250,2500,4250,2751,4751,2751,4751,3001,5000,4751,2250,4500,4250,4500,4250,4500,4751,4500,4751,5001,5000,5001,5000,5502,5000,5502,5000,6001,4501,6001,4501,6251,3750,6251,3750,6251,3250,6251,3250,6001,2499,6001,2499,5751,2000,5751,2000,5251,1499,5251,1499,4751,1249,4751,1249,4001,1000,4001,1000,3250,1000,3250,1000,2500,1249,2500,1249,2000,1499,2000,1499,1501,2000,1501,2000,1250,2499,1250,2499,1000,3250,1000,3250,1000,4000,1000,4000,1250,4751,1250,4751,1501,5250,1501,5250,2000,5751,2000,5751,2500,6001,2500,6001,3250,6250,3250,6250,4001,6250,4001,6250,4751,6001,4751,6001,5251,5751,5251,5751,5502,5501,5001,2250,4751,4250,4751,4250,4751,4751,4751,4751,5001,5000}, 
                                       {4248, 1200, 800}, 
                                       {2524,1000,1000,5000,2524,1000,4048,5000,1572,3667,3477,3667}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1763,3666,1763,3666,2143,3666,2143,3476,2524,3476,2524,3285,2715,3285,2715,2714,2905,1000,2905,2714,2905,2714,2905,3285,3095,3285,3095,3476,3286,3476,3286,3666,3667,3666,3667,3666,4238,3666,4238,3476,4620,3476,4620,3285,4809,3285,4809,2714,5000,2714,5000,1000,5000}, 
                                       {4057, 1200, 800}, 
                                       {3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000,2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3477,1000,3477,1190,4048,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,2334,1000,2334,1000,2905,1191,2905,1191,3286,1572,3286,1572,3476,1952,3476,1952,3666,2524,3666,2524,3666,3477,3666,3477,3476,4048,3476,4048,3286,4429,3286,4429,2905,4809,2905,4809,2334,5000,2334,5000,1000,5000}, 
                                       {3676, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,3476,1000,1000,2905,2523,2905,1000,5000,3476,5000}, 
                                       {3675, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,3475,1000,1000,2905,2523,2905}, 
                                       {4057, 1200, 800}, 
                                       {3857,1952,3666,1572,3666,1572,3286,1191,3286,1191,2905,1000,2905,1000,2143,1000,2143,1000,1762,1191,1762,1191,1381,1572,1381,1572,1191,1952,1191,1952,1000,2524,1000,2524,1000,3477,1000,3477,1191,4048,1191,4048,1381,4429,1381,4429,1762,4809,1762,4809,2143,5000,2143,5000,2905,5000,2905,5000,3286,4809,3286,4809,3666,4429,3666,4429,3857,4048,3857,4048,3857,3477,2905,3477,3857,3477}, 
                                       {3867, 1200, 800}, 
                                       {1000,1000,1000,5000,3667,5000,3667,1000,1000,2905,3667,2905}, 
                                       {2200, 1200, 800}, 
                                       {0,1000,1000,1000,500,1000,500,5000,0,5000,1000,5000}, 
                                       {3105, 1200, 800}, 
                                       {2905,1000,2905,4048,2905,4048,2715,4620,2715,4620,2524,4809,2524,4809,2143,5000,2143,5000,1763,5000,1763,5000,1381,4809,1381,4809,1191,4620,1191,4620,1000,4048,1000,4048,1000,3667}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,1000,5000,3666,1000,1000,3667,1952,2715,3666,5000},
                                       {3485, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,5000,3285,5000}, 
                                       {4248, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,2523,5000,4048,1000,2523,5000,4048,1000,4048,5000}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,3666,5000,3666,1000,3666,5000}, 
                                       {4246, 1200, 800}, 
                                       {2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3476,1000,3476,1190,4049,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048,3857,4048,4046,3476,4046,3476,4046,2524,4046,2524,3857,1952,3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1762,3666,1762,3666,2334,3666,2334,3476,2714,3476,2714,3285,2905,3285,2905,2714,3095,2714,3095,1000,3095}, 
                                       {4247, 1200, 800}, 
                                       {2142,1000,1762,1191,1762,1191,1380,1572,1380,1572,1190,1952,1190,1952,1000,2524,1000,2524,1000,3476,1000,3476,1190,4048,1190,4048,1380,4429,1380,4429,1762,4809,1762,4809,2142,5000,2142,5000,2905,5000,2905,5000,3285,4809,3285,4809,3666,4429,3666,4429,3857,4048,3857,4048,4047,3476,4047,3476,4047,2524,4047,2524,3857,1952,3857,1952,3666,1572,3666,1572,3285,1191,3285,1191,2905,1000,2905,1000,2142,1000,2714,4238,3857,5381}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,1000,2714,1000,2714,1000,3285,1191,3285,1191,3476,1381,3476,1381,3666,1762,3666,1762,3666,2143,3666,2143,3476,2524,3476,2524,3285,2714,3285,2714,2714,2905,2714,2905,1000,2905,2333,2905,3666,5000}, 
                                       {3866, 1200, 800}, 
                                       {3666,1572,3285,1191,3285,1191,2714,1000,2714,1000,1952,1000,1952,1000,1380,1191,1380,1191,1000,1572,1000,1572,1000,1952,1000,1952,1189,2334,1189,2334,1380,2524,1380,2524,1762,2714,1762,2714,2905,3095,2905,3095,3285,3286,3285,3286,3475,3476,3475,3476,3666,3857,3666,3857,3666,4429,3666,4429,3285,4809,3285,4809,2714,5000,2714,5000,1952,5000,1952,5000,1380,4809,1380,4809,1000,4429}, 
                                       {3866, 1200, 800}, 
                                       {2333,1000,2333,5000,1000,1000,3666,1000}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,1000,3857,1000,3857,1191,4429,1191,4429,1571,4809,1571,4809,2143,5000,2143,5000,2523,5000,2523,5000,3095,4809,3095,4809,3476,4429,3476,4429,3666,3857,3666,3857,3666,1000}, 
                                       {4248, 1200, 800}, 
                                       {1000,1000,2523,5000,4048,1000,2523,5000}, 
                                       {5009, 1200, 800}, 
                                       {1000,1000,1952,5000,2905,1000,1952,5000,2905,1000,3857,5000,4809,1000,3857,5000}, 
                                       {3866, 1200, 800}, 
                                       {1000,1000,3666,5000,3666,1000,1000,5000}, 
                                       {4248, 1200, 800}, 
                                       {1000,1000,2523,2905,2523,2905,2523,5000,4048,1000,2523,2905}, 
                                       {3866, 1200, 800}, 
                                       {3666,1000,1000,5000,1000,1000,3666,1000,1000,5000,3666,5000}, 
                                       {2534, 1200, 800}, 
                                       {1000,0,1000,6094,1191,0,1191,6094,1000,0,2334,0,1000,6094,2334,6094}, 
                                       {3451, 1200, 800}, 
                                       {1000,1000,3251,5000}, 
                                       {2533, 1200, 800}, 
                                       {7666,0,7666,6094,7857,0,7857,6094,6524,0,7857,0,6524,6094,7857,6094}, 
                                       {4246, 1200, 800}, 
                                       {2523,1000,1000,3222,2523,1000,4046,3222}, 
                                       {3700, 1200, 800}, 
                                       {1000,5000,3500,5000}, 
                                       {1581, 1200, 800}, 
                                       {1191,1381,1000,1191,1000,1191,1191,1000,1191,1000,1381,1191,1381,1191,1381,1571,1381,1571,1191,1952,1191,1952,1000,2143}, 
                                       {3486, 1200, 800}, 
                                       {3286,2333,3286,4999,3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427}, 
                                       {3486, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,2904,1381,2523,1381,2523,1761,2333,1761,2333,2333,2333,2333,2333,2713,2523,2713,2523,3095,2904,3095,2904,3286,3475,3286,3475,3286,3856,3286,3856,3095,4427,3095,4427,2713,4809,2713,4809,2333,5000,2333,5000,1761,5000,1761,5000,1381,4809,1381,4809,1000,4427}, 
                                       {3486, 1200, 800}, 
                                       {3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427}, 
                                       {3484, 1200, 800}, 
                                       {3284,1000,3284,5000,3284,2904,2904,2523,2904,2523,2523,2333,2523,2333,1952,2333,1952,2333,1570,2523,1570,2523,1189,2904,1189,2904,1000,3475,1000,3475,1000,3856,1000,3856,1189,4427,1189,4427,1570,4809,1570,4809,1952,5000,1952,5000,2523,5000,2523,5000,2904,4809,2904,4809,3284,4427}, 
                                       {3484, 1200, 800}, 
                                       {1000,3474,3284,3474,3284,3474,3284,3094,3284,3094,3094,2713,3094,2713,2904,2522,2904,2522,2523,2333,2523,2333,1952,2333,1952,2333,1570,2522,1570,2522,1189,2903,1189,2903,1000,3474,1000,3474,1000,3856,1000,3856,1189,4427,1189,4427,1570,4808,1570,4808,1952,4999,1952,4999,2523,4999,2523,4999,2904,4808,2904,4808,3284,4427}, 
                                       {2723, 1200, 800}, 
                                       {2523,1000,2143,1000,2143,1000,1761,1190,1761,1190,1570,1761,1570,1761,1570,5000,1000,2333,2333,2333}, 
                                       {3486, 1200, 800}, 
                                       {3286,2333,3286,5379,3286,5379,3095,5951,3095,5951,2904,6142,2904,6142,2524,6331,2524,6331,1952,6331,1952,6331,1572,6142,3286,2903,2904,2522,2904,2522,2524,2333,2524,2333,1952,2333,1952,2333,1572,2522,1572,2522,1190,2903,1190,2903,1000,3474,1000,3474,1000,3856,1000,3856,1190,4427,1190,4427,1572,4808,1572,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3286,4427}, 
                                       {3295, 1200, 800}, 
                                       {1000,1000,1000,5000,1000,3095,1570,2523,1570,2523,1952,2333,1952,2333,2523,2333,2523,2333,2904,2523,2904,2523,3095,3095,3095,3095,3095,5000}, 
                                       {1581, 1200, 800}, 
                                       {1190,2324,1190,4990,1000,990,1190,1181,1190,1181,1381,990,1381,990,1190,800,1190,800,1000,990}, 
                                       {2343, 1200, 800}, 
                                       {1952,2324,1952,5561,1952,5561,1761,6133,1761,6133,1380,6323,1380,6323,1000,6323,1761,990,1952,1181,1952,1181,2143,990,2143,990,1952,800,1952,800,1761,990}, 
                                       {3295, 1200, 800}, 
                                       {1000,1000,1000,5000,2904,2333,1000,4238,1761,3475,3095,5000}, 
                                       {1200, 1200, 800}, 
                                       {1000,1000,1000,5000}, 
                                       {5389, 1200, 800}, 
                                       {1000,2333,1000,4999,1000,3094,1570,2522,1570,2522,1952,2333,1952,2333,2523,2333,2523,2333,2904,2522,2904,2522,3094,3094,3094,3094,3094,4999,3094,3094,3666,2522,3666,2522,4046,2333,4046,2333,4618,2333,4618,2333,4998,2522,4998,2522,5189,3094,5189,3094,5189,4999}, 
                                       {3295, 1200, 800}, 
                                       {1000,2333,1000,4999,1000,3094,1572,2522,1572,2522,1952,2333,1952,2333,2524,2333,2524,2333,2904,2522,2904,2522,3095,3094,3095,3094,3095,4999}, 
                                       {3675, 1200, 800}, 
                                       {1952,2333,1570,2522,1570,2522,1189,2903,1189,2903,1000,3474,1000,3474,1000,3856,1000,3856,1189,4427,1189,4427,1570,4808,1570,4808,1952,4999,1952,4999,2523,4999,2523,4999,2904,4808,2904,4808,3284,4427,3284,4427,3475,3856,3475,3856,3475,3474,3475,3474,3284,2903,3284,2903,2904,2522,2904,2522,2523,2333,2523,2333,1952,2333}, 
                                       {3486, 1200, 800}, 
                                       {1000,2333,1000,6333,1000,2903,1381,2523,1381,2523,1761,2333,1761,2333,2333,2333,2333,2333,2714,2523,2714,2523,3095,2903,3095,2903,3286,3476,3286,3476,3286,3856,3286,3856,3095,4428,3095,4428,2714,4808,2714,4808,2333,4999,2333,4999,1761,4999,1761,4999,1381,4808,1381,4808,1000,4428}, 
                                       {3485, 1200, 800}, 
                                       {3285,2333,3285,6333,3285,2903,2904,2523,2904,2523,2524,2333,2524,2333,1952,2333,1952,2333,1571,2523,1571,2523,1190,2903,1190,2903,1000,3476,1000,3476,1000,3856,1000,3856,1190,4428,1190,4428,1571,4808,1571,4808,1952,4999,1952,4999,2524,4999,2524,4999,2904,4808,2904,4808,3285,4428}, 
                                       {2724, 1200, 800}, 
                                       {1000,2333,1000,4999,1000,3476,1190,2903,1190,2903,1572,2523,1572,2523,1952,2333,1952,2333,2524,2333}, 
                                       {3294, 1200, 800}, 
                                       {3094,2903,2904,2523,2904,2523,2332,2333,2332,2333,1761,2333,1761,2333,1189,2523,1189,2523,1000,2903,1000,2903,1189,3285,1189,3285,1570,3476,1570,3476,2523,3666,2523,3666,2904,3856,2904,3856,3094,4237,3094,4237,3094,4428,3094,4428,2904,4808,2904,4808,2332,4999,2332,4999,1761,4999,1761,4999,1189,4808,1189,4808,1000,4428}, 
                                       {2724, 1200, 800}, 
                                       {1572,1000,1572,4238,1572,4238,1761,4809,1761,4809,2143,5000,2143,5000,2524,5000,1000,2333,2333,2333}, 
                                       {3295, 1200, 800}, 
                                       {1000,2333,1000,4237,1000,4237,1190,4808,1190,4808,1572,4999,1572,4999,2143,4999,2143,4999,2524,4808,2524,4808,3095,4237,3095,2333,3095,4999}, 
                                       {3484, 1200, 800}, 
                                       {1000,2333,2142,4999,3284,2333,2142,4999}, 
                                       {4247, 1200, 800}, 
                                       {1000,2333,1761,4999,2523,2333,1761,4999,2523,2333,3286,4999,4047,2333,3286,4999}, 
                                       {3295, 1200, 800}, 
                                       {1000,2333,3095,4999,3095,2333,1000,4999}, 
                                       {3676, 1200, 800}, 
                                       {1190,2333,2333,4999,3476,2333,2333,4999,2333,4999,1952,5760,1952,5760,1572,6142,1572,6142,1190,6333,1190,6333,1000,6333}, 
                                       {3295, 1200, 800}, 
                                       {3095,2333,1000,4999,1000,2333,3095,2333,1000,4999,3095,4999}, 
                                       {2152, 1200, 800}, 
                                       {1952,0,1572,191,1572,191,1381,380,1381,380,1191,762,1191,762,1191,1143,1191,1143,1381,1523,1381,1523,1572,1714,1572,1714,1763,2095,1763,2095,1763,2475,1763,2475,1381,2857,1572,191,1381,571,1381,571,1381,952,1381,952,1572,1332,1572,1332,1763,1523,1763,1523,1952,1905,1952,1905,1952,2285,1952,2285,1763,2666,1763,2666,1000,3048,1000,3048,1763,3428,1763,3428,1952,3809,1952,3809,1952,4189,1952,4189,1763,4571,1763,4571,1572,4761,1572,4761,1381,5142,1381,5142,1381,5523,1381,5523,1572,5904,1381,3237,1763,3619,1763,3619,1763,4000,1763,4000,1572,4380,1572,4380,1381,4571,1381,4571,1191,4952,1191,4952,1191,5332,1191,5332,1381,5714,1381,5714,1572,5904,1572,5904,1952,6094}, 
                                       {1200, 1200, 800}, 
                                       {1000,250,1000,5750}, 
                                       {2152, 1200, 800}, 
                                       {1000,0,1381,191,1381,191,1572,380,1572,380,1762,762,1762,762,1762,1143,1762,1143,1572,1523,1572,1523,1381,1714,1381,1714,1191,2095,1191,2095,1191,2475,1191,2475,1572,2857,1381,191,1572,571,1572,571,1572,952,1572,952,1381,1332,1381,1332,1191,1523,1191,1523,1000,1905,1000,1905,1000,2285,1000,2285,1191,2666,1191,2666,1952,3048,1952,3048,1191,3428,1191,3428,1000,3809,1000,3809,1000,4189,1000,4189,1191,4571,1191,4571,1381,4761,1381,4761,1572,5142,1572,5142,1572,5523,1572,5523,1381,5904,1572,3237,1191,3619,1191,3619,1191,4000,1191,4000,1381,4380,1381,4380,1572,4571,1572,4571,1762,4952,1762,4952,1762,5332,1762,5332,1572,5714,1572,5714,1381,5904,1381,5904,1000,6094}, 
                                       {4629, 1200, 800}, 
                                       {1000,3643,1000,3262,1000,3262,1191,2691,1191,2691,1572,2500,1572,2500,1952,2500,1952,2500,2334,2691,2334,2691,3095,3262,3095,3262,3477,3452,3477,3452,3857,3452,3857,3452,4238,3262,4238,3262,4429,2881,1000,3262,1191,2881,1191,2881,1572,2691,1572,2691,1952,2691,1952,2691,2334,2881,2334,2881,3095,3452,3095,3452,3477,3643,3477,3643,3857,3643,3857,3643,4238,3452,4238,3452,4429,2881,4429,2881,4429,2500} };

  } // end hershey sans stroke data

  // we use a jagged long array of stroked font data
  // {width, kerning, thickness}, followed by
  // {x1,y2, x2,y2, ... , xn,yn}
  // for the cyrillic
  private static final class HersheyCyrillic {
    static final long [][] fontData = {
      {2800, 1800, 800},
      {},
      {1581, 1200, 800},
      {1190,1380,1190,2523,1190,1000,1000,1380,1000,1380,1190,3666,1190,3666,1381,1380,1381,1380,1190,1000,1190,4618,1000,4809,1000,4809,1190,4998,1190,4998,1381,4809,1381,4809,1190,4618},
      {3104, 1200, 800},
      {1190,1000,1000,2332,1381,1000,1000,2332,2715,1000,2524,2332,2904,1000,2524,2332},
      {4407, 1200, 800},
      {2573,1000,1239,5000,3916,1000,2582,5000,1339,2325,4207,2325,1000,3675,3866,3675},
      {6152, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,1000,1000,2333,1000,1761,2904,3095,2904,3095,2904,3666,3094,3666,3094,3856,3284,3856,3284,4047,3666,4047,3666,4047,4237,4047,4237,3856,4618,3856,4618,3666,4809,3666,4809,3095,4998,3095,4998,1000,4998,3095,2904,3475,3094,3475,3094,3666,3284,3666,3284,3856,3666,3856,3666,3856,4237,3856,4237,3666,4618,3666,4618,3475,4809,3475,4809,3095,4998,5190,1000,5190,4998,5380,1000,5380,4998,4618,1000,5952,1000,4618,4998,5952,4998},
      {4629, 1200, 800},
      {1572,2333,1572,4999,1761,2333,1761,4999,3666,2333,3666,4999,3856,2333,3856,4999,1000,2333,2333,2333,3095,2333,4429,2333,1000,4999,4429,4999,4429,4999,4429,5951,4429,5951,4238,4999},
      {5389, 1200, 800},
      {1570,2333,1570,4999,1761,2333,1761,4999,1000,2333,2332,2333,1761,3666,2523,3666,2523,3666,3094,3857,3094,3857,3284,4237,3284,4237,3284,4428,3284,4428,3094,4809,3094,4809,2523,4999,2523,4999,1000,4999,2523,3666,2904,3857,2904,3857,3094,4237,3094,4237,3094,4428,3094,4428,2904,4809,2904,4809,2523,4999,4427,2333,4427,4999,4618,2333,4618,4999,3856,2333,5189,2333,3856,4999,5189,4999},
      {4809, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,4046,1000,4046,4998,4237,1000,4237,4998,1000,1000,2332,1000,3475,1000,4809,1000,1000,4998,4809,4998,4618,4998,4809,6332,4809,4998,4809,6332},
      {2723, 1200, 800},
      {2332,0,1952,381,1952,381,1570,952,1570,952,1190,1715,1190,1715,1000,2667,1000,2667,1000,3429,1000,3429,1190,4381,1190,4381,1570,5142,1570,5142,1952,5713,1952,5713,2332,6095,2332,6095,2523,6095,2332,0,2523,0,2523,0,2143,381,2143,381,1761,952,1761,952,1380,1715,1380,1715,1190,2667,1190,2667,1190,3429,1190,3429,1380,4381,1380,4381,1761,5142,1761,5142,2143,5713,2143,5713,2523,6095},
      {2724, 1200, 800},
      {1000,0,1381,381,1381,381,1761,952,1761,952,2143,1715,2143,1715,2333,2667,2333,2667,2333,3429,2333,3429,2143,4381,2143,4381,1761,5142,1761,5142,1381,5713,1381,5713,1000,6095,1000,6095,1190,6095,1000,0,1190,0,1190,0,1572,381,1572,381,1952,952,1952,952,2333,1715,2333,1715,2524,2667,2524,2667,2524,3429,2524,3429,2333,4381,2333,4381,1952,5142,1952,5142,1572,5713,1572,5713,1190,6095},
      {3104, 1200, 800},
      {1952,1000,1952,3286,1000,1572,2904,2715,2904,1572,1000,2715},
      {4628, 1200, 800},
      {2714,1250,2714,4678,1000,2964,4428,2964},
      {681, 1200, 800},
      {1381,4891,1191,5080,1191,5080,1000,4891,1000,4891,1191,4700,1191,4700,1381,4891,1381,4891,1381,5271,1381,5271,1000,5652},
      {3700, 1200, 800},
      {1000,3000,3500,3000},
      {680, 1200, 800},
      {1190,4700,1000,4891,1000,4891,1190,5080,1190,5080,1380,4891,1380,4891,1190,4700},
      {3451, 1200, 800},
      {3251,1000,1000,5000},
      {3866, 1200, 800},
      {2141,1000,1761,1189,1761,1189,1570,1380,1570,1380,1380,1761,1380,1761,1189,2713,1189,2713,1189,3284,1189,3284,1380,4237,1380,4237,1570,4618,1570,4618,1761,4809,1761,4809,2141,4998,2523,4998,2904,4809,2904,4809,3094,4618,3094,4618,3284,4237,3284,4237,3475,3284,3475,3284,3475,2713,3475,2713,3284,1761,3284,1761,3094,1380,3094,1380,2904,1189,2904,1189,2523,1000,2141,1000,1570,1189,1570,1189,1189,1761,1189,1761,1000,2713,1000,2713,1000,3284,1000,3284,1189,4237,1189,4237,1570,4809,1570,4809,2141,4998,2141,4998,2523,4998,2523,4998,3094,4809,3094,4809,3475,4237,3475,4237,3666,3284,3666,3284,3666,2713,3666,2713,3475,1761,3475,1761,3094,1189,3094,1189,2523,1000,2523,1000,2141,1000},
      {2913, 1200, 800},
      {1000,1761,1381,1570,1381,1570,1952,1000,1952,1000,1952,4998,1761,1189,1761,4998,1000,4998,2713,4998},
      {3866, 1200, 800},
      {1189,1761,1380,1952,1380,1952,1189,2141,1189,2141,1000,1952,1000,1952,1000,1761,1000,1761,1189,1380,1189,1380,1380,1189,1380,1189,1952,1000,1952,1000,2713,1000,2713,1000,3284,1189,3284,1189,3475,1380,3475,1380,3666,1761,3666,1761,3666,2141,3666,2141,3475,2523,3475,2523,2904,2904,2904,2904,1952,3284,1952,3284,1570,3475,1570,3475,1189,3856,1189,3856,1000,4427,1000,4427,1000,4998,2713,1000,3094,1189,3094,1189,3284,1380,3284,1380,3475,1761,3475,1761,3475,2141,3475,2141,3284,2523,3284,2523,2713,2904,2713,2904,1952,3284,1000,4618,1189,4427,1189,4427,1570,4427,1570,4427,2523,4809,2523,4809,3094,4809,3094,4809,3475,4618,3475,4618,3666,4427,1570,4427,2523,4998,2523,4998,3284,4998,3284,4998,3475,4809,3475,4809,3666,4427,3666,4427,3666,4046},
      {3866, 1200, 800},
      {1189,1761,1380,1952,1380,1952,1189,2141,1189,2141,1000,1952,1000,1952,1000,1761,1000,1761,1189,1380,1189,1380,1380,1189,1380,1189,1952,1000,1952,1000,2713,1000,2713,1000,3284,1189,3284,1189,3475,1570,3475,1570,3475,2141,3475,2141,3284,2523,3284,2523,2713,2713,2713,2713,2141,2713,2713,1000,3094,1189,3094,1189,3284,1570,3284,1570,3284,2141,3284,2141,3094,2523,3094,2523,2713,2713,2713,2713,3094,2904,3094,2904,3475,3284,3475,3284,3666,3666,3666,3666,3666,4237,3666,4237,3475,4618,3475,4618,3284,4809,3284,4809,2713,4998,2713,4998,1952,4998,1952,4998,1380,4809,1380,4809,1189,4618,1189,4618,1000,4237,1000,4237,1000,4046,1000,4046,1189,3856,1189,3856,1380,4046,1380,4046,1189,4237,3284,3094,3475,3666,3475,3666,3475,4237,3475,4237,3284,4618,3284,4618,3094,4809,3094,4809,2713,4998},
      {4247, 1200, 800},
      {2904,1380,2904,4998,3095,1000,3095,4998,3095,1000,1000,3856,1000,3856,4047,3856,2333,4998,3666,4998},
      {3866, 1200, 800},
      {1381,1000,1000,2904,1000,2904,1381,2523,1381,2523,1952,2332,1952,2332,2523,2332,2523,2332,3095,2523,3095,2523,3475,2904,3475,2904,3666,3475,3666,3475,3666,3856,3666,3856,3475,4427,3475,4427,3095,4809,3095,4809,2523,4998,2523,4998,1952,4998,1952,4998,1381,4809,1381,4809,1190,4618,1190,4618,1000,4237,1000,4237,1000,4046,1000,4046,1190,3856,1190,3856,1381,4046,1381,4046,1190,4237,2523,2332,2904,2523,2904,2523,3286,2904,3286,2904,3475,3475,3475,3475,3475,3856,3475,3856,3286,4427,3286,4427,2904,4809,2904,4809,2523,4998,1381,1000,3286,1000,1381,1189,2333,1189,2333,1189,3286,1000},
      {3866, 1200, 800},
      {3286,1570,3095,1761,3095,1761,3286,1952,3286,1952,3475,1761,3475,1761,3475,1570,3475,1570,3286,1189,3286,1189,2904,1000,2904,1000,2333,1000,2333,1000,1761,1189,1761,1189,1381,1570,1381,1570,1190,1952,1190,1952,1000,2713,1000,2713,1000,3856,1000,3856,1190,4427,1190,4427,1570,4809,1570,4809,2143,4998,2143,4998,2523,4998,2523,4998,3095,4809,3095,4809,3475,4427,3475,4427,3666,3856,3666,3856,3666,3666,3666,3666,3475,3094,3475,3094,3095,2713,3095,2713,2523,2523,2523,2523,2333,2523,2333,2523,1761,2713,1761,2713,1381,3094,1381,3094,1190,3666,2333,1000,1952,1189,1952,1189,1570,1570,1570,1570,1381,1952,1381,1952,1190,2713,1190,2713,1190,3856,1190,3856,1381,4427,1381,4427,1761,4809,1761,4809,2143,4998,2523,4998,2904,4809,2904,4809,3286,4427,3286,4427,3475,3856,3475,3856,3475,3666,3475,3666,3286,3094,3286,3094,2904,2713,2904,2713,2523,2523},
      {3866, 1200, 800},
      {1000,1000,1000,2141,1000,1761,1190,1380,1190,1380,1570,1000,1570,1000,1952,1000,1952,1000,2904,1570,2904,1570,3286,1570,3286,1570,3475,1380,3475,1380,3666,1000,1190,1380,1570,1189,1570,1189,1952,1189,1952,1189,2904,1570,3666,1000,3666,1570,3666,1570,3475,2141,3475,2141,2713,3094,2713,3094,2523,3475,2523,3475,2333,4046,2333,4046,2333,4998,3475,2141,2523,3094,2523,3094,2333,3475,2333,3475,2143,4046,2143,4046,2143,4998},
      {3866, 1200, 800},
      {1952,1000,1570,1189,1570,1189,1381,1570,1381,1570,1381,2141,1381,2141,1570,2523,1570,2523,1952,2713,2713,2713,3095,2523,3095,2523,3286,2141,3286,2141,3286,1570,3286,1570,3095,1189,3095,1189,2713,1000,1952,2713,1381,2904,1381,2904,1190,3094,1190,3094,1000,3475,1000,3475,1000,4237,1000,4237,1190,4618,1190,4618,1381,4809,1381,4809,1952,4998,1952,4998,2713,4998,2713,4998,3286,4809,3286,4809,3475,4618,3475,4618,3666,4237,3666,4237,3666,3475,3666,3475,3475,3094,3475,3094,3286,2904,3286,2904,2713,2713,1952,2713,1570,2904,1570,2904,1381,3094,1381,3094,1190,3475,1190,3475,1190,4237,1190,4237,1381,4618,1381,4618,1570,4809,1570,4809,1952,4998,2713,4998,3095,4809,3095,4809,3286,4618,3286,4618,3475,4237,3475,4237,3475,3475,3475,3475,3286,3094,3286,3094,3095,2904,3095,2904,2713,2713,1952,1000,1381,1189,1381,1189,1190,1570,1190,1570,1190,2141,1190,2141,1381,2523,1381,2523,1952,2713,1952,2713,2713,2713,2713,2713,3286,2523,3286,2523,3475,2141,3475,2141,3475,1570,3475,1570,3286,1189,3286,1189,2713,1000,2713,1000,1952,1000},
      {3866, 1200, 800},
      {3475,2332,3286,2904,3286,2904,2904,3284,2904,3284,2333,3475,2333,3475,2143,3475,2143,3475,1570,3284,1570,3284,1190,2904,1190,2904,1000,2332,1000,2332,1000,2141,1000,2141,1190,1570,1190,1570,1570,1189,1570,1189,2143,1000,2143,1000,2523,1000,2523,1000,3095,1189,3095,1189,3475,1570,3475,1570,3666,2141,3666,2141,3666,3284,3666,3284,3475,4046,3475,4046,3286,4427,3286,4427,2904,4809,2904,4809,2333,4998,2333,4998,1761,4998,1761,4998,1381,4809,1381,4809,1190,4427,1190,4427,1190,4237,1190,4237,1381,4046,1381,4046,1570,4237,1570,4237,1381,4427,2143,3475,1761,3284,1761,3284,1381,2904,1381,2904,1190,2332,1190,2332,1190,2141,1190,2141,1381,1570,1381,1570,1761,1189,1761,1189,2143,1000,2523,1000,2904,1189,2904,1189,3286,1570,3286,1570,3475,2141,3475,2141,3475,3284,3475,3284,3286,4046,3286,4046,3095,4427,3095,4427,2713,4809,2713,4809,2333,4998},
      {680, 1200, 800},
      {1190,3400,1000,3591,1000,3591,1190,3781,1190,3781,1380,3591,1380,3591,1190,3400,1190,4734,1000,4925,1000,4925,1190,5114,1190,5114,1380,4925,1380,4925,1190,4734},
      {680, 1200, 800},
      {1191,3400,1000,3591,1000,3591,1191,3781,1191,3781,1380,3591,1380,3591,1191,3400,1380,4925,1191,5114,1191,5114,1000,4925,1000,4925,1191,4734,1191,4734,1380,4925,1380,4925,1380,5305,1380,5305,1000,5686},
      {3740, 1200, 800},
      {3540,1000,1000,3001,1000,3001,3540,5000},
      {3700, 1200, 800},
      {1000,2400,3500,2400,1000,3600,3500,3600},
      {3740, 1200, 800},
      {1000,1000,3540,3001,3540,3001,1000,5000},
      {3484, 1200, 800},
      {1190,1761,1380,1952,1380,1952,1190,2141,1190,2141,1000,1952,1000,1952,1000,1761,1000,1761,1190,1380,1190,1380,1380,1189,1380,1189,1761,1000,1761,1000,2332,1000,2332,1000,2904,1189,2904,1189,3095,1380,3095,1380,3284,1761,3284,1761,3284,2141,3284,2141,3095,2523,3095,2523,2904,2713,2904,2713,2143,3094,2143,3094,2143,3666,2332,1000,2713,1189,2713,1189,2904,1380,2904,1380,3095,1761,3095,1761,3095,2141,3095,2141,2904,2523,2904,2523,2523,2904,2143,4618,1952,4809,1952,4809,2143,4998,2143,4998,2332,4809,2332,4809,2143,4618},
      {6451, 1200, 800},
      {4751,3000,4500,2499,4500,2499,4001,2250,4001,2250,3250,2250,3250,2250,2751,2499,2751,2499,2500,2750,2500,2750,2250,3501,2250,3501,2250,4250,2250,4250,2500,4751,2500,4751,3001,5000,3001,5000,3751,5000,3751,5000,4251,4751,4251,4751,4500,4250,3250,2250,2751,2750,2751,2750,2500,3501,2500,3501,2500,4250,2500,4250,2751,4751,2751,4751,3001,5000,4751,2250,4500,4250,4500,4250,4500,4751,4500,4751,5001,5000,5001,5000,5502,5000,5502,5000,6001,4501,6001,4501,6251,3750,6251,3750,6251,3250,6251,3250,6001,2499,6001,2499,5751,2000,5751,2000,5251,1499,5251,1499,4751,1249,4751,1249,4001,1000,4001,1000,3250,1000,3250,1000,2500,1249,2500,1249,2000,1499,2000,1499,1501,2000,1501,2000,1250,2499,1250,2499,1000,3250,1000,3250,1000,4000,1000,4000,1250,4751,1250,4751,1501,5250,1501,5250,2000,5751,2000,5751,2500,6001,2500,6001,3250,6250,3250,6250,4001,6250,4001,6250,4751,6001,4751,6001,5251,5751,5251,5751,5502,5501,5001,2250,4751,4250,4751,4250,4751,4751,4751,4751,5001,5000},
      {4629, 1200, 800},
      {2713,1000,1381,4998,2713,1000,4047,4998,2713,1570,3856,4998,1761,3856,3476,3856,1000,4998,2143,4998,3286,4998,4429,4998},
      {4427, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,1000,1000,4047,1000,4047,1000,4047,2141,4047,2141,3856,1000,1761,2904,3284,2904,3284,2904,3856,3094,3856,3094,4047,3284,4047,3284,4237,3666,4237,3666,4237,4237,4237,4237,4047,4618,4047,4618,3856,4809,3856,4809,3284,4998,3284,4998,1000,4998,3284,2904,3666,3094,3666,3094,3856,3284,3856,3284,4047,3666,4047,3666,4047,4237,4047,4237,3856,4618,3856,4618,3666,4809,3666,4809,3284,4998},
      {4056, 1200, 800},
      {1190,1570,1000,1000,1000,1000,1000,2141,1000,2141,1190,1570,1190,1570,1570,1189,1570,1189,2143,1000,2143,1000,2523,1000,2523,1000,3095,1189,3095,1189,3475,1570,3475,1570,3666,1952,3666,1952,3856,2523,3856,2523,3856,3475,3856,3475,3666,4046,3666,4046,3475,4427,3475,4427,3095,4809,3095,4809,2523,4998,2523,4998,1952,4998,1952,4998,1381,4809,1381,4809,1190,4618,1190,4618,1000,4237,1000,4237,1000,4046,1000,4046,1190,3856,1190,3856,1381,4046,1381,4046,1190,4237,2523,1000,2904,1189,2904,1189,3286,1570,3286,1570,3475,1952,3475,1952,3666,2523,3666,2523,3666,3475,3666,3475,3475,4046,3475,4046,3286,4427,3286,4427,2904,4809,2904,4809,2523,4998,1952,2904,3666,2904},
      {5199, 1200, 800},
      {2333,1000,2333,2141,2333,2141,2143,3666,2143,3666,1952,4427,1952,4427,1761,4809,1761,4809,1570,4998,4238,1000,4238,4998,4427,1000,4427,4998,1761,1000,4999,1000,1000,4998,4999,4998,1000,4998,1000,6332,1190,4998,1000,6332,4809,4998,4999,6332,4999,4998,4999,6332},
      {5009, 1200, 800},
      {1572,1000,1572,4998,1761,1000,1761,4998,4047,1000,4047,4998,4238,1000,4238,4998,1000,1000,2333,1000,3476,1000,4809,1000,4047,1380,1761,4618,1000,4998,2333,4998,3476,4998,4809,4998},
      {4818, 1200, 800},
      {2713,1000,2713,4998,2904,1000,2904,4998,2141,1000,3475,1000,2332,1570,1761,1761,1761,1761,1380,2141,1380,2141,1189,2713,1189,2713,1189,3284,1189,3284,1380,3856,1380,3856,1761,4237,1761,4237,2332,4427,3284,4427,3856,4237,3856,4237,4237,3856,4237,3856,4427,3284,4427,3284,4427,2713,4427,2713,4237,2141,4237,2141,3856,1761,3856,1761,3284,1570,2141,4998,3475,4998,2332,1570,1570,1761,1570,1761,1189,2141,1189,2141,1000,2713,1000,2713,1000,3284,1000,3284,1189,3856,1189,3856,1570,4237,1570,4237,2332,4427,2332,4427,3284,4427,3284,4427,4046,4237,4046,4237,4427,3856,4427,3856,4618,3284,4618,3284,4618,2713,4618,2713,4427,2141,4427,2141,4046,1761,4046,1761,3284,1570,3284,1570,2332,1570},
      {4056, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,1000,1000,3856,1000,3856,1000,3856,2141,3856,2141,3666,1000,1000,4998,2332,4998},
      {6341, 1200, 800},
      {3475,1000,3475,4998,3666,1000,3666,4998,2904,1000,4237,1000,1380,1189,1570,1380,1570,1380,1380,1570,1380,1570,1190,1380,1190,1380,1190,1189,1190,1189,1380,1000,1380,1000,1570,1000,1570,1000,1761,1189,1761,1189,1952,1570,1952,1570,2143,2332,2143,2332,2332,2713,2332,2713,2713,2904,2713,2904,4427,2904,4427,2904,4809,2713,4809,2713,5000,2332,5000,2332,5189,1570,5189,1570,5380,1189,5380,1189,5570,1000,5570,1000,5761,1000,5761,1000,5952,1189,5952,1189,5952,1380,5952,1380,5761,1570,5761,1570,5570,1380,5570,1380,5761,1189,2713,2904,2332,3094,2332,3094,2143,3475,2143,3475,1952,4427,1952,4427,1761,4809,1761,4809,1570,4998,2713,2904,2523,3094,2523,3094,2332,3475,2332,3475,2143,4427,2143,4427,1952,4809,1952,4809,1761,4998,1761,4998,1380,4998,1380,4998,1190,4809,1190,4809,1000,4427,4427,2904,4809,3094,4809,3094,5000,3475,5000,3475,5189,4427,5189,4427,5380,4809,5380,4809,5570,4998,4427,2904,4618,3094,4618,3094,4809,3475,4809,3475,5000,4427,5000,4427,5189,4809,5189,4809,5380,4998,5380,4998,5761,4998,5761,4998,5952,4809,5952,4809,6141,4427,2904,4998,4237,4998},
      {5009, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,4047,1000,4047,4998,4238,1000,4238,4998,1000,1000,2333,1000,3475,1000,4809,1000,4047,1380,1761,4618,1000,4998,2333,4998,3475,4998,4809,4998},
      {4818, 1200, 800},
      {1570,1000,1570,3094,1570,3094,1761,3475,1761,3475,2333,3666,2333,3666,2904,3666,2904,3666,3475,3475,3475,3475,3856,3094,1761,1000,1761,3094,1761,3094,1952,3475,1952,3475,2333,3666,3856,1000,3856,4998,4047,1000,4047,4998,1000,1000,2333,1000,3286,1000,4618,1000,3286,4998,4618,4998},
      {5009, 1200, 800},
      {1572,1000,1572,4998,1761,1000,1761,4998,1000,1000,2333,1000,1761,2904,3095,2904,3095,2904,3476,2713,3476,2713,3666,2332,3666,2332,3856,1570,3856,1570,4047,1189,4047,1189,4238,1000,4238,1000,4429,1000,4429,1000,4618,1189,4618,1189,4618,1380,4618,1380,4429,1570,4429,1570,4238,1380,4238,1380,4429,1189,3095,2904,3476,3094,3476,3094,3666,3475,3666,3475,3856,4427,3856,4427,4047,4809,4047,4809,4238,4998,3095,2904,3286,3094,3286,3094,3476,3475,3476,3475,3666,4427,3666,4427,3856,4809,3856,4809,4047,4998,4047,4998,4429,4998,4429,4998,4618,4809,4618,4809,4809,4427,1000,4998,2333,4998},
      {5199, 1200, 800},
      {2143,1000,2143,2141,2143,2141,1952,3666,1952,3666,1761,4427,1761,4427,1570,4809,1570,4809,1381,4998,1381,4998,1190,4998,1190,4998,1000,4809,1000,4809,1000,4618,1000,4618,1190,4427,1190,4427,1381,4618,1381,4618,1190,4809,4238,1000,4238,4998,4427,1000,4427,4998,1570,1000,4999,1000,3666,4998,4999,4998},
      {5200, 1200, 800},
      {1572,1000,1572,4998,1761,1000,2904,4427,1572,1000,2904,4998,4238,1000,2904,4998,4238,1000,4238,4998,4429,1000,4429,4998,1000,1000,1761,1000,4238,1000,5000,1000,1000,4998,2143,4998,3666,4998,5000,4998},
      {5009, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,4047,1000,4047,4998,4237,1000,4237,4998,1000,1000,2332,1000,3475,1000,4809,1000,1761,2904,4047,2904,1000,4998,2332,4998,3475,4998,4809,4998},
      {4247, 1200, 800},
      {2333,1000,1952,1189,1952,1189,1572,1570,1572,1570,1381,1952,1381,1952,1190,2713,1190,2713,1190,3284,1190,3284,1381,4046,1381,4046,1572,4427,1572,4427,1952,4809,1952,4809,2333,4998,2715,4998,3095,4809,3095,4809,3476,4427,3476,4427,3667,4046,3667,4046,3856,3284,3856,3284,3856,2713,3856,2713,3667,1952,3667,1952,3476,1570,3476,1570,3095,1189,3095,1189,2715,1000,2333,1000,1762,1189,1762,1189,1381,1570,1381,1570,1190,1952,1190,1952,1000,2713,1000,2713,1000,3284,1000,3284,1190,4046,1190,4046,1381,4427,1381,4427,1762,4809,1762,4809,2333,4998,2333,4998,2715,4998,2715,4998,3286,4809,3286,4809,3667,4427,3667,4427,3856,4046,3856,4046,4047,3284,4047,3284,4047,2713,4047,2713,3856,1952,3856,1952,3667,1570,3667,1570,3286,1189,3286,1189,2715,1000,2715,1000,2333,1000},
      {5009, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,4047,1000,4047,4998,4238,1000,4238,4998,1000,1000,4809,1000,1000,4998,2333,4998,3475,4998,4809,4998},
      {6723, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,3666,1000,3666,4998,3856,1000,3856,4998,5761,1000,5761,4998,5952,1000,5952,4998,1000,1000,2332,1000,3095,1000,4427,1000,5189,1000,6523,1000,1000,4998,6523,4998},
      {4438, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,1000,1000,3286,1000,3286,1000,3856,1189,3856,1189,4047,1380,4047,1380,4238,1761,4238,1761,4238,2332,4238,2332,4047,2713,4047,2713,3856,2904,3856,2904,3286,3094,3286,3094,1761,3094,3286,1000,3666,1189,3666,1189,3856,1380,3856,1380,4047,1761,4047,1761,4047,2332,4047,2332,3856,2713,3856,2713,3666,2904,3666,2904,3286,3094,1000,4998,2333,4998},
      {4056, 1200, 800},
      {3667,1570,3856,2141,3856,2141,3856,1000,3856,1000,3667,1570,3667,1570,3286,1189,3286,1189,2715,1000,2715,1000,2333,1000,2333,1000,1762,1189,1762,1189,1381,1570,1381,1570,1190,1952,1190,1952,1000,2523,1000,2523,1000,3475,1000,3475,1190,4046,1190,4046,1381,4427,1381,4427,1762,4809,1762,4809,2333,4998,2333,4998,2715,4998,2715,4998,3286,4809,3286,4809,3667,4427,3667,4427,3856,4046,2333,1000,1952,1189,1952,1189,1572,1570,1572,1570,1381,1952,1381,1952,1190,2523,1190,2523,1190,3475,1190,3475,1381,4046,1381,4046,1572,4427,1572,4427,1952,4809,1952,4809,2333,4998},
      {4056, 1200, 800},
      {2333,1000,2333,4998,2524,1000,2524,4998,1190,1000,1000,2141,1000,2141,1000,1000,1000,1000,3856,1000,3856,1000,3856,2141,3856,2141,3666,1000,1761,4998,3095,4998},
      {6152, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,1000,1000,2333,1000,1000,4998,2333,4998,4238,1000,3856,1189,3856,1189,3475,1570,3475,1570,3286,1952,3286,1952,3095,2713,3095,2713,3095,3284,3095,3284,3286,4046,3286,4046,3475,4427,3475,4427,3856,4809,3856,4809,4238,4998,4618,4998,5000,4809,5000,4809,5380,4427,5380,4427,5570,4046,5570,4046,5761,3284,5761,3284,5761,2713,5761,2713,5570,1952,5570,1952,5380,1570,5380,1570,5000,1189,5000,1189,4618,1000,1761,2904,2904,2904,4238,1000,3666,1189,3666,1189,3286,1570,3286,1570,3095,1952,3095,1952,2904,2713,2904,2713,2904,3284,2904,3284,3095,4046,3095,4046,3286,4427,3286,4427,3666,4809,3666,4809,4238,4998,4238,4998,4618,4998,4618,4998,5190,4809,5190,4809,5570,4427,5570,4427,5761,4046,5761,4046,5952,3284,5952,3284,5952,2713,5952,2713,5761,1952,5761,1952,5570,1570,5570,1570,5190,1189,5190,1189,4618,1000,4618,1000,4238,1000},
      {4438, 1200, 800},
      {1572,1000,1572,4998,1761,1000,1761,4998,1000,1000,3286,1000,3286,1000,3856,1189,3856,1189,4047,1380,4047,1380,4238,1761,4238,1761,4238,2141,4238,2141,4047,2523,4047,2523,3856,2713,3856,2713,3286,2904,3286,1000,3666,1189,3666,1189,3856,1380,3856,1380,4047,1761,4047,1761,4047,2141,4047,2141,3856,2523,3856,2523,3666,2713,3666,2713,3286,2904,1761,2904,3286,2904,3286,2904,3856,3094,3856,3094,4047,3284,4047,3284,4238,3666,4238,3666,4238,4237,4238,4237,4047,4618,4047,4618,3856,4809,3856,4809,3286,4998,3286,4998,1000,4998,3286,2904,3666,3094,3666,3094,3856,3284,3856,3284,4047,3666,4047,3666,4047,4237,4047,4237,3856,4618,3856,4618,3666,4809,3666,4809,3286,4998},
      {6723, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,3666,1000,3666,4998,3856,1000,3856,4998,5761,1000,5761,4998,5952,1000,5952,4998,1000,1000,2333,1000,3095,1000,4427,1000,5190,1000,6523,1000,1000,4998,6523,4998,6332,4998,6523,6332,6523,4998,6523,6332},
      {4627, 1200, 800},
      {1380,1000,3856,4998,1570,1000,4046,4998,4046,1000,1380,4998,1000,1000,2141,1000,3284,1000,4427,1000,1000,4998,2141,4998,3284,4998,4427,4998},
      {4818, 1200, 800},
      {1381,1000,2713,4046,1570,1000,2904,4046,4238,1000,2904,4046,2904,4046,2523,4618,2523,4618,2333,4809,2333,4809,1952,4998,1952,4998,1761,4998,1761,4998,1570,4809,1570,4809,1570,4618,1570,4618,1761,4427,1761,4427,1952,4618,1952,4618,1761,4809,1000,1000,2143,1000,3475,1000,4618,1000},
      {3867, 1200, 800},
      {1190,1570,1000,1000,1000,1000,1000,2141,1000,2141,1190,1570,1190,1570,1572,1189,1572,1189,1952,1000,1952,1000,2715,1000,2715,1000,3286,1189,3286,1189,3476,1570,3476,1570,3476,2141,3476,2141,3286,2523,3286,2523,2715,2713,2715,2713,2143,2713,2715,1000,3095,1189,3095,1189,3286,1570,3286,1570,3286,2141,3286,2141,3095,2523,3095,2523,2715,2713,2715,2713,3095,2904,3095,2904,3476,3284,3476,3284,3667,3666,3667,3666,3667,4237,3667,4237,3476,4618,3476,4618,3286,4809,3286,4809,2715,4998,2715,4998,1762,4998,1762,4998,1381,4809,1381,4809,1190,4618,1190,4618,1000,4237,1000,4237,1000,4046,1000,4046,1190,3856,1190,3856,1381,4046,1381,4046,1190,4237,3286,3094,3476,3666,3476,3666,3476,4237,3476,4237,3286,4618,3286,4618,3095,4809,3095,4809,2715,4998},
      {4247, 1200, 800},
      {1570,1000,1570,4998,1761,1000,1761,4998,2904,2141,2904,3666,1000,1000,4047,1000,4047,1000,4047,2141,4047,2141,3856,1000,1761,2904,2904,2904,1000,4998,4047,4998,4047,4998,4047,3856,4047,3856,3856,4998},
      {3451, 1200, 800},
      {1000,1000,3251,5000},
      {5200, 1200, 800},
      {2523,1000,2523,4998,2713,1000,2713,4998,1190,1000,1000,2141,1000,2141,1000,1000,1000,1000,3284,1000,2713,2904,4047,2904,4047,2904,4618,3094,4618,3094,4809,3284,4809,3284,5000,3666,5000,3666,5000,4237,5000,4237,4809,4618,4809,4618,4618,4809,4618,4809,4047,4998,4047,4998,1952,4998,4047,2904,4427,3094,4427,3094,4618,3284,4618,3284,4809,3666,4809,3666,4809,4237,4809,4237,4618,4618,4618,4618,4427,4809,4427,4809,4047,4998},
      {4629, 1200, 800},
      {3667,1000,3667,4998,3856,1000,3856,4998,4429,1000,2143,1000,2143,1000,1572,1189,1572,1189,1381,1380,1381,1380,1190,1761,1190,1761,1190,2141,1190,2141,1381,2523,1381,2523,1572,2713,1572,2713,2143,2904,2143,2904,3667,2904,2143,1000,1762,1189,1762,1189,1572,1380,1572,1380,1381,1761,1381,1761,1381,2141,1381,2141,1572,2523,1572,2523,1762,2713,1762,2713,2143,2904,2715,2904,2333,3094,2333,3094,2143,3284,2143,3284,1572,4618,1572,4618,1381,4809,1381,4809,1190,4809,1190,4809,1000,4618,2333,3094,2143,3475,2143,3475,1762,4809,1762,4809,1572,4998,1572,4998,1190,4998,1190,4998,1000,4618,1000,4618,1000,4427,3095,4998,4429,4998},
      {4247, 1200, 800},
      {1572,1000,1572,4998,1762,1000,1762,4998,1000,1000,2333,1000,1762,2904,3095,2904,3095,2904,3667,3094,3667,3094,3856,3284,3856,3284,4047,3666,4047,3666,4047,4237,4047,4237,3856,4618,3856,4618,3667,4809,3667,4809,3095,4998,3095,4998,1000,4998,3095,2904,3476,3094,3476,3094,3667,3284,3667,3284,3856,3666,3856,3666,3856,4237,3856,4237,3667,4618,3667,4618,3476,4809,3476,4809,3095,4998},
      {4629, 1200, 800},
      {1000,3643,1000,3262,1000,3262,1191,2691,1191,2691,1572,2500,1572,2500,1952,2500,1952,2500,2334,2691,2334,2691,3095,3262,3095,3262,3477,3452,3477,3452,3857,3452,3857,3452,4238,3262,4238,3262,4429,2881,1000,3262,1191,2881,1191,2881,1572,2691,1572,2691,1952,2691,1952,2691,2334,2881,2334,2881,3095,3452,3095,3452,3477,3643,3477,3643,3857,3643,3857,3643,4238,3452,4238,3452,4429,2881,4429,2881,4429,2500},
      {4056, 1200, 800},
      {1380,2714,1380,2905,1380,2905,1190,2905,1190,2905,1190,2714,1190,2714,1380,2523,1380,2523,1761,2333,1761,2333,2523,2333,2523,2333,2904,2523,2904,2523,3095,2714,3095,2714,3284,3094,3284,3094,3284,4428,3284,4428,3475,4809,3475,4809,3666,4999,3095,2714,3095,4428,3095,4428,3284,4809,3284,4809,3666,4999,3666,4999,3856,4999,3095,3094,2904,3285,2904,3285,1761,3476,1761,3476,1190,3666,1190,3666,1000,4046,1000,4046,1000,4428,1000,4428,1190,4809,1190,4809,1761,4999,1761,4999,2332,4999,2332,4999,2713,4809,2713,4809,3095,4428,1761,3476,1380,3666,1380,3666,1190,4046,1190,4046,1190,4428,1190,4428,1380,4809,1380,4809,1761,4999},
      {3866, 1200, 800},
      {3475,1000,3286,1189,3286,1189,2143,1570,2143,1570,1570,1952,1570,1952,1190,2523,1190,2523,1000,3094,1000,3094,1000,3856,1000,3856,1190,4427,1190,4427,1570,4809,1570,4809,2143,4998,2143,4998,2523,4998,2523,4998,3095,4809,3095,4809,3475,4427,3475,4427,3666,3856,3666,3856,3666,3475,3666,3475,3475,2904,3475,2904,3095,2523,3095,2523,2523,2332,2523,2332,2143,2332,2143,2332,1570,2523,1570,2523,1190,2904,1190,2904,1000,3475,3475,1000,3286,1380,3286,1380,2904,1570,2904,1570,2143,1761,2143,1761,1570,2141,1570,2141,1190,2523,2143,2332,1761,2523,1761,2523,1381,2904,1381,2904,1190,3475,1190,3475,1190,3856,1190,3856,1381,4427,1381,4427,1761,4809,1761,4809,2143,4998,2523,4998,2904,4809,2904,4809,3286,4427,3286,4427,3475,3856,3475,3856,3475,3475,3475,3475,3286,2904,3286,2904,2904,2523,2904,2523,2523,2332},
      {4056, 1200, 800},
      {1190,1570,1000,1000,1000,1000,1000,2141,1000,2141,1190,1570,1190,1570,1572,1189,1572,1189,2143,1000,2143,1000,2524,1000,2524,1000,3095,1189,3095,1189,3476,1570,3476,1570,3667,1952,3667,1952,3856,2523,3856,2523,3856,3475,3856,3475,3667,4046,3667,4046,3476,4427,3476,4427,3095,4809,3095,4809,2524,4998,2524,4998,1952,4998,1952,4998,1381,4809,1381,4809,1190,4618,1190,4618,1000,4237,1000,4237,1000,4046,1000,4046,1190,3856,1190,3856,1381,4046,1381,4046,1190,4237,2524,1000,2904,1189,2904,1189,3286,1570,3286,1570,3476,1952,3476,1952,3667,2523,3667,2523,3667,3475,3667,3475,3476,4046,3476,4046,3286,4427,3286,4427,2904,4809,2904,4809,2524,4998,1952,2904,3667,2904},
      {4818, 1200, 800},
      {2141,2333,2141,3094,2141,3094,1952,4237,1952,4237,1761,4809,1761,4809,1570,4999,3856,2333,3856,4999,4046,2333,4046,4999,1570,2333,4618,2333,1189,4999,1000,5951,1000,5951,1000,4999,1000,4999,4618,4999,4618,4999,4618,5951,4618,5951,4427,4999},
      {4629, 1200, 800},
      {1572,2332,1572,4998,1762,2332,1762,4998,3667,2332,3667,4998,3856,2332,3856,4998,1000,2332,2333,2332,3095,2332,4429,2332,1000,4998,2333,4998,3095,4998,4429,4998,3667,2523,1762,4809,2143,1189,2143,1000,2143,1000,1952,1000,1952,1000,1952,1189,1952,1189,2143,1570,2143,1570,2524,1761,2524,1761,2904,1761,2904,1761,3286,1570,3286,1570,3476,1189},
      {4056, 1200, 800},
      {2332,1000,2332,6332,2523,1000,2523,6332,1761,1000,2523,1000,2332,2904,2141,2523,2141,2523,1952,2332,1952,2332,1570,2332,1570,2332,1189,2523,1189,2523,1000,3094,1000,3094,1000,4237,1000,4237,1189,4809,1189,4809,1570,4998,1570,4998,1952,4998,1952,4998,2141,4809,2141,4809,2332,4427,1570,2332,1380,2523,1380,2523,1189,3094,1189,3094,1189,4237,1189,4237,1380,4809,1380,4809,1570,4998,3284,2332,3475,2523,3475,2523,3666,3094,3666,3094,3666,4237,3666,4237,3475,4809,3475,4809,3284,4998,2523,2904,2713,2523,2713,2523,2904,2332,2904,2332,3284,2332,3284,2332,3666,2523,3666,2523,3856,3094,3856,3094,3856,4237,3856,4237,3666,4809,3666,4809,3284,4998,3284,4998,2904,4998,2904,4998,2713,4809,2713,4809,2523,4427,1761,6332,3094,6332},
      {3866, 1200, 800},
      {1570,2333,1570,4999,1761,2333,1761,4999,1000,2333,3666,2333,3666,2333,3666,3285,3666,3285,3475,2333,1000,4999,2332,4999},
      {5581, 1200, 800},
      {3095,2333,3095,4999,3286,2333,3286,4999,2524,2333,3856,2333,1572,2523,1381,2714,1381,2714,1190,2523,1190,2523,1381,2333,1381,2333,1572,2333,1572,2333,1762,2523,1762,2523,2143,3285,2143,3285,2333,3476,2333,3476,2715,3666,2715,3666,3667,3666,3667,3666,4047,3476,4047,3476,4238,3285,4238,3285,4619,2523,4619,2523,4809,2333,4809,2333,4999,2333,4999,2333,5190,2523,5190,2523,4999,2714,4999,2714,4809,2523,2715,3666,2333,3857,2333,3857,2143,4046,2143,4046,1762,4809,1762,4809,1572,4999,2715,3666,2333,4046,2333,4046,1952,4809,1952,4809,1762,4999,1762,4999,1381,4999,1381,4999,1190,4809,1190,4809,1000,4428,3667,3666,4047,3857,4047,3857,4238,4046,4238,4046,4619,4809,4619,4809,4809,4999,3667,3666,4047,4046,4047,4046,4429,4809,4429,4809,4619,4999,4619,4999,4999,4999,4999,4999,5190,4809,5190,4809,5381,4428,2524,4999,3856,4999},
      {4629, 1200, 800},
      {1572,2333,1572,4999,1762,2333,1762,4999,3667,2333,3667,4999,3856,2333,3856,4999,1000,2333,2333,2333,3095,2333,4429,2333,1000,4999,2333,4999,3095,4999,4429,4999,3667,2523,1762,4809},
      {4627, 1200, 800},
      {1570,2333,1570,3666,1570,3666,1761,4046,1761,4046,2333,4237,2333,4237,2713,4237,2713,4237,3286,4046,3286,4046,3666,3666,1761,2333,1761,3666,1761,3666,1952,4046,1952,4046,2333,4237,3666,2333,3666,4999,3856,2333,3856,4999,1000,2333,2333,2333,3095,2333,4427,2333,3095,4999,4427,4999},
      {4247, 1200, 800},
      {1572,2333,1572,4999,1761,2333,1761,4999,1000,2333,2333,2333,1761,3666,2143,3666,2143,3666,2713,3476,2713,3476,2904,3285,2904,3285,3286,2523,3286,2523,3476,2333,3476,2333,3666,2333,3666,2333,3856,2523,3856,2523,3666,2714,3666,2714,3476,2523,2143,3666,2713,3857,2713,3857,2904,4046,2904,4046,3286,4809,3286,4809,3476,4999,2143,3666,2524,3857,2524,3857,2713,4046,2713,4046,3095,4809,3095,4809,3286,4999,3286,4999,3666,4999,3666,4999,3856,4809,3856,4809,4047,4428,1000,4999,2333,4999},
      {4627, 1200, 800},
      {1952,2333,1952,3094,1952,3094,1761,4237,1761,4237,1570,4809,1570,4809,1380,4999,1380,4999,1189,4999,1189,4999,1000,4809,1000,4809,1189,4619,1189,4619,1380,4809,3666,2333,3666,4999,3856,2333,3856,4999,1380,2333,4427,2333,3094,4999,4427,4999},
      {4818, 1200, 800},
      {1572,2333,1572,4999,1572,2333,2713,4999,1761,2333,2713,4619,3856,2333,2713,4999,3856,2333,3856,4999,4047,2333,4047,4999,1000,2333,1761,2333,3856,2333,4618,2333,1000,4999,2143,4999,3286,4999,4618,4999},
      {4627, 1200, 800},
      {1570,2333,1570,4999,1761,2333,1761,4999,3666,2333,3666,4999,3856,2333,3856,4999,1000,2333,2332,2333,3095,2333,4427,2333,1761,3666,3666,3666,1000,4999,2332,4999,3095,4999,4427,4999},
      {3867, 1200, 800},
      {2143,2333,1762,2523,1762,2523,1381,2905,1381,2905,1190,3476,1190,3476,1190,3857,1190,3857,1381,4428,1381,4428,1762,4809,1762,4809,2143,4999,2524,4999,2904,4809,2904,4809,3286,4428,3286,4428,3476,3857,3476,3857,3476,3476,3476,3476,3286,2905,3286,2905,2904,2523,2904,2523,2524,2333,2143,2333,1572,2523,1572,2523,1190,2905,1190,2905,1000,3476,1000,3476,1000,3857,1000,3857,1190,4428,1190,4428,1572,4809,1572,4809,2143,4999,2143,4999,2524,4999,2524,4999,3095,4809,3095,4809,3476,4428,3476,4428,3667,3857,3667,3857,3667,3476,3667,3476,3476,2905,3476,2905,3095,2523,3095,2523,2524,2333,2524,2333,2143,2333},
      {4629, 1200, 800},
      {1572,2333,1572,4999,1762,2333,1762,4999,3667,2333,3667,4999,3856,2333,3856,4999,1000,2333,4429,2333,1000,4999,2333,4999,3095,4999,4429,4999},
      {6343, 1200, 800},
      {1572,2333,1572,4999,1761,2333,1761,4999,3476,2333,3476,4999,3666,2333,3666,4999,5381,2333,5381,4999,5570,2333,5570,4999,1000,2333,2333,2333,2904,2333,4238,2333,4809,2333,6143,2333,1000,4999,6143,4999},
      {4247, 1200, 800},
      {1572,2333,1572,6333,1762,2333,1762,6333,1762,2905,2143,2523,2143,2523,2524,2333,2524,2333,2904,2333,2904,2333,3476,2523,3476,2523,3856,2905,3856,2905,4047,3476,4047,3476,4047,3857,4047,3857,3856,4428,3856,4428,3476,4809,3476,4809,2904,4999,2904,4999,2524,4999,2524,4999,2143,4809,2143,4809,1762,4428,2904,2333,3286,2523,3286,2523,3667,2905,3667,2905,3856,3476,3856,3476,3856,3857,3856,3857,3667,4428,3667,4428,3286,4809,3286,4809,2904,4999,1000,2333,1762,2333,1000,6333,2333,6333},
      {3675, 1200, 800},
      {3284,2905,3094,3094,3094,3094,3284,3285,3284,3285,3475,3094,3475,3094,3475,2905,3475,2905,3094,2523,3094,2523,2713,2333,2713,2333,2141,2333,2141,2333,1570,2523,1570,2523,1189,2905,1189,2905,1000,3476,1000,3476,1000,3857,1000,3857,1189,4428,1189,4428,1570,4809,1570,4809,2141,4999,2141,4999,2523,4999,2523,4999,3094,4809,3094,4809,3475,4428,2141,2333,1761,2523,1761,2523,1380,2905,1380,2905,1189,3476,1189,3476,1189,3857,1189,3857,1380,4428,1380,4428,1761,4809,1761,4809,2141,4999},
      {3676, 1200, 800},
      {2143,2333,2143,4999,2333,2333,2333,4999,1190,2333,1000,3285,1000,3285,1000,2333,1000,2333,3476,2333,3476,2333,3476,3285,3476,3285,3286,2333,1572,4999,2904,4999},
      {5770, 1200, 800},
      {1570,2333,1570,4999,1761,2333,1761,4999,1000,2333,2333,2333,1000,4999,2333,4999,4047,2333,3666,2523,3666,2523,3286,2905,3286,2905,3095,3476,3095,3476,3095,3857,3095,3857,3286,4428,3286,4428,3666,4809,3666,4809,4047,4999,4427,4999,4809,4809,4809,4809,5190,4428,5190,4428,5380,3857,5380,3857,5380,3476,5380,3476,5190,2905,5190,2905,4809,2523,4809,2523,4427,2333,1761,3666,2904,3666,4047,2333,3475,2523,3475,2523,3095,2905,3095,2905,2904,3476,2904,3476,2904,3857,2904,3857,3095,4428,3095,4428,3475,4809,3475,4809,4047,4999,4047,4999,4427,4999,4427,4999,5000,4809,5000,4809,5380,4428,5380,4428,5570,3857,5570,3857,5570,3476,5570,3476,5380,2905,5380,2905,5000,2523,5000,2523,4427,2333,4427,2333,4047,2333},
      {4056, 1200, 800},
      {1570,2333,1570,4999,1761,2333,1761,4999,1000,2333,3095,2333,3095,2333,3666,2523,3666,2523,3856,2905,3856,2905,3856,3094,3856,3094,3666,3476,3666,3476,3095,3666,3095,2333,3475,2523,3475,2523,3666,2905,3666,2905,3666,3094,3666,3094,3475,3476,3475,3476,3095,3666,1761,3666,3095,3666,3095,3666,3666,3857,3666,3857,3856,4237,3856,4237,3856,4428,3856,4428,3666,4809,3666,4809,3095,4999,3095,4999,1000,4999,3095,3666,3475,3857,3475,3857,3666,4237,3666,4237,3666,4428,3666,4428,3475,4809,3475,4809,3095,4999},
      {6341, 1200, 800},
      {1570,2333,1570,4999,1761,2333,1761,4999,3475,2333,3475,4999,3666,2333,3666,4999,5380,2333,5380,4999,5570,2333,5570,4999,1000,2333,2332,2333,2904,2333,4237,2333,4809,2333,6141,2333,1000,4999,6141,4999,6141,4999,6141,5951,6141,5951,5951,4999},
      {4247, 1200, 800},
      {1381,2333,3475,4999,1570,2333,3666,4999,3666,2333,1381,4999,1000,2333,2143,2333,2904,2333,4047,2333,1000,4999,2143,4999,2904,4999,4047,4999},
      {4247, 1200, 800},
      {1381,2333,2523,4999,1570,2333,2523,4619,3666,2333,2523,4999,2523,4999,2143,5762,2143,5762,1761,6142,1761,6142,1381,6333,1381,6333,1190,6333,1190,6333,1000,6142,1000,6142,1190,5951,1190,5951,1381,6142,1000,2333,2143,2333,2904,2333,4047,2333},
      {3484, 1200, 800},
      {1189,2714,1000,2333,1000,2333,1000,3094,1000,3094,1189,2714,1189,2714,1380,2523,1380,2523,1761,2333,1761,2333,2523,2333,2523,2333,3094,2523,3094,2523,3284,2905,3284,2905,3284,3094,3284,3094,3094,3476,3094,3476,2523,3666,2523,2333,2904,2523,2904,2523,3094,2905,3094,2905,3094,3094,3094,3094,2904,3476,2904,3476,2523,3666,1952,3666,2523,3666,2523,3666,3094,3857,3094,3857,3284,4237,3284,4237,3284,4428,3284,4428,3094,4809,3094,4809,2523,4999,2523,4999,1761,4999,1761,4999,1189,4809,1189,4809,1000,4428,1000,4428,1000,4237,1000,4237,1189,4046,1189,4046,1380,4237,1380,4237,1189,4428,2523,3666,2904,3857,2904,3857,3094,4237,3094,4237,3094,4428,3094,4428,2904,4809,2904,4809,2523,4999},
      {3676, 1200, 800},
      {1190,3476,3476,3476,3476,3476,3476,3094,3476,3094,3286,2714,3286,2714,3095,2523,3095,2523,2713,2333,2713,2333,2143,2333,2143,2333,1572,2523,1572,2523,1190,2905,1190,2905,1000,3476,1000,3476,1000,3857,1000,3857,1190,4428,1190,4428,1572,4809,1572,4809,2143,4999,2143,4999,2524,4999,2524,4999,3095,4809,3095,4809,3476,4428,3286,3476,3286,2905,3286,2905,3095,2523,2143,2333,1761,2523,1761,2523,1381,2905,1381,2905,1190,3476,1190,3476,1190,3857,1190,3857,1381,4428,1381,4428,1761,4809,1761,4809,2143,4999},
      {4056, 1200, 800},
      {2143,2333,2143,4999,2332,2333,2332,4999,1190,2333,1000,3285,1000,3285,1000,2333,1000,2333,2904,2333,2332,3666,3095,3666,3095,3666,3666,3857,3666,3857,3856,4237,3856,4237,3856,4428,3856,4428,3666,4809,3666,4809,3095,4999,3095,4999,1570,4999,3095,3666,3475,3857,3475,3857,3666,4237,3666,4237,3666,4428,3666,4428,3475,4809,3475,4809,3095,4999},
      {4247, 1200, 800},
      {3284,2333,3284,4999,3475,2333,3475,4999,4047,2333,1952,2333,1952,2333,1380,2523,1380,2523,1190,2905,1190,2905,1190,3094,1190,3094,1380,3476,1380,3476,1952,3666,1952,3666,3284,3666,1952,2333,1570,2523,1570,2523,1380,2905,1380,2905,1380,3094,1380,3094,1570,3476,1570,3476,1952,3666,2904,3666,2332,3857,2332,3857,2143,4046,2143,4046,1761,4809,1761,4809,1570,4999,2904,3666,2523,3857,2523,3857,2332,4046,2332,4046,1952,4809,1952,4809,1761,4999,1761,4999,1380,4999,1380,4999,1190,4809,1190,4809,1000,4428,2713,4999,4047,4999},
      {3486, 1200, 800},
      {1570,2333,1570,4999,1761,2333,1761,4999,1000,2333,2333,2333,1761,3666,2523,3666,2523,3666,3095,3857,3095,3857,3286,4237,3286,4237,3286,4428,3286,4428,3095,4809,3095,4809,2523,4999,2523,4999,1000,4999,2523,3666,2904,3857,2904,3857,3095,4237,3095,4237,3095,4428,3095,4428,2904,4809,2904,4809,2523,4999}};

  }


}
