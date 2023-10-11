# -*- coding: utf-8 -*-
H, W = [int(s) for s in input().split(' ')]
A = []
ans = []
alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
for i in range(H):
    A = [int(s) for s in input().split(' ')]
    preAns = []
    for a in A:
        if 0 == a:
            preAns.append('.')
        else:
            preAns.append(alphabet[a-1])
    ans.append(preAns)
for s in ans:
    print(*s)