a,b,c,x=map(int,input().split())
if a>=x:
    print(1)
elif b>=x>a:
    print(c/(b-a+1))
else:
    print(0)