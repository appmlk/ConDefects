import sys
input = sys.stdin.readline
from operator import itemgetter

N=int(input())
P=[list(map(int,input().split())) for i in range(N)]

hosei=10**30
ANS=float("inf")

Q=[]
for a,b,c in P:
    a0=hosei*a//c
    b0=hosei*b//c

    Q.append((a0,b0))

    if a0>b0:
        ANS=min(ANS,a0)
    else:
        ANS=min(ANS,b0)

P=list(set(Q))

P.sort(key=itemgetter(1)) # 一番左下の点から始める。
P.sort(key=itemgetter(0))
 
Q1=[]
Q2=[]
 
def outer_product(x,y,z,w):
    return x*w-y*z
 
for x,y in P:
    while True:
        if len(Q1)<2:
            break
 
        s,t=Q1[-1]
        u,v=Q1[-2]
 
        if outer_product(u-s,v-t,x-u,y-v)<0:
            Q1.pop()
        else:
            break
 
    Q1.append((x,y))
 
    while True:
        if len(Q2)<2:
            break
 
        s,t=Q2[-1]
        u,v=Q2[-2]
 
        if outer_product(u-s,v-t,x-u,y-v)>0:
            Q2.pop()
        else:
            break
 
    Q2.append((x,y))



Q2.reverse()
Q=Q1+Q2[1:]

for i in range(len(Q)):
    a,b=Q[i]
    c,d=Q[i-1]

    if (a-c)**2+(b-d)**2<1000:
        continue

    if a==c:
        ANS=min(ANS,a)
        continue
    if b==d:
        ANS=min(ANS,b)
        continue

    if ((a-c)*(0-b) - (b-d)*(0-a))*((a-c)*(10**100-b) - (b-d)*(10**100-a))<0:
        ANS=min(ANS,(a*(b-d)/(a-c)-b)/((b-d)/(a-c)-1))

print(1/ANS*(hosei))