import collections,sys,math,functools,operator,itertools,bisect,heapq,decimal,string,time,random
#sys.setrecursionlimit(10**9)
#n = int(input())
#alist = list(map(int,input().split()))
#alist = []
#s = input()
x,y,a,b,c = map(int,input().split())
def judge(x,y,a,b,c):
    if (a // x) + (1 if a % x != 0 else 0) + (b // x) + (1 if b % x != 0 else 0) + (c // x) + (1 if c % x != 0 else 0) <= y:
        return True
    y -= (a // x) + (1 if a % x != 0 else 0)
    if y <= 0:
        return False
    z = (b // y) + (1 if b % y != 0 else 0) + (c // y) + (1 if c % y != 0 else 0)
    if z <= x:
        return True
    else:
        return False
ans = False
for i,j in [[x,y],[y,x]]:
    for k in itertools.permutations([a,b,c]):
        ans |= judge(i,j,k[0],k[1],k[2])
print('Yes' if ans else 'No')