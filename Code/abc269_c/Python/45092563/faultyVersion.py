n=int(input())
i=1
L=[]
while i<n:
    if i&n:
        L+=[i]
    i*=2
ans=[]
def f(i):
    add=[i]
    for k in ans:
        add+=[i+k]
    return add
for i in L:
    ans+=f(i)
print(0,*ans,sep="\n")