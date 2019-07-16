# How to compile the project
You need maven to compile the project.

From the root directory, simply run ```mvn clean package``` and it will compile the project and create a jar to be executed.

# How to run the program
The jar file is located inside target directory. You can run the program using ```java -jar searcher.jar [path_to_your_directory]```

# Considerations and assumptions
The only external dependency I used is junit. I also used an intellij plugin for running pitest and check mutation coverage.

The core code has been created following the Classist/Detroit TDD approach, testing SUT behaviour and mocking external dependencies manually without any library (creating my own stubs and spies).

The core search application is really easy. Iterate over each file and check how many words has each file.
Each file has been modeled as a Document, having a Set<String> of words, so searching words in a document is O(1).
After each search, a result is created using a TreeSet, for ordering purposes (documents with more words should be shown first)

The whole practice took me almost 4 hours, as I had some problems with Path/Files, mainly because of Windows way of handling Paths with Java, and also because i'm not used to work with Files directly, so some investigation was needed while doing some integration tests.

# What can be improved
...