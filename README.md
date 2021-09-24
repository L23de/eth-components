# eth-components

Implementing the basic parts of a blockchain (Ethereum/Blockchain-based)

## Usage

For each component, there is a respective branch to learn more about it

Inside each branch, there will be a couple of `sh` files and a `sample-generator.py` file

 - `sample-generator.py` is used to create sample file(s) as input for App.java
 - `recompile.sh` is used to recompile the Java files
 - `run.sh` is used to run App.java (There may be some variations of the `run` script)

## Components

### `merkle_root`

One of the core items in maintaining the verification property in the blockchain. Merkle-root implementation is implemented such that a Merkle root can be extracted from an array of data (populated by sample files). This is not how a typical Merkle tree or a Merkle-Patricia tree (MPT) would be implemented, as a MPT should allow for sibling verification and proofs of membership/non-membership

### `block`

More similar to a Bitcoin block, where it is based on proof of work (Solving for a nonce). This holds a collection of data, which hashes to a `merkle_root`, and are the singular elements of a blockchain