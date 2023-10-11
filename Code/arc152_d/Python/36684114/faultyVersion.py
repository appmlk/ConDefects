import sys
input = sys.stdin.readline
import pypyjit
pypyjit.set_param('max_unroll_recursion=-1')
#sys.setrecursionlimit(10000000)
from collections import defaultdict,deque
import bisect

inf=10**20

class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())


#bisect.bisect_left(A,x):Aの内x未満のものの個数
def bisectlow(A,x,low=0):
  #Aのうちx以下で最大の数字を返す
  if A[0]>x:
    return low
  else:
    return A[bisect.bisect_right(A,x)-1]
def bisecthigh(A,x,high=inf):
  #Aのうちx以上で最小の数字を返す
  if A[-1]<x:
    return high
  else:
    return A[bisect.bisect_left(A,x)]

#入力
def II():
  return int(input().strip("\n"))
def MI():
  return map(int,input().strip("\n").split())
def LI():
  return list(map(int,input().strip("\n").split()))
def SI():
  return input().strip("\n")

N,K=MI()
if N%2==0:
  print(-1)
  exit()
M=N//2
import math
if math.gcd(N,K)==1:
  print(M)
  for i in range(M):
    print((2*i*K)%N,((2*i+1)*K)%N)
else:
  print(M)
  g=math.gcd(N,K)
  n=N//g
  k=K//g
  S=set()
  for i in range(0,n-1,2):
    print((i*g*k)%N,((i+1)*g*k)%N)
    
    for j in range(1,g):
      print((i*g*k)%N,(i*g*k + j*k)%N)
  x=(N-K)
  for i in range(x+1,x+g-1,2):
    print((i+1-K)%N,i)