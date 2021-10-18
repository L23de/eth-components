# Validation

## About

This section replicates what light nodes, nodes that don't have the full blockchain but need to access it, will do for validation of responses they get from full nodes

App.java under `validation` can take in 1 or 2 arguments which dictates what App.java does. This section utilizes the `block` section, generating ledgers and creating a blockchain from that, which is used as input to `validation/App`

### Results

#### One Argument

- Argument: Blockchain file (`block.out`)
- Action: Checks that the provided blockchain file is a valid blockchain

  - Next block's previous hash field should have the same hash as the current block's hash
  - Merkle Root of each block in the blockchain should be correct for the contents of the block

#### Two Arguments

- Arguments: Blockchain file (`block.out`) and an address (Valid addresses should be of length 40)
- Action: Check that the provided address is in the blockchain

  - Print out the account list of the corresponding block
  - Print out the merkle tree representation of that block
  - If it exists in the blockchain, a proof of membership and header trail is provided for a light node or user to verify validity
  - If it doesn't exist, return false and `null` items


`sample-generator.py` will generate sample text files under `block/files` labelled ledgerX.txt with X being an integer up to 5 (by default). The python file has configuration variables at the top

`bad-block-generator.py` will generate a bad block to test for a incorrect block validation (Block should not exist in the blockchain)

## Usage

A Makefile has been provided to make running the program easier.

Default commands:

- `make` or `make all` - Compile the Java files, create sample input files and runs the address validation and blockchain validation that will always be valid (Address validation will choose a random address from the ledgers and blockchain validation will validate the blockchain generated from the `block` section)
- `make clean` - Deletes all input files and java compiled `*.class` files

Individual steps:

- `make get-samples` - Generate input ledger samples
- `make recompile` - Compile the java files
- `make reset-io` - Removes all input (\*.txt) and output (\*.out) files
- `make rm-class` - Removes all `*.class` files
- `make new-chain` - Creates sample ledgers (blocks) and generates a blockchain out of them
- `make addr-validate-true` - Will validate that an address exists in the blockchain that will always choose a valid address
- `make addr-validate-false` - Will validate that an address exists in the blockchain that will always fail validation, bad address is passed
- `make chain-validate` - Will validate that the input blockchain file (`block.out`) holds blockchain properties
