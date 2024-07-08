N, A, B = map(int, input().split())
S = list(input())
ans = 0
sa = [0 for _ in range(2 * N)]
wa = [0 for _ in range(2 * N + 1)]
for s in range(2 * N):
    if S[s] == ")":
        sa[s] = -1
    else:
        sa[s] = 1
W = sum(sa)
if W != 0:
    i = 0
    while W != 0:
        if W < 0:
            if S[i] == ")":
                S[i] = "("
                sa[i] = 1
                W += 2
                ans += B
        else:
            if S[2 * N - i - 1] == "(":
                S[2 * N - i - 1] = ")"
                sa[i] = -1
                W -= 2
                ans += B
        i += 1
for i in range(2 * N):
    wa[i + 1] = wa[i] + sa[i]
m = 999999999
mnum = 0
for w in wa:
    if m > w:
        m = w
if m < 0:
    if A > 2 * B:
        ans += (abs(m) + 1) // 2 * 2 * B
    else:
        ans += (abs(m) + 1) // 2 * A
print(ans)