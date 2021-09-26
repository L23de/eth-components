# eth-components

Implementing the basic parts of a blockchain (Ethereum/Blockchain-based)

## Usage

For each component, there is a respective branch to learn more about it

Inside each branch, there will be a `Makefile`

- `make` or `make all` is used to setup everything and run the program
- `make clean` is used to remove everything created for `make` and reset to just the code

## Components

### `merkle_root`

One of the core items in maintaining the verification property in the blockchain. Merkle-root implementation is implemented such that a Merkle root can be extracted from an array of data (populated by sample files). This is not how a typical Merkle tree or a Merkle-Patricia tree (MPT) would be implemented, as a MPT should allow for sibling verification and proofs of membership/non-membership

### `block`

More similar to a Bitcoin block, where it is based on proof of work (Solving for a nonce). This holds a collection of data, which hashes to a `merkle_root`, and are the singular elements of a blockchain