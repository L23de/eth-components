all: run

clean: rm-class
	rm ledger.txt

get-sample:
	python sample-generator.py

recompile:
	javac merkle_root/code/*.java

rm-class:
	find . -type f -name '*.class' -delete

run: get-samples recompile
	java merkle_root/code/App ledger.txt




