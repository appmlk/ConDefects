X,Y = map(int, input().split())
if X >= Y:
    print(0)
    exit()
elif((Y-X)%10==0):
    print((Y-X)//10)
else:
    print((Y-X)//10+1)