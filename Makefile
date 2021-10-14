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
	sh gen_chain.sh

run-true: new-chain
	export CHAIN=`find . -name "*.block.out"`
	export ADDR=`python getRandomAddress.py`
	java validation/code/App ${CHAIN} `python getRandomAddress.py`

run-false: new-chain
	export CHAIN=`find . -name "*.block.out"`
	java validation/code/App ${CHAIN} thiswillnotwork
