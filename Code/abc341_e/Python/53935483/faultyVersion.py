from sys import stdin
N,Q = map(int, stdin.readline().split())
S = list(stdin.readline()[:-1])

TF = [0]*(N+10)
BIT = [0]*(N+10)

def get(pos):
    s = 0
    while pos > 0:
        s += BIT[pos]
        pos -= pos & -pos
    return s

def add(pos,val):
    while pos <= N+5:
        BIT[pos] += val
        pos += pos & -pos
    return

for i,v in enumerate(S):
    if i == 0:
        continue
    if S[i] == S[i-1]:
        add(i+1,1)
        TF[i+1] = 1

for i in range(Q):
    que,left,right = map(int, stdin.readline().split())
    if que == 2:
        if left == 1:
            tmp = get(right)
        else:
            tmp = get(right)-get(left-1)

        if tmp == 0:
            print("Yes")
        else:
            print("No")
    else:
        if right != N:
            if TF[right+1] == 1:
                TF[right+1] = 0
                add(right+1,-1)
            else:
                TF[right+1] = 1
                add(right+1,1)
        if left != 1:
            if TF[left] == 1:
                TF[left] = 0
                add(left,-1)
            else:
                TF[left] = 1
                add(left,1)
