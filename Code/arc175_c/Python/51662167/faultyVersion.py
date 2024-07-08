import sys
input = sys.stdin.readline

N=int(input())
LR=[list(map(int,input().split())) for i in range(N)]

for i in range(N-1):
    x,y=LR[i]
    z,w=LR[i+1]

    if y>w:
        LR[i][1]=max(w,x)

    if w>y:
        LR[i+1][1]=max(y,z)

for i in range(N-2,-1,-1):
    x,y=LR[i]
    z,w=LR[i+1]

    if y>w:
        LR[i][1]=max(w,x)

    if w>y:
        LR[i+1][1]=max(y,z)

for i in range(N-1):
    x,y=LR[i]
    z,w=LR[i+1]

    if y>w:
        LR[i][1]=max(w,x)

    if w>y:
        LR[i+1][1]=max(y,z)

for i in range(N-2,-1,-1):
    x,y=LR[i]
    z,w=LR[i+1]

    if y>w:
        LR[i][1]=max(w,x)

    if w>y:
        LR[i+1][1]=max(y,z)

ANS=[-1]*N
for i in range(N):
    if LR[i][0]==LR[i][1]:
        ANS[i]=LR[i][0]

mae=-1
maeind=-1
for i in range(N):
    if ANS[i]!=-1:
        now=ANS[i]
        nowind=i


        if mae==-1:
            for j in range(i-1,maeind,-1):
                if LR[j][0]<=ANS[j+1]<=LR[j][1]:
                    ANS[j]=ANS[j+1]
                else:
                    ANS[j]=LR[j][1]
            mae=now
            maeind=i
        else:
            while nowind>maeind+1:
                if mae<now:
                    if LR[maeind+1][1]<=mae:
                        ANS[maeind+1]=LR[maeind+1][1]
                    elif LR[maeind+1][0]<=mae<=LR[maeind+1][1]:
                        ANS[maeind+1]=mae
                    else:
                        ANS[maeind+1]=LR[maeind+1][0]

                    maeind+=1
                    mae=ANS[maeind]
                else:
                    if LR[nowind-1][1]<=now:
                        ANS[nowind-1]=LR[nowind-1][1]
                    elif LR[nowind-1][0]<=now<=LR[nowind-1][1]:
                        ANS[nowind-1]=now
                    else:
                        ANS[nowind-1]=LR[nowind-1][0]

                    nowind-=1
                    now=ANS[nowind]

            mae=ANS[i]
            maeind=i

#print(ANS)

if mae==-1:
    MAX=-1
    for l,r in LR:
        MAX=max(l,MAX)

    print(*[MAX]*N)
    exit()
for i in range(maeind+1,N):
    if LR[i][0]<=mae<=LR[i][1]:
        ANS[i]=mae
    else:
        ANS[i]=max(mae,LR[i][1])
    mae=ANS[i]

print(*ANS)
            

