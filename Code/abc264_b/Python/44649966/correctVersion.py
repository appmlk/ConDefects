r,c=map(int,input().split())
if max(abs(r-7),abs(c-7))%2==0:print("black")
else:print("white")