class Unionfind:#根っこ参照で付け直し、デカイ木にちっさい木をつける
    def __init__(self,n):
        self.parent=[i for i in range(n)]#自分の親頂点の番号
        self.num=[1]*n#自分を根とする部分木の頂点の個数
        self.rank=[1]*n#自分を根とする部分木の高さ
    def find(self,i):
        if self.parent[i]==i:
            return i
        else:
            self.parent[i]=self.find(self.parent[i])
            return self.parent[i]
    def union(self,i,j):
        a=self.find(i)
        b=self.find(j)
        if self.rank[a]>self.rank[b]:
            self.parent[b]=a
            
            self.num[a]+=self.num[b]
        elif self.rank[a]<self.rank[b]:
            self.parent[a]=b
            self.num[b]+=self.num[a]
        else:
            if a!=b:
                self.rank[a]+=1
                self.parent[b]=a
                self.num[a]+=self.num[b]
    def numb(self,i):#その頂点を含む連結成分の点の個数
        j=self.find(i)
        return self.num[j]


from collections import defaultdict
#いい問題、教育的
N,M=map(int,input().split())
graph=[[] for _ in range(N)]
graph_after=[[] for _ in range(N)]
edge=[]
for i in range(M):
    u,v=map(int,input().split())
    u-=1
    v-=1
    edge.append((u,v,i))
K=int(input())
e=list(map(int,input().split()))
for i in range(K):
    e[i]-=1
s_e=set(e)
U=Unionfind(N)
for u,v,i in edge:
    if i not in s_e:
        U.union(u,v)
graph_ans=defaultdict(int)
for u,v,i in edge:
    if i in s_e:
        graph_ans[U.find(u)]+=1
        graph_ans[U.find(v)]+=1
cnt=0
for v in graph_ans:
    if graph_ans[v]%2==1:
        cnt+=1
if cnt==0 or cnt==2:
    print('Yes')
else:
    print('No')