from atcoder.dsu import DSU
from atcoder.segtree import SegTree

N, K = map(int, input().split())
P = list(map(int, input().split()))
ans = [-1] * (N + 1)
if K == 1:
    for i,p in enumerate(P,start= 1):
        ans[p] = i
    print(*ans)        
    exit()
    
dsu = DSU(N + 1)
st = SegTree(max, 0, [0] * (N + 1))
dic = {}
for i, p in enumerate(P, start=1):
    x = st.max_right(p, lambda v: v == 0)
    if x > N or x < p:
        st.set(p, 1)
    elif x > p:
        st.set(x, 0)
        dsu.merge(x, p)
        if dsu.size(x) == K:
            dic[dsu.leader(x)] = i
        else:
            st.set(p, 1)

for i in range(N + 1):
    x = dsu.leader(i)
    if x in dic:
        ans[i] = dic[x]
print(*ans[1:])
