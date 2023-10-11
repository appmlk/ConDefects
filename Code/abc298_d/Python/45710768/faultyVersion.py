from collections import deque
Q = int(input())
q = [list(map(int, input().split())) for _ in range(Q)]

MOD = 998244353

def mod_pow(x, n, mod):
    result = 1
    base = x
    while n > 0:
        if n % 2 == 1:
            result = (result * base) % mod
        base = (base * base) % mod
        n = n // 2
    return result

pos = 0
queue = deque([1])
ans = 1
for d in q:
    if d[0] == 1:
        queue.append(d[1])
        ans = (ans * 10 + d[1]) % MOD
    elif d[0] == 2:
        front = queue.popleft()
        ans = (ans - front * mod_pow(10, len(queue)-1, MOD)) % MOD
    elif d[0] == 3:
        print(ans)