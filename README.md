# gatling-java-maven
This repo shows the basic simulation using the new Gatling Java.

# System setup steps
 
### System requirements
1. Homebrew (Package Manager for Mac)
2. Java.
3. Maven
4. Any IDE - to make life simpler. We are using Intellij(optional).

## Steps for setting up Mac
##### 1. Install Homebrew
Run this command on the terminal:
```sh
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

##### 2. Install Java and 
Now that you have homebrew,you can get JDK using Homebrew cask.


```sh
$ brew cask install java
```



##### 3. Install maven
Can be installed using homebrew

```sh
$ brew install maven
```



Run `mvn gatling:test` from the project folder on the terminal to run the simulation
