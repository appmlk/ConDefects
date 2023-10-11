import sys
input = lambda: sys.stdin.readline().rstrip()
INF = 10**18

N,M = map(int,input().split())
L = list(map(int,input().split()))

def is_ok(arg):
    nrow = 1
    x = 0
    for i in range(N):
        if x == 0:
            if x + L[i] <= arg:
                x += L[i]
            else:
                nrow += 1
                x = L[i]
        else:
            if x + L[i] + 1 <= arg:
                x += L[i]+1
            else:
                nrow += 1
                x = L[i]
                
    if nrow <= M:
        return True
    else:
        return False
        

def meguru_bisect(ng, ok):
    '''
    初期値のng,okを受け取り,is_okを満たす最小(最大)のokを返す
    まずis_okを定義すべし
    ng ok は  とり得る最小の値-1 とり得る最大の値+1
    最大最小が逆の場合はよしなにひっくり返す
    '''
    while (abs(ok - ng) > 1):
        mid = (ok + ng) // 2
        if is_ok(mid):
            ok = mid
        else:
            ng = mid
    return ok

ans = meguru_bisect(0,10**18)
print(ans)