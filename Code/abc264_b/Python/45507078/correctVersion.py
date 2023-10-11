R,C=map(int,input().split())
dist=max(abs(R-8),abs(C-8))
if dist%2==1: print("black")
else: print("white")