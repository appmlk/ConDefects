from itertools import groupby
N = int(input())
S = list(input())
mod = 10**9 + 7
for i in range(0, N, 2):
    if S[i] == "A":
        S[i] = "B"
    else:
        S[i] = "A"

RLE = [(k, len(list(v))) for k, v in groupby(S)]
ans = 1
for s, cnt in RLE:
    ans *= (cnt+1)//2
    ans %= mod
print(ans)