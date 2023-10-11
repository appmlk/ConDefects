import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


A, X, M = map(int, readline().split())

def f(X):
    if X == 1:
        return 1
    num = f(X // 2)
    ret = num + pow(A, X // 2, M) * num
    ret %= M
    if X % 2:
        ret += pow(A, X - 1, M)
        ret %= M
    return ret

print(f(X))
