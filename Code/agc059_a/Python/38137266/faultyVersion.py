N,Q=map(int,input().split())
S=input()

#Segment Tree
def operator(x,y): #行う計算
    return x+y

identity=0 #単位元

def stcreate(N:int): #セグメント木の生成,N=要素数
    nl=(N-1).bit_length()
    ST=[identity]*(2**(nl+1))
    return ST

def stupdate(ST:list,i:int,x): #値の更新
    ci=(len(ST)>>1)+i
    ST[ci]=x
    ci>>=1
    while ci>0:
        ST[ci]=operator(ST[ci*2],ST[ci*2+1])
        ci>>=1

def stget(ST:list,l:int,r:int): #区間値の取得
    d=(len(ST)>>1)
    cl=d+l
    cr=d+r
    s=identity
    while cl<=cr:
        if cl%2==1:
            s=operator(s,ST[cl])
            cl+=1
        if cr%2==0:
            s=operator(s,ST[cr])
            cr-=1
        cl>>=1
        cr>>=1
    return s

ST=stcreate(N-1)
for i in range(N-1):
    if S[i]!=S[i+1]:
        stupdate(ST,i,1)

for i in range(Q):
    l,r=map(int,input().split())
    if l==r:
        print(0)
    else:
        s=stget(ST,l-1,r-2)
        print((s+1)//2)