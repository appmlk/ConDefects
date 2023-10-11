n,d=map(int,input().split())
if (n*(n-1))//2<n*d:
    print("No")
    exit()
print("Yes")
for i in range(1,d+1):
    for j in range(n):
        print(j+1,(j+i)%n+1)
        