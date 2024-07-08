import sys
input = sys.stdin.readline
n,m = map(int,input().split())
e = [[0]*n for i in range(n)]
for _ in range(m):
    a,b = [int(x)-1 for x in input().split()]
    e[a][b] = 1
for i in range(n):
    e[i][i] = 1

q = int(input())
ST = [[int(x)-1 for x in input().split()] for i in range(q)]

ans = [-1]*q
le = (n+61)//62
bits = []
for i in range(n):
    bi = []
    for j in range(le):
        num = 0
        for x in range(62):
            if j*62+x < n and e[i][j*62+x]:
                num |= 1<<x
        bi.append(num)
    bits.append(bi)


for k in range(n):
    kx,ky = divmod(k,62)
    for i in range(n):
        if bits[i][kx] & 1<<ky == 0:
            continue
        for j in range(le):
            bits[i][j] |= bits[k][j]
    
    for i,(s,t) in enumerate(ST):
        if ans[i] != -1:
            continue
        if s > k or t > k:
            continue
        x,y = divmod(t,62)
        if bits[s][x] & 1<<y:
            ans[i] = k+1

for i in ans:
    print(i)