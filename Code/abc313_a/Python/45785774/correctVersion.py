N = int(input())
P = list(map(int,input().split()))
m = 0
for i in range(1,N):
    m = max(m, P[i])
print(max(0,m+1-P[0]))