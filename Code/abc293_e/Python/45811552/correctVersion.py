a,x,m = map(int,input().split())
if m == 1:
    exit(print(0))
A = [[a,1]]
for i in range(60):
    t = A[-1]
    A.append([(t[0] ** 2) % m,(t[0]*t[1] + t[1]) % m])
x = bin(x - 1)
x = list(x[2:])
x.reverse()
ans = 1
for i,v in enumerate(x):
    if v == "1":
        ans = (ans * A[i][0] + A[i][1]) % m
print(ans)