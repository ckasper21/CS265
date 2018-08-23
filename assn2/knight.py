#!/usr/bin/env python
# Author: Chris Kasper
# assn2 - Knight's Tour
# Accepts 3 arguments: row col attempts

from random import randint
import sys

def CheckMoves(pos,board): # See avaliable moves for knight
        options=[]
        moves_list=[[-2,-1],[-2,1],[-1,-2],[-1,2],[1,-2],[1,2],[2,-1],[2,1]] 
	for move in moves_list:
		if (pos[0]+move[0]) >= 0 and (pos[1]+move[1]) >= 0: # Check to make sure move is a negative index on board
			try:
				spot=board[pos[0]+move[0]][pos[1]+move[1]]
			except IndexError: # Check to make sure indices aren't exceeded
				None
			else:
				if spot == ' x': 
					move=[pos[0]+move[0],pos[1]+move[1]] # Add move to list of options if x exists
					options.append(move)
        return options # List of spaces the knight can move to

def Result(board): # Checks the board to see if the attempt was a success/fail
	for row in range(0,len(board)):
                for col in range(0,len(board[row])):
                        if board[row][col]==' x':
				return False
	return True
			
def Move(options,num,board): # Picks a random space to move to based on the options from CheckMoves
   	randnum=randint(0,len(options)-1)
	new_pos=options[randnum]
	if num < 10: # This is for printing the board, making it look nicer
		board[new_pos[0]][new_pos[1]]= ' '+str(num)
	else:
		board[new_pos[0]][new_pos[1]]=str(num)
	return new_pos # Returns new knight position on the board

def ResetBoard(board): # Resets the board, and initial position for a fresh attempt
	for row in range(0,len(board)):
		for col in range(0,len(board[row])):
			board[row][col]=' x'
	board[0][0]=' 1'
	return board	

def PrintBoard(board): # Prints the board
	for row in board:
		row_print=','.join(row)
		row_print=row_print.replace(',','  ')
		print row_print

def GenerateBoard(r,c): # Generates board based on command-line args
	board=[]
	if r<=0 or c<=0:
		print "ERROR: Board has invalid row column combination"
		sys.exit()
	board=[[0 for x in range(c)] for y in range(r)]
	for i in range(0,r):
		for j in range(0,c):
			board[i][j]=' x' # x indicates an unused space on the board ; space is place holder for two digit nums
	board[0][0]=' 1'
	return board

def Play(board,attempts): # Runs the knights tour simulation
	cur_attempt=1
	pos=[0,0] # Initialize position at the top lefthand corner
	move_num=2 # This number is the next one to be put on the board
        while cur_attempt <= attempts:
		options=CheckMoves(pos,board)
		if not options: # If no options, check the board
			outcome=Result(board)
			if not outcome: # If Result returns false
				if (cur_attempt + 1) > attempts: # If last attempt
					cur_attempt+=1
				else:
					cur_attempt+=1
					board=ResetBoard(board)
					pos=[0,0]
					move_num=2
			else:
				print 'SUCCESS:'
				PrintBoard(board)
				sys.exit()
		else:
			pos=Move(options,move_num,board) 
			move_num+=1 
			
	print 'FAIL:'
	PrintBoard(board)

def main():
	if len(sys.argv)==4: # Check command-line args
		try:
			row=int(sys.argv[1])
                        col=int(sys.argv[2])
                        attempts=int(sys.argv[3])
		except ValueError:
			print 'ERROR: All args have to be integers'
			sys.exit()
	else:
		print('ERROR: This python scripts has 3 arguments')
		sys.exit()

	board=GenerateBoard(row,col)
	if attempts <= 0:
		print 'ERROR: attempts must be greater then 0'
		sys.exit()
	else:
		Play(board,attempts)

if __name__ == '__main__':
	main()
