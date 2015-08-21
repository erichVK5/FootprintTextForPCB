# FootprintTextForPCB
A utility for generating silkscreen text as line elements for use in gEDA PCB footprints.

Copyright (C) 2015 Erich S. Heinzle, a1039181@gmail.com

    see LICENSE-gpl-v2.txt for software license

This is a command line utility for turning text strings into silkscreen line elements which can then be added to footprints. PCB does not support text elements in footprints, but does support silk screen lines. Without a utility of this nature, those making footprints and seeking to add text have to hand draw the desired text.

v1.1 of the utility uses the free Hershey Sans 1 Stroke Font and outputs 0.01mil (imperial, square bracketed) units. 

Hints for usage:

Step 1)

decide on the text (or texts) needed in the footprint being designed

Step 2)

generate each needed bit of text as a footprint with this utility, i.e.

	java FootprintTextForPCB -t "You Shouldn't Have Unsoldered This" -m 1.3

which will generate a footprint file

	You_Shouldn_t_Have_Unsoldered_This.fp

that will contain the text. It will recognise spaces and any of the usual ASCII characters that PCB can ordinarily display as text, but, you will need to escape characters that the shell might take exception to, and the escape character may end up getting rendered in the footprint text, until such time as I support excape characters a bit better.

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

    java FootprintTextForPCB -t "my Text For Conversion To Silkscreen Stroke Elements" -m X.XXXX

    "my Text For Conversion To Silkscreen Stroke Elements" is ASCII text, which can include spaces,
    and X.XXXX is an optional magnification ratio; default = 1.0)

    If run without any command line arguments, a demonstration footprint file
    called demonstration.fp, will be generated

Installation:

1) install a java compiler and java virtual machine (JVM) using your preferred
package management system/source, if it isn't already installed.

2) clone the FootprintTextForPCB git repository (this should be simple, after all,
you already build the most current stable gEDA PCB release from the git
repository.... don't you?). Failing that, download the java source file and
put it in a suitable directory with the same subdirectories and contents.

3) in the FootprintTextForPCB directory, type:

user@box:~$  javac FootprintTextForPCB.java

and that should be it, you are now ready to use the FootprinTextForPCB utility.

