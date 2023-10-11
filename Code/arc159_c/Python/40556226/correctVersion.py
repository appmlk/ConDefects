import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
INF = 2**63-1
mod = 998244353
import random
n = ii()

a = li()

def solve(n, a, p):
    ans = []
    for i in range(random.randint(0, p)):
        p = list(range(1, n + 1))
        random.shuffle(p)
        ans.append(p)
        for j in range(n):
            a[j] += p[j]
    for i in range(10 ** 4):
        if sum(a) % n == 0:
            break
        p = list(range(1, n + 1))
        random.shuffle(p)
        ans.append(p)
        for j in range(n):
            a[j] += p[j]
    else:
        return []

    def add(i):
        p = list(range(1, n + 1))
        p[i], p[i+1] = p[i+1], p[i]
        ans.append(p)
        p = list(range(1, n + 1))
        q = p[::-1]
        ans.append(q)
        a[i] += 1
        a[i + 1] -= 1

    def sub(i):
        p = list(range(1, n + 1))
        ans.append(p)
        q = p[::-1]
        q[i], q[i + 1] = q[i + 1], q[i]
        ans.append(q)
        a[i] -= 1
        a[i + 1] += 1

    ave = sum(a) // n

    for i in range(n):
        a[i] -= ave
    for i in range(n - 1):
        while a[i] > 0:
            sub(i)
        while a[i] < 0:
            add(i)

    if max(a) == min(a) == 0 and len(ans) <= 10 ** 4:
        return ans
    else:
        return []


for p in range(14):
    ans = solve(n, a[::], p * 10)
    if len(ans) > 0:
        print('Yes')
        print(len(ans))
        for v in ans:
            print(*v)
        exit()
print('No')