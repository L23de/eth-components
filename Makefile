all: run

clean: reset-io rm-class

get-samples:
	python3 sample-generator.py

recompile:
	javac block/code/*.java
	javac validation/code/*.java

reset-io:
	find . -type f -name "*.txt" -delete
	find . -type f -name "*.out" -delete

rm-class:
	find . -type f -name "*.class" -delete

new-chain: recompile reset-io get-samples
	sh gen-chain.sh

run: new-chain
	java validation/code/App
