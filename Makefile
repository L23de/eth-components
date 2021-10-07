all: run

clean: reset-io rm-class

get-samples:
	python3 sample-generator.py

recompile:
	javac block/code/*.java

reset-io:
	find . -type f -name "*.txt" -delete
	find . -type f -name "*.out" -delete

rm-class:
	find . -type f -name "*.class" -delete

run: recompile reset-io get-samples
	sh run.sh

run-verbose: recompile reset-io get-samples
	sh run-verbose.sh