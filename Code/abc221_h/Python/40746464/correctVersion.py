n,m = map(int,input().split())
d = [[0] * (n+2) for _ in range(n+2)] 
c = [0] * (n+1)
d[0][0] = c[0] = 1
M = 998244353
for i in range(1,n+1):
    for s in range(i,n+1):
        d[i][s] += d[i][s-i] + c[s-i]
    for s in range(n+1):
        c[s] += d[i][s] - d[i-m][s]
        c[s] %= M
    print(d[i][n] % M)



    
    

