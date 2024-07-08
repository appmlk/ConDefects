N,P=map(int,input().split())
A=list(map(int,input().split()))

pr=set()
K=P-1
i=2
while i*i<=P-1:
    while K%i==0:
        if not(i in set()):
            pr.add(i)
        K//=i
    if i==2:
        i+=1
    else:
        i+=2
if K!=1:
    pr.add(K)

div=[]
div2=[]
i=1
while i*i<=P-1:
    if (P-1)%i==0:
        div.append(i)
        div2.append((P-1)//i)
    i+=1

if div2[-1]==div[-1]:
    div2.pop()
while div2:
    div.append(div2.pop())

d=dict()
for i in range(len(div)):
    d[div[i]]=i

def n_ord(a):
    ans=P-1
    for p in pr:
        while ans%p==0:
            l=ans//p
            if pow(a,l,P)==1:
                ans=l
            else:
                break
    return d[ans]

B=[0]*len(div)
for a in A:
    B[n_ord(a)]+=1

cnt=0
for i in range(len(div)):
    for j in range(i,len(div)):
        if div[j]%div[i]==0:
            cnt+=B[i]*B[j]
        
print(cnt)