#!/usr/bin/env python3
n, m = map(int, input().split())
S = input()

muji_t = m
logo_t = 0
ans = 0
for s in S:
    if s == '2':
        if logo_t == 0:
            ans += 1
        else:
            logo_t -= 1
    elif s == '1':
        if muji_t == 0 and logo_t == 0:
            ans += 1
        elif muji_t == 0:
            logo_t -= 1
        else:
            muji_t -= 1
    else:
        logo_t = ans
        muji_t = m

print(ans)