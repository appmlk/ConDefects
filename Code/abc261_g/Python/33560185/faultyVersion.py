import sys
input = sys.stdin.readline
from collections import deque

S=input().strip()
T=input().strip()
K=int(input())
CHANGE=[input().split() for i in range(K)]

DP=[[[1<<60]*len(T) for i in range(len(T))] for j in range(26)]

C1=[]
C2=[]

for x,y in CHANGE:
    if len(y)==1:
        C1.append([x,y])
    else:
        C2.append([x,y])

E1=[[1<<60]*26 for i in range(26)]

for i in range(26):
    E1[i][i]=0

E2=[[] for i in range(26)]
for x,y in C1:
    x0=ord(x)-97
    y0=ord(y)-97

    E2[y0].append(x0)

for i in range(26):
    Q=deque()
    Q.append(i)
    while Q:
        x=Q.popleft()

        for to in E2[x]:
            if E1[i][to]>E1[i][x]+1:
                E1[i][to]=E1[i][x]+1
                Q.append(to)

for i in range(len(T)):
    x=ord(T[i])-97
    for j in range(26):
        DP[j][i][i]=E1[x][j]

for sa in range(len(T)):
    for i in range(sa,len(T)):
        first=i-sa
        last=i

        for x,y in C2:
            DPX=[[1<<60]*len(T) for i in range(len(y))]

            for j in range(len(y)):
                sx=ord(y[j])-97

                if j==0:
                    for k in range(first,last+1):
                        if DP[sx][first][k]!=1<<60:
                            DPX[j][k]=DP[sx][first][k]
                else:
                    for k in range(first,last+1):
                        if DPX[j-1][k]!=1<<60:
                            for l in range(k+1,last+1):
                                if DP[sx][k+1][l]!=1<<60:
                                    DPX[j][l]=min(DPX[j][l],DPX[j-1][k]+DP[sx][k+1][l])
                                    
            if DPX[len(y)-1][last]!=1<<60:
                DP[ord(x)-97][first][last]=DPX[len(y)-1][last]+1

        for j in range(26):
            if DP[j][first][last]!=1<<60:
                for to in range(26):
                    DP[to][first][last]=min(DP[to][first][last],DP[j][first][last]+E1[j][to])
                    

DPX=[[1<<60]*len(T) for i in range(len(S))]

for j in range(len(S)):
    sx=ord(S[j])-97

    if j==0:
        for k in range(len(T)):
            if DP[sx][0][k]!=1<<60:
                DPX[j][k]=DP[sx][0][k]
    else:
        for k in range(len(T)):
            if DPX[j-1][k]!=1<<60:
                for l in range(k+1,len(T)):
                    if DP[sx][k+1][l]!=1<<60:
                        DPX[j][l]=min(DPX[j][l],DPX[j-1][k]+DP[sx][k+1][l])

if DPX[len(S)-1][last]>=1<<30:
    print(-1)
else:
    print(DPX[len(S)-1][last])