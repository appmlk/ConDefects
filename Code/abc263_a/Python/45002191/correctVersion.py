x=list(map(int,input().split()))
if len(set(x))==2 and x.count(list(set(x))[0])>=2:
    print("Yes")
else:
    print("No")