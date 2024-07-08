# https://atcoder.jp/contests/arc173/tasks/arc173_b
import sys; input: lambda _: sys.stdin.readline().rstrip()
# import pypyjit; pypyjit.set_param('max_unroll_recursion=-1')
sys.setrecursionlimit(10001000)
int1=lambda x: int(x) - 1

def isonline(u, v, w):
    dw0, dw1 = w[0] - u[0], w[1] - u[1]
    dv0, dv1 = v[0] - u[0], v[1] - u[1]
    return dw1*dv0 == dv1*dw0

N = int(input())
P = [list(map(int, input().split())) for _ in range(N)]

ret = 0
for i in range(N):
    for j in range(i):
        cnt = 0
        for k in range(N):
            if isonline(P[i], P[j], P[k]):
                cnt += 1
        ret = max(ret, cnt)

u = min(ret//2, N-ret)
if ret-2*u >= 3:
  print(u)
else:
  print(N//3)