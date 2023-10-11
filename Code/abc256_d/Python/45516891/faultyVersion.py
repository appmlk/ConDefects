N = int(input())
num = 2 * 10 ** 5

L = [0] * (num + 2)
for _ in range(N):
    l, r = map(int, input().split())
    L[l] += 1
    L[r] -= -1
for i in range(num):
    L[i+1] += L[i]

flg = False
l = 0
r = 0
for i in range(1,num+1):
    if flg == False:
        if L[i-1] == 0 and L[i] > 0:
            l = i
            flg = True
    else:
        if L[i-1] > 0 and L[i] == 0:
            r = i
            print(l,r)
            flg = False


