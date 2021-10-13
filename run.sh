#!/bin/bash
export FILES=`find block/files -name "*.txt"`
java block/code/App ${FILES}
export BLOCKFILE=`find . -name "*.block.out"`
echo ${BLOCKFILE}
java validation/code/App ${BLOCKFILE}
