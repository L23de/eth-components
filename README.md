# Merkle-Root

Calculates the merkle root of the merkle tree constructed from a text file containing a
list of alphanumerically sorted addresses (of length 40), a single space, and a balance.

## Usage

A Makefile has been provided to make running the program easier.

Default commands:

- `make` or `make all` - Compile the Java files, create a sample `ledger.txt` and runs the program
- `make clean` - Deletes `ledger.txt` and java compiled `*.class` files

Individual steps:

- `make get-sample` - Generate a sample `ledger.txt`
- `make recompile` - Compile the java files
- `make rm-class` - Removes all `*.class` files
- `make run` - Same as `make all`