from random import shuffle
def judge(i, j, k):
    # print("i, j, k =", i, j, k)
    if X[i] + X[j] + 1 > X[k]:
        # print(">> Yes")
        return 1
    # print(">> No")
    return 0

def ask(i, j, k):
    if 1 or not DEBUG:
        print("?", i + 1, j + 1, k + 1)
    if DEBUG:
        return judge(i, j, k)
    ret = input()
    if ret == "Yes":
        return 1
    return 0

def comp(i, j):
    return ask(i, x, j)

DEBUG = 0
if DEBUG:
    N = 10
    X = [i for i in range(N)]
    shuffle(X)
    X = [4, 6, 9, 3, 0, 1, 2, 5, 7, 8]
    print("X =", X)
else:
    N = int(input())

if N == 1:
    print("!", 1)
    exit()
x = 0
for i in range(1, N):
    if ask(i, i, x) == 0:
        x = i
if DEBUG:
    print("x =", x)

def check(l, r):
    global Y
    if r == l + 1:
        return
    m = l + r >> 1
    check(l, m)
    check(m, r)
    i = l
    j = m
    C = []
    if DEBUG and (l, r) == (0, 4):
        print("!!!!! l, r =", l, r)
    while i < m and j < r:
        if comp(Y[i], Y[j]):
            if DEBUG and (l, r) == (0, 4):
                print("comp", i, j, "True")
            C.append(Y[j])
            j += 1
        else:
            if DEBUG and (l, r) == (0, 4):
                print("comp", i, j, "False")
            C.append(Y[i])
            i += 1
    if DEBUG and (l, r) == (0, 4):
        print("C =", C)
    while i < m:
        C.append(Y[i])
        i += 1
    while j < r:
        C.append(Y[j])
        j += 1
    for i in range(len(C)):
        Y[l+i] = C[i]
    if DEBUG:
        print("l, r =", l, r)
        print("Y =", Y, Y[l:r])
Y = [x] + [i for i in range(N) if i != x]
check(1, N)
if DEBUG:
    print("X =", X)
    print("Y =", Y)
ans = [0] * N
for i, a in enumerate(Y):
    ans[a] = i + 1
print("!", *ans)