#!/bin/bash
# Author:  Chris Kasper 
# count.bash
# Purpose is to print the filename of each file, along
# with the number of lines and words

for file in * ; do # Indexs through current directory
	if [ -f "$file" ] ; then # Checks to make sure the current file is not actually a directory
	lines=$(wc -l "$file")
	words=$(wc -w "$file") 
	echo "$file"  ${lines% $file} ${words% $file}
	fi
	# Note: ${lines% $file} removes the file name
	# wc -l file outputs the number and filename (we don't want the
	# file name to output)
done
exit 0 # success
