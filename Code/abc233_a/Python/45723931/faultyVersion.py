X,Y = map(int, input().split())
if X >= Y:
    print(0)
    exit()
else:
    print((Y-X)//10+1)