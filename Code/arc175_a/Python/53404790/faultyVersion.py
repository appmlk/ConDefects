N = int(input())
P = list(map(int, input().split()))
S = input()

ans_l = 1
ans_r = 1

visited = [False]*N
for p in P:
    visited[p-1] = True
    if visited[p%(N)]:
        if S[p-1] == "?": ans_l *= 2
    else:
        if S[p-1] == "R": ans_l *= 0
    if visited[p-2]:
        if S[p-1] == "?": ans_r *= 2
    else:
        if S[p-1] == "L": ans_r *= 0
    ans_l %= 998244353
    ans_r %= 998244353
    #print(ans_l, ans_r, p, S[p-1])
print(ans_l+ans_r)