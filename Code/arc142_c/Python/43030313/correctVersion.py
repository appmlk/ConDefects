import collections,sys,math,functools,operator,itertools,bisect,heapq,decimal,string,time,random
#sys.setrecursionlimit(10**9)
n = int(input())
#alist = list(map(int,input().split()))
#alist = []
#s = input()
#n,m = map(int,input().split())
#for i in range(n):
#    alist.append(list(map(int,input().split())))
dist = [10000]*(n+1)
ans = 1000000
ans2 = -1000000
for i in range(3,n+1):
    print('?',i,1)
    x = int(input())
    print('?',i,2)
    y = int(input())
    ans = min(ans,x+y)
    dist[i] = x+y
z = min(dist)
q = []
for i in range(3,n+1):
    if z == dist[i]:
        q.append(i)

if ans == 3:
    if len(q) == 2:
        print('?',q[0],q[1])
        if int(input()) == 1:
            ans = 3
        else:
            ans = 1
    else:
        ans = 1
        
print('!',ans)
