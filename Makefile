all: new-chain addr-validate-true chain-validate

clean: reset-io rm-class

get-samples:
	python sample-generator.py
	python bad-block-generator.py

recompile:
	javac block/code/App.java
	javac validation/code/App.java

reset-io:
	find . -type f -name "*.txt" -delete
	find . -type f -name "*.out" -delete
	find . -type f -name "badBlock" -delete

rm-class:
	find . -type f -name "*.class" -delete

new-chain: recompile reset-io get-samples
	java block/code/App `find ./block/files -name "*.txt"` --verbose

addr-validate-true: 
	java validation/code/App `find . -name "*.block.out"` `python getRandomAddress.py`

addr-validate-false: 
	java validation/code/App `find . -name "*.block.out"` thiswillnotwork

chain-validate:
	java validation/code/App `find . -name "*.block.out"`
	java validation/code/App badChain

bad-chain-validate:
	java validation/code/App badChain
