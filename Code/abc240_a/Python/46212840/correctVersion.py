a,b = map(int,input().split())
ok = [(i,i+1) for i in range(1,10)]
ok.append((1,10))
if (a,b) in ok:
    print("Yes")
else:
    print("No")