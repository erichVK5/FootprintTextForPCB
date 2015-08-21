// FootprintElementArchetype.java v1.0
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
//    FootprintElementArchetype Copyright (C) 2015 Erich S. Heinzle a1039181@gmail.com


public class FootprintElementArchetype
{

	long xOffsetNm = 0;
	long yOffsetNm = 0;

	public long Xposition()
	{
		return xOffsetNm;
	}

	public long Yposition()
	{
		return yOffsetNm;
	}

	public void FootprintElementArchetype()
	{
		xOffsetNm = 0;
		yOffsetNm = 0;
	}

        public void FootprintElementArchetype(long x, long y)
        {
                xOffsetNm = x;
                yOffsetNm = y;
        }

	public String toString()
	{
		return("x: " + xOffsetNm + ", y: " + yOffsetNm);
	}

	public String generateGEDAelement(long xOffset, long yOffset, float magnificationRatio)
	{
		return "";
	}

	public void populateElement(String moduleDefinition, boolean metric)
	{
		System.out.println("You're not supposed to see this.");		
	}

        public void populateElement(String moduleDefinition, boolean metric, long minimumViaDrillSize)
        {
                System.out.println("You're not supposed to see this.");
        }
	
}
