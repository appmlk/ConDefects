n=int(input())
A=[*map(int,input().split())]
B=[*map(int,input().split())]
st=sorted

def check(a,b):
    ev=0
    for i in a: 
        if i%2==0:ev+=1

    if ev==0 and a!=b:
        return 0     

    od=0
    for i in range(n-2):
        if a[i]%2 + a[i+1]%2 + a[i+2]%2 > 1:
            od=1
    

    if od and st(a)==st(b):
        if ev==2:
            ea,eb=0,0
            for i in a:
                if i%2==0:ea=i;break
            for i in b:
                if i%2==0:eb=i;break
            if ea!=eb:
                return 0  
        return 1

    l=0
    for i in range(n):
        if a[i]%2:
            if a[i]!=b[i]:
                return 0
            if st(a[l:i])!=st(b[l:i]):
                return 0
            if i<3 and a[:i]!=b[:i]:
                return 0
            l=i+1

    if n-l>2:
        return st(a[l:])==st(b[l:])
    return a[l:]==b[l:]

if check(A,B) and check(B,A):
    print('Yes')
else:
    print('No')