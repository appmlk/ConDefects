n,d=map(int,input().split())
data=list(map(int,input().split()))
for i in range(n-1):
    if data[i+1]-data[i]<=d:
        print(data[i])
        quit()
print(-1)