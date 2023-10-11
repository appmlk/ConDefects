from itertools import permutations
N,M = map(int,input().split())
S = [input().rstrip() for n in range(N)]
T = set([input().rstrip() for m in range(M)])
def recc(surp):
    for i in range(surp+1):
        temp.append(i)
        if len(temp)<N-1 :
            recc(surp-i)
        else :
            ans = ""
            for n in range(N-1):
                ans+=S[ss[n]]+"_"*(temp[n]+1)
            ans+=S[ss[N-1]]
            if ans not in T:
                exit(print(ans))
        temp.pop()

if N==1:
    if tuple(S) in T or len(S[0])<3 : print(-1)
    else : print(S[0])
else :
    for ss in permutations(range(N),N):
        surp = 16-(sum(len(S[s]) for s in ss)+N-1)
        temp = []
        recc(surp)
    else:
        print(-1)