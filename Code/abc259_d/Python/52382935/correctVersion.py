class UnionFind:
    def __init__(self, n:int) -> None:
        self.parents = list(range(n))
        self.rank = [0] * n

    def find(self, x:int) -> int:
        if self.parents[x] == x:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
        return self.parents[x] 

    def union(self, x:int, y:int) -> None:
        root_x, root_y = self.find(x), self.find(y)
        if root_x == root_y:
            return
        if self.rank[root_x] < self.rank[root_y]:
            root_x, root_y = root_y, root_x
        self.parents[root_y] = root_x

        if self.rank[root_x] == self.rank[root_y]:
            self.rank[root_x] += 1

    def same(self, x:int, y:int) -> bool:
        return self.find(x) == self.find(y)

def diff(x, y, v, w):
    return (x-v)**2+(y-w)**2

N = int(input())
uf = UnionFind(N+2)
ma = {}
radius = [0 for i in range(N+2)]
sx, sy, tx, ty = map(int, input().split())

for i in range(N):
    x, y, r = map(int, input().split())
    radius[i] = r
    ma[(x, y)] = i

ma[(sx, sy)] = N
ma[(tx, ty)] = N+1

for x, y in ma.keys():
    for v, w in ma.keys():
        numxy = ma[(x, y)]
        numvw = ma[(v, w)]
        if x == v and y == w:
            continue
        d2 = diff(x, y, v, w)
        if (radius[numxy]-radius[numvw])**2 <= d2 <= (radius[numxy]+radius[numvw])**2:
            uf.union(numxy, numvw)

if uf.same(ma[(sx, sy)], ma[(tx, ty)]):
    print("Yes")
else:
    print("No")