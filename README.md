# eth-components

To run/test each component, go into the respective folder and run the following scripts

## Merkle Root

Calculates the merkle root of the merkle tree constructed from a text file containing a list of alphanumerically sorted addresses (of length 40), a single space, and a balance.

To compile: Run `sh recompile.sh`

To run: Place the text file of accounts and balances into the directory containing the bash scripts. Name it `ledger.txt` and run `sh run.sh`
