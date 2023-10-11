import math
x,y = map(int,input().split())
print(math.ceil(max((y-x),0)/10))