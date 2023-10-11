r,c = list(map(int,input().split(" ")))
if(max([abs(8-r),abs(8-c)])%2 != 0):
    print("black")
else:
    print("white")