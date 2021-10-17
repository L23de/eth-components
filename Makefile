all: run-true

clean: reset-io rm-class

get-samples:
	python sample-generator.py

recompile:
	javac block/code/*.java
	javac validation/code/*.java

reset-io:
	find . -type f -name "*.txt" -delete
	find . -type f -name "*.out" -delete

rm-class:
	find . -type f -name "*.class" -delete

new-chain: recompile reset-io get-samples
	java block/code/App `find ./block/files -name "*.txt"` --verbose

run-true: new-chain
	java -cp . validation/code/App `find . -name "*.block.out"` `python getRandomAddress.py`

run-false: new-chain
	java -cp . validation/code/App `find . -name "*.block.out"` thiswillnotwork

run-validate: new-chain
	java -cp . validation/code/App `find . -name "*.block.out"`
