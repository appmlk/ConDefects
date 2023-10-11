R,C=map(int,input().split())
dist=min(R-8,C-8)
if dist%2==1: print("black")
else: print("white")