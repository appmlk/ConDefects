import sys
sys.setrecursionlimit(10**7)
def I(): return int(sys.stdin.readline().rstrip())
def MI(): return map(int,sys.stdin.readline().rstrip().split())
def LI(): return list(map(int,sys.stdin.readline().rstrip().split()))
def LI2(): return list(map(int,sys.stdin.readline().rstrip()))
def S(): return sys.stdin.readline().rstrip()
def LS(): return list(sys.stdin.readline().rstrip().split())
def LS2(): return list(sys.stdin.readline().rstrip())


N, K = MI()
S = S()

count_x, count_y = 0, 0
for s in S:
    if s == "X":
        count_x += 1
    else:
        count_y += 1


def calc(S, N, K):  # K <= count_x の場合
    y_pos = []
    for i in range(N):
        if S[i] == "Y":
            y_pos.append(i)
    if y_pos == []:
        return max(0, K - 1)
    diff = [y_pos[_ + 1] - y_pos[_] - 1 for _ in range(len(y_pos) - 1)]
    diff.sort()
    res = K
    s = 0
    for d in diff:
        s += d
        if s <= K:
            res += 1
        else:
            break
    return res


if K <= count_x:
    ans = calc(S, N, K)
else:
    inverse_S = "".join(["X" if s == "Y" else "Y" for s in S])
    ans = calc(inverse_S, N, N - K)

print(ans)
