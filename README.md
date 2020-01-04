This program uses config.properties to
read a directory, get all images, then
show them in a slideshow.

TODO (All OS):
* Make screensaver not activate while
in fullscreen mode
* Make an external config file

TODO (Linux):
* Make fullscreen work

TODO (Mac):
* Test if slideshow works on mac

Test run:

`mvn compile; mvn exec:java -Dexec.mainClass="tech.mattharris.java.App" -e`