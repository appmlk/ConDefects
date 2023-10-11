n=int(input())
INDEX=-1
now=float('inf')
ans=[]
for i in range(n):
    s,a=input().split()
    a=int(a)
    ans.append([s,a])
    if now>a:
        now=a
        INDEX=i
for i in range(n):
    print(ans[(i+INDEX)%n][0])