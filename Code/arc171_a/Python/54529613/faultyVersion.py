#!/usr/bin/env python3

T = int(input())

def solve(n, a, b):
    if a >= n + 1:
        return 0
    
    if a == n:
        if b == 0:
            return 1
        else:
            return 0
    
    c = (b + n - a - 1) // (n - a)

    if a + c <= n:
        return 1
    
    return 0

for _ in range(T):
    n, a, b = map(int, input().split())
    
    if solve(n, a, b):
        print('Yes')

    else:
        print('No')