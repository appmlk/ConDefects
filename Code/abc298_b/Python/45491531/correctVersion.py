#!/usr/bin/env python3


def r(a):
    a = a[::-1]
    a = list(map(list, zip(*a)))
    return a


n = int(input())
a = [list(input().split()) for _ in range(n)]
b = [list(input().split()) for _ in range(n)]

f = True
for _ in range(4):
    a = r(a)

    f = True
    for al, bl in zip(a, b):
        for av, bv in zip(al, bl):
            if av == "1":
                if bv == "0":
                    f = False
                    break
        if not f:
            break

    if f:
        print("Yes")
        exit()
print("No")
