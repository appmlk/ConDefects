# [l, r)のうち値[x, y]のみを使って作れるLISの長さを返す
def f(l, r, x, y):

    if (l, r, x, y) in memory:
        return memory[(l, r, x, y)]
    if l >= r:
        memory[(l, r, x, y)] = 0
        return 0
    if x > y:
        memory[(l, r, x, y)] = 0
        return 0

    ret = 0
    #A[l]を使わない
    ret = max(ret, f(l + 1, r, x, y))
    if x <= A[l] <= y:
        #A[l]をstackに入れた直後に取り出してXに追加
        ret = max(ret, 1 + f(l + 1, r, A[l], y))
        #A[l]をstackに入れて, A[k]の直後にXに追加
        for k in range(l + 1, r):
            ret = max(ret, 1 + f(l + 1, k, x, A[l]) + f(k, r, A[l], y))

    memory[(l, r, x, y)] = ret
    
    return ret

n = int(input())
A = list(map(int, input().split()))

memory = {}
for i, a in enumerate(A):
    memory[(i, i + 1, a, a)] = 1

print(f(0, n, 1, 50))
