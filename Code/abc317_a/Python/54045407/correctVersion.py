n,h,x = map(int,input().split())
l = list(map(int,input().split()))

for i in range(n):
    if h + l[i] >= x:
        print(i+1)
        exit()
