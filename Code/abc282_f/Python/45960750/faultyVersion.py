N = int(input())
log_table = [0]*(N+1)
a = 1
cnt = 0
for i in range(1,N+1):
    if i == a*2:
        a += 2
        cnt += 1
    log_table[i] = cnt
K = log_table[N]
table = [[-1]*(K+1) for _ in range(N)]
M = 0
for i in range(N):
    table[i][0] = M
    M += 1
for k in range(1,K+1):
    for i in range(N):
        if i+(1<<k)-1 >= N:
            continue
        table[i][k] = M
        M += 1
print(M)
for k in range(K+1):
    for i in range(N):
        if table[i][k] == -1:
            continue
        l,r = i,i+(1<<k)-1
        print(l+1,r+1)
Q = int(input())
for i in range(Q):
    l,r = map(int,input().split())
    l,r = l-1,r-1
    k = log_table[r-l+1]
    print(table[l][k]+1,table[r-(1<<k)+1][k]+1)