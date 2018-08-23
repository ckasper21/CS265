#!/usr/bin/env python
# Python 2.7.12
# Author: Chris Kasper
# Date: 11/29/17
# assn4 - Bank Transactions
# Accepts 1 of 4 possible arguments: -i, -h, -t, and -?.

import sys
import os
import operator
import datetime

def create_dict(): # Create dictionary based on log file 
	acct_dict = {} 
	filename = os.environ['ACCT_LIST'] # Get filename from env variable ACCT_LIST
        file = open(filename,"r+")
        for line in file:
		line = line.strip('\n')
		line = line.split(':')
		if acct_dict.has_key(line[0]): # If account number exists in dictionary, add transaction as an array
			acct_dict[line[0]].append(line[2:]) 
		
		else:
			acct_dict[line[0]] = [line[1]] # Else, make new entry in dictionary
			acct_dict[line[0]].append(line[2:])
	file.close()
	return acct_dict
	
	# Sample Dictionary entry: acct_dict['1234']=['Joe Smith',[TRANSACTION],[TRANSACTION]]

def run_info(acct_dict,sorted_keys):
	usr_input = 0
	while (usr_input != 'q'):
		i = 1
		print("Info\n"+"-----") # Print menu
		for key in sorted_keys:
			print "%d) %s %s" % (i,acct_dict[key][0],key)
			i+=1
		print "q)uit\n"
		usr_input = raw_input("Enter Choice => ")
		if usr_input.isdigit() and (int(usr_input)<i):	# Check for entry
			total = 0
			key = sorted_keys[int(usr_input)-1] 
			for t in range(1,len(acct_dict[key])): # Calculate total based on account history
				if acct_dict[key][t][1]=='D':
					total+=float(acct_dict[key][t][2])
				else:
					total-=float(acct_dict[key][t][2])
			print"\n" + "	account #: %s" % key
			print"	name: %s" % acct_dict[key][0]
			if total<0:
				print"	balance: -$%.2f" % (float(total)*-1)
			else:
				print"	balance: $%.2f" % float(total)
			raw_input("\nPress enter to return to menu...\n") # Prompt user to return to menu

def run_hist(acct_dict,sorted_keys):
	usr_input = 0
        while (usr_input != 'q'):
                i = 1
                print("History\n"+"-------") # Print menu
                for key in sorted_keys:
                        print "%d) %s %s" % (i,acct_dict[key][0],key)
                        i+=1
                print "q)uit\n"
                usr_input = raw_input("Enter Choice => ")
                if usr_input.isdigit() and (int(usr_input)<i): # Check for entry
                        key = sorted_keys[int(usr_input)-1]
			print "\n"
                        for t in range(1,len(acct_dict[key])): # Print each transaction from account
                                if acct_dict[key][t][1]=='D':
					print "	%s deposit $%s" % (acct_dict[key][t][0], acct_dict[key][t][2])
                                else:
        				print "	%s withdraw $%s" % (acct_dict[key][t][0], acct_dict[key][t][2])                
			raw_input("\nPress enter to return to menu...\n")
	
def run_trans(acct_dict,sorted_keys):
	usr_input = 0
        while (usr_input != 'q'):
                i = 1
                print("New Transaction\n"+"------------") # Print menu
                for key in sorted_keys:
                        print "%d) %s %s" % (i,acct_dict[key][0],key)
                        i+=1
		create = i
		print "%d) Create" % i
                print "q)uit\n"
                usr_input = raw_input("Enter Choice => ")
                if usr_input.isdigit() and (int(usr_input)<=i): # Check for entry
			if usr_input == str(create): 
				# Create account
				accnum = raw_input("\nEnter a new account number: ")
				while acct_dict.has_key(accnum): # Check to see if account number exists
					accnum = raw_input("Account number already exists. Enter a different account number: ")	
				accname = raw_input("Enter a name for the account: ")
				acct_dict[accnum]=[accname] # Add new account to dictionary
				key = accnum
				sorted_keys = sort_dict(acct_dict) # Resort dictionary keys		
			else:
                        	key = sorted_keys[int(usr_input)-1]
			newtran(key, acct_dict) # Add new transaction for account
			raw_input("\nPress enter to return to menu...\n")

def newtran(key,acct_dict):
	tran_type = raw_input("(d)eposit or (w)ithdraw? ") # Prompts the user for type of transaction
	while tran_type not in ('d','w'):
		tran_type = raw_input("(d)eposit or (w)ithdraw? ")
	amount = raw_input("Enter amount: $") # Prompts for amount
	time=((str(datetime.datetime.now()))[0:10]).split('-') # One-liner to get current time
	tran = "%s:%s:%s.%s.%s:%s:%s\n" % (key,acct_dict[key][0],time[0][2:],time[1],time[2],tran_type.upper(),amount) # Timestamp
	filename = os.environ['ACCT_LIST']
        file = open(filename,"a") # Open log file, set to append it (not overwriting previous transactions)
	file.write(tran) # Add transaction to log
	file.close()

def sort_dict(acct_dict): # Returns keys in order based on name under account
	sorted_keys = []
	sorted_dict = sorted(acct_dict.items(), key=operator.itemgetter(1))	
	for i in range(0,len(sorted_dict)):
		sorted_keys.append(sorted_dict[i][0])
	return sorted_keys

def start(option):
	acct_dict = create_dict() # Get dictionary of accounts
	sorted_keys = sort_dict(acct_dict) # Get sorted dictionary keys based on name under account
	if option == '-i': # Run different function based on supplied arg
		run_info(acct_dict,sorted_keys)
	elif option == '-h':
		run_hist(acct_dict,sorted_keys)
	elif option == '-t':
		run_trans(acct_dict,sorted_keys)
	else:
		print('Accepted arguments...\n'+'-i: Shows account info\n'+ '-h: Shows account history\n' 
			+ '-t: Adds transactions or accounts\n'+'-?: Provides usage details\n')
		sys.exit()

def main():
	# Make sure $ACCT_LIST was set and exported
	try:
		os.environ["ACCT_LIST"]
	except KeyError:
		print "ERROR: Environment variable ACCT_LIST not set"
		sys.exit()
	if len(sys.argv) >= 2: # Check command-line arg
		if sys.argv[1] in ('-i','-h','-t','-?'): # Only take the first arg
			start(sys.argv[1])
		else:
			print 'ERROR:', sys.argv[1] + ' is not a valid argument'
			sys.exit()
	else:
		start('-?') # If no arg is supplied, run usage message

if __name__ == '__main__':
	main()
