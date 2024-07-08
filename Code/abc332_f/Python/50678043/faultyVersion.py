#ABC332F Random Update Query
#結局双対セグ木はソラで書けばよいため

#入力受取
N, M = map(int, input().split())
A = list(map(int, input().split()))
MOD = 998244353

#push機能付きの双対セグ木を書く
#遅延は(ax + b)の形で保持する
logN = (N - 1).bit_length()
size = 1 << logN
lazy = [(1, 0) for _ in range(2 * size)]
for i in range(N):
    lazy[i + size] = (1, A[i])

def lazy_f(lazy1, lazy2):
    a1, b1 = lazy1
    a2, b2 = lazy2
    return (a1 * a2 % MOD, (b1 * a2 + b2) % MOD)

def push(i):
    i += size
    for h in range(logN - 1, 0, -1):
        j = i >> h
        if lazy[j] != (1, 0):
            Lt, Rt = j << 1, j << 1 | 1
            lazy[Lt] = lazy_f(lazy[Lt], lazy[j])
            lazy[Rt] = lazy_f(lazy[Rt], lazy[j])
            lazy[j] = (1, 0)

def change(Lt, Rt, X):
    diff = Rt - Lt
    a = (diff - 1) * pow(diff, -1, MOD) % MOD
    b = X * pow(diff, -1, MOD) % MOD

    #区間[Lt, Rt)に(ax + b)を作用させる
    push(Lt)
    push(Rt)
    Lt += size
    Rt += size
    while Lt < Rt:
        if Lt & 1:
            lazy[Lt] = lazy_f(lazy[Lt], (a, b))
            Lt += 1
        if Rt & 1:
            Rt -= 1
            lazy[Rt] = lazy_f(lazy[Rt], (a, b))
        Lt >>= 1
        Rt >>= 1

#クエリを実行
for _ in range(M):
    Lt, Rt, X = map(int, input().split())
    Lt -= 1
    change(Lt, Rt, X)

#遅延を全部pushしてからクエリに回答
ans = [0] * N
for i in range(N):
    push(i)
    ans[i] = lazy[i + size][1]

print(*ans)
