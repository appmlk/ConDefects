N,K = map(int,input().split())
S = input()
X = S.count("X")
if X < K:
    K = N-K
    T = []
    for s in S:
        if s == "X":
            T.append("Y")
        else:
            T.append("X")
    S = T

if all(s == "X" for s in S):
    print(K-1)
    exit()

C = [10**6]

state = 0
cnt = 0

ans = 0
for i in range(1,N):
    if state == 0:
        if S[i-1] == "Y" and S[i] == "X":
            cnt = 1
            state = 1
    else:
        if S[i-1] == "X" and S[i] == "Y":
            C.append(cnt)
            state = 0
            cnt = 0
        else:
            cnt += 1

    if S[i-1] == "Y" and S[i] == "Y":
        ans += 1


C.sort()
for c in C:
    if K >= c:
        ans += c+1
        K -= c
    else:
        ans += K
        break

print(ans)