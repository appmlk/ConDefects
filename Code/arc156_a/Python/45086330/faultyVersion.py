import sys
# from collections import deque
input = sys.stdin.readline
# sys.setrecursionlimit(10**6)

############ ---- Input Functions ---- ############
def inp():
    return int(input())


def inlt():
    return list(map(int, input().split()))


def insr():
    s = input()
    return list(s[: len(s) - 1])


def invr():
    return map(int, input().split())

for _ in range(inp()):
    n=inp()
    s=insr()
    if s.count("1")%2 or s=="011" or s=='110':
        print(-1)
        continue
    if s=='0110':
        print(3)
        continue
    if s.count('1')==2 and s.count('11')==1:
        print(2)
        continue
    print(s.count('1')//2)
