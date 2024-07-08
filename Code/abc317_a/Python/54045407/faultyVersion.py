n,h,x = map(int,input().split())
l = list(map(int,input().split()))

for i in range(n):
    if x >= h + l[i]:
        print(i+1)
        exit()
