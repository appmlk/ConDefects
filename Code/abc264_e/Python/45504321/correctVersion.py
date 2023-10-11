n,m,e=map(int,input().split())
s=[list(map(int,input().split())) for i in range(e)]
q=int(input())
event=[int(input()) for i in range(q)]
p=set()
for i in range(e):
  s[i][0]-=1
  s[i][1]-=1
for i in range(q):
  event[i]-=1
for k in event:
  p.add(k)
#unionfind#
from collections import defaultdict
class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self,x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]
    def size(self, x):
        return -self.parents[self.find(x)]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return 
        if self.size(x)< self.size(y):
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

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
r=UnionFind(m+n)
for i in range(e):
  if i not in p:
    r.union(s[i][0],s[i][1])
ans=[0]*q
now=0
elect=set()
for i in range(n,m+n):
  x=r.find(i)
  if x not in elect:
    elect.add(x)
for i in range(n):
  if r.find(i) in elect:
    ans[-1]+=1
le=[0]*(n+m)
for i in range(n+m):
  le[r.find(i)]+=1
for i in range(1,q):
  ans[-i-1]=ans[-i]
  j=event[-i]
  x,y=r.find(s[j][0]),r.find(s[j][1])
  if x not in elect:
    if y in elect:
      ans[-i-1]+=le[x]
      elect.add(x)
  if x in elect:
    if y not in elect:
      ans[-i-1]+=le[y]
      elect.add(y)
  r.union(s[j][0],s[j][1])
  if x!=r.find(x):
    le[y]+=le[x]
  elif y!=r.find(y):
    le[x]+=le[y]
for i in range(q):
  print(ans[i])