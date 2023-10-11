#!/usr/bin/env python3

l1, r1, l2, r2 = [int(x) for x in input().split()]
if r1 <= l2 or r2 < l1:
    print(0)
else:
    print(min(r1, r2) - max(l1, l2))
