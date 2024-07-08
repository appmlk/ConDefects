import sys
input = sys.stdin.readline

N=int(input())
A=list(map(int,input().split()))

LIST=[[] for i in range(N+100)]

for i in range(N):
    LIST[A[i]].append(i)

# 非再帰遅延伝搬セグ木
# 高々N点を更新

seg_el=1<<(N.bit_length()) # Segment treeの台の要素数
SEG=[[0,1] for i in range(2*seg_el)] # 1-indexedなので、要素数2*seg_el.Segment treeの初期値で初期化
# 最小値、最小値の個数


seg_height=1+N.bit_length() # Segment treeのたかさ
for i in range(2*seg_el):
    SEG[i][1]=1<<(seg_height-i.bit_length())

LAZY=[0]*(2*seg_el) # 1-indexedなので、要素数2*seg_el.Segment treeの初期値で初期化

def indexes(L,R): # 遅延伝搬すべきノードのリストを下から上の順に返す. （つまり, updateやgetvaluesで見るノードより上にあるノードたち）
    INDLIST=[]

    R-=1
    
    L>>=1
    R>>=1

    while L!=R:
        if L>R:
            INDLIST.append(L)
            L>>=1
        else:
            INDLIST.append(R)
            R>>=1

    while L!=0:
        INDLIST.append(L)
        L>>=1

    return INDLIST
        

def updates(l,r,x): # 区間[l,r)を x 加算
        
    L=l+seg_el
    R=r+seg_el

    L//=(L & (-L))
    R//=(R & (-R))

    UPIND=indexes(L,R)
    
    for ind in UPIND[::-1]: # 遅延伝搬. 上から更新していく. 
        if LAZY[ind]!=0:
            plus_lazy=LAZY[ind]
            
            SEG[ind<<1][0]=plus_lazy+SEG[ind<<1][0]
            SEG[1+(ind<<1)][0]=plus_lazy+SEG[1+(ind<<1)][0]
            
            LAZY[ind<<1]+=plus_lazy
            LAZY[1+(ind<<1)]+=plus_lazy
            
            LAZY[ind]=0
    
    while L!=R:
        if L > R:
            SEG[L][0]=SEG[L][0]+x
            LAZY[L]+=x
            L+=1
            L//=(L & (-L))

        else:
            R-=1
            SEG[R][0]=SEG[R][0]+x
            LAZY[R]+=x
            R//=(R & (-R))

    for ind in UPIND:
        if SEG[ind<<1][0]==SEG[1+(ind<<1)][0]:
            SEG[ind][0]=SEG[ind<<1][0]
            SEG[ind][1]=SEG[ind<<1][1]+SEG[1+(ind<<1)][1]
            
        elif SEG[ind<<1][0]<SEG[1+(ind<<1)][0]:
            SEG[ind][0]=SEG[ind<<1][0]
            SEG[ind][1]=SEG[ind<<1][1]

        else:
            SEG[ind][0]=SEG[1+(ind<<1)][0]
            SEG[ind][1]=SEG[1+(ind<<1)][1]
        
def getvalues(l,r):

    L=l+seg_el
    R=r+seg_el

    L//=(L & (-L))
    R//=(R & (-R))

    UPIND=indexes(L,R)
    
    for ind in UPIND[::-1]: # 遅延伝搬. 上から更新していく. 
        if LAZY[ind]!=0:
            plus_lazy=LAZY[ind]
            
            SEG[ind<<1][0]=plus_lazy+SEG[ind<<1][0]
            SEG[1+(ind<<1)][0]=plus_lazy+SEG[1+(ind<<1)][0]
            
            LAZY[ind<<1]+=plus_lazy
            LAZY[1+(ind<<1)]+=plus_lazy
            
            LAZY[ind]=0
            
    ANS=1<<60
    ko=0

    while L!=R:
        if L > R:
            if ANS>SEG[L][0]:
                ANS=SEG[L][0]
                ko=SEG[L][1]
            elif ANS==SEG[L][0]:
                ko+=SEG[L][1]
            L+=1
            L//=(L & (-L))

        else:
            R-=1
            if ANS>SEG[R][0]:
                ANS=SEG[R][0]
                ko=SEG[R][1]
            elif ANS==SEG[R][0]:
                ko+=SEG[R][1]
            R//=(R & (-R))

    return ANS,ko

LIST2=[]

for i in range(len(LIST)):
    for j in range(len(LIST[i])):
        if j>=1:
            x=LIST[i][j-1]+1
        else:
            x=0

        if j+1<len(LIST[i]):
            y=LIST[i][j+1]-1
        else:
            y=N-1

        LIST2.append((x,LIST[i][j],LIST[i][j],y))

PLUS=[[] for i in range(N+5)]
MINUS=[[] for i in range(N+5)]

for x,y,z,w in LIST2:
    PLUS[x].append((z,w))
    MINUS[y+1].append((z,w))

ANS=0
for i in range(N):
    for z,w in PLUS[i]:
        updates(z,w+1,1)
    for z,w in MINUS[i]:
        updates(z,w+1,-1)

    c,ko=getvalues(0,N)
    

    if c==0:
        ANS+=N-ko
    else:
        ANS+=N

print(ANS)
