import sys
input = sys.stdin.readline

from random import randint

N,M,K=map(int,input().split())

if N<=K<=N*M and K%2==N%2:
    print("Yes")
else:
    print("No")
    exit()

ANS=[["+"]*(2*M+1) for i in range(2*N+1)]

for i in range(1,2*N):
    for j in range(1,2*M):
        if i%2==1 and j%2==1:
            ANS[i][j]="o"
        elif i%2==1:
            ANS[i][j]="|"
        elif j%2==1:
            ANS[i][j]="-"

ANS[0][-2]="S"
ANS[-1][-2]="G"

x=1
y=2*M-2
K-=N

while True:
    #for ans in ANS:
    #    print("".join(ans))
    #print("...",x,y)
    if K>=2:
        if x!=2*N-1:
            ANS[x][y]="."
            ANS[x+2][y]="."
            K-=2
            y-=2
        else:
            K-=2
            ANS[x-1][y]="."
            ANS[x-1][y+2]="."
            ANS[x-2][y+1]="|"
            ANS[x][y+1]="."
            y+=4

    #for ans in ANS:
    #    print("".join(ans))

    #print(x,y,K)     

    if K==0:
        if x==2*N-1:
            break
        #print(x,y)
        ANS[x+1][y+1]="."
        if y==2*M-2:
            for i in range(x+1,2*N,2):
                ANS[i][-2]="."

        else:
            for i in range(x+3,2*N,2):
                ANS[i][-2]="."
        break
    else:
        if y==0:
            ANS[x+1][y+1]="."
            ANS[x+3][-2]="."
            x+=4
            if x!=2*N-1:
                y=2*M-2
            else:
                y=1
        



for ans in ANS:
    print("".join(ans))
