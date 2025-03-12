# Task 3 - XML Parsing

In this task, we had to develop several sub-tasks regarding parsing and processing of XML
files. 

## Part 1

For the first part, we were given a program that parsed a XML file that contained data about 
holidays. The task was adding new tag to the elements inside the document and add the necessary
code to parse again the new data. This was done using the SAX Parser.

### How to run this part?

To run this program, first we have to compile it with this command:

```bash
javac -d out src/part1/VacationOutput.java
```

and this command to execute it:

```bash
java -cp out src.part1.VacationOutput assets/holidays.xml
```

## Part 2

For the second part, we have to go to the page [openstreetmap.org](https://openstreetmap.org) and
export a part of the map which contained a park. In this case, we have used one park
located in Bratislava, called Botanická záhrada. This piece of map is exported into a file
with .osm extension, however it is essentially a XML file.

Then, we had to write a parser for this type of file, storing only the nodes that represent 
footways and also storing the ways formed by the nodes.

Finally, we had to render this map into a UI application using a canvas. We could do this
using AWT or Java FX. I decided to use Java FX as it is a newer technology than AWT and I 
want to practice it a bit more for possible future projects.

### How to run this part?

To run this part, you need to install the Java Fx library and put it wherever you want in your computer.
Once you have it installed, you can run the following command to compile the application:

```bash
javac -d out --module-path "<absolute-path-to-javafx-lib>" --add-modules javafx.controls,javafx.fxml src/part2/OSMApp.java
```

and this command to execute it:

```bash
java -cp out --module-path "<absolute-path-to-javafx-lib>" --add-modules javafx.controls,javafx.fxml src.part2.OSMApp assets/map.osm
```

## Part 3

For the third part, we had to do the same as in the first part, but the difference is that in
this one we had to use the DOM Parser instead of the SAX Parser. The main difference between these
two is that DOM parser loads the whole document in memory, while SAX parser does it on the way,
without loading the entire file in memory.

### How to run this part?

To run this part, first we have to compile it with the following command:

```bash
javac -d out src/part3/DOMVacationParser.java
```

and this command to execute it:

```bash
java -cp out src.part3.DOMVacationParser assets/holidays.xml
```

## Part 4

For the last part, we had to implement the same funcitonality as part 1 and
using the SAX Parser, but in this case, instead of printing to the std
output the parsed info, we had to create an XML with a different name using 
the parsed info.

### How to run this part?

To run this part, first we have to compile it with the following command:

```bash
javac -d out src/part4/VacationOutput.java
```

and this command to execute it:

```bash
java -cp out src.part4.VacationOutput assets/holidays.xml
```

The output will be in the folder assets/outputs. 