#!/usr/bin/python
# s2.py
# Author:  Chris Kasper 
# Purpose: Breakdown ids file, make it into a dictionary, print it sorted

import sys

if len(sys.argv)==1: # Check to make sure theres an arg (other then the script)
        f=sys.stdin
else:
	f = open(str(sys.argv[1]), 'r') # Open the file from stdin

l=f.readline()
d=dict()
while l :
	l=l.strip() # Get rid of newlines, trailing spaces, etc
	l=l.split(" ",1) # Split off after ID
	d[int(l[0])]=l[1] # Make ID an int (not str) for key, add name to dictionary
	l=f.readline()
f.close()
for k in sorted(d): # This creates a sorted list of keys from d
	print '%i %s' % (k, d[k])
