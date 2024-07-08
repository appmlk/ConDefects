from bisect import *

n, h = map(int, input().split())
td = [tuple(map(int, input().split())) for _ in range(n)] + [(1, 0)]
td.sort() # t の小さい順

prod_max = [t * d for t, d in td]
for i in range(1, n):
    prod_max[i] = max(prod_max[i], prod_max[i - 1])

def check(x):

    # [0, t1_), [t1_, t2_), [t2_, t3_)... があって t2_ <= x < t3_ ならば 
    # 1_ と 2_ は tj * dj
    # 3_ 以降は ターン * dj

    attack_all = 0

    last = x + 1
    now_max_d = 0
    for i in reversed(range(n)):
        t, d = td[i]
        if t > x:
            now_max_d = max(now_max_d, d)
            continue
        if now_max_d == 0:
            attack_all += (last - t) * prod_max[i]
        else:
            # ターン i として i < tjの時 i * dj
            #              i >= tj の時 tj * dj
            # i * dj > tj * dj となるような i を求める
            border_i = (prod_max[i] + now_max_d - 1) // now_max_d
            if border_i >= last:
                attack_all += (last - t) * prod_max[i]
            elif t <= border_i:
                # border_iより左側は tj * djの方が大きい
                # 右側は sum[i=border_i~last-1] i * dj なので等差数列の公式
                attack_all += (border_i - t) * prod_max[i] + (last - border_i) * (border_i + last - 1) * now_max_d // 2
            else: # border_i < t
                # sum[i=t~last-1] i * dj なので等差数列の公式
                attack_all += (last - t) * (t + last - 1) * now_max_d // 2
    
        now_max_d = max(now_max_d, d)
        last = t

        if attack_all >= h:
            break

    return attack_all >= h

ng, ok = -1, 10 ** 18 + 10
while ng + 1 < ok:
    mid = (ng + ok) // 2
    if check(mid):
        ok = mid
    else:
        ng = mid
print(ok)
