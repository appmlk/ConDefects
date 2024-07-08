from collections import defaultdict


class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        way=[]
        while True:
            if self.parents[x] < 0:
                break
            else:
                way.append(x)
                x=self.parents[x]
        for w in way:
            self.parents[w]=x
        return x

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
    

def noshi_base(A):
    base=[]
    for v in A:
        for e in base:
            v=min(v,v^e)
        '''
        vの最上位bitを見る。
        base 1
        減る。baseの最上位bitは違うので変更して良い。
        てか変更しなきゃダメ。
        base 0
        増える。
        '''
        for i in range(len(base)):
            if (v^base[i])<base[i]:
                base[i]^=v
        if v>0:
            base.append(v)
    return base

N,L,R=map(int,input().split())
A=list(map(int,input().split()))
base=noshi_base(A)
base.sort()
ans=[0 for _ in range(R-L+1)]
L-=1
R-=1
for i in range(L,R+1):
    left=1
    for j in range(len(base)):
        if i&left:
            ans[i-L]^=base[j]
        left*=2
print(*ans)