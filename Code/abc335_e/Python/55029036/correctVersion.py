from collections import defaultdict

class UnionFind:
    def __init__(self,N,label=None,f=None,weighted=False,rollback=False):
        self.N=N
        self.parents=[None]*self.N
        self.size=[1]*self.N
        self.roots={i for i in range(self.N)}
        self.label=label
        if self.label!=None:
            self.label=[x for x in label]
        self.f=f
        self.weighted=weighted
        if self.weighted:
            self.weight=[0]*self.N
        self.rollback=rollback
        if self.rollback:
            self.operate_list=[]
            self.operate_set=[]

    def Find(self,x):
        stack=[]
        while self.parents[x]!=None:
            stack.append(x)
            x=self.parents[x]
        if not self.rollback:
            if self.weighted:
                w=0
                for y in stack[::-1]:
                    self.parents[y]=x
                    w+=self.weight[y]
                    self.weight[y]=w
            else:
                for y in stack[::-1]:
                    self.parents[y]=x
        return x

    def Union(self,x,y,w=None):
        root_x=self.Find(x)
        root_y=self.Find(y)
        if self.rollback:
            self.operate_list.append([])
            self.operate_set.append([])
        if root_x==root_y:
            if self.weighted:
                if self.weight[y]-self.weight[x]==w:
                    return True
                else:
                    return False
        else:
            if self.size[root_x]<self.size[root_y]:
                x,y=y,x
                root_x,root_y=root_y,root_x
                if self.weighted:
                    w=-w
            if self.rollback:
                self.operate_list[-1].append((self.parents,root_y,self.parents[root_y]))
                self.operate_list[-1].append((self.size,root_x,self.size[root_x]))
                self.operate_set[-1].append(root_y)
                if self.label!=None:
                    self.operate_list[-1]((self.label,root_x,self.label[root_x]))
                if self.weighted:
                    self.operate_list[-1].append((self.weight,root_y,self.weight[root_y]))
            self.parents[root_y]=root_x
            self.size[root_x]+=self.size[root_y]
            self.roots.remove(root_y)
            if self.label!=None:
                self.label[root_x]=self.f(self.label[root_x],self.label[root_y])
            if self.weighted:
                self.weight[root_y]=w+self.weight[x]-self.weight[y]

    def Size(self,x):
        return self.size[self.Find(x)]

    def Same(self,x,y):
        return self.Find(x)==self.Find(y)

    def Label(self,x):
        return self.label[self.Find(x)]

    def Weight(self,x,y):
        root_x=self.Find(x)
        root_y=self.Find(y)
        if root_x!=root_y:
            return None
        return self.weight[y]-self.weight[x]

    def Roots(self):
        return list(self.roots)

    def Linked_Components_Count(self):
        return len(self.roots)

    def Linked_Components(self):
        linked_components=defaultdict(list)
        for x in range(self.N):
            linked_components[self.Find(x)].append(x)
        return linked_components

    def Rollback(self):
        assert self.rollback
        if self.operate_list:
            for lst,x,v in self.operate_list.pop():
                lst[x]=v
            for x in self.operate_set.pop():
                self.roots.add(x)            
            return True
        else:
            return False

    def __str__(self):
        linked_components=defaultdict(list)
        for x in range(self.N):
            linked_components[self.Find(x)].append(x)
        return "\n".join(f"{r}: {linked_components[r]}" for r in sorted(list(linked_components.keys())))

N,M=map(int,input().split())
A=list(map(int,input().split()))
edges=[]
UF=UnionFind(N)
for m in range(M):
    u,v=map(int,input().split())
    u-=1;v-=1
    if A[u]==A[v]:
        UF.Union(u,v)
    else:
        if A[u]>A[v]:
            u,v=v,u
        edges.append((u,v))
idx=[None]*N
lc=UF.Linked_Components()
le=len(lc)
AA=[]
for i,lst in enumerate(lc.values()):
    for x in lst:
        idx[x]=i
    AA.append(A[lst[0]])
graph=[[] for i in range(le)]
for u,v in edges:
    graph[idx[u]].append(idx[v])
inf=1<<30
dp=[-inf]*le
dp[idx[0]]=1
for i in sorted([i for i in range(le)],key=lambda i:AA[i]):
    for j in graph[i]:
        dp[j]=max(dp[j],dp[i]+1)
ans=dp[idx[N-1]]
if ans<0:
    ans=0
print(ans)