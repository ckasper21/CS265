#!/usr/bin/python
# s2.py
# Author:  Chris Kasper 
# Purpose: Breakdown students (.csv) input file, outputs name and average

import sys

if len(sys.argv)==1: # Check to make sure theres an arg (other then the script)
        sys.exit()

f = open(str(sys.argv[1]), 'r') # Open the file from arg1
l=f.readline()
while l :
	total=0
	l=l.strip() # Get rid of newlines, trailing spaces, etc
	l=l.split(",") # Split by the commas for CSV
	for x in range(1,len(l)): # Index through grades. l[0] is the name
		total+=int(l[x])
	average=(float(total)/(len(l)-1))
	print "%s %.2f" % (l[0],average)
	l=f.readline()
f.close()
