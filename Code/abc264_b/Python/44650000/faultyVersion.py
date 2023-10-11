r,c = map(int,input().split())

if (abs(r-8)+abs(c-8))%2==0:
    print("black")
else:
    print("white")