# javaflashplayer
A rudimentary Flash Player for Windows using Java

- Requires JDK 9.0.4 installed in the user's Program Files folder
- If you have a different JDK version, change the JDK version in the run.bat file
- Make sure the files are placed in the Documents folder with the javaflashplayer folder being in the root of the Documents folder with the Runtime folder nested in the javaflashplayer folder

Features:
- Save state features
- No need to run java scripts (will be run automatically by bat scripts)
- Has ability to automatically search for and download SWF files that the user chooses

Notes:
- If you wish, you can add extra URL's to the archive.xml file in the same format as the current XML file only contains the SWF files from ArmorGames's server listing.
- There is a current bug where the SWF searcher isn't loading the XML file properly. (Fix still in progress)
