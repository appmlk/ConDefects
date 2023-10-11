import sys

N,A,B = (int(x) for x in input().split())
C = list(map(int,input().split()))

sumAB = A + B

for i in range(len(C)):
    #print("{} {}".format(i,C[i]))
    if C[i] == sumAB:
        print(i+1)
        exit
    
    

