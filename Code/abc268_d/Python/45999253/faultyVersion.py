from itertools import permutations
from collections import defaultdict as dd
N,M = map(int,input().split())
S = [input().rstrip() for n in range(N)]
T = dd(set)
for m in range(M):
    t = list(input().rstrip())
    if t[0]=="_" or t[-1]=="_" : continue
    key = []
    val = []
    temp = [t[0]]
    for i in range(1,len(t)):
        if t[i]!="_" and temp[-1]=="_":
            val.append(len(temp)-1)
            temp = []
        elif t[i]=="_" and temp[-1]!="_":
            key.append("".join(temp))
            temp = []
        temp.append(t[i])
    T[tuple(key+["".join(temp)])].add(tuple(val))
def recc(surp):
    for i in range(surp+1):
        temp.append(i)
        if len(temp)<N-1 :
            recc(surp-i)
        else :
            if tuple(temp) not in T[key] : 
                ans = ""
                for n in range(N-1):
                    ans+=key[n]+"_"*(temp[n]+1)
                ans+=key[N-1]
                exit(print(ans))
        temp.pop()

if N==1:
    if tuple(S) in T : print(-1)
    else : print(S[0])
else :
    for ss in permutations(range(N),N):
        key = tuple([S[s] for s in ss])
        if key not in T : 
            exit(print("_".join(key)))
        else :
            surp = 16-(sum(len(k) for k in key)+N-1)
            temp = []
            recc(surp)
    else:
        print(-1)