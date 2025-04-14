# Github Activity Reader (Simpler)
This is a simpler version of the gitHub Activity reader found [here](). It has no command line features, uses only anonymous access and gets the last 30 activity events of the selected user.


## Dependencies
**Java**: JRE 9+ and/or JDK 9+ if developing (streams)


**Maven**: Maven 3.6+

## Installation
Clone the repository to the desired folder.
In the project directory, run the following:

```Shell
mvn clean package
```
In /target there should now be 2 files:
<ul>
	<li>githubActivity.jar</li>
	<li>githubActivity-jar-with-dependencies.jar</li>
</ul>

run the following to execute the program, providing the username

```Shell
java -jar githubActivity-jar-with-dependencies.jar -n <username>
```

## Features
The program has the following features:

It will query the gitHub API and return the given user's last 30 activities. The connection will be attempted via the anonymous connection method
## Usage
java -jar githubActivity-jar-with-dependencies.jar -n USERNAME


```Shell
java -jar githubActivity-jar-with-dependencies.jar -n Raz-Rotundu -l 10 -a abcdef
```