# Hack The Future To Mars!
Hi there! I see you chose java as your language! You chose well ;) To kickstart your little 
project, we've provided a little base with the needed GrovePi code, 
examples and dependencies.

Within this repository you will find:
 - GrovePi Java library (professionally copy-pasted)
 - Spring Boot web starter
 - A LEDApi Example (connect a led on D3 and see the magic happen)
 
#Getting Started
To jump into the fun, first fork this repository to one of your own. That's the place you will be
 working in. You may just clone this one to navigate through the code and examples if you like 
 too :)
 
 The project is build with Gradle 3.1. If you do not have it installed, you can just install it 
 or use the included scripts :) (gradlew for unix, gradlew.bat for windows)
 
 You can build your solution with the command:
 
`
    gradle build
`

This will compile your solution into a .jar file located in ./build/libs. Copy the jar over 
to your pi and execute it with java -jar 'jarFile'. When all goes well an embedded tomcat/Spring Boot server will boot up on 
port 8080 and show you a very professional "Hello World!". When you throw a PUT request to /led 
you can toggle a LED on or off when you connect one on the D3 pin of the GrovePi board.

If you do not want to copy a jar file to your pi all the time, you can also clone your repo with 
the git client on the pi itself and run the solution with:

`
    gradle bootrun
`

Happy Coding!