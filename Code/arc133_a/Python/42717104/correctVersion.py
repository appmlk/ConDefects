import sys
input = sys.stdin.buffer.readline
# def input(): return sys.stdin.readline().rstrip()
# sys.setrecursionlimit(10 ** 7)

N = int(input())
A = list(map(int, (input().split())))

def f(x):
    a = []
    for i in range(N):
        if A[i]!=x:
            a.append(A[i])
    return a

x = A[-1]
for i in range(N-1):
    if A[i]>A[i+1]:
        x = A[i]
        break

a = f(x)
print(*a)
