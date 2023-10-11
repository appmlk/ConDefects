import sys, os, io
input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline

def f(u, v):
    return u * (m + 1) + v

n = int(input())
ans = "Yes" if not (n * (n - 1)) % 3 and n >= 6 else "No"
print(ans)
if ans == "No":
    exit()
m = n * (n - 1) // 6
dp = [0] * ((m + 1) * (m + 1))
p = [-1] * ((m + 1) * (m + 1))
dp[0] = 1
s = 0
for i in range(1, n):
    for j in range(min(s, m), -1, -1):
        for k in range(min(s - j, m), -1, -1):
            u = f(j, k)
            if not dp[u]:
                continue
            for nj, nk in [(j + i, k), (j, k + i)]:
                if not max(nj, nk) <= m:
                    continue
                v = f(nj, nk)
                if not dp[v]:
                    dp[v], p[v] = 1, u
    s += i
c = ["R"] * n
i, j = m, m
while f(i, j):
    ni, nj = divmod(p[f(i, j)], m + 1)
    c[i - ni], c[j - nj] = "G", "B"
    i, j = ni, nj
ans = []
for i in range(1, n):
    ans0 = c[n - i] * (n - i)
    ans.append(ans0)
sys.stdout.write("\n".join(ans))