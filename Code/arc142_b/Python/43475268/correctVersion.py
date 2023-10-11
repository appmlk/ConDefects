n=int(input())
k=list(range(n))
x=[0]*n
x[0::2]=k[n//2:]
x[1::2]=k[:n//2]
for i in x:
    print(*list(range(i*n+1,(i+1)*n+1)))