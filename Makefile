all: new-chain new-chain run-true

clean: reset-io rm-class

get-samples:
	python sample-generator.py

recompile:
	javac block/code/App.java
	javac validation/code/App.java

reset-io:
	find . -type f -name "*.txt" -delete
	find . -type f -name "*.out" -delete

rm-class:
	find . -type f -name "*.class" -delete

new-chain: recompile reset-io get-samples
	java block/code/App `find ./block/files -name "*.txt"` --verbose

run-true: 
	java validation/code/App `find . -name "*.block.out"` `python getRandomAddress.py`

run-false: 
	java validation/code/App `find . -name "*.block.out"` thiswillnotwork

run-validate: new-chain
	java validation/code/App `find . -name "*.block.out"`
