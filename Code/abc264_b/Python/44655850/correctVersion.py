R,C = map(int,input().split())

if max(abs(R-8),abs(C-8)) % 2 == 0:
    print("white")
else:
    print("black")