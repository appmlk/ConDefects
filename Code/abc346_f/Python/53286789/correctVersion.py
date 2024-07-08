N = int(input())
S = input()
T = input()

sl = len(S)
tl = len(T)
ind = [[] for i in range(26)]
for i in range(2 * sl):
    ind[ord(S[i % sl]) - ord("a")].append(i)
cnt = [0 for i in range(26)]
for i in range(26):
    cnt[i] = len(ind[i]) // 2

import bisect
def is_ok(k):
    itr = -1
    # f(S, N)[0, itr)まで見た状況
    for i in range(tl):
        q = itr // sl
        r = itr % sl
        a = ord(T[i]) - ord("a")
        if cnt[a] == 0: return False
        now = cnt[a] * q + bisect.bisect_right(ind[a], r)
        next = now + k
        nq = next // cnt[a]
        nr = next % cnt[a]
        if nr == 0:
            nq -= 1
            nr += cnt[a]
        itr = nq * sl + ind[a][nr - 1]
        # print(k, itr, now, next)
        if itr >= N * sl:
            return False
    return True

def m_bisect(ng, ok):
    while abs(ok - ng) > 1:
        mid = (ok + ng) // 2
        if is_ok(mid):
            ok = mid
        else:
            ng = mid
    return ok

ok, ng = 0, 10 ** 18
print(m_bisect(ng, ok))