import bisect
import copy
import decimal
import fractions
import heapq
import itertools
import math
import random
import sys
import time
from collections import Counter,deque,defaultdict
from functools import lru_cache,reduce
from heapq import heappush,heappop,heapify,heappushpop,_heappop_max,_heapify_max
def _heappush_max(heap,item):
    heap.append(item)
    heapq._siftdown_max(heap, 0, len(heap)-1)
def _heappushpop_max(heap, item):
    if heap and item < heap[0]:
        item, heap[0] = heap[0], item
        heapq._siftup_max(heap, 0)
    return item
from math import gcd as GCD
read=sys.stdin.read
readline=sys.stdin.readline
readlines=sys.stdin.readlines
import heapq
import random
from collections import defaultdict,deque

class Graph:
    def __init__(self,V,edges=False,graph=False,directed=False,weighted=False,inf=float("inf")):
        self.V=V
        self.directed=directed
        self.weighted=weighted
        self.inf=inf
        if graph:
            self.graph=graph
            self.edges=[]
            for i in range(self.V):
                if self.weighted:
                    for j,d in self.graph[i]:
                        if self.directed or not self.directed and i<=j:
                            self.edges.append((i,j,d))
                else:
                    for j in self.graph[i]:
                        if self.directed or not self.directed and i<=j:
                            self.edges.append((i,j))
        else:
            self.edges=edges
            self.graph=[[] for i in range(self.V)]
            if weighted:
                for i,j,d in self.edges:
                    self.graph[i].append((j,d))
                    if not self.directed:
                        self.graph[j].append((i,d))
            else:
                for i,j in self.edges:
                    self.graph[i].append(j)
                    if not self.directed:
                        self.graph[j].append(i)

    def SIV_DFS(self,s,bipartite_graph=False,cycle_detection=False,directed_acyclic=False,euler_tour=False,linked_components=False,lowlink=False,parents=False,postorder=False,preorder=False,subtree_size=False,topological_sort=False,unweighted_dist=False,weighted_dist=False):
        seen=[False]*self.V
        finished=[False]*self.V
        if directed_acyclic or cycle_detection or topological_sort:
            dag=True
        if euler_tour:
            et=[]
        if linked_components:
            lc=[]
        if lowlink:
            order=[None]*self.V
            ll=[None]*self.V
            idx=0
        if parents or cycle_detection or lowlink or subtree_size:
            ps=[None]*self.V
        if postorder or topological_sort:
            post=[]
        if preorder:
            pre=[]
        if subtree_size:
            ss=[1]*self.V
        if unweighted_dist or bipartite_graph:
            uwd=[self.inf]*self.V
            uwd[s]=0
        if weighted_dist:
            wd=[self.inf]*self.V
            wd[s]=0
        stack=[(s,0)] if self.weighted else [s]
        while stack:
            if self.weighted:
                x,d=stack.pop()
            else:
                x=stack.pop()
            if not seen[x]:
                seen[x]=True
                stack.append((x,d) if self.weighted else x)
                if euler_tour:
                    et.append(x)
                if linked_components:
                    lc.append(x)
                if lowlink:
                    order[x]=idx
                    ll[x]=idx
                    idx+=1
                if preorder:
                    pre.append(x)
                for y in self.graph[x]:
                    if self.weighted:
                        y,d=y
                    if not seen[y]:
                        stack.append((y,d) if self.weighted else y)
                        if parents or cycle_detection or lowlink or subtree_size:
                            ps[y]=x
                        if unweighted_dist or bipartite_graph:
                            uwd[y]=uwd[x]+1
                        if weighted_dist:
                            wd[y]=wd[x]+d
                    elif not finished[y]:
                        if (directed_acyclic or cycle_detection or topological_sort) and dag:
                            dag=False
                            if cycle_detection:
                                cd=(y,x)
            elif not finished[x]:
                finished[x]=True
                if euler_tour:
                    et.append(~x)
                if lowlink:
                    bl=True
                    for y in self.graph[x]:
                        if self.weighted:
                            y,d=y
                        if ps[x]==y and bl:
                            bl=False
                            continue
                        ll[x]=min(ll[x],order[y])
                    if x!=s:
                        ll[ps[x]]=min(ll[ps[x]],ll[x])
                if postorder or topological_sort:
                    post.append(x)
                if subtree_size:
                    for y in self.graph[x]:
                        if self.weighted:
                            y,d=y
                        if y==ps[x]:
                            continue
                        ss[x]+=ss[y]
        if bipartite_graph:
            bg=[[],[]]
            for tpl in self.edges:
                x,y=tpl[:2] if self.weighted else tpl
                if uwd[x]==self.inf or uwd[y]==self.inf:
                    continue
                if not uwd[x]%2^uwd[y]%2:
                    bg=False
                    break
            else:
                for x in range(self.V):
                    if uwd[x]==self.inf:
                        continue
                    bg[uwd[x]%2].append(x)
        retu=()
        if bipartite_graph:
            retu+=(bg,)
        if cycle_detection:
            if dag:
                cd=[]
            else:
                y,x=cd
                cd=self.Route_Restoration(y,x,ps)
            retu+=(cd,)
        if directed_acyclic:
            retu+=(dag,)
        if euler_tour:
            retu+=(et,)
        if linked_components:
            retu+=(lc,)
        if lowlink:
            retu=(ll,)
        if parents:
            retu+=(ps,)
        if postorder:
            retu+=(post,)
        if preorder:
            retu+=(pre,)
        if subtree_size:
            retu+=(ss,)
        if topological_sort:
            if dag:
                tp_sort=post[::-1]
            else:
                tp_sort=[]
            retu+=(tp_sort,)
        if unweighted_dist:
            retu+=(uwd,)
        if weighted_dist:
            retu+=(wd,)
        if len(retu)==1:
            retu=retu[0]
        return retu

    def MIV_DFS(self,initial_vertices=None,bipartite_graph=False,cycle_detection=False,directed_acyclic=False,euler_tour=False,linked_components=False,lowlink=False,parents=False,postorder=False,preorder=False,subtree_size=False,topological_sort=False,unweighted_dist=False,weighted_dist=False):
        if initial_vertices==None:
            initial_vertices=[s for s in range(self.V)]
        seen=[False]*self.V
        finished=[False]*self.V
        if bipartite_graph:
            bg=[None]*self.V
            cnt=-1
        if directed_acyclic or cycle_detection or topological_sort:
            dag=True
        if euler_tour:
            et=[]
        if linked_components:
            lc=[]
        if lowlink:
            order=[None]*self.V
            ll=[None]*self.V
            idx=0
        if parents or cycle_detection or lowlink or subtree_size:
            ps=[None]*self.V
        if postorder or topological_sort:
            post=[]
        if preorder:
            pre=[]
        if subtree_size:
            ss=[1]*self.V
        if bipartite_graph or unweighted_dist:
            uwd=[self.inf]*self.V
        if weighted_dist:
            wd=[self.inf]*self.V
        for s in initial_vertices:
            if seen[s]:
                continue
            if bipartite_graph:
                cnt+=1
                bg[s]=(cnt,0)
            if linked_components:
                lc.append([])
            if bipartite_graph or unweighted_dist:
                uwd[s]=0
            if weighted_dist:
                wd[s]=0
            stack=[(s,0)] if self.weighted else [s]
            while stack:
                if self.weighted:
                    x,d=stack.pop()
                else:
                    x=stack.pop()
                if not seen[x]:
                    seen[x]=True
                    stack.append((x,d) if self.weighted else x)
                    if euler_tour:
                        et.append(x)
                    if linked_components:
                        lc[-1].append(x)
                    if lowlink:
                        order[x]=idx
                        ll[x]=idx
                        idx+=1
                    if preorder:
                        pre.append(x)
                    for y in self.graph[x]:
                        if self.weighted:
                            y,d=y
                        if not seen[y]:
                            stack.append((y,d) if self.weighted else y)
                            if bipartite_graph:
                                bg[y]=(cnt,bg[x][1]^1)
                            if parents or cycle_detection or lowlink or subtree_size:
                                ps[y]=x
                            if unweighted_dist or bipartite_graph:
                                uwd[y]=uwd[x]+1
                            if weighted_dist:
                                wd[y]=wd[x]+d
                        elif not finished[y]:
                            if directed_acyclic and dag:
                                dag=False
                                if cycle_detection:
                                    cd=(y,x)
                elif not finished[x]:
                    finished[x]=True
                    if euler_tour:
                        et.append(~x)
                    if lowlink:
                        bl=True
                        for y in self.graph[x]:
                            if self.weighted:
                                y,d=y
                            if ps[x]==y and bl:
                                bl=False
                                continue
                            ll[x]=min(ll[x],order[y])
                        if x!=s:
                            ll[ps[x]]=min(ll[ps[x]],ll[x])
                    if postorder or topological_sort:
                        post.append(x)
                    if subtree_size:
                        for y in self.graph[x]:
                            if self.weighted:
                                y,d=y
                            if y==ps[x]:
                                continue
                            ss[x]+=ss[y]
        if bipartite_graph:
            bg_=bg
            bg=[[[],[]] for i in range(cnt+1)]
            for tpl in self.edges:
                i,j=tpl[:2] if self.weighted else tpl
                if not bg_[i][1]^bg_[j][1]:
                    bg[bg_[i][0]]=False
            for x in range(self.V):
                if bg[bg_[x][0]]:
                    bg[bg_[x][0]][bg_[x][1]].append(x)
        retu=()
        if bipartite_graph:
            retu+=(bg,)
        if cycle_detection:
            if dag:
                cd=[]
            else:
                y,x=cd
                cd=self.Route_Restoration(y,x,ps)
            retu+=(cd,)
        if directed_acyclic:
            retu+=(dag,)
        if euler_tour:
            retu+=(et,)
        if linked_components:
            retu+=(lc,)
        if lowlink:
            retu=(ll,)
        if parents:
            retu+=(ps,)
        if postorder:
            retu+=(post,)
        if preorder:
            retu+=(pre,)
        if subtree_size:
            retu+=(ss,)
        if topological_sort:
            if dag:
                tp_sort=post[::-1]
            else:
                tp_sort=[]
            retu+=(tp_sort,)
        if unweighted_dist:
            retu+=(uwd,)
        if weighted_dist:
            retu+=(wd,)
        if len(retu)==1:
            retu=retu[0]
        return retu

    def SIV_BFS(self,s,bfs_tour=False,bipartite_graph=False,linked_components=False,parents=False,unweighted_dist=False,weighted_dist=False):
        seen=[False]*self.V
        seen[s]=True
        if bfs_tour:
            bt=[s]
        if linked_components:
            lc=[s]
        if parents:
            ps=[None]*self.V
        if unweighted_dist or bipartite_graph:
            uwd=[self.inf]*self.V
            uwd[s]=0
        if weighted_dist:
            wd=[self.inf]*self.V
            wd[s]=0
        queue=deque([s])
        while queue:
            x=queue.popleft()
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                if not seen[y]:
                    seen[y]=True
                    queue.append(y)
                    if bfs_tour:
                        bt.append(y)
                    if linked_components:
                        lc.append(y)
                    if parents:
                        ps[y]=x
                    if unweighted_dist or bipartite_graph:
                        uwd[y]=uwd[x]+1
                    if weighted_dist:
                        wd[y]=wd[x]+d
        if bipartite_graph:
            bg=[[],[]]
            for tpl in self.edges:
                i,j=tpl[:2] if self.weighted else tpl
                if uwd[i]==self.inf or uwd[j]==self.inf:
                    continue
                if not uwd[i]%2^uwd[j]%2:
                    bg=False
                    break
            else:
                for x in range(self.V):
                    if uwd[x]==self.inf:
                        continue
                    bg[uwd[x]%2].append(x)
        retu=()
        if bfs_tour:
            retu+=(bt,)
        if bipartite_graph:
            retu+=(bg,)
        if linked_components:
            retu+=(lc,)
        if parents:
            retu+=(ps,)
        if unweighted_dist:
            retu+=(uwd,)
        if weighted_dist:
            retu+=(wd,)
        if len(retu)==1:
            retu=retu[0]
        return retu

    def MIV_BFS(self,initial_vertices=None,bipartite_graph=False,linked_components=False,parents=False,unweighted_dist=False,weighted_dist=False):
        if initial_vertices==None:
            initial_vertices=[i for i in range(self.V)]
        seen=[False]*self.V
        if bipartite_graph:
            bg=[None]*self.V
            cnt=-1
        if linked_components:
            lc=[]
        if parents:
            ps=[None]*self.V
        if unweighted_dist:
            uwd=[self.inf]*self.V
        if weighted_dist:
            wd=[self.inf]*self.V
        for s in initial_vertices:
            if seen[s]:
                continue
            seen[s]=True
            if bipartite_graph:
                cnt+=1
                bg[s]=(cnt,0)
            if linked_components:
                lc.append([s])
            if unweighted_dist:
                uwd[s]=0
            if weighted_dist:
                wd[s]=0
            queue=deque([s])
            while queue:
                x=queue.popleft()
                for y in self.graph[x]:
                    if self.weighted:
                        y,d=y
                    if not seen[y]:
                        seen[y]=True
                        queue.append(y)
                        if bipartite_graph:
                            bg[y]=(cnt,bg[x][1]^1)
                        if linked_components:
                            lc[-1].append(y)
                        if parents:
                            ps[y]=x
                        if unweighted_dist:
                            uwd[y]=uwd[x]+1
                        if weighted_dist:
                            wd[y]=wd[x]+d
        if bipartite_graph:
            bg_=bg
            bg=[[[],[]] for i in range(cnt+1)]
            for tpl in self.edges:
                i,j=tpl[:2] if self.weighted else tpl
                if not bg_[i][1]^bg_[j][1]:
                    bg[bg_[i][0]]=False
            for x in range(self.V):
                if bg[bg_[x][0]]:
                    bg[bg_[x][0]][bg_[x][1]].append(x)
        retu=()
        if bipartite_graph:
            retu+=(bg,)
        if linked_components:
            retu+=(lc,)
        if parents:
            retu=(ps,)
        if unweighted_dist:
            retu+=(uwd,)
        if weighted_dist:
            retu+=(wd,)
        if len(retu)==1:
            retu=retu[0]
        return retu

    def Tree_Diameter(self,weighted=False):
        def Farthest_Point(u):
            dist=self.SIV_BFS(u,weighted_dist=True) if weighted else self.SIV_BFS(u,unweighted_dist=True)
            fp=0
            for i in range(self.V):
                if dist[fp]<dist[i]:
                    fp=i
            return fp,dist[fp]
        u,d=Farthest_Point(0)
        v,d=Farthest_Point(u)
        return u,v,d

    def SCC(self):
        reverse_graph=[[] for i in range(self.V)]
        for tpl in self.edges:
            u,v=tpl[:2] if self.weighted else tpl
            reverse_graph[v].append(u)
        postorder=self.MIV_DFS(postorder=True)
        scc_points=[]
        seen=[False]*self.V
        for s in postorder[::-1]:
            if seen[s]:
                continue
            queue=deque([s])
            seen[s]=True
            lst=[]
            while queue:
                x=queue.popleft()
                lst.append(x)
                for y in reverse_graph[x]:
                    if not seen[y]:
                        seen[y]=True
                        queue.append(y)
            scc_points.append(lst)
        l=len(scc_points)
        idx=[None]*self.V
        for i in range(l):
            for x in scc_points[i]:
                idx[x]=i
        scc_edges=set()
        for tpl in self.edges:
            u,v=tpl[:2] if self.weighted else tpl
            if idx[u]!=idx[v]:
                scc_edges.add((idx[u],idx[v]))
        scc_edges=list(scc_edges)
        return scc_points,scc_edges

    def Build_LCA(self,s,segment_tree=False):
        self.lca_segment_tree=segment_tree
        if self.lca_segment_tree:
            self.lca_euler_tour,self.lca_parents,depth=self.SIV_DFS(s,euler_tour=True,parents=True,unweighted_dist=True)
            self.lca_dfs_in_index=[None]*self.V
            self.lca_dfs_out_index=[None]*self.V
            for i,x in enumerate(self.lca_euler_tour):
                if x>=0:
                    self.lca_dfs_in_index[x]=i
                else:
                    self.lca_dfs_out_index[~x]=i
            self.ST=Segment_Tree(2*self.V,lambda x,y:min(x,y),self.V)
            lst=[None]*(2*self.V)
            for i in range(2*self.V-1):
                if self.lca_euler_tour[i]>=0:
                    lst[i]=depth[self.lca_euler_tour[i]]
                else:
                    lst[i]=depth[self.lca_parents[~self.lca_euler_tour[i]]]
            lst[2*self.V-1]=-1
            self.ST.Build(lst)
        else:
            self.lca_parents,self.lca_depth=self.SIV_DFS(s,parents=True,unweighted_dist=True)
            self.lca_parents[s]=s
            self.lca_PD=Path_Doubling(self.V,self.lca_parents)
            self.lca_PD.Build_Next(self.V)

    def LCA(self,a,b):
        if self.lca_segment_tree:
            m=min(self.lca_dfs_in_index[a],self.lca_dfs_in_index[b])
            M=max(self.lca_dfs_in_index[a],self.lca_dfs_in_index[b])
            x=self.lca_euler_tour[self.ST.Fold_Index(m,M+1)]
            if x>=0:
                lca=x
            else:
                lca=self.lca_parents[~x]
        else:
            if self.lca_depth[a]>self.lca_depth[b]:
                a,b=b,a
            b=self.lca_PD.Permutation_Doubling(b,self.lca_depth[b]-self.lca_depth[a])
            if a!=b:
                for k in range(self.lca_PD.k-1,-1,-1):
                    if self.lca_PD.permutation_doubling[a][k]!=self.lca_PD.permutation_doubling[b][k]:
                        a,b=self.lca_PD.permutation_doubling[a][k],self.lca_PD.permutation_doubling[b][k]
                a,b=self.lca_PD.permutation_doubling[a][0],self.lca_PD.permutation_doubling[b][0]
            lca=a
        return lca

    def Build_HLD(self,s):
        self.hld_parents,size,self.hld_depth=self.SIV_DFS(s,parents=True,subtree_size=True,unweighted_dist=True)
        stack=[s]
        self.hld_tour=[]
        self.hld_path_parents=[None]*self.V
        self.hld_path_parents[s]=s
        while stack:
            x=stack.pop()
            self.hld_tour.append(x)
            max_size=0
            max_size_y=None
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                if y==self.hld_parents[x]:
                    continue
                if max_size<size[y]:
                    max_size=size[y]
                    max_size_y=y
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                if y==self.hld_parents[x]:
                    continue
                if y!=max_size_y:
                    stack.append(y)
                    self.hld_path_parents[y]=y
            if max_size_y!=None:
                stack.append(max_size_y)
                self.hld_path_parents[max_size_y]=self.hld_path_parents[x]
        self.hld_tour_idx=[None]*self.V
        for i in range(self.V):
            self.hld_tour_idx[self.hld_tour[i]]=i

    def HLD(self,a,b,edge=False):
        L,R=[],[]
        while self.hld_path_parents[a]!=self.hld_path_parents[b]:
            if self.hld_depth[self.hld_path_parents[a]]<self.hld_depth[self.hld_path_parents[b]]:
                R.append((self.hld_tour_idx[self.hld_path_parents[b]],self.hld_tour_idx[b]+1))
                b=self.hld_parents[self.hld_path_parents[b]]
            else:
                L.append((self.hld_tour_idx[a]+1,self.hld_tour_idx[self.hld_path_parents[a]]))
                a=self.hld_parents[self.hld_path_parents[a]]
        if edge:
            if self.hld_depth[a]!=self.hld_depth[b]:
                retu=L+[(self.hld_tour_idx[a]+1,self.hld_tour_idx[b]+1)]+R[::-1]
            else:
                retu=L+R[::-1]
        else:
            if self.hld_depth[a]<self.hld_depth[b]:
                retu=L+[(self.hld_tour_idx[a],self.hld_tour_idx[b]+1)]+R[::-1]
            else:
                retu=L+[(self.hld_tour_idx[a]+1,self.hld_tour_idx[b])]+R[::-1]
        return retu

    def Build_Hash(self,s,random_number=False,mod=(1<<61)-1,rerooting=False):
        self.lower_hash=[None]*self.V
        if random_number:
            self.hash_random_number=random_number
        else:
            self.hash_random_number=[random.randint(1,10**10) for i in range(self.V)]
        self.hash_mod=mod
        parents,postorder,preorder=self.SIV_DFS(s,parents=True,postorder=True,preorder=True)
        for x in postorder:
            level=0
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                if y==parents[x]:
                    continue
                h,l=self.lower_hash[y]
                level=max(level,l+1)
            ha=1
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                if y==parents[x]:
                    continue
                h,l=self.lower_hash[y]
                ha*=h+self.hash_random_number[l]
                ha%=self.hash_mod
            self.lower_hash[x]=(ha,level)
        if rerooting:
            self.upper_hash=[None]*self.V
            self.upper_hash[s]=(1,-1)
            for x in preorder:
                children=[y for y,d in self.graph[x] if y!=parents[x]] if self.weighted else [y for y in self.graph[x] if y!=parents[x]]
                if children:
                    l=len(children)
                    l_lst,r_lst=[None]*(l+1),[None]*(l+1)
                    l_lst[0],r_lst[l]=(1,-1),(1,-1)
                    for i in range(1,l+1):
                        h0,l0=l_lst[i-1]
                        h1,l1=self.lower_hash[children[i-1]]
                        l_lst[i]=(h0*(h1+self.hash_random_number[l1])%self.hash_mod,max(l0,l1))
                    for i in range(l-1,-1,-1):
                        h0,l0=r_lst[i+1]
                        h1,l1=self.lower_hash[children[i]]
                        r_lst[i]=(h0*(h1+self.hash_random_number[l1])%self.hash_mod,max(l0,l1))
                    for i in range(l):
                        if x==s:
                            ha,level=1,0
                        else:
                            ha,level=self.upper_hash[x]
                        h0,l0=l_lst[i]
                        h1,l1=r_lst[i+1]
                        ha*=h0*h1
                        level=max(level,l0+1,l1+1)
                        ha+=self.hash_random_number[level]
                        ha%=self.hash_mod
                        level+=1
                        self.upper_hash[children[i]]=(ha,level)
        return 

    def Hash(self,root,subtree=False):
        if subtree:
            ha,level=self.lower_hash[root]
            ha+=self.hash_random_number[level]
            ha%=self.hash_mod
        else:
            h0,l0=self.lower_hash[root]
            h1,l1=self.upper_hash[root]
            ha=(h0*h1+self.hash_random_number[max(l0,l1)])%self.hash_mod
            level=max(l0,l1)
        return ha,level

    def Build_Rerooting(self,s,f_transition,f_merge):
        self.rerooting_s=s
        self.rerooting_f_transition=f_transition
        self.rerooting_f_merge=f_merge
        parents,postorder,preorder=self.SIV_DFS(s,parents=True,postorder=True,preorder=True)
        self.rerooting_lower_dp=[None]*self.V
        for x in postorder:
            self.rerooting_lower_dp[x]=self.rerooting_f_merge([self.rerooting_f_transition(self.rerooting_lower_dp[y]) for y in G.graph[x] if y!=parents[x]])
        self.rerooting_upper_dp=[None]*self.V
        for x in preorder:
            children=[y for y in self.graph[x] if y!=parents[x]]
            left_accumule_f=[None]*(len(children)+1)
            right_accumule_f=[None]*(len(children)+1)
            left_accumule_f[0]=self.rerooting_f_merge([])
            for i in range(1,len(children)+1):
                left_accumule_f[i]=self.rerooting_f_merge([left_accumule_f[i-1],self.rerooting_f_transition(self.rerooting_lower_dp[children[i-1]])])
            right_accumule_f[len(children)]=self.rerooting_f_merge([])
            for i in range(len(children)-1,-1,-1):
                right_accumule_f[i]=self.rerooting_f_merge([right_accumule_f[i+1],self.rerooting_f_transition(self.rerooting_lower_dp[children[i]])])
            for i in range(len(children)):
                if parents[x]!=None:
                    self.rerooting_upper_dp[children[i]]=self.rerooting_f_merge([left_accumule_f[i],right_accumule_f[i+1],self.rerooting_f_transition(self.rerooting_upper_dp[x])])
                else:
                    self.rerooting_upper_dp[children[i]]=self.rerooting_f_merge([left_accumule_f[i],right_accumule_f[i+1]])
 
    def Rerooting(self,x):
        if x==self.rerooting_s:
            retu=self.rerooting_lower_dp[x]
        else:
            retu=self.rerooting_f_merge([self.rerooting_lower_dp[x],self.rerooting_f_transition(self.rerooting_upper_dp[x])])
        return retu

    def Centroid(self,root=0):
        x=root
        parents,size=self.SIV_DFS(x,parents=True,subtree_size=True)
        while True:
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                if y==parents[x]:
                    continue
                if size[y]*2>size[root]:
                    x=y
                    break
            else:
                for y in self.graph[x]:
                    if self.weighted:
                        y,d=y
                    if y==parents[x]:
                        continue
                    if size[root]<=2*size[y]:
                        return x,y
                return x,None

    def Centroid_Decomposition(self,edge=False,linked_point=False,point=False,tree=False):
        if edge:
            cd_edges_lst=[None]*self.V
        if linked_point:
            cd_linked_points=[None]*self.V
        if point:
            cd_points_lst=[None]*self.V
        if tree:
            cd_tree=[]*self.V
        edges=self.edges
        points=[i for i in range(self.V)]
        prev_centroid=None
        stack=[(edges,points,None,prev_centroid)] if linked_point else [(edges,points,prev_centroid)]
        while stack:
            if linked_point:
                edges,points,lp,prev_centroid=stack.pop()
            else:
                edges,points,prev_centroid=stack.pop()
            if len(points)==1:
                centroid=points[0]
                if edge:
                    cd_edges_lst[centroid]=[]
                if linked_point:
                    cd_linked_points[centroid]=lp
                if point:
                    cd_points_lst[centroid]=[centroid]
                if tree and prev_centroid!=None:
                    cd_tree.append((prev_centroid,centroid))
                continue
            G=Graph(len(points),edges=edges,weighted=self.weighted)
            centroid,_=G.Centroid()
            if tree and prev_centroid!=None:
                cd_tree.append((prev_centroid,points[centroid]))
            parents,tour=G.SIV_DFS(centroid,parents=True,preorder=True)
            dp=[None]*len(points)
            edges_lst=[]
            points_lst=[]
            if linked_point:
                linked_points=[]
            for i,x in enumerate(G.graph[centroid]):
                if G.weighted:
                    x,d=x
                dp[x]=(i,0)
                edges_lst.append([])
                points_lst.append([points[x]])
                if linked_point:
                    linked_points.append(points[x])
            for x in tour[1:]:
                for y in G.graph[x]:
                    if G.weighted:
                        y,d=y
                    if y==parents[x]:
                        continue
                    i,j=dp[x]
                    jj=len(points_lst[i])
                    edges_lst[i].append((j,jj,d) if G.weighted else (j,jj))
                    points_lst[i].append(points[y])
                    dp[y]=(i,jj)
            centroid=points[centroid]
            if edge:
                cd_edges_lst[centroid]=edges
            if linked_point:
                cd_linked_points[centroid]=lp
            if point:
                cd_points_lst[centroid]=points
            if linked_point:
                for edges,points,lp in zip(edges_lst,points_lst,linked_points):
                    stack.append((edges,points,lp,centroid))
            else:
                for edges,points in zip(edges_lst,points_lst):
                    stack.append((edges,points,centroid))
        retu=()
        if edge:
            retu+=(cd_edges_lst,)
        if linked_point:
            retu+=(cd_linked_points,)
        if point:
            retu+=(cd_points_lst,)
        if tree:
            retu+=(cd_tree,)
        if len(retu)==1:
            retu=retu[0]
        return retu

    def Bridges(self):
        lowlink,preorder=self.MIV_DFS(lowlink=True,preorder=True)
        order=[None]*self.V
        for x in range(self.V):
            order[preorder[x]]=x
        bridges=[]
        for e in self.edges:
            if self.weighted:
                x,y,d=e
            else:
                x,y=e
            if order[x]<lowlink[y] or order[y]<lowlink[x]:
                bridges.append((x,y))
        return bridges

    def Articulation_Points(self):
        lowlink,parents,preorder=self.MIV_DFS(lowlink=True,parents=True,preorder=True)
        order=[None]*self.V
        for x in range(self.V):
            order[preorder[x]]=x
        articulation_points=[]
        for x in range(self.V):
            if parents[x]==None:
                if len({y for y in self.graph[x] if parents[y]==x})>=2:
                    articulation_points.append(x)
            else:
                for y in self.graph[x]:
                    if parents[y]!=x:
                        continue
                    if order[x]<=lowlink[y]:
                        articulation_points.append(x)
                        break
        return articulation_points

    def TECCD(self):
        lowlink,preorder=self.MIV_DFS(lowlink=True,preorder=True)
        order=[None]*self.V
        for x in range(self.V):
            order[preorder[x]]=x
        edges=[]
        for e in self.edges:
            if self.weighted:
                x,y,d=e
            else:
                x,y=e
            if order[x]>=lowlink[y] and order[y]>=lowlink[x]:
                edges.append((x,y))
        teccd=Graph(self.V,edges=edges).MIV_DFS(linked_components=True)
        return teccd

    def LCD(self):
        lcd_points=self.MIV_DFS(linked_components=True)
        lcd_edges=[[] for i in range(len(lcd_points))]
        idx=[None]*self.V
        for i in range(len(lcd_points)):
            for j in range(len(lcd_points[i])):
                idx[lcd_points[i][j]]=(i,j)
        for tpl in self.edges:
            if self.weighted:
                x,y,d=tpl
            else:
                x,y=tpl
            i,j0=idx[x]
            i,j1=idx[y]
            if self.weighted:
                lcd_edges[i].append((j0,j1,d))
            else:
                lcd_edges[i].append((j0,j1))
        return lcd_points,lcd_edges

    def Dijkstra(self,s,route_restoration=False):
        dist=[self.inf]*self.V
        dist[s]=0
        hq=[(0,s)]
        if route_restoration:
            parents=[None]*self.V
        while hq:
            dx,x=heapq.heappop(hq)
            if dist[x]<dx:
                continue
            for y,dy in self.graph[x]:
                if dist[y]>dx+dy:
                    dist[y]=dx+dy
                    if route_restoration:
                        parents[y]=x
                    heapq.heappush(hq,(dist[y],y))
        if route_restoration:
            return dist,parents
        else:
            return dist

    def Bellman_Ford(self,s,route_restoration=False):
        dist=[self.inf]*self.V
        dist[s]=0
        if route_restoration:
            parents=[None]*self.V
        for _ in range(self.V-1):
            for i,j,d in self.edges:
                if dist[j]>dist[i]+d:
                    dist[j]=dist[i]+d
                    if route_restoration:
                        parents[j]=i
                if not self.directed and dist[i]>dist[j]+d:
                    dist[i]=dist[j]+d
                    if route_restoration:
                        parents[i]=j
        negative_cycle=[]
        for i,j,d in self.edges:
            if dist[j]>dist[i]+d:
                negative_cycle.append(j)
            if not self.directed and dist[i]>dist[j]+d:
                negative_cycle.append(i)
        if negative_cycle:
            is_negative_cycle=[False]*self.V
            for i in negative_cycle:
                if is_negative_cycle[i]:
                    continue
                else:
                    queue=[i]
                    is_negative_cycle[i]=True
                    while queue:
                        x=queue.popleft()
                        for y,d in self.graph[x]:
                            if not is_negative_cycle[y]:
                                queue.append(y)
                                is_negative_cycle[y]=True
                                if route_restoration:
                                    parents[y]=x
            for i in range(self.V):
                if is_negative_cycle[i]:
                    dist[i]=-self.inf
        if route_restoration:
            return dist,parents
        else:
            return dist

    def Warshall_Floyd(self,route_restoration=False):
        dist=[[self.inf]*self.V for i in range(self.V)]
        for i in range(self.V):
            dist[i][i]=0
        if route_restoration:
            parents=[[j for j in range(self.V)] for i in range(self.V)]
        for i,j,d in self.edges:
            if i==j:
                continue
            if dist[i][j]>d:
                dist[i][j]=d
                if route_restoration:
                    parents[i][j]=i
            if not self.directed and dist[j][i]>d:
                dist[j][i]=d
                if route_restoration:
                    parents[j][i]=j
        for k in range(self.V):
            for i in range(self.V):
                for j in range(self.V):
                    if dist[i][j]>dist[i][k]+dist[k][j]:
                        dist[i][j]=dist[i][k]+dist[k][j]
                        if route_restoration:
                            parents[i][j]=parents[k][j]
        for i in range(self.V):
            if dist[i][i]<0:
                for j in range(self.V):
                    if dist[i][j]!=self.inf:
                        dist[i][j]=-self.inf
        if route_restoration:
            for i in range(self.V):
                if dist[i][i]==0:
                    parents[i][i]=None
            return dist,parents
        else:
            return dist

    def BFS_01(self,s,route_restoration=False):
        queue=deque([s])
        seen=[False]*self.V
        dist=[self.inf]*self.V
        dist[s]=0
        if route_restoration:
            parents=[None]*self.V
        while queue:
            x=queue.popleft()
            if seen[x]:
                continue
            seen[x]=False
            for y,d in self.graph[x]:
                if dist[y]>dist[x]+d:
                    dist[y]=dist[x]+d
                    if route_restoration:
                        parents[y]=x
                    if d:
                        queue.append(y)
                    else:
                        queue.appendleft(y)
        if route_restoration:
            return dist,parents
        else:
            return dist

    def Distance_Frequency(self):
        mod=206158430209
        cnt=[0]*self.V
        cd_edges,cd_points,cd_tree=self.Centroid_Decomposition(edge=True,point=True,tree=True)
        CD=Graph(self.V,edges=cd_tree)
        parents,tour=CD.SIV_DFS(cd_tree[0][0],parents=True,postorder=True)
        for x in tour:
            C=[0]*(len(cd_points[x])+1)
            for y in CD.graph[x]:
                if y==parents[x]:
                    continue
                depth=Graph(len(cd_points[y]),edges=cd_edges[y],weighted=self.weighted).SIV_DFS(0,unweighted_dist=True)
                CC=[0]*(max(depth)+2)
                for d in depth:
                    CC[d+1]+=1
                    cnt[d+1]+=2
                    C[d+1]+=1
                poly=NTT_Pow(CC,2)
                for d,c in enumerate(poly):
                    if d<self.V:
                        cnt[d]-=c
            while C and C[-1]==0:
                C.pop()
            poly=NTT_Pow(C,2)
            for d,c in enumerate(poly):
                if d<N:
                    cnt[d]+=c
        for i in range(self.V):
            cnt[i]//=2
        return cnt

    def Shortest_Path_Count(self,s,dist,mod=0):
        cnt=[0]*self.V
        cnt[s]=1
        for x in sorted([x for x in range(self.V)],key=lambda x:dist[x]):
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                else:
                    d=1
                if dist[x]+d==dist[y]:
                    cnt[y]+=cnt[x]
                    if mod:
                        cnt[y]%=mod
        return cnt

    def K_Shortest_Path_Routing(self,s,t,K,edge_unicursal=False,point_unicursal=False):
        if point_unicursal:
            if self.weighted:
                dist,parents=self.Dijkstra(s,route_restoration=True)
            else:
                parents,dist=self.SIV_BFS(s,parents=True,unweighted_dist=True)
            route=tuple(self.Route_Restoration(s,t,parents))
            queue=[(dist[t],route,[dist[x] for x in route])]
            set_queue=set()
            set_queue.add(route)
            retu=[]
            while queue and K:
                d,route,route_dist=heapq.heappop(queue)
                retu.append((d,route,route_dist))
                K-=1
                set_route=set()
                for i in range(len(route)-1):
                    x=route[i]
                    set_route.add(x)
                    if self.weighted:
                        edges=[(v,u,d) for u,v,d in self.edges if not u in set_route and not v in set_route]
                    else:
                        edges=[(v,u) for u,v in self.edges if not u in set_route and not v in set_route]
                    G_rev=Graph(self.V,edges=edges,directed=self.directed,weighted=self.weighted,inf=self.inf)
                    if self.weighted:
                        dist_rev,parents_rev=G_rev.Dijkstra(t,route_restoration=True)
                    else:
                        parents_rev,dist_rev=G_rev.SIV_BFS(t,parents=True,unweighted_dist=True)
                    for y in self.graph[x]:
                        if self.weighted:
                            y,d=y
                        else:
                            d=1
                        if y==route[i+1]:
                            continue
                        if dist_rev[y]==self.inf:
                            continue
                        tpl=route[:i+1]+tuple(self.Route_Restoration(t,y,parents_rev)[::-1])
                        if not tpl in set_queue:
                            heapq.heappush(queue,(route_dist[i]+d+dist_rev[y],tpl,route_dist[:i+1]+[route_dist[i]+d+dist_rev[y]-dist_rev[z] for z in tpl[i+1:]]))
                            set_queue.add(tpl)
        elif edge_unicursal:
            if self.weighted:
                dist,parents=self.Dijkstra(s,route_restoration=True)
            else:
                parents,dist=self.SIV_BFS(s,parents=True,unweighted_dist=True)
            route=tuple(self.Route_Restoration(s,t,parents))
            queue=[(dist[t],route,[dist[x] for x in route])]
            set_queue=set()
            set_queue.add(route)
            retu=[]
            while queue and K:
                d,route,route_dist=heapq.heappop(queue)
                retu.append((d,route,route_dist))
                K-=1
                set_route=set()
                for i in range(len(route)-1):
                    x=route[i]
                    y=route[i+1]
                    set_route.add((x,y,route_dist[i+1]-route_dist[i]))
                    if not self.directed:
                        set_route.add((y,x,route_dist[i+1]-route_dist[i]))
                    if self.weighted:
                        edges=[(v,u,d) for u,v,d in self.edges if not (u,v,d) in set_route]
                    else:
                        edges=[(v,u) for u,v in self.edges if not (u,v,d) in set_route]
                    G_rev=Graph(self.V,edges=edges,directed=self.directed,weighted=self.weighted,inf=self.inf)
                    if self.weighted:
                        dist_rev,parents_rev=G_rev.Dijkstra(t,route_restoration=True)
                    else:
                        parents_rev,dist_rev=G_rev.SIV_BFS(t,parents=True,unweighted_dist=True)
                    for y in self.graph[x]:
                        if self.weighted:
                            y,d=y
                        else:
                            d=1
                        if y==route[i+1]:
                            continue
                        if dist_rev[y]==self.inf:
                            continue
                        tpl=route[:i+1]+tuple(self.Route_Restoration(t,y,parents_rev)[::-1])
                        if not tpl in set_queue:
                            heapq.heappush(queue,(route_dist[i]+d+dist_rev[y],tpl,route_dist[:i+1]+[route_dist[i]+d+dist_rev[y]-dist_rev[z] for z in tpl[i+1:]]))
                            set_queue.add(tpl)
        else:
            if self.weighted:
                dist,parents=self.Dijkstra(s,route_restoration=True)
            else:
                parents,dist=self.SIV_BFS(s,parents=True,unweighted_dist=True)
            if dist[t]==self.inf:
                return False
            route_lst=[tuple(self.Route_Restoration(s,x,parents)) for x in range(self.V)]
            if self.weighted:
                edges_rev=[(j,i,d) for i,j,d in self.edges]
            else:
                edges_rev=[(j,i) for i,j in self.edges]
            G_rev=Graph(self.V,edges=edges_rev,weighted=self.weighted,directed=self.directed,inf=self.inf)
            if self.weighted:
                dist_rev,parents_rev=G_rev.Dijkstra(t,route_restoration=True)
            else:
                parents_rev,dist_rev=G_rev.SIV_BFS(t,parents=True,unweighted_dist=True)
            route_rev_lst=[]
            for x in range(self.V):
                route_rev_lst.append(tuple(self.Route_Restoration(t,x,parents_rev)[::-1]))
            route=route_lst[t]
            queue=[(dist[t],route,[dist[x] for x in route])]
            set_queue=set()
            set_queue.add(route)
            retu=[]
            while queue and K:
                d,route,route_dist=heapq.heappop(queue)
                retu.append((d,route,route_dist))
                K-=1
                for i in range(len(route)):
                    x=route[i]
                    for y in self.graph[x]:
                        if self.weighted:
                            y,d=y
                        else:
                            d=1
                        if i!=len(route)-1 and y==route[i+1]:
                            continue
                        tpl=route[:i+1]+route_rev_lst[y]
                        if not tpl in set_queue:
                            heapq.heappush(queue,(route_dist[i]+d+dist_rev[y],tpl,route_dist[:i+1]+[route_dist[i]+d+dist_rev[y]-dist_rev[z] for z in route_rev_lst[y]]))
                            set_queue.add(tpl)
        return retu

    def Euler_Path(self,s=None,t=None):
        if self.directed:
            indegree=[0]*self.V
            outdegree=[0]*self.V
            graph=[[] for x in range(self.V)]
            for tpl in self.edges:
                if self.weighted:
                    u,v,d=tpl
                else:
                    u,v=tpl
                indegree[v]+=1
                outdegree[u]+=1
                graph[v].append(u)
            for x in range(self.V):
                if indegree[x]+1==outdegree[x]:
                    if s==None:
                        s=x
                    elif s!=x:
                        return False
                elif indegree[x]==outdegree[x]+1:
                    if t==None:
                        t=x
                    elif t!=x:
                        return False
                elif indegree[x]!=outdegree[x]:
                    return False
            if (s,t)==(None,None):
                for x in range(self.V):
                    if graph[x]:
                        s=x
                        t=x
                        break
            elif s==None:
                s=t
            elif t==None:
                t=s
            elif s==t:
                for x in range(self.V):
                    if indegree[x]!=outdegree[x]:
                        return False
            queue=[t]
            euler_path=[]
            while queue:
                while graph[queue[-1]]:
                    queue.append(graph[queue[-1]].pop())
                x=queue.pop()
                euler_path.append(x)
            for x in range(self.V):
                if graph[x]:
                    return False
        else:
            degree=[0]*self.V
            graph=[[] for x in range(self.V)]
            use_count=[defaultdict(int) for x in range(self.V)]
            for tpl in self.edges:
                if self.weighted:
                    u,v,d=tpl
                else:
                    u,v=tpl
                degree[v]+=1
                degree[u]+=1
                graph[u].append(v)
                graph[v].append(u)
            for x in range(self.V):
                if degree[x]%2:
                    if s==None and t!=x:
                        s=x
                    elif t==None and s!=x:
                        t=x
                    elif not x in (s,t):
                        return False
            if s==None and t==None:
                for x in range(self.V):
                    if graph[x]:
                        s=x
                        t=x
                        break
                else:
                    s,t=0,0
            elif s==None:
                s=t
            elif t==None:
                t=s
            elif s!=t:
                if degree[s]%2==0 or degree[t]%2==0:
                    return False
            queue=[t]
            euler_path=[]
            while queue:
                while graph[queue[-1]]:
                    if use_count[queue[-1]][graph[queue[-1]][-1]]:
                        use_count[queue[-1]][graph[queue[-1]][-1]]-=1
                        graph[queue[-1]].pop()
                    else: 
                        queue.append(graph[queue[-1]].pop())
                        use_count[queue[-1]][queue[-2]]+=1
                x=queue.pop()
                euler_path.append(x)
            for x in range(self.V):
                if graph[x]:
                    return False
        if euler_path[0]!=s:
            return False
        return euler_path

    def Route_Restoration(self,s,g,parents):
        route=[g]
        while s!=g:
            if parents[g]==None:
                route=[]
                break
            g=parents[g]
            route.append(g)
        route=route[::-1]
        return route

    def Negative_Cycls(self):
        dist=[0]*self.V
        for _ in range(self.V-1):
            for i,j,d in self.edges:
                dist[j]=min(dist[j],dist[i]+d)
        for i,j,d in self.edges:
            if dist[j]>dist[i]+d:
                return True
        return False

    def Kruskal(self,maximize=False):
        UF=UnionFind(self.V)
        sorted_edges=sorted(self.edges,key=lambda x:x[2],reverse=maximize)
        spnning_tree=[]
        for i,j,d in sorted_edges:
            if not UF.Same(i,j):
                UF.Union(i,j)
                spnning_tree.append((i,j,d))
        return spnning_tree

    def Max_Clique(self):
        graph=[[False]*self.V for x in range(self.V)]
        for x in range(self.V):
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                graph[x][y]=True
        N0,N1=self.V//2,self.V-self.V//2
        pop_count=[sum(bit>>i&1 for i in range(N1)) for bit in range(1<<N1)]
        is_clique0=[True]*(1<<N0)
        for j in range(N0):
            for i in range(j):
                if not graph[i][j]:
                    is_clique0[1<<i|1<<j]=False
        for i in range(N0):
            for bit in range(1<<N0):
                if bit&1<<i:
                    is_clique0[bit]=is_clique0[bit]&is_clique0[bit^1<<i]
        is_clique1=[True]*(1<<N1)
        for j in range(N1):
            for i in range(j):
                if not graph[i+N0][j+N0]:
                    is_clique1[1<<i|1<<j]=False
        for i in range(N1):
            for bit in range(1<<N1):
                if bit&1<<i:
                    is_clique1[bit]=is_clique1[bit]&is_clique1[bit^1<<i]
        max_clique_bit=[bit if is_clique0[bit] else 0 for bit in range(1<<N0)]
        for i in range(N0):
            for bit in range(1<<N0):
                if bit&1<<i and pop_count[max_clique_bit[bit]]<pop_count[max_clique_bit[bit^1<<i]]:
                    max_clique_bit[bit]=max_clique_bit[bit^1<<i]
        dp=[(1<<N0)-1]*(1<<N1)
        for j in range(N1):
            for i in range(N0):
                if not graph[j+N0][i]:
                    dp[1<<j]^=1<<i
        for i in range(N1):
            for bit in range(1<<N1):
                if bit&1<<i:
                    dp[bit]&=dp[bit^1<<i]
        bit0,bit1=0,0
        for bit in range(1<<N1):
            if is_clique1[bit] and pop_count[max_clique_bit[dp[bit]]]+pop_count[bit]>pop_count[bit0]+pop_count[bit1]:
                bit0=max_clique_bit[dp[bit]]
                bit1=bit
        max_clique=[i for i in range(N0) if bit0&1<<i]+[i+N0 for i in range(N1) if bit1&1<<i]
        return max_clique

    def Cliques(self):
        graph=[[False]*self.V for x in range(self.V)]
        for x in range(self.V):
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                graph[x][y]=True
        cliques=[]
        points=[x for x in range(self.V)]
        while points:
            l=len(points)
            min_degree,min_degree_point=self.inf,None
            sum_degree=0
            for x in points:
                s=sum(graph[x][y] for y in points)
                sum_degree+=s
                if s<min_degree:
                    min_degree=s
                    min_degree_point=x
            if min_degree**2>=sum_degree:
                lst=points
            else:
                lst=[x for x in points if x==min_degree_point or graph[min_degree_point][x]]
            l=len(lst)
            is_clique=[True]*(1<<l)
            for j in range(l):
                for i in range(j):
                    if not graph[lst[i]][lst[j]]:
                        is_clique[1<<i|1<<j]=False
            for i in range(l):
                for bit in range(1<<l):
                    if bit&1<<i:
                        is_clique[bit]=is_clique[bit]&is_clique[bit^1<<i]
            if min_degree**2>=sum_degree:
                for bit in range(1<<l):
                    if is_clique[bit]:
                        cliques.append([lst[i] for i in range(l) if bit&1<<i])
            else:
                idx=lst.index(min_degree_point)
                for bit in range(1<<l):
                    if is_clique[bit] and bit&1<<idx:
                        cliques.append([lst[i] for i in range(l) if bit&1<<i])
            if min_degree**2>=sum_degree:
                points=[]
            else:
                points=[x for x in points if x!=min_degree_point]
        return cliques

    def Ford_Fulkerson(self,s,t):
        max_flow=0
        residual_graph=[defaultdict(int) for i in range(self.V)]
        if self.weighted:
            for i,j,d in self.edges:
                if not d:
                    continue
                residual_graph[i][j]+=d
                if not self.directed:
                    residual_graph[j][i]+=d
        else:
            for i,j in self.edges:
                residual_graph[i][j]+=1
                if not self.directed:
                    residual_graph[j][i]+=1
        while True:
            parents=[None]*self.V
            parents[s]=s
            seen=[False]*self.V
            seen[s]=True
            queue=deque([s])
            while queue:
                x=queue.popleft()
                for y in residual_graph[x].keys():
                    if not seen[y]:
                        seen[y]=True
                        queue.append(y)
                        parents[y]=x
                        if y==t:
                            tt=t
                            while tt!=s:
                                residual_graph[parents[tt]][tt]-=1
                                residual_graph[tt][parents[tt]]+=1
                                if not residual_graph[parents[tt]][tt]:
                                    residual_graph[parents[tt]].pop(tt)
                                tt=parents[tt]
                            max_flow+=1
                            break
                else:
                    continue
                break
            else:
                break
        return max_flow

    def BFS(self,s):
        seen=[False]*self.V
        seen[s]=True
        queue=deque([s])

        while queue:
            x=queue.popleft()
            for y in self.graph[x]:
                if self.weighted:
                    y,d=y
                if not seen[y]:
                    seen[y]=True
                    queue.append(y)
                    
        return 

    def DFS(self,s):
        seen=[False]*self.V
        finished=[False]*self.V
        stack=[(s,0)] if self.weighted else [s]

        while stack:
            if self.weighted:
                x,d=stack.pop()
            else:
                x=stack.pop()
            if not seen[x]:
                seen[x]=True
                stack.append((x,d) if self.weighted else x)

                for y in self.graph[x]:
                    if self.weighted:
                        y,d=y
                    if not seen[y]:
                        stack.append((y,d) if self.weighted else y)
            elif not finished[x]:
                finished[x]=True
                
        return 

N,M=map(int,readline().split())
A=list(map(int,readline().split()))
B=list(map(int,readline().split()))
for i in range(M):
    A[i]-=1
    B[i]-=1
edges=[]
for a,b in zip(A,B):
    edges.append((a,b))
seen=[False]*N
G=Graph(N,edges=edges)
parents,depth=G.SIV_DFS(0,parents=True,unweighted_dist=True)
ans_lst=[None]*M
for i,(a,b) in enumerate(zip(A,B)):
    if parents[a]==b:
        if seen[a]:
            ans_lst[i]=1
        else:
            ans_lst[i]=0
            seen[a]=True
    elif parents[b]==a:
        if seen[b]:
            ans_lst[i]=0
        else:
            ans_lst[i]=1
            seen[b]=True
    elif depth[a]<depth[b]:
        ans_lst[i]=0
    else:
        ans_lst[i]=1
print(*ans_lst,sep="")