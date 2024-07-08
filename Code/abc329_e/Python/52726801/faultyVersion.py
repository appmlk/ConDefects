N, M = map(int,input().split())
S = list(input())
T = input()

from collections import deque
q = deque([])

for i in range(N-M+1) :
    if "".join(S[i:i+M]) == T :
        q.append(i)

def chk(s_,t) :
    rtn = False
    for i, ch in enumerate(t) :
        if s_[i] == "#" : continue
        if s_[i] != ch : return False
        rtn = True
    return rtn
        

while q :
    idx = q.popleft()
    for j in range(M) :
        if idx+j >= N : break
        S[idx+j] = "#"
    # print(S)

    l = max(0,idx-M)
    r = min(N-M,idx+M)

    for k in range(l,r) :
        if chk(S[k:k+M],T) :
            q.append(k)

if set(S) == {"#"} :
    print("Yes")
else :
    print("No")

