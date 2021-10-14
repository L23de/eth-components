#!/bin/bash
export FILES=`find ./block/files -name "*.txt"`
java block/code/App `find ./block/files -name "*.txt"` --verbose