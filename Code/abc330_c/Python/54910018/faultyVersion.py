import math
import sys
import random
from collections import deque
from itertools import product
debug = lambda name, value, *args, **kwargs: print( f"{name}: {value}", *args,  file=sys.stderr, **kwargs)

mod = 1000000007
m = random.randint(1,1000000)
iinf = 100000000000000005 #1e18 + 5
 
input = lambda: sys.stdin.readline().strip()
#sys.stdin = open('input.txt', 'r') 
#sys.stdout = open('output.txt', 'w')

dr = [1,-1,0,0]
dc = [0,0,1,-1]
 
def main():
    d = int(input())
    res = iinf*10
    for x in range(0, int(d**.5) +1):
        #check flr, cel
        y = int((d - x*x)**.5)
        for i in range(max(0,y-5), y+5):


            res = min(res, abs(x**2 + y**2 - d))
    print(int(res))






t=1
#t = int(input())
for _ in range(t):
    main()