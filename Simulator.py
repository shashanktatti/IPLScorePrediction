import csv
from bisect import bisect
from random import random
firstbattingOrder=['AM Rahane','UT Khawaja','SS Tiwary','MS Dhoni','NLTC Perera','GJ Bailey','R Bhatia', 'R Ashwin','A Zampa', 'AB Dinda', 'RP Singh']
firstbowlingOrder=['str binny','parvez rasool','cj jordan','sr watson','parvez rasool','vr aaron','ys chahal','vr aaron','ys chahal','sr watson','str binny','parvez rasool','vr aaron','cj jordan','ys chahal','sr watson','ys chahal','cj jordan','sr watson','cj jordan']
secondBattingOrder=['V Kohli','KL Rahul','AB de Villiers','SR Watson','TM Head','Sachin Baby', 'STR Binny', 'Parvez Rasool', 'CJ Jordan', 'VR Aaron', 'YS Chahal']
secondBowlingOrder=['ab dinda','rp singh','ab dinda','rp singh','ab dinda','nltc perera','a zampa','r bhatia','a zampa','nltc perera','r bhatia','a zampa','r bhatia','nltc perera','r bhatia','rp singh','r ashwin','a zampa','rp singh','ab dinda'] 
batsmanRuns = [0,0,0,0,0,0,0,0,0,0,0]
ballsFaced = [0,0,0,0,0,0,0,0,0,0,0]
stayAtCrease = [0,0,0,0,0,0,0,0,0,0,0] # to see which player is currently at the crease
runs=0 #total score
striker=0
nonStriker=1
wicketsLost=0
ballInProgess=0
FirstInningsScore=0
secondInnings=0
win=0
mapTuple = {float(0):int(0),float(1):int(1),float(2):int(2),float(3):int(3),float(4):int(4),float(5):int(6),float(6):str('out')}
overInProgress=0
while overInProgress<=20:
	if(overInProgress==0):
		print "First Innings in Progress............"
		battingOrder=firstbattingOrder
		bowlingOrder=firstbowlingOrder
		striker=0
		nonStriker=1
	if(overInProgress==20 and secondInnings==0):
		secondInnings=1
		FirstInningsScore=runs
		#second Innings
		print "\n"
		print "\n--------------------------------------------------- \n"
		print "End of First Innings\n"
		print "Team Scored : " + str(runs) + "/" + str(wicketsLost) + "\n"
		print "ScoreCard: \n"
		i=0;
		checkForError=0
		while(i<len(battingOrder)):
			if(stayAtCrease[i]==1):
				checkForError+=1
			i+=1
		i=0
		if((checkForError==2) or (checkForError==1 and wicketsLost<=10)):
			while(i<len(battingOrder)):
				if(stayAtCrease[i]==1):
					#notOut Batsmen
					print str(battingOrder[i]) + " : " + str(batsmanRuns[i]) + "(" + str(ballsFaced[i]) + ")" + "*"
				else:
					print str(battingOrder[i]) + " : " + str(batsmanRuns[i]) + "(" + str(ballsFaced[i]) + ")"
				i+=1
		else:
			#Batsmen Data not found,So shld be seen in cluster
			print "Batsmen Data not found"
		print "---------------------------------------"
		print "\n Second Innings in Progress................"
		battingOrder=secondBattingOrder
		bowlingOrder=secondBowlingOrder
		batsmanRuns = [0,0,0,0,0,0,0,0,0,0,0]
		ballsFaced = [0,0,0,0,0,0,0,0,0,0,0]
		stayAtCrease = [0,0,0,0,0,0,0,0,0,0,0] # to see which player is currently at the crease
		runs=0 #total score
		striker=0
		nonStriker=1
		wicketsLost=0
		overInProgress=0
	ballInProgess=0
	runsThisOver=0
	check=0
	if(overInProgress>0):
		(striker,nonStriker)=(nonStriker,striker) #changing the striker and non-striker after every over
	while (ballInProgess<6 and overInProgress<20):
		flag=0 #to match only one occurence
		with open('results5.csv') as csvfile:
			fileReader = csv.reader(csvfile)
			for row in fileReader:
				if((row[0].lower()==battingOrder[striker].lower()) and (row[1].lower()==bowlingOrder[overInProgress].lower()) and(flag==0)):
					flag=1
					tempArray = [float(row[2]),float(row[3]),float(row[4]),float(row[5]),float(row[6]),float(row[7]),float(row[8])]
					cdf = [tempArray[0]] #cummulative dist func
					for i in xrange(1, len(tempArray)):
	  					cdf.append(cdf[-1] + tempArray[i])

					random_ind = bisect(cdf,random()) #choosing a random index
					#print mapTuple[random_ind] 
					if(type(mapTuple[random_ind])is int):
						#batsman has survived!
						stayAtCrease[striker]=1
						batsmanRuns[striker]+=mapTuple[random_ind]
						ballsFaced[striker]+=1
						if(mapTuple[random_ind]%2==0):
							#0,2, or 6
							striker=striker
						else:
							#1,3
							(striker,nonStriker)=(nonStriker,striker)
						runsThisOver+=mapTuple[random_ind]
						runs+=mapTuple[random_ind]
						if(runs>FirstInningsScore and secondInnings==1 and win==0):
							win=1
							print "Second Team Won the match by " + str(10-wicketsLost) + " wickets"
							overInProgress=20
							ballInProgess=5
							break
					else:
						#batsmen lost his wicket
						stayAtCrease[striker]=0
						wicketsLost+=1
						ballsFaced[striker]+=1
						if(wicketsLost==10 and secondInnings==0):
							#all Out
							overInProgress=19
							ballInProgess=5
							break
						elif(wicketsLost==10 and secondInnings==1 and win==0):
							win=1
							overInProgress=20
							ballInProgess=5
							break
							#end the simulation
						print "\n"
						print str(wicketsLost)," WICKET!!!! \t"
						print (str(battingOrder[striker]) + " was dismissed by " + bowlingOrder[overInProgress] + " for " + str(batsmanRuns[striker]))
						print "\n"
						striker=max(striker,nonStriker)+1 # Next Batsmen
		if(flag==0):
			with open ('bat_cluster.csv') as batsmanClusterFile:
				fileReader = csv.reader(batsmanClusterFile)
				for row in fileReader:
					if(row[0].lower()==battingOrder[striker].lower()):
						batsmanClusterNumber = row[1]

			with open ('bowl_cluster.csv') as batsmanClusterFile:
				fileReader = csv.reader(batsmanClusterFile)
				for row in fileReader:
					if(row[0].lower()==bowlingOrder[overInProgress].lower()):
						bowlerClusterNumber = row[1]

			with open ('results4.csv') as batsmanClusterFile:
				fileReader = csv.reader(batsmanClusterFile)
				for row in fileReader:
					if((row[0] == batsmanClusterNumber) and (row[1] == bowlerClusterNumber) and flag==0):
						flag=1
						tempArray = [float(row[2]),float(row[3]),float(row[4]),float(row[5]),float(row[6]),float(row[7]),float(row[8])]
						cdf = [tempArray[0]] #cummulative dist func
						for i in xrange(1, len(tempArray)):
	  						cdf.append(cdf[-1] + tempArray[i])
						random_ind = bisect(cdf,random()) #choosing a random index
						if(type(mapTuple[random_ind])is int):
							#batsman has survived!
							stayAtCrease[striker]=1
							batsmanRuns[striker]+=mapTuple[random_ind]
							ballsFaced[striker]+=1
							if(mapTuple[random_ind]%2==0):
								#0,2, or 6
								striker=striker
							else:
								#1,3
								(striker,nonStriker)=(nonStriker,striker)
							runsThisOver+=mapTuple[random_ind]
							runs+=mapTuple[random_ind]
							if(runs>FirstInningsScore and secondInnings==1 and win==0):
								win=1
								print "Second Team Won the match by " + str(10-wicketsLost) + " wickets"
								overInProgress=20
								ballInProgess=5
								break
						else:
							#batsmen lost his wicket
							stayAtCrease[striker]=0
							wicketsLost+=1
							if(wicketsLost==10):
								ballInProgess=6
								overInProgress=19
								break
							ballsFaced[striker]+=1
							print "\n"
							print str(wicketsLost)," WICKET!!!! \t"
							print (str(battingOrder[striker]) + " was dismissed by " + bowlingOrder[overInProgress] + " for " + str(batsmanRuns[striker]))
							print "\n"
							striker=max(striker,nonStriker)+1 # Next Batsmen
		ballInProgess+=1
	if(win==0 and overInProgress<20 and wicketsLost<10):
		print "Over: " + str(overInProgress) +  " bowled by " + str(bowlingOrder[overInProgress]) + " and conceded " + str(runsThisOver) +" runs "
	overInProgress+=1
print "\n"
print "\n--------------------------------------------------- \n"
print "End of Second Innings\n"
print "Team Scored : " + str(runs) + "/" + str(wicketsLost) + "\n"
print "ScoreCard: \n"
i=0;
checkForError=0
while(i<len(battingOrder)):
	if(stayAtCrease[i]==1):
		checkForError+=1
	i+=1
i=0
if((checkForError==2) or (checkForError==1 and wicketsLost<=10)):
	while(i<len(battingOrder)):
		if(stayAtCrease[i]==1):
			#notOut Batsmen
			print str(battingOrder[i]) + " : " + str(batsmanRuns[i]) + "(" + str(ballsFaced[i]) + ")" + "*"
		else:
			print str(battingOrder[i]) + " : " + str(batsmanRuns[i]) + "(" + str(ballsFaced[i]) + ")"
		i+=1
else:
	#Batsmen Data not found,So shld be seen in cluster
	print "Batsmen Data not found"
if(runs<FirstInningsScore):
	print " \nFirst Team Won The Match By " +str(FirstInningsScore-runs) + " runs"
print "\n"
