r,c = list(map(int,input().split(" ")))

if((abs(8-r)+abs(8-c))%2 == 0):
    print("black")
else:
    print("white")