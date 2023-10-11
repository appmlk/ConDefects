from collections import deque
N=int(input())
S=input()
def solve(N,S):
    rs=[]
    As,Cs=0,0
    st=False
    for s in S:
        if s=='C':
            Cs+=1
        else:
            if st:
                st=False
                rs[-1]=min(rs[-1],Cs)
            Cs=0
        if s=='R':
            st=True
            rs.append(As)
        if s=='A':
            As+=1
        else:
            As=0
    if st:
        rs[-1]=min(rs[-1],Cs)
    #print(rs)
    sil,gold=0,deque()
    for r in rs:
        if r==0:
            continue
        elif r==1:
            sil+=1
        else:
            gold.append(r)
    time=0
    while True:
        time+=1
        if time%2==1:
            if len(gold)!=0:
                p=gold.popleft()
                p-=1
                if p==1:
                    sil+=1
                else:
                    gold.append(p)
            elif sil>0:
                sil-=1
            else:
                print(time-1)
                return
        else:
            if sil>0:
                sil-=1
            elif len(gold)!=0:
                p=gold.popleft()
            else:
                print(time-1)
                return

'''
import itertools
import random
s='AAARRRCCC'
lis=list(itertools.permutations(s))
cs=random.sample(lis,5)
for c in cs:
    print(c)
    solve(9,c)
'''
    
solve(N,S)