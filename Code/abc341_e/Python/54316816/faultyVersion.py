from atcoder.segtree import SegTree

n,q=map(int,input().split())
S=input()

L=[0]*(n+1)
for i in range(1,n-1):
    if S[i]!=S[i-1]:
        L[i]=1

def op(a,b):
  return a+b

st=SegTree(op,0,L)
# print(1)
for i in range(q):
    num,l,r=map(int,input().split())
    if num==1:
        st.set(l-1,(st.get(l-1)+1)%2)
        st.set(r,(st.get(r)+1)%2)
    else:
        temp=st.prod(l,r)
        if temp==r-l:
            print('Yes')
        else:
            print('No')


