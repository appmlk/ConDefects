from functools import lru_cache

@lru_cache
def f(maxi, no):
    if maxi < 10:
        return maxi - (0 < no <= maxi)
    q = maxi // 10
    r = maxi % 10
    ans = 0
    for x in range(10):
        if x == no:
            continue
        if x <= r:
            ans += f(q, x) + (x != 0)
        else:
            ans += f(q - 1, x) + (x != 0)
    return ans

def g(maxi):
    if maxi < 10:
        return maxi
    q = maxi // 10
    r = maxi % 10
    ans = 0
    for x in range(10):
        if x <= r:
           ans += f(q, x) + (x != 0)
        else:
            ans += f(q - 1, x) + (x != 0)
    return ans

T = int(input())
for _ in range(T):
    k = int(input())
    lo = 0
    hi = 10**15
    # f(lo) < k
    while lo < hi - 1:
        m = (lo + hi) // 2
        s = g(m)
        if s < k:
            lo = m
        else:
            hi = m
    print(hi)
