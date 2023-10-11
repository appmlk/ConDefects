a,b,c,d=map(int,input().split())
if a<c:
    print("Takahashi")
if a==c:
    if b<=d:
        print("Takahashi")
    else:
        print("Aoki")
else:
    print("Aoki")