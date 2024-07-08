import sys
input = sys.stdin.readline
inf = 10 ** 18


def read(dtype=int):
    return list(map(dtype, input().split()))


n, = read()
s, = read(str)
s = list(map(int, s))
c = read()
dp = [[inf, inf], [inf, inf]]
dp[s[0] ^ 1][0] = c[0]
dp[s[0]][0] = 0
for i in range(1, n):
    ndp = [[inf, inf], [inf, inf]]
    for k in range(2):
        cost = k and c[i]
        for x in range(2):
            for y in range(2):
                same = s[i] == x ^ k
                if y and same:
                    continue

                ndp[s[i] ^ k][same | y] = min(
                    ndp[s[i] ^ k][same | y], dp[x][y] + cost)
    dp = ndp
print(min(map(min, dp)))
