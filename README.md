# FootprintTextForPCB
A utility for generating silkscreen text as line elements for use in gEDA PCB footprints.

Copyright (C) 2015 Erich S. Heinzle, a1039181@gmail.com

    see LICENSE-gpl-v2.txt for software license

This is a command line utility for turning text strings into silkscreen line elements which can then be added to footprints. PCB does not support text elements in footprints, but does support silk screen lines. Without a utility of this nature, those making footprints and seeking to add text have to hand draw the desired text.

v1.1 of the utility uses the free Hershey Sans 1 Stroke Font and outputs 0.01mil (imperial, square bracketed) units. 

v1.2 of the utility will implement Hershey Cyrillic, Greek and Gothic fonts. Cyrillic, Greek and German Gothic are now working, if the -c, -g or -gg flag is used. For cyrillic, text is mapped from an English keyboard layout using the AATSEEL (phonetic) mapping for Cyrillic. Greek mapping is phonetic as well.

v1.3 of the utility can export the text as a gschem compatible symbol (.sym) if a -cs (create symbol) flag is used on the command line, for those wishing to embed "mu" or "Omega" on schematics, for capacitance or resistance, for example, or perhaps some cyrillic or simple maths, as graphical elements.

Hints for usage:

Step 1)

decide on the text (or texts) needed in the footprint being designed

Step 2)

generate each needed bit of text as a footprint with this utility, i.e.

	java FootprintTextForPCB -t "You Shouldn't Have Unsoldered This" -m 1.3 -a 450

which will generate a footprint file

	You_Shouldn_t_Have_Unsoldered_This.fp

that will contain the text, 1.3 times larger than the default gEDA PCB text size, and angled at 45 degrees, based on the supplied, optional, 450 decidegree command line -a argument. It will recognise spaces and any of the usual ASCII characters that PCB can ordinarily display as text, but, you will need to escape characters that the shell might take exception to, and the escape character may end up getting rendered in the footprint text, until such time as I support escape characters a bit better.

Step 3)

When generating the new footprint in PCB, use "File:Load Element To Buffer" to load the newly generated footprint onto the layout.

Place the text in a suitable position. If it is the wrong size, go back and play with the magnification ratio option.

Step 4)

Select the text by clicking on it.

CTRL-x to cut the text to buffer

Go to "Buffer:Break Element To Pieces" to convert the text footprint into silk line primitives, and click to place the broken up element where it is needed.

Hit "Esc" to deselect.

Step 5)

Proceed now, as you normally would, to convert your collection of elements (which now include the silk lines showing the text) making up your footprint in its entirety into a footprint.

Usage:

    java FootprintTextForPCB -t "Text For Conversion to .fp" -m X.XXXX -a YYYY -c

    "my Text For Conversion To Silkscreen Stroke Elements" is ASCII text, which can include spaces,
    and X.XXXX is an optional magnification ratio; default = 1.0)
    and YYYY is an optional integer argument giving the rotation of the text
    counterclockwise from the x-axis in decidegrees, i.e. use 1800 for 180 degrees

    The -c flag will render the letters phonetically in Cyrillic.

    Alternatively, the -g flag will render the letters phonetically in Greek.

    Alternatively, the -gg flag will render the letters phonetically in German Gothic.

    If run without any command line arguments, a demonstration footprint file
    called demonstration1234567890.fp, will be generated

Hints for non english mapping generally.

the " ` " character (usually lives under the tilde " ~ " next to "1") is different to the " ' " character, and will need to be escaped with a "\" to allow alpha to be printed in greek, for example.

Hints for Cyrillic:

if you cut and paste and execute the following text, the resulting footprint will show how the english keyboard layout is mapped to Cyrillic:

    java FootprintTextForPCB -t "$%&ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_'abcdefghijklmnopqrstuvwxyz{|}~\`" -c

Hints for Greek:

if you cut and paste and execute the following text, the resulting footprint will show how the english keyboard layout is mapped to Greek:

    java FootprintTextForPCB -t "$%&ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_\`abcdefghijklmnopqrstuvwxyz{|}'" -g

Hints for German Gothic:

if you cut and paste and execute the following text, the resulting footprint will be rendered with German Gothic:

    java FootprintTextForPCB -t "$%&ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_\`abcdefghijklmnopqrstuvwxyz{|}'" -gg

Hints for gschem symbol export:

if you want to have exotic text rendered in gschem as a symbol, this can be done with a -cs (create symbol) flag, i.e. to produce a "mu" symbol compatible with 12 point text in gschem, we specify the lower case L (Greek has less letters in the alphabet than English). Note that the resulting symbol will need mirroring and rotation in gschem before it is ready for final use: 

    java FootprintTextForPCB -t "l" -g

TODO:

complete the unicode parsing and mapping code.

Installation:

1) install a java compiler and java virtual machine (JVM) using your preferred
package management system/source, if it isn't already installed.

2) clone the FootprintTextForPCB git repository (this should be simple, after all,
you already build the most current stable gEDA PCB release from the git
repository.... don't you?). Failing that, download the java source file and
put it in a suitable directory with the same subdirectories and contents.

3) in the FootprintTextForPCB directory, type:

user@box:~$  javac FootprintTextForPCB.java

and that should be it, you are now ready to use the FootprintTextForPCB utility.

Compiling a native binary:

It seems gcj will compile a native binary executable from java source if asked nicely.

	sudo apt-get install gcj-jdk
	gcj -I src -C *.java
	gcj -I src --main=FootprintTextForPCB *.class
	./a.out -t "test"

Compiling to byte code first, as above, appears to avoid a bug affecting compilation directly to native code in one step.
