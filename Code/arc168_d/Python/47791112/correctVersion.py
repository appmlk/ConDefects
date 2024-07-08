import sys
input = sys.stdin.readline

N,M=map(int,input().split())
LR=[list(map(int,input().split())) for i in range(M)]

S=[[0]*505 for i in range(505)]

for l,r in LR:
    S[l][r]+=1

for i in range(1,505):
    for j in range(505):
        S[i][j]+=S[i-1][j]


for i in range(505):
    for j in range(1,505):
        S[i][j]+=S[i][j-1]

def rect(x0,x1,y0,y1):
    return S[x1][y1]-S[x0-1][y1]-S[x1][y0-1]+S[x0-1][y0-1]


DP=[[-1]*505 for i in range(505)]
def calc(x,y):
    if x>y:
        return 0
    if DP[x][y]!=-1:
        return DP[x][y]
    ANS=0
    for i in range(x,y+1):
        if rect(x,i,i,y)>=1:
            ANS=max(ANS,1+calc(x,i-1)+calc(i+1,y))

    DP[x][y]=ANS
    return ANS

print(calc(1,503))