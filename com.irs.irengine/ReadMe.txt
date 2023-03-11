					CSCE 5200 Information Retrieval and Web Search

StudentID:11534526
Name: Somasekar Bathina Suresh

Project Name: IR Engine
Module 1: Text Parser
Module 2: Indexer
Module 3: Retrieval System


Language Used: Java
Data: TREC Data

Instructions to run the program.

1. Import the project folder as a Java Project in the IDE of your choice which supports Java Runtime Environment.
2. In the src folder expand the com.irs.irengine.main package.
3. Open the IREngineMain Class in the package.
4. Right click on the class file and run it as a Java Application.
5. Output is generated in the 'parser_output.txt' file in the same project folder.

if using command line,

1. Navigate to the project folder location in the command line
2. Compile the Project using the command javac IREngineMain.java
3. Run the Project by using the command java IREngineMain

Module 1: Text Parser -- Mar 12th 2023

In this module, document preprocessing is done.

Document Preprocessing Steps:-
•	Tokenization to handle numbers, punctuation marks, and the case of letters (upper/lower)
•	Elimination of stopwords
•	Stemming of the remaining words 
•	Selection of terms for the term dictionary
•	Creating the dictionary file

	