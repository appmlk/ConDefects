from atcoder.segtree import SegTree

N=int(input())
S={0}
P=[None]*N
for i in range(N):
  h,w,d=map(int,input().split())
  S.add(h); S.add(w); S.add(d)
  P[i]=(h,w,d)
D={v:i for i,v in enumerate(sorted(S))}
points=[]
for i in range(N):
  a,b,c=list(sorted(P[i]))
  a=D[a]; b=D[b]; c=D[c]
  points.append((a,b,c))

points.sort(key = lambda x: (x[0], -x[1], -x[2]))

INF = 1<<60
st = SegTree(min, INF, [INF]*(len(S)+5))

for x, y, z in points:
  if st.prod(0, y)<z:
    print("Yes")
    exit()
  st.set(y, z)

print("No")
