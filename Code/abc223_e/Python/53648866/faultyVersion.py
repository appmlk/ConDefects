from itertools import permutations

X, Y, A, B, C = map(int, input().split())

def func1(x, y):
    cnt = 0
    cnt += (A+x-1)//x
    cnt += (B+x-1)//x
    cnt += (C+x-1)//x
    return cnt <= y

if func1(X, Y) or func1(Y, X):
    exit(print("Yes"))

def func2(a, b, c, x, y):
    y -= (a+x-1)//x
    if y <= 0:
        return False
    x, y = y, x
    cnt = 0
    cnt += (b+x-1)//x
    cnt += (c+x-1)//x
    return cnt <= x

for perm1 in permutations([X, Y]):
    x, y = perm1
    for perm2 in permutations([A, B, C]):
        a, b, c = perm2
        if func2(a, b, c, x, y):
            exit(print("Yes"))

print("No")