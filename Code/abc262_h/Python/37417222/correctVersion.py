import sys
input = sys.stdin.readline
from operator import itemgetter

N,M,Q=map(int,input().split())
LRX=[list(map(int,input().split())) for i in range(Q)]

mod=998244353

seg_el=1<<((N+1).bit_length()) # Segment treeの台の要素数
SEG=[M]*(2*seg_el) # 1-indexedなので、要素数2*seg_el.Segment treeの初期値で初期化

def getvalue(n,seg_el): # 一点の値を取得
    i=n+seg_el
    ANS=1<<40
    
    ANS=min(SEG[i],ANS)
    i>>=1# 子ノードへ
    
    while i!=0:
        ANS=min(SEG[i],ANS)
        i>>=1

    return ANS

def updates(l,r,x): # 区間[l,r)のminを更新.
    L=l+seg_el
    R=r+seg_el

    while L<R:
        if L & 1:
            SEG[L]=min(x,SEG[L])
            L+=1

        if R & 1:
            R-=1
            SEG[R]=min(x,SEG[R])
        L>>=1
        R>>=1
        
XLIST=[M]
for l,r,x in LRX:
    updates(l,r+1,x)
    XLIST.append(x)

XLIST=sorted(set(XLIST))
DICT={XLIST[i]:i for i in range(len(XLIST))}

ANS=1

LIST=[[] for i in range(len(XLIST))]

for i in range(1,N+1):
    a=getvalue(i,seg_el)
    LIST[DICT[a]].append(i)

LR=[[] for i in range(len(XLIST))]

for l,r,x in LRX:
    LR[DICT[x]].append([l,r])

for i in range(len(XLIST)):
    x=XLIST[i]

    INV=pow(x,mod-2,mod)

    if len(LIST[i])==0 and len(LR[i])>0:
        ANS=0
        continue
    
    LR[i].sort(key=itemgetter(1))

    DP=[1]*(len(LIST[i])+1)
    TOTAL=1

    ind=0
    MINMAX=0
    for j in range(len(LIST[i])):
        while ind<len(LR[i]):
            if j<len(LIST[i]) and LR[i][ind][1]<LIST[i][j]:
                if MINMAX==0 or LIST[i][MINMAX-1]<LR[i][ind][0]:
                    while MINMAX==0 or LIST[i][MINMAX-1]<LR[i][ind][0]:
                        TOTAL-=DP[MINMAX]
                        MINMAX+=1
                ind+=1
            else:
                break
        #print(j,ind)

        DP[j+1]=TOTAL*INV%mod
        TOTAL+=DP[j+1]

    while ind<len(LR[i]):

        if MINMAX==0 or LIST[i][MINMAX-1]<LR[i][ind][0]:
            while MINMAX==0 or LIST[i][MINMAX-1]<LR[i][ind][0]:
                TOTAL-=DP[MINMAX]
                MINMAX+=1
        ind+=1


    #print(TOTAL*pow(x,len(LIST[i]),mod)%mod)

    ANS=ANS*TOTAL*pow(x,len(LIST[i]),mod)%mod

print(ANS%mod)