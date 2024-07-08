class Segtree:#入力は全て0-indexedでok.
    def __init__(self,n,operator,identity):
        num=1<<((n-1).bit_length())
        self.tree=[identity]*(2*num-1)
        self.identity=identity 
        self.operator=operator
        self.num=num

    def update(self,i,val):#i番目をvalに変える(変えた後、上の方に更新を伝播)
        i+=self.num-1
        self.tree[i]=val
        while i>=0:
            i=(i-1)//2
            if i>=0:
                self.tree[i]=self.operator(self.tree[i*2+1],self.tree[i*2+2])
    
    def plus(self,i,val):#i番目にval足す
        now=self.q(i,i+1)
        self.update(i,val+now)
    
    def q(self,l,r):#[l,r)のoperate値を求める
        l+=self.num#ここは1-indexになってる
        r+=self.num#ここは1-indexになってる
        s=self.identity#こいつに計算していく
        while l<r:
            if r & 1:
                r -= 1
                s = self.operator(s, self.tree[r-1])#１引いて0-indexedに戻す

            if l & 1:
                s = self.operator(s, self.tree[l-1])#同上
                l += 1
            l >>= 1; r >>= 1
        return s
def a(x,y):
    return x+y


#ABC287 G
#クエリ先読み、何もわからん
N=int(input())
card=[tuple(map(int,input().split())) for _ in range(N)]
Q=int(input())
query=[tuple(map(int,input().split())) for _ in range(Q)]
v=set()
for i in range(N):
    v.add(card[i][0])
for i in range(Q):
    if query[i][0]==1:
        v.add(query[i][2])
v=list(v)
v.sort(reverse=True)
n=len(v)
d=dict()
for i in range(n):
    d[v[i]]=i
X=Segtree(n,a,0)
Y=Segtree(n,a,0)
v_now=[-1]*N
l_now=[-1]*N
for i in range(N):
    a,b=card[i]
    X.plus(d[a],b)
    Y.plus(d[a],a*b)
    v_now[i]=a
    l_now[i]=b
for i in range(Q):
    tup=query[i]
    if tup[0]==1:
        x,y=tup[1:]
        x-=1
        a,b=v_now[x],l_now[x]
        X.plus(d[a],-b)
        Y.plus(d[a],-a*b)
        X.plus(d[y],b)
        Y.plus(d[y],y*b)
        v_now[x]=y
    if tup[0]==2:
        x,y=tup[1:]
        x-=1
        a,b=v_now[x],l_now[x]
        X.plus(d[a],-b)
        Y.plus(d[a],-a*b)
        X.plus(d[a],y)
        Y.plus(d[a],a*y)
        l_now[x]=y
    if tup[0]==3:
        x=tup[1]
        #つらい
        if X.q(0,n)<x:
            print(-1)
        elif X.q(0,n)==x:
            print(Y.q(0,n))
        else:
            l=0
            r=n-1
            while r-l!=1:
                now=(r+l)//2
                if X.q(0,now+1)<=x:
                    l=now
                else:
                    r=now
            ans=Y.q(0,l+1)
            num=X.q(0,l+1)
            t=x-num
            ans+=t*v[l+1]
            print(ans)