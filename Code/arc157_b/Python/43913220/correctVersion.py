import sys

N, K = map(int,input().split())
S = list(input())

x_cnt = 0
for i in range(N):
    if S[i] == "X":
        x_cnt += 1
    
if N == 1:
    print(0)
    sys.exit()

if x_cnt < K:
    for i in range(N):
        if S[i] == "X":
            S[i] = "Y"
        elif S[i] == "Y":
            S[i] = "X"
    K = N-K
    
if S == ["X"]*N:
    print(max(0,K-1))
    sys.exit()

haba = []
now = 0
for i in range(N):
    if S[i] == "X":
        now += 1
    elif S[i] == "Y":
        haba.append(now)
        now = 0
haba.append(now)

haba = haba[1:-1]
haba.sort()

ans = 0
    
for h in haba:
    if h <= K:
        K -= h
        ans += h+1
    else:
        break
        
ans += K
print(ans)