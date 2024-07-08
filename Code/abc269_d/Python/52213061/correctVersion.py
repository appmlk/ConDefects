from collections import defaultdict
class UnionFind():

    def __init__(self, n):
        self.n = n
        self.root = [-1]*n
        self.rank = [0]*n

    def find(self, x):
        if(self.root[x] < 0):
            return x
        else:
            self.root[x] = self.find(self.root[x])
            return self.root[x]

    def unite(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if(x == y):
            return
        elif(self.rank[x] > self.rank[y]):
            self.root[x] += self.root[y]
            self.root[y] = x
        else:
            self.root[y] += self.root[x]
            self.root[x] = y
            if(self.rank[x] == self.rank[y]):
                self.rank[y] += 1

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def size(self, x):
        return -self.root[self.find(x)]

    def roots(self):
        return [i for i, x in enumerate(self.root) if x < 0]

    def group_size(self):
        return len(self.roots())

    def group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members
        
N=int(input())
uf=UnionFind(2003*2003)
black=[False]*2003*2003
for i in range(N):
  X,Y=map(int,input().split())
  X+=1001
  Y+=1001
  
  black[2002*X+Y]=True
  if black[2002*(X-1)+Y-1]:
    uf.unite(2002*X+Y,2002*(X-1)+Y-1)
  if black[2002*(X-1)+Y]:
    uf.unite(2002*X+Y,2002*(X-1)+Y)
  if black[2002*(X)+Y-1]:
    uf.unite(2002*X+Y,2002*(X)+Y-1)
  if black[2002*(X+1)+Y+1]:
    uf.unite(2002*X+Y,2002*(X+1)+Y+1)
  if black[2002*(X+1)+Y]:
    uf.unite(2002*X+Y,2002*(X+1)+Y)
  if black[2002*(X)+Y+1]:
    uf.unite(2002*X+Y,2002*(X)+Y+1)
print(uf.group_size()-(2003*2003-sum(black)))
  